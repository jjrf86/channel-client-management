package com.bci.channel.client.management.dao;

import com.bci.channel.client.management.entity.Phone;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Inferfaz PhoneRepository, abstrae la capa de acceso a datos usando el
 * patron de dise&ntilde;o dao.</br>
 * <b>Interface</b>: PhoneRepository</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */
@Repository
public interface PhoneRepository extends ReactiveCrudRepository<Phone, Long> {

}