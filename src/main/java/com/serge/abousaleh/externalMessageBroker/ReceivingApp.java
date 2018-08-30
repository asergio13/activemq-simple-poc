package com.serge.abousaleh.externalMessageBroker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  Independent Application receiving a message from a queue located on the ApacheMQ server running on localhost
 */
public class ReceivingApp {

	private static final Logger logger = LogManager.getLogger(ReceivingApp.class);
	
	public static void main(String[] args) {
		logger.debug("================== ReceivingApp ---- Start");
		
		// get the Spring Context by scanning the package for bean Classes
		// try with resources to close the resource after processing
		try(ConfigurableApplicationContext  ctx 
				= new AnnotationConfigApplicationContext("com.serge.abousaleh.externalMessageBroker")) {
		
			// Get a Bean instance from the Spring container
			ConfigProperties configProperties = ctx.getBean(ConfigProperties.class);
			logger.debug("Queue Name: " + configProperties.getQueueName());
			Receiver receiver = ctx.getBean(Receiver.class);
			
			// send a message
			receiver.receiveMessageTask(configProperties.getQueueName());
		}
		logger.debug("================== ReceivingApp ---- End");
	}

}
