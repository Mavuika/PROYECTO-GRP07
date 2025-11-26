package LectorArchivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class LeerArchivo {

    private LeerArchivo() {
    }

    public static String leerArchivo(String ruta) throws IOException {
        Objects.requireNonNull(ruta, "ruta no puede ser null");
        String lowerCaseRuta = ruta.toLowerCase();
        if (lowerCaseRuta.endsWith(".txt")) {
            return leerTxt(ruta);
        } else if (lowerCaseRuta.endsWith(".xlsx") || lowerCaseRuta.endsWith(".xls")) {
            return leerXlsx(ruta);
        } else {
            throw new IOException("Formato de archivo no soportado: " + ruta);
        }
    }

    private static String leerTxt(String ruta) throws IOException {
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

    private static String leerXlsx(String ruta) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileInputStream fis = new FileInputStream(ruta);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            sb.append(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            sb.append(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            sb.append(cell.getBooleanCellValue());
                            break;
                        default:
                            // No hacer nada para otros tipos de celda
                    }
                    if (cellIterator.hasNext()) {
                        sb.append("\t"); // Separador de tabulación entre celdas
                    }
                }
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
}