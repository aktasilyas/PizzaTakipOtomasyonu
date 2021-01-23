package application;

public class KullanýcKayýtlar {
	
	private int id;
	private String ad;
	private String soyad;
	private String tel;
	private String adres;
	private String kullaniciadi;
	private String parola;
	private String rol;
	private String durum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAd() {
		return ad;
	}
	public void setAd(String ad) {
		this.ad = ad;
	}
	public String getSoyad() {
		return soyad;
	}
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdres() {
		return adres;
	}
	public void setAdres(String adres) {
		this.adres = adres;
	}
	public String getKullaniciadi() {
		return kullaniciadi;
	}
	public void setKullaniciadi(String kullaniciadi) {
		this.kullaniciadi = kullaniciadi;
	}
	public String getParola() {
		return parola;
	}
	public void setParola(String parola) {
		this.parola = parola;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getDurum() {
		return durum;
	}
	public void setDurum(String durum) {
		this.durum = durum;
	}
	public KullanýcKayýtlar(int id, String ad, String soyad, String tel, String adres, String kullaniciadi,
			String parola, String rol, String durum) {
		super();
		this.id = id;
		this.ad = ad;
		this.soyad = soyad;
		this.tel = tel;
		this.adres = adres;
		this.kullaniciadi = kullaniciadi;
		this.parola = parola;
		this.rol = rol;
		this.durum = durum;
	}
	public KullanýcKayýtlar() {
		super();
	}
	

}
