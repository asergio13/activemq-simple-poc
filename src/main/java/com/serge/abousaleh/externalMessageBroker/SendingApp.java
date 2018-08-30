package com.serge.abousaleh.externalMessageBroker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Independent Application sending a message to a queue located on the ApacheMQ server running on localhost
 */

public class SendingApp {

	private static final Logger logger = LogManager.getLogger(SendingApp.class);
	
	public static void main(String[] args) throws Exception {
		logger.debug("================== SendingApp ---- Start");
		
		// get the Spring Context by scanning the package for bean Classes
		// try with resources to close the resource after processing
		try(ConfigurableApplicationContext  ctx 
				= new AnnotationConfigApplicationContext("com.serge.abousaleh.externalMessageBroker")) {
	
			ConfigProperties configProperties = ctx.getBean(ConfigProperties.class);
			logger.debug("Queue Name: " + configProperties.getQueueName());
			
			Sender sender = ctx.getBean(Sender.class);
			// send a message
			sender.sendMessageTask(configProperties.getQueueName());
		}
		logger.debug("================== SendingApp ---- End");
	}
}