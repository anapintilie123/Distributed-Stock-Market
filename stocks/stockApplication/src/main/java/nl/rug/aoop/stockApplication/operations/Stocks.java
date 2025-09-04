package nl.rug.aoop.stockApplication.operations;

import lombok.Getter;
import nl.rug.aoop.stockApplication.models.Stock;

import java.util.*;

/**
 * Data structure for stocks.
 */
public class Stocks {

    @Getter
    private final Map<String, Stock> map;

    /**
     * constructor.
     */
    public Stocks() {
        map = new HashMap<>();
    }

    /**
     * getter for stocks.
     *
     * @return map.
     */
    public Map<String, Stock> getStocks() {
        return map;
    }

    /**
     * gets a random stock.
     *
     * @return randomStockID.
     */
    public String getRandomElement() {
        Set<String> keySet = map.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int randIdx = new Random().nextInt(size);

        String randomStockID = keyList.get(randIdx);
        Stock randomStock = map.get(randomStockID);

        System.out.println("key: " + randomStockID + ", Stock: " + randomStock);
        return randomStockID;
    }

}
