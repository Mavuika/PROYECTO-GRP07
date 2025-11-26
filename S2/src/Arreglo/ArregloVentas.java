package Arreglo;

import java.util.ArrayList;
import CLASE.Venta;

public class ArregloVentas {
    private ArrayList<Venta> ventas;
    
    public ArregloVentas() {
        ventas = new ArrayList<Venta>();
    }
    
    public void adicionar(Venta x) {
        ventas.add(x);
    }
    
    public Venta obtener(int x) {
        return ventas.get(x);
    }
    
    public int tamano() {
        return ventas.size();
    }
    
    public Venta buscarPorId(int id) {
        for (Venta v : ventas) {
            if (v.getId() == id) return v;
        }
        return null;
    }
    
    public boolean anularVenta(int id, String motivo) {
        Venta v = buscarPorId(id);
        if (v == null) return false;
        if (v.isAnulada()) return false; 
        v.anular(motivo);
        return true;
    }
    
    public ArrayList<Venta> listarPorMetodoPago(String metodo) {
        ArrayList<Venta> resultado = new ArrayList<>();
        for (Venta v : ventas) {
            if (v.getMetodoPago() != null && v.getMetodoPago().equalsIgnoreCase(metodo)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}