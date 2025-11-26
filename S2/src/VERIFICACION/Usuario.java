package VERIFICACION;

public class Usuario {
    private final String nombre;
    private final String rol;
    private final String nrc;

    public Usuario(String nombre, String rol, String nrc) {
        this.nombre = nombre;
        this.rol = rol;
        this.nrc = nrc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getNrc() {
        return nrc;
    }
}