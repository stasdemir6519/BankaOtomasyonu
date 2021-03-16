import com.stasdemir.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.SystemColor;


public class hesapBilgileri extends veritabani {

	private JFrame frmHesapBilgileri;
	
	public JFrame getJframe() {
		return frmHesapBilgileri;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hesapBilgileri window = new hesapBilgileri();
					window.frmHesapBilgileri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public hesapBilgileri() {
		super(getDatabase_username(),getDatabase_password(),getDatabase_name(),getDatabase_table_name());
		initialize();
	}

	//ABSTRACT FONKSİYON IMPLEMENT EDİLİYOR
	public String setFrameTitleName() {
		if ((Integer.parseInt(veriler[4])) != 0)
			return "Hesap Bilgileri";
		return null;
	}
	
	private void initialize() {
		frmHesapBilgileri = new JFrame();
		frmHesapBilgileri.setTitle(setFrameTitleName());
		frmHesapBilgileri.setBounds(100, 100, 561, 384);
		frmHesapBilgileri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		frmHesapBilgileri.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HESAP B\u0130LG\u0130LER\u0130");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(190, 20, 169, 28);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("G\u00FCn \u0130\u00E7inde Harcanan Tutar  :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(22, 124, 181, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("G\u00FCn Ba\u015F\u0131 Hesap Durumu      :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(22, 93, 181, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Anl\u0131k Hesap Durumu            :");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2.setBounds(22, 155, 181, 21);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("G\u00FCncel G\u00FCnl\u00FCk Faiz Tutar\u0131     :");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_2_1.setBounds(22, 186, 181, 21);
		panel.add(lblNewLabel_1_2_1);
		
		JButton btnNewButton = new JButton("\u015Eifreyi S\u0131f\u0131rla");
		btnNewButton.setBackground(new Color(240, 240, 240));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBounds(22, 248, 132, 35);
		panel.add(btnNewButton);
		
		JLabel getAmount = new JLabel(veriler[4] + " ₺");
		getAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		getAmount.setBounds(213, 93, 181, 21);
		panel.add(getAmount);
		
		JLabel getSpent = new JLabel(veriler[5] + " ₺");
		getSpent.setFont(new Font("Tahoma", Font.BOLD, 12));
		getSpent.setBounds(213, 124, 181, 21);
		panel.add(getSpent);
		
		String s = new String();
		JLabel anlikHesap = new JLabel(veriler[6] + " ₺");
		anlikHesap.setFont(new Font("Tahoma", Font.BOLD, 12));
		anlikHesap.setBounds(213, 155, 181, 21);
		panel.add(anlikHesap);
		
		JLabel guncelFaiz = new JLabel(veriler[7] + "  ₺");
		guncelFaiz.setFont(new Font("Tahoma", Font.BOLD, 12));
		guncelFaiz.setBounds(213, 186, 181, 21);
		panel.add(guncelFaiz);
		//String.format("%.20f", a)
		
		JButton btnDovizHesabiAc = new JButton("Döviz Hesabı Aç");
		btnDovizHesabiAc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(setCurrencyAccount() == true){
					
					dovizHesabiAc dh = new dovizHesabiAc();
					dh.getFrame().setVisible(true);
					//main_cb2 cb2 = new main_cb2();
					//cb2.getJframe().setVisible(true);
					//frmiftlikBankLogin.setVisible(false);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Hesap Bulunamadı!", "Başarısız", 0);
				}	
			}
		});
				
		btnDovizHesabiAc.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDovizHesabiAc.setBackground(SystemColor.menu);
		btnDovizHesabiAc.setBounds(391, 248, 132, 35);
		panel.add(btnDovizHesabiAc);
		
		JButton btnDovizHesabiGoruntule = new JButton("Döviz Hesabı Görüntüle");
		btnDovizHesabiGoruntule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dovizHesabiAc.getAmountOfTL != 0) {
					dovizHesabiBilgileri dhb = new dovizHesabiBilgileri();
					dhb.getJframe().setVisible(true);
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Döviz Hesabınız bulunmamaktadır!", "Başarısız", 0);
				}
			}
		});
		btnDovizHesabiGoruntule.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDovizHesabiGoruntule.setBackground(SystemColor.menu);
		btnDovizHesabiGoruntule.setBounds(182, 248, 177, 35);
		panel.add(btnDovizHesabiGoruntule);
		
		
		//sonradan eklendi
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reset_password rs = new reset_password();
				rs.getFrame().setVisible(true);
				
			}
		});
		
	}
}


