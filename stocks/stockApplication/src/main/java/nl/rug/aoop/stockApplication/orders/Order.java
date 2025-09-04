package nl.rug.aoop.stockApplication.orders;

import com.google.gson.Gson;
import lombok.Getter;

/**
 * An order that a trader can make for buying and selling stocks.
 */
@Getter
public class Order {
    private String type;
    private String stockID;
    private int price;
    private String traderID;

    private static final Gson GSON = new Gson();

    /**
     * constructor.
     *
     * @param type     type of order.
     * @param stockID  id of stock.
     * @param price    price of stock.
     * @param traderID id for trader.
     */
    public Order(String type, String stockID, int price, String traderID) {
        this.type = type;
        this.stockID = stockID;
        this.price = price;
        this.traderID = traderID;
    }

    /**
     * converts order to json.
     *
     * @return JSON String.
     */
    public String toJSONString() {
        return GSON.toJson(this);

    }

    /**
     * converts json to string.
     *
     * @param JSONString the json.
     * @return a string.
     */
    public static Order fromJSONString(String JSONString) {

        return GSON.fromJson(JSONString, Order.class);
    }
}
