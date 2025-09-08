package CLASE;

import java.time.LocalDateTime;

public class Venta {
	 private Producto producto;
	 private int cantidadVendida;
	 private LocalDateTime fecha;

 public Venta(Producto producto, int cantidadVendida) {
	  this.producto = producto;
	  this.cantidadVendida = cantidadVendida;
	  this.fecha = LocalDateTime.now();
}

 public double getTotal() {
	  return producto.getPrecio() * cantidadVendida;
}
 public String toString() {
	 return "Venta de " + cantidadVendida + " x " + producto.getNombre() + " - Total: $" + getTotal() + " - Fecha: " + fecha;
  }
}
