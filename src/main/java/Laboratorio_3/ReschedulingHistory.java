package Laboratorio_3;

public class ReschedulingHistory {
	
	private String idCita;
	private String fechaAnterior;
	private String fechaNueva;
	
	ReschedulingHistory(String idCita, String fechaAnterior, String fechaNueva) {
		this.idCita = idCita;
		this.fechaAnterior = fechaAnterior;
		this.fechaNueva = fechaNueva;
	}
	
	public String getIdCita() {
		return this.idCita;
	}

	public String getFechaAnterior() {
		return this.fechaAnterior;
	}
	
	public String getFechaNueva() {
		return this.fechaNueva;
	}
	
	public String toString() {
		return "Cita:" +
				"|id: {" + this.getIdCita() + "}" +
				"|fecha anterior: {" + this.getFechaAnterior() + "}" +
				"|fecha nueva: {" + this.getFechaNueva() + "}";
	}
}
