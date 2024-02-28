package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.User.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RolUsuarioDTO {
    private Roles rol;
}
