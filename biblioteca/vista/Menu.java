package biblioteca.vista;
/**
 * Menú principal del sistema de biblioteca.
 * Funciona como el eje central que permite al usuario navegar hacia los submenús de gestión de libros, alumnos y préstamos.
 *
 * @author Noelia Alfonso
 */
public class Menu extends ModeloVista{
    AlumnoVista alumno= new AlumnoVista();
    LibroVista libro= new LibroVista();
    PrestamoVista prestamo= new PrestamoVista();

    @Override
    protected void imprimirMenu(){
        System.out.println("Menu Biblioteca: \n"+
                "[ 1 ] - Gestion de Libros\n" +
                "[ 2 ] - Gestion de Alumnos\n" +
                "[ 3 ] - Gestion de Préstamos\n"+
                "[ 4 ] - Salir");
        System.out.println();
        System.out.println("Ingrese una opcion: ");
    }
    @Override
    protected  int getCantidadOpciones(){
        return 4;
    }

    @Override
    protected boolean procesarOpcion(String opcion){
        switch(opcion){
            case "4":
                return true;
            case "1":
                libro.ejecutar();
                break;
            case "2":
                alumno.ejecutar();
                break;
            case "3":
                prestamo.ejecutar();
                break;
        }
        return false;
    }

}
