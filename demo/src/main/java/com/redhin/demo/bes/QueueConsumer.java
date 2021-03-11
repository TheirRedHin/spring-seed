/**
 * Copyright (c) 2014~2015 BES Technology & Service Co.,Ltd. All rights reserved
 */
package com.redhin.demo.bes;

import com.bes.mq.BESMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Description: The consumer keeps receiving messages from the queue until the
 * queue receive a shutdown message.
 */
public class QueueConsumer {

    private BESMQConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private String host;
    private String port;
    private String queueName;
    private String username;
    private String password;

    public QueueConsumer(String[] args) {
        host = args[0];
        port = args[1];
        username = args[2];
        password = args[3];
        queueName = args[4];
    }

    public static void main(String[] args) {
        QueueConsumer sample = new QueueConsumer(args);
        sample.execute();
    }

    public void execute() {
        try {
            factory = new BESMQConnectionFactory(username, password, "tcp://" + host + ":" + port);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            consumer = session.createConsumer(queue);

            int count = 0;
            System.out.println("Consumer is waiting to receive the message...");
            while (true) {
                TextMessage message = (TextMessage) consumer.receive(60 * 1000);
                System.out.println("Received " + ++count + " message" + (count > 1 ? "s." : "."));
                System.out.println("Received message : " + message.getText());
                if (message.getText().equals("SHUTDOWN")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (consumer != null) {
                try {
                    consumer.close();
                } catch (Exception ignore) {
                }
            }
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ignore) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ignore) {
                }
            }
        }
    }
}
