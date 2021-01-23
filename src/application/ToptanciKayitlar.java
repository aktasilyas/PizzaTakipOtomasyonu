package application;

public class ToptanciKayitlar {
private int id;
private String firma;
private String mali;
private int miktar;
public ToptanciKayitlar() {
	super();
}
public ToptanciKayitlar(int id, String firma, String mali, int miktar, String cins, int fiyat) {
	super();
	this.id = id;
	this.firma = firma;
	this.mali = mali;
	this.miktar = miktar;
	this.cins = cins;
	this.fiyat = fiyat;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFirma() {
	return firma;
}
public void setFirma(String firma) {
	this.firma = firma;
}
public String getMali() {
	return mali;
}
public void setMali(String mali) {
	this.mali = mali;
}
public int getMiktar() {
	return miktar;
}
public void setMiktar(int miktar) {
	this.miktar = miktar;
}
public String getCins() {
	return cins;
}
public void setCins(String cins) {
	this.cins = cins;
}
public int getFiyat() {
	return fiyat;
}
public void setFiyat(int fiyat) {
	this.fiyat = fiyat;
}
private String cins;
private int fiyat;

}
