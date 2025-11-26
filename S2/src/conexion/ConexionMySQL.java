package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
public static Connection getConexión() {
	Connection cnx=null;
	try {
		//ADJUNTO EL DRIVER
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver correcto");
		cnx=DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/polleria","root","therealmath"
				);
		System.out.println("conexión correcta");
	} catch (Exception e) {
		System.out.println("Error: "+e);
	}
	return cnx;
}
	public static void main(String[] args) {
		getConexión();
	}

}
