package application;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class Toptanci {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public Toptanci() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane paneToptanci;

    @FXML
    private TextField txtfirma;

    @FXML
    private TextField txtmali;

    @FXML
    private TextField txtmiktar;

    @FXML
    private TextField txtcins;

    @FXML
    private TextField txtfiyat;

    @FXML
    private Button ekle;

    @FXML
    private Button sil;

    @FXML
    private TableView<ToptanciKayitlar> tableview;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> ID;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> T_Firmasi;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> T_Mali;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> T_Miktar;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> T_Cins;

    @FXML
    private TableColumn<ToptanciKayitlar, Integer> T_Fiyat;

    @FXML
    void btnekle(ActionEvent event) {

    	sorgu="insert into toptanci(firma,mali,miktar,cins,fiyat) "
    			+ "values(?,?,?,?,?)";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			sorguifadesi.setString(1, txtfirma.getText().trim());
			sorguifadesi.setString(2,txtmali.getText().trim());
			sorguifadesi.setInt(3,Integer.parseInt(txtmiktar.getText().trim()));
			sorguifadesi.setString(4,txtcins.getText().trim());
			sorguifadesi.setInt(5,Integer.parseInt(txtfiyat.getText().trim()));
			
			
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
    void btnsil(ActionEvent event) {
sorgu="delete from toptanci where ID=?";
    	
    	ToptanciKayitlar kayitlar=new ToptanciKayitlar();
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
    public void Kayitlarim(TableView<ToptanciKayitlar> tablom) {
   	 sorgu="select * from toptanci";
  	  
      	
      	try {
  			sorguifadesi=baglanti.prepareStatement(sorgu);
  			getirilen=sorguifadesi.executeQuery();
  			ObservableList<ToptanciKayitlar> verilerKayitlars=FXCollections.observableArrayList();
  			while(getirilen.next()) {
  				verilerKayitlars.add(new ToptanciKayitlar(getirilen.getInt("ID"), getirilen.getString("firma"), 
  						getirilen.getString("mali"),getirilen.getInt("miktar"), 
  						getirilen.getString("cins"), getirilen.getInt("fiyat")));
  					
  				
  				
  			}
  			
  			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
  			T_Firmasi.setCellValueFactory(new PropertyValueFactory<>("firma"));
  			T_Mali.setCellValueFactory(new PropertyValueFactory<>("mali"));
  			T_Miktar.setCellValueFactory(new PropertyValueFactory<>("miktar"));
  			T_Cins.setCellValueFactory(new PropertyValueFactory<>("cins"));
  			T_Fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
  			
  			tableview.setItems(verilerKayitlars);
      	} catch (SQLException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
		
	}

    @FXML
    void initialize() {
       Kayitlarim(tableview);

    }
}
