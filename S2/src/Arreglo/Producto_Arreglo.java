package Arreglo;

import java.util.ArrayList;
import CLASE.Producto;

public class Producto_Arreglo {
private ArrayList<Producto>invent;
public Producto_Arreglo()
{
	invent=new ArrayList<Producto>();
	Adicionar(new Producto("Sergio" ,"n00362754",12.3,12));
}
public void Adicionar(Producto x)
{
	invent.add(x);
}
}
