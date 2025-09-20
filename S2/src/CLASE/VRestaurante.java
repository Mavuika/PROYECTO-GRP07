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

public class VRestaurante extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JMenuItem mntmNewMenuItem_1;
	private JMenuItem mntmNewMenuItem_2;
	private JMenuItem mntmNewMenuItem_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VRestaurante frame = new VRestaurante();
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
	public VRestaurante() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\swank\\Downloads\\ProyectoLenin\\PROYECTO-GRP07\\S2\\Imagenes\\NorkysIco.PNG"));
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Archivo");
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem = new JMenuItem("Salir");
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);
		
		mnNewMenu_1 = new JMenu("Opciones");
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_2 = new JMenu("Registro");
		mnNewMenu_1.add(mnNewMenu_2);
		
		mntmNewMenuItem_1 = new JMenuItem("Registrar productos");
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		mntmNewMenuItem_3 = new JMenuItem("Registrar ventas");
		mntmNewMenuItem_3.addActionListener(this);
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		mntmNewMenuItem_2 = new JMenuItem("Inventario");
		mntmNewMenuItem_2.addActionListener(this);
		mnNewMenu_1.add(mntmNewMenuItem_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmNewMenuItem) {
			do_mntmNewMenuItem_actionPerformed(e);
		}
		if (e.getSource() == mntmNewMenuItem_2) {
			do_mntmNewMenuItem_2_actionPerformed(e);
		}
		if (e.getSource() == mntmNewMenuItem_3) {
			do_mntmNewMenuItem_3_actionPerformed(e);
		}
		if (e.getSource() == mntmNewMenuItem_1) {
			do_mntmNewMenuItem_1_actionPerformed(e);
		}
	}
	protected void do_mntmNewMenuItem_1_actionPerformed(ActionEvent e) {
		//EventHandlerDeRproductos
		Rproductos pro=new Rproductos();
		pro.setVisible(true);
		dispose();
	}
	protected void do_mntmNewMenuItem_3_actionPerformed(ActionEvent e) {
		//EventHandlerDeRventas
		Rventas ven=new Rventas();
		ven.setVisible(true);
		dispose();
	}
	protected void do_mntmNewMenuItem_2_actionPerformed(ActionEvent e) {
		//EventHandlerDeInventario
		Rinventario inv=new Rinventario();
		inv.setVisible(true);
		dispose();
	}
	protected void do_mntmNewMenuItem_actionPerformed(ActionEvent e) {
		//SALIDA
		System.exit(0);
	}
}
