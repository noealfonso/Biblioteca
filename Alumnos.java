package biblioteca.vista.gui;
import biblioteca.controlador.AlumnoControlador;
import biblioteca.vista.consola.ConversionFecha;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;


/**
 * Interfaz gráfica para administrar alumnos.
 * Desde esta ventana se pueden crear, editar, eliminar y listar registros.
 *
 * @author Noelia Alfonso
 */
public class Alumnos extends JPanel {

    private AlumnoControlador control= new AlumnoControlador();
    private MenuPrincipal ventanaPrincipal;
    private JPanel panelCentro;
    private DefaultTableModel modeloTabla;
    private JTextField campoCi;
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JTextField campoFecha;
    private JTextField campoFacu;
    private JTextField campoBorrar;
    private JTextField campoEditar;

    private final Font FUENTE_TITULO= new Font("Segoe UI", Font.BOLD, 24);
    private final Font FUENTE_MEDIANA= new Font("Segoe UI", Font.BOLD, 16); // Para botones, menús y búsquedas
    private final Font FUENTE_FORMULARIO= new Font("Segoe UI", Font.PLAIN, 14); // Estándar para etiquetas y campos
    private final Color COLOR_PRINCIPAL= new Color(23, 162, 184);
    private final Color COLOR_TEXTO_BOTON= Color.WHITE;
    private final Dimension DIMENSION_ETIQUETA= new Dimension(290, 30); // Alineación fija para formularios

    /**
     * Constructor principal de la interfaz de Alumnos.
     * @param ventanaPrincipal Instancia del menú principal para gestionar la navegación.
     */
    public Alumnos(MenuPrincipal ventanaPrincipal){
        this.ventanaPrincipal= ventanaPrincipal;
        setLayout(new BorderLayout());

        crearRegionNorte();
        crearRegionSur();
        crearRegionOeste();
        crearRegionCentro();
    }

    /**
     * Agrega el título principal de la ventana.
     */
    private void crearRegionNorte(){
        JLabel titulo= new JLabel("Gestión de Alumnos", SwingConstants.CENTER);
        titulo.setFont(FUENTE_TITULO);
        add(titulo, BorderLayout.NORTH);
    }

    /**
     * Crea el botón para volver al menú principal.
     */
    private void crearRegionSur(){
        JPanel panelBotonAbajo= new JPanel();
        panelBotonAbajo.setOpaque(false);
        JButton btnVolverMenuPrincipal= new JButton("Volver a Menú");

        btnVolverMenuPrincipal.setFont(FUENTE_MEDIANA);
        btnVolverMenuPrincipal.setForeground(COLOR_TEXTO_BOTON);
        btnVolverMenuPrincipal.setBackground(COLOR_PRINCIPAL);
        btnVolverMenuPrincipal.setFocusPainted(false);

        panelBotonAbajo.add(btnVolverMenuPrincipal);
        add(panelBotonAbajo, BorderLayout.SOUTH);

        btnVolverMenuPrincipal.addActionListener(e -> {
            ventanaPrincipal.mostrarMenuInicio();
        });
    }

    /**
     * Crea el menú lateral con las opciones disponibles.
     */
    private void crearRegionOeste(){
        JPanel panelMenuAlumno= new JPanel(new GridLayout(4, 1, 1, 40));
        panelMenuAlumno.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnCrearAlumno= new JButton("Crear Alumno");
        JButton btnEditar= new JButton("Editar Alumno");
        JButton btnBorrar= new JButton("Borrar Alumno");
        JButton btnListar= new JButton("Listar Alumnos");

        JButton[] botones= {btnCrearAlumno, btnEditar, btnBorrar, btnListar};
        for (JButton btn : botones) {
            btn.setFont(FUENTE_MEDIANA);
            btn.setForeground(COLOR_TEXTO_BOTON);
            btn.setBackground(COLOR_PRINCIPAL);
            btn.setFocusPainted(false);
            panelMenuAlumno.add(btn);
        }

        add(panelMenuAlumno, BorderLayout.WEST);

        btnCrearAlumno.addActionListener(e -> { cambiarPanel(crearAlumno()); });
        btnEditar.addActionListener(e -> { cambiarPanel(editarAlumno()); });
        btnBorrar.addActionListener(e -> { cambiarPanel(borrarAlumno()); });
        btnListar.addActionListener(e -> { cambiarPanel(listarAlumnos()); });
    }

