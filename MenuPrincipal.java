package biblioteca.vista.gui;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private JPanel panelContenedor;
    private JPanel panelPrincipal;
    private JPanel panelCentro;

    public MenuPrincipal() {
        setTitle("Sistema de Gestión de Biblioteca" );
        setSize(1000,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panelContenedor = new JPanel(new BorderLayout());
        panelContenedor.setBackground(new Color(204, 214, 221));
        add(panelContenedor);
        
        crearMenuPrincipal();
        
        panelCentro= new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);

        crearMenuPrincipal();
        panelCentro.add(panelPrincipal,new GridBagConstraints());
        panelContenedor.add(panelCentro,BorderLayout.CENTER);
        setVisible(true);
    }

    private void crearMenuPrincipal(){
        panelPrincipal= new JPanel(new GridLayout(4,1,20,50));
        panelPrincipal.setPreferredSize(new Dimension(350,300));
        panelPrincipal.setOpaque(false);

        JButton btnAlumnos= new JButton("Gestión de Alumnos");
        JButton btnLibros= new JButton("Gestión de Libros");
        JButton btnPrestamos= new JButton("Gestión de Préstamos");
        JButton btnSalir= new JButton("Salir");

        Font fuenteBotones = new Font("Segoe UI",Font.BOLD,18);
        Color colorFondoBoton= new Color(23, 162, 184);
        Color colorTexto= Color.WHITE;

        btnAlumnos.setFont(fuenteBotones);
        btnAlumnos.setForeground(colorTexto);
        btnAlumnos.setBackground(colorFondoBoton);
        btnAlumnos.setFocusPainted(false);

        btnLibros.setFont(fuenteBotones);
        btnLibros.setForeground(colorTexto);
        btnLibros.setBackground(colorFondoBoton);
        btnLibros.setFocusPainted(false);

        btnPrestamos.setFont(fuenteBotones);
        btnPrestamos.setForeground(colorTexto);
        btnPrestamos.setBackground(colorFondoBoton);
        btnPrestamos.setFocusPainted(false);

        btnSalir.setFont(fuenteBotones);
        btnSalir.setForeground(colorTexto);
        btnSalir.setBackground(colorFondoBoton);
        btnSalir.setFocusPainted(false);

        panelPrincipal.add(btnAlumnos);
        panelPrincipal.add(btnLibros);
        panelPrincipal.add(btnPrestamos);
        panelPrincipal.add(btnSalir);

        btnAlumnos.addActionListener(e -> {
            Alumnos pantallaAlumnos= new Alumnos(MenuPrincipal.this);
            cambiarPantalla(pantallaAlumnos);

        });

        btnLibros.addActionListener(e-> {

        });

        btnPrestamos.addActionListener(e -> {

        });



        btnSalir.addActionListener(e -> {
            System.exit(0);
        });




    }
    public void cambiarPantalla(JPanel nuevaPantalla){
        panelContenedor.removeAll();
        panelContenedor.add(nuevaPantalla,BorderLayout.CENTER);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    public void mostrarMenuInicio(){
        panelContenedor.add(panelPrincipal,BorderLayout.CENTER);
        panelContenedor.revalidate();
        panelContenedor.repaint();
    }

    public static void main(String[] args){
        MenuPrincipal ventana= new MenuPrincipal();

    }


}
