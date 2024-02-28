package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component

public class ReservaScheduler {
    private final ReservaRepository reservaRepository;
    private final ReservaService reservaService;

    public ReservaScheduler(ReservaRepository reservaRepository, ReservaService reservaService) {
        this.reservaRepository = reservaRepository;
        this.reservaService = reservaService;
    }

    // Esto se ejecuta solo cada 24 horas (86400000 ms)
    @Scheduled(fixedRate = 86400000)
    public void verificarReservasFinalizadas() {
        List<Reserva> reservasFinalizadas = reservaRepository.findByFechaFinBefore(LocalDate.now());
        for (Reserva reserva : reservasFinalizadas) {
            reservaService.marcarAutoComoDisponible(reserva);
        }
    }
}
