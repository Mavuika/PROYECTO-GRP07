package CLASE;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Arreglo.Producto_Arreglo;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ralertas extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtUmbral;
    private JTextArea area;
    private Producto_Arreglo pa = Producto_Arreglo.getInstance();
    private JButton btnBuscar;
    private JButton btnRegresar;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Ralertas frame = new Ralertas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public Ralertas() {
        setResizable(false);
        setTitle("ALERTAS DE STOCK BAJO");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 520, 380);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lbl = new JLabel("Umbral de stock (ej. 5):");
        lbl.setBounds(20, 20, 160, 20);
        contentPane.add(lbl);
        
        txtUmbral = new JTextField("5");
        txtUmbral.setBounds(180, 20, 80, 20);
        contentPane.add(txtUmbral);
        
        btnBuscar = new JButton("Mostrar Productos Críticos");
        btnBuscar.setBounds(280, 18, 200, 24);
        btnBuscar.addActionListener(this);
        contentPane.add(btnBuscar);
        
        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(20, 60, 460, 240);
        contentPane.add(scroll);
        
        area = new JTextArea();
        area.setEditable(false);
        scroll.setViewportView(area);

        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(20, 310, 105, 27);
        btnRegresar.addActionListener(this);
        contentPane.add(btnRegresar);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBuscar) {
            do_btnBuscar_actionPerformed(e);
        }
        if (e.getSource() == btnRegresar) {
            do_btnRegresar_actionPerformed(e);
        }
    }
    protected void do_btnBuscar_actionPerformed(ActionEvent e) {
        try {
            int umbral = Integer.parseInt(txtUmbral.getText().trim());
            ArrayList<CLASE.Producto> criticos = pa.obtenerProductosCriticos(umbral);
            area.setText("");
            if (criticos.isEmpty()) {
                area.append("No hay productos con stock <= " + umbral);
                return;
            }
            area.append("Productos críticos (stock <= " + umbral + "):\n");
            for (CLASE.Producto p : criticos) {
                area.append(p.toString() + "\n");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Umbral inválido.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }
    protected void do_btnRegresar_actionPerformed(ActionEvent e) {
        VRestaurante vr = new VRestaurante();
        vr.setLocationRelativeTo(null);
        vr.setVisible(true);
        dispose();
    }
}