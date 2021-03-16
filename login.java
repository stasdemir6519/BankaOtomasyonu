import com.stasdemir.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JToolBar;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends veritabani{

	private JFrame frmiftlikBankLogin;
	private JTextField input_username;
	private JPasswordField password_input;

	
	public static void main (String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmiftlikBankLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//ABSTRACT FONKSİYON IMPLEMENT EDİLİYOR
	public String setFrameTitleName() {
		return null;
	}
	
	public login() {
		super("root", "", "bank_database", "account_info");
		initialize();
	}

	private void initialize() {
		frmiftlikBankLogin = new JFrame();
		frmiftlikBankLogin.setTitle("\u00C7iftlik Bank Giri\u015F Ekran\u0131");
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		frmiftlikBankLogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\JavaProje\\TL_134-163.jpg"));
		lblNewLabel.setBounds(238, 125, 128, 128);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanici Adi");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(56, 305, 133, 31);
		panel.add(lblNewLabel_1);
		
		input_username = new JTextField();
		input_username.setBounds(172, 309, 285, 27);
		panel.add(input_username);
		input_username.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("\u015Eifre");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1_1.setBounds(114, 346, 56, 31);
		panel.add(lblNewLabel_1_1);
		
		password_input = new JPasswordField();
		password_input.setEchoChar('✽');
		password_input.setBounds(172, 350, 285, 27);
		panel.add(password_input);
		
		JButton btnNewButton = new JButton("Giri\u015F Yap");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Sorgu gönderildi..");
				if(access_control(input_username.getText(),String.valueOf(password_input.getPassword())) == true){
					JOptionPane.showMessageDialog(null,"Hesap Bulundu: " + veriler[0] + " " + veriler [1], "Başarılı", 3);
					hesapBilgileri hb = new hesapBilgileri();
					hb.getJframe().setVisible(true);
					//main_cb2 cb2 = new main_cb2();
					//cb2.getJframe().setVisible(true);
					//frmiftlikBankLogin.setVisible(false);			
				}
				else {
					JOptionPane.showMessageDialog(null, "Hesap Bulunamadı!", "Başarısız", 0);
				}	
			}
		});
		btnNewButton.setBounds(269, 404, 97, 21);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("\u00C7IFTLIK BANK");
		lblNewLabel_2.setBackground(Color.ORANGE);
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Microsoft Yi Baiti", Font.BOLD, 35));
		lblNewLabel_2.setBounds(200, 79, 214, 29);
		panel.add(lblNewLabel_2);
	} 
}
