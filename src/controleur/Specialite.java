package controleur;

public class Specialite {
	private int idSpecialite;
	private String categorie;
	
	public Specialite(int idSpecialite, String categorie) {
	this.idSpecialite = idSpecialite;
	this.categorie = categorie;
	}
	
	public Specialite(String categorie) {
	this.idSpecialite = 0;
	this.categorie = categorie;
	}
	
	public int getIdSpecialite() {
	return idSpecialite;
	}
	
	public void setIdSpecialite(int idSpecialite) {
	this.idSpecialite = idSpecialite;
	}
	
	public String getCategorie() {
	return categorie;
	}
	
	public void setCategorie(String categorie) {
	this.categorie = categorie;
	}
}
