package org.workshop.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workshop.handler.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Server.class);
    private int counter = 0;
    private final int DEFAUL_PORT = 1535;
    private static ServerSocket server;

    public void run() {
        try {
            int port = Integer.parseInt(System.getenv("ENCODER_PORT") == null ? String.valueOf(DEFAUL_PORT) : System.getenv("ENCODER_PORT"));

            this.server = new ServerSocket(port);
            logger.info("Server listening by port: {} ", this.server.getLocalPort());
            
            while (true) {
                counter++;
                Socket connection = server.accept();
                Client client = new Client(connection, counter);
                client.start();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}