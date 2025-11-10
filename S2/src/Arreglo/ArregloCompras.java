package Arreglo;

import java.util.ArrayList;
import CLASE.Compra;

public class ArregloCompras {
    private ArrayList<Compra> compras;
    public ArregloCompras() {
        compras = new ArrayList<>();
    }
    public void adicionar(Compra c) {
        compras.add(c);
    }
    public int tamano() {
        return compras.size();
    }
    public Compra obtener(int i) {
        return compras.get(i);
    }
}