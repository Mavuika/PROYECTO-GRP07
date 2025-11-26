package VERIFICACION;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import conexion.ConexionMySQL;

public class ServicioAutenticacion {

    private final Map<String, String> codigosPorRol;

    public ServicioAutenticacion() {
        codigosPorRol = new HashMap<>();
        codigosPorRol.put("Administrador", "123");
        codigosPorRol.put("Vendedor", "234");
        codigosPorRol.put("Almacenero", "245");
        codigosPorRol.put("Usuario", "456");
        codigosPorRol.put("Gerente", "567");
    }

    public Usuario autenticar(String nombre, String codigoNrc, String rol) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (codigoNrc == null || codigoNrc.trim().isEmpty()) {
            throw new IllegalArgumentException("El código (NRC) no puede estar vacío.");
        }
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío.");
        }

        String nrc = codigoNrc.trim();
        String nombreTrim = nombre.trim();
        String rolTrim = rol.trim();

        String sel = "SELECT nombre, cargo FROM empleados WHERE nrc = ?";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sel)) {

            ps.setString(1, nrc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String nombreDB = rs.getString("nombre");
                    String cargoDB = rs.getString("cargo");
                    if (cargoDB == null) cargoDB = "";

                    if (!cargoDB.equalsIgnoreCase(rolTrim)) {
                        return null;
                    }

                    if (nombreDB == null || nombreDB.trim().isEmpty() || !nombreDB.equalsIgnoreCase(nombreTrim)) {
                        String upd = "UPDATE empleados SET nombre = ? WHERE nrc = ?";
                        try (PreparedStatement psUpd = cnx.prepareStatement(upd)) {
                            psUpd.setString(1, nombreTrim);
                            psUpd.setString(2, nrc);
                            psUpd.executeUpdate();
                        } catch (SQLException ex) {
                            // No es crítico, se puede ignorar si falla la actualización del nombre
                        }
                    }

                    return new Usuario(nombreTrim, rolTrim, nrc);
                } else {
                    // Si el NRC no existe, no se puede autenticar. No se inserta un nuevo usuario.
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void setCodigoParaRol(String rol, String codigo) {
        if (rol == null || rol.trim().isEmpty()) return;
        codigosPorRol.put(rol.trim(), codigo);
    }

    public String getCodigoParaRol(String rol) {
        return codigosPorRol.get(rol);
    }
}