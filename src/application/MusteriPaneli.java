package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;

public class MusteriPaneli {
	
	String sorgu="";
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
	ResultSet getirilen=null;
	
	public MusteriPaneli() {
		
		baglanti=Connect.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane PaneMusteriPaneli;

    @FXML
    private ComboBox<String> ComboPizza;

    @FXML
    private ComboBox<String> comboMenu;

    @FXML
    private ComboBox<String> comboicecek;

    @FXML
    private ComboBox<String> comboekurun;

    @FXML
    private ComboBox<Integer> combopizaamiktar;

    @FXML
    private ComboBox<Integer> combomenumiktar;

    @FXML
    private ComboBox<Integer> comboicecekmiktar;

    @FXML
    private ComboBox<Integer> comboekurunmiktar;

    @FXML
    private ComboBox<String> comboboyutsec;

    @FXML
    private Label lblpizzasonuc;

    @FXML
    private TextArea txtareaSiparisListesi;

    @FXML
    private TextField txtadi;

    @FXML
    private TextField txtsoyadi;

    @FXML
    private TextField txttel;

    @FXML
    private TextArea txtareaaciklama;

    @FXML
    private TextArea txtareaadres;

    @FXML
    private Label lblmenusonuc;

    @FXML
    private Label lbliceceksonuc;

    @FXML
    private Label lblekurunsouc;

    @FXML
    private Label lblToplamSonuc;

    @FXML
    void btnlistesil(ActionEvent event) {
    	
    	comboboyutsec.getSelectionModel().select(null);
    	comboekurun.getSelectionModel().select(null);
    	comboekurunmiktar.getSelectionModel().select(null);
    	comboicecek.getSelectionModel().select(null);
    	comboicecekmiktar.getSelectionModel().select(null);
    	comboMenu.getSelectionModel().select(null);
    	combomenumiktar.getSelectionModel().select(null);
    	combopizaamiktar.getSelectionModel().select(null);
    	ComboPizza.getSelectionModel().select(null);
    	
    	lblekurunsouc.setText(null);
    	lbliceceksonuc.setText(null);
    	lblmenusonuc.setText(null);
    	lblpizzasonuc.setText(null);
    	lblToplamSonuc.setText(null);
    	txtareaSiparisListesi.setText(null);
    	

    }

    @FXML
    void btnsepeteekle(ActionEvent event) {
    	
    	txtareaSiparisListesi.setText("Pizza:"+ComboPizza.getSelectionModel().getSelectedItem()+"Boyut:"+comboboyutsec.getSelectionModel().getSelectedItem()
    			+"Fiyat:"+lblpizzasonuc.getText()+"\n"+
    			"Menu:"+comboMenu.getSelectionModel().getSelectedItem()+"Fiyat:"+lblmenusonuc.getText()+"\n"+
    			"Icecek:"+comboicecek.getSelectionModel().getSelectedItem()+"Fiyat:"+lbliceceksonuc.getText()+"\n"+
    			"Ek Urun:"+comboekurun.getSelectionModel().getSelectedItem()+"Fiyat:"+lblekurunsouc.getText());
    	
    	int deger1=Integer.parseInt(lblekurunsouc.getText());
    	int deger2=Integer.parseInt(lbliceceksonuc.getText());
    	int deger3=Integer.parseInt(lblmenusonuc.getText());
    	int deger4=Integer.parseInt(lblpizzasonuc.getText());
    	int sonuc=deger1+deger2+deger3+deger4;
    	lblToplamSonuc.setText(String.valueOf(sonuc));

    }

    @FXML
    void siparistamamla(ActionEvent event) {
    	sorgu="insert into siparisliste(musteri_adi,musteri_soyadi,tutar,sip_pizza,sip_menu,sip_icecek,sip_ekgida,durum) "
    			+ "values(?,?,?,?,?,?,?,?)";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sorgu);
			sorguifadesi.setString(1, txtadi.getText().trim());
			sorguifadesi.setString(2, txtsoyadi.getText().trim());
			sorguifadesi.setDouble(3,Double.parseDouble(lblToplamSonuc.getText()));
			sorguifadesi.setString(4, ComboPizza.getSelectionModel().getSelectedItem().trim());
			sorguifadesi.setString(5, comboMenu.getSelectionModel().getSelectedItem().trim());
			sorguifadesi.setString(6, comboicecek.getSelectionModel().getSelectedItem().trim());
			sorguifadesi.setString(7, comboekurun.getSelectionModel().getSelectedItem().trim());
			sorguifadesi.setString(8,"Teslim Edilmedi");
			
			sorguifadesi.executeUpdate();
			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setContentText("ekleme basarili");
			alert.show();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    @FXML
    void eventcomboekurunmiktar(ActionEvent event) {
       	int sonuc=0;
    	if (comboekurun.getSelectionModel().getSelectedIndex()==0) {
    		
    		sonuc=10;
    		sonuc=sonuc*comboekurunmiktar.getSelectionModel().getSelectedItem();
    		lblekurunsouc.setText(String.valueOf(sonuc));
			
		}

    }

    @FXML
    void eventcomboicecekmiktar(ActionEvent event) {
    	int sonuc=0;
    	if (comboicecek.getSelectionModel().getSelectedIndex()==0) {
    		
    		sonuc=5;
    		sonuc=sonuc*comboicecekmiktar.getSelectionModel().getSelectedItem();
    		lbliceceksonuc.setText(String.valueOf(sonuc));
			
		}
       if (comboicecek.getSelectionModel().getSelectedIndex()==1) {
    		
    		sonuc=10;
    		sonuc=sonuc*comboicecekmiktar.getSelectionModel().getSelectedItem();
    		lbliceceksonuc.setText(String.valueOf(sonuc));
			
		}

    }

    @FXML
    void eventcombomenumiktar(ActionEvent event) {
    	int sonuc=0;
    	if (comboMenu.getSelectionModel().getSelectedIndex()==0) {
    		
    		sonuc=30;
    		sonuc=sonuc*combomenumiktar.getSelectionModel().getSelectedItem();
    		lblmenusonuc.setText(String.valueOf(sonuc));
			
		}
       if (comboMenu.getSelectionModel().getSelectedIndex()==1) {
    		
    		sonuc=40;
    		sonuc=sonuc*combomenumiktar.getSelectionModel().getSelectedItem();
    		lblmenusonuc.setText(String.valueOf(sonuc));
			
		}
    	

    }

    @FXML
    void eventcombopizaamiktar(ActionEvent event) throws NullPointerException {
    	
    	if (ComboPizza.getSelectionModel().getSelectedItem().equals("Sasasa")) {
    		int sonuc=0;
    		if (comboboyutsec.getSelectionModel().getSelectedIndex()==0) {
    			
				sonuc=20;
			}
    		if (comboboyutsec.getSelectionModel().getSelectedIndex()==1) {
    			sonuc=15;
			}
           if (comboboyutsec.getSelectionModel().getSelectedIndex()==2) {
        	   sonuc=10;
			}
    		
    		sonuc=sonuc*combopizaamiktar.getSelectionModel().getSelectedItem();
    		lblpizzasonuc.setText(String.valueOf(sonuc));
			
		}
    	if (ComboPizza.getSelectionModel().getSelectedItem().equals("Ekspresso Pizza")) {
    		int sonuc=0;
    		if (comboboyutsec.getSelectionModel().getSelectedIndex()==0) {
    			
				sonuc=30;
			}
    		if (comboboyutsec.getSelectionModel().getSelectedIndex()==1) {
    			sonuc=20;
			}
           if (comboboyutsec.getSelectionModel().getSelectedIndex()==2) {
        	   sonuc=15;
			}
    		
    		sonuc=sonuc*combopizaamiktar.getSelectionModel().getSelectedItem();
    		lblpizzasonuc.setText(String.valueOf(sonuc));
			
		}

    }


    @FXML
    void initialize() {
    	ComboPizza.getItems().addAll("Sasasa","Ekspresso Pizza");
    	comboMenu.getItems().addAll("Buyuk menu","kral menu");
    	comboicecek.getItems().addAll("visne suyu ","elma suyu");
    	comboekurun.getItems().addAll("muz ");
    	comboboyutsec.getItems().addAll("buyuk ","orta","kucuk");
    	
    	for (int i = 0; i < 10; i++) {
			combopizaamiktar.getItems().add(i);
			comboekurunmiktar.getItems().add(i);
			comboicecekmiktar.getItems().add(i);
			combomenumiktar.getItems().add(i);
		}
    	
       

    }
}
