package com.Equipo2.RaceACar.DTO;

import com.Equipo2.RaceACar.model.Auto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class ItemsDTO {

    private Long id;
    private String nombre;
    //    private List<Auto> autos = new ArrayList<>();

}
