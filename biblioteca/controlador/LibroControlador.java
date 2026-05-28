package biblioteca.controlador;
import biblioteca.modelo.Libro;
import biblioteca.repositorio.RepositorioLibros;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador que maneja todo lo relacionado con los libros.
 * Sirve de puente para pasar la información entre la pantalla y la base de datos.
 * @author Noelia Alfonso
 */
public class LibroControlador {
    private static RepositorioLibros repositorio= new RepositorioLibros();

    public boolean crearLibro(long id,String titulo,String editorial, int anho,String autor ){
        Libro libro= new Libro(id,titulo,editorial,anho,autor);
        return repositorio.crear(libro);
    }

    public boolean editarLibro(long id,String titulo,String editorial, int anho,String autor ){
        Libro nuevoLibro= new Libro(id,titulo,editorial,anho,autor);
        return repositorio.editar(nuevoLibro);
    }

    public boolean borrarLibro(Libro libro){
        return repositorio.borrar(libro);
    }

    /**
     * Junta todos los libros y los acomoda en texto separado por punto y coma.
     * Sirve para pasar los datos ordenados a la pantalla sin mandarle los objetos completos.
     *
     * @return Lista de textos con formato: "id;titulo;editorial;anhoPublicacion;autor"
     */
    public List<String> listarLibros(){
        List<Libro> listaOriginal = repositorio.getList();
        List<String> listaParaVista = new ArrayList<>();

        for (Libro libro : listaOriginal) {
            String datos = libro.getId() + ";" +
                    libro.getTitulo() + ";" +
                    libro.getEditorial() + ";" +
                    libro.getAnhoPublicacion() + ";" +
                    libro.getAutor();
            listaParaVista.add(datos);
        }
        return listaParaVista;
    }

    public Libro  obtenerLibroPorId(long id){
        return repositorio.obtenerPorId(id);
    }

    public boolean existe(long id) {
        return repositorio.obtenerPorId(id) != null;
    }
}
