package com.bci.channel;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Jesse James Rios Franco
 *
 */
@SpringBootApplication
public class Application {
	
	@Value(value = "${message.topic.name}")
	private String topicName;
	
    public static void main( String[] args ){
    	SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public NewTopic clientTopic() {
    	return new NewTopic(topicName,1,(short)1);
    }
}