    /**
     * Inicializa el panel central donde se mostrarán las distintas vistas.
     */
    private void crearRegionCentro(){
        panelCentro= new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        add(panelCentro, BorderLayout.CENTER);
    }

    /**
     * Crea el formulario para registrar un nuevo alumno.
     * @return panel con los campos de carga.
     */
    private JPanel crearAlumno() {
        JPanel panelDatos= new JPanel(new GridLayout(7, 1, 0, 15));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        int anchoCampos= 25;

        JPanel filaCi= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaCi.setOpaque(false);
        JLabel etiquetaCi= new JLabel("Cédula de Identidad: ");
        etiquetaCi.setFont(FUENTE_FORMULARIO);
        etiquetaCi.setPreferredSize(DIMENSION_ETIQUETA);
        campoCi= new JTextField(anchoCampos);
        campoCi.setFont(FUENTE_FORMULARIO);
        filaCi.add(etiquetaCi);
        filaCi.add(campoCi);

        JPanel filaNombre= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaNombre.setOpaque(false);
        JLabel etiquetaNombre= new JLabel("Nombre Completo:");
        etiquetaNombre.setFont(FUENTE_FORMULARIO);
        etiquetaNombre.setPreferredSize(DIMENSION_ETIQUETA);
        campoNombre= new JTextField(anchoCampos);
        campoNombre.setFont(FUENTE_FORMULARIO);
        filaNombre.add(etiquetaNombre);
        filaNombre.add(campoNombre);

        JPanel filaCorreo= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaCorreo.setOpaque(false);
        JLabel etiquetaCorreo= new JLabel("Correo Electrónico:");
        etiquetaCorreo.setFont(FUENTE_FORMULARIO);
        etiquetaCorreo.setPreferredSize(DIMENSION_ETIQUETA);
        campoCorreo= new JTextField(anchoCampos);
        campoCorreo.setFont(FUENTE_FORMULARIO);
        filaCorreo.add(etiquetaCorreo);
        filaCorreo.add(campoCorreo);

        JPanel filaTelefono= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaTelefono.setOpaque(false);
        JLabel etiquetaTelefono= new JLabel("Teléfono:");
        etiquetaTelefono.setFont(FUENTE_FORMULARIO);
        etiquetaTelefono.setPreferredSize(DIMENSION_ETIQUETA);
        campoTelefono= new JTextField(anchoCampos);
        campoTelefono.setFont(FUENTE_FORMULARIO);
        filaTelefono.add(etiquetaTelefono);
        filaTelefono.add(campoTelefono);

        JPanel filaFecha= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaFecha.setOpaque(false);
        JLabel etiquetaFecha= new JLabel("Fecha de nacimiento (dd/mm/aaaa): ");
        etiquetaFecha.setFont(FUENTE_FORMULARIO);
        etiquetaFecha.setPreferredSize(DIMENSION_ETIQUETA);
        campoFecha= new JTextField(anchoCampos);
        campoFecha.setFont(FUENTE_FORMULARIO);
        filaFecha.add(etiquetaFecha);
        filaFecha.add(campoFecha);

        JPanel filaFacu= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaFacu.setOpaque(false);
        JLabel etiquetaFacu= new JLabel("Facultad: ");
        etiquetaFacu.setFont(FUENTE_FORMULARIO);
        etiquetaFacu.setPreferredSize(DIMENSION_ETIQUETA);
        campoFacu= new JTextField(anchoCampos);
        campoFacu.setFont(FUENTE_FORMULARIO);
        filaFacu.add(etiquetaFacu);
        filaFacu.add(campoFacu);

        JPanel filaBoton= new JPanel(new FlowLayout());
        filaBoton.setOpaque(false);
        JButton btnGuardar= new JButton("Guardar Alumno");
        btnGuardar.setFont(FUENTE_MEDIANA);
        btnGuardar.setBackground(COLOR_PRINCIPAL);
        btnGuardar.setForeground(COLOR_TEXTO_BOTON);
        filaBoton.add(btnGuardar);
        btnGuardar.addActionListener(e -> { activarGuardarAlumno(); });

        panelDatos.add(filaCi);
        panelDatos.add(filaNombre);
        panelDatos.add(filaCorreo);
        panelDatos.add(filaTelefono);
        panelDatos.add(filaFecha);
        panelDatos.add(filaFacu);
        panelDatos.add(filaBoton);

        return panelDatos;
    }

