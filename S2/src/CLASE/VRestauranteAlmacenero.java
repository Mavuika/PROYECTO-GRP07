package CLASE;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class VRestauranteAlmacenero extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu mnArchivo;
    private JMenuItem mntmSalir;
    private JMenu mnOpciones;
    private JMenu mnRegistro;
    private JMenuItem mntmRegistrarProductos;
    private JMenuItem mntmRegistrarCompras; 
    private JMenuItem mntmInventario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VRestauranteAlmacenero frame = new VRestauranteAlmacenero();
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
    public VRestauranteAlmacenero() {
        setResizable(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\swank\\Downloads\\ProyectoLenin\\PROYECTO-GRP07\\S2\\Imagenes\\NorkysIco.PNG"));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);
        
        mntmSalir = new JMenuItem("Salir");
        mntmSalir.addActionListener(this);
        mnArchivo.add(mntmSalir);
        
        mnOpciones = new JMenu("Opciones");
        menuBar.add(mnOpciones);
        
        mnRegistro = new JMenu("Registro");
        mnOpciones.add(mnRegistro);
        
        mntmRegistrarProductos = new JMenuItem("Registrar productos");
        mntmRegistrarProductos.addActionListener(this);
        mnRegistro.add(mntmRegistrarProductos);

        mntmRegistrarCompras = new JMenuItem("Registrar compras");
        mntmRegistrarCompras.addActionListener(this);
        mnRegistro.add(mntmRegistrarCompras);
        
        mntmInventario = new JMenuItem("Inventario");
        mntmInventario.addActionListener(this);
        mnOpciones.add(mntmInventario);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmSalir) {
            do_mntmSalir_actionPerformed(e);
        }
        if (e.getSource() == mntmInventario) {
            do_mntmInventario_actionPerformed(e);
        }
        if (e.getSource() == mntmRegistrarProductos) {
            do_mntmRegistrarProductos_actionPerformed(e);
        }
        if (e.getSource() == mntmRegistrarCompras) {
            do_mntmRegistrarCompras_actionPerformed(e);
        }
    }
    protected void do_mntmRegistrarProductos_actionPerformed(ActionEvent e) {
        Rproductos pro = new Rproductos();
        pro.setLocationRelativeTo(null);
        pro.setVisible(true);
        dispose();
    }
    protected void do_mntmRegistrarCompras_actionPerformed(ActionEvent e) {
        Rcompras comp = new Rcompras();
        comp.setLocationRelativeTo(null);
        comp.setVisible(true);
        dispose();
    }
    protected void do_mntmInventario_actionPerformed(ActionEvent e) {
        Rinventario inv = new Rinventario();
        inv.setLocationRelativeTo(null);
        inv.setVisible(true);
        dispose();
    }
    protected void do_mntmSalir_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}