package com.bci.channel.client.management.model.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Informaci&oacute;n del cliente.")
public class ClientRequest implements Serializable {
	
	private static final long serialVersionUID = 1806416142437748780L;

	@NotBlank(message = "El campo Name no puede ser vacio")
	@Schema(description = "Nombres completos del cliente.",
		example = "Jesse James", type = "String", required = true)
	@Size(min = 2, max = 200)
    private String name;
    
    @Email(message = "Email debe ser valido")
    @NotBlank(message = "El campo Email no puede ser vacio")
    @Schema(description = "Email del cliente.",
		example = "jjrf86@gmail.com", type = "String", required = true)
    private String email;
    
    @NotBlank(message = "El campo Password no puede ser vacio")
    @Pattern(regexp = "^(?=.*?\\d.*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$",
            message = "El campo Password debe tener al menos una letra mayuscula, una letra minuscula y dos numeros. " +
                    "Tambien el tamaño debe ser entre 8 y 16 caracteres")
    @Schema(description = "Password del cliente.",
		example = "190949Jjrf1", type = "String", required = true)
    @Size(min = 8, max = 16)
    private String password;
    
    @NotEmpty(message = "La lista del campo Phones debe tener al menos un elemento")
    @Valid
    @Schema(description = "Lista de phones del cliente.",
		type = "List", required = true, allOf = PhoneDTO.class)
    private List<PhoneDTO> phones;
    
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
