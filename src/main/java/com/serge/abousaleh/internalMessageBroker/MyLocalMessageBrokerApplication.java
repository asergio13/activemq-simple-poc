package com.serge.abousaleh.internalMessageBroker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLocalMessageBrokerApplication {

	private static final Logger logger = LogManager.getLogger(MyLocalMessageBrokerApplication.class);
	
	 public static void main(String[] args) throws Exception {
		 logger.debug("===> main() - Start");
	        thread(new MyProducer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        Thread.sleep(1000);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        Thread.sleep(1000);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyProducer(), false);
	        Thread.sleep(1000);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyConsumer(), false);
	        thread(new MyProducer(), false);
	        logger.debug("===> main() - End");
	    }
	 
	    public static void thread(Runnable runnable, boolean daemon) {
	        Thread brokerThread = new Thread(runnable);
	        brokerThread.setDaemon(daemon);
	        brokerThread.start();
	    }
	
}
