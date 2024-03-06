package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.RolUsuario;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsuarioSinPassDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String email;

    private String telefono;

    private String documento;

    private RolUsuario rolUsuario;

}
