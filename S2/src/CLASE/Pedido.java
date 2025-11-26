package CLASE;

public class Pedido {
    private String codigo; 
    private String empleado; 
    private String producto; 
    private int cantidad;
    private double total;
    private String metodoPago;

    public Pedido(String codigo, String empleado, String producto, int cantidad, double total, String metodoPago) {
        this.codigo = codigo == null ? "" : codigo;
        this.empleado = empleado == null ? "" : empleado;
        this.producto = producto == null ? "" : producto;
        this.cantidad = cantidad;
        this.total = total;
        this.metodoPago = metodoPago == null ? "" : metodoPago;
    }


    public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getEmpleado() {
		return empleado;
	}


	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}


	public String getProducto() {
		return producto;
	}


	public void setProducto(String producto) {
		this.producto = producto;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getMetodoPago() {
		return metodoPago;
	}


	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}


	public String toString() {
        return codigo + " - " + producto + " x" + cantidad + " - " + total + " - " + empleado + " (" + metodoPago + ")";
    }
}