package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioDTO {
    private String nombreCompleto;
    private String email;
    @JsonProperty
    private String password;
    private String telefono;

    private String documento;

    //private RolUsuario rol;



}