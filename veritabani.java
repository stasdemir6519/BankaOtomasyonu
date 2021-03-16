package com.stasdemir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

//ABSTRACTION
public abstract class veritabani {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	//STATİK DİZİ VE DEĞİŞKEN TANIMLAMA, PROTECTED VE PRIVATE ERİŞİM BELİRLEYİCİLER
	protected static String [] veriler = new String[8];
	protected static double [] kurDegerleri = new double[3];
	static int anlikHesapVeritabani;
	static double faizlenmişTutar;
	
	
	private static String database_username= "";
	private static String database_password= "";
	private String query= "";
	private String query2= "";
	private static String database_name= "";
	private static String database_table_name= "";
	
	//ABSTRACT FONKSİYON TANIMLANIYOR.
	public abstract String setFrameTitleName(); 
	
	//STATİK FONKSİYON OLUŞTURMA
	public static String getDatabase_username() {
		return database_username;
	}
	//statik metoddan statik nesneye erişiliyor

	public void setDatabase_username(String database_username) {
		veritabani.database_username = database_username;
	}
	//nonstatic metoddan static değişkene erişiliyor. 

	public static String getDatabase_password() {
		return database_password;
	}
	//statik metoddan statik nesneye erişiliyor

	public void setDatabase_password(String database_password) {
		veritabani.database_password = database_password;
	}
	//nonstatic metoddan static değişkene erişiliyor. 

	public static String getDatabase_name() {
		return database_name;
	}

	public void setDatabase_name(String database_name) {
		veritabani.database_name = database_name;
	}

	public static String getDatabase_table_name() {
		return database_table_name;
	}

	public void setDatabase_table_name(String database_table_name) {
		veritabani.database_table_name = database_table_name;
	}
	
	//CONSTRUCTOR
	public veritabani(String database_username, String database_password, String database_name, String database_table_name) {
		
		this.setDatabase_username(database_username);
		this.setDatabase_password(database_password);
		this.setDatabase_name(database_name);
		this.setDatabase_table_name(database_table_name);
		
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());  
			//MySql Connector ekledigimiz Drive'ı tanımladık ve ismini aldık
			
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307" + "/" + database_name , database_username, database_password);
			//Veritabanı ile bağlantıyı sağlar(format:DriverManager.getConnection(url, user, password))
			
			System.out.println("Bağlandı ->" + database_name);
			//bağlantının başarılı olması halinde konsola yazdırılır.
			
