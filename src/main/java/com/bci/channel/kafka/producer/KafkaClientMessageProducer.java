package com.bci.channel.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaClientMessageProducer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value(value = "${message.topic.name}")
	private String topicName;	
	
	public void sendMessage(String message) {
		log.info("KafkaMessageProducer - sendMessage");
		
		ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
			
			@Override
			public void onSuccess(SendResult<String, String> result) {
				log.info("Mensaje enviado=[" + message + "] con offset=[" + result.getRecordMetadata().offset() + "]");
			}
			
			@Override
			public void onFailure(Throwable ex) {
				log.error("Envio de mensaje fallido=[" + message + "] debido a: " + ex.getMessage());
			}
		});
	}
}