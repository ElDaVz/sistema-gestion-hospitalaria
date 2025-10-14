package Laboratorio_3;

/// Esta clase es de tipo abstracto, será la superclase para cada distinto medico.

public abstract class GeneralMedic {
	protected String id;
    protected String nombre;
    protected String departamento;
    protected int añosExperiencia;
    protected double salarioBase;

    
    /// Constructor de la clase GeneralMedic
     
    public GeneralMedic(String id, String nombre, String departamento, int añosExperiencia, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.añosExperiencia = añosExperiencia;
        this.salarioBase = salarioBase;
    }

    // Getters y Setters
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }


    public String getDepartamento() {
        return departamento;
    }


    public int getAñosExperiencia() {
        return añosExperiencia;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }


    @Override
    public String toString() {
        return "ID: {" + id + "}" + 
               " | Nombre: " + nombre + 
               " | Departamento: " + departamento + 
               " | Años Experiencia: " + añosExperiencia + 
               " | Salario Base: $" + salarioBase;
    }
    
    
    ///Método abstracto que cada especialización debe implementar
    ///para calcular su salario de acuerdo a sus propias reglas.
    
   public abstract double calcularSalario();
}
