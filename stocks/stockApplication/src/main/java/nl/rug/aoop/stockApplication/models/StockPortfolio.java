package nl.rug.aoop.stockApplication.models;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * Map for the stocks that a trader owns.
 */
@Getter
@Setter
public class StockPortfolio {
    private Map<String, Integer> ownedShares;

    /**
     * init owned shares.
     */
    public StockPortfolio() {
        this.ownedShares = new HashMap<String, Integer>();
    }
}
