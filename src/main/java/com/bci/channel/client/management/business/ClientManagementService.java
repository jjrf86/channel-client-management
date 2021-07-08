package com.bci.channel.client.management.business;

import com.bci.channel.client.management.entity.Client;
import com.bci.channel.client.management.model.api.ClientRequest;

import io.reactivex.Single;

/**
 * Inferfaz del servicio para la logica de negocio que consumira la clase REST
 * ClientManagementController.</br>
 * <b>Interface</b>: ClientManagementService</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */
public interface ClientManagementService {
	
	public Single<Client> createClient(ClientRequest clientRequest);
}