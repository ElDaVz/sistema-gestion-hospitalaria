package Laboratorio_3;

/// Clase hija de GeneralMedic, implementa calcularSalario() 
/// tomando en cuenta Salario = Salario base + (número de consultas × tarifa)
/// representa un doctor general en el hospital.
///
public class GeneralDoctor extends GeneralMedic{
	private String especializacion;
    private int capacidadPacientesPorDia;
    private double tarifaDeConsulta;
    private int consultasRealizadas;

    public GeneralDoctor(String id, String nombre, String departamento, int añosExperiencia, 
                        double salarioBase, String especializacion, int capacidadPacientesPorDia, 
                        double tarifaDeConsulta) {
        super(id, nombre, departamento, añosExperiencia, salarioBase);
        this.especializacion = especializacion;
        this.capacidadPacientesPorDia = capacidadPacientesPorDia;
        this.tarifaDeConsulta = tarifaDeConsulta;
        this.consultasRealizadas = 0;
    }

    // Getters y Setters
    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public int getCapacidadPacientesPorDia() {
        return capacidadPacientesPorDia;
    }

    public void setCapacidadPacientesPorDia(int capacidadPacientesPorDia) {
        this.capacidadPacientesPorDia = capacidadPacientesPorDia;
    }

    public double getTarifaDeConsulta() {
        return tarifaDeConsulta;
    }

    public void setTarifaDeConsulta(double tarifaDeConsulta) {
        this.tarifaDeConsulta = tarifaDeConsulta;
    }

    public int getConsultasRealizadas() {
        return consultasRealizadas;
    }

    public void setConsultasRealizadas() {
        this.consultasRealizadas += 1;
    }

    @Override
    public double calcularSalario() {
        return salarioBase + (consultasRealizadas * tarifaDeConsulta);
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Especialización: " + especializacion + 
               " | Consultas Realizadas: " + consultasRealizadas +
               " | Salario Total: $" + calcularSalario();
    }
}
