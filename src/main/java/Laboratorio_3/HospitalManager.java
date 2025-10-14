package Laboratorio_3;

import java.util.ArrayList;

///Esta clase implementa absolutamente todos los métodos de el resto de nuestras clases
///su funcion es orquestar todo nuestro programa, este es el corazon :')
///Tiene el derecho de agregar, eliminar, instanciar y todo, es literalmente el admin.

public class HospitalManager {
	private ArrayList<GeneralMedic> personalMedico;
	private MedicalAppointmentController appointmentsController;
	
	HospitalManager() {
		this.personalMedico = new ArrayList<>();
		this.appointmentsController = new MedicalAppointmentController();
		
	}
	
	public String contratarPersonal(GeneralMedic medico) {
		
		if (medico == null) {
			return "Error: medico inválido";
		}
		//Buscar un medico con el mismo id
		GeneralMedic medicoBuscado = buscarPersonalPorId(medico.getId());
		
		if (!(medicoBuscado == null)) {
			return "Error, personal con el id ya existe";
		}
		
		this.personalMedico.add(medico);
		return "Medico contratado exitósamente";
		
	}
	
	public String despedirPersonal(String id) {
		
		//Buscar un medico con el mismo id
		GeneralMedic medicoBuscado = buscarPersonalPorId(id);
		
		if (medicoBuscado == null) {
			return String.format("Personal con el id {%s} no encontrado", id);
		}
		
		this.personalMedico.remove(medicoBuscado);
		return medicoBuscado.toString() + "Eliminado";
	}
	
	public ArrayList<GeneralMedic> obtenerTodoElPersonal() {
		return this.personalMedico;
	}
	
	public GeneralMedic buscarPersonalPorId(String id) {
		//Recorrer lista de personalMedico
		
		for (GeneralMedic medico : this.personalMedico) {
			String idMedico = medico.getId();
			
			if (idMedico.equals(id)) {
				return medico;
			}
		}
		
		return null;
	}
	
	///Gestion de citas (Delegación al controller)
	//Crear un appointment y delegarselo al controller
	
	public String programarCita(String id, String paciente, GeneralMedic medico, String fecha, String tipo, int duracion) {
		
		//Crear cita
		Appointment nuevaCita = new Appointment(id, paciente, medico, fecha, tipo, duracion);
		
		return this.appointmentsController.agregarCita(nuevaCita);
	}
	
	public String reagendarCitaHospital(String id, String nuevaFecha) {
		return this.appointmentsController.reagendarCita(id, nuevaFecha);
	}
	
	public String cancelarCita(String id) {
		return this.appointmentsController.cambiarEstadoCita(id, EstadoActual.CANCELADA);
	}
	
	///Reportes
	//Mostrar info de todo el personal
	public String generarReportePersonal() {
		if (this.personalMedico.isEmpty()) {
			return "Lista vacía";
		}
		
		StringBuilder sb = new StringBuilder();
		
		//Agregar encabezado
		sb.append("=== REPORTE DE PERSONAL MÉDICO ===" + "\n");
		
		for (GeneralMedic medico : this.personalMedico) {
			sb.append(medico.toString() + "\n");
		}
		
		return sb.toString();
	}
	
	//Mostrar reporte citas
	public String generarReporteCitas() {
		ArrayList<Appointment> citas = appointmentsController.getCitas();
		
		if (citas.isEmpty()) {
			return "Lista vacía";
		}
		
		StringBuilder sb = new StringBuilder();
		
		//Agregar encabezado
		sb.append("=== REPORTE DE CITAS ===" + "\n");
		
		for (Appointment cita : citas) {
			sb.append(cita.toString() + "\n");
		}
		
		return sb.toString();
	}
	
