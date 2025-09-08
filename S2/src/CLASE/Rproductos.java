package CLASE;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Rproductos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rproductos frame = new Rproductos();
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
	public Rproductos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistroDeProductos = new JLabel("REGISTRO DE PRODUCTOS");
		lblRegistroDeProductos.setBounds(24, 0, 173, 33);
		contentPane.add(lblRegistroDeProductos);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(227, 12, 211, 239);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRestaurante newframe = new VRestaurante();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(52, 181, 105, 27);
		contentPane.add(btnRegresar);

	}

}
