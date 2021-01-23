package application;

public class SiparisKayitlar {
  private int id;
  private int siparisliste;
  private int iskonto;
  private String adsoyad;
  private String starihi;
  private String painmatarihi;
  public SiparisKayitlar() {
	super();
}
private String ttarihi;
  public SiparisKayitlar(int id, int siparisliste, int iskonto, String adsoyad, String starihi, String painmatarihi,
		String ttarihi, int tutar) {
	super();
	this.id = id;
	this.siparisliste = siparisliste;
	this.iskonto = iskonto;
	this.adsoyad = adsoyad;
	this.starihi = starihi;
	this.painmatarihi = painmatarihi;
	this.ttarihi = ttarihi;
	this.tutar = tutar;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getSiparisliste() {
	return siparisliste;
}
public void setSiparisliste(int siparisliste) {
	this.siparisliste = siparisliste;
}
public int getIskonto() {
	return iskonto;
}
public void setIskonto(int iskonto) {
	this.iskonto = iskonto;
}
public String getAdsoyad() {
	return adsoyad;
}
public void setAdsoyad(String adsoyad) {
	this.adsoyad = adsoyad;
}
public String getStarihi() {
	return starihi;
}
public void setStarihi(String starihi) {
	this.starihi = starihi;
}
public String getPainmatarihi() {
	return painmatarihi;
}
public void setPainmatarihi(String painmatarihi) {
	this.painmatarihi = painmatarihi;
}
public String getTtarihi() {
	return ttarihi;
}
public void setTtarihi(String ttarihi) {
	this.ttarihi = ttarihi;
}
public int getTutar() {
	return tutar;
}
public void setTutar(int tutar) {
	this.tutar = tutar;
}
private int tutar;
}
