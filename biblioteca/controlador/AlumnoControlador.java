package biblioteca.controlador;
import biblioteca.modelo.Alumno;
import biblioteca.repositorio.RepositorioAlumnos;
import biblioteca.vista.consola.ConversionFecha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
/**
 * Controlador que maneja todo lo relacionado con los alumnos.
 * Sirve de puente para pasar la información entre la pantalla y la base de datos.
 * @author Noelia Alfonso
 */
public class AlumnoControlador {

    private static RepositorioAlumnos repositorio= new RepositorioAlumnos();

    public boolean crearAlumno(String nombre,int ci , String email, String telefono, LocalDate fecha, String facultad){
        Alumno alumno= new Alumno(nombre,ci,email, telefono,fecha,facultad);
        return repositorio.crear(alumno);
    }

    public boolean editarAlumno(String nombre,int ci, String email, String telefono, LocalDate fecha, String facultad){
        Alumno alumnoNuevo= new Alumno(nombre,ci,email, telefono,fecha,facultad);
        return repositorio.editar(alumnoNuevo);
    }

    public boolean borrarAlumno(Alumno alumno){
        return repositorio.borrar(alumno);
    }


    public List<String> listarAlumnos() {
        return convertirListaAString(repositorio.getList());
    }


    public List<String> ordenarListaPorNombre() {
        List<Alumno> lista = repositorio.getList();
        lista.sort(Comparator.comparing(Alumno::getNombreCompleto));
        return convertirListaAString(lista);
    }

    public List<String> ordenarListaPorEdad() {
        List<Alumno> lista = repositorio.getList();
        lista.sort(Comparator.comparing(Alumno::getFechaNacimiento));
        return convertirListaAString(lista);
    }

    /**
     * Recupera todos los alumnos y los formatea en cadenas de texto separadas por punto y coma.
     * Es útil para transferir datos a la vista sin exponer directamente los objetos del modelo.
     * @return Lista de strings con el formato: "ci;nombre;email;telefono;fechaNacimiento;facultad"
     */
    private List<String> convertirListaAString(List<Alumno> listaOriginal) {
        List<String> listaParaVista = new ArrayList<>();
        for (Alumno a : listaOriginal) {
            String fechaString = ConversionFecha.formatearFecha(a.getFechaNacimiento());
            String datos = a.getCi() + ";" +
                    a.getNombreCompleto() + ";" +
                    a.getEmail() + ";" +
                    a.getTelefono() + ";" +
                    fechaString + ";" +
                    a.getFacultadPerteneciente();
            listaParaVista.add(datos);
        }
        return listaParaVista;
    }
    public Alumno obtenerAlumnoPorCi( int ci){
        return repositorio.obtenerPorCi(ci);
    }
    
    public String obtenerDatosPorCi(int ci){
        Alumno a = repositorio.obtenerPorCi(ci);
        if (a == null) {
            return null;
        }
        return  a.getCi() + ";" +
                a.getNombreCompleto() + ";" +
                a.getEmail() + ";" +
                a.getTelefono() + ";" +
                ConversionFecha.formatearFecha(a.getFechaNacimiento()) + ";" +
                a.getFacultadPerteneciente();

    }

    /**
     * Verifica la existencia de un alumno en el sistema a través de su cédula.
     * @return true si el alumno existe; false en caso contrario.
     */
    public boolean existe(int ci) {
        return repositorio.obtenerPorCi(ci) != null;
    }


}
