package test;
import biblioteca.vista.Menu;
/**
 * Punto de entrada principal de la aplicación (Clase Main).
 * Se encarga de encender el sistema y cederle el control al menú raíz.
 *
 * @author Noelia Alfonso
 */
public class MenuPrincipal {
    public static void main(String[] args) {

        Menu menu = new Menu();

        System.out.println("Iniciando Sistema de Gestión de Biblioteca.");
        System.out.println();
        menu.ejecutar();

    }
}
