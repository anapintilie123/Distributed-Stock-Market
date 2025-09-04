package nl.rug.aoop.stockApplication;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.command.ICommand;
import nl.rug.aoop.stockApplication.orders.Order;

import java.util.Map;

/**
 * command handler for orders.
 */
@Slf4j
public class StocksCommand implements ICommand {

    private Order order;
    private StocksManager stocksManager;

    /**
     * constructor.
     *
     * @param manager the manager.
     */
    public StocksCommand(StocksManager manager) {
        this.stocksManager = manager;
    }

    /**
     * executes a command.
     *
     * @param options list of commands.
     */
    @Override
    public void execute(Map<String, Object> options) {
        String JsonMessage = (String) options.get("body");
        this.order = Order.fromJSONString(JsonMessage);
        log.info("StocksCommand: " +order.getType() + order.getStockID()  + order.getPrice());
        if(order.getType().equals("buy")){
            this.stocksManager.acceptAsk(order);
        }else{
            this.stocksManager.acceptBid(order);
        }
    }
}