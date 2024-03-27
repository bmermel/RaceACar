package com.Equipo2.RaceACar.service;

import com.Equipo2.RaceACar.DTO.ValoracionDTO;
import com.Equipo2.RaceACar.model.Auto;
import com.Equipo2.RaceACar.model.Reserva;
import com.Equipo2.RaceACar.model.Valoracion;
import com.Equipo2.RaceACar.repository.AutoRepository;
import com.Equipo2.RaceACar.repository.ReservaRepository;
import com.Equipo2.RaceACar.repository.ValoracionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ValoracionService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ValoracionRepository valoracionRepository;


    public ValoracionDTO crearValoracion(Long reservaId, Integer valoracion, String resena) {
        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new NoSuchElementException("No se encontró la reserva con ID: " + reservaId));

        Valoracion nuevaValoracion = new Valoracion();
        nuevaValoracion.setReserva(reserva);
        nuevaValoracion.setValoracion(valoracion);
        nuevaValoracion.setResena(resena);
        nuevaValoracion.setFechaResena(LocalDate.now());

        Valoracion valoracionGuardada = valoracionRepository.save(nuevaValoracion);
        return modelMapper.map(valoracionGuardada, ValoracionDTO.class);
    }
/* metodo no utilizado ver si borrar
*     public List<ValoracionDTO> obtenerTodasLasValoraciones() {
        List<Valoracion> valoraciones = valoracionRepository.findAll();

        if (valoraciones.isEmpty()) {
            return Collections.emptyList();
        }

        return valoraciones.stream()
                .map(valoracion -> modelMapper.map(valoracion, ValoracionDTO.class))
                .collect(Collectors.toList());
    }
* */

    public List<ValoracionDTO> obtenerValoracionesPorIdAuto(Long idAuto) {
        List<Valoracion> valoraciones = valoracionRepository.findByReservaAutoId(idAuto);

        if (valoraciones.isEmpty()) {
            return Collections.emptyList();
        }

        return valoraciones.stream()
                .map(valoracion -> modelMapper.map(valoracion, ValoracionDTO.class))
                .collect(Collectors.toList());
    }


    public Double obtenerPromedioValoracionPorAutoId(Long autoId) {
        Double promedio = valoracionRepository.obtenerPromedioValoracionPorAutoId(autoId);
        return redondearADosDecimales(promedio);
    }

    public Double redondearADosDecimales(Double valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(valor));
    }
}
