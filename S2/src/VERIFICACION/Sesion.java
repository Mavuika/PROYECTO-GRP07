package VERIFICACION;

public final class Sesion {
    private Sesion() {
    	
    } 
    private static Usuario usuarioActual;

    public static void setCurrentUser(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getCurrentUser() {
        return usuarioActual;
    }


    public static String getRole() {
        if (usuarioActual == null) return "";
        String r = usuarioActual.getRol();
        return (r == null) ? "" : r.trim();
    }

    public static String getUserName() {
        if (usuarioActual == null) return "";
        String n = usuarioActual.getNombre();
        return (n == null) ? "" : n.trim();
    }

    public static void clear() {
        usuarioActual = null;
    }
}