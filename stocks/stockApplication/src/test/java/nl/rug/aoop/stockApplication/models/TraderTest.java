package nl.rug.aoop.stockApplication.models;

import nl.rug.aoop.stockApplication.operations.Stocks;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TraderTest {

    @Test
    public void nullStocksTest() {
        StockPortfolio stockPortfolio= new StockPortfolio();
        assertThrows(IllegalArgumentException.class ,()->new Trader("pp","ll",500,stockPortfolio,null));
    }

    @Test
    public void ownsStocksTest() {
        StockPortfolio stockPortfolio= new StockPortfolio();
        Stocks stocks = new Stocks();
        Trader trader =new Trader("pp","ll",-500,stockPortfolio,stocks);
        assertEquals(false,trader.ownsStocks());
    }

    @Test
    public void updateBuyTrader() {
        StockPortfolio stockPortfolio= new StockPortfolio();
        stockPortfolio.getOwnedShares().put("AM",6);
        Stocks stocks = new Stocks();
        Trader trader =new Trader("pp","ll",-500,stockPortfolio,stocks);
        int initialPrice = trader.getFunds();
        trader.updateBuyTrader("OP",700);
        assertTrue(trader.getFunds() == (initialPrice-700) );
    }

    @Test
    public void updateSellTraderWrongStockTest() {
        StockPortfolio stockPortfolio= new StockPortfolio();
        stockPortfolio.getOwnedShares().put("AM",6);
        Stocks stocks = new Stocks();
        Trader trader =new Trader("pp","ll",500,stockPortfolio,stocks);
        int initialPrice = trader.getFunds();
        trader.updateBuyTrader("OP",200);
        assertFalse(trader.getFunds() == (initialPrice+700) );
    }
}