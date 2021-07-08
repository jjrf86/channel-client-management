package com.bci.channel.client.management.dao;

import java.util.UUID;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bci.channel.client.management.entity.Client;

import reactor.core.publisher.Mono;

/**
 * Inferfaz ClientRepository, abstrae la capa de acceso a datos usando el
 * patron de dise&ntilde;o dao.</br>
 * <b>Interface</b>: ClientRepository</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */

@Repository
public interface ClientRepository extends ReactiveCrudRepository<Client, UUID> {
	
	public Mono<Client> findByEmail(String email);
}