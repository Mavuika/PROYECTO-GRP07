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
		setBounds(100, 100, 608, 458);
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
		btnRegresar.setBounds(10, 323, 167, 40);
		contentPane.add(btnRegresar);
		
		JButton btnReporte = new JButton("Reporte Producto");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnReporte.setBounds(10, 114, 167, 21);
		contentPane.add(btnReporte);
		
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
		
		btnModificar = new JButton("Modificar Producto");
		btnModificar.addActionListener(this);
		btnModificar.setBounds(10, 257, 167, 23);
		contentPane.add(btnModificar);
		
		btnAdicionar_Producto = new JButton("Adicionar Producto");
		btnAdicionar_Producto.addActionListener(this);
		btnAdicionar_Producto.setBounds(10, 163, 167, 21);
		contentPane.add(btnAdicionar_Producto);
		
		btnEliminar = new JButton("Eliminar Producto");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(10, 207, 167, 21);
		contentPane.add(btnEliminar);
		
		btnBuscar = new JButton("Buscar Producto (Codigo)");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(197, 375, 275, 21);
		contentPane.add(btnBuscar);
		
		btnBuscarPre = new JButton("Buscar Producto (Precio)");
		btnBuscarPre.addActionListener(this);
		btnBuscarPre.setBounds(197, 341, 275, 23);
		contentPane.add(btnBuscarPre);

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
	private Producto_Arreglo pa = Producto_Arreglo.getInstance();
	private JButton btnModificar;
	private JButton btnAdicionar_Producto;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JButton btnBuscarPre;
	

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
		
		txtS.append("Nombre\tCódigo\tPrecio\tCantidad");
		for(int i=0; i<pa.Tamaño(); i++)
		{
			txtS.append("\n"+pa.Obtener(i).getNombre()+"\t"+pa.Obtener(i).getCodigo()+"\t"+pa.Obtener(i).getPrecio()+"\t"+pa.Obtener(i).getCantidad());
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBuscarPre) {
			do_btnBuscarPre_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnAdicionar_Producto) {
			do_btnAdicionar_Producto_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
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
	

	protected void do_btnAdicionar_Producto_actionPerformed(ActionEvent e) {
		try {
			Producto proc=pa.Buscar(LeerCodigo());
			if(proc==null)
			{		
				proc=new Producto(LeerNombre(), LeerCodigo(), LeerPrecio(), LeerCantidad());
				pa.Adicionar(proc);
				JOptionPane.showMessageDialog(null, "Producto agregado con éxito");
			}
			else JOptionPane.showMessageDialog(null, "Producto repetido, ingrese otro porfavor");
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");

	
	}
}	

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
	
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
	

	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		try {
			txtS.setText("");
			Producto pro=pa.Buscar(LeerCodigo());
			if(pro!=null)
			{
				txtS.append("\tNombre\tCódigo○\tPrecio\tCantidad");
				txtS.append("\n"+pro.getNombre()+"\t"+pro.getCodigo()+"\t"+pro.getPrecio()+"\t"+pro.getCantidad());	
			}
			else JOptionPane.showMessageDialog(null, "Producto no encontrado");
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");
		}			}

	protected void do_btnBuscarPre_actionPerformed(ActionEvent e) {
		try {
			txtS.setText("");
			Producto pro=pa.Buscar(LeerPrecio());
			if(pro!=null)
			{
				txtS.append("\tNombre\tCódigo○\tPrecio\tCantidad");
				txtS.append("\n"+pro.getNombre()+"\t"+pro.getCodigo()+"\t"+pro.getPrecio()+"\t"+pro.getCantidad());	
			}
			else JOptionPane.showMessageDialog(null, "Producto no encontrado");
		}
		catch(Exception e2) {
			JOptionPane.showMessageDialog(this, "Ingrese el producto");
		}			
	}
}
