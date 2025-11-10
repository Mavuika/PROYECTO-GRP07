package CLASE;

import java.time.LocalDateTime;


public class Compra {
    private int cantidad;
    private Producto producto;
    private String proveedor;
    private LocalDateTime fecha;
    
    public Compra(Producto producto, int cantidad, String proveedor) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.proveedor = proveedor == null ? "" : proveedor;
        this.fecha = LocalDateTime.now();
    }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public String getProveedor() { return proveedor; }
    public LocalDateTime getFecha() { return fecha; }
    public double getTotal() { return producto.getPrecio() * cantidad; }

    public String toString() {
        double totalRedondeado = Math.round(getTotal() * 100.0) / 100.0;
        return "Compra - " + cantidad + " x " + producto.getNombre()
                + " (" + producto.getCodigo() + ") - Proveedor: " + proveedor
                + " - Total: " + totalRedondeado + " - Fecha: " + fecha;
    }
}