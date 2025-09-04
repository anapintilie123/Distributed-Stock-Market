package nl.rug.aoop.traderApplication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.CommandHandler;
import nl.rug.aoop.messagequeue.Messagez.MqMessageHandler;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.operations.Stocks;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * initializes all the trader bots.
 */
@Slf4j
public class TradersManager {
    private final List<TraderBot> traderBots;
    private Map<String,Trader> tradersMap;
    private ArrayList<String> tradersIDs;
    private Stocks stocks;
    private CommandHandler commandHandler;
    private MessageHandler messageHandler;

    /**
     * constructor.
     *
     * @throws IOException if things load correctly.
     */
    public TradersManager() throws IOException {
        traderBots = new ArrayList<>();
        tradersMap = new HashMap<>();
        loadBotsYaml();
        loadStocksYaml();
    }

    /**
     * loads the trader info for each bot.
     *
     * @throws IOException if yaml reads correctly.
     */
    private void loadBotsYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Path stringPath = Path.of("data", "tradersIDs.yaml");
        tradersIDs = objectMapper.readValue(stringPath.toFile(), ArrayList.class);
    }

    /**
     * loads the stocks.
     *
     * @throws IOException if yaml reads correctly.
     */
    private void loadStocksYaml() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        Path stringPath = Path.of("data", "stocks.yaml");
        stocks = objectMapper.readValue(stringPath.toFile(), Stocks.class);
    }

    /**
     * initiates all the bots.
     */
    public void initTraderBots() throws IOException {
        commandHandler = new CommandHandler("UpdateTrader", new UpdateTraderCommand(this.tradersMap));
        messageHandler = new MqMessageHandler(commandHandler);
        for (String id : tradersIDs) {
            Trader trader = new Trader(id, null, 0, null, stocks);
            Strategy strategy = new Strategy(trader);
            Interactor interactor = new Interactor(trader, messageHandler, commandHandler);
            TraderBot bot = new TraderBot(strategy, interactor, true, trader);
            traderBots.add(bot);
            tradersMap.put(id,trader);
        }
    }

    /**
     * starts each bot on their own thread.
     */
    public void start() {
        traderBots.forEach((traderBot) -> new Thread(traderBot).start());
    }

}
