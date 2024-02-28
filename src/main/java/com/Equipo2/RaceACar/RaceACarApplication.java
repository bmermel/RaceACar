package com.Equipo2.RaceACar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EntityScan(basePackages = {"com.Equipo2.RaceACar.model"})

public class RaceACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RaceACarApplication.class, args);
	}

}
