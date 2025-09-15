package CLASE;

public class Producto {
	private String nombre;
    private String codigo;
    private double precio;
    private int cantidad;

    
  
    
    public Producto(String nombre, String codigo, double precio, int cantidad) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public String toString() {
        return nombre + " (" + codigo + ") - $" + precio + " - Cantidad: " + cantidad;
    }
}
