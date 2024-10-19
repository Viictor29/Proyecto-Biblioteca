package InterfazGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class vista extends JFrame {
    private JLabel libroL, autorL, usuarioL, opcionL, fechaL;
    private JTextField libro, autor, usuario, fecha;
    private JComboBox<String> opcion;
    private JButton guardar;
    private String[] opciones = {"Insertar", "Actualizar", "Eliminar"};

    public vista(){
        Ventana();
        inicializarComponentes();
    }

    public void Ventana() {
        setTitle("Registro de Libro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout()); // Usamos BorderLayout
        setVisible(true);
    }

    public void inicializarComponentes(){
        // Panel para los campos de texto y etiquetas
        JPanel ventana = new JPanel();
        ventana.setLayout(new GridLayout(6, 2));

        libroL = new JLabel("Libro:");
        autorL = new JLabel("Autor:");
        usuarioL = new JLabel("Usuario:");
        opcionL = new JLabel("Opción:");
        fechaL = new JLabel("Fecha:");
        libro = new JTextField(20);
        autor = new JTextField(20);
        usuario = new JTextField(20);
        fecha = new JTextField(10);
        opcion = new JComboBox<>();
        for (String s: opciones) {
            opcion.addItem(s);
        }


        // Añadir componentes al panel
        ventana.add(libroL);
        ventana.add(libro);
        ventana.add(autorL);
        ventana.add(autor);
        ventana.add(usuarioL);
        ventana.add(usuario);
        ventana.add(fechaL);
        ventana.add(fecha);
        ventana.add(opcionL);
        ventana.add(opcion);

        // Añadir el panel de campos al centro de la ventana
        add(ventana, BorderLayout.CENTER);

        // Panel para el botón
        JPanel botonPanel = new JPanel();
        botonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        guardar = new JButton("Guardar");
        guardar.setHorizontalAlignment(0);

        // Añadir el botón al panel
        botonPanel.add(guardar);

        // Añadir el panel del botón al sur (parte inferior)
        add(botonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void añadirActionListener(ActionListener actionListener){
        guardar.addActionListener(actionListener);
    }

    public String getLibro() {
        return libro.getText();
    }

    public String getAutor() {
        return autor.getText();
    }

    public String getUsuario() {
        return usuario.getText();
    }

    public String getFechaPrestamo() {
        return fecha.getText();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}




