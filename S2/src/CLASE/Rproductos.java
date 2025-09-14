package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Arreglo.Producto_Arreglo;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Rproductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtcant;
	private JTextField txtnom;
	private JTextField txtprecio;
	private JTextField txtcod;
	private JScrollPane scrollpane;
	private JTextArea txtS;
	private JButton btnAdicionar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rproductos frame = new Rproductos();
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
	public Rproductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeProductos = new JLabel("REGISTRO DE PRODUCTOS");
		lblRegistroDeProductos.setBounds(25, 10, 173, 33);
		contentPane.add(lblRegistroDeProductos);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRestaurante newframe = new VRestaurante();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(10, 305, 105, 27);
		contentPane.add(btnRegresar);
		
		JButton btnNewButton = new JButton("Reporte Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(10, 114, 147, 21);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(37, 54, 55, 19);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Código:");
		lblNewLabel_1.setBounds(37, 84, 59, 19);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(210, 54, 45, 19);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(210, 84, 58, 19);
		contentPane.add(lblNewLabel_3);
		
		txtcant = new JTextField();
		txtcant.setBounds(275, 84, 96, 19);
		contentPane.add(txtcant);
		txtcant.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setBounds(93, 53, 96, 19);
		contentPane.add(txtnom);
		txtnom.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(275, 54, 96, 19);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		txtcod = new JTextField();
		txtcod.setBounds(93, 84, 96, 19);
		contentPane.add(txtcod);
		txtcod.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(187, 114, 285, 216);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		scrollPane.setViewportView(txtS);
		
		btnAdicionar = new JButton("Adicionar Producto");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAdicionar.setBounds(10, 146, 147, 23);
		contentPane.add(btnAdicionar);

	}
	String LeerNombre()
	{
		return txtnom.getText();
	}
	String LeerCodigo()
	{
		return txtcod.getText();
	}
	double LeerPrecio()
	{
		return Double.parseDouble(txtprecio.getText());
	}
	int LeerCantidad()
	{
		return Integer.parseInt(txtcant.getText());
	}

	protected void do_btnAdicionar_actionPerformed(ActionEvent e) {
		
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		txtS.setText("");
		
	}
	void Reporte_producto()
	{
		txtS.append("\n");
	}
}
