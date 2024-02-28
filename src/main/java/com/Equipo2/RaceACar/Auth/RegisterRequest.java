package com.Equipo2.RaceACar.Auth;

import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String email;

    String nombreCompleto;
    String  telefono;
    String documento;

    @JsonProperty()
    public String getPassword(){
        return password;
    }

}
