import com.stasdemir.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class dovizHesabiAc extends veritabani{

	private JFrame frmDvizHesab;
	private JTextField getTL;
	static String getCurrencyString;
	static int getAmountOfTL;
	

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dovizHesabiAc window = new dovizHesabiAc();
					window.frmDvizHesab.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public dovizHesabiAc() {
		super(veritabani.getDatabase_username(),veritabani.getDatabase_password(),veritabani.getDatabase_name(),veritabani.getDatabase_table_name());
		initialize();
	}
	
	public JFrame getFrame() {
		return frmDvizHesab;
	}
	
	//ABSTRACT FONKSİYON IMPLEMENT EDİLİYOR
	public String setFrameTitleName() {
		return null;
	}
	
	private void initialize() {
		frmDvizHesab = new JFrame();
		frmDvizHesab.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmDvizHesab.setTitle("D\u00F6viz Hesab\u0131");
		frmDvizHesab.setBounds(100, 100, 612, 442);
		
		JPanel panel = new JPanel();
		frmDvizHesab.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox comboBox = new JComboBox(); 
		comboBox.setBackground(Color.WHITE);
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Segoe Print", Font.BOLD, 12));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Euro", "Dolar", "Sterlin"}));
		comboBox.setBounds(366, 28, 124, 27);
		panel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Hesap a\u00E7mak istedi\u011Finiz d\u00F6viz cinsini se\u00E7iniz ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(28, 27, 306, 27);
		panel.add(lblNewLabel);
		
		JLabel lblTlHesabnzdanDvize = new JLabel("TL Hesab\u0131n\u0131zdan d\u00F6vize \u00E7evirmek istedi\u011Finiz tutar");
		lblTlHesabnzdanDvize.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTlHesabnzdanDvize.setBounds(28, 65, 318, 27);
		panel.add(lblTlHesabnzdanDvize);
		
		getTL = new JTextField();
		getTL.setBackground(Color.WHITE);
		getTL.setBounds(366, 65, 124, 24);
		panel.add(getTL);
		getTL.setColumns(10);
		
		JButton btnNewButton = new JButton("Hesab\u0131 Olu\u015Ftur");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCurrencyString = comboBox.getSelectedItem().toString();
				getAmountOfTL = Integer.parseInt(getTL.getText());
				dovizHesabiBilgileri dhb = new dovizHesabiBilgileri();
				dhb.getJframe().setVisible(true);
				//hesap bilgileri penceresi kapanacak
				veriler[5] = String.valueOf(Integer.parseInt(veriler[5]) + getAmountOfTL);
				veriler[6] = String.valueOf(Integer.parseInt(veriler[4]) - Integer.parseInt(veriler[5]));
				veriler[7] = String.valueOf(Integer.parseInt(veriler[6]) * 1.18 / 365);
				hesapBilgileri hb1 = new hesapBilgileri();
				hb1.getJframe().setVisible(true);
				
			}
		});
		
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(240, 341, 136, 35);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("KUR DE\u011EERLER\u0130");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(255, 115, 136, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Dolar   :");
		lblNewLabel_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1.setBounds(224, 141, 68, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Euro    :");
		lblNewLabel_1_1_1.setForeground(Color.BLACK);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBackground(SystemColor.menu);
		lblNewLabel_1_1_1.setBounds(224, 167, 68, 27);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Sterlin :");
		lblNewLabel_1_1_2.setForeground(Color.BLACK);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_2.setBackground(SystemColor.menu);
		lblNewLabel_1_1_2.setBounds(224, 194, 68, 27);
		panel.add(lblNewLabel_1_1_2);
		
		JLabel getDollar = new JLabel(String.valueOf(kurDegerleri[0]) + " ₺");
		getDollar.setForeground(Color.BLACK);
		getDollar.setFont(new Font("Tahoma", Font.BOLD, 14));
		getDollar.setBackground(SystemColor.menu);
		getDollar.setBounds(302, 141, 68, 27);
		panel.add(getDollar);
		
		JLabel getEuro = new JLabel(String.valueOf(kurDegerleri[1]) + " ₺");
		getEuro.setForeground(Color.BLACK);
		getEuro.setFont(new Font("Tahoma", Font.BOLD, 14));
		getEuro.setBackground(SystemColor.menu);
		getEuro.setBounds(301, 167, 68, 27);
		panel.add(getEuro);
		
		JLabel getSterlin = new JLabel(String.valueOf(kurDegerleri[1]) + " ₺");
		getSterlin.setForeground(Color.BLACK);
		getSterlin.setFont(new Font("Tahoma", Font.BOLD, 14));
		getSterlin.setBackground(SystemColor.menu);
		getSterlin.setBounds(301, 194, 68, 27);
		panel.add(getSterlin);
	}
}
