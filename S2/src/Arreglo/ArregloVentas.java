package Arreglo;

import java.util.ArrayList;
import CLASE.Venta;

public class ArregloVentas {
	private ArrayList<Venta>ventas;
	
	public ArregloVentas() {
		ventas=new ArrayList<Venta>();
		
	}
	
	public void adicionar(Venta x)
	{
		ventas.add(x);
	}
	
	
	public Venta Obtener(int x)
	{
		return ventas.get(x);
	}
	
	public int Tamaño() {
		return ventas.size();
	}
	
}
