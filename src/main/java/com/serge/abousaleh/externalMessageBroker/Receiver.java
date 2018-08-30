package com.serge.abousaleh.externalMessageBroker;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class Receiver implements ExceptionListener{
	
	private static final Logger logger = LogManager.getLogger(Receiver.class);
	
	 public void receiveMessageTask(String queueName) {
		logger.debug("receiveMessageTask ----- Start");
		logger.debug("queueName = " + queueName);
	        Runnable recTask = () -> {
	            try {
	                ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
	                Connection connection = connectionFactory.createConnection();
	                connection.start();
	                connection.setExceptionListener(this);
	                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
	                Destination destination = session.createQueue(queueName);
	                MessageConsumer consumer = session.createConsumer(destination);
	                Message message = consumer.receive(4000);
	                if (message instanceof TextMessage) {
	                    TextMessage textMessage = (TextMessage) message;
	                    String text = textMessage.getText();
	                   logger.info("Received TextMessage object: " + text);
	                } else {
	                    logger.info("Received other object type with message: " + message);
	                }
	                consumer.close();
	                session.close();
	                connection.close();
	            } catch (JMSException e) {
	                logger.error("receiveMessageTask method error", e);
	            }
	        };
	        new Thread(recTask).start();
	        logger.debug("receiveMessageTask ----- End");
	    }
	    @Override
	    public void onException(JMSException exception) {
	        logger.error(exception.getMessage(), exception);
	    }
}
