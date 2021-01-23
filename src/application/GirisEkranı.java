package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GirisEkran� {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public GirisEkran�() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneGiris;

    @FXML
    private TextField adi;

    @FXML
    private PasswordField sifre;
    
    
    
   

    @FXML
    void eventgiris(ActionEvent event) {
    
				sorgu="select*from girisekran�";
				try {
				sorguifadesi=baglanti.prepareStatement(sorgu);
				getirilen=sorguifadesi.executeQuery();
				 while(getirilen.next()) {
	    		    	
	    		    	if(getirilen.getString("Ad�").equals(adi.getText())&&getirilen.getString("Sifre").equals(sifre.getText())){
	    		    		
	    		    		try {
	    		    			AnchorPane	pane = (AnchorPane)FXMLLoader.load(getClass().getResource("AnaSayfa.fxml"));
	    		    			Scene scene=new Scene(pane);
	    		    	    	  Stage stage=new Stage();
	    		    	    	  stage.initModality(Modality.APPLICATION_MODAL);
	    		    	    	  stage.initStyle(StageStyle.UTILITY);
	    		    	    	  stage.setScene(scene);
	    		    	    	  paneGiris.getScene().getWindow().hide();
	    		    	    	  stage.showAndWait();
	    		    			
	    		    		} catch (IOException e) {
	    		    			// TODO Auto-generated catch block
	    		    			e.printStackTrace();
	    		    		}
	    		    	 
	    		    		
	    		    	}
				 }
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		
    	


    

    @FXML
    void eventuyeol(ActionEvent event) {
    	try {
			AnchorPane	pane = (AnchorPane)FXMLLoader.load(getClass().getResource("UyeOlEkrani.fxml"));
			Scene scene=new Scene(pane);
	    	  Stage stage=new Stage();
	    	  stage.initModality(Modality.APPLICATION_MODAL);
	    	  stage.initStyle(StageStyle.UTILITY);
	    	  stage.setScene(scene);
	    	  stage.showAndWait();
	    	 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }

    @FXML
    void initialize() {
       

    }
}
