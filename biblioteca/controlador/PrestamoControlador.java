package biblioteca.controlador;
import biblioteca.modelo.Alumno;
import biblioteca.modelo.Libro;
import biblioteca.modelo.Prestamo;
import biblioteca.repositorio.RepositorioPrestamos;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que maneja todo lo relacionado con los prestamos.
 * Sirve de puente para pasar la información entre la pantalla y la base de datos.
 * @author Noelia Alfonso
 */
public class PrestamoControlador {

    private static RepositorioPrestamos repositorio= new RepositorioPrestamos();
    private LibroControlador controlLibro= new LibroControlador();

    public long crearPrestamo(Alumno alumno, List<Libro> libros){
        Prestamo prestamo= new Prestamo(alumno,libros);
        repositorio.crear(prestamo);
        return prestamo.getId();
    }

    /**
     * Busca y arma una lista de objetos Libro usando una lista de IDs.
     * @return La lista de libros si todos existen; null si al menos uno no se encuentra.
     */
    public List<Libro> obtenerListaLibros(List<Long> idLibros){
        List<Libro> libros = new ArrayList<>();
        for( Long id: idLibros){
            if(controlLibro.existe(id)){
                libros.add(controlLibro.obtenerLibroPorId(id));
            }
            else {
                return null;
            }
        }
        return libros;
    }


    public boolean borrarPrestamo(Prestamo prestamo){
        return repositorio.borrar(prestamo);
    }

    public boolean devolverPrestamo(Prestamo prestamo) {
        return repositorio.devolverPrestamo(prestamo);
    }

    /**
     * Junta todos los préstamos y los formatea en texto separado por punto y coma.
     * @return Lista de textos con formato: "id;ciAlumno;nombreAlumno;libros;fechaPrestamo;fechaDevolucion"
     */
    public List<String>  listarPrestamo(){
        List<Prestamo> listaOriginal = repositorio.getList();
        List<String> listaParaVista = new ArrayList<>();
        for (Prestamo prestamo : listaOriginal) {
            String librosFormateados= obtenerNombresLibros(prestamo.getLibros());
            String datos = prestamo.getId() + ";" +
                    prestamo.getAlumno().getCi() + ";" +
                    prestamo.getAlumno().getNombreCompleto() + ";" +
                    librosFormateados + ";" +
                    prestamo.getFechaPrestamo() + ";" +
                    prestamo.getFechaDevolucion();
            listaParaVista.add(datos);
        }
        return listaParaVista;
    }

    /**
     * Filtra los préstamos que ya vencieron y los formatea para la pantalla.
     * @return Lista de textos con formato: "id;ciAlumno;nombreAlumno;libros;fechaPrestamo"
     */
    public List<String> listarVencidos(){
        List<Prestamo> listaOriginal = repositorio.obtenerVencidos();
        List<String> listaParaVista = new ArrayList<>();
        for (Prestamo prestamo : listaOriginal) {
            String librosFormateados= obtenerNombresLibros(prestamo.getLibros());
            String datos = prestamo.getId() + ";" +
                    prestamo.getAlumno().getCi() + ";" +
                    prestamo.getAlumno().getNombreCompleto() + ";" +
                    librosFormateados + ";" +
                    prestamo.getFechaPrestamo() ;
            listaParaVista.add(datos);
        }
        return listaParaVista;
    }

    public boolean existe(long id) {
        return repositorio.obtenerPrestamoPorId(id) != null;
    }
    public Prestamo obtenerPrestamo(long id){
        return repositorio.obtenerPrestamoPorId(id);
    }

    /**
     * Método auxiliar para transformar una lista de libros en una sola cadena de texto.
     */
    private String obtenerNombresLibros(List<Libro> libros) {
        String nombres = "";
        for (Libro lib : libros) {
            nombres += lib.getTitulo() + ", ";
        }
        if (nombres.endsWith(", ")) {
            //Se le resta 2 a la cantidad de caracteres de nombres que serian la ultima coma y espacio
            nombres = nombres.substring(0, nombres.length() - 2);
        }
        return nombres;
    }


}
