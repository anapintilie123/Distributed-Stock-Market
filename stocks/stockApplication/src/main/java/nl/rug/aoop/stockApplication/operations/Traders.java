package nl.rug.aoop.stockApplication.operations;

import lombok.Getter;
import nl.rug.aoop.stockApplication.models.Trader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  a map of traders.
 */
public class Traders {
    @Getter
    private Map<String, Trader> map ;

    /**
     * constructor.
     */
    public Traders(){
        this.map=new HashMap<String,Trader>();
    }

    /**
     * getter.
     *
     * @param traderID the trader id.
     * @return the map.
     */
    public Trader getTrader(String traderID){
        return this.map.get(traderID);
    }

    /**
     * sets a map.
     *
     * @param listTraders the traders.
     * @param stocks the stocks.
     */
    public void setTradersMap(List<Trader> listTraders,Stocks stocks){
        for(Trader trader: listTraders){
            String id = trader.getId();
            trader.setStocks(stocks);
            this.map.put(id,trader);
        }
    }
}
