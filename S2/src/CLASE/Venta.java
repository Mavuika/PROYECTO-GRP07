package CLASE;

import java.time.LocalDateTime;

public class Venta {
	 private Producto producto;
	 private int cantidadVendida;
	 private LocalDateTime fecha;
	 private double precio;

 public Venta(Producto producto, int cantidadVendida, int precio) {
	  this.producto = producto;
	  this.cantidadVendida = cantidadVendida;
	  this.fecha = LocalDateTime.now();
	  this.precio = precio;
}

 public double getTotal() {
	  return producto.getPrecio() * cantidadVendida;
}
 public String toString() {
	 return "Venta de " + cantidadVendida + " x " + producto.getNombre() + " - Total: $" + getTotal() + " - Fecha: " + fecha;
  }
}
