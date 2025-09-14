package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private JScrollPane scrollPane;
	private JTextField txtAreaP;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextField txtNom;
	private JTextField txtCod;
	private JTextField txtPrecio;
	private JTextField txtCant;

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
		btnRegresar.setBounds(10, 316, 105, 27);
		contentPane.add(btnRegresar);
		
		JButton btnNewButton = new JButton("Reporte Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(10, 114, 131, 21);
		contentPane.add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(190, 114, 231, 199);
		contentPane.add(scrollPane);
		
		txtAreaP = new JTextField();
		scrollPane.setViewportView(txtAreaP);
		txtAreaP.setColumns(10);
		
		lblNewLabel = new JLabel("Nombre :");
		lblNewLabel.setBounds(35, 53, 45, 13);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Codigo :");
		lblNewLabel_1.setBounds(35, 76, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Precio :");
		lblNewLabel_2.setBounds(210, 53, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad :");
		lblNewLabel_3.setBounds(210, 76, 70, 13);
		contentPane.add(lblNewLabel_3);
		
		txtNom = new JTextField();
		txtNom.setBounds(90, 50, 96, 19);
		contentPane.add(txtNom);
		txtNom.setColumns(10);
		
		txtCod = new JTextField();
		txtCod.setBounds(90, 76, 96, 19);
		contentPane.add(txtCod);
		txtCod.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(265, 50, 96, 19);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtCant = new JTextField();
		txtCant.setBounds(265, 73, 96, 19);
		contentPane.add(txtCant);
		txtCant.setColumns(10);

	}

	
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		txtAreaP.setText("");
		
	}
	void Reporte_producto()
	{
		
	}
}
