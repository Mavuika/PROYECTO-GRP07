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
import java.util.Iterator;

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
import arrayList.ArrayEmpleado;

/**
 * Rempleados con JTable (estilo MantenimientoAccesorio)
 * - Lista empleados en una JTable
 * - Click en fila carga campos para editar/eliminar
 * - Botones: Agregar, Modificar, Eliminar, Refrescar, Buscar por NRC, Regresar
 */
public class Rempleados extends JFrame implements ActionListener, MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel lblNRC, lblNombre, lblCargo, lblSalario, lblTelefono;
    private JTextField txtNRC, txtNombre, txtCargo, txtSalario, txtTelefono;
    private JScrollPane scrollPane;
    private JTable tbTable;
    private DefaultTableModel modelo;

    private JButton btnAgregar, btnModificar, btnEliminar, btnRefrescar, btnBuscar, btnRegresar;

    private ArrayEmpleado dao = new ArrayEmpleado();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Rempleados frame = new Rempleados();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Rempleados() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 680, 460);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lblNRC = new JLabel("NRC");
        lblNRC.setBounds(20, 20, 60, 16);
        contentPane.add(lblNRC);

        txtNRC = new JTextField();
        txtNRC.setBounds(90, 20, 140, 20);
        contentPane.add(txtNRC);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(20, 50, 60, 16);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(90, 50, 240, 20);
        txtNombre.addKeyListener(this);
        contentPane.add(txtNombre);

        lblCargo = new JLabel("Cargo");
        lblCargo.setBounds(20, 80, 60, 16);
        contentPane.add(lblCargo);

        txtCargo = new JTextField();
        txtCargo.setBounds(90, 80, 180, 20);
        contentPane.add(txtCargo);

        lblSalario = new JLabel("Salario");
        lblSalario.setBounds(20, 110, 60, 16);
        contentPane.add(lblSalario);

        txtSalario = new JTextField();
        txtSalario.setBounds(90, 110, 120, 20);
        txtSalario.addKeyListener(this);
        contentPane.add(txtSalario);

        lblTelefono = new JLabel("Teléfono");
        lblTelefono.setBounds(20, 140, 60, 16);
        contentPane.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(90, 140, 140, 20);
        contentPane.add(txtTelefono);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(380, 20, 140, 24);
        btnAgregar.addActionListener(this);
        contentPane.add(btnAgregar);

        btnModificar = new JButton("Modificar");
        btnModificar.setBounds(380, 52, 140, 24);
        btnModificar.addActionListener(this);
        contentPane.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(380, 84, 140, 24);
        btnEliminar.addActionListener(this);
        contentPane.add(btnEliminar);

        btnRefrescar = new JButton("Refrescar");
        btnRefrescar.setBounds(20, 360, 120, 26);
        btnRefrescar.addActionListener(this);
        contentPane.add(btnRefrescar);

        btnBuscar = new JButton("Buscar por NRC");
        btnBuscar.setBounds(150, 360, 160, 26);
        btnBuscar.addActionListener(this);
        contentPane.add(btnBuscar);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(320, 360, 120, 26);
        btnRegresar.addActionListener(e -> regresarSegunRol());
        contentPane.add(btnRegresar);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 180, 620, 160);
        contentPane.add(scrollPane);

        tbTable = new JTable();
        tbTable.setFillsViewportHeight(true);
        tbTable.addMouseListener(this);
        scrollPane.setViewportView(tbTable);

        modelo = new DefaultTableModel();
        modelo.addColumn("NRC");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cargo");
        modelo.addColumn("Salario");
        modelo.addColumn("Teléfono");
        tbTable.setModel(modelo);

        Listar("");
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) doAgregar();
        else if (e.getSource() == btnModificar) doModificar();
        else if (e.getSource() == btnEliminar) doEliminar();
        else if (e.getSource() == btnRefrescar) Listar("");
        else if (e.getSource() == btnBuscar) doBuscarPorNRC();
    }

    private void doAgregar() {
        try {
            String nrc = txtNRC.getText().trim();
            String nombre = txtNombre.getText().trim();
            String cargo = txtCargo.getText().trim();
            double salario = Double.parseDouble(txtSalario.getText().trim());
            String telefono = txtTelefono.getText().trim();
            CLASE.Empleado emp = new CLASE.Empleado(nrc, nombre, cargo, salario, telefono);
            boolean ok = dao.insertar(emp);
            JOptionPane.showMessageDialog(this, ok ? "Empleado agregado" : "Error al agregar (revisa NRC único)");
            Listar("");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario inválido");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
        }
    }

    private void doModificar() {
        try {
            String nrc = txtNRC.getText().trim();
            if (nrc.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese NRC"); return; }
            CLASE.Empleado emp = new CLASE.Empleado(nrc, txtNombre.getText().trim(), txtCargo.getText().trim(),
                    Double.parseDouble(txtSalario.getText().trim()), txtTelefono.getText().trim());
            boolean ok = dao.editar(emp);
            JOptionPane.showMessageDialog(this, ok ? "Empleado modificado" : "Error al modificar");
            Listar("");
            limpiar();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salario inválido");
        }
    }

    private void doEliminar() {
        String nrc = txtNRC.getText().trim();
        if (nrc.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese NRC"); return; }
        int resp = JOptionPane.showConfirmDialog(this, "Eliminar empleado y pedidos relacionados? Esta acción es irreversible.", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) return;
        boolean ok = dao.eliminarPorNRC(nrc);
        JOptionPane.showMessageDialog(this, ok ? "Empleado eliminado" : "Error al eliminar");
        Listar("");
        limpiar();
    }

    private void doBuscarPorNRC() {
        String nrc = txtNRC.getText().trim();
        if (nrc.isEmpty()) { JOptionPane.showMessageDialog(this, "Ingrese NRC"); return; }
        modelo.setRowCount(0);
        ArrayList<CLASE.Empleado> res = dao.consultarPorNRC(nrc);
        if (res == null || res.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No encontrado");
            return;
        }
        for (CLASE.Empleado e : res) {
            Object[] fila = new Object[] { e.getNrc(), e.getNombre(), e.getCargo(), e.getSalario(), e.getTelefono() };
            modelo.addRow(fila);
        }
    }


    public void Listar(String nom) {
        modelo.setRowCount(0);
        ArrayList<CLASE.Empleado> lista = dao.listarEmpleados();
        if (lista == null) return;
        String filtro = (nom == null) ? "" : nom.trim().toLowerCase();
        for (CLASE.Empleado e : lista) {
            if (!filtro.isEmpty()) {
                if (e.getNombre() == null || !e.getNombre().toLowerCase().contains(filtro)) continue;
            }
            Object[] fila = new Object[] { e.getNrc(), e.getNombre(), e.getCargo(), e.getSalario(), e.getTelefono() };
            modelo.addRow(fila);
        }
    }

    private void limpiar() {
        txtNRC.setText("");
        txtNombre.setText("");
        txtCargo.setText("");
        txtSalario.setText("");
        txtTelefono.setText("");
    }


    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == tbTable) {
            int fila = tbTable.getSelectedRow();
            if (fila >= 0) {
                txtNRC.setText(String.valueOf(tbTable.getValueAt(fila, 0)));
                txtNombre.setText(String.valueOf(tbTable.getValueAt(fila, 1)));
                txtCargo.setText(String.valueOf(tbTable.getValueAt(fila, 2)));
                txtSalario.setText(String.valueOf(tbTable.getValueAt(fila, 3)));
                txtTelefono.setText(String.valueOf(tbTable.getValueAt(fila, 4)));
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
        if (e.getSource() == txtSalario) {
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