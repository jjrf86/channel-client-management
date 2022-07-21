package com.bci.channel.client.management.business.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.bci.channel.client.management.business.ClientManagementService;
import com.bci.channel.client.management.dao.ClientRepository;
import com.bci.channel.client.management.dao.PhoneRepository;
import com.bci.channel.client.management.entity.Client;
import com.bci.channel.client.management.entity.Phone;
import com.bci.channel.client.management.model.api.ClientRequest;
import com.bci.channel.client.management.model.api.PhoneDTO;
import com.bci.channel.client.management.util.JWTUtil;
import com.bci.channel.exception.ApiException;
import com.bci.channel.kafka.producer.KafkaClientMessageProducer;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.adapter.rxjava.RxJava2Adapter;

/**
 * Clase del servicio que contiene los metodos necesarios para la gestion de
 * data y logica de negocio que consumira la clase REST
 * ClientManagementController.</br>
 * <b>Class</b>: ClientManagementServiceImpl</br>
 * 
 * @author Jesse James Rios Franco </br>
 *         <u>Desarrollado por</u>: </br>
 *         <ul>
 *         <li>Jesse James Rios Franco
 *         <li>
 *         </ul>
 * @version 1.0
 */

@Slf4j
@Service
@AllArgsConstructor
public class ClientManagementServiceImpl implements ClientManagementService {
	
	private ClientRepository clientRepository;
	
	private PhoneRepository phoneRepository;
	
	private KafkaClientMessageProducer kafkaClientMessageProducer;
	
	/**
	 *
	 */
	@Override
	public Single<Client> createClient(ClientRequest clientRequest) {
		log.info("ClientManagementServiceImpl - createClient");
		
		return createClientIfNotExist(clientRequest)
				.doAfterSuccess(client -> {
					
					savePhones(clientRequest.getPhones(),client);
					
					kafkaClientMessageProducer.sendMessage(client.getEmail());
				})
				.subscribeOn(Schedulers.io());
	}
	
	/**
	 * @param clientRequest
	 * @return
	 */
	private Single<Client> createClientIfNotExist(ClientRequest clientRequest){
		return RxJava2Adapter
				.monoToSingle(clientRepository.findByEmail(clientRequest.getEmail())
						.doOnNext(obj -> {
							throw new ApiException("TL0001",String.format("El email %s ya esta registrado", 
									clientRequest.getEmail()));
							}).switchIfEmpty(clientRepository.save(buildClient(clientRequest))));
	}
	
	/**
	 * @param clientRequest
	 * @return
	 */
	private Client buildClient(ClientRequest clientRequest) {
		return Client.builder()
				.name(clientRequest.getName())
				.active(true)
				.created(LocalDate.now())
				.lastLogin(LocalDate.now())
				.email(clientRequest.getEmail())
				.password(clientRequest.getPassword())
				.token(JWTUtil.getJWTToken(clientRequest))
				.build();
	}

	/**
	 * @param phones
	 * @param client
	 */
	private void savePhones(List<PhoneDTO> phones, Client client) {
		
		List<Phone> phonesEntity = phones.stream()
				.map(phoneDto -> Phone.builder()
						.clientId(client.getId())
						.number(phoneDto.getNumber())
						.cityCode(phoneDto.getCityCode())
						.countryCode(phoneDto.getCountryCode())
						.build())
				.collect(Collectors.toList());
		
		if(!phonesEntity.isEmpty()) {
			phoneRepository.saveAll(phonesEntity);
		}
	}
}