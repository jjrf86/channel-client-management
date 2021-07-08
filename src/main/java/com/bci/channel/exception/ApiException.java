package com.bci.channel.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonAutoDetect(creatorVisibility = Visibility.NONE,
				fieldVisibility = Visibility.NONE,
				getterVisibility = Visibility.NONE,
				isGetterVisibility = Visibility.NONE,
				setterVisibility = Visibility.NONE)
@JsonInclude(Include.NON_NULL)
@SuppressWarnings("serial")
@Schema(name = "ApiException", description = "Datos del error del sistema.")
public class ApiException extends RuntimeException {
	
	@JsonProperty
	@Schema(description = "Codigo de error de sistema", example = "TL0001")
	private final String code;
	
	@JsonProperty
	@Schema(description = "Descripcion de error de sistema", example = "Error al llamar al servicio")
	private final String description;
	
	@JsonCreator
	public ApiException(final String code,
			final String description) {
		
		super(description);
		
		this.code = code;
		this.description = description;
	}
}