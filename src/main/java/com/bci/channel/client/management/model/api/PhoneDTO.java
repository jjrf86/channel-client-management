package com.bci.channel.client.management.model.api;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Phone del cliente.")
public class PhoneDTO implements Serializable {
	
	private static final long serialVersionUID = 8881563676312399568L;

	@NotBlank(message = "El campo Number no puede ser vacio")
	@Schema(description = "Numero del Phone del cliente.",
			example = "989187601", type = "String", required = true)
    private String number;

    @NotBlank(message = "El campo cityCode no puede ser vacio")
	@Schema(description = "Codigo de ciudad del Phone del cliente.",
		example = "1", type = "String", required = true)
    private String cityCode;

    @NotBlank(message = "El campo countryCode no puede ser vacio")
	@Schema(description = "Codigo de pais del Phone del cliente.",
		example = "+51", type = "String", required = true)
    private String countryCode;
}