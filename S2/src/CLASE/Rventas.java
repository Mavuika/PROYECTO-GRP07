package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import Arreglo.Producto_Arreglo;
import Arreglo.ArregloVentas;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Rventas extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNom;
    private JTextField txtCantidad;
    private JTextArea areaVentas;
    private JButton btnRegresar;
    private JButton btnRegistrarVenta;
    private JButton btnAnularVenta;
    private JButton btnReporteMetodo;
    private JComboBox<String> cbMetodo;

    private Producto_Arreglo pa = Producto_Arreglo.getInstance();
    private ArregloVentas listaVentas = new ArregloVentas();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Rventas frame = new Rventas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Rventas() {
        setResizable(false);
        setTitle("REGISTRO DE VENTAS");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 560, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Nombre del producto:");
        lblNewLabel.setBounds(20, 30, 140, 19);
        contentPane.add(lblNewLabel);

        txtNom = new JTextField();
        txtNom.setBounds(170, 30, 220, 19);
        txtNom.setColumns(10);
        contentPane.add(txtNom);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(170, 60, 96, 19);
        txtCantidad.setColumns(10);
        contentPane.add(txtCantidad);

        JLabel lblNewLabel_3 = new JLabel("Cantidad:");
        lblNewLabel_3.setBounds(100, 60, 58, 19);
        contentPane.add(lblNewLabel_3);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 140, 510, 250);
        contentPane.add(scrollPane);

        areaVentas = new JTextArea();
        areaVentas.setEditable(false);
        scrollPane.setViewportView(areaVentas);

        btnRegistrarVenta = new JButton("Registrar Venta");
        btnRegistrarVenta.addActionListener(this);
        btnRegistrarVenta.setBounds(410, 25, 120, 30);
        contentPane.add(btnRegistrarVenta);

        btnAnularVenta = new JButton("Anular Venta");
        btnAnularVenta.addActionListener(this);
        btnAnularVenta.setBounds(410, 60, 120, 30);
        contentPane.add(btnAnularVenta);

        btnReporteMetodo = new JButton("Reporte por Método");
        btnReporteMetodo.addActionListener(this);
        btnReporteMetodo.setBounds(300, 100, 160, 30);
        contentPane.add(btnReporteMetodo);

        cbMetodo = new JComboBox<String>();
        cbMetodo.setModel(new DefaultComboBoxModel<>(new String[] {"Efectivo", "Tarjeta", "Transferencia", "Otro"}));
        cbMetodo.setBounds(20, 100, 160, 30);
        contentPane.add(cbMetodo);

        btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(this);
        btnRegresar.setBounds(20, 400, 105, 27);
        contentPane.add(btnRegresar);

        actualizarReporte();
    }

    private void actualizarReporte() {
        areaVentas.setText("");
        areaVentas.append("Productos disponibles:\n");
        for (int i = 0; i < pa.Tamaño(); i++) {
            Producto p = pa.Obtener(i);
            areaVentas.append(p.getNombre() + "\t" + p.getCodigo() + "\tPrecio: " + p.getPrecio() + "\tCant: " + p.getCantidad() + "\n");
        }
        areaVentas.append("\nVentas registradas:\n");
        for (int i = 0; i < listaVentas.tamano(); i++) {
            areaVentas.append(listaVentas.obtener(i).toString() + "\n");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrarVenta) {
            do_btnRegistrarVenta_actionPerformed(e);
        }
        if (e.getSource() == btnRegresar) {
            do_btnRegresar_actionPerformed(e);
        }
        if (e.getSource() == btnAnularVenta) {
            do_btnAnularVenta_actionPerformed(e);
        }
        if (e.getSource() == btnReporteMetodo) {
            do_btnReporteMetodo_actionPerformed(e);
        }
    }
    protected void do_btnRegresar_actionPerformed(ActionEvent e) {
        VRestaurante newframe = new VRestaurante();
        newframe.setVisible(true);
        dispose();
    }
    protected void do_btnRegistrarVenta_actionPerformed(ActionEvent e) {
        try {
            String nombre = txtNom.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el nombre del producto.");
                return;
            }
            Producto prod = pa.BuscarPorNombre(nombre);
            if (prod == null) {
                prod = pa.Buscar(nombre); 
            }
            if (prod == null) {
                JOptionPane.showMessageDialog(this, "Producto no encontrado por nombre o código: " + nombre);
                return;
            }
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida (>0).");
                return;
            }
            if (prod.getCantidad() < cantidad) {
                JOptionPane.showMessageDialog(this, "Stock insuficiente. Stock actual: " + prod.getCantidad());
                return;
            }

            Venta venta = new Venta(prod, cantidad);
            venta.setMetodoPago((String) cbMetodo.getSelectedItem());

            prod.setCantidad(prod.getCantidad() - cantidad);
            listaVentas.adicionar(venta);
            JOptionPane.showMessageDialog(this, "Venta registrada correctamente.\n" + venta.toString());
            actualizarReporte();
            txtNom.setText("");
            txtCantidad.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar la venta: " + ex.getMessage());
        }
    }
    protected void do_btnAnularVenta_actionPerformed(ActionEvent e) {
        try {
            String idText = JOptionPane.showInputDialog(this, "Ingrese ID de la venta a anular:");
            if (idText == null) return;
            int id = Integer.parseInt(idText.trim());
            Venta v = listaVentas.buscarPorId(id);
            if (v == null) {
                JOptionPane.showMessageDialog(this, "Venta no encontrada con ID: " + id);
                return;
            }
            if (v.isAnulada()) {
                JOptionPane.showMessageDialog(this, "La venta ya está anulada.");
                return;
            }
            String motivo = JOptionPane.showInputDialog(this, "Ingrese motivo de anulación:");
            if (motivo == null) motivo = "";
            boolean ok = listaVentas.anularVenta(id, motivo);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "No se pudo anular la venta.");
                return;
            }
            Producto p = v.getProducto();
            p.setCantidad(p.getCantidad() + v.getCantidadVendida());
            JOptionPane.showMessageDialog(this, "Venta anulada y stock restaurado.\n" + v.toString());
            actualizarReporte();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al anular venta: " + ex.getMessage());
        }
    }
    protected void do_btnReporteMetodo_actionPerformed(ActionEvent e) {
        try {
            String metodo = (String) cbMetodo.getSelectedItem();
            if (metodo == null) metodo = "";
            java.util.ArrayList<Venta> lista = listaVentas.listarPorMetodoPago(metodo);
            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay ventas con método: " + metodo);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Ventas por método: ").append(metodo).append("\n");
            for (Venta v : lista) sb.append(v.toString()).append("\n");
            
            JTextArea ta = new JTextArea(sb.toString());
            ta.setEditable(false);
            JOptionPane.showMessageDialog(this, new JScrollPane(ta), "Reporte", JOptionPane.INFORMATION_MESSAGE);
            
            int resp = JOptionPane.showConfirmDialog(this, "¿Desea exportar este reporte a CSV en carpeta 'datos'?", "Exportar", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.YES_OPTION) {
                java.io.File dir = new java.io.File("datos");
                if (!dir.exists()) dir.mkdir();
                String nombre = "datos/reporte_por_metodo_" + metodo + ".csv";
                try (java.io.PrintWriter pw = new java.io.PrintWriter(new java.io.FileWriter(nombre))) {
                    pw.println("id,producto,codigo,cantidad,total,fecha,anulada,metodo");
                    for (Venta v : lista) {
                        pw.printf("%d,\"%s\",\"%s\",%d,%.2f,\"%s\",%b,\"%s\"%n",
                                v.getId(),
                                v.getProducto().getNombre(),
                                v.getProducto().getCodigo(),
                                v.getCantidadVendida(),
                                v.getTotal(),
                                v.getFecha().toString(),
                                v.isAnulada(),
                                v.getMetodoPago()
                        );
                    }
                }
                JOptionPane.showMessageDialog(this, "Reporte guardado en: " + nombre);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al generar reporte por método: " + ex.getMessage());
        }
    }
}