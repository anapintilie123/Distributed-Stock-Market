package nl.rug.aoop.networking.client;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * creates a scanner for the client, and checks for BYE to terminate.
 */
@Slf4j
public class MessageFilter implements Runnable {
    private boolean running;
    private final Client client;
    private final Scanner scanner;

    /**
     * The constructor.
     *
     * @param client the client that gets its messages filtered
     */
    public MessageFilter(Client client) {
        this.client = client;
        this.scanner = new Scanner(System.in);
        System.out.println("message filter instance created");
    }

    /**
     * Runs while input is not BYE.
     */
    public void run() {
        running = true;
        while (running) {
            String input = scanner.nextLine();
            client.sendMessage(input);
            if (input.trim().equalsIgnoreCase("BYE")) {
                client.terminate();
                terminate();
            }
        }
    }

    /**
     * terminates the run.
     */

    private void terminate() {
        running = false;
    }
}
