package CLASE;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import Arreglo.Producto_Arreglo;


public class Rinventario extends JFrame {

    private Producto_Arreglo productoArreglo;
    private JTextArea areaInformacion;
    private JTextField txtUmbral;

    public Rinventario() {
    	productoArreglo = Producto_Arreglo.getInstance(); 

        setTitle("INVENTARIO - Simple");
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(500, 350);
        setLocationRelativeTo(null);

        initUI();
        mostrarInventario();
    }

    private void initUI() {

        JLabel lblTitulo = new JLabel("Inventario");
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(Font.BOLD, 16f));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(lblTitulo, BorderLayout.NORTH);


        areaInformacion = new JTextArea();
        areaInformacion.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaInformacion);
        add(scroll, BorderLayout.CENTER);


        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnCriticos = new JButton("Mostrar críticos");
        JButton btnRegresar = new JButton("Regresar");
        txtUmbral = new JTextField("5", 4); 
        panelInferior.add(new JLabel("Umbral:"));
        panelInferior.add(txtUmbral);
        panelInferior.add(btnCriticos);
        panelInferior.add(btnRefrescar);
        panelInferior.add(btnRegresar);

        add(panelInferior, BorderLayout.SOUTH);


        btnRefrescar.addActionListener(e -> mostrarInventario());

        btnCriticos.addActionListener(e -> {
            try {
                int umbral = Integer.parseInt(txtUmbral.getText().trim());
                mostrarProductosCriticos(umbral);
            } catch (NumberFormatException ex) {
                areaInformacion.setText("Umbral inválido. Ingrese un número entero.\n");
            }
        });

        btnRegresar.addActionListener(e -> {
            VRestaurante ventana = new VRestaurante();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
            dispose();
        });
    }

    private void mostrarInventario() {
        StringBuilder sb = new StringBuilder();
        sb.append("Listado de productos:\n");
        sb.append("---------------------\n");
        for (int i = 0; i < productoArreglo.Tamaño(); i++) {
            sb.append(productoArreglo.Obtener(i).toString()).append("\n");
        }
        sb.append("\nTotal de productos: ").append(productoArreglo.Tamaño()).append("\n");
        areaInformacion.setText(sb.toString());
    }


    private void mostrarProductosCriticos(int umbral) {
        StringBuilder sb = new StringBuilder();
        sb.append("Productos con stock <= ").append(umbral).append(":\n");
        sb.append("------------------------------------\n");
        ArrayList<CLASE.Producto> criticos = productoArreglo.obtenerProductosCriticos(umbral);
        if (criticos.isEmpty()) {
            sb.append("No hay productos críticos.\n");
        } else {
            for (CLASE.Producto p : criticos) {
                sb.append(p.toString()).append("\n");
            }
        }
        areaInformacion.setText(sb.toString());
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Rinventario frame = new Rinventario();
            frame.setVisible(true);
        });
    }
}