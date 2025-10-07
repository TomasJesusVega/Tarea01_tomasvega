package entidades;

import java.time.LocalDate;

public class Coordinacion extends Persona {
	private Long idCoord;
	private boolean senior = false;
	private LocalDate fechaSenior = null;

	public Coordinacion() {
		super();
	}

	public Coordinacion(Long idCoord, boolean senior, LocalDate fechaSenior) {
		super();
		this.idCoord = idCoord;
		this.senior = senior;
		this.fechaSenior = fechaSenior;
	}

	public Long getIdCoord() {
		return idCoord;
	}

	public void setIdCoord(Long idCoord) {
		this.idCoord = idCoord;
	}

	public boolean isSenior() {
		return senior;
	}

	public void setSenior(boolean senior) {
		this.senior = senior;
	}

	public LocalDate getFechaSenior() {
		return fechaSenior;
	}

	public void setFechaSenior(LocalDate fechaSenior) {
		this.fechaSenior = fechaSenior;
	}

}
