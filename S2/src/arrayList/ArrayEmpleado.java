package arrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CLASE.Empleado;
import conexion.ConexionMySQL;


public class ArrayEmpleado {

    public ArrayList<Empleado> listarEmpleados() {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT NRC, nombre, cargo, salario, telefono FROM empleados ORDER BY NRC";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Empleado(
                        rs.getString("NRC"),
                        rs.getString("nombre"),
                        rs.getString("cargo"),
                        rs.getDouble("salario"),
                        rs.getString("telefono")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Empleado> consultarPorNRC(String nrc) {
        ArrayList<Empleado> lista = new ArrayList<>();
        String sql = "SELECT NRC, nombre, cargo, salario, telefono FROM empleados WHERE NRC = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, nrc);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Empleado(
                            rs.getString("NRC"),
                            rs.getString("nombre"),
                            rs.getString("cargo"),
                            rs.getDouble("salario"),
                            rs.getString("telefono")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean insertar(Empleado emp) {
        String sql = "INSERT INTO empleados (NRC, nombre, cargo, salario, telefono) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, emp.getNrc());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getCargo());
            ps.setDouble(4, emp.getSalario());
            ps.setString(5, emp.getTelefono());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar(Empleado emp) {
        String sql = "UPDATE empleados SET nombre = ?, cargo = ?, salario = ?, telefono = ? WHERE NRC = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getCargo());
            ps.setDouble(3, emp.getSalario());
            ps.setString(4, emp.getTelefono());
            ps.setString(5, emp.getNrc());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPorNRC(String nrc) {
        Connection cnx = null;
        try {
            cnx = ConexionMySQL.getConexión();
            cnx.setAutoCommit(false);

            //NOTA: OBTIENE EL NOMBRE DEL EMPLEADO ANTES DE BORRARLO
            String empName = null;
            try (PreparedStatement ps1 = cnx.prepareStatement("SELECT nombre FROM empleados WHERE NRC = ?")) {
                ps1.setString(1, nrc);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) empName = rs.getString(1);
                }
            }

            if (empName == null) {
                cnx.rollback();
                return false;
            }

            //NOTA: BORRA LOS PEDIDOS ASOCIADOS AL EMPLEADO
            try (PreparedStatement ps2 = cnx.prepareStatement("DELETE FROM pedidos WHERE empleado = ?")) {
                ps2.setString(1, empName);
                ps2.executeUpdate();
            }

            //NOTA: BORRA EL EMPLEADO
            try (PreparedStatement ps3 = cnx.prepareStatement("DELETE FROM empleados WHERE NRC = ?")) {
                ps3.setString(1, nrc);
                ps3.executeUpdate();
            }

            cnx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (cnx != null) try { cnx.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            return false;
        } finally {
            if (cnx != null) try { cnx.setAutoCommit(true); cnx.close(); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }
}