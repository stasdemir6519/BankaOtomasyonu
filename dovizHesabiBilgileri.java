import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class dovizHesabiBilgileri extends dovizHesabiAc{

	private JFrame frmDvizHesabBilgileri;
	double amountOfCurrency;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dovizHesabiBilgileri window = new dovizHesabiBilgileri();
					window.frmDvizHesabBilgileri.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public dovizHesabiBilgileri() {
		initialize();
	}
	
	public JFrame getJframe() {
		return frmDvizHesabBilgileri;
	}
	
	public double getAmountOfCurrency() {
		double divider = 1.0;
		switch(getCurrencyString) {
			case "Dolar":{
				divider = kurDegerleri[0];
				break;
			}
			
			case "Euro":{
				divider = kurDegerleri[1];
				break;
			}
			case "Sterlin":{
				divider = kurDegerleri[2];
				break;
			}
			default:
				break;
		}
		return getAmountOfTL / divider;
	}
	
	//ABSTRACT FONKSİYON IMPLEMENT EDİLİYOR
	public String setFrameTitleName() {
		if ((Integer.parseInt(veriler[5])) != 0)
			return "Döviz Hesabı Bilgileri";
		return null;
	}

	private void initialize() {
		frmDvizHesabBilgileri = new JFrame();
		frmDvizHesabBilgileri.setTitle(setFrameTitleName());
		frmDvizHesabBilgileri.setBounds(100, 100, 536, 227);
		frmDvizHesabBilgileri.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmDvizHesabBilgileri.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		
		JLabel lblDvizHesabiBilgileri = new JLabel(getCurrencyString.toUpperCase() + " HESABI B\u0130LG\u0130LER\u0130");
		lblDvizHesabiBilgileri.setForeground(Color.RED);
		lblDvizHesabiBilgileri.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblDvizHesabiBilgileri.setBounds(168, 10, 250, 28);
		panel.add(lblDvizHesabiBilgileri);
		
		JLabel lblNewLabel_1_1 = new JLabel("Hesabınızda Bulunan " + getCurrencyString + " miktarı      :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1.setBounds(30, 80, 277, 21);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Günlük Faiz Tutarı:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1_1_1.setBounds(30, 111, 277, 21);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel getCurrencyAmount = new JLabel(String.format("%.2f",getAmountOfCurrency()));
		getCurrencyAmount.setFont(new Font("Tahoma", Font.BOLD, 12));
		getCurrencyAmount.setBounds(317, 80, 154, 21);
		panel.add(getCurrencyAmount);
		
		
		JLabel getCurrencyInterest = new JLabel(String.format("%.4f",(getAmountOfCurrency()*0.01)));
		getCurrencyInterest.setFont(new Font("Tahoma", Font.BOLD, 12));
		getCurrencyInterest.setBounds(317, 111, 154, 21);
		panel.add(getCurrencyInterest);
	}
}
