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

public class VRestauranteRH extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JMenuBar menuBar;
    private JMenu mnArchivo;
    private JMenuItem mntmSalir;
    private JMenu mnOpciones;
    private JMenu mnRegistro;
    private JMenuItem mntmRegistrarEmpleados; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VRestauranteRH frame = new VRestauranteRH();
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
    public VRestauranteRH() {
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

        mntmRegistrarEmpleados = new JMenuItem("Registrar empleados");
        mntmRegistrarEmpleados.addActionListener(this);
        mnRegistro.add(mntmRegistrarEmpleados);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mntmSalir) {
            do_mntmSalir_actionPerformed(e);
        }
        if (e.getSource() == mntmRegistrarEmpleados) {
            do_mntmRegistrarEmpleados_actionPerformed(e);
        }
    }
    protected void do_mntmRegistrarEmpleados_actionPerformed(ActionEvent e) {
        Rempleados remp = new Rempleados();
        remp.setLocationRelativeTo(null);
        remp.setVisible(true);
        dispose();
    }
    protected void do_mntmSalir_actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}