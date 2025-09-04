package nl.rug.aoop.networking.client;

import lombok.extern.slf4j.Slf4j;
import nl.rug.aoop.networking.client.messageHandlers.MessageHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class ClientTest {

    private int serverPort;
    private boolean serverStarted = false ;
    private PrintWriter serverSideOut;
    private BufferedReader serverSideIn;

    private void startTempServer(){
        new Thread( ()-> {
            try {
                ServerSocket s = new ServerSocket(0);
                serverPort = s.getLocalPort();
                serverStarted = true;
                Socket serverSideSocket = s.accept();
                serverSideOut = new PrintWriter(serverSideSocket.getOutputStream());
                serverSideIn = new BufferedReader(new InputStreamReader(serverSideSocket.getInputStream()));
                log.info("client accepted");
            }
            catch(IOException e){
                fail("Could not start server" + e.getMessage());
            }
        }).start();
        await().atMost(Duration.ofSeconds(1)).until(()-> serverStarted);
    }

    @Test
    public void testConstructorWithRunningServer() throws IOException {
        startTempServer();
        InetSocketAddress address = new InetSocketAddress("localhost", serverPort);
        MessageHandler mockHandler = Mockito.mock(MessageHandler.class);
        Client client = new Client(address, mockHandler);
        assertTrue(client.isConnected());
    }

    @Test
    public void testRunReadSingleMessage() throws IOException{
        startTempServer();
        InetSocketAddress address = new InetSocketAddress("localhost",serverPort);
        //socket client
        MessageHandler mockHandler = Mockito.mock(MessageHandler.class);
        //a fake instance of an interface
        Client client = new Client(address , mockHandler);

        new Thread(client).start();
        await().atMost(3,TimeUnit.SECONDS).until(client :: isRunning);
        assertTrue(client.isRunning());
        String message = "paul";
        serverSideOut.println(message);
        serverSideOut.flush();

        //Mockito.when(mockHandler.handleMessage("paul")).thenThrow(()-> throw new Exception("paul"));
        //this will check if the message sent from teh server to the message handler is paul
        Mockito.verify(mockHandler).handleMessage("paul",client);
    }

}