package nl.rug.aoop.traderApplication;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.ICommand;
import nl.rug.aoop.stockApplication.models.Trader;
import java.util.Map;

import static nl.rug.aoop.stockApplication.models.Trader.fromJSONString;

/**
 * Updates each trader with info from the stock application.
 */
@Slf4j
public class UpdateTraderCommand implements ICommand {
    private Map<String,Trader> tradersMap;

    /**
     * constructor.
     *
     * @param tradersMap trader map.
     */
    public UpdateTraderCommand( Map<String,Trader> tradersMap) {
        this.tradersMap = tradersMap;
    }

    /**
     * executes a command.
     *
     * @param options list of commands.
     */
    @Override
    public void execute(Map<String, Object> options) {
        String JsonTrader = (String) options.get("body");
        Trader updatedTrader = fromJSONString(JsonTrader);
        String updatedID = updatedTrader.getId();
        this.tradersMap.get(updatedID).update(updatedTrader);
        log.info("received update :" + JsonTrader);
    }

}