			stat = (Statement) conn.createStatement();
			//oluşturulan bağlantı aracılığıyla sql sorgusu gönderebilmek için Statement arayüzünden yaratılan stat nesnesi aktive edildi.
			
		}
		catch(ClassNotFoundException e) {
			System.err.println("Connector bulunamadı.");
			System.err.println("Bağlantı başarısız\nClassNotFoundException");
		}
		//ClassNotFoundException hatası durumunda ekrana yazdırır
		
		catch(SQLException e) {
			System.err.println("Bağlantı başarısız\nSqlException");
		}
		//SQL Exception hatası durumunda ekrana yazdırır
		
		if(conn != null) {
			System.out.println("Bağlantı tamam!");
		}
		else {
			System.out.println("Bağlantı başarısız!");
		}
	}

	/* access_control() metoduyla veritabanı sorgusu yapılmakta ve hata olması durumunda '0' 
	başarılı olması durumunda '1' değerleri döndürülmektedir*/
	public boolean access_control(String username, String password) {
		try { 
			query = "select * from account_info where username ='"+username+"' and password ='"+password+"' ";
			//sql sorgusu oluşturuldu
			
			rs = stat.executeQuery(query);
			//sorgunun sonucu rs değişkenine atanıyor
			
			while(rs.next()) {
				System.out.println("Kullanıcı adı: " + rs.getString("username"));
				System.out.println("Parola: " + rs.getString("password"));
				//Kullanıcı adı ve parola konsola yazdırılıyor
				
				//veritabanından getirilen veriler veriler dizisine yerleştiriliyor
				veriler[0] = rs.getString("name");
				veriler[1] = rs.getString("surname");
				veriler[2] = rs.getString("username");
				veriler[3] = rs.getString("password");
				veriler[4] = rs.getString("amountOfMoney_Now");
				veriler[5] = rs.getString("amountOfSpent");
				anlikHesapVeritabani = Integer.parseInt(veriler[4]) - Integer.parseInt(veriler[5]);
				//typecasting yapılıyor(string-integer)
				
				faizlenmişTutar = anlikHesapVeritabani * 1.18 / 365;
				veriler[6] = String.valueOf(anlikHesapVeritabani);
				veriler[7] = String.valueOf(faizlenmişTutar);
				//typecasting yapılıyor(integer-string)
				
				
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public void pass_update(String reset_username, String yeni_sifre) {
		query = "update account_info set password = ? where username =?";
		//şifre değiştirme için girilen yeni şifreyi veritabanına göndererek eski şifreyle değiştirilmesini sağlayan sorgu ifadesi
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, yeni_sifre);
			preparedStatement.setString(2, reset_username);
			//preparedStatement nesnesi ile metodun parametreleri kullanılarak güncelleme sorgusu oluşturuluyor.
			
			preparedStatement.executeUpdate();
			//oluşturulan sorgu gerçekleştiriliyor.
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}
	//PreparedStatement arayüzünden nesne yaratılarak şifre değiştirme işlemi gerçekleştiriliyor.
	
	public void reset_pass(String username, String eski_sifre, String yeni_sifre) {
		if(!eski_sifre.equals(veriler[3])) {
			
			JOptionPane.showMessageDialog(null, "Eski şifrenizi hatalı girdiniz!","Başarısız",JOptionPane.ERROR_MESSAGE);
			//Ekrana çıkan ikaz mesajı
			
			System.err.println("Eski şifre hatalı girildi");
			//Konsola yazdırılan ikaz mesajı
		}
		
		else {
			
			pass_update(username,yeni_sifre);
			//pass update() metodu çağrılarak şifre güncelleniyor.
			
			JOptionPane.showMessageDialog(null, "Şifreniz Güncellendi!","Başarılı",JOptionPane.INFORMATION_MESSAGE);
			//güncellemenin başarılı olduğuna dair mesaj kutusu ekrana çıkıyor.
		}
	}
	/*Kullanıcı tarafından girilen eski şifrenin veritabanından çekilen şifreyle eşit olması halinde şifreyi değiştiriyor; 
	aksi durumda girilen şifrenin yanlış olduğu yönünde ikaz mesajı çıkıyor*/
	
	public boolean setCurrencyAccount(){
		try { 
			query2 = "select * from dovizkurlari";
			//sql sorgusu oluşturuldu
			
			rs = stat.executeQuery(query2);
			//sorgunun sonucu rs değişkenine atanıyor
			
			while(rs.next()) {
				System.out.println("Dolar Kuru: " + rs.getDouble("dolar"));
				System.out.println("Euro Kuru: " + rs.getDouble("euro"));
				System.out.println("Sterlin Kuru: " + rs.getDouble("sterlin"));
				//Döviz Kurları konsola yazdırılıyor
				
				kurDegerleri[0] = rs.getDouble("dolar");
				kurDegerleri[1] = rs.getDouble("euro");
				kurDegerleri[2] = rs.getDouble("sterlin");
				
				//veritabanından getirilen veriler kurDegerleri dizisine yerleştiriliyor
				
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	}
	


package com.stasdemir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;

//ABSTRACTION
public abstract class veritabani {
	Connection conn = null;
	Statement stat = null;
	ResultSet rs = null;
	
	//STATİK DİZİ VE DEĞİŞKEN TANIMLAMA, PROTECTED VE PRIVATE ERİŞİM BELİRLEYİCİLER
	protected static String [] veriler = new String[8];
	protected static double [] kurDegerleri = new double[3];
	static int anlikHesapVeritabani;
	static double faizlenmişTutar;
	
	
	private static String database_username= "";
	private static String database_password= "";
	private String query= "";
	private String query2= "";
	private static String database_name= "";
	private static String database_table_name= "";
	
	//ABSTRACT FONKSİYON TANIMLANIYOR.
	public abstract String setFrameTitleName(); 
	
	//STATİK FONKSİYON OLUŞTURMA
	public static String getDatabase_username() {
		return database_username;
	}
	//statik metoddan statik nesneye erişiliyor

	public void setDatabase_username(String database_username) {
		veritabani.database_username = database_username;
	}
	//nonstatic metoddan static değişkene erişiliyor. 

	public static String getDatabase_password() {
		return database_password;
	}
	//statik metoddan statik nesneye erişiliyor

	public void setDatabase_password(String database_password) {
		veritabani.database_password = database_password;
	}
	//nonstatic metoddan static değişkene erişiliyor. 

	public static String getDatabase_name() {
		return database_name;
	}

	public void setDatabase_name(String database_name) {
		veritabani.database_name = database_name;
	}

	public static String getDatabase_table_name() {
		return database_table_name;
	}

	public void setDatabase_table_name(String database_table_name) {
		veritabani.database_table_name = database_table_name;
	}
	
	//CONSTRUCTOR
	public veritabani(String database_username, String database_password, String database_name, String database_table_name) {
		
		this.setDatabase_username(database_username);
		this.setDatabase_password(database_password);
		this.setDatabase_name(database_name);
		this.setDatabase_table_name(database_table_name);
		
		try {
			Class.forName(com.mysql.jdbc.Driver.class.getName());  
			//MySql Connector ekledigimiz Drive'ı tanımladık ve ismini aldık
			
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307" + "/" + database_name , database_username, database_password);
			//Veritabanı ile bağlantıyı sağlar(format:DriverManager.getConnection(url, user, password))
			
			System.out.println("Bağlandı ->" + database_name);
			//bağlantının başarılı olması halinde konsola yazdırılır.
			
			stat = (Statement) conn.createStatement();
			//oluşturulan bağlantı aracılığıyla sql sorgusu gönderebilmek için Statement arayüzünden yaratılan stat nesnesi aktive edildi.
			
		}
		catch(ClassNotFoundException e) {
			System.err.println("Connector bulunamadı.");
			System.err.println("Bağlantı başarısız\nClassNotFoundException");
		}
		//ClassNotFoundException hatası durumunda ekrana yazdırır
		
		catch(SQLException e) {
			System.err.println("Bağlantı başarısız\nSqlException");
		}
		//SQL Exception hatası durumunda ekrana yazdırır
		
		if(conn != null) {
			System.out.println("Bağlantı tamam!");
		}
		else {
			System.out.println("Bağlantı başarısız!");
		}
	}

	/* access_control() metoduyla veritabanı sorgusu yapılmakta ve hata olması durumunda '0' 
	başarılı olması durumunda '1' değerleri döndürülmektedir*/
	public boolean access_control(String username, String password) {
		try { 
			query = "select * from account_info where username ='"+username+"' and password ='"+password+"' ";
			//sql sorgusu oluşturuldu
			
			rs = stat.executeQuery(query);
			//sorgunun sonucu rs değişkenine atanıyor
			
			while(rs.next()) {
				System.out.println("Kullanıcı adı: " + rs.getString("username"));
				System.out.println("Parola: " + rs.getString("password"));
				//Kullanıcı adı ve parola konsola yazdırılıyor
				
				//veritabanından getirilen veriler veriler dizisine yerleştiriliyor
				veriler[0] = rs.getString("name");
				veriler[1] = rs.getString("surname");
				veriler[2] = rs.getString("username");
				veriler[3] = rs.getString("password");
				veriler[4] = rs.getString("amountOfMoney_Now");
				veriler[5] = rs.getString("amountOfSpent");
				anlikHesapVeritabani = Integer.parseInt(veriler[4]) - Integer.parseInt(veriler[5]);
				//typecasting yapılıyor(string-integer)
				
				faizlenmişTutar = anlikHesapVeritabani * 1.18 / 365;
				veriler[6] = String.valueOf(anlikHesapVeritabani);
				veriler[7] = String.valueOf(faizlenmişTutar);
				//typecasting yapılıyor(integer-string)
				
				
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public void pass_update(String reset_username, String yeni_sifre) {
		query = "update account_info set password = ? where username =?";
		//şifre değiştirme için girilen yeni şifreyi veritabanına göndererek eski şifreyle değiştirilmesini sağlayan sorgu ifadesi
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(query);
			preparedStatement.setString(1, yeni_sifre);
			preparedStatement.setString(2, reset_username);
			//preparedStatement nesnesi ile metodun parametreleri kullanılarak güncelleme sorgusu oluşturuluyor.
			
			preparedStatement.executeUpdate();
			//oluşturulan sorgu gerçekleştiriliyor.
		}
		catch(SQLException e) {
			e.printStackTrace(); 
		}
	}
	//PreparedStatement arayüzünden nesne yaratılarak şifre değiştirme işlemi gerçekleştiriliyor.
	
	public void reset_pass(String username, String eski_sifre, String yeni_sifre) {
		if(!eski_sifre.equals(veriler[3])) {
			
			JOptionPane.showMessageDialog(null, "Eski şifrenizi hatalı girdiniz!","Başarısız",JOptionPane.ERROR_MESSAGE);
			//Ekrana çıkan ikaz mesajı
			
			System.err.println("Eski şifre hatalı girildi");
			//Konsola yazdırılan ikaz mesajı
		}
		
		else {
			
			pass_update(username,yeni_sifre);
			//pass update() metodu çağrılarak şifre güncelleniyor.
			
			JOptionPane.showMessageDialog(null, "Şifreniz Güncellendi!","Başarılı",JOptionPane.INFORMATION_MESSAGE);
			//güncellemenin başarılı olduğuna dair mesaj kutusu ekrana çıkıyor.
		}
	}
	/*Kullanıcı tarafından girilen eski şifrenin veritabanından çekilen şifreyle eşit olması halinde şifreyi değiştiriyor; 
	aksi durumda girilen şifrenin yanlış olduğu yönünde ikaz mesajı çıkıyor*/
	
	public boolean setCurrencyAccount(){
		try { 
			query2 = "select * from dovizkurlari";
			//sql sorgusu oluşturuldu
			
			rs = stat.executeQuery(query2);
			//sorgunun sonucu rs değişkenine atanıyor
			
			while(rs.next()) {
				System.out.println("Dolar Kuru: " + rs.getDouble("dolar"));
				System.out.println("Euro Kuru: " + rs.getDouble("euro"));
				System.out.println("Sterlin Kuru: " + rs.getDouble("sterlin"));
				//Döviz Kurları konsola yazdırılıyor
				
				kurDegerleri[0] = rs.getDouble("dolar");
				kurDegerleri[1] = rs.getDouble("euro");
				kurDegerleri[2] = rs.getDouble("sterlin");
				
				//veritabanından getirilen veriler kurDegerleri dizisine yerleştiriliyor
				
				return true;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	}
	


