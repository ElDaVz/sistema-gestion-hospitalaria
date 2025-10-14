package Laboratorio_3;

///La clase appointment se refiere a las citas
///esta clase está encargada de definir los atributos y métodos de una cita.
///
public class Appointment {
	private String id;
	private String paciente;
	private GeneralMedic medico;
	private String fecha;
	private String tipo;
	private int duracion;
	private EstadoActual estadoActual;
	
	public Appointment(String id, String paciente, GeneralMedic medico, String fecha, String tipo, int duracion) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.medico = medico;
		this.fecha = fecha;
		this.tipo = tipo;
		this.duracion = duracion;
		this.estadoActual = EstadoActual.PROGRAMADA;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public GeneralMedic getMedico() {
		return medico;
	}

	public void setMedico(GeneralMedic medico) {
		this.medico = medico;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public EstadoActual getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(EstadoActual estadoActual) {
		this.estadoActual = estadoActual;
	}

	@Override
	public String toString() {
		return "Cita [id=" + this.id + ", paciente=" + this.paciente + ", medico=" + this.medico + ", fecha=" + this.fecha
				+ ", tipo=" + this.tipo + ", duracion=" + this.duracion + ", estadoActual=" + this.estadoActual + "]";
	}
	

	
	
}
