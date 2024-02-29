package com.Equipo2.RaceACar.Auth;

import com.Equipo2.RaceACar.User.Roles;
import com.Equipo2.RaceACar.model.RolUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")

public class RegisterRequest {
    //String username;
    String password;
    String email;

    String nombre;
    String apellido;
    String  telefono;
    String documento;

    @JsonProperty()
    public String getPassword(){
        return password;
    }

}
