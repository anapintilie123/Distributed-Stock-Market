package nl.rug.aoop.networking.client;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * The client class that takes messages from the user.
 */
@Slf4j
public class Client implements Runnable , ICommunicator {
    private final int TIMEOUT = 10000;
    private Socket socket;
    private InetSocketAddress address;
    private PrintWriter out;
    private BufferedReader in;
    @Getter
    private boolean running = false ;
    private MessageHandler messageHandler;

    /**
     * Client constructor.
     *
     * @param address address of server that client connects to.
     * @param messageHandler handles messages for the client.
     * @throws IOException checks if port is correct.
     */
    public Client(InetSocketAddress address, MessageHandler messageHandler) throws IOException {
        initSocket(address);
        this.messageHandler = messageHandler;
    }

    /**
     * Initiates the socket for the server, and the streams.
     *
     * @param address the server socket address.
     * @throws IOException checks if address is correct.
     */
    private void initSocket(InetSocketAddress address) throws IOException {
        this.socket = new Socket();
        socket.connect(address, TIMEOUT);

        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        if (!socket.isConnected()) {
            throw new IOException("Socket is not connected to the server");
        }
    }

    /**
     * Prints a message to the terminal.
     *
     * @param message the body of the message.
     */
    public void sendMessage(String message){
        if(message == null || message.equals("")) {
            throw new IllegalArgumentException("message is not correct");
        }
        out.println(message);
    }

    /**
     * The connection to the server.
     */
    public void run(){
        running = true;
        while(running) {
            try {
                String serverResponse = in.readLine();
                if(serverResponse == null){
                    terminate();
                    break;
                }
                log.info("client received "+ serverResponse);
                messageHandler.handleMessage(serverResponse,this);
            } catch (IOException e) {
                terminate();
            }
        }
    }

    /**
     * terminates when no longer connected to the server.
     */
    public void terminate(){
        log.info("Server closed . Terminating");
        running = false;
        try {
            this.socket.close();
        } catch (IOException e) {
            log.error("Cannot close the socket ! Client closed !",e);
        }
    }

    public boolean isConnected(){
        return !this.socket.isClosed();
    }
}
