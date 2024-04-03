package com.Equipo2.RaceACar.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
    public class CrearReservaDTO {
        private Long autoId;
        private LocalDate fechaComienzo;
        private LocalDate fechaFin;
        private String formaDePago;
        private String email;
        private String recogida;
        private String entrega;
}
