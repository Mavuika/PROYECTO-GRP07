package arrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CLASE.Producto;
import conexion.ConexionMySQL;

public class ArrayProducto {

    public boolean insertar(Producto p) {
        String sql = "INSERT INTO productos (id, categoria, nombre, precio, stock) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getCategoria());
            ps.setString(3, p.getNombre());
            ps.setDouble(4, p.getPrecio());
            ps.setInt(5, p.getCantidad());
            int r = ps.executeUpdate();
            return r > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean editar(Producto p) {
        String sql = "UPDATE productos SET categoria = ?, nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, p.getCategoria());
            ps.setString(2, p.getNombre());
            ps.setDouble(3, p.getPrecio());
            ps.setInt(4, p.getCantidad());
            ps.setString(5, p.getCodigo());
            int r = ps.executeUpdate();
            return r > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean eliminarPorCodigo(String codigo) {
        String sql = "DELETE FROM productos WHERE id = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, codigo);
            int r = ps.executeUpdate();
            return r > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT id, categoria, nombre, precio, stock FROM productos ORDER BY nombre";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("id"));
                p.setCategoria(rs.getString("categoria"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("stock"));
                lista.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public ArrayList<Producto> consultarPorCodigo(String codigo) {
        ArrayList<Producto> lista = new ArrayList<>();
        String sql = "SELECT id, categoria, nombre, precio, stock FROM productos WHERE id = ? OR id LIKE ? OR nombre LIKE ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, codigo + "%");
            ps.setString(3, "%" + codigo + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    p.setCodigo(rs.getString("id"));
                    p.setCategoria(rs.getString("categoria"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setCantidad(rs.getInt("stock"));
                    lista.add(p);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }
}