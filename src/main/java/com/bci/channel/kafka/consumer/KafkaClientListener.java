package com.bci.channel.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaClientListener {
	
	@KafkaListener(topics = "${message.topic.name}", groupId = "${message.group.name}")
	public void listenTopic1(String message) {
		log.info("Mensaje recibido del clientTopic in  listener: " + message);
	}
}