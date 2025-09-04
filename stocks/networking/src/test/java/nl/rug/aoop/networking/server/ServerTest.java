package nl.rug.aoop.networking.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ServerTest {

    @Test
    public void testRunWithSingleConnection() throws IOException {
        Server server = new Server(0,null);
        new Thread(server).start();
        await().atMost(1, TimeUnit.SECONDS).until(server::isRunning);
        try(Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", server.getPort()));
            await().atMost(1,TimeUnit.SECONDS).until(()->server.getNumClients()==1);
            assertEquals(1, server.getNumClients());
        }
    }
}