package CLASE;

public class Inventario {
    private Producto[] productos;
    private Venta[] historialVentas;
    private int totalProductos;
    private int totalVentas;
    private double ingresosTotales;

    public Inventario() {
        productos = new Producto[100];
        historialVentas = new Venta[100];
        totalProductos = 0;
        totalVentas = 0;
        ingresosTotales = 0.0;
    }

    public void agregarProducto(Producto producto) {
        productos[totalProductos] = producto;
        totalProductos++;
    }

    public boolean registrarVenta(String codigoProducto, int cantidad) {
        for (int i = 0; i < totalProductos; i++) {
            if (productos[i].getCodigo().equals(codigoProducto)) {
                if (productos[i].getCantidad() >= cantidad) {
                    productos[i].setCantidad(productsafe(productos[i].getCantidad() - cantidad));
                    Venta venta = new Venta(productos[i], cantidad);
                    historialVentas[totalVentas] = venta;
                    totalVentas++;
                    ingresosTotales += venta.getTotal();
                    return true;
                }
            }
        }
        return false;
    }

    private int productsafe(int valor) {
        return Math.max(valor, 0);
    }

    public String mostrarInventario() {
        String resultado = "";
        for (int i = 0; i < totalProductos; i++) {
            resultado += productos[i].toString() + "\n";
        }
        return resultado;
    }

    public String mostrarHistorialVentas() {
        String resultado = "";
        for (int i = 0; i < totalVentas; i++) {
            resultado += historialVentas[i].toString() + "\n";
        }
        return resultado;
    }

    public double obtenerIngresosTotales() {
        return ingresosTotales;
    }
}
