package Laboratorio_3;

import java.util.ArrayList;


 ///Representa un Cirujano en el hospital.
 ///Salario = Salario base + (horas de cirugía × tarifa por hora) + bonos por riesgo
 
public class Surgeon extends GeneralMedic {
    private ArrayList<String> tipoDeOperacionesQueRealiza;
    private int horasDeCirugiaDisponibles;
    private double bonoPorRiesgo;
    private int horasCirugiaRealizadas;

    ///Constructor de cirujano, tipo de operaciones que realiza es un array.
    ///
    public Surgeon(String id, String nombre, String departamento, int añosExperiencia, 
                  double salarioBase, ArrayList<String> tipoDeOperacionesQueRealiza, 
                  int horasDeCirugiaDisponibles, double bonoPorRiesgo) {
        super(id, nombre, departamento, añosExperiencia, salarioBase);
        this.tipoDeOperacionesQueRealiza = tipoDeOperacionesQueRealiza;
        this.horasDeCirugiaDisponibles = horasDeCirugiaDisponibles;
        this.bonoPorRiesgo = bonoPorRiesgo;
        this.horasCirugiaRealizadas = 0;
    }

    /// Getters y Setters
    public ArrayList<String> getTipoDeOperacionesQueRealiza() {
        return tipoDeOperacionesQueRealiza;
    }

    public void setTipoDeOperacionesQueRealiza(ArrayList<String> tipoDeOperacionesQueRealiza) {
        this.tipoDeOperacionesQueRealiza = tipoDeOperacionesQueRealiza;
    }

    public int getHorasDeCirugiaDisponibles() {
        return horasDeCirugiaDisponibles;
    }

    public void setHorasDeCirugiaDisponibles(int horasDeCirugiaDisponibles) {
        this.horasDeCirugiaDisponibles = horasDeCirugiaDisponibles;
    }

    public double getBonoPorRiesgo() {
        return bonoPorRiesgo;
    }

    public void setBonoPorRiesgo(double bonoPorRiesgo) {
        this.bonoPorRiesgo = bonoPorRiesgo;
    }

    public int getHorasCirugiaRealizadas() {
        return horasCirugiaRealizadas;
    }

    public void setHorasCirugiaRealizadas(int horasCirugiaRealizadas) {
        this.horasCirugiaRealizadas += horasCirugiaRealizadas;
    }

    @Override
    public double calcularSalario() {
        double tarifaPorHora = 150.0; // Tarifa estándar por hora de cirugía
        return salarioBase + (horasCirugiaRealizadas * tarifaPorHora) + bonoPorRiesgo;
    }

    @Override
    public String toString() {
        return super.toString() + 
               " | Tipo: Cirujano" +
               " | Horas Cirugía Realizadas: " + horasCirugiaRealizadas +
               " | Bono Riesgo: $" + bonoPorRiesgo +
               " | Salario Total: $" + calcularSalario();
    }
}
