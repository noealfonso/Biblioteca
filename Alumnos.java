package biblioteca.vista.gui;

import biblioteca.controlador.AlumnoControlador;
import biblioteca.vista.consola.ConversionFecha;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Alumnos extends JPanel {
    private AlumnoControlador control= new AlumnoControlador();;
    private MenuPrincipal ventanaPrincipal;
    private JPanel panelCentro;
    private JTextField campoCi;
    private JTextField campoNombre;
    private JTextField campoCorreo;
    private JTextField campoTelefono;
    private JTextField campoFecha;
    private JTextField campoFacu;
    private JTextField campoBorrar;

    public Alumnos(MenuPrincipal ventanaPrincipal){
        this.ventanaPrincipal= ventanaPrincipal;
        setLayout(new BorderLayout());
        crearRegionNorte();
        crearRegionSur();
        crearRegionOeste();
        crearRegionCentro();


    }

    private void crearRegionNorte(){
        JLabel titulo= new JLabel("Gestión de Alumnos",SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD,24));
        add(titulo,BorderLayout.NORTH);
    }
    private void crearRegionSur(){
        JPanel panelBotonAbajo= new JPanel();
        panelBotonAbajo.setOpaque(false);
        JButton btnVolverMenuPrincipal= new JButton("Volver a Menú");
        Font fuenteBotones = new Font("Segoe UI",Font.BOLD,16);
        Color colorFondoBoton= new Color(23, 162, 184);
        Color colorTexto= Color.WHITE;

        btnVolverMenuPrincipal.setFont(fuenteBotones);
        btnVolverMenuPrincipal.setForeground(colorTexto);
        btnVolverMenuPrincipal.setBackground(colorFondoBoton);
        btnVolverMenuPrincipal.setFocusPainted(false);

        panelBotonAbajo.add(btnVolverMenuPrincipal);
        add(panelBotonAbajo,BorderLayout.SOUTH);

        btnVolverMenuPrincipal.addActionListener(e -> {
            ventanaPrincipal.mostrarMenuInicio();
        });

    }

    private void crearRegionOeste(){
        JPanel panelMenuAlumno = new JPanel(new GridLayout(4,1,1,40));
        panelMenuAlumno.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton btnCrearAlumno= new JButton("Crear Alumno");
        JButton btnEditar= new JButton("Editar Alumno");
        JButton btnBorrar= new JButton("Borrar Alumno");
        JButton btnListar= new JButton("Listar Alumnos");

        Font fuenteBotones = new Font("Segoe UI",Font.BOLD,18);
        Color colorFondoBoton= new Color(23, 162, 184);
        Color colorTexto= Color.WHITE;

        btnCrearAlumno.setFont(fuenteBotones);
        btnCrearAlumno.setForeground(colorTexto);
        btnCrearAlumno.setBackground(colorFondoBoton);
        btnCrearAlumno.setFocusPainted(false);

        btnEditar.setFont(fuenteBotones);
        btnEditar.setForeground(colorTexto);
        btnEditar.setBackground(colorFondoBoton);
        btnEditar.setFocusPainted(false);

        btnBorrar.setFont(fuenteBotones);
        btnBorrar.setForeground(colorTexto);
        btnBorrar.setBackground(colorFondoBoton);
        btnBorrar.setFocusPainted(false);

        btnListar.setFont(fuenteBotones);
        btnListar.setForeground(colorTexto);
        btnListar.setBackground(colorFondoBoton);
        btnListar.setFocusPainted(false);

        panelMenuAlumno.add(btnCrearAlumno);
        panelMenuAlumno.add(btnEditar);
        panelMenuAlumno.add(btnBorrar);
        panelMenuAlumno.add(btnListar);

        add(panelMenuAlumno,BorderLayout.WEST);

        btnCrearAlumno.addActionListener(e->{
            cambiarPanel(crearAlumno());
        });

        btnBorrar.addActionListener(e->{
            cambiarPanel(borrarAlumno());
        });


    }
    private void crearRegionCentro(){
        panelCentro=new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        add(panelCentro,BorderLayout.CENTER);


    }

    private JPanel crearAlumno() {
        JPanel panelDatos= new JPanel(new GridLayout(7, 1, 0, 15));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40, 50, 40, 50));

        Font fuenteEtiquetas= new Font("Segoe UI", Font.BOLD, 14);
        Font fuenteCampos= new Font("Segoe UI", Font.PLAIN, 14);
        int anchoCampos= 25;

        JPanel filaCi= new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        filaCi.setOpaque(false);
        JLabel etiquetaCi= new JLabel("Cédula de Identidad: ");
        etiquetaCi.setFont(fuenteEtiquetas);
        etiquetaCi.setPreferredSize(new Dimension(250,30));
        campoCi= new JTextField(anchoCampos);
        campoCi.setFont(fuenteCampos);
        filaCi.add(etiquetaCi);
        filaCi.add(campoCi);


        JPanel filaNombre= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaNombre.setOpaque(false);
        JLabel etiquetaNombre= new JLabel("Nombre Completo:");
        etiquetaNombre.setFont(fuenteEtiquetas);
        etiquetaNombre.setPreferredSize(new Dimension(250, 30));
        campoNombre= new JTextField(anchoCampos);
        campoNombre.setFont(fuenteCampos);
        filaNombre.add(etiquetaNombre);
        filaNombre.add(campoNombre);

        JPanel filaCorreo= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaCorreo.setOpaque(false);
        JLabel etiquetaCorreo= new JLabel("Correo Electrónico:");
        etiquetaCorreo.setFont(fuenteEtiquetas);
        etiquetaCorreo.setPreferredSize(new Dimension(250, 30));
        campoCorreo= new JTextField(anchoCampos);
        campoCorreo.setFont(fuenteCampos);
        filaCorreo.add(etiquetaCorreo);
        filaCorreo.add(campoCorreo);


        JPanel filaTelefono= new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        filaTelefono.setOpaque(false);
        JLabel etiquetaTelefono= new JLabel("Teléfono:");
        etiquetaTelefono.setFont(fuenteEtiquetas);
        etiquetaTelefono.setPreferredSize(new Dimension(250, 30));
        campoTelefono= new JTextField(anchoCampos);
        campoTelefono.setFont(fuenteCampos);
        filaTelefono.add(etiquetaTelefono);
        filaTelefono.add(campoTelefono);

        JPanel filaFecha= new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        filaFecha.setOpaque(false);
        JLabel etiquetaFecha= new JLabel("Fecha de nacimiento (dd/mm/aaaa): ");
        etiquetaFecha.setFont(fuenteEtiquetas);
        etiquetaFecha.setPreferredSize(new Dimension(250,30));
        campoFecha= new JTextField(anchoCampos);
        campoFecha.setFont(fuenteCampos);
        filaFecha.add(etiquetaFecha);
        filaFecha.add(campoFecha);

        JPanel filaFacu= new JPanel(new FlowLayout(FlowLayout.LEFT,10,0));
        filaFacu.setOpaque(false);
        JLabel etiquetaFacu= new JLabel("Facultad: ");
        etiquetaFacu.setFont(fuenteEtiquetas);
        etiquetaFacu.setPreferredSize(new Dimension(250,30));
        campoFacu= new JTextField(anchoCampos);
        campoFacu.setFont(fuenteCampos);
        filaFacu.add(etiquetaFacu);
        filaFacu.add(campoFacu);

        JPanel filaBoton= new JPanel(new FlowLayout());
        filaBoton.setOpaque(false);
        JButton btnGuardar= new JButton("Guardar Alumno");
        btnGuardar.setFont(new Font("Segoe UI",Font.BOLD,15));
        btnGuardar.setBackground(new Color(23, 162, 184));
        btnGuardar.setForeground(Color.WHITE);
        filaBoton.add(btnGuardar);
        btnGuardar.addActionListener(e ->{
            activarGuardarAlumno();
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

    private void limpiarCamposAlumnos(){
        campoCi.setText("");
        campoNombre.setText("");
        campoTelefono.setText("");
        campoCorreo.setText("");
        campoFacu.setText("");
        campoFecha.setText("");

    }
    private void activarGuardarAlumno(){
        int cedula=0;
        try{
            cedula= Integer.parseInt(campoCi.getText());
        }catch(NumberFormatException ae){
            JOptionPane.showMessageDialog(this,"Ingrese un ci válido.","Error",JOptionPane.ERROR_MESSAGE);
            campoCi.setText("");
            return;
        }
        if(control.existe(cedula)){
            JOptionPane.showMessageDialog(this,"El alumno ya existe.","Error",JOptionPane.ERROR_MESSAGE);
            limpiarCamposAlumnos();
            return;
        }

        String nombre=campoNombre.getText();
        String correo=campoCorreo.getText();
        String telefono=campoTelefono.getText();
        String facultad=campoFacu.getText();
        LocalDate fecha=null;


        if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()||facultad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos obligatorios.", "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }


        try {
            fecha = ConversionFecha.convertirFecha(campoFecha.getText());
        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Formato de fecha inválido. Use dd/mm/aaaa", "Error de Fecha", JOptionPane.ERROR_MESSAGE);
            campoFecha.setText("");
            return;
        }

        control.crearAlumno(nombre,cedula,correo,telefono,fecha,facultad);
        JOptionPane.showMessageDialog(this,"El alumno ha sido creado con éxito.","Crear Alumno",JOptionPane.INFORMATION_MESSAGE);
        limpiarCamposAlumnos();

    }
    private void cambiarPanel(JPanel nuevoPanel){
        panelCentro.removeAll();
        panelCentro.add(nuevoPanel,BorderLayout.CENTER);
        panelCentro.revalidate();
        panelCentro.repaint();
    }

    private JPanel borrarAlumno(){
        JPanel panelPrincipal= new JPanel(new FlowLayout(FlowLayout.CENTER,0,100));
        panelPrincipal.setOpaque(false);

        JPanel panelDatos= new JPanel(new GridLayout(3,1,0,20));
        panelDatos.setOpaque(false);
        panelDatos.setBorder(BorderFactory.createEmptyBorder(40,50,40,50));

        JLabel etiquetaBorrar= new JLabel("Ingrese el C.I. del alumno a borrar: ",SwingConstants.CENTER);
        etiquetaBorrar.setFont(new Font("Segoe UI",Font.BOLD,17));
        etiquetaBorrar.setPreferredSize(new Dimension(300,30));

        JPanel filaCampo= new JPanel();
        campoBorrar= new JTextField(25);
        campoBorrar.setFont(new Font("Segoe UI",Font.PLAIN,15));
        panelDatos.add(etiquetaBorrar);
        filaCampo.add(campoBorrar);
        panelDatos.add(filaCampo);

        JPanel filaBoton= new JPanel(new FlowLayout(FlowLayout.CENTER,0,0));
        JButton btnBorrar= new JButton("Borrar Alumno");
        btnBorrar.setFont(new Font("Segoe UI",Font.BOLD,17));
        btnBorrar.setBackground(new Color(23, 162, 184));
        btnBorrar.setForeground(Color.WHITE);
        filaBoton.add(btnBorrar);
        panelDatos.add(filaBoton);
        panelPrincipal.add(panelDatos);

        btnBorrar.addActionListener(e -> {
            activarBorrar();
        });


        return panelPrincipal;
    }

    private void activarBorrar(){
        int cedula=0;
        try{
            cedula=Integer.parseInt(campoBorrar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Ingrese un ci válido.","Error",JOptionPane.ERROR_MESSAGE);
        }
        if(!control.existe(cedula)){
            JOptionPane.showMessageDialog(this,"El alumno no existe.","Error",JOptionPane.ERROR_MESSAGE);
            campoBorrar.setText("");
            return;
        }
        control.borrarAlumno(control.obtenerAlumnoPorCi(cedula));
        JOptionPane.showMessageDialog(this,"El alumno ha sido borrado con éxito.","Crear Alumno",JOptionPane.INFORMATION_MESSAGE);
        campoBorrar.setText("");
    }

}
