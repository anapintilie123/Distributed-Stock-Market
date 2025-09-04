package nl.rug.aoop.traderApplication;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * The main class for the trader application.
 */
@Slf4j
public class MainTraderApplication {
    /**
     * main.
     *
     * @param args main.
     * @throws IOException if thing start incorrectly.
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Trader application started...");
        TradersManager manager =new TradersManager();
        manager.initTraderBots();
        manager.start();
        System.out.println("Trader application finished.");
    }
}
