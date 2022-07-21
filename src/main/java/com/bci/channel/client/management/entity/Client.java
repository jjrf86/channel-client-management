package com.bci.channel.client.management.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Informaci&oacute;n del cliente.")
@Table("CLIENTS")
public class Client implements Serializable {

	private static final long serialVersionUID = -6697432822487663028L;

	@Id
    @Column("id")
    private UUID id;
    
    private String name;
    
    private String email;
    
    private String password;
    
    private LocalDate created;
    
    private LocalDate modified;
    
    @Column("last_login")
    private LocalDate lastLogin;
    
    private String token;
    
    private boolean active;
    
//    @MappedCollection(idColumn = "ID",keyColumn = "CLIENT_ID")
//    private Set<Phone> phones;
}