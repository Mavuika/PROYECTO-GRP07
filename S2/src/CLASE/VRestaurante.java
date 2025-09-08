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

public class VRestaurante extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Registro de ventas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//----------------------------------
				Rventas newframe = new Rventas();
				newframe.setVisible(true);
				dispose(); //evita la acumulación de ventanas
				//Código investigado en este video: https://www.youtube.com/watch?v=h2oTM6ehU-E
			}
		});
		btnNewButton.setBounds(110, 85, 183, 27);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Registro de productos");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rproductos newframe = new Rproductos();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setBounds(110, 124, 183, 27);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Inventario");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rinventario newframe = new Rinventario();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1_1.setBounds(110, 163, 183, 27);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("Historial de Ingresos");
		btnNewButton_1_1_1.setBounds(110, 202, 188, 27);
		contentPane.add(btnNewButton_1_1_1);
		
		JLabel lblMen = new JLabel("MENÚ");
		lblMen.setBounds(175, 31, 60, 17);
		contentPane.add(lblMen);

	}
}
