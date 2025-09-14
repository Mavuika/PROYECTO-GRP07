package Arreglo;

import java.util.ArrayList;
import CLASE.Producto;

public class Producto_Arreglo {
private ArrayList<Producto>invent;
public Producto_Arreglo()
{
	invent=new ArrayList<Producto>();
	Adicionar(new Producto("Tarro Leche Gloria Azul" ,"A1",4.50,12));
	Adicionar(new Producto("Arroz Añejo Extra COSTEÑITO Saco 50Kg" ,"A2",205.50,3));
	Adicionar(new Producto("Aceite Fritura Intensa ARO Balde 18L" ,"A3",128.0,5));
}
public void Adicionar(Producto x)
{
	invent.add(x);
}
public int Tamaño()
{
	return invent.size();
}
public Producto Obtener(int x)
{
	return invent.get(x);
}
}
