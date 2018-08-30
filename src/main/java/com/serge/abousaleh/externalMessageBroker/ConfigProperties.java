package com.serge.abousaleh.externalMessageBroker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigProperties {
	
	@Value("${queue.name.random:not set}")
	private String queueName;

	public String getQueueName() {
		return queueName;
	}
}
