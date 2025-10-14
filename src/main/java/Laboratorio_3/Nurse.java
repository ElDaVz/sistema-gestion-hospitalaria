package Laboratorio_3;

///
///Representa un Enfermero/a en el hospital.
///Salario = Salario base + bonificación nocturna (si aplica)
///
public class Nurse extends GeneralMedic {
    private String tipoDeTurno;
    private String nivelDeCertificacion;

    public Nurse(String id, String nombre, String departamento, int añosExperiencia, 
                double salarioBase, String tipoDeTurno, String nivelDeCertificacion) {
        super(id, nombre, departamento, añosExperiencia, salarioBase);
        this.tipoDeTurno = tipoDeTurno;
        this.nivelDeCertificacion = nivelDeCertificacion;
    }

    // Getters y Setters
    public String getTipoDeTurno() {
        return tipoDeTurno;
    }

    public void setTipoDeTurno(String tipoDeTurno) {
        this.tipoDeTurno = tipoDeTurno;
    }

    public String getNivelDeCertificacion() {
        return nivelDeCertificacion;
    }

    public void setNivelDeCertificacion(String nivelDeCertificacion) {
        this.nivelDeCertificacion = nivelDeCertificacion;
    }

    @Override
    public double calcularSalario() {
        double bonificacionNocturna = 0.0;
        
        // Si el turno es nocturno, aplica bonificación del 20%
        if (tipoDeTurno.equalsIgnoreCase("nocturno")) {
            bonificacionNocturna = salarioBase * 0.20;
        }
        
        return salarioBase + bonificacionNocturna;
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Tipo: Enfermero/a" +
               " | Turno: " + tipoDeTurno +
               " | Nivel Certificación: " + nivelDeCertificacion +
               " | Salario Total: $" + calcularSalario();
    }
}