    /**
     * Limpia todos los campos del formulario.
     */
    private void limpiarCamposAlumnos(){
        campoCi.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
        campoCorreo.setText("");
        campoFacu.setText("");
        campoFecha.setText("");
    }

    /**
     * Verifica los datos ingresados y registra el alumno.
     */
    private void activarGuardarAlumno(){
        int cedula= 0;
        try{
            cedula= Integer.parseInt(campoCi.getText());
        }catch(NumberFormatException ae){
            JOptionPane.showMessageDialog(this, "Ingrese un ci válido.", "Error", JOptionPane.ERROR_MESSAGE);
            campoCi.setText("");
            return;
        }
        if(control.existe(cedula)){
            JOptionPane.showMessageDialog(this, "El alumno ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposAlumnos();
            return;
        }

        String nombre= campoNombre.getText();
        String correo= campoCorreo.getText();
        String telefono= campoTelefono.getText();
        String facultad= campoFacu.getText();
        LocalDate fecha= null;

        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty() || facultad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            fecha= ConversionFecha.convertirFecha(campoFecha.getText());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/mm/aaaa", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
            campoFecha.setText("");
            return;
        }

        control.crearAlumno(nombre, cedula, correo, telefono, fecha, facultad);
        JOptionPane.showMessageDialog(this, "El alumno ha sido creado con éxito.", "Crear Alumno", JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposAlumnos();
    }

    /**
     * Cambia el contenido mostrado en el panel central.
     * @param nuevoPanel panel que se mostrará.
     */
    private void cambiarPanel(JPanel nuevoPanel){
        panelCentro.removeAll();
        panelCentro.add(nuevoPanel, BorderLayout.CENTER);
        panelCentro.revalidate();
        panelCentro.repaint();
    }

    /**
     * Crea la pantalla para buscar un alumno y eliminarlo.
     * @return panel de eliminación.
     */
    private JPanel borrarAlumno(){
        JPanel panelPrincipal= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        panelPrincipal.setOpaque(false);

        JPanel panelDatos= new JPanel(new GridLayout(3, 1, 0, 20));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel etiquetaBorrar= new JLabel("Ingrese el C.I. del alumno a borrar: ", SwingConstants.CENTER);
        etiquetaBorrar.setFont(FUENTE_MEDIANA);
        etiquetaBorrar.setPreferredSize(DIMENSION_ETIQUETA);

        JPanel filaCampo= new JPanel();
        campoBorrar= new JTextField(25);
        campoBorrar.setFont(FUENTE_FORMULARIO);
        panelDatos.add(etiquetaBorrar);
        filaCampo.add(campoBorrar);
        panelDatos.add(filaCampo);

        JPanel filaBoton= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton btnBorrar= new JButton("Borrar Alumno");
        btnBorrar.setFont(FUENTE_MEDIANA);
        btnBorrar.setBackground(COLOR_PRINCIPAL);
        btnBorrar.setForeground(COLOR_TEXTO_BOTON);
        filaBoton.add(btnBorrar);
        panelDatos.add(filaBoton);
        panelPrincipal.add(panelDatos);

        btnBorrar.addActionListener(e -> {
            activarBorrar();
        });

        return panelPrincipal;
    }

    /**
     * Elimina un alumno después de confirmar la operación.
     */
    private void activarBorrar(){
        int cedula= 0;
        try{
            cedula= Integer.parseInt(campoBorrar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un ci válido.", "Error", JOptionPane.ERROR_MESSAGE);
            campoBorrar.setText("");
            return;
        }
        if(!control.existe(cedula)){
            JOptionPane.showMessageDialog(this, "El alumno no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            campoBorrar.setText("");
            return;
        }
        String datosAlumno= control.obtenerDatosPorCi(cedula);
        String datos[]= datosAlumno.split(";");
        int respuesta= JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar a " + datos[1] + "?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            control.borrarAlumno(control.obtenerAlumnoPorCi(cedula));
            JOptionPane.showMessageDialog(this, "El alumno ha sido eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            campoBorrar.setText("");
        }
    }

    /**
     * Solicita la cédula del alumno que se desea editar.
     * @return panel de búsqueda.
     */
    private JPanel editarAlumno(){
        JPanel panelPrincipal= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        panelPrincipal.setOpaque(false);
        JPanel panelDatos= new JPanel(new GridLayout(3, 1, 0, 20));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        JLabel etiquetaEditar= new JLabel("Ingrese el C.I. del alumno a editar: ", SwingConstants.CENTER);
        etiquetaEditar.setFont(FUENTE_MEDIANA);
        etiquetaEditar.setPreferredSize(DIMENSION_ETIQUETA);
        panelDatos.add(etiquetaEditar);

        JPanel filaCampo= new JPanel();
        campoEditar= new JTextField(25);
        campoEditar.setFont(FUENTE_FORMULARIO);
        filaCampo.add(campoEditar);
        panelDatos.add(filaCampo);

        JPanel filaBoton= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JButton btnComprobarCi= new JButton("Verificar C.I.");
        btnComprobarCi.setFont(FUENTE_MEDIANA);
        btnComprobarCi.setBackground(COLOR_PRINCIPAL);
        btnComprobarCi.setForeground(COLOR_TEXTO_BOTON);
        filaBoton.add(btnComprobarCi);
        panelDatos.add(filaBoton);
        panelPrincipal.add(panelDatos);

        btnComprobarCi.addActionListener(e -> {
            int cedula= 0;
            try{
                cedula= Integer.parseInt(campoEditar.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un ci válido.", "Error", JOptionPane.ERROR_MESSAGE);
                campoEditar.setText("");
                return;
            }
            if(!control.existe(cedula)){
                JOptionPane.showMessageDialog(this, "El alumno no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                campoEditar.setText("");
                return;
            }
            cambiarPanel(formularioEditar(control.obtenerDatosPorCi(cedula)));
        });

        return panelPrincipal;
    }

    /**
     * Carga los datos actuales del alumno en el formulario de edición.
     * @param datosAlumno datos del alumno separados por ';'.
     * @return formulario con los datos cargados.
     */
    private JPanel formularioEditar(String datosAlumno){
        // Obtiene cada dato del alumno para cargarlo en los campos
        String datos[]= datosAlumno.split(";");
        JPanel panelDatos= new JPanel(new GridLayout(7, 1, 0, 15));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));
        int anchoCampos= 25;

        JPanel filaCi= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaCi.setOpaque(false);
        JLabel etiquetaCi= new JLabel("Cédula de Identidad: ");
        etiquetaCi.setFont(FUENTE_FORMULARIO);
        etiquetaCi.setPreferredSize(DIMENSION_ETIQUETA);
        campoCi= new JTextField(datos[0], anchoCampos);
        campoCi.setFont(FUENTE_FORMULARIO);
        filaCi.add(etiquetaCi);
        filaCi.add(campoCi);

        JPanel filaNombre= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaNombre.setOpaque(false);
        JLabel etiquetaNombre= new JLabel("Nombre Completo:");
        etiquetaNombre.setFont(FUENTE_FORMULARIO);
        etiquetaNombre.setPreferredSize(DIMENSION_ETIQUETA);
        campoNombre= new JTextField(datos[1], anchoCampos);
        campoNombre.setFont(FUENTE_FORMULARIO);
        filaNombre.add(etiquetaNombre);
        filaNombre.add(campoNombre);

        JPanel filaCorreo= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaCorreo.setOpaque(false);
        JLabel etiquetaCorreo= new JLabel("Correo Electrónico:");
        etiquetaCorreo.setFont(FUENTE_FORMULARIO);
        etiquetaCorreo.setPreferredSize(DIMENSION_ETIQUETA);
        campoCorreo= new JTextField(datos[2], anchoCampos);
        campoCorreo.setFont(FUENTE_FORMULARIO);
        filaCorreo.add(etiquetaCorreo);
        filaCorreo.add(campoCorreo);

        JPanel filaTelefono= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaTelefono.setOpaque(false);
        JLabel etiquetaTelefono= new JLabel("Teléfono:");
        etiquetaTelefono.setFont(FUENTE_FORMULARIO);
        etiquetaTelefono.setPreferredSize(DIMENSION_ETIQUETA);
        campoTelefono= new JTextField(datos[3], anchoCampos);
        campoTelefono.setFont(FUENTE_FORMULARIO);
        filaTelefono.add(etiquetaTelefono);
        filaTelefono.add(campoTelefono);

        JPanel filaFecha= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaFecha.setOpaque(false);
        JLabel etiquetaFecha= new JLabel("Fecha de nacimiento (dd/mm/aaaa): ");
        etiquetaFecha.setFont(FUENTE_FORMULARIO);
        etiquetaFecha.setPreferredSize(DIMENSION_ETIQUETA);
        campoFecha= new JTextField(datos[4], anchoCampos);
        campoFecha.setFont(FUENTE_FORMULARIO);
        filaFecha.add(etiquetaFecha);
        filaFecha.add(campoFecha);

        JPanel filaFacu= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaFacu.setOpaque(false);
        JLabel etiquetaFacu= new JLabel("Facultad: ");
        etiquetaFacu.setFont(FUENTE_FORMULARIO);
        etiquetaFacu.setPreferredSize(DIMENSION_ETIQUETA);
        campoFacu= new JTextField(datos[5], anchoCampos);
        campoFacu.setFont(FUENTE_FORMULARIO);
        filaFacu.add(etiquetaFacu);
        filaFacu.add(campoFacu);

        JPanel filaBoton= new JPanel(new FlowLayout());
        filaBoton.setOpaque(false);
        JButton btnGuardarCambio= new JButton("Editar Alumno");
        btnGuardarCambio.setFont(FUENTE_MEDIANA);
        btnGuardarCambio.setBackground(COLOR_PRINCIPAL);
        btnGuardarCambio.setForeground(COLOR_TEXTO_BOTON);
        filaBoton.add(btnGuardarCambio);
        btnGuardarCambio.addActionListener(e -> {
            activarEditarAlumno();
        });

        panelDatos.add(filaCi);
        panelDatos.add(filaNombre);
        panelDatos.add(filaCorreo);
        panelDatos.add(filaTelefono);
        panelDatos.add(filaFecha);
        panelDatos.add(filaFacu);
        panelDatos.add(filaBoton);

        return panelDatos;
    }

    /**
     * Guarda los cambios realizados en el alumno.
     */
    private void activarEditarAlumno(){
        int cedula= 0;
        try{
            cedula= Integer.parseInt(campoCi.getText());
        }catch(NumberFormatException ae){
            JOptionPane.showMessageDialog(this, "Ingrese un ci válido.", "Error", JOptionPane.ERROR_MESSAGE);
            campoCi.setText("");
            return;
        }
        String nombre= campoNombre.getText();
        String correo= campoCorreo.getText();
        String telefono= campoTelefono.getText();
        String facultad= campoFacu.getText();
        LocalDate fecha= null;
        try {
            fecha= ConversionFecha.convertirFecha(campoFecha.getText());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/mm/aaaa", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
            campoFecha.setText("");
            return;
        }
        control.editarAlumno(nombre, cedula, correo, telefono, fecha, facultad);
        JOptionPane.showMessageDialog(this, "El alumno ha sido editado con éxito.", "Editar Alumno", JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposAlumnos();
    }

    /**
     * Muestra el listado de alumnos en una tabla.
     * @return panel con la tabla de alumnos.
     */
    private JPanel listarAlumnos(){
        JPanel panelPrincipal= new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 100));
        panelPrincipal.setOpaque(false);

        JPanel panelDatos= new JPanel(new BorderLayout());
        panelDatos.setOpaque(false);

        JLabel etiquetaListar= new JLabel("Listado de alumnos");
        etiquetaListar.setFont(FUENTE_MEDIANA);
        etiquetaListar.setPreferredSize(DIMENSION_ETIQUETA);
        panelDatos.add(etiquetaListar, BorderLayout.NORTH);

        String[] columnas= {"C.I.", "Nombre", "Correo", "Teléfono", "Fecha Nac.", "Facultad"};
        modeloTabla= new DefaultTableModel(columnas, 0);
        JTable tabla= new JTable(modeloTabla);
        if(control.listarAlumnos().isEmpty()){
            JOptionPane.showMessageDialog(this, "No se ha creado ningún alumno", "Listar Alumnos", JOptionPane.INFORMATION_MESSAGE);
            return panelPrincipal;
        }
        cargarFilasEnTabla(control.listarAlumnos());
        JScrollPane scrollPane= new JScrollPane(tabla);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        panelDatos.add(scrollPane, BorderLayout.CENTER);

        JPanel filaBotones= new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        JButton btnOrdenarPorNombre= new JButton("Ordenar por nombre");
        btnOrdenarPorNombre.setFont(FUENTE_MEDIANA);
        btnOrdenarPorNombre.setBackground(COLOR_PRINCIPAL);
        btnOrdenarPorNombre.setForeground(COLOR_TEXTO_BOTON);
        filaBotones.add(btnOrdenarPorNombre);

        JButton btnOrdenarPorEdad= new JButton("Ordenar por Edad");
        btnOrdenarPorEdad.setFont(FUENTE_MEDIANA);
        btnOrdenarPorEdad.setBackground(COLOR_PRINCIPAL);
        btnOrdenarPorEdad.setForeground(COLOR_TEXTO_BOTON);
        filaBotones.add(btnOrdenarPorEdad);
        panelDatos.add(filaBotones, BorderLayout.SOUTH);
        panelPrincipal.add(panelDatos);

        btnOrdenarPorNombre.addActionListener(e -> { cargarFilasEnTabla(control.ordenarListaPorNombre()); });
        btnOrdenarPorEdad.addActionListener(e -> { cargarFilasEnTabla(control.ordenarListaPorEdad()); });

        return panelPrincipal;
    }

    /**
     * Carga los datos recibidos en la tabla.
     * @param listaAlumnos lista con la información de los alumnos.
     */
    private void cargarFilasEnTabla(List<String> listaAlumnos) {
        // Limpia las filas actuales antes de volver a cargar la tabla
        modeloTabla.setRowCount(0);
        for (String linea : listaAlumnos) {
            // Cada línea representa una fila separada por ';'
            String[] fila= linea.split(";");
            modeloTabla.addRow(fila);
        }
    }
}