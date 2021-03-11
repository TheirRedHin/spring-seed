/**
 * Copyright (c) 2014~2015 BES Technology & Service Co.,Ltd. All rights reserved
 */
package com.redhin.demo.bes;

import com.bes.mq.BESMQConnectionFactory;
import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Description: The producer sends a number of messages to the queue.
 */
public class QueueProducer {

    private BESMQConnectionFactory factory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private String host;
    private String port;
    private String queueName;
    private String username;
    private String password;

    public QueueProducer(String[] args) {
        host = args[0];
        port = args[1];
        username = args[2];
        password = args[3];
        queueName = args[4];
    }

    public static void main(String[] args) {
        QueueProducer sample = new QueueProducer(args);
        sample.execute();
    }

    public void execute() {
        try {
            factory = new BESMQConnectionFactory(username, password, "tcp://" + host + ":" + port);
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(queueName);
            producer = session.createProducer(queue);
            TextMessage message = session.createTextMessage();
            message.setText("HELLO");
            producer.send(message);
            message.setText("GOODBYE");
            producer.send(message);
            message.setText("SHUTDOWN");
            producer.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (producer != null) {
                try {
                    producer.close();
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
