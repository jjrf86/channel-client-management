package com.bci.channel.client.management.expose.web;

import javax.validation.Valid;

import com.bci.channel.client.management.business.ClientManagementService;
import com.bci.channel.client.management.entity.Client;
import com.bci.channel.client.management.model.api.ClientRequest;
import com.bci.channel.client.management.model.api.ClientResponse;
import com.bci.channel.exception.ApiException;

import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador principal que expone el servicio a trav&eacute;s de HTTP/Rest para
 * las operaciones del recurso.</br>
 * <b>Class</b>: ClientManagementController</br>
 * @author Jesse James Rios Franco </br>
 * <u>Desarrollado por</u>: </br>
 * <ul>
 * <li>Jesse James Rios Franco<li>
 * </ul>
 * @version 1.0
 */

@Slf4j
@Validated
@AllArgsConstructor
@Tag(name = "Client Management", description = "Client Management Controller")
@RestController
@RequestMapping("/channel/v1/client-management")
public class ClientManagementController {
	
	private ClientManagementService clientManagementService;
	
	@Validated
	@ResponseStatus(code = HttpStatus.CREATED)
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Se creó el recurso correctamente."),
		@ApiResponse(responseCode = "400", description = "Los datos proporcionados por el cliente no son validos.",
				content = @Content(schema = @Schema(implementation = ApiException.class))),
		@ApiResponse(responseCode = "404", description = "El cliente no esta registrado en la base de datos.",
				content = @Content(schema = @Schema(implementation = ApiException.class))),
		@ApiResponse(responseCode = "500", description = "Error al obtener el recurso.",
				content = @Content(schema = @Schema(implementation = ApiException.class)))
	})
	@Operation(
			tags = "Registra un cliente",
			summary = "Registra un cliente",
			method = "POST", description = "classpath:/swagger/notes/save-client.md")
	@PostMapping(path = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
	public Single<ClientResponse> createClient(@Valid @RequestBody ClientRequest request) {
		log.info("Inicio createClient");
		
		return clientManagementService.createClient(request)
				.map(this::parse);
	}
	
	private ClientResponse parse(Client client) {
		return ClientResponse.builder()
				.id(client.getId())
				.created(client.getCreated())
				.modified(client.getModified())
				.lastLogin(client.getLastLogin())
				.token(client.getToken())
				.active(client.isActive())
				.build();
	}
}