import com.stasdemir.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPasswordField;

public class reset_password extends veritabani{

	private JFrame frame;
	private JPasswordField eski_sifre;
	private JPasswordField yeni_sifre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					reset_password window = new reset_password();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public reset_password() {
		super(getDatabase_username(),getDatabase_password(),getDatabase_name(),getDatabase_table_name());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
	private void initialize() {
		frame = new JFrame("Şifresi Sıfırlanacak Hesap: " + veriler[2]);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\JavaProje\\TL_134-163.jpg"));
		frame.setBounds(100, 100, 392, 250);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Eski \u015Eifreniz");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(40, 61, 97, 19);
		panel.add(lblNewLabel);
		
		JLabel lblYeniifreniz = new JLabel("Yeni \u015Eifreniz");
		lblYeniifreniz.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblYeniifreniz.setBounds(40, 90, 97, 19);
		panel.add(lblYeniifreniz);
		
		JButton btnNewButton = new JButton("\u015Eifreyi De\u011Fi\u015Ftir");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(141, 135, 156, 35);
		panel.add(btnNewButton);
		
		eski_sifre = new JPasswordField();
		eski_sifre.setBounds(147, 63, 139, 19);
		panel.add(eski_sifre);
		
		yeni_sifre = new JPasswordField();
		yeni_sifre.setBounds(147, 92, 138, 19);
		panel.add(yeni_sifre);
		
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String get_oldpass = String.valueOf(eski_sifre.getPassword()); 
				String get_newpass = String.valueOf(yeni_sifre.getPassword());
				
				reset_pass(veriler[2],get_oldpass,get_newpass);
			}
		});
	}
	
	//ABSTRACT FONKSİYON IMPLEMENT EDİLİYOR
	public String setFrameTitleName() {
		return null;
	}
}
