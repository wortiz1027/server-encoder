package org.workshop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workshop.server.Server;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.debug("Starting server...");
        Server server = new Server();
        server.start();
    }

}