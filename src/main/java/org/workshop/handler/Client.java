package org.workshop.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workshop.factory.EncoderFactory;
import org.workshop.starter.objects.Message;
import org.workshop.starter.objects.Response;
import org.workshop.service.IServiceEncoder;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Optional;

public class Client extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(Client.class);

    private int counter;

    private Socket socket;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public Client(Socket socket, int counter) {
        this.socket = socket;
        this.counter = counter;
    }

    public void run() {
        try {
            this.output = new ObjectOutputStream(this.socket.getOutputStream());
            this.input  = new ObjectInputStream(this.socket.getInputStream());

            Object message;

            do {
                logger.info("======================================");
                logger.info("CLIENT NUMBER : {} ", counter);
                logger.info("HOSTNAME      : {} ", this.socket.getInetAddress().getHostName());
                logger.info("HOST ADDRESS  : {} ", this.socket.getInetAddress().getHostAddress());
                logger.info("PORT LOCAL    : {} ", this.socket.getLocalPort());
                logger.info("PORT          : {} ", this.socket.getPort());
                logger.info("======================================");

                message = this.input.readObject();
                logger.info("OBJECT: {}", message);

                if (message instanceof Message) {
                    Message msg = Message.builder()
                            .password(((Message) message).getPassword())
                            .algorithm(((Message) message).getAlgorithm())
                            .build();

                    Optional<IServiceEncoder> encoder = EncoderFactory.getInstance(msg);

                    Response response = Response.builder().password(encoder.get().encode(msg.getPassword())).build();;
                    logger.info("RESPONSE: {}", response);
                    this.output.writeObject(response);
                }

            } while (!(message instanceof String) && !message.equals("exit"));

            if (message instanceof String && message.equals("exit")) {
                logger.info(message.toString());
                this.output.writeObject("You have been disconected! - " + LocalDateTime.now());
                close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        this.socket.close();
        this.output.close();
        this.input.close();
    }

}