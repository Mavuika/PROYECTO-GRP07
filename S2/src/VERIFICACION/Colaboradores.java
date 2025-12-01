package VERIFICACION;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Colaboradores extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel lbln;
	private JLabel lblNewLabel_1;
	private JLabel lbln_1;
	private JLabel lblNewLabel_2;
	private JLabel label_2;
	private JLabel lbln_2;
	private JLabel lblNewLabel_3;
	private JLabel label_3;
	private JLabel lbln_3;
	private JLabel lblNewLabel_4;
	private JLabel label_4;
	private JLabel lbln_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Colaboradores frame = new Colaboradores();
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
	public Colaboradores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("SCHRADER VALERA, MATHIAS VALENTINO");
		lblNewLabel.setBounds(59, 113, 214, 32);
		contentPane.add(lblNewLabel);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\swank\\OneDrive\\Escritorio\\LeninFinal\\PROYECTO-GRP07\\S2\\Imagenes\\MS.png"));
		label.setBounds(97, 136, 126, 165);
		contentPane.add(label);
		
		lbln = new JLabel("(N00437910)");
		lbln.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbln.setBounds(118, 289, 83, 32);
		contentPane.add(lbln);
		
		lblNewLabel_1 = new JLabel("MONZÓN ARREA, ALEJANDRO MANUEL");
		lblNewLabel_1.setBounds(297, 113, 214, 32);
		contentPane.add(lblNewLabel_1);
		
		lbln_1 = new JLabel("(N00441452)");
		lbln_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbln_1.setBounds(356, 289, 83, 32);
		contentPane.add(lbln_1);
		
		lblNewLabel_2 = new JLabel("SCHRADER VALERA, MATHIAS VALENTINO");
		lblNewLabel_2.setBounds(543, 113, 214, 32);
		contentPane.add(lblNewLabel_2);
		
		label_2 = new JLabel("");
		label_2.setIcon(new ImageIcon("C:\\Users\\swank\\OneDrive\\Escritorio\\LeninFinal\\PROYECTO-GRP07\\S2\\Imagenes\\MS.png"));
		label_2.setBounds(581, 136, 126, 165);
		contentPane.add(label_2);
		
		lbln_2 = new JLabel("(N00437910)");
		lbln_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbln_2.setBounds(602, 289, 83, 32);
		contentPane.add(lbln_2);
		
		lblNewLabel_3 = new JLabel("SCHRADER VALERA, MATHIAS VALENTINO");
		lblNewLabel_3.setBounds(433, 353, 214, 32);
		contentPane.add(lblNewLabel_3);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("C:\\Users\\swank\\OneDrive\\Escritorio\\LeninFinal\\PROYECTO-GRP07\\S2\\Imagenes\\MS.png"));
		label_3.setBounds(471, 376, 126, 165);
		contentPane.add(label_3);
		
		lbln_3 = new JLabel("(N00437910)");
		lbln_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbln_3.setBounds(492, 529, 83, 32);
		contentPane.add(lbln_3);
		
		lblNewLabel_4 = new JLabel("SCHRADER VALERA, MATHIAS VALENTINO");
		lblNewLabel_4.setBounds(155, 353, 214, 32);
		contentPane.add(lblNewLabel_4);
		
		label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon("C:\\Users\\swank\\OneDrive\\Escritorio\\LeninFinal\\PROYECTO-GRP07\\S2\\Imagenes\\MS.png"));
		label_4.setBounds(193, 376, 126, 165);
		contentPane.add(label_4);
		
		lbln_4 = new JLabel("(N00437910)");
		lbln_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbln_4.setBounds(214, 529, 83, 32);
		contentPane.add(lbln_4);
		
		lblNewLabel_5 = new JLabel("COLABORADORES");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_5.setBounds(308, 45, 186, 32);
		contentPane.add(lblNewLabel_5);
		
		btnNewButton = new JButton("VOLVER");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 579, 110, 32);
		contentPane.add(btnNewButton);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\swank\\OneDrive\\Escritorio\\LeninFinal\\PROYECTO-GRP07\\S2\\Imagenes\\MS.png"));
		label_1.setBounds(333, 136, 126, 165);
		contentPane.add(label_1);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNewButton) {
			do_btnNewButton_actionPerformed(e);
		}
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		Login log = new Login();
		log.setVisible(true);
		this.dispose();
	}
}
