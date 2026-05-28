package biblioteca.repositorio;
import biblioteca.modelo.Alumno;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementación en memoria del repositorio de alumnos.
 * Utiliza la Cédula de Identidad (CI) como clave única para la gestión.
 * @author Noelia Alfonso
 */

public class RepositorioAlumnos  implements Repositorio<Alumno>{

    // Almacenamiento estático compartido para simular una base de datos en memoria
    private static Map< Integer, Alumno> alumnos= new HashMap<>();

    /**
     * Registra un nuevo alumno en el sistema.
     * @return true si el alumno fue registrado; false si el CI ya existía.
     */
    @Override
    public boolean crear(Alumno alumno) {
        if(!(this.alumnos.containsValue(alumno))){
            this.alumnos.put(alumno.getCi(),alumno );
            return true;
        }
        return false;
    }

    @Override
    public boolean borrar(Alumno alumno) {
        if(alumnos.containsValue(alumno)){
            alumnos.remove(alumno.getCi(), alumno);
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
            return true;
        }
        return false;

    }

    public Alumno obtenerPorCi(int ci) {
        return alumnos.get(ci);
    }
}
