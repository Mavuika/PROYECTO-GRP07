package CLASE;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import VERIFICACION.Login;
import VERIFICACION.Sesion;
import arrayList.ArrayPedido;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;


public class Rpedidos extends JFrame implements ActionListener, MouseListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCodigo;
	private JLabel lblEmpleado;
	private JLabel lblProducto;
	private JLabel lblCantidad;
	private JLabel lblMetodo;
	private JTextField txtCodigo;
	private JTextField txtEmpleado;
	private JTextField txtProducto;
	private JTextField txtCantidad;
	private JTextField txtMetodo;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnListar;
	private JButton btnRegresar;
	private JScrollPane scrollPane;
	private JTable tbTable;

	private ArrayPedido dao = new ArrayPedido();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Rpedidos frame = new Rpedidos();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Rpedidos() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(22, 10, 60, 13);
		contentPane.add(lblCodigo);

		lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(22, 35, 60, 13);
		contentPane.add(lblEmpleado);

		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(22, 60, 60, 13);
		contentPane.add(lblProducto);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(22, 85, 60, 13);
		contentPane.add(lblCantidad);

		lblMetodo = new JLabel("Método");
		lblMetodo.setBounds(22, 110, 60, 13);
		contentPane.add(lblMetodo);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(100, 7, 120, 19);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		txtEmpleado = new JTextField();
		txtEmpleado.setBounds(100, 32, 200, 19);
		contentPane.add(txtEmpleado);
		txtEmpleado.setColumns(10);

		txtProducto = new JTextField();
		txtProducto.setBounds(100, 57, 200, 19);
		contentPane.add(txtProducto);
		txtProducto.setColumns(10);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(100, 82, 72, 19);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		txtMetodo = new JTextField();
		txtMetodo.setBounds(100, 107, 120, 19);
		contentPane.add(txtMetodo);
		txtMetodo.setColumns(10);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(320, 22, 100, 21);
		btnAgregar.addActionListener(this);
		contentPane.add(btnAgregar);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(320, 48, 100, 21);
		btnModificar.addActionListener(this);
		contentPane.add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(320, 74, 100, 21);
		btnEliminar.addActionListener(this);
		contentPane.add(btnEliminar);

		btnListar = new JButton("Listar Pedidos");
		btnListar.setBounds(22, 140, 120, 21);
		btnListar.addActionListener(this);
		contentPane.add(btnListar);

		btnRegresar = new JButton("Regresar");
		btnRegresar.setBounds(160, 140, 120, 21);
		btnRegresar.addActionListener(e -> regresarSegunRol());
		contentPane.add(btnRegresar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 420, 140);
		contentPane.add(scrollPane);

		tbTable = new JTable();
		tbTable.addMouseListener(this);
		tbTable.setFillsViewportHeight(true);
		scrollPane.setViewportView(tbTable);

		Listar("");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			do_btnAgregar_actionPerformed(e);
		}
		if (e.getSource() == btnModificar) {
			do_btnModificar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminar) {
			do_btnEliminar_actionPerformed(e);
		}
		if (e.getSource() == btnListar) {
			Listar("");
		}
	}

	protected void do_btnAgregar_actionPerformed(ActionEvent e) {
		try {
			String codigo = txtCodigo.getText().trim();
			String empleado = txtEmpleado.getText().trim();
			String producto = txtProducto.getText().trim();
			int cantidad = Integer.parseInt(txtCantidad.getText().trim());
			String metodo = txtMetodo.getText().trim();

			CLASE.Pedido pedido = new CLASE.Pedido(codigo, empleado, producto, cantidad, 0.0, metodo);
			boolean ok = dao.insertar(pedido);
			if (!ok) JOptionPane.showMessageDialog(this, "Error al agregar (verifica datos/código).");
			Listar("");
			Limpiar();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Cantidad inválida");
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		}
	}

	protected void do_btnModificar_actionPerformed(ActionEvent e) {
		try {
			String codigo = txtCodigo.getText().trim();
			if (codigo.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Ingrese código");
				return;
			}
			String empleado = txtEmpleado.getText().trim();
			String producto = txtProducto.getText().trim();
			int cantidad = Integer.parseInt(txtCantidad.getText().trim());
			String metodo = txtMetodo.getText().trim();

			CLASE.Pedido pedido = new CLASE.Pedido(codigo, empleado, producto, cantidad, 0.0, metodo);
			boolean ok = dao.editar(pedido);
			if (!ok) JOptionPane.showMessageDialog(this, "Error al modificar (verifica existencia).");
			Listar("");
			Limpiar();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Cantidad inválida");
		}
	}

	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
		String codigo = txtCodigo.getText().trim();
		if (codigo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Ingrese código");
			return;
		}
		int resp = JOptionPane.showConfirmDialog(this, "¿Eliminar pedido " + codigo + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
		if (resp != JOptionPane.YES_OPTION) return;
		boolean ok = dao.eliminarPorCodigo(codigo);
		if (!ok) JOptionPane.showMessageDialog(this, "Error al eliminar (verifica código).");
		Listar("");
		Limpiar();
	}


	public void Listar(String filtro) {
		DefaultTableModel modelo = new DefaultTableModel();
		ArrayList<CLASE.Pedido> lista = new ArrayList<CLASE.Pedido>();
		lista = dao.listarPedidos(); 
		modelo.setRowCount(lista.size());
		modelo.addColumn("Código");
		modelo.addColumn("Empleado");
		modelo.addColumn("Producto");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Total");
		modelo.addColumn("Método");

		Iterator<CLASE.Pedido> it = lista.iterator();
		int i = 0;
		while (it.hasNext()) {
			CLASE.Pedido p = it.next();
			modelo.setValueAt(p.getCodigo(), i, 0);
			modelo.setValueAt(p.getEmpleado(), i, 1);
			modelo.setValueAt(p.getProducto(), i, 2);
			modelo.setValueAt(p.getCantidad(), i, 3);
			modelo.setValueAt(p.getTotal(), i, 4);
			modelo.setValueAt(p.getMetodoPago(), i, 5);
			i++;
		}
		tbTable.setModel(modelo);
	}

	void Limpiar() {
		txtCodigo.setText("");
		txtEmpleado.setText("");
		txtProducto.setText("");
		txtCantidad.setText("");
		txtMetodo.setText("");
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tbTable) {
			int fila = tbTable.getSelectedRow();
			if (fila >= 0) {
				txtCodigo.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
				txtEmpleado.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
				txtProducto.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
				txtCantidad.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
				txtMetodo.setText(String.valueOf(tbTable.getValueAt(fila, 5)));
			}
		}
	}

	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}


	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		
	}

    private void regresarSegunRol() {
        String rol = Sesion.getRole();
        if (rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay sesión activa, vuelve a iniciar sesión.");
            mostrarLogin();
            return;
        }
        switch (rol.toLowerCase()) {
            case "cajero":
                new VRestauranteCajero().setVisible(true);
                break;
            case "gerente":
            	new VRestaurante().setVisible(true);
                break;
            case "admin":
                new VRestaurante().setVisible(true);
                break;
            case "supervisor":
                new VRestauranteSupervisor().setVisible(true);
                break;
            case "almacenero":
                new VRestauranteAlmacenero().setVisible(true);
                break;
            case "recursos humanos":
                new VRestauranteRH().setVisible(true);
                break;
            case "auditor":
                new VRestauranteAuditor().setVisible(true);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Rol no soportado: " + rol);
                return;
        }
        dispose();
    }

    private void mostrarLogin() {
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        dispose();
    }
}