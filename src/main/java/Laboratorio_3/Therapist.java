package Laboratorio_3;

///Representa un Terapeuta en el hospital.
///Salario = Salario base + comisiones por procedimientos
///
public class Therapist extends GeneralMedic {
    private String terapiaEspecializada;
    private int duracionSesion;
    private int procedimientosRealizados;

    public Therapist(String id, String nombre, String departamento, int añosExperiencia, 
                    double salarioBase, String terapiaEspecializada, int duracionSesion) {
        super(id, nombre, departamento, añosExperiencia, salarioBase);
        this.terapiaEspecializada = terapiaEspecializada;
        this.duracionSesion = duracionSesion;
        this.procedimientosRealizados = 0;
    }

    // Getters y Setters
    public String getTerapiaEspecializada() {
        return terapiaEspecializada;
    }

    public void setTerapiaEspecializada(String terapiaEspecializada) {
        this.terapiaEspecializada = terapiaEspecializada;
    }

    public int getDuracionSesion() {
        return duracionSesion;
    }

    public void setDuracionSesion(int duracionSesion) {
        this.duracionSesion = duracionSesion;
    }

    public int getProcedimientosRealizados() {
        return procedimientosRealizados;
    }

    public void setProcedimientosRealizados() {
        this.procedimientosRealizados += 1;
    }

    @Override
    public double calcularSalario() {
        double comisionPorProcedimiento = 50.0; // Comisión fija por procedimiento
        return salarioBase + (procedimientosRealizados * comisionPorProcedimiento);
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Tipo: Terapeuta" +
               " | Especialización: " + terapiaEspecializada +
               " | Procedimientos Realizados: " + procedimientosRealizados +
               " | Salario Total: $" + calcularSalario();
    }
}
