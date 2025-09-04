package nl.rug.aoop.networking.server;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Creates a server that handles every connection.
 */
@Slf4j
@Getter
public class Server implements Runnable{
    private final ServerSocket serverSocket ;
    private boolean running = false;
    private final int port;
    private final ExecutorService executerService;
    private int threadNr;
    private final MessageHandler messageHandler;

    /**
     * constructor for class.
     *
     * @param port port number of server.
     * @param messageHandler mm.
     * @throws IOException checks for correct port.
     */
    public Server( int port, MessageHandler messageHandler) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
        this.executerService = Executors.newCachedThreadPool();
        this.threadNr = 0 ;
        this.messageHandler = messageHandler;
    }

    /**
     * Keeps the server running and checks for connections.
     */
    public void run(){
        log.info("server has booted up");
        running = true;
        while(running){
            try {
                Socket socket = serverSocket.accept();
                executerService.submit(new ClientHandler(socket,threadNr,messageHandler));
            } catch (IOException e) {
                log.error("An error has occurred while trying to accept the client connection",e);
            }
            threadNr++;
        }
    }

    /**
     * terminates the server.
     */
    public void terminate(){
        running = false;
    }

    public int getPort(){
        return serverSocket.getLocalPort();
    }

    public int getNumClients(){
        return threadNr;
    }
}
