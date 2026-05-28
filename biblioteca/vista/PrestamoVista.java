package biblioteca.vista;
import biblioteca.controlador.AlumnoControlador;
import biblioteca.controlador.LibroControlador;
import biblioteca.controlador.PrestamoControlador;
import java.util.ArrayList;
import java.util.List;

/**
 * Pantalla de usuario para administrar la creación, devolución e informes de préstamos.
 * Coordina la comunicación con los controladores de alumnos, libros y préstamos.
 *
 * @author Noelia Alfonso
 */
public class PrestamoVista extends ModeloVista{
    private PrestamoControlador control = new PrestamoControlador();
    private AlumnoControlador controlAlumno= new AlumnoControlador();
    private LibroControlador controlLibro= new LibroControlador();

    @Override
    protected void imprimirMenu() {
        System.out.println("Gestion de Prestamos:\n" +
                "[ 1 ] - Crear  Préstamo\n" +
                "[ 2 ] - Borrar Préstamo\n" +
                "[ 3 ] - Listar Préstamos\n" +
                "[ 4 ] - Devolver Préstamo\n" +
                "[ 5 ] - Informe de Préstamos Vencidos\n"+
                "[ 6 ] - Salir");
        System.out.println();
        System.out.println("Ingrese una opcion: ");
    }
    @Override
    protected  int getCantidadOpciones(){
        return 6;
    }

    @Override
    protected boolean procesarOpcion(String opcion) {
        switch(opcion){
            case "6":
                return true;
            case "1":
                crearPrestamo();
                break;
            case "2":
                borrarPrestamo();
                break;
            case "3":
                listarPrestamos();
                break;
            case "4":
                devolverPrestamo();
                break;
            case "5":
                prestamosVencidos();
                break;
        }
        return false;
    }

    private void crearPrestamo(){
        System.out.println("Ingrese el numero de C.I. del alumno: ");
        int cedula= teclado.nextInt();
        teclado.nextLine();

        if(controlAlumno.existe(cedula)){
            List<Long> idLibros= pedirLibros();
            long idGenerado = control.crearPrestamo(controlAlumno.obtenerAlumnoPorCi(cedula), control.obtenerListaLibros(idLibros));
            System.out.println("El Prestamo ha sido creado con exito");
            System.out.println("El id de su prestamo es: " + idGenerado);

        }
        else{
            System.out.println("Error: El alumno no existe en el sistema.");
            System.out.println();
            return;
        }

    }

    /**
     * Solicita repetidamente los IDs de los libros que se van a prestar.
     * @return Lista con los identificadores de los libros válidos elegidos.
     */
    private List<Long> pedirLibros() {
        List<Long> idLibros = new ArrayList<>();
        do {
            System.out.print("Ingrese el id del libro a prestar (o 0 para finalizar): ");
            long idIngresado = teclado.nextLong();
            teclado.nextLine(); // Limpiar buffer

            if (idIngresado == 0) {
                // Se valida que al menos haya llevado un libro
                if (idLibros.isEmpty()) {
                    System.out.println("Debe agregar al menos un libro para realizar un préstamo.");
                    System.out.println();
                    continue; // Vuelve al inicio del do-while
                }
                break;
            }

            if (controlLibro.existe(idIngresado)) {
                idLibros.add(idIngresado);
                System.out.println("El libro ha sido prestado correctamente.");
                System.out.println();

            } else {
                System.out.println("Error: El id del libro no existe en el sistema.");
                System.out.println();
            }

        } while (true);
        return idLibros;
    }

    private void borrarPrestamo(){
        do{
            System.out.println("Ingrese el I.D. del prestamo a eliminar (o 0 para cancelar): ");
            long id= teclado.nextLong();
            teclado.nextLine();
            if(id==0){
                break;
            }
            if(control.existe(id)){
                control.borrarPrestamo(control.obtenerPrestamo(id));
                System.out.println("Prestamo eliminado con exito.");
                System.out.println();
                break;
            }
            else{
                System.out.println("Error: el prestamo no existe.");
                System.out.println();
            }
        }while(true);
    }

    private void listarPrestamos(){
        List<String> listaDeLineas= control.listarPrestamo();
        if(listaDeLineas.isEmpty()){
            System.out.println("No hay datos.");
        }
        else{
            // Estructura de columnas alineadas a la izquierda para armar la tabla en consola
            String formato = "| %-15s | %-15s | %-40s | %-35s | %-12s | %-12s |%n";
            System.out.println("LISTADO DE PRESTAMOS");
            System.out.printf(formato, "I.D. PRÉSTAMO ", "C.I. ALUMNO", "NOMBRE ALUMNO", "LIBROS PRESTADOS", "FECHA PRÉSTAMO","FECHA DEVOLUCIÓN");
            System.out.println();
            for(String linea: listaDeLineas){
                String[] partes= linea.split(";");
                System.out.printf(formato, partes[0], partes[1], partes[2], partes[3], partes[4],partes[5]);
            }
            System.out.println();
        }
    }

    private void devolverPrestamo(){
        System.out.println("Ingrese el id del prestamo:");
        long id= teclado.nextLong();
        teclado.nextLine();
        if(control.existe(id)){
            control.devolverPrestamo(control.obtenerPrestamo(id));
            System.out.println("Prestamo devuelto con exito");
            System.out.println();
        }
        else{
            System.out.println("Error: el prestamo no existe.");
            System.out.println();
        }

    }

    private void prestamosVencidos(){
        List<String> listaDeLineas= control.listarVencidos();
        if(listaDeLineas.isEmpty()){
            System.out.println("No hay prestamos vencidos.");
            System.out.println();
        }
        else{
            String formato = "| %-15s | %-15s | %-40s | %-50s | %-10s |%n";
            System.out.println("LISTADO DE PRESTAMOS VENCIDOS");
            System.out.printf(formato, "I.D. PRÉSTAMO ", "C.I. ALUMNO", "NOMBRE ALUMNO", "LIBROS PRESTADOS", "FECHA PRÉSTAMO");
            System.out.println();
            for(String linea: listaDeLineas){
                String[] partes= linea.split(";");
                System.out.printf(formato, partes[0], partes[1], partes[2], partes[3], partes[4]);
            }
            System.out.println();
        }

    }

}









