package biblioteca.vista;
import java.util.Scanner;
/**
 * Clase base para todas las pantallas de la consola.
 * Controla el ciclo de vida de un menú: muestra las opciones, lee el teclado,
 *
 * @author Noelia Alfonso
 */
public abstract class ModeloVista {

    protected Scanner teclado = new Scanner(System.in);

    /**
     * Inicia el bucle principal de la pantalla.
     * Se ejecuta continuamente hasta que el método procesarOpcion devuelva true (salir).
     */
    public void ejecutar(){
        boolean salir= false;
        do{
            String opcion= pedirOpcion();
            salir=  procesarOpcion(opcion);

        }while(!salir);
    }

    /**
     * Muestra el menú y se asegura de que el usuario ingrese una opción correcta.
     */
    private String pedirOpcion(){
        String opcion;
        do{
            imprimirMenu();
            opcion= teclado.nextLine();

        }while(!esOpcionValida(opcion));
        return opcion;
    }

    /**
     * Verifica que la opción ingresada sea un número entre 1 y la cantidad máxima permitida.
     */
    protected boolean esOpcionValida(String opcion) {
        int max = getCantidadOpciones();

        for (int i = 1; i <= max; i++) {
            if (opcion.equals(String.valueOf(i))) {
                return true;
            }
        }
        return false;
    }

    // Métodos que cada pantalla específica (Alumnos, Libros, etc.) debe rellenar con su propia lógica
    protected abstract void imprimirMenu();
    protected abstract boolean procesarOpcion(String opcion);
    protected abstract int getCantidadOpciones();

}
