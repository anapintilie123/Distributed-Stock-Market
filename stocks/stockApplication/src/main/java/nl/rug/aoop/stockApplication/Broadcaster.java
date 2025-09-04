package nl.rug.aoop.stockApplication;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.messagequeue.Messagez.Message;
import nl.rug.aoop.networking.client.ICommunicator;
import nl.rug.aoop.stockApplication.models.Trader;
import nl.rug.aoop.stockApplication.operations.Traders;

import java.util.Map;

/**
 * sends updates to the trader application.
 */
@Slf4j
public class Broadcaster {

    private Map<String, ICommunicator> connectedTraders;
    private Traders traders;

    /**
     * constructor.
     *
     * @param connectedTraders map of connected traders.
     * @param traders the traders.
     */
    public Broadcaster(Map<String, ICommunicator> connectedTraders, Traders traders) {
        this.connectedTraders = connectedTraders;
        this.traders = traders;
    }

    /**
     * sends the updated info to each trader.
     */
    public void sendUpdatesToTraders(){
        for (Map.Entry<String, ICommunicator> entry : this.connectedTraders.entrySet()) {
            log.info("connected trader : "+ entry.getKey());
            String IDtrader = entry.getKey();
            String update = createUpdate(IDtrader);
            entry.getValue().sendMessage(update);
        }
    }

    /**
     * wraps the trader info to be sent to the traderApp.
     *
     * @param traderID the trader.
     * @return Trader as a string.
     */
    public String createUpdate(String traderID){
        Trader trader = traders.getTrader(traderID);
        String JSON = trader.toJSONString();
        Message update = new Message("UpdateTrader",JSON);
        return update.toJSONString();
    }
}
