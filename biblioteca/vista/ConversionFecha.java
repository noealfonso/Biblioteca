package biblioteca.vista;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Se encarga de convertir formatos de fecha entre la vista y el modelo.
 *
 * @author Noelia Alfonso
 */
public class ConversionFecha {

    /**
     * Convierte un String (dd/MM/yyyy) en un objeto LocalDate.
     */
    public static  LocalDate convertirFecha(String fecha){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaFormato = LocalDate.parse(fecha, formateador);
        return fechaFormato;
    }

}
