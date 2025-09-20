package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Rventas extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtCantidad;
	private JTextField fieldVentas;
	private JButton btnRegresar;
	private JButton btnRegistrarVenta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rventas frame = new Rventas();
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
	public Rventas() {
		setResizable(false);
		setTitle("REGISTRO DE VENTAS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(36, 44, 55, 19);
		contentPane.add(lblNewLabel);
		
		txtNom = new JTextField();
		txtNom.setBounds(96, 44, 96, 19);
		txtNom.setColumns(10);
		contentPane.add(txtNom);
		
		txtCantidad = new JTextField();
		txtCantidad.setBounds(96, 74, 96, 19);
		txtCantidad.setColumns(10);
		contentPane.add(txtCantidad);
		
		JLabel lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(36, 74, 58, 19);
		contentPane.add(lblNewLabel_3);
		
		fieldVentas = new JTextField();
		fieldVentas.setBounds(26, 104, 378, 165);
		fieldVentas.setColumns(10);
		contentPane.add(fieldVentas);
		
		btnRegistrarVenta = new JButton("Registrar Venta");
		btnRegistrarVenta.addActionListener(this);
		btnRegistrarVenta.setBounds(257, 50, 147, 30);
		contentPane.add(btnRegistrarVenta);
		
		btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(this);
		btnRegresar.setBounds(26, 280, 105, 27);
		contentPane.add(btnRegresar);
		
	

	}
	void Reporte()
	{
		
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrarVenta) {
			do_btnRegistrarVenta_actionPerformed(e);
		}
		if (e.getSource() == btnRegresar) {
			do_btnRegresar_actionPerformed(e);
		}
	}
	protected void do_btnRegresar_actionPerformed(ActionEvent e) {
		
		VRestaurante newframe = new VRestaurante();
		newframe.setVisible(true);
		dispose();
	}
	protected void do_btnRegistrarVenta_actionPerformed(ActionEvent e) {
		
		
	}
}
