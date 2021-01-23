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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Kullan�c�Ekle {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public Kullan�c�Ekle() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneKullan�c�;

    @FXML
    private TextField ad�;

    @FXML
    private TextField soyad�;

    @FXML
    private TextField tel;

    @FXML
    private TextField kullan�c�ad�;

    @FXML
    private TextArea adres;

    @FXML
    private PasswordField parola;

    @FXML
    private ComboBox<String> comborol;

    @FXML
    private ComboBox<String> combodurum;

    @FXML
    private TableView<Kullan�cKay�tlar> tableview;

    @FXML
    private TableColumn<Kullan�cKay�tlar, Integer> ID;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Ad;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Soyad;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Tel;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Adres;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Kullan�c�Ad�;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Parola;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Rol;

    @FXML
    private TableColumn<Kullan�cKay�tlar, String> Durum;

    @FXML
    void evenGuncelle(ActionEvent event) {
sorgu="update  kullaniciekle set Adi=?,Soyadi=?,Tel=?,Adres=?,KullaniciAdi=?,Parola=?,Rol=?,Durum=? where ID=?";
    	
    	Kullan�cKay�tlar kay�tlar=new Kullan�cKay�tlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kay�tlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kay�tlar.getId();
        	try {
    			sorguifadesi=baglanti.prepareStatement(sorgu);
    			sorguifadesi.setString(1, ad�.getText().trim());
    			sorguifadesi.setString(2, soyad�.getText().trim());
    			sorguifadesi.setString(3,String.valueOf( tel.getText().trim()));
    			sorguifadesi.setString(4, adres.getText().trim());
    			sorguifadesi.setString(5, kullan�c�ad�.getText().trim());
    			sorguifadesi.setString(6, parola.getText().trim());
    			sorguifadesi.setString(7, comborol.getSelectionModel().getSelectedItem().trim());
    			sorguifadesi.setString(8,combodurum.getSelectionModel().getSelectedItem().trim());
    			sorguifadesi.setInt(9, id);
    			sorguifadesi.executeUpdate();
    			Kayitlar(tableview);
    			Alert alert=new Alert(AlertType.CONFIRMATION);
    			alert.setContentText("Guncelleme Basarili");
    			alert.showAndWait();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	
    	
    	
    }

    @FXML
    void evenS�l(ActionEvent event) {
    	
    	sorgu="delete from kullaniciekle where ID=?";
    	
    	Kullan�cKay�tlar kay�tlar=new Kullan�cKay�tlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kay�tlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kay�tlar.getId();
        	try {
    			sorguifadesi=baglanti.prepareStatement(sorgu);
    			sorguifadesi.setInt(1, id);
    			sorguifadesi.executeUpdate();
    			Kayitlar(tableview);
    			Alert alert=new Alert(AlertType.CONFIRMATION);
    			alert.setContentText("Silme Basarili");
    			alert.showAndWait();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
			
		}
    	
    	

    }

    @FXML
    void eventkulan�c�ekle(ActionEvent event) {
    	
    	sorgu="insert into kullaniciekle(Adi,Soyadi,Tel,Adres,KullaniciAdi,Parola,Rol,Durum)"
    			+ "values(?,?,?,?,?,?,?,?)";
    	
    	try {
    		sorguifadesi=baglanti.prepareStatement(sorgu);
			
    		sorguifadesi.setString(1, ad�.getText().trim());
    		sorguifadesi.setString(2, soyad�.getText().trim());
    		sorguifadesi.setString(3, String.valueOf(tel.getText().trim()));
    		sorguifadesi.setString(4, adres.getText().trim());
    		sorguifadesi.setString(5, kullan�c�ad�.getText().trim());
    		sorguifadesi.setString(6, parola.getText().trim());
    		sorguifadesi.setString(7, comborol.getSelectionModel().getSelectedItem().trim());
    		sorguifadesi.setString(8, combodurum.getSelectionModel().getSelectedItem().trim());
    	
    		sorguifadesi.executeUpdate();
    		Kayitlar(tableview);
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setContentText("Ekleme Basarili");
			alert.showAndWait();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void tableviewClick(MouseEvent event) {
    	Kullan�cKay�tlar kay�tlar=new Kullan�cKay�tlar();
    	kay�tlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    	ad�.setText(kay�tlar.getAd());
    	soyad�.setText(kay�tlar.getSoyad());
    	tel.setText(kay�tlar.getTel());
    	adres.setText(kay�tlar.getAdres());
    	kullan�c�ad�.setText(kay�tlar.getKullaniciadi());
    	parola.setText(kay�tlar.getParola());
    	comborol.getSelectionModel().select(kay�tlar.getRol());
    	combodurum.getSelectionModel().select(kay�tlar.getDurum());
    	

    }
    public void Kayitlar(TableView<Kullan�cKay�tlar> tablo) {
    	sorgu="select *from kullaniciekle";
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			
			getirilen=sorguifadesi.executeQuery();
			ObservableList<Kullan�cKay�tlar> verilerKay�tlars=FXCollections.observableArrayList();
			
			while(getirilen.next()) {
				verilerKay�tlars.add(new Kullan�cKay�tlar(getirilen.getInt("ID"), getirilen.getString("Adi"), getirilen.getString("Soyadi"),
						getirilen.getString("Tel"), getirilen.getString("Adres"), getirilen.getString("KullaniciAdi"), getirilen.getString("Parola"),
						getirilen.getString("Rol"), getirilen.getString("Durum")));
			}
			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
			Ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
			Soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
			Tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
			Adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
			Kullan�c�Ad�.setCellValueFactory(new PropertyValueFactory<>("kullaniciadi"));
			Parola.setCellValueFactory(new PropertyValueFactory<>("parola"));
			Rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
			Durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
			tableview.setItems(verilerKay�tlars);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

    @FXML
    void initialize() {
    	Kayitlar(tableview);
       combodurum.getItems().addAll("Aktif","Pasif");
       comborol.getItems().addAll("Admin","Musteri");

    }
}
