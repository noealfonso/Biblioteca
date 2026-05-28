package biblioteca.repositorio;
import biblioteca.modelo.Prestamo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.time.LocalDate;
/**
 * Implementación en memoria del repositorio de prestamos.
 * Utiliza el ID como clave única para la gestión.
 * @author Noelia Alfonso
 */
public class RepositorioPrestamos implements Repositorio<Prestamo> {

    // Almacenamiento estático compartido para simular una base de datos en memoria
    private static Map<Long, Prestamo> prestamos= new HashMap<>();

    /**
     * Registra un nuevo prestamo en el sistema.
     * @return true si el prestamo fue registrado; false si el ID ya existía.
     */
    @Override
    public boolean crear(Prestamo prestamo) {
        if(! (prestamos.containsKey(prestamo.getId()) ) ){
            prestamos.put(prestamo.getId(), prestamo);
            return true;
        }
        return false;
    }


    @Override
    public boolean borrar(Prestamo prestamo) {
        if(prestamos.containsValue(prestamo)) {
            prestamos.remove(prestamo.getId(), prestamo);
            return true;
        }
        return false;
    }

    @Override
    public List<Prestamo> getList() {
        return new ArrayList(prestamos.values());
    }

    /**
     * Registra la devolución de un préstamo activo en el sistema.
     * @return true si el préstamo existía y se procesó la devolución; false en caso contrario.
     */
    public boolean devolverPrestamo( Prestamo prestamo){
        if(prestamos.containsValue(prestamo)){
            prestamo.devolverPrestamo();
            return true;
        }
        return false;
    }

    /**
     * Filtra y devuelve aquellos préstamos cuya fecha límite expiró y aún no fueron devueltos.
     * @return Lista de préstamos vencidos.
     */
    public List<Prestamo> obtenerVencidos(){
        List<Prestamo> vencidos= new ArrayList<>();
        for( Prestamo prestamo: prestamos.values()){
            if(prestamo.getFechaLimite().isBefore(LocalDate.now()) && prestamo.getFechaDevolucion()==null ){
                vencidos.add(prestamo);
            }
        }
        return vencidos;
    }

    public Prestamo obtenerPrestamoPorId(long id){
        return prestamos.get(id);
    }
}
