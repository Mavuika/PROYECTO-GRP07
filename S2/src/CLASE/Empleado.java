package CLASE;

public class Empleado {
    private String nrc; 
    private String nombre;
    private String cargo;
    private double salario;
    private String telefono;

    public Empleado(String nrc, String nombre, String cargo, double salario, String telefono) {
        this.nrc = nrc == null ? "" : nrc;
        this.nombre = nombre == null ? "" : nombre;
        this.cargo = cargo == null ? "" : cargo;
        this.salario = salario;
        this.telefono = telefono == null ? "" : telefono;
    }

    
    public String getNrc() {
		return nrc;
	}


	public void setNrc(String nrc) {
		this.nrc = nrc;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCargo() {
		return cargo;
	}


	public void setCargo(String cargo) {
		this.cargo = cargo;
	}


	public double getSalario() {
		return salario;
	}


	public void setSalario(double salario) {
		this.salario = salario;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String toString() {
        return nombre + " (" + nrc + ") - " + cargo + " - S/ " + salario + " - Tel: " + telefono;
    }
}