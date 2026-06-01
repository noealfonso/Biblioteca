package biblioteca.repositorio;
import biblioteca.modelo.Libro;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Implementación en memoria del repositorio de libros.
 * Utiliza el ID como clave única para la gestión.
 * @author Noelia Alfonso
 */
public class RepositorioLibros implements Repositorio<Libro>, Serializable {

    private static final String ARCHIVOLIBROS="libros.txt";
    // El contenedor central. Aquí se guarda los libros en la memoria RAM mientras el programa corre.
    // No es 'static' porque se quiere que Java pueda meterlo por completo dentro del archivo.
    private  Map< Long, Libro> libros;

    public RepositorioLibros(){
        // Se crea un mapa limpio por si acaso es la primera vez que se usa el programa
        this.libros = new HashMap<>();
        cargarDatos();

    }

    /**
     * Registra un nuevo libro en el sistema.
     * @return true si el libro fue registrado; false si el ID ya existía.
     */
    @Override
    public boolean crear(Libro libro) {
        if(!(libros.containsValue(libro))){
            libros.put(libro.getId(),libro );
            guardarDatos();
            return true;
        }
        return false;
    }

    @Override
    public boolean borrar(Libro libro) {
        if(libros.containsKey(libro.getId())){
            libros.remove(libro.getId(),libro);
            guardarDatos();
            return true;
        }
        return false;
    }

    @Override
    public List<Libro> getList() {
        return new ArrayList(libros.values());
    }

    /**
     * Actualiza los datos de un libro existente utilizando su ID como referencia.
     * @return true si se encontró y actualizó el libro; false en caso contrario.
     */
    public boolean editar(Libro libroNuevo){
        if(libros.containsKey(libroNuevo.getId())){
            libros.put(libroNuevo.getId(), libroNuevo);
            guardarDatos();
            return true;
        }
        return false;
    }

    public Libro obtenerPorId(long id) {
        return libros.get(id);
    }

    /**
     * Guarda el mapa completo de libros en el disco duro.
     */
    private void guardarDatos(){
        try(ObjectOutputStream escribirArchivo= new ObjectOutputStream(new FileOutputStream(ARCHIVOLIBROS))){
            escribirArchivo.writeObject(libros);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el archivo por: "+e.getMessage());
        }
    }

    /**
     * Trae de vuelta los libros desde el archivo hacia el programa.
     */
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        // Se crea un apuntador al archivo físico para inspeccionarlo
        File archivo = new File(ARCHIVOLIBROS);

        if (!archivo.exists()) {
            return;
        }

        try (ObjectInputStream lecturaArchivo = new ObjectInputStream(new FileInputStream(archivo))) {

            // Leemos los bytes del archivo y le obligamos a Java a entender que es nuestro Mapa (casteo)
            // Esto destruye el mapa vacío del constructor y lo reemplaza por el mapa que guardamos en el pasado
            libros = (Map<Long, Libro>) lecturaArchivo.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("El archivo está dañado. Iniciando con mapa vacío: " + e.getMessage());
            libros = new HashMap<>(); // Le damos un mapa limpio de respaldo para que la app no colapse
        }
    }


}
