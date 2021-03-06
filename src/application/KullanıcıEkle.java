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

public class KullanıcıEkle {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public KullanıcıEkle() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneKullanıcı;

    @FXML
    private TextField adı;

    @FXML
    private TextField soyadı;

    @FXML
    private TextField tel;

    @FXML
    private TextField kullanıcıadı;

    @FXML
    private TextArea adres;

    @FXML
    private PasswordField parola;

    @FXML
    private ComboBox<String> comborol;

    @FXML
    private ComboBox<String> combodurum;

    @FXML
    private TableView<KullanıcKayıtlar> tableview;

    @FXML
    private TableColumn<KullanıcKayıtlar, Integer> ID;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Ad;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Soyad;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Tel;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Adres;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> KullanıcıAdı;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Parola;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Rol;

    @FXML
    private TableColumn<KullanıcKayıtlar, String> Durum;

    @FXML
    void evenGuncelle(ActionEvent event) {
sorgu="update  kullaniciekle set Adi=?,Soyadi=?,Tel=?,Adres=?,KullaniciAdi=?,Parola=?,Rol=?,Durum=? where ID=?";
    	
    	KullanıcKayıtlar kayıtlar=new KullanıcKayıtlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kayıtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kayıtlar.getId();
        	try {
    			sorguifadesi=baglanti.prepareStatement(sorgu);
    			sorguifadesi.setString(1, adı.getText().trim());
    			sorguifadesi.setString(2, soyadı.getText().trim());
    			sorguifadesi.setString(3,String.valueOf( tel.getText().trim()));
    			sorguifadesi.setString(4, adres.getText().trim());
    			sorguifadesi.setString(5, kullanıcıadı.getText().trim());
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
    void evenSıl(ActionEvent event) {
    	
    	sorgu="delete from kullaniciekle where ID=?";
    	
    	KullanıcKayıtlar kayıtlar=new KullanıcKayıtlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kayıtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kayıtlar.getId();
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
    void eventkulanıcıekle(ActionEvent event) {
    	
    	sorgu="insert into kullaniciekle(Adi,Soyadi,Tel,Adres,KullaniciAdi,Parola,Rol,Durum)"
    			+ "values(?,?,?,?,?,?,?,?)";
    	
    	try {
    		sorguifadesi=baglanti.prepareStatement(sorgu);
			
    		sorguifadesi.setString(1, adı.getText().trim());
    		sorguifadesi.setString(2, soyadı.getText().trim());
    		sorguifadesi.setString(3, String.valueOf(tel.getText().trim()));
    		sorguifadesi.setString(4, adres.getText().trim());
    		sorguifadesi.setString(5, kullanıcıadı.getText().trim());
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
    	KullanıcKayıtlar kayıtlar=new KullanıcKayıtlar();
    	kayıtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    	adı.setText(kayıtlar.getAd());
    	soyadı.setText(kayıtlar.getSoyad());
    	tel.setText(kayıtlar.getTel());
    	adres.setText(kayıtlar.getAdres());
    	kullanıcıadı.setText(kayıtlar.getKullaniciadi());
    	parola.setText(kayıtlar.getParola());
    	comborol.getSelectionModel().select(kayıtlar.getRol());
    	combodurum.getSelectionModel().select(kayıtlar.getDurum());
    	

    }
    public void Kayitlar(TableView<KullanıcKayıtlar> tablo) {
    	sorgu="select *from kullaniciekle";
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			
			getirilen=sorguifadesi.executeQuery();
			ObservableList<KullanıcKayıtlar> verilerKayıtlars=FXCollections.observableArrayList();
			
			while(getirilen.next()) {
				verilerKayıtlars.add(new KullanıcKayıtlar(getirilen.getInt("ID"), getirilen.getString("Adi"), getirilen.getString("Soyadi"),
						getirilen.getString("Tel"), getirilen.getString("Adres"), getirilen.getString("KullaniciAdi"), getirilen.getString("Parola"),
						getirilen.getString("Rol"), getirilen.getString("Durum")));
			}
			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
			Ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
			Soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
			Tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
			Adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
			KullanıcıAdı.setCellValueFactory(new PropertyValueFactory<>("kullaniciadi"));
			Parola.setCellValueFactory(new PropertyValueFactory<>("parola"));
			Rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
			Durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
			tableview.setItems(verilerKayıtlars);
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
