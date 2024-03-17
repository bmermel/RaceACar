package com.Equipo2.RaceACar.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ValoracionDTO {
    private Integer id;
    private Integer valoracion;
    private String resena;
    private LocalDate fechaResena;
    private String nombreUsuario;
}
