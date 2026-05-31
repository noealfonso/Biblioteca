package biblioteca.vista.gui;
import biblioteca.controlador.LibroControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Libros extends JPanel {
    private MenuPrincipal ventanaPrincipal;
    private JPanel panelCentro;

    private JTextField campoIsbn;
    private JTextField campoTitulo;
    private JTextField campoAutor;
    private JTextField campoAnio;
    private JTextField campoBorrar;

    public Libros(MenuPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        setLayout(new BorderLayout()); // Layout Manager Principal

        crearRegionNorte();
        crearRegionSur();
        crearRegionOeste();
        crearRegionCentro();
    }

    private void crearRegionNorte(){
        JLabel titulo = new JLabel("Gestión de Libros", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,24));
        add(titulo, BorderLayout.NORTH);
    }
    private void crearRegionSur(){
        JPanel panelBotonAbajo= new JPanel();
        panelBotonAbajo.setOpaque(false);
        JButton botonVolverMenuPrincipal = new JButton("Volver a Menú");

        botonVolverMenuPrincipal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonVolverMenuPrincipal.setForeground(Color.WHITE);
        botonVolverMenuPrincipal.setBackground(new Color(23, 162, 184));
        botonVolverMenuPrincipal.setFocusPainted(false);

        panelBotonAbajo.add(botonVolverMenuPrincipal);
        add(panelBotonAbajo, BorderLayout.SOUTH);

        botonVolverMenuPrincipal.addActionListener(e -> {
            ventanaPrincipal.mostrarMenuInicio();
        });
    }
    private void crearRegionOeste(){
        JPanel panelMenuLibro = new JPanel(new GridLayout(4, 1, 1, 40));
        panelMenuLibro.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton botonCrearLibro = new JButton("Crear Libro");
        JButton botonEditar = new JButton("Editar Libro");
        JButton botonBorrar = new JButton("Borrar Libro");
        JButton botonListar = new JButton("Listar Libros");

        Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 18);
        Color colorFondoBoton = new Color(23, 162, 184);
        Color colorTexto = Color.WHITE;

        JButton[] botones={botonCrearLibro, botonEditar, botonBorrar, botonListar};
        for(JButton boton: botones){
            boton.setFont(fuenteBotones);
            boton.setForeground(colorTexto);
            boton.setBackground(colorFondoBoton);
            boton.setFocusPainted(false);
            panelMenuLibro.add(boton);
        }
        add(panelMenuLibro, BorderLayout.WEST);
        botonCrearLibro.addActionListener(e -> cambiarPanel(crearFormularioLibro()));
        botonBorrar.addActionListener(e -> cambiarPanel(borrarLibroPanel()));
        botonListar.addActionListener(e -> cambiarPanel(listarLibrosPanel()));

    }
    private void crearRegionCentro(){
        panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        add(panelCentro, BorderLayout.CENTER);
    }
    private void cambiarPanel(JPanel nuevoPanel){
        panelCentro.removeAll();
        panelCentro.add(nuevoPanel, BorderLayout.CENTER);
        panelCentro.revalidate();
        panelCentro.repaint();
    }

    private JPanel crearFormularioLibro(){

        JPanel panelDatos = new JPanel(new GridLayout(5, 1, 0, 15));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        Font fuenteEtiquetas = new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCampos = new Font("Segoe UI", Font.PLAIN, 14);
        int anchoCampos = 25;

        // ISBN
        JPanel filaIsbn = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel etiquetaIsbn = new JLabel("ISBN / Código:");
        etiquetaIsbn.setFont(fuenteEtiquetas);
        etiquetaIsbn.setPreferredSize(new Dimension(250, 30));
        campoIsbn = new JTextField(anchoCampos);
        campoIsbn.setFont(fuenteCampos);
        filaIsbn.add(etiquetaIsbn); filaIsbn.add(campoIsbn);

        // Título
        JPanel filaTitulo = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel etiquetaTitulo = new JLabel("Título del Libro:");
        etiquetaTitulo.setFont(fuenteEtiquetas);
        etiquetaTitulo.setPreferredSize(new Dimension(250, 30));
        campoTitulo = new JTextField(anchoCampos);
        campoTitulo.setFont(fuenteCampos);
        filaTitulo.add(etiquetaTitulo); filaTitulo.add(campoTitulo);

        // Autor
        JPanel filaAutor = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel etiquetaAutor = new JLabel("Autor:");
        etiquetaAutor.setFont(fuenteEtiquetas);
        etiquetaAutor.setPreferredSize(new Dimension(250, 30));
        campoAutor = new JTextField(anchoCampos);
        campoAutor.setFont(fuenteCampos);
        filaAutor.add(etiquetaAutor); filaAutor.add(campoAutor);

        // Año
        JPanel filaAnio = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JLabel etiquetaAnio = new JLabel("Año de Publicación:");
        etiquetaAnio.setFont(fuenteEtiquetas);
        etiquetaAnio.setPreferredSize(new Dimension(250, 30));
        campoAnio = new JTextField(anchoCampos);
        campoAnio.setFont(fuenteCampos);
        filaAnio.add(etiquetaAnio); filaAnio.add(campoAnio);

        JPanel filaBoton = new JPanel(new FlowLayout());
        JButton btnGuardar = new JButton("Guardar Libro");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btnGuardar.setBackground(new Color(23, 162, 184));
        btnGuardar.setForeground(Color.WHITE);
        filaBoton.add(btnGuardar);

        btnGuardar.addActionListener(e -> activarGuardarLibro());

        panelDatos.add(filaIsbn);
        panelDatos.add(filaTitulo);
        panelDatos.add(filaAutor);
        panelDatos.add(filaAnio);
        panelDatos.add(filaBoton);

        return panelDatos;
    }
    private void activarGuardarLibro(){
        if (campoIsbn.getText().isEmpty() || campoTitulo.getText().isEmpty() || campoAutor.getText().isEmpty() || campoAnio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, "El libro ha sido creado con éxito", "Crear Libro", JOptionPane.INFORMATION_MESSAGE);
    }
    private JPanel borrarLibroPanel(){
        JPanel panelPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        JPanel panelDatos = new JPanel(new GridLayout(3, 1, 0, 20));
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel etiquetaBorrar = new JLabel("Ingrese el ISBN del libro a borrar:", SwingConstants.CENTER);
        etiquetaBorrar.setFont(new Font("Segoe UI", Font.BOLD, 17));

        JPanel filaCampo = new JPanel();
        campoBorrar = new JTextField(25);
        campoBorrar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        filaCampo.add(campoBorrar);

        JPanel filaBoton = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton botonBorrar = new JButton("Borrar Libro");
        botonBorrar.setFont(new Font("Segoe UI", Font.BOLD, 17));
        botonBorrar.setBackground(new Color(23, 162, 184));
        botonBorrar.setForeground(Color.WHITE);
        filaBoton.add(botonBorrar);

        panelDatos.add(etiquetaBorrar);
        panelDatos.add(filaCampo);
        panelDatos.add(filaBoton);
        panelPrincipal.add(panelDatos);

        return panelPrincipal;
    }
    private JPanel listarLibrosPanel(){
        JPanel panelListar = new JPanel(new BorderLayout());
        panelListar.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        String[] columnas = {"ISBN", "Título", "Autor", "Año"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tablaLibros = new JTable(modeloTabla);
        panelListar.add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        return panelListar;
    }



}
