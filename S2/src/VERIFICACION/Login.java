package VERIFICACION;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import CLASE.VRestaurante;
import CLASE.VRestauranteAlmacenero;
import CLASE.VRestauranteAuditor;
import CLASE.VRestauranteCajero;
import CLASE.VRestauranteRH;
import CLASE.VRestauranteSupervisor;
import java.awt.Color;

public class Login extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBox;
	private JButton btnCancelar;
	private JButton btnIngresar;
	private JLabel lblRol;
	private JCheckBox chMostrar;
	private JLabel lblCodigo;
	private JPasswordField passwordField;
	private JTextField textField;
	private JLabel lblNombre;
	private JLabel lblMensaje;

	private ServicioAutenticacion servicio;
	private String rol;
	private int intentosFallidos;
	private JLabel textoalerta;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		servicio = new ServicioAutenticacion();
		intentosFallidos = 0;

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"-- seleccione su cargo --", "Admin", "Gerente", "Supervisor", "Cajero", "Almacenero", "Recursos Humanos", "Auditor"}));
		comboBox.setBounds(150, 140, 220, 22);
		contentPane.add(comboBox);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(150, 175, 100, 26);
		btnCancelar.addActionListener(e -> System.exit(0));
		contentPane.add(btnCancelar);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(260, 175, 110, 26);
		btnIngresar.addActionListener(this);
		contentPane.add(btnIngresar);

		lblRol = new JLabel("Cargo / Rol:");
		lblRol.setBounds(60, 140, 90, 20);
		contentPane.add(lblRol);

		chMostrar = new JCheckBox("Mostrar código");
		chMostrar.setBounds(150, 115, 150, 18);
		chMostrar.addActionListener(e -> {
			if (chMostrar.isSelected()) passwordField.setEchoChar((char) 0);
			else passwordField.setEchoChar('•');
		});
		contentPane.add(chMostrar);

		lblCodigo = new JLabel("Código:");
		lblCodigo.setBounds(60, 90, 80, 20);
		contentPane.add(lblCodigo);

		passwordField = new JPasswordField();
		passwordField.setEchoChar('•');
		passwordField.setBounds(150, 90, 220, 20);
		contentPane.add(passwordField);

		textField = new JTextField();
		textField.setBounds(150, 60, 220, 20);
		contentPane.add(textField);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(60, 60, 80, 20);
		contentPane.add(lblNombre);

		lblMensaje = new JLabel(" ");
		lblMensaje.setBounds(60, 25, 310, 20);
		lblMensaje.setForeground(java.awt.Color.RED);
		contentPane.add(lblMensaje);

		getRootPane().setDefaultButton(btnIngresar);
		
		textoalerta = new JLabel("New label");
		textoalerta.setForeground(new Color(255, 0, 0));
		textoalerta.setBounds(150, 212, 188, 14);
		contentPane.add(textoalerta);
		
		btnNewButton = new JButton("Colaboradores");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(300, 238, 134, 23);
		contentPane.add(btnNewButton);
		textoalerta.setVisible(false);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
		if (e.getSource() == btnIngresar) {
			intentarIngreso();
		}
	}

	private void intentarIngreso() {
		String nombre = textField.getText().trim();
		char[] pase = passwordField.getPassword();
		if (nombre.isEmpty()) {
			mostrarMensaje("Ingrese su nombre.");
			textField.requestFocusInWindow();
			return;
		}
		try {
			String codigo = new String(pase).trim();
			String seleccionado = (String) comboBox.getSelectedItem();
			rol = seleccionado;
			if (codigo.isEmpty()) {
				mostrarMensaje("Ingrese el código.");
				passwordField.requestFocusInWindow();
				return;
			}
			if (seleccionado == null || seleccionado.isEmpty()) {
				mostrarMensaje("Seleccione su cargo.");
				comboBox.requestFocusInWindow();
				return;
			}
			Usuario u = servicio.autenticar(nombre, codigo, seleccionado);
			if (u != null) {
				intentosFallidos = 0;
				Sesion.setCurrentUser(u);
				switch (seleccionado) {
				case "Admin":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteFull();
					return;
				case "Gerente":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteFull();
					return;
				case "Supervisor":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteSupervisor();
					return;
				case "Almacenero":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteAlmacenero();
					return;
				case "Cajero":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteCajero();
					return;
				case "Recursos Humanos":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteRecursosHumanos();
					return;
				case "Auditor":
					JOptionPane.showMessageDialog(this, "Bienvenido, " + u.getNombre() + " (" + u.getRol() + ")");
					abrirRestauranteAuditor();
					return;
				default:
					JOptionPane.showMessageDialog(this, "Rol no escogido");
				}
			} else {
				intentosFallidos++;
				Sesion.clear();
				mostrarMensaje("Código o rol incorrecto.");
				verificarIntentos();
			}
		} catch (IllegalArgumentException ex) {
			mostrarMensaje(ex.getMessage());
		} finally {
			if (pase != null) java.util.Arrays.fill(pase, '\0');
			passwordField.setText("");
		}
	}

	private void abrirRestauranteFull() {
		VRestaurante vr = new VRestaurante();
		vr.setLocationRelativeTo(null);
		vr.setVisible(true);
		dispose();
	}
	private void abrirRestauranteSupervisor() {
		VRestauranteSupervisor vrs = new VRestauranteSupervisor();
		vrs.setLocationRelativeTo(null);
		vrs.setVisible(true);
		dispose();
	}
	private void abrirRestauranteCajero() {
		VRestauranteCajero vrc = new VRestauranteCajero();
		vrc.setLocationRelativeTo(null);
		vrc.setVisible(true);
		dispose();
	}
    private void abrirRestauranteAlmacenero() {
    	VRestauranteAlmacenero vra = new VRestauranteAlmacenero();
		vra.setLocationRelativeTo(null);
		vra.setVisible(true);
		dispose();
	}
    private void abrirRestauranteRecursosHumanos() {
    	VRestauranteRH vrrh = new VRestauranteRH();
		vrrh.setLocationRelativeTo(null);
		vrrh.setVisible(true);
		dispose();
	}  
    private void abrirRestauranteAuditor() {
    	VRestauranteAuditor vrau = new VRestauranteAuditor();
		vrau.setLocationRelativeTo(null);
		vrau.setVisible(true);
		dispose();	
	}
	public String AlmacenarRol() {
		return rol;
	}

	private void mostrarMensaje(String msg) {
		lblMensaje.setText(msg);
	}

	private void verificarIntentos() {
		if (intentosFallidos == 1 )	{
			textoalerta.setText("1 intento fallido, 2 restantes");
			textoalerta.setVisible(true);
		}
		else if (intentosFallidos == 2) {
			textoalerta.setText("2 intentos fallidos, 1 restante");
			textoalerta.setVisible(true);
		}
		else if (intentosFallidos >= 3) {
			JOptionPane.showMessageDialog(this, "Usted ha excedido los 3 intentos, intente más tarde.");
			System.exit(0);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		Colaboradores colab = new Colaboradores();
		colab.setLocationRelativeTo(null);
		colab.setVisible(true);
	}
}