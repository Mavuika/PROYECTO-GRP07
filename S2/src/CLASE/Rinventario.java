package CLASE;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Rinventario extends JFrame implements KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblBuscar;
    private JTextField txtBuscar;
    private JScrollPane scrollPane;
    private JTable tbTable;
    private DefaultTableModel modelo;

    private JButton btnRefrescar;
    private JButton btnBajoStock;
    private JButton btnRegresar;
    private JTextField txtUmbral;

    private ArrayProducto dao;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Rinventario frame = new Rinventario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Rinventario() {
        dao = new ArrayProducto();

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 700, 420);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(8, 8, 8, 8));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("Inventario");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setBounds(10, 6, 200, 24);
        contentPane.add(lblTitulo);

        lblBuscar = new JLabel("Buscar nombre:");
        lblBuscar.setBounds(10, 40, 120, 20);
        contentPane.add(lblBuscar);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(130, 40, 220, 20);
        txtBuscar.addKeyListener(this);
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblUmbral = new JLabel("Umbral:");
        lblUmbral.setBounds(370, 40, 50, 20);
        contentPane.add(lblUmbral);

        txtUmbral = new JTextField("5");
        txtUmbral.setBounds(420, 40, 40, 20);
        contentPane.add(txtUmbral);

        btnBajoStock = new JButton("Productos con bajo stock");
        btnBajoStock.setBounds(480, 36, 180, 26);
        btnBajoStock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarProductosBajoStock();
            }
        });
        contentPane.add(btnBajoStock);

        btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(10, 320, 100, 26);
        btnRefrescar.addActionListener(e -> listar(""));
        contentPane.add(btnRefrescar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(120, 320, 100, 26);
        btnRegresar.addActionListener(e -> regresarSegunRol());
        contentPane.add(btnRegresar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 70, 670, 240);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.setFillsViewportHeight(true);
        scrollPane.setViewportView(tbTable);

        modelo = new DefaultTableModel();
        modelo.addColumn("Código");
        modelo.addColumn("Categoría");
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        modelo.addColumn("Stock");
        tbTable.setModel(modelo);

        listar("");
    }

    
    public void listar(String nom) {

        modelo.setRowCount(0);

        ArrayList<Producto> lista = dao.listarProductos();
        if (lista == null) return;

        String filtro = (nom == null) ? "" : nom.trim().toLowerCase();

        for (Producto p : lista) {
            if (!filtro.isEmpty()) {
                if (p.getNombre() == null || !p.getNombre().toLowerCase().contains(filtro)) {
                    continue;
                }
            }
            Object[] fila = new Object[5];
            fila[0] = p.getCodigo();              
            fila[1] = getCategoriaSafe(p);       
            fila[2] = p.getNombre();
            fila[3] = p.getPrecio();
            fila[4] = p.getCantidad();
            modelo.addRow(fila);
        }
    }

   
    private void mostrarProductosBajoStock() {
        int umbral = 5;
        try {
            umbral = Integer.parseInt(txtUmbral.getText().trim());
        } catch (NumberFormatException ex) {
            umbral = 5;
        }

        modelo.setRowCount(0);
        ArrayList<Producto> lista = dao.listarProductos();
        if (lista == null) return;
        for (Producto p : lista) {
            if (p.getCantidad() <= umbral) {
                Object[] fila = new Object[5];
                fila[0] = p.getCodigo();
                fila[1] = getCategoriaSafe(p);
                fila[2] = p.getNombre();
                fila[3] = p.getPrecio();
                fila[4] = p.getCantidad();
                modelo.addRow(fila);
            }
        }
    }


    private String getCategoriaSafe(Producto p) {
        if (p == null) return "";
        try {
            java.lang.reflect.Method m = p.getClass().getMethod("getCategoria");
            Object val = m.invoke(p);
            return val == null ? "" : val.toString();
        } catch (Exception e) {

            return "";
        }
    }


    public void keyReleased(KeyEvent e) {
        if (e.getSource() == txtBuscar) {
            String nom = txtBuscar.getText();
            listar(nom);
        }
    }

    
    public void keyTyped(KeyEvent e) { 
    	
    }

    public void keyPressed(KeyEvent e) {
    	
    }

    private void regresarSegunRol() {
        String rol = Sesion.getRole();
        if (rol.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay sesión activa, vuelve a iniciar sesión.");
            mostrarLogin();
            return;
        }
        if (rol.equalsIgnoreCase("Cajero")) {
            VRestauranteCajero vr = new VRestauranteCajero();
            vr.setLocationRelativeTo(null);
            vr.setVisible(true);
            dispose();
            return;
        }
        if (rol.equalsIgnoreCase("Gerente") || rol.equalsIgnoreCase("Admin")) {
            VRestaurante v = new VRestaurante();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            dispose();
            return;
        }
        if (rol.equalsIgnoreCase("Supervisor")) {
            VRestauranteSupervisor vrs = new VRestauranteSupervisor();
            vrs.setLocationRelativeTo(null);
            vrs.setVisible(true);
            dispose();
            return;
        }
        if (rol.equalsIgnoreCase("Almacenero")) {
            VRestauranteAlmacenero vra = new VRestauranteAlmacenero();
            vra.setLocationRelativeTo(null);
            vra.setVisible(true);
            dispose();
            return;
        }
        if (rol.equalsIgnoreCase("Recursos Humanos")) {
            VRestauranteRH vrrh = new VRestauranteRH();
            vrrh.setLocationRelativeTo(null);
            vrrh.setVisible(true);
            dispose();
            return;
        }
        if (rol.equalsIgnoreCase("Auditor")) {
            VRestauranteAuditor vrau = new VRestauranteAuditor();
            vrau.setLocationRelativeTo(null);
            vrau.setVisible(true);
            dispose();
            return;
        }
        JOptionPane.showMessageDialog(this, "Rol no soportado: " + rol);
    }

    private void mostrarLogin() {
        Login login = new Login();
        login.setLocationRelativeTo(null);
        login.setVisible(true);
        dispose();
    }
}