package biblioteca.repositorio;
import biblioteca.modelo.Alumno;
import biblioteca.modelo.Libro;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Implementación en memoria del repositorio de libros.
 * Utiliza el ID como clave única para la gestión.
 * @author Noelia Alfonso
 */
public class RepositorioLibros implements Repositorio<Libro>{

    // Almacenamiento estático compartido para simular una base de datos en memoria
    private static Map<Long, Libro> libros= new HashMap<>();

    /**
     * Registra un nuevo libro en el sistema.
     * @return true si el libro fue registrado; false si el ID ya existía.
     */
    @Override
    public boolean crear(Libro libro) {
        if(!(libros.containsValue(libro))){
            libros.put(libro.getId(),libro );
            return true;
        }
        return false;
    }

    @Override
    public boolean borrar(Libro libro) {
        if(libros.containsKey(libro.getId())){
            libros.remove(libro.getId(),libro);
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
            return true;
        }
        return false;
    }

    public Libro obtenerPorId(long id) {
        return libros.get(id);
    }


}

