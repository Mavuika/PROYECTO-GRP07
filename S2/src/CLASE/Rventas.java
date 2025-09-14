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

public class Rventas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Rventas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(208, 12, 230, 239);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblRegistroDeVentas = new JLabel("REGISTRO DE VENTAS");
		lblRegistroDeVentas.setBounds(34, 0, 141, 33);
		contentPane.add(lblRegistroDeVentas);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VRestaurante newframe = new VRestaurante();
				newframe.setVisible(true);
				dispose();
			}
		});
		btnRegresar.setBounds(49, 207, 105, 27);
		contentPane.add(btnRegresar);
		
		JButton btnReporte = new JButton("Reporte");
		btnReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnReporte_actionPerformed(e);
			}
		});
		btnReporte.setBounds(33, 60, 85, 21);
		contentPane.add(btnReporte);
		
	

	}
	
	//Reporte
	protected void do_btnReporte_actionPerformed(ActionEvent e) {
	
	}
	void Reporte()
	{
		
	}
}
