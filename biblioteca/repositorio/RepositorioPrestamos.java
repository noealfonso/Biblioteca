package biblioteca.repositorio;
import biblioteca.modelo.Prestamo;
import java.io.*;
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
public class RepositorioPrestamos implements Repositorio<Prestamo>, Serializable {

    private static final String ARCHIVOPRESTAMOS="prestamos.txt";
    // El contenedor central. Aquí se guarda los préstamos en la memoria RAM mientras el programa corre.
    // No es 'static' porque se quiere que Java pueda meterlo por completo dentro del archivo.
    private  Map< Long, Prestamo> prestamos;

    public RepositorioPrestamos(){
        // Se crea un mapa limpio por si acaso es la primera vez que se usa el programa
        this.prestamos = new HashMap<>();
        cargarDatos();

    }

    /**
     * Registra un nuevo prestamo en el sistema.
     * @return true si el prestamo fue registrado; false si el ID ya existía.
     */
    @Override
    public boolean crear(Prestamo prestamo) {
        if(! (prestamos.containsKey(prestamo.getId()) ) ){
            prestamos.put(prestamo.getId(), prestamo);
            guardarDatos();
            return true;
        }
        return false;
    }


    @Override
    public boolean borrar(Prestamo prestamo) {
        if(prestamos.containsValue(prestamo)) {
            prestamos.remove(prestamo.getId(), prestamo);
            guardarDatos();
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
            guardarDatos();
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

    /**
     * Guarda el mapa completo de prestamos en el disco duro.
     */
    private void guardarDatos(){
        try(ObjectOutputStream escribirArchivo= new ObjectOutputStream(new FileOutputStream(ARCHIVOPRESTAMOS))){
            escribirArchivo.writeObject(prestamos);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el archivo por: "+e.getMessage());
        }
    }

    /**
     * Trae de vuelta los prestamos desde el archivo hacia el programa.
     */
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        // Se crea un apuntador al archivo físico para inspeccionarlo
        File archivo = new File(ARCHIVOPRESTAMOS);

        if (!archivo.exists()) {
            return;
        }

        try (ObjectInputStream lecturaArchivo = new ObjectInputStream(new FileInputStream(archivo))) {

            // Leemos los bytes del archivo y le obligamos a Java a entender que es nuestro Mapa (casteo)
            // Esto destruye el mapa vacío del constructor y lo reemplaza por el mapa que guardamos en el pasado
            prestamos = (Map<Long, Prestamo>) lecturaArchivo.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("El archivo está dañado. Iniciando con mapa vacío: " + e.getMessage());
            prestamos = new HashMap<>(); // Le damos un mapa limpio de respaldo para que la app no colapse
        }
    }
}
