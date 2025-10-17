package utiles;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import entidades.Espectaculo;

public class Objetos {

	protected static Espectaculo espectaculo1 = new Espectaculo(1L, "Espectaculo 1",
			LocalDate.of(2025, 1, 1), LocalDate.of(2025, 1, 30));
	protected static Espectaculo espectaculo2 = new Espectaculo(2L, "Espectaculo 2",
			LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 28));
	protected static Espectaculo espectaculo3 = new Espectaculo(3L, "Espectaculo 3",
			LocalDate.of(2025, 3, 1), LocalDate.of(2025, 3, 30));
	
	
}