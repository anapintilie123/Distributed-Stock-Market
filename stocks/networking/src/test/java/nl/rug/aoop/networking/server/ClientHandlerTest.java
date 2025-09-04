package nl.rug.aoop.networking.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Duration;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
class ClientHandlerTest {
    private int serverPort;
    private boolean serverStarted = false ;
    private Socket serverSideSocket;

    private void startTempServer(){
        new Thread( ()-> {
            try {
                ServerSocket s = new ServerSocket(0);
                serverPort = s.getLocalPort();
                serverStarted = true;
                serverSideSocket = s.accept();
                log.info("client accepted");
            }
            catch(IOException e){
                fail("Could not start server" + e.getMessage());
            }
        }).start();
        await().atMost(Duration.ofSeconds(1)).until(()-> serverStarted);
    }

}