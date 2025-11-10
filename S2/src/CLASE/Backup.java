package CLASE;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import Arreglo.Producto_Arreglo;
import Arreglo.ArregloVentas;

public class Backup {
    private Producto_Arreglo pa;
    private ArregloVentas av;
    public Backup(Producto_Arreglo pa, ArregloVentas av) {
        this.pa = pa;
        this.av = av;
    }
    public String crearRespaldo() {
        try {
            File dir = new File("datos");
            if (!dir.exists()) dir.mkdir();
            String pfile = "datos/productos_backup.csv";
            try (PrintWriter pw = new PrintWriter(new FileWriter(pfile))) {
                pw.println("nombre,codigo,precio,cantidad");
                for (int i = 0; i < pa.Tamaño(); i++) {
                    CLASE.Producto prod = pa.Obtener(i);
                    pw.printf("\"%s\",\"%s\",%.2f,%d%n",
                            prod.getNombre(), prod.getCodigo(), prod.getPrecio(), prod.getCantidad());
                }
            }
            String vfile = "datos/ventas_backup.csv";
            try (PrintWriter pw = new PrintWriter(new FileWriter(vfile))) {
                pw.println("id,producto,codigo,cantidad,total,fecha,anulada,metodo");
                for (int i = 0; i < av.tamano(); i++) {
                    CLASE.Venta v = av.obtener(i);
                    pw.printf("%d,\"%s\",\"%s\",%d,%.2f,\"%s\",%b,\"%s\"%n",
                            v.getId(),
                            v.getProducto().getNombre(),
                            v.getProducto().getCodigo(),
                            v.getCantidadVendida(),
                            v.getTotal(),
                            v.getFecha().toString(),
                            v.isAnulada(),
                            v.getMetodoPago()
                    );
                }
            }
            return "Respaldo creado en carpeta 'datos' (productos_backup.csv, ventas_backup.csv)";
        } catch (Exception ex) {
            return "Error al crear respaldo: " + ex.getMessage();
        }
    }
}