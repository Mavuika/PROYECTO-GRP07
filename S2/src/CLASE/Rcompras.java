package CLASE;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import VERIFICACION.Login;
import VERIFICACION.Sesion;
import conexion.ConexionMySQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Rcompras extends JFrame implements ActionListener, MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblCod, lblCat, lblNom, lblPre, lblCant;
    private JTextField txtCod, txtCat, txtNom, txtPre, txtCant;
    private JButton btnComprar, btnListar, btnRegresar;
    private JScrollPane scrollPane;
    private JTable tbTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Rcompras frame = new Rcompras();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Rcompras() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(6, 6, 6, 6));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblCod = new JLabel("Código:");
        lblCod.setBounds(20, 10, 60, 16);
        contentPane.add(lblCod);

        txtCod = new JTextField();
        txtCod.setBounds(90, 8, 140, 20);
        contentPane.add(txtCod);
        txtCod.setColumns(10);

        lblCat = new JLabel("Categoría:");
        lblCat.setBounds(20, 40, 60, 16);
        contentPane.add(lblCat);

        txtCat = new JTextField();
        txtCat.setBounds(90, 38, 140, 20);
        contentPane.add(txtCat);
        txtCat.setColumns(10);

        lblNom = new JLabel("Nombre:");
        lblNom.setBounds(20, 70, 60, 16);
        contentPane.add(lblNom);

        txtNom = new JTextField();
        txtNom.setBounds(90, 68, 320, 20);
        contentPane.add(txtNom);
        txtNom.setColumns(10);

        lblPre = new JLabel("Precio:");
        lblPre.setBounds(20, 100, 60, 16);
        contentPane.add(lblPre);

        txtPre = new JTextField();
        txtPre.setBounds(90, 98, 100, 20);
        txtPre.addKeyListener(this);
        contentPane.add(txtPre);
        txtPre.setColumns(10);

        lblCant = new JLabel("Cantidad:");
        lblCant.setBounds(200, 100, 60, 16);
        contentPane.add(lblCant);

        txtCant = new JTextField();
        txtCant.setBounds(270, 98, 80, 20);
        txtCant.addKeyListener(this);
        contentPane.add(txtCant);
        txtCant.setColumns(10);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(400, 35, 100, 26);
        btnComprar.addActionListener(this);
        contentPane.add(btnComprar);

        btnListar = new JButton("Listar Compras");
        btnListar.setBounds(20, 140, 140, 26);
        btnListar.addActionListener(this);
        contentPane.add(btnListar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(180, 140, 120, 26);
        btnRegresar.addActionListener(e -> regresarSegunRol());
        contentPane.add(btnRegresar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 180, 490, 190);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.addMouseListener(this);
        tbTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(tbTable);

        listarCompras();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnComprar) doComprar();
        else if (e.getSource() == btnListar) listarCompras();
    }

    protected void doComprar() {
        try {
            String codigo = txtCod.getText().trim();
            String categoria = txtCat.getText().trim();
            String nombre = txtNom.getText().trim();
            String sPrecio = txtPre.getText().trim();
            String sCant = txtCant.getText().trim();

            if (codigo.isEmpty() || nombre.isEmpty() || sPrecio.isEmpty() || sCant.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese Código, Nombre, Precio y Cantidad (obligatorios).");
                return;
            }

            double precio;
            int cantidad;
            try {
                precio = Double.parseDouble(sPrecio);
                cantidad = Integer.parseInt(sCant);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio o Cantidad inválidos.");
                return;
            }

            if (precio < 0 || cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Precio debe ser >= 0 y Cantidad > 0.");
                return;
            }

            try (Connection cnx = ConexionMySQL.getConexión()) {
                try {
                    cnx.setAutoCommit(false);

                    String sel = "SELECT nombre, precio, stock, categoria FROM productos WHERE id = ? FOR UPDATE";
                    try (PreparedStatement psSel = cnx.prepareStatement(sel)) {
                        psSel.setString(1, codigo);
                        try (ResultSet rs = psSel.executeQuery()) {
                            if (rs.next()) {
                                String nombreExist = rs.getString("nombre");
                                double precioExist = rs.getDouble("precio");
                                String categoriaExist = rs.getString("categoria");

                                String upd = "UPDATE productos SET nombre = ?, precio = ?, stock = stock + ?, categoria = ? WHERE id = ?";
                                try (PreparedStatement psUpd = cnx.prepareStatement(upd)) {
                                    String nombreFinal = (nombre.isEmpty()) ? nombreExist : nombre;
                                    double precioFinal = (precio > 0) ? precio : precioExist;
                                    String categoriaFinal = (categoria == null || categoria.isEmpty()) ? categoriaExist : categoria;
                                    psUpd.setString(1, nombreFinal);
                                    psUpd.setDouble(2, precioFinal);
                                    psUpd.setInt(3, cantidad);
                                    psUpd.setString(4, categoriaFinal);
                                    psUpd.setString(5, codigo);
                                    psUpd.executeUpdate();
                                }
                            } else {
                                String insProd = "INSERT INTO productos (id, categoria, nombre, precio, stock) VALUES (?, ?, ?, ?, ?)";
                                try (PreparedStatement psIns = cnx.prepareStatement(insProd)) {
                                    psIns.setString(1, codigo);
                                    psIns.setString(2, categoria);
                                    psIns.setString(3, nombre);
                                    psIns.setDouble(4, precio);
                                    psIns.setInt(5, cantidad);
                                    psIns.executeUpdate();
                                }
                            }
                        }
                    }

                    String insCompra = "INSERT INTO compras (producto_id, categoria, nombre, precio, cantidad) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement psCompr = cnx.prepareStatement(insCompra, Statement.RETURN_GENERATED_KEYS)) {
                        psCompr.setString(1, codigo);
                        psCompr.setString(2, categoria);
                        psCompr.setString(3, nombre);
                        psCompr.setDouble(4, precio);
                        psCompr.setInt(5, cantidad);
                        psCompr.executeUpdate();
                    }

                    cnx.commit();
                    JOptionPane.showMessageDialog(this, "Compra registrada y stock actualizado.");
                    listarCompras();
                    limpiar();
                } catch (Exception ex) {
                    try { cnx.rollback(); } catch (Exception e2) {}
                    JOptionPane.showMessageDialog(this, "Error al registrar compra: " + ex.getMessage());
                    ex.printStackTrace();
                } finally {
                    try { cnx.setAutoCommit(true); } catch (Exception e3) {}
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void listarCompras() {
        DefaultTableModel modelo = new DefaultTableModel();
        ArrayList<CLASE.Compra> lista = new ArrayList<>();
        String sql = "SELECT id, fecha, producto_id, categoria, nombre, precio, cantidad FROM compras ORDER BY fecha DESC";
        try (Connection cnx = ConexionMySQL.getConexión();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                CLASE.Compra c = new CLASE.Compra();
                c.setId(rs.getInt("id"));
                c.setFecha(rs.getTimestamp("fecha"));
                c.setProductoId(rs.getString("producto_id"));
                c.setCategoria(rs.getString("categoria"));
                c.setNombre(rs.getString("nombre"));
                c.setPrecio(rs.getDouble("precio"));
                c.setCantidad(rs.getInt("cantidad"));
                lista.add(c);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        modelo.setRowCount(lista.size());
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Cantidad");

        Iterator<CLASE.Compra> it = lista.iterator();
        int i = 0;
        while (it.hasNext()) {
            CLASE.Compra c = it.next();
            modelo.setValueAt(c.getId(), i, 0);
            modelo.setValueAt(c.getFecha(), i, 1);
            modelo.setValueAt(c.getProductoId(), i, 2);
            modelo.setValueAt(c.getCategoria(), i, 3);
            modelo.setValueAt(c.getNombre(), i, 4);
            modelo.setValueAt(c.getPrecio(), i, 5);
            modelo.setValueAt(c.getCantidad(), i, 6);
            i++;
        }
        tbTable.setModel(modelo);
    }

    void limpiar() {
        txtCod.setText("");
        txtCat.setText("");
        txtNom.setText("");
        txtPre.setText("");
        txtCant.setText("");
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            if (fila >= 0) {
                txtCod.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
                txtCat.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
                txtNom.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
                txtPre.setText(String.valueOf(tbTable.getValueAt(fila, 5)));
                txtCant.setText(String.valueOf(tbTable.getValueAt(fila, 6)));
            }
        }
    }

    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}	

    public void keyTyped(KeyEvent e) {
        if (e.getSource() == txtPre) {
            char ch = e.getKeyChar();
            if (!(Character.isDigit(ch) || ch == '.' || ch == '\b')) {
                e.consume();
            }
        }
        if (e.getSource() == txtCant) {
            char ch = e.getKeyChar();
            if (!Character.isDigit(ch) && ch != '\b') e.consume();
        }
    }

    public void keyPressed(KeyEvent e) {}
    public void keyReleased(KeyEvent e) {}

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