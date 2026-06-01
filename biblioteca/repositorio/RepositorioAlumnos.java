package biblioteca.repositorio;
import biblioteca.modelo.Alumno;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación en memoria del repositorio de alumnos.
 * Utiliza la Cédula de Identidad (CI) como clave única para la gestión.
 * @author Noelia Alfonso
 */

public class RepositorioAlumnos  implements Repositorio<Alumno>, Serializable {

    private static final String ARCHIVOALUMNO="alumnos.txt";
    // El contenedor central. Aquí se guarda los alumnos en la memoria RAM mientras el programa corre.
    // No es 'static' porque se quiere que Java pueda meterlo por completo dentro del archivo.
    private  Map< Integer, Alumno> alumnos;

    public RepositorioAlumnos(){
        // Se crea un mapa limpio por si acaso es la primera vez que se usa el programa
        this.alumnos = new HashMap<>();
        cargarDatos();

    }

    /**
     * Registra un nuevo alumno en el sistema.
     * @return true si el alumno fue registrado; false si el CI ya existía.
     */
    @Override
    public boolean crear(Alumno alumno) {
        if(!(this.alumnos.containsValue(alumno))){
            this.alumnos.put(alumno.getCi(),alumno );
            guardarDatos();
            return true;
        }
        return false;
    }

    @Override
    public boolean borrar(Alumno alumno) {
        if(alumnos.containsValue(alumno)){
            alumnos.remove(alumno.getCi(), alumno);
            guardarDatos();
            return true;
        }
        return false;
    }

    @Override
    public List<Alumno> getList() {
        return  new ArrayList(alumnos.values());
    }

    /**
     * Actualiza los datos de un alumno existente utilizando su CI como referencia.
     * @return true si se encontró y actualizó el alumno; false en caso contrario.
     */
    public boolean editar(Alumno alumnoNuevo) {
        if(alumnos.containsKey(alumnoNuevo.getCi())){
            alumnos.put(alumnoNuevo.getCi(), alumnoNuevo);
            guardarDatos();
            return true;
        }
        return false;

    }

    public Alumno obtenerPorCi(int ci) {
        return alumnos.get(ci);
    }

    /**
     * Guarda el mapa completo de alumnos en el disco duro.
     */
    private void guardarDatos(){
        try(ObjectOutputStream escribirArchivo= new ObjectOutputStream(new FileOutputStream(ARCHIVOALUMNO))){
            escribirArchivo.writeObject(alumnos);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el archivo por: "+e.getMessage());
        }
    }

    /**
     * Trae de vuelta los alumnos desde el archivo hacia el programa.
     */
    @SuppressWarnings("unchecked")
    private void cargarDatos() {
        // Se crea un apuntador al archivo físico para inspeccionarlo
        File archivo = new File(ARCHIVOALUMNO);

        if (!archivo.exists()) {
            return;
        }

        try (ObjectInputStream lecturaArchivo = new ObjectInputStream(new FileInputStream(archivo))) {

            // Leemos los bytes del archivo y le obligamos a Java a entender que es nuestro Mapa (casteo)
            // Esto destruye el mapa vacío del constructor y lo reemplaza por el mapa que guardamos en el pasado
            alumnos = (Map<Integer, Alumno>) lecturaArchivo.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("El archivo está dañado. Iniciando con mapa vacío: " + e.getMessage());
            alumnos = new HashMap<>(); // Le damos un mapa limpio de respaldo para que la app no colapse
        }
    }
}

