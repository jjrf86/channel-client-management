package com.bci.channel.client.management.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Phone del cliente.")
@Table("PHONES")
public class Phone implements Serializable {

	private static final long serialVersionUID = 4354864487077519420L;
	
	@Id
    private Long id;
	
	@Column("client_id")
    private UUID clientId;
    
    private String number;
    
    @Column("city_code")
    private String cityCode;
    
    @Column("country_code")
    private String countryCode;
}