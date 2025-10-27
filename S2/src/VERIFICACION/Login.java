package VERIFICACION;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import CLASE.VRestaurante;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Login extends JFrame implements ItemListener {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel_1;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblCdigo;
	private JTextField txtnombre;
	private JTextField txtcode;

	private boolean comprobacion = false;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 189);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel_1 = new JLabel("Ingrese sus datos y luego su cargo");
		lblNewLabel_1.setBounds(10, 11, 294, 14);
		contentPane.add(lblNewLabel_1);
		
		comboBox = new JComboBox();
		comboBox.addItemListener(this);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Administrador", "Vendedor", "Almacenero", "Usuario", "Gerente"}));
		comboBox.setBounds(20, 117, 266, 22);
		contentPane.add(comboBox);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(41, 49, 78, 14);
		contentPane.add(lblNewLabel);
		
		lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(41, 77, 76, 14);
		contentPane.add(lblCdigo);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(129, 46, 86, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtcode = new JTextField();
		txtcode.setColumns(10);
		txtcode.setBounds(129, 74, 86, 20);
		contentPane.add(txtcode);
	}

	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == comboBox) {
			do_comboBox_itemStateChanged(e);
		}
	}
//RECORDATORIO, UN COMBOBOX DISPARA DOS ITEMEVENTS
	protected void do_comboBox_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() != ItemEvent.SELECTED) {
			return;
		}
		if (comprobacion) {
			return;
		}

		int combo = comboBox.getSelectedIndex();

		String codigoTexto = txtcode.getText().trim();
		if (codigoTexto.length() == 0) {
			JOptionPane.showMessageDialog(this, "Por favor ingrese el código primero.");
			return;
		}

		int Codigo;
		try {
			Codigo = Integer.parseInt(codigoTexto);
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "El código debe ser numérico.");
			return;
		}

		switch(combo)
		{
		case 0:
			JOptionPane.showMessageDialog(this, "Porfavor, ingrese un cargo!");
			break;
		case 1://ADMINISTRADOR
			if(Codigo==123)
			{
				JOptionPane.showMessageDialog(this, "Bienvenido, usted acaba de registrarse como Administrador");
				VRestaurante vr = new VRestaurante();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
				return; 
			} else {
				JOptionPane.showMessageDialog(this, "Su código no coincide con el código de acceso para el cargo ADMINISTRADOR");
				comprobacion = true;
				comboBox.setSelectedIndex(-1);   
				txtcode.setText("");
				txtnombre.setText("");
				txtnombre.requestFocusInWindow();
				comprobacion = false;
			}
			break;
		case 2://VENDEDOR
			if(Codigo==234)
			{
				JOptionPane.showMessageDialog(this, "Bienvenido, usted acaba de registrarse como Vendedor");
				VRestaurante vr = new VRestaurante();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Su código no coincide con el código de acceso para el cargo VENDEDOR");
				comprobacion = true;
				comboBox.setSelectedIndex(-1);   
				txtcode.setText("");
				txtnombre.setText("");
				txtnombre.requestFocusInWindow();
				comprobacion = false;
			}
			break;
		case 3://ALMACENERO
			if(Codigo==245)
			{
				JOptionPane.showMessageDialog(this, "Bienvenido, usted acaba de registrarse como Almacenero");
				VRestaurante vr = new VRestaurante();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Su código no coincide con el código de acceso para el cargo ALMACENERO");
				comprobacion = true;
				comboBox.setSelectedIndex(-1);   
				txtcode.setText("");
				txtnombre.setText("");
				txtnombre.requestFocusInWindow();
				comprobacion = false;
			}
			break;
		case 4://USUARIO
			if(Codigo==456)
			{
				JOptionPane.showMessageDialog(this, "Bienvenido, usted acaba de registrarse como Usuario");
				VRestaurante vr = new VRestaurante();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Su código no coincide con el código de acceso para el cargo USUARIO");
				comprobacion = true;
				comboBox.setSelectedIndex(-1);   
				txtcode.setText("");
				txtnombre.setText("");
				txtnombre.requestFocusInWindow();
				comprobacion = false;
			}
			break;
		default://GERENTE
			if(Codigo==567)
			{
				JOptionPane.showMessageDialog(this, "Bienvenido, usted acaba de registrarse como Gerente");
				VRestaurante vr = new VRestaurante();
				vr.setLocationRelativeTo(null);
				vr.setVisible(true);
				dispose();
				return;
			} else {
				JOptionPane.showMessageDialog(this, "Su código no coincide con el código de acceso para el cargo GERENTE");
				comprobacion = true;
				comboBox.setSelectedIndex(-1);   
				txtcode.setText("");
				txtnombre.setText("");
				txtnombre.requestFocusInWindow();
				comprobacion = false;
			}
			break;
		}
	}
}
