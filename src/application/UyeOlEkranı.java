package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class UyeOlEkraný {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	
	public UyeOlEkraný() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneUyeOl;

    @FXML
    private TextField adi;

    @FXML
    private PasswordField sifre;

    @FXML
    private TextField email;

    @FXML
    private PasswordField sifretekrar;

    @FXML
    void eventuyeol(ActionEvent event) {
sorgu="insert into girisekraný (Adý,Sifre,Mail) values(?,?,?)";
    	
    	
    	String hataString="";
    	
    	if (!(sifre.getText().equals(sifretekrar.getText())&&sifre.getText().length()==sifretekrar.getText().length())) {
    		
    		hataString="Þifreler ayný olmalý";
			
		}
    	if (hataString.length()==0) {
    		try {
    			sorguifadesi=baglanti.prepareStatement(sorgu);
    			
    			sorguifadesi.setString(1, adi.getText().trim());
    			sorguifadesi.setString(2, sifre.getText().trim());
    			sorguifadesi.setString(3, email.getText().trim());
    			
    			sorguifadesi.executeUpdate();
    			Alert alert=new Alert(AlertType.CONFIRMATION);
    			alert.setContentText("Uye Basarili");
    			alert.showAndWait();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	else {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setContentText(hataString);
			alert.showAndWait();
		}
    	

    }

    @FXML
    void initialize() {
       

    }
}
