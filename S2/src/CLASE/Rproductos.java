package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Arreglo.Producto_Arreglo;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Rproductos extends JFrame implements ActionListener {

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
		setResizable(false);
		setTitle("REGISTRO DE PRODUCTOS");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 498, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRestaurante newframe = new VRestaurante();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(10, 290, 167, 40);
		contentPane.add(btnRegresar);
		
		JButton btnNewButton = new JButton("Reporte Producto");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(10, 114, 167, 21);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(60, 25, 55, 19);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Código:");
		lblNewLabel_1.setBounds(60, 68, 59, 19);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(266, 25, 45, 19);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cantidad:");
		lblNewLabel_3.setBounds(266, 68, 58, 19);
		contentPane.add(lblNewLabel_3);
		
		txtcant = new JTextField();
		txtcant.setBounds(331, 68, 96, 19);
		contentPane.add(txtcant);
		txtcant.setColumns(10);
		
		txtnom = new JTextField();
		txtnom.setBounds(116, 24, 96, 19);
		contentPane.add(txtnom);
		txtnom.setColumns(10);
		
		txtprecio = new JTextField();
		txtprecio.setBounds(331, 25, 96, 19);
		contentPane.add(txtprecio);
		txtprecio.setColumns(10);
		
		txtcod = new JTextField();
		txtcod.setBounds(116, 68, 96, 19);
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
				try {
					Producto pro=new Producto(LeerNombre(), LeerCodigo(), LeerPrecio(), LeerCantidad());
					pa.Adicionar(pro);
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(this, "Ingrese el producto");
				}
			}
		});
		btnAdicionar.setBounds(10, 146, 167, 23);
		contentPane.add(btnAdicionar);
		
		btnBuscar = new JButton("Buscar Producto");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtS.setText("");
					Producto pro=pa.Buscar(LeerCodigo());
					if(pro!=null)
					{
						txtS.append("Nombre\tCódigo○\tPrecio\tCantidad");
						txtS.append("\n"+pro.getNombre()+"\t"+pro.getCodigo()+"\t"+pro.getPrecio()+"\t"+pro.getCantidad());	
					}
					else JOptionPane.showMessageDialog(null, "Producto no encontrado");
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(this, "Ingrese el producto");
				}
			}
			
		});
		btnBuscar.setBounds(10, 179, 167, 20);
		contentPane.add(btnBuscar);
		
		btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(10, 210, 167, 23);
		contentPane.add(btnEliminar);
		
		btnModificar = new JButton("Modificar Producto");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(10, 245, 167, 23);
		contentPane.add(btnModificar);

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
	Producto_Arreglo pa=new Producto_Arreglo();
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JButton btnModificar;
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try {
			txtS.setText("");
			Reporte_producto();
			txtS.append("\nCantidad de productos registrados: "+pa.Tamaño());
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");
		}
	}
	void Reporte_producto()
	{
		txtS.append("Nombre\tCódigo○\tPrecio\tCantidad");
		for(int i=0; i<pa.Tamaño(); i++)
		{
			txtS.append("\n"+pa.Obtener(i).getNombre()+"\t"+pa.Obtener(i).getCodigo()+"\t"+pa.Obtener(i).getPrecio()+"\t"+pa.Obtener(i).getCantidad());
		}
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnNewButton_1_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_1_actionPerformed(ActionEvent e) {
		try {
			Producto pro=pa.Buscar(LeerCodigo());
			if(pro!=null)
			{
				pa.Eliminar(pro);
			}
			else JOptionPane.showMessageDialog(null, "Producto no encontrado");
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");
		}
	}
	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		try {
			Producto pro=pa.Buscar(LeerCodigo());
			if(pro!=null)
			{
				pro.setNombre(LeerNombre());
				pro.setPrecio(LeerPrecio());
				pro.setCantidad(LeerCantidad());
				Reporte_producto();
			}
			else JOptionPane.showMessageDialog(null, "Producto no encontrado");
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");
		}
	}
}
