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

public class KullanýcýEkle {
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public KullanýcýEkle() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneKullanýcý;

    @FXML
    private TextField adý;

    @FXML
    private TextField soyadý;

    @FXML
    private TextField tel;

    @FXML
    private TextField kullanýcýadý;

    @FXML
    private TextArea adres;

    @FXML
    private PasswordField parola;

    @FXML
    private ComboBox<String> comborol;

    @FXML
    private ComboBox<String> combodurum;

    @FXML
    private TableView<KullanýcKayýtlar> tableview;

    @FXML
    private TableColumn<KullanýcKayýtlar, Integer> ID;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Ad;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Soyad;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Tel;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Adres;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> KullanýcýAdý;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Parola;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Rol;

    @FXML
    private TableColumn<KullanýcKayýtlar, String> Durum;

    @FXML
    void evenGuncelle(ActionEvent event) {
sorgu="update  kullaniciekle set Adi=?,Soyadi=?,Tel=?,Adres=?,KullaniciAdi=?,Parola=?,Rol=?,Durum=? where ID=?";
    	
    	KullanýcKayýtlar kayýtlar=new KullanýcKayýtlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kayýtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kayýtlar.getId();
        	try {
    			sorguifadesi=baglanti.prepareStatement(sorgu);
    			sorguifadesi.setString(1, adý.getText().trim());
    			sorguifadesi.setString(2, soyadý.getText().trim());
    			sorguifadesi.setString(3,String.valueOf( tel.getText().trim()));
    			sorguifadesi.setString(4, adres.getText().trim());
    			sorguifadesi.setString(5, kullanýcýadý.getText().trim());
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
    void evenSýl(ActionEvent event) {
    	
    	sorgu="delete from kullaniciekle where ID=?";
    	
    	KullanýcKayýtlar kayýtlar=new KullanýcKayýtlar();
    	if (tableview.getSelectionModel().getSelectedIndex()!=-1) {
    		kayýtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    		int id=kayýtlar.getId();
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
    void eventkulanýcýekle(ActionEvent event) {
    	
    	sorgu="insert into kullaniciekle(Adi,Soyadi,Tel,Adres,KullaniciAdi,Parola,Rol,Durum)"
    			+ "values(?,?,?,?,?,?,?,?)";
    	
    	try {
    		sorguifadesi=baglanti.prepareStatement(sorgu);
			
    		sorguifadesi.setString(1, adý.getText().trim());
    		sorguifadesi.setString(2, soyadý.getText().trim());
    		sorguifadesi.setString(3, String.valueOf(tel.getText().trim()));
    		sorguifadesi.setString(4, adres.getText().trim());
    		sorguifadesi.setString(5, kullanýcýadý.getText().trim());
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
    	KullanýcKayýtlar kayýtlar=new KullanýcKayýtlar();
    	kayýtlar=tableview.getItems().get(tableview.getSelectionModel().getSelectedIndex());
    	adý.setText(kayýtlar.getAd());
    	soyadý.setText(kayýtlar.getSoyad());
    	tel.setText(kayýtlar.getTel());
    	adres.setText(kayýtlar.getAdres());
    	kullanýcýadý.setText(kayýtlar.getKullaniciadi());
    	parola.setText(kayýtlar.getParola());
    	comborol.getSelectionModel().select(kayýtlar.getRol());
    	combodurum.getSelectionModel().select(kayýtlar.getDurum());
    	

    }
    public void Kayitlar(TableView<KullanýcKayýtlar> tablo) {
    	sorgu="select *from kullaniciekle";
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			
			getirilen=sorguifadesi.executeQuery();
			ObservableList<KullanýcKayýtlar> verilerKayýtlars=FXCollections.observableArrayList();
			
			while(getirilen.next()) {
				verilerKayýtlars.add(new KullanýcKayýtlar(getirilen.getInt("ID"), getirilen.getString("Adi"), getirilen.getString("Soyadi"),
						getirilen.getString("Tel"), getirilen.getString("Adres"), getirilen.getString("KullaniciAdi"), getirilen.getString("Parola"),
						getirilen.getString("Rol"), getirilen.getString("Durum")));
			}
			ID.setCellValueFactory(new PropertyValueFactory<>("id"));
			Ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
			Soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
			Tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
			Adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
			KullanýcýAdý.setCellValueFactory(new PropertyValueFactory<>("kullaniciadi"));
			Parola.setCellValueFactory(new PropertyValueFactory<>("parola"));
			Rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
			Durum.setCellValueFactory(new PropertyValueFactory<>("durum"));
			tableview.setItems(verilerKayýtlars);
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
