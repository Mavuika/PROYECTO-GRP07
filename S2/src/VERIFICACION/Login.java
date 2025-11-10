package VERIFICACION;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import CLASE.VRestaurante;

public class Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    private JTextField txtNombre;
    private JPasswordField txtCodigo;
    private JComboBox<String> cbRol;
    private JButton btnIngresar;
    private JButton btnCancelar;
    private JCheckBox chMostrar;
    private JLabel lblMensaje;

    private ServicioAutenticacion servicio;

    public Login() {
        servicio = new ServicioAutenticacion();
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        initComponentes();
    }

    private void initComponentes() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel campos = new JPanel(new GridLayout(4, 2, 6, 6));
        campos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        campos.add(txtNombre);

        campos.add(new JLabel("Código:"));
        txtCodigo = new JPasswordField();
        campos.add(txtCodigo);

        campos.add(new JLabel(""));
        chMostrar = new JCheckBox("Mostrar código");
        chMostrar.addActionListener(e -> {
            if (chMostrar.isSelected()) txtCodigo.setEchoChar((char) 0);
            else txtCodigo.setEchoChar('•');
        });
        campos.add(chMostrar);

        campos.add(new JLabel("Cargo / Rol:"));
        cbRol = new JComboBox<>(new String[] {"", "Administrador", "Vendedor", "Almacenero", "Usuario", "Gerente"});
        campos.add(cbRol);

        panel.add(campos, BorderLayout.CENTER);

        lblMensaje = new JLabel(" ");
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje, BorderLayout.NORTH);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnIngresar = new JButton("Ingresar");
        btnCancelar = new JButton("Cancelar");
        botones.add(btnCancelar);
        botones.add(btnIngresar);
        panel.add(botones, BorderLayout.SOUTH);

        btnIngresar.addActionListener(this);
        btnCancelar.addActionListener(e -> System.exit(0));

        getRootPane().setDefaultButton(btnIngresar);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIngresar) {
            intentarIngreso();
        }
    }

    private void intentarIngreso() {
        String nombre = txtNombre.getText().trim();
        String codigo = new String(txtCodigo.getPassword()).trim();
        String rol = (String) cbRol.getSelectedItem();

        if (nombre.isEmpty()) {
            mostrarMensaje("Ingrese su nombre.");
            txtNombre.requestFocusInWindow();
            return;
        }
        if (codigo.isEmpty()) {
            mostrarMensaje("Ingrese el código.");
            txtCodigo.requestFocusInWindow();
            return;
        }
        if (rol == null || rol.isEmpty()) {
            mostrarMensaje("Seleccione su cargo.");
            cbRol.requestFocusInWindow();
            return;
        }

        try {
            Usuario u = servicio.autenticar(nombre, codigo, rol);
            if (u != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
                VRestaurante vr = new VRestaurante();
                vr.setLocationRelativeTo(null);
                vr.setVisible(true);
                dispose();
            } else {
                mostrarMensaje("Código o rol incorrecto.");
            }
        } catch (IllegalArgumentException ex) {
            mostrarMensaje(ex.getMessage());
        } finally {
            java.util.Arrays.fill(txtCodigo.getPassword(), '\0');
        }
    }

    private void mostrarMensaje(String msg) {
        lblMensaje.setText(msg);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login l = new Login();
            l.setVisible(true);
        });
    }
}