	//Mostrar reporte del historial
	public String obtenerHistorialCompleto() {
		ArrayList<ReschedulingHistory> historialReagendados = appointmentsController.obtenerHistorialReagendados();
		
		if (historialReagendados.isEmpty()) {
			return "Historial vacío";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append( "=== HISTORIAL DE REAGENDAMIENTOS ===" + "\n");
		
		for (ReschedulingHistory reagendado : historialReagendados) {
			sb.append(reagendado.toString() + "\n");
		}
		
		return sb.toString();
	}
	
	///Cálculos y nomina
	// Calcular el sueldo de cada empleado
	
	public double calcularNominaTotal() {
		
		double total = 0.0;
		
		if (this.personalMedico.isEmpty()) {
			return total;
		}
		
		for (GeneralMedic medico : this.personalMedico) {
			double salarioMedico = medico.calcularSalario();
			total += salarioMedico;
		}
		
		return total;
	}
	
	//Calcular sueldo por departamento
	public double calcularNominaPorDepartamento(String departamento) {
		double total = 0.0;
		
		if (this.personalMedico.isEmpty()) {
			return total;
		}
		
		if (departamento == null || departamento.equals("")) {
			return total;
		}
		
		for (GeneralMedic medico : this.personalMedico) {
			String departamentoDelMedico = medico.getDepartamento();
			
			if (departamentoDelMedico.equals(departamento)) {
				double salarioMedico = medico.calcularSalario();
				total += salarioMedico;
			}
		}
		
		return total;
	}
	
	public String generarReporteNomina() {
		if (this.personalMedico.isEmpty()) {
			return "No hay personal medico registrado";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append( "=== REPORTE DE NÓMINA ===" + "\n");
		
		for (GeneralMedic medico : this.personalMedico) {
			String idMedico = medico.getId();
			String nombreMedico = medico.getNombre();
			String departamentoMedico = medico.getDepartamento();
			double salarioMedico = medico.calcularSalario();
			
			String formato = String.format("%s %S %s $%.2f", idMedico, nombreMedico, departamentoMedico, salarioMedico);
			sb.append(formato + "\n");
			
			}
		
		//Ya me aburrí de comentar loco :c
		
		double total = this.calcularNominaTotal();
		String formatoTotalSueldo = String.format("TOTAL: $%.2f", total);
		
		sb.append("\n" + formatoTotalSueldo);
		
		return sb.toString();
	}
	
	public void actualizarEstadisticasPersonal() {
		ArrayList<Appointment> citasController = appointmentsController.getCitas();
		
		for (Appointment cita : citasController) {
			EstadoActual estadoCita = cita.getEstadoActual();
			
			//Verificar si su estado es COMPLETADA
			if (estadoCita.equals(EstadoActual.COMPLETADA)) {
				
				GeneralMedic medicoCita = cita.getMedico();
				int duracionCita = cita.getDuracion();
				
				if (medicoCita instanceof GeneralDoctor) {
					((GeneralDoctor) medicoCita).setConsultasRealizadas();
				}
				
				if (medicoCita instanceof Surgeon) {
					((Surgeon) medicoCita).setHorasCirugiaRealizadas(duracionCita);
				}
				
				if (medicoCita instanceof Therapist) {
					((Therapist) medicoCita).setProcedimientosRealizados();
				}
			}
		}
	}
	
	
	//Filtrar por especialidad y fecha disponible.
	public ArrayList<GeneralMedic> encontrarPersonalDisponible(String especialidad, String fecha) {
		
		if (especialidad == null || especialidad.equals("")) {
			return  null;
		}
		
		ArrayList<GeneralMedic> medicosDisponibles = new ArrayList<>();
		
		if (this.personalMedico.isEmpty()) {
			return medicosDisponibles;
		}
		
		for (GeneralMedic medico : this.personalMedico) {
			
			if (especialidadValidaParaMedico(medico, especialidad) && this.appointmentsController.verificarDisponibilidadMedico(medico, fecha)) {
				medicosDisponibles.add(medico);
			}
			
		}
		
		return medicosDisponibles;
		
	}
	
	//Función auxiliar privada para usarla localmente
	private boolean especialidadValidaParaMedico(GeneralMedic medico, String especialidad) {
	    return (especialidad.equals("Doctor") && medico instanceof GeneralDoctor) ||
	           (especialidad.equals("Cirujano") && medico instanceof Surgeon) ||
	           (especialidad.equals("Enfermera") && medico instanceof Nurse) ||
	           (especialidad.equals("Terapeuta") && medico instanceof Therapist);
	}
	
	
	public String asignarMedicoCita(String idCita, String idMedico) {
		Appointment citaBuscada = appointmentsController.buscarCitaPorId(idCita);
		
		if (citaBuscada == null) {
			return "Cita no encontrada";
		}
		
		GeneralMedic medicoBuscado = this.buscarPersonalPorId(idMedico);
		
		if (medicoBuscado == null) {
			return "Medico no encontrado";
		}
		
		boolean disponibilidadMedico = appointmentsController.verificarDisponibilidadMedico(medicoBuscado, citaBuscada.getFecha());
		
		if (!(disponibilidadMedico)) {
			return "Medico no disponible en la fecha estipulada";
		}
		
		//Agregar medico a la cita
		citaBuscada.setMedico(medicoBuscado);
		return String.format("Medico con id {%s} agregado a la cita con id {%s} exitósamente", idMedico, idCita);
	}
	
	public ArrayList<String> detectarConflictosDeHorario() {
		//Crear un nuevo array para guardar todos los conflictos
		ArrayList<String> conflictos = new ArrayList<>();
		//Obtener citas del controlador
		ArrayList<Appointment> citas = appointmentsController.getCitas();
		
		for (int i = 0; i < citas.size(); i++) {
			
			for (int j = i + 1; j < citas.size(); j++) {
				
				Appointment primerCita = citas.get(i);
				Appointment segundaCita = citas.get(j);
				
				//Medicos de las citas
				GeneralMedic medicoPrimerCita = primerCita.getMedico();
				GeneralMedic medicoSegundaCita = segundaCita.getMedico();
				
				//Fechas de las citas
				String fechaPrimerCita = primerCita.getFecha();
				String fechaSegundaCita = segundaCita.getFecha();
				
				//EstadoActual de citas
				EstadoActual estadoPrimerCita = primerCita.getEstadoActual();
				EstadoActual estadoSegundaCita = segundaCita.getEstadoActual();
				EstadoActual estadoCancelado = EstadoActual.CANCELADA;
				
				if (  (medicoPrimerCita.equals(medicoSegundaCita) && fechaPrimerCita.equals(fechaSegundaCita) && 
						!(estadoPrimerCita.equals(estadoCancelado)) && !(estadoSegundaCita.equals(estadoCancelado) ) ) ) {
					String formato = String.format("Conflicto: Dr %S tiene más de una cita el %s", medicoPrimerCita.getNombre(), fechaPrimerCita);
					conflictos.add(formato);
				}
			}
			
		}
		
		return conflictos;
		
	}
	
	public String resolverConflictosAutomaticamente() {
		
		int conflictosResueltos = 0;
		
		ArrayList<String> conflictosDeHorario = detectarConflictosDeHorario();
		
		if (conflictosDeHorario.isEmpty()) {
			return "No hay conflictos por resolver";
		}
		
		ArrayList<Appointment> citas = appointmentsController.getCitas();
		
			for (int i = 0; i < citas.size(); i++) {
						
				for (int j = i + 1; j < citas.size(); j++) {
					
					Appointment primerCita = citas.get(i);
					Appointment segundaCita = citas.get(j);
					
					//Medicos de las citas
					GeneralMedic medicoPrimerCita = primerCita.getMedico();
					GeneralMedic medicoSegundaCita = segundaCita.getMedico();
					
					//Fechas de las citas
					String fechaPrimerCita = primerCita.getFecha();
					String fechaSegundaCita = segundaCita.getFecha();
					
					//EstadoActual de citas
					EstadoActual estadoPrimerCita = primerCita.getEstadoActual();
					EstadoActual estadoSegundaCita = segundaCita.getEstadoActual();
					EstadoActual estadoCancelado = EstadoActual.CANCELADA;
					
					if (  (medicoPrimerCita.equals(medicoSegundaCita) && fechaPrimerCita.equals(fechaSegundaCita) && 
							!(estadoPrimerCita.equals(estadoCancelado)) && !(estadoSegundaCita.equals(estadoCancelado) ) ) ) {
						
						String nuevaFecha = fechaSegundaCita + " (REAGENDADA)";
						
						String id = segundaCita.getId();
						
						//Reagendar nueva cita con el controlador
						appointmentsController.reagendarCita(id, nuevaFecha);
						conflictosResueltos++;
						
					}
					
				}
			}
		return "Se resolvieron {" + String.valueOf(conflictosResueltos) + "} conflictos automáticamente";
	}
	
	///Reportes estadísticos avanzados
	
	public String generarAnalisisUtilizacion() {
		
		int programadas = 0;
		int confirmadas = 0;
		int en_progreso = 0;
		int completadas = 0;
		int canceladas = 0;
		int reagendadas = 0;
		
		ArrayList<Appointment> citas = appointmentsController.getCitas();
		
		for (Appointment cita : citas) {
			
			EstadoActual estado = cita.getEstadoActual();
			
			switch (estado) {
			case PROGRAMADA:
				programadas++;
				break;
				
			case CONFIRMADA:
				confirmadas++;
				break;
				
			case EN_PROGRESO:
				en_progreso++;
				break;
				
			case COMPLETADA:
				completadas++;
				break;
				
			case CANCELADA:
				canceladas++;
				break;
				
			case REAGENDADA:
				reagendadas++;
				break;
			}
			
		}
		
		int total = citas.size();
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append("=== ANÁLISIS DE UTILIZACIÓN ===");
		sb.append("- Total de citas: ").append(total).append("\n");
		sb.append("- Programadas: ").append(programadas).append(" [").append(String.format("%.2f%%", (programadas * 100.0) / total)).append("\n");
		sb.append("- Confirmadas: ").append(confirmadas).append(" [").append(String.format("%.2f%%", (confirmadas * 100.0) / total)).append("\n");
		sb.append("- En Progreso: ").append(en_progreso).append(" [").append(String.format("%.2f%%", (en_progreso * 100.0) / total)).append("\n");
		sb.append("- Completadas: ").append(completadas).append(" [").append(String.format("%.2f%%", (completadas * 100.0) / total)).append("\n");
		sb.append("- Canceladas: ").append(canceladas).append(" [").append(String.format("%.2f%%", (canceladas * 100.0) / total)).append("\n");
		sb.append("- Reagendadas: ").append(reagendadas).append(" [").append(String.format("%.2f%%", (reagendadas * 100.0) / total)).append("\n");
		
		return sb.toString();
		
	}
	
	
	///Generar reporte de eficiencia del personal médico
	public String calcularEficienciaPersonal() {
	    
	    if (this.personalMedico.isEmpty()) {
	        return "No hay personal médico registrado";
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    sb.append("=== EFICIENCIA DEL PERSONAL ===\n");
	    
	    for (GeneralMedic medico : this.personalMedico) {
	        String nombre = medico.getNombre();
	        String id = medico.getId();
	        double salario = medico.calcularSalario();
	        
	        sb.append("\n- ID: ").append(id);
	        sb.append(" | Nombre: ").append(nombre).append("\n");
	        
	        //Identificar tipo y mostrar estadísticas específicas
	        if (medico instanceof GeneralDoctor) {
	            GeneralDoctor doctor = (GeneralDoctor) medico;
	            int consultas = doctor.getConsultasRealizadas();
	            sb.append("  Tipo: Doctor General\n");
	            sb.append("  Consultas realizadas: ").append(consultas).append("\n");
	            
	            //Calcular eficiencia (consultas por cada $1000 de salario)
	            double eficiencia = (salario > 0) ? (consultas / (salario / 1000.0)) : 0;
	            sb.append("  Eficiencia: ").append(String.format("%.2f", eficiencia)).append(" consultas/$1000\n");
	            
	        } else if (medico instanceof Surgeon) {
	            Surgeon cirujano = (Surgeon) medico;
	            int horas = cirujano.getHorasCirugiaRealizadas();
	            sb.append("  Tipo: Cirujano\n");
	            sb.append("  Horas de cirugía realizadas: ").append(horas).append("\n");
	            
	            //Calcular eficiencia (horas por cada $1000 de salario)
	            double eficiencia = (salario > 0) ? (horas / (salario / 1000.0)) : 0;
	            sb.append("  Eficiencia: ").append(String.format("%.2f", eficiencia)).append(" horas/$1000\n");
	            
	        } else if (medico instanceof Therapist) {
	            Therapist terapeuta = (Therapist) medico;
	            int procedimientos = terapeuta.getProcedimientosRealizados();
	            sb.append("  Tipo: Terapeuta\n");
	            sb.append("  Procedimientos realizados: ").append(procedimientos).append("\n");
	            
	            //Calcular eficiencia (procedimientos por cada $1000 de salario)
	            double eficiencia = (salario > 0) ? (procedimientos / (salario / 1000.0)) : 0;
	            sb.append("  Eficiencia: ").append(String.format("%.2f", eficiencia)).append(" procedimientos/$1000\n");
	            
	        } else if (medico instanceof Nurse) {
	            Nurse enfermero = (Nurse) medico;
	            String turno = enfermero.getTipoDeTurno();
	            sb.append("  Tipo: Enfermero/a\n");
	            sb.append("  Turno: ").append(turno).append("\n");
	            sb.append("  (Sin contador de actividades)\n");
	        }
	        
	        sb.append("  Salario: $").append(String.format("%.2f", salario)).append("\n");
	        sb.append("  ").append("--------------------------------------------------").append("\n");
	    }
	    
	    return sb.toString();
	}
	
	public MedicalAppointmentController getAppointmentsController() {
	    return this.appointmentsController;
	}
	
}


