package biblioteca.vista.gui;
import biblioteca.controlador.PrestamoControlador;
import biblioteca.controlador.LibroControlador;
import biblioteca.controlador.AlumnoControlador;
import biblioteca.vista.ConversionFecha;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Prestamos extends JPanel{
    private MenuPrincipal ventanaPrincipal;
    private JComboBox<String> comboUsuarios;
    private JList<String> listaLibrosDisponibles;
    private JTextField campoFiltroFecha;
    private JTextField campoFiltroUsuario;
    private JTextField campoFechaPrestamo;
    private JTable tablaPrestamos;
    private DefaultTableModel modeloTabla;

    public Prestamos (MenuPrincipal ventanaPrincipal){
        this.ventanaPrincipal= ventanaPrincipal;
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gestión de Préstamos",SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD,24));
        titulo.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        add(titulo, BorderLayout.NORTH);

        JTabbedPane pestañas = new JTabbedPane();
        pestañas.setFont(new Font("Segoe UI", Font.PLAIN,14));
        pestañas.addTab("Registrar Préstamo", crearPanelRegistro());
        pestañas.addTab("Ver préstamos y filtros", crearPanelListarYFiltrar());
        pestañas.addTab("Reportes",crearPanelReportes());

        add(pestañas, BorderLayout.CENTER);

        JPanel panelSur = new JPanel();
        JButton botonVolver = new JButton("Volver a Menú");
        botonVolver.setFont(new Font("Segoe UI", Font.BOLD,16));
        botonVolver.setForeground(Color.WHITE);
        botonVolver.setBackground(new Color(23,162,184));

        botonVolver.addActionListener( e -> ventanaPrincipal.mostrarMenuInicio());
        panelSur.add(botonVolver);
        add(panelSur, BorderLayout.SOUTH);

    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Seleccionar Usuario/Alumno:"), gbc);

        comboUsuarios = new JComboBox<>(new String[]{"-- Seleccione un Alumno --"});
        gbc.gridx = 1;
        panel.add(comboUsuarios, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Seleccionar Libros (Mantener Ctrl para varios):"), gbc);

        String[] librosSimulados = {"Libro de Ejemplo 1", "Libro de Ejemplo 2"};
        listaLibrosDisponibles = new JList<String>(librosSimulados);
        listaLibrosDisponibles.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane scrollLibros = new JScrollPane(listaLibrosDisponibles);
        scrollLibros.setPreferredSize(new Dimension(200, 80));
        gbc.gridx = 1;
        panel.add(scrollLibros, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Fecha de Préstamo (dd/mm/aaaa):"), gbc);

        campoFechaPrestamo = new JTextField(15);
        gbc.gridx = 1;
        panel.add(campoFechaPrestamo, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton botonGuardar = new JButton("Confirmar Préstamo Múltiple");
        botonGuardar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botonGuardar.setBackground(new Color(23, 162, 184));
        botonGuardar.setForeground(Color.WHITE);
        panel.add(botonGuardar, gbc);

        return panel;
    }
    private JPanel crearPanelListarYFiltrar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        panelFiltros.add(new JLabel("Filtrar por Fecha:"));
        campoFiltroFecha = new JTextField(10);
        panelFiltros.add(campoFiltroFecha);

        panelFiltros.add(new JLabel("Filtrar por Usuario (C.I.):"));
        campoFiltroUsuario = new JTextField(10);
        panelFiltros.add(campoFiltroUsuario);

        JButton btnFiltrar = new JButton("Aplicar Filtros");
        btnFiltrar.setBackground(new Color(23, 162, 184));
        btnFiltrar.setForeground(Color.WHITE);
        panelFiltros.add(btnFiltrar);

        panel.add(panelFiltros, BorderLayout.NORTH);

        String[] columnas = {"ID Préstamo", "Usuario / Alumno", "Libros Prestados", "Fecha Préstamo", "Estado"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPrestamos = new JTable(modeloTabla);
        panel.add(new JScrollPane(tablaPrestamos), BorderLayout.CENTER);

        return panel;
    }
    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JButton btnVencidos = new JButton("Generar Informe de Préstamos Vencidos");
        JButton btnAnulados = new JButton("Generar Informe de Ventas/Préstamos Anulados");
        JButton btnLibrosPorUsuario = new JButton("Ver Libros Prestados por Usuario");

        panel.add(btnVencidos);
        panel.add(btnAnulados);
        panel.add(btnLibrosPorUsuario);

        return panel;
    }

}
