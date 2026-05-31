package biblioteca.vista.consola;
import biblioteca.controlador.AlumnoControlador;
import biblioteca.modelo.Alumno;

import java.time.LocalDate;
import java.util.List;


/**
 * Pantalla de usuario para administrar el catálogo de alumnos por consola.
 * Permite interactuar directamente con las operaciones del AlumnoControlador.
 *
 * @author Noelia Alfonso
 */
public class AlumnoVista extends ModeloVista {
    private  AlumnoControlador control = new AlumnoControlador();

    @Override
    protected void imprimirMenu(){
        System.out.println("Gestion de Alumnos: \n" +
                        "[ 1 ] - Crear  Alumno\n" +
                        "[ 2 ] - Editar un Alumno\n" +
                        "[ 3 ] - Borrar un Alumno\n" +
                        "[ 4 ] - Listar Alumnos\n" +
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
                crearAlumno();
                break;


            case "2":
                editarAlumno();
                break;


            case "3":
                borrarAlumno();
                break;


            case "4":
                listarAlumnos();
                break;
        }
        return false;

    }

    private void borrarAlumno() {
        do{
            System.out.println("Ingrese el C.I. del alumno a eliminar (o 0 para cancelar): ");
            int cedula= teclado.nextInt();
            teclado.nextLine();
            Alumno alumno= control.obtenerAlumnoPorCi(cedula);
            if(cedula==0){
                break;
            }
            if(control.borrarAlumno(alumno)){
                System.out.println("Alumno eliminado con exito.");
                System.out.println();
                break;
            }
            else{
                System.out.println("El alumno que quiere eliminar no existe.");
                System.out.println();
            }

        }while(true);

    }

    private void listarAlumnos() {
        List<String> listaDeLineas = control.listarAlumnos();

        if (listaDeLineas.isEmpty()) {
            System.out.println("No hay datos.");
        }
        else {
            // Estructura de columnas alineadas a la izquierda para armar la tabla en consola.
            String formato = "| %-10s | %-50s | %-20s | %-12s | %-12s | %-15s |%n";
            System.out.println("LISTADO DE ALUMNOS ");
            System.out.println();
            System.out.printf(formato, "C.I.", "NOMBRE", "EMAIL", "TEL", "FECHA", "FACULTAD");
            System.out.println();
            for (String linea : listaDeLineas) {
                String[] partes = linea.split(";");
                System.out.printf(formato, partes[0], partes[1], partes[2], partes[3], partes[4], partes[5]);
            }
            System.out.println();
        }
    }

    private void editarAlumno() {
        int ced = 0;
        boolean esValido = false;
        do {
            System.out.println("Ingrese el C.I. del alumno (o 0 para cancelar): ");
            ced = teclado.nextInt();
            teclado.nextLine();

            if (ced == 0) {
                return;
            }

            if (control.existe(ced)) {
                esValido = true;
            } else {
                System.out.println("El alumno no existe. Intente de nuevo.");
                System.out.println();
            }
        } while (!esValido);

        if(esValido){
            System.out.println("Ingrese el nuevo nombre:");
            String nuevoNombre= teclado.nextLine();

            System.out.println("Ingrese su  nuevo correo electronico: ");
            String emailNuevo= teclado.nextLine();

            System.out.println("Ingrese su nuevo numero de telefono: ");
            String telefonoNuevo= teclado.nextLine();

            System.out.println("Ingrese su fecha de nacimiento en el formato dd/MM/yyyy : ");
            String fechaNueva= teclado.nextLine();
            LocalDate fechaNacimientoNueva= ConversionFecha.convertirFecha(fechaNueva);

            System.out.println("Ingrese su Facultad: ");
            String facultadNueva= teclado.nextLine();

            control.editarAlumno(nuevoNombre,ced,emailNuevo,telefonoNuevo,fechaNacimientoNueva,facultadNueva);
            System.out.println("El alumno fue editado con exito.");
            System.out.println();
        }
    }

    private  void crearAlumno() {
        int ci;
        do{
            System.out.println("Ingrese el C.I. del  nuevo alumno (o 0 para cancelar): ");
            ci= teclado.nextInt();
            teclado.nextLine();

            if(ci==0){
                return;
            }

            if(control.existe(ci)){
                System.out.println(" Error: El alumno ya fue creado.");
                System.out.println();
            }
            else{
                break;
            }
        }while(true);

        if(!control.existe(ci)){

            System.out.println("Ingrese su nombre completo: ");
            String nombre= teclado.nextLine();

            System.out.println("Ingrese su correo electronico: ");
            String email= teclado.nextLine();

            System.out.println("Ingrese su numero de telefono: ");
            String telefono= teclado.nextLine();

            System.out.println("Ingrese su fecha de nacimiento en el formato dd/mm/aa : ");
            String fecha= teclado.nextLine();
            LocalDate fechaNacimiento= ConversionFecha.convertirFecha(fecha);

            System.out.println("Ingrese su Facultad: ");
            String facultad= teclado.nextLine();

            control.crearAlumno(nombre,ci,email,telefono,fechaNacimiento,facultad);
            System.out.println("Alumno creado con éxito.");
            System.out.println();

        }
    }

}

