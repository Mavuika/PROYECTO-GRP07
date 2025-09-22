package Arreglo;

import java.util.ArrayList;
import CLASE.Producto;

public class Producto_Arreglo {
private ArrayList<Producto>invent;
public Producto_Arreglo()
{
	invent=new ArrayList<Producto>();
	Adicionar(new Producto("Tarro " ,"tA1",4.50,12));
	Adicionar(new Producto("Arroz " ,"tA2",205.50,3));
	Adicionar(new Producto("Aceite " ,"tA3",128.0,5));
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
public Producto Buscar(String cod)
{
    for(int i=0; i<Tamaño(); i++)
    {
        if(Obtener(i).getCodigo().equals(cod)) return Obtener(i);
    }
    return null;
}
public void Eliminar(Producto x)
{
	invent.remove(x);
}

}