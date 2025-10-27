package LectorArchivo;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public final class LeerArchivo {

    private LeerArchivo() { 
    	//CONSTRUCTOR PRIVADO QUE EVITA SER INSTANCIADA,PORQUE?
    	//PREVIENE CREAR INSTANCIACIONES COMO POR EJEMPLO LEERARCHIVO LEAR=NEW LEERARCHIVO();
    }

    // DEVUELVE LO QUE ESTA DENTRO DEL ARCHIVO COMO STRING (UTF-8)
    //QUE ES UTF-8: ES UNA INSTANCIA QUE PASA DE UNICODE A BYTES, LO QUE PUEDE REPRESENTAR CUALQUIER SIMBOLO LETRA ETC
    //USADA PARA EVITAR PROBLEMAS CON SIMBOLOS ESPECIALES
    //EN RESUMEN, NOS AYUDA A LEER CUALQUIER CARACTER 
    
    public static String leerArchivo(String ruta) throws IOException {
        Objects.requireNonNull(ruta, "ruta no puede ser null");
        Path path = Paths.get(ruta);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader lector = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                sb.append(linea).append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}