package biblioteca.vista.consola;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Se encarga de convertir formatos de fecha entre la vista y el modelo.
 * @author Noelia Alfonso
 */
public class ConversionFecha {
    
    private static final DateTimeFormatter FORMATEADOR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Convierte de String (dd/mm/aaaa) a LocalDate
     */
    public static LocalDate convertirFecha(String fecha) {
        return LocalDate.parse(fecha, FORMATEADOR);
    }

    /**
     * Convierte de LocalDate a String (dd/mm/aaaa)
     */
    public static String formatearFecha(LocalDate fecha) {
        if (fecha == null) {
            return "";
        }
        return fecha.format(FORMATEADOR);
    }

}
