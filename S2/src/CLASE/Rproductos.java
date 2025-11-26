package CLASE;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import VERIFICACION.Login;
import VERIFICACION.Sesion;
import arrayList.ArrayProducto;

public class Rproductos extends JFrame implements ActionListener, MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCodigo, lblCategoria, lblNombre, lblPrecio, lblCantidad;
    private JTextField txtCodigo, txtCategoria, txtNombre, txtPrecio, txtCantidad;
    private JScrollPane scrollPane;
    private JTable tbTable;
    private DefaultTableModel modelo;

    private JButton btnAgregar, btnModificar, btnEliminar, btnRefrescar, btnBuscar, btnRegresar;

    private ArrayProducto dao = new ArrayProducto();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Rproductos frame = new Rproductos();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Rproductos() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 720, 500);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblCodigo = new JLabel("Código");
        lblCodigo.setBounds(20, 20, 60, 16);
        contentPane.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(90, 20, 140, 20);
        contentPane.add(txtCodigo);

        lblCategoria = new JLabel("Categoría");
        lblCategoria.setBounds(250, 20, 80, 16);
        contentPane.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(330, 20, 180, 20);
        contentPane.add(txtCategoria);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(20, 50, 60, 16);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(90, 50, 240, 20);
        txtNombre.addKeyListener(this);
        contentPane.add(txtNombre);

        lblPrecio = new JLabel("Precio");
        lblPrecio.setBounds(20, 80, 60, 16);
        contentPane.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(90, 80, 120, 20);
        txtPrecio.addKeyListener(this);
        contentPane.add(txtPrecio);

        lblCantidad = new JLabel("Cantidad");
        lblCantidad.setBounds(20, 110, 60, 16);
        contentPane.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(90, 110, 120, 20);
        contentPane.add(txtCantidad);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(520, 20, 140, 24);
        btnAgregar.addActionListener(this);
        contentPane.add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(520, 52, 140, 24);
        btnModificar.addActionListener(this);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(520, 84, 140, 24);
        btnEliminar.addActionListener(this);
        contentPane.add(btnEliminar);

        btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(20, 420, 120, 26);
        btnRefrescar.addActionListener(this);
        contentPane.add(btnRefrescar);

        btnBuscar = new JButton("Buscar por Código");
        btnBuscar.setBounds(150, 420, 160, 26);
        btnBuscar.addActionListener(this);
        contentPane.add(btnBuscar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(320, 420, 120, 26);
        btnRegresar.addActionListener(e -> regresarSegunRol());
        contentPane.add(btnRegresar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 150, 640, 250);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.setFillsViewportHeight(true);
        tbTable.addMouseListener(this);
        scrollPane.setViewportView(tbTable);

        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");
        tbTable.setModel(modelo);

        Listar("");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) doAgregar();
        else if (e.getSource() == btnModificar) doModificar();
        else if (e.getSource() == btnEliminar) doEliminar();
        else if (e.getSource() == btnRefrescar) Listar("");
        else if (e.getSource() == btnBuscar) doBuscarPorCodigo();
    }

    private void doAgregar() {
        try {
            String codigo = txtCodigo.getText().trim();
            String categoria = txtCategoria.getText().trim();
            String nombre = txtNombre.getText().trim();
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            CLASE.Producto p = new CLASE.Producto(nombre, codigo, precio, cantidad);
            p.setCategoria(categoria);
            boolean ok = dao.insertar(p);
            JOptionPane.showMessageDialog(this, ok ? "Producto agregado" : "Error al agregar (verifica código)");
            Listar("");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio o Cantidad inválidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void doModificar() {
        try {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese código"); return; }
            CLASE.Producto p = new CLASE.Producto(txtNombre.getText().trim(), codigo, Double.parseDouble(txtPrecio.getText().trim()), Integer.parseInt(txtCantidad.getText().trim()));
            p.setCategoria(txtCategoria.getText().trim());
            boolean ok = dao.editar(p);
            JOptionPane.showMessageDialog(this, ok ? "Producto modificado" : "Error al modificar");
            Listar("");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio o Cantidad inválidos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage());
        }
    }

    private void doEliminar() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese código"); return; }
        int resp = JOptionPane.showConfirmDialog(this, "¿Eliminar producto " + codigo + "?\nSe eliminarán pedidos relacionados.", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) return;
        boolean ok = dao.eliminarPorCodigo(codigo);
        JOptionPane.showMessageDialog(this, ok ? "Producto eliminado" : "Error al eliminar");
        Listar("");
        limpiar();
    }

    private void doBuscarPorCodigo() {
        String codigo = txtCodigo.getText().trim();
        if (codigo.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese código"); return; }
        modelo.setRowCount(0);
        ArrayList<CLASE.Producto> res = dao.consultarPorCodigo(codigo);
        if (res == null || res.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No encontrado");
            return;
        }
        for (CLASE.Producto p : res) {
            modelo.addRow(new Object[] { p.getCodigo(), getCategoriaSafe(p), p.getNombre(), p.getPrecio(), p.getCantidad() });
        }
    }

    public void Listar(String nom) {
        modelo.setRowCount(0);
        ArrayList<CLASE.Producto> lista = dao.listarProductos();
        if (lista == null) return;
        String filtro = (nom == null) ? "" : nom.trim().toLowerCase();
        for (CLASE.Producto p : lista) {
            if (!filtro.isEmpty()) {
                if (p.getNombre() == null || !p.getNombre().toLowerCase().contains(filtro)) continue;
            }
            modelo.addRow(new Object[] { p.getCodigo(), getCategoriaSafe(p), p.getNombre(), p.getPrecio(), p.getCantidad() });
        }
    }

    private String getCategoriaSafe(CLASE.Producto p) {
        if (p == null) return "";
        try {
            java.lang.reflect.Method m = p.getClass().getMethod("getCategoria");
            Object val = m.invoke(p);
            return val == null ? "" : val.toString();
        } catch (Exception e) {
            return "";
        }
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtCategoria.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            if (fila >= 0) {
                txtCodigo.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
                txtCategoria.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
                txtNombre.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
                txtPrecio.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
                txtCantidad.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
            }
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtNombre) {
            String nom = txtNombre.getText();
            Listar(nom);
        }
    }

    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {}

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