package LectorArchivo;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class gui extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTextArea txtS;
	private JButton btnLeerArchivo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
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
	public gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 210);
		contentPane.add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setEditable(false);
		scrollPane.setViewportView(txtS);
		
		btnLeerArchivo = new JButton("Leer Archivo");
		btnLeerArchivo.addActionListener(this);
		btnLeerArchivo.setBounds(140, 232, 139, 23);
		contentPane.add(btnLeerArchivo);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLeerArchivo) {
			do_btnLeerArchivo_actionPerformed(e);
		}
	}

	//ABRE UN JFILECHOOSER, LO QUE NOS PERMITIRA ESCOGER EL ARCHIVO EN UNA VENTANA 
	protected void do_btnLeerArchivo_actionPerformed(ActionEvent e) {
		JFileChooser chooser = new JFileChooser("datos");
		chooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto y Excel", "txt", "xls", "xlsx"));
		//DEVUELVE LA CARPETA DATOS
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int seleccion = chooser.showOpenDialog(this);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			File f = chooser.getSelectedFile();
			String ruta = f.getAbsolutePath();
			//BOTON DESACTIVADO MIENTRAS CARGA
			btnLeerArchivo.setEnabled(false);
			txtS.setText("Cargando " + ruta + " ...");

			//SE LEE EN EL BACKGROUND PARA NO BLOQUEAR LA INTERFAZ
			new SwingWorker<String, Void>() {
				protected String doInBackground() throws Exception {
					//USA LA CLASE LEERARCHIVO.JAVA
					try {
						return LeerArchivo.leerArchivo(ruta);
					} catch (IOException ex) {
						//REENVIA LA EXCEPCION, ENTONCES CON EX PODREMOS MANEJARLA EN DONE()
						throw ex;
					}
				}

				protected void done() {
					try {
						String contenido = get();
						txtS.setText(contenido);
						txtS.setCaretPosition(0);
						//CARET/CURSORINSERCION:LA LINEA QUE SALE AL ESCRIBIR EN CUALQUIER OBJETO TEXTUAL
					} catch (Exception ex) {
						txtS.setText("");
						JOptionPane.showMessageDialog(gui.this,
								"Error al leer el archivo:\n" + ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
					} finally {
						btnLeerArchivo.setEnabled(true);
					}
				}
			}.execute();
		}
	}
}