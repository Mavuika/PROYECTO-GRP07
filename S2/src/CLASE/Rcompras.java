package CLASE;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Arreglo.Producto_Arreglo;
import Arreglo.ArregloCompras;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rcompras extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNombre;
    private JTextField txtCantidad;
    private JTextField txtProveedor;
    private JButton btnRegistrar;
    private JButton btnRegresar;
    private JTextArea area;
    
    private Producto_Arreglo pa = Producto_Arreglo.getInstance();
    private ArregloCompras ac = new ArregloCompras();
    
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
        setResizable(false);
        setTitle("REGISTRO DE COMPRAS");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNombre = new JLabel("Nombre o código del producto:");
        lblNombre.setBounds(20, 20, 200, 20);
        contentPane.add(lblNombre);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(220, 20, 200, 20);
        contentPane.add(txtNombre);
        
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 60, 100, 20);
        contentPane.add(lblCantidad);
        
        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 60, 80, 20);
        contentPane.add(txtCantidad);
        
        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setBounds(220, 60, 100, 20);
        contentPane.add(lblProveedor);
        
        txtProveedor = new JTextField();
        txtProveedor.setBounds(300, 60, 120, 20);
        contentPane.add(txtProveedor);
        
        btnRegistrar = new JButton("Registrar Compra");
        btnRegistrar.addActionListener(this);
        btnRegistrar.setBounds(280, 331, 140, 25);
        contentPane.add(btnRegistrar);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 100, 460, 200);
        contentPane.add(scroll);
        
        area = new JTextArea();
        area.setEditable(false);
        scroll.setViewportView(area);
        
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(115, 330, 105, 27);
        btnRegresar.addActionListener(this);
        contentPane.add(btnRegresar);
        
        actualizarReporte();
    }
    private void actualizarReporte() {
        area.setText("");
        area.append("Productos:\n");
        for (int i=0;i<pa.Tamaño();i++){
            Producto p = pa.Obtener(i);
            area.append(p.getNombre()+"\t"+p.getCodigo()+"\tPrecio:"+p.getPrecio()+"\tCant:"+p.getCantidad()+"\n");
        }
        area.append("\nCompras registradas: "+ac.tamano()+"\n");
        for (int i=0;i<ac.tamano();i++){
            area.append(ac.obtener(i).toString()+"\n");
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            do_btnRegistrar_actionPerformed(e);
        }
        if (e.getSource() == btnRegresar) {
            do_btnRegresar_actionPerformed(e);
        }
    }
    protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
        try {
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese nombre o código del producto.");
                return;
            }
            Producto prod = pa.Buscar(nombre);
            if (prod == null) prod = pa.BuscarPorNombre(nombre);
            if (prod == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado: " + nombre);
                return;
            }
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida (>0).");
                return;
            }
            String proveedor = txtProveedor.getText().trim();
            prod.setCantidad(prod.getCantidad() + cantidad);
            Compra compra = new Compra(prod, cantidad, proveedor);
            ac.adicionar(compra);
            JOptionPane.showMessageDialog(this, "Compra registrada:\n" + compra.toString());
            actualizarReporte();
            txtNombre.setText("");
            txtCantidad.setText("");
            txtProveedor.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser número entero.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar compra: " + ex.getMessage());
        }
    }
    protected void do_btnRegresar_actionPerformed(ActionEvent e) {
        VRestaurante vr = new VRestaurante();
        vr.setLocationRelativeTo(null);
        vr.setVisible(true);
        dispose();
    }
}