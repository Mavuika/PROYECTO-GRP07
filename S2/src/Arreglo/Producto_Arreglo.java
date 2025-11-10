package Arreglo;

import java.util.ArrayList;
import CLASE.Producto;

public class Producto_Arreglo {
    private static Producto_Arreglo instance = null;
    private ArrayList<Producto> invent;

    private Producto_Arreglo() {
        invent = new ArrayList<Producto>();
        Adicionar(new Producto("Tarro" ,"tA1",4.50,12));
        Adicionar(new Producto("Arroz" ,"tA2",205.50,3));
        Adicionar(new Producto("Aceite" ,"tA3",128.0,5));
    }

    public static synchronized Producto_Arreglo getInstance() {
        if (instance == null) {
            instance = new Producto_Arreglo();
        }
        return instance;
    }

    public void Adicionar(Producto x) {
        invent.add(x);
    }

    public int Tamaño() {
        return invent.size();
    }

    public Producto Obtener(int x) {
        return invent.get(x);
    }

    public Producto Buscar(String cod) {
        for(int i=0; i<Tamaño(); i++) {
            if(Obtener(i).getCodigo().equals(cod)) return Obtener(i);
        }
        return null;
    }

    public Producto Buscar(double pre) {
        for(int i=0; i<Tamaño(); i++) {
            if(Obtener(i).getPrecio() == pre) return Obtener(i);
        }
        return null;
    }

    public void Eliminar(Producto x) {
        invent.remove(x);
    }

    public Producto BuscarPorNombre(String nombre) {
        if (nombre == null) return null;
        String b = nombre.trim().toLowerCase();
        for (int i = 0; i < Tamaño(); i++) {
            if (Obtener(i).getNombre().trim().toLowerCase().equals(b)) return Obtener(i);
        }
        return null;
    }

    public boolean ajustarStock(String codigo, int delta) {
        Producto p = Buscar(codigo);
        if (p == null) return false;
        int nueva = p.getCantidad() + delta;
        p.setCantidad(Math.max(nueva, 0));
        return true;
    }

    public ArrayList<Producto> obtenerProductosCriticos(int umbral) {
        ArrayList<Producto> critic = new ArrayList<>();
        for (int i = 0; i < Tamaño(); i++) {
            Producto p = Obtener(i);
            if (p.getCantidad() <= umbral) critic.add(p);
        }
        return critic;
    }
}