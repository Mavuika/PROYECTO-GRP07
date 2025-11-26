package arrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import CLASE.Pedido;
import conexion.ConexionMySQL;


public class ArrayPedido {

    public ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> lista = new ArrayList<>();
        String sql = "SELECT codigo, empleado, producto, cantidad, total, metodo_pago FROM pedidos ORDER BY codigo";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Pedido(
                        rs.getString("codigo"),
                        rs.getString("empleado"),
                        rs.getString("producto"),
                        rs.getInt("cantidad"),
                        rs.getDouble("total"),
                        rs.getString("metodo_pago")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public Pedido consultarPorCodigo(String codigo) {
        String sql = "SELECT codigo, empleado, producto, cantidad, total, metodo_pago FROM pedidos WHERE codigo = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Pedido(
                            rs.getString("codigo"),
                            rs.getString("empleado"),
                            rs.getString("producto"),
                            rs.getInt("cantidad"),
                            rs.getDouble("total"),
                            rs.getString("metodo_pago")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insertar(Pedido p) {
        Connection cnx = null;
        try {
            cnx = ConexionMySQL.getConexión();
            cnx.setAutoCommit(false);

            // buscar precio del producto
            Double precio = null;
            try (PreparedStatement ps1 = cnx.prepareStatement("SELECT precio FROM productos WHERE nombre = ?")) {
                ps1.setString(1, p.getProducto());
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) precio = rs.getDouble(1);
                }
            }

            if (precio == null) {
                cnx.rollback();
                return false; 
            }

            double total = precio * p.getCantidad();

            String sql = "INSERT INTO pedidos (codigo, empleado, producto, cantidad, total, metodo_pago) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps2 = cnx.prepareStatement(sql)) {
                ps2.setString(1, p.getCodigo());
                ps2.setString(2, p.getEmpleado());
                ps2.setString(3, p.getProducto());
                ps2.setInt(4, p.getCantidad());
                ps2.setDouble(5, total);
                ps2.setString(6, p.getMetodoPago());
                ps2.executeUpdate();
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

    public boolean editar(Pedido p) {
        Connection cnx = null;
        try {
            cnx = ConexionMySQL.getConexión();
            cnx.setAutoCommit(false);

            //NOTA: BUSCA EL PRECIO DEL PRODUCTO
            Double precio = null;
            try (PreparedStatement ps1 = cnx.prepareStatement("SELECT precio FROM productos WHERE nombre = ?")) {
                ps1.setString(1, p.getProducto());
                try (ResultSet rs = ps1.executeQuery()) {
                    if (rs.next()) precio = rs.getDouble(1);
                }
            }

            if (precio == null) {
                cnx.rollback();
                return false;
            }

            double total = precio * p.getCantidad();

            String sql = "UPDATE pedidos SET empleado = ?, producto = ?, cantidad = ?, total = ?, metodo_pago = ? WHERE codigo = ?";
            try (PreparedStatement ps2 = cnx.prepareStatement(sql)) {
                ps2.setString(1, p.getEmpleado());
                ps2.setString(2, p.getProducto());
                ps2.setInt(3, p.getCantidad());
                ps2.setDouble(4, total);
                ps2.setString(5, p.getMetodoPago());
                ps2.setString(6, p.getCodigo());
                int r = ps2.executeUpdate();
                cnx.commit();
                return r > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cnx != null) try { cnx.rollback(); } catch (Exception ex) { ex.printStackTrace(); }
            return false;
        } finally {
            if (cnx != null) try { cnx.setAutoCommit(true); cnx.close(); } catch (Exception ex) { ex.printStackTrace(); }
        }
    }

    public boolean eliminarPorCodigo(String codigo) {
        String sql = "DELETE FROM pedidos WHERE codigo = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public int borrarPedidosPorEmpleado(String empleadoNombre) {
        String sql = "DELETE FROM pedidos WHERE empleado = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, empleadoNombre);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int borrarPedidosPorProducto(String productoNombre) {
        String sql = "DELETE FROM pedidos WHERE producto = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, productoNombre);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}