package com.bci.channel.client.management.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Informaci&oacute;n de respuesta del cliente.")
public class ClientResponse implements Serializable {

	private static final long serialVersionUID = 8144203527724092974L;
	
	@Schema(description = "Id del cliente.",
			example = "123456789", type = "String", required = true)
	private UUID id;
    
	@Schema(description = "Fecha de creacion del cliente.",
			example = "07/07/2021", type = "String", required = true)
    private LocalDate created;
    
	@Schema(description = "Fecha de la ultima actualizacion del cliente.",
			example = "08/07/2021", type = "String", required = true)
    private LocalDate modified;
    
	@Schema(description = "Fecha del ultimo ingreso del cliente.",
			example = "07/07/2021", type = "String", required = true)
    private LocalDate lastLogin;
    
	@Schema(description = "Token de acceso al API.",type = "String", required = true)
    private String token;
    
	@Schema(description = "Estado del estado del cliente en el sistema.",
			type = "String", required = true)
    private boolean active;
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}