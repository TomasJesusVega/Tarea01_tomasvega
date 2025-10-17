package utiles;

import java.time.LocalDate;

import entidades.Espectaculo;

public class Objetos {
	
	protected static Espectaculo crearEspectaculo() {
	Espectaculo espectaculo1 = new Espectaculo(1L, "Espectaculo 1", LocalDate.of(2025, 1, 1),
			LocalDate.of(2025, 1, 30));
	Espectaculo espectaculo2 = new Espectaculo(2L, "Espectaculo 1", LocalDate.of(2025, 2, 1),
			LocalDate.of(2025, 2, 28));
	Espectaculo espectaculo3 = new Espectaculo(3L, "Espectaculo 1", LocalDate.of(2025, 3, 1),
			LocalDate.of(2025, 3, 30));
	return espectaculo1;
	} 
} 