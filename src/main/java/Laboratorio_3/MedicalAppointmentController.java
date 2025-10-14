package Laboratorio_3;
import java.util.ArrayList;

public class MedicalAppointmentController {
	private ArrayList<Appointment> citas;
	private ArrayList<ReschedulingHistory> historialReagendados;
	
	MedicalAppointmentController() {
		this.citas = new ArrayList<>();
		this.historialReagendados = new ArrayList<>();
		
	}
	
	public Appointment buscarCitaPorId(String id) {
		
		for (Appointment cita : citas) {
			
			if (cita.getId().equals(id))
				
			{
				return cita;
			}
		}
		return null;
	}
	
	public String cambiarEstadoCita(String id, EstadoActual nuevoEstado) {
		//Buscar cita
		Appointment cita = buscarCitaPorId(id);
		
		//Verificar si existe la cita
		if (cita == null)
		{
			return "Cita no encontrada";
		}
		
		//Recuperar estado anterior
		EstadoActual estadoActual = cita.getEstadoActual();
		
		//Si existe, settear estado
		cita.setEstadoActual(nuevoEstado);
		
		//Si es reagendada agregar a la lista del historial
		
		return String.format("Cita con id: %s --> estado %s actualizado a %s", id, estadoActual, nuevoEstado);
		
	}
	
	public ArrayList<Appointment> listarCitasPorEstado(EstadoActual estado) {
		//Crear un nuevo ArrayList con las citas filtradas.
		ArrayList<Appointment> citasFiltradas = new ArrayList<>();
		
		//Recorrer citas actuales :p
		for (Appointment cita : this.citas) {
			
			EstadoActual estadoCita = cita.getEstadoActual();
		
			//Si el estado de la cita (parametro) coincide con el de la cita que se recorre en 
			//las citas existentes, se agrega a la lista de citas filtradas.
			if(estadoCita.equals(estado)) {
				citasFiltradas.add(cita);
			}
		}
		
		return citasFiltradas;
	}
	
	public ArrayList<Appointment> listarCitasPorMedico(GeneralMedic medico) {
		//Crear un nuevo ArrayList con las citas filtradas.
		ArrayList<Appointment> citasFiltradas = new ArrayList<>();
		
		//Recorrer citas actuales :p
		for (Appointment cita : this.citas) {
			
			GeneralMedic medicoCita = cita.getMedico();
		
			//Si el medico de la cita (parametro) coincide con el de la cita que se recorre en 
			//las citas existentes, se agrega a la lista de citas filtradas.
			
			if(medicoCita.equals(medico)) {
				citasFiltradas.add(cita);
			}
		}
		
		return citasFiltradas;
	}
	
	public String reagendarCita(String id, String nuevaFecha) {
		Appointment cita = this.buscarCitaPorId(id);
		if (cita == null) {
			return "Cita no encontrada";
		}
		
		//Guardar fecha de la cita previo a cambio.
		String fechaAnterior = cita.getFecha();
		
		//Verificar la disponibilidad del médico de esa cita con la nueva fecha
		GeneralMedic medicoDeLaCita = cita.getMedico();
		
		if (!this.verificarDisponibilidadMedico(medicoDeLaCita, nuevaFecha)) {
			return "Médico no disponible";
		}
		
		//Si es válido, agregar nueva fecha, cambiar estado a REAGENDADA y guardar en el historial de reagendados.
		cita.setFecha(nuevaFecha);
		cita.setEstadoActual(EstadoActual.REAGENDADA);
		
		//Crear objeto para el historial
		ReschedulingHistory reagendada = new ReschedulingHistory(id, fechaAnterior, nuevaFecha);
		
		//Guardar en el historial de reagendados.
		this.historialReagendados.add(reagendada);
		
		return "Cita exitosamente reagendada " + reagendada.toString();
	}
	
	//Implementa métodos auxiliares para mejorar la legibilidad, retorna si un medico es apto para apuntarlo a la cita.
	public boolean verificarDisponibilidadMedico(GeneralMedic medico, String fecha) {
		for (Appointment cita : this.citas)
		{
			GeneralMedic medicoAEvaluar = cita.getMedico();
			boolean fechaAEvaluar = evaluarFecha(cita, fecha);
			
			if ( medicoAEvaluar.equals(medico) && fechaAEvaluar && evaluarEstadoActualCancelado(cita) ) {
				
				return false;
			}
		}
		
		return true;
	}
	
	//Métodos auxiliares para verificarDisponibilidadMedico()
	
	///Recibe una cita para evaluar si coinciden o no
	public boolean evaluarFecha(Appointment cita, String fecha) {
		String fechaCita = cita.getFecha();

		return fechaCita.equals(fecha);
	}
	
	public boolean evaluarEstadoActualCancelado(Appointment cita) {
		EstadoActual estadoCita = cita.getEstadoActual();

		return !estadoCita.equals(EstadoActual.CANCELADA);
	}
	
	
	///Agrega una cita, se implementan las siguientes validaciones:
	///- que no exista una cita con el mismo ID 
	///- verificar la disponibilidad del médico.
	///- validar que los datos ingresados sean realistas.
	///
	public String agregarCita(Appointment cita) {
		
		//Validar que la cita exista
		if (cita == null) {
			return "Cita imposible de agregar";
		}
		
		String idCita = cita.getId();
		Appointment citasExistentes = this.buscarCitaPorId(idCita);
		
		if (!(citasExistentes == null)) {
			return String.format("Cita con id {%s} ya existente", idCita);
		}
		
		GeneralMedic doctorCita = cita.getMedico();
		String fechaCita = cita.getFecha();
		
		if (!(this.verificarDisponibilidadMedico(doctorCita, fechaCita))) {
			return "Médico no disponible para la fecha estipulada";
		}
		
		if (cita.getPaciente() == null || cita.getPaciente().equals("")) {
			return "Agregar un paciente válido";
		}
		
		if (fechaCita == null || fechaCita.isEmpty()) {
			return "Fecha vacía o no válida";
		}
		
		if (cita.getDuracion() <= 0 || cita.getDuracion() > 24) {
			
			return "Duración de la cita inválida";
		}
		
		
		citas.add(cita);
		return cita.toString() + " - Agregada con éxito";
	}

	public ArrayList<Appointment> getCitas() {
		
		return citas;
	}

	public ArrayList<ReschedulingHistory> obtenerHistorialReagendados() {
		return historialReagendados;
	}
	
}
