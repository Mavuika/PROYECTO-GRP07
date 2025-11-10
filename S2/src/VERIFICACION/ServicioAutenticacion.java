package VERIFICACION;

import java.util.HashMap;
import java.util.Map;

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

    public Usuario autenticar(String nombre, String codigo, String rol) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (codigo == null || codigo.isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        if (rol == null || rol.trim().isEmpty()) {
            throw new IllegalArgumentException("El rol no puede estar vacío.");
        }

        String codigoEsperado = codigosPorRol.get(rol);
        if (codigoEsperado == null) {
            return null; 
        }

        if (codigoEsperado.equals(codigo.trim())) {
            return new Usuario(nombre.trim(), rol);
        }
        return null;
    }

 
    public void setCodigoParaRol(String rol, String codigo) {
        codigosPorRol.put(rol, codigo);
    }
}