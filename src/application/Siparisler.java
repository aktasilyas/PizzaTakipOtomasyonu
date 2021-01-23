package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Siparisler {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public Siparisler() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneSiparisler;

    @FXML
    private TextField txtMadsoyad;

    @FXML
    private DatePicker SiparisTarihi;

    @FXML
    private DatePicker ParaAlinmaTarihi;

    @FXML
    private DatePicker TeslimTarihi;

    @FXML
    private TextField tutar;
    @FXML
    private ComboBox<Integer> comboSiparisListe;

    @FXML
    private ComboBox<Integer> Comboiskonto;
    @FXML
    private TableView<SiparisKayitlar> tableview;

    @FXML
    private TableColumn<SiparisKayitlar, Integer> ID;

    @FXML
    private TableColumn<SiparisKayitlar, Integer> S_Liste;

    @FXML
    private TableColumn<SiparisKayitlar, Integer> iskonto;

    @FXML
    private TableColumn<SiparisKayitlar, String> M_AdSoyad;

    @FXML
    private TableColumn<SiparisKayitlar, String> S_Tarihi;

    @FXML
    private TableColumn<SiparisKayitlar, String> P_Alinma_tarihi;

    @FXML
    private TableColumn<SiparisKayitlar, String> T_tarihi;

    @FXML
    private TableColumn<SiparisKayitlar, Integer> Tutar;
    

    @FXML
    void Comboiskonto(ActionEvent event) {
    	System.out.println(gelentutar);
    	
    	if (Comboiskonto.getSelectionModel().getSelectedIndex()==0) {
    		 tutar.setText(String.valueOf(gelentutar));
			int g=Integer.parseInt(tutar.getText());
			int sonuc=g-g*50/100;
			tutar.setText(String.valueOf(sonuc));
		}
        if (Comboiskonto.getSelectionModel().getSelectedIndex()==1) {
        	tutar.setText(String.valueOf(gelentutar));
        	int g=Integer.parseInt(tutar.getText());
			int sonuc=g-g*75/100;
			tutar.setText(String.valueOf(sonuc));
			
		}
       if (Comboiskonto.getSelectionModel().getSelectedIndex()==2) {
    	   tutar.setText(String.valueOf(gelentutar));
    	   int g=Integer.parseInt(tutar.getText());
			int sonuc=g-g*100/100;
			tutar.setText(String.valueOf(sonuc));
	
        }
    	

    }

    @FXML
    void btnAnasayfa(ActionEvent event) {
    	try {
			AnchorPane	pane = (AnchorPane)FXMLLoader.load(getClass().getResource("AnaSayfa.fxml"));
			Scene scene=new Scene(pane);
	    	  Stage stage=new Stage();
	    	  stage.initModality(Modality.APPLICATION_MODAL);
	    	  stage.initStyle(StageStyle.UTILITY);
	    	  stage.setScene(scene);
	    	  stage.showAndWait();
	    	  stage.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	

    }

    @FXML
    void btnSTeslim(ActionEvent event) {
    	
    	
    	
    	sorgu="insert into siparisler(siparisListe,iskonto,MusteriAdSoyad,siparisTarihi,paraAlinmaTarihi,TeslimTarihi,tutar) "
    			+ "values(?,?,?,?,?,?,?)";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			sorguifadesi.setInt(1, comboSiparisListe.getSelectionModel().getSelectedItem());
			sorguifadesi.setInt(2, Comboiskonto.getSelectionModel().getSelectedItem());
			sorguifadesi.setString(3,txtMadsoyad.getText().trim());
			sorguifadesi.setString(4,String.valueOf(SiparisTarihi.getValue()));
			sorguifadesi.setString(5,String.valueOf(ParaAlinmaTarihi.getValue()));
			sorguifadesi.setString(6, String.valueOf(TeslimTarihi.getValue()));
			sorguifadesi.setDouble(7,Double.parseDouble(tutar.getText()));
			
			sorguifadesi.executeUpdate();
			Kayitlarim(tableview);
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setContentText("ekleme basarili");
			alert.show();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void btnSsil(ActionEvent event) {
    	sorgu="delete from siparisler where ID=?";
    	
    	SiparisKayitlar kayitlar=new SiparisKayitlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kayitlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int gelenid=kayitlar.getId();
    		try {
				sorguifadesi=baglanti.prepareStatement(sorgu);
				sorguifadesi.setInt(1, gelenid);
				sorguifadesi.executeUpdate();
				Kayitlarim(tableview);
				Alert alert=new Alert(AlertType.CONFIRMATION);
				alert.setContentText("silme basarili");
				alert.show();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			
		}
    	else {
    		Alert alert=new Alert(AlertType.ERROR);
			alert.setContentText("bir kayit  seciniz");
			alert.show();
		}

    }
  int gelentutar=0;
    @FXML
    void combosiparisListe(ActionEvent event) {
    	  sorgu="select * from siparisliste where ID=?";
    	  
      	
      	try {
  			sorguifadesi=baglanti.prepareStatement(sorgu);
  			sorguifadesi.setString(1, String.valueOf(comboSiparisListe.getSelectionModel().getSelectedItem()));
  			getirilen=sorguifadesi.executeQuery();
  			
  			while(getirilen.next()) {
  				txtMadsoyad.setText(getirilen.getString("musteri_adi")+" "+ getirilen.getString("musteri_soyadi"));
  				tutar.setText(String.valueOf(getirilen.getInt("tutar")));
  				
  				
  			}
  			
  			
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}

      	 gelentutar=Integer.parseInt(tutar.getText());
    }

     public void Kayitlarim(TableView<SiparisKayitlar> tablom) {
    	 sorgu="select * from siparisler";
   	  
       	
       	try {
   			sorguifadesi=baglanti.prepareStatement(sorgu);
   			getirilen=sorguifadesi.executeQuery();
   			ObservableList<SiparisKayitlar> verilerKayitlars=FXCollections.observableArrayList();
   			while(getirilen.next()) {
   				verilerKayitlars.add(new SiparisKayitlar(getirilen.getInt("ID"), getirilen.getInt("siparisListe"), 
   						getirilen.getInt("iskonto"),getirilen.getString("MusteriAdSoyad"), 
   						getirilen.getString("siparisTarihi"), getirilen.getString("paraAlinmaTarihi"),
   						getirilen.getString("TeslimTarihi"), getirilen.getInt("tutar")));
   				
   				
   			}
   			
   			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
   			S_Liste.setCellValueFactory(new PropertyValueFactory<>("siparisliste"));
   			iskonto.setCellValueFactory(new PropertyValueFactory<>("iskonto"));
   			M_AdSoyad.setCellValueFactory(new PropertyValueFactory<>("adsoyad"));
   			S_Tarihi.setCellValueFactory(new PropertyValueFactory<>("starihi"));
   			P_Alinma_tarihi.setCellValueFactory(new PropertyValueFactory<>("painmatarihi"));
   			T_tarihi.setCellValueFactory(new PropertyValueFactory<>("ttarihi"));
   			Tutar.setCellValueFactory(new PropertyValueFactory<>("tutar"));
   			tableview.setItems(verilerKayitlars);
       	} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
		
	}
    @FXML
    void initialize() {
    	Kayitlarim(tableview);
    	Comboiskonto.getItems().addAll(50,75,100);
    	
    	sorgu="select * from siparisliste";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			getirilen=sorguifadesi.executeQuery();
			
			while(getirilen.next()) {
				comboSiparisListe.getItems().addAll(getirilen.getInt("ID"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
      

    }
}
