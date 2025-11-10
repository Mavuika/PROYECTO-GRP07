package CLASE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Venta {
    private static int contadorId = 0;
    private int id;
    private Producto producto;
    private int cantidadVendida;
    private LocalDateTime fecha;
    private boolean anulada;
    private String motivoAnulacion;
    private String metodoPago; 

    public Venta(Producto producto, int cantidadVendida) {
        this.id = ++contadorId;
        this.producto = producto;
        this.cantidadVendida = cantidadVendida;
        this.fecha = LocalDateTime.now();
        this.anulada = false;
        this.motivoAnulacion = "";
        this.metodoPago = "Efectivo";
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        return producto.getPrecio() * cantidadVendida;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public boolean isAnulada() {
        return anulada;
    }

    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void anular(String motivo) {
        this.anulada = true;
        this.motivoAnulacion = motivo == null ? "" : motivo;
    }

    public String toString() {
        double totalRedondeado = Math.round(getTotal() * 100.0) / 100.0;
        String estado = anulada ? " (ANULADA: " + motivoAnulacion + ")" : "";
        return "Venta#" + id
                + " - " + cantidadVendida + " x " + producto.getNombre()
                + " (" + producto.getCodigo() + ")"
                + " - Total: " + totalRedondeado
                + " - Fecha: " + fecha
                + estado;
    }
}