package com.serge.abousaleh.externalMessageBroker;

import javax.jms.*;
import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Sender {
	
	private static final Logger logger = LogManager.getLogger(Sender.class);
	
    private SecureRandom random = new SecureRandom();
    
    public void sendMessageTask(String queueName){
    	logger.debug("sendMessageTask ----- Start");
    	logger.debug("queueName = " + queueName);
        String taskName = generateTaskName();
        Runnable sendTask = () -> {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection connection = null;
            try {
                connection = connectionFactory.createConnection();
                connection.start();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue(queueName);
                MessageProducer producer = session.createProducer(destination);
                producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
                String text = "Hello from: " + taskName + " : " + this.hashCode();
                TextMessage message = session.createTextMessage(text);
                logger.info("Sent message hash code: "+ message.hashCode() + " : " + taskName);
                producer.send(message);
                session.close();
                connection.close();
            } catch (JMSException e) {
                logger.error("Sender createTask method error", e);
            }
        };
        new Thread(sendTask).start();
        logger.debug("sendMessageTask ----- End");
    }
    private String generateTaskName() {
        return new BigInteger(20, random).toString(16);
    }
	
}
