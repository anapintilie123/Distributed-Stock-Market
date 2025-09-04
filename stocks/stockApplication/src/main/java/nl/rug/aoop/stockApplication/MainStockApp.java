package nl.rug.aoop.stockApplication;

/**
 * Main class for the stockApplication.
 */
public class MainStockApp {
    /**
     * main.
     *
     * @param args main.
     */
    public static void main(String[] args) {
        System.out.println("Stock application is starting...");
        StackApp app = new StackApp();
        app.init();
        new Thread(app).start();
    }
}
