package biblioteca.vista;
import biblioteca.controlador.LibroControlador;
import java.util.List;
/**
 * Pantalla de usuario para administrar el catálogo de libros por consola.
 * Sirve para que el usuario pueda interactuar con las operaciones del LibroControlador.
 *
 * @author Noelia Alfonso
 */
public class LibroVista extends ModeloVista{

    LibroControlador control= new LibroControlador();

    @Override
    protected void imprimirMenu(){
        System.out.println("Gestion de Libros:\n" +
                "[ 1 ] - Crear  Libro\n" +
                "[ 2 ] - Editar un Libro\n" +
                "[ 3 ] - Borrar un Libro\n" +
                "[ 4 ] - Listar Libros\n" +
                "[ 5 ] - Salir"
        );
        System.out.println();
        System.out.println("Ingrese una opcion: ");
    }

    @Override
    protected  int getCantidadOpciones(){
        return 5;
    }

    @Override
    protected boolean procesarOpcion(String opcion){
        switch (opcion){
            case "5":
                return true;
            case "1":
                crearLibro();
                break;
            case "2":
                editarLibro();
                break;
            case "3":
                borrarLibro();
                break;
            case "4":
                listarLibros();
                break;
        }
        return false;
    }

    private void crearLibro(){
        long id;
        do{
            System.out.println("Ingrese el I.D. del  nuevo libro (o 0 para cancelar): ");
            id= teclado.nextLong();
            teclado.nextLine();
            if(id==0){
                return;
            }
            if(control.existe(id)){
                System.out.println(" Error: El libro ya fue creado.");
                System.out.println();
            }
            else{
                break;
            }
        }while(true);

        if(!control.existe(id)){
            System.out.println("Ingrese el titulo del libro: ");
            String titulo= teclado.nextLine();

            System.out.println("Ingrese la editorial del libro: ");
            String editorial= teclado.nextLine();

            System.out.println("Ingrese el año de publicacion del libro: ");
            int anho= teclado.nextInt();
            teclado.nextLine();

            System.out.println("Ingrese el autor del libro:  ");
            String autor= teclado.nextLine();

            control.crearLibro(id,titulo,editorial,anho,autor);
            System.out.println("Libro creado con éxito.");
            System.out.println();

        }
    }

    private void editarLibro(){
        long id= 0;
        boolean esValido = false;
        do {
            System.out.println("Ingrese el I.D. del libro a editar (o 0 para cancelar): ");
            id = teclado.nextLong();
            teclado.nextLine();

            if (id == 0) {
                return;
            }

            if (control.existe(id)) {
                esValido = true;
            } else {
                System.out.println("Error: El libro no existe.");
                System.out.println();
            }
        } while (!esValido);

        if(esValido){
            System.out.println("Ingrese el titulo del libro: ");
            String titulo= teclado.nextLine();

            System.out.println("Ingrese la editorial del libro: ");
            String editorial= teclado.nextLine();

            System.out.println("Ingrese el año de publicacion del libro: ");
            int anho= teclado.nextInt();
            teclado.nextLine();

            System.out.println("Ingrese el autor del libro:  ");
            String autor= teclado.nextLine();

            control.editarLibro(id,titulo,editorial,anho,autor);
            System.out.println("Libro editado con éxito.");
            System.out.println();

        }

    }

    private void borrarLibro(){
        do{
            System.out.println("Ingrese el I.D. del libro a eliminar (o 0 para cancelar): ");
            long id= teclado.nextLong();
            teclado.nextLine();
            if(id==0){
                break;
            }

            if(control.existe(id)){
                control.borrarLibro(control.obtenerLibroPorId(id));
                System.out.println("Libro eliminado con exito.");
                System.out.println();
                break;
            }
            else{
                System.out.println("Error: El libro no existe.");
                System.out.println();
            }

        }while(true);

    }

    private void listarLibros(){
        List<String> listaDeLineas = control.listarLibros();

        if (listaDeLineas.isEmpty()) {
            System.out.println("No hay datos.");
            System.out.println();
        }
        else {
            // Estructura de columnas alineadas a la izquierda para armar la tabla en consola
            String formato = "| %-15s | %-35s | %-25s | %-8s | %-30s |%n";
            System.out.println("LISTADO DE LIBROS");
            System.out.println();
            System.out.printf(formato, "I.D.", "TITULO", "EDITORIAL", "AÑO", "AUTOR");
            System.out.println();
            for (String linea : listaDeLineas) {
                String[] partes= linea.split(";");
                System.out.printf(formato, partes[0], partes[1], partes[2], partes[3], partes[4]);
            }
            System.out.println();
        }
    }

}
