package nl.rug.aoop.networking.server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.ICommunicator;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Accepts messages from the user, and echos them, and terminates on BYE.
 */
@Slf4j
@Getter
public class ClientHandler implements Runnable, ICommunicator {
    private final Socket socket;
    private final PrintWriter out;
    private final BufferedReader in;
    private boolean running = false;
    private final int threadNr;
    private final MessageHandler messageHandler;

    /**
     * Constructor for class.
     *
     * @param socket server socket.
     * @param threadNr thread that this handles is running on.
     * @param messageHandler hh.
     * @throws IOException checks for correct input.
     */
    public ClientHandler(Socket socket, int threadNr, MessageHandler messageHandler) throws IOException {
        this.threadNr = threadNr;
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.messageHandler = messageHandler;
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            try {
                String input = in.readLine();
                log.info("ClientHandler received input " + input + " from a client");
                if (input == null || input.trim().equalsIgnoreCase("BYE")) {
                    terminate();
                    break;
                }
                messageHandler.handleMessage(input,this);
            } catch (IOException e) {
                log.info("Client connection closed");
                terminate();
            }
        }
    }

    /**
     * Sends a message to the terminal.
     *
     * @param message message to be sent.
     */
    @Override
    public void sendMessage(String message) {
        if (message == null || message.equals("")) {
            throw new IllegalArgumentException("message is not correct");
        }
        out.println(message);
    }

    /**
     * terminates the connection.
     */
    public void terminate() {
        running = false;
        try {
            socket.close();
        } catch (IOException e) {
            log.error("Cannot close the socket.", e);
        }
    }
}
