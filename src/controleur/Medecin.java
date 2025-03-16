package controleur;

import java.sql.Blob;

public class Medecin {
	private int idMedecin;
	private String nom, prenom, email, password, telephone, statut;
	private int idSpecialite, idLieu;
	private Blob photo;
	
	public Medecin(int idMedecin, String nom, String prenom, String email, String password, String telephone, String statut, Blob photo, int idSpecialite, int idLieu) {
		this.idMedecin = idMedecin;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.statut = statut;
		this.photo = photo;
		this.idSpecialite = idSpecialite;
		this.idLieu = idLieu;
	}
	
	public Medecin(String nom, String prenom, String email, String telephone, String statut, Blob photo, int idSpecialite, int idLieu) {
		this.idMedecin = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.statut = statut;
		this.idSpecialite = idSpecialite;
		this.idLieu = idLieu;
	}

	public int getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Blob getPhoto() {
		return photo;
	}

	public void setPhoto(Blob photo) {
		this.photo= photo;
	}

	public int getIdSpecialite() {
		return idSpecialite;
	}

	public void setIdSpecialite(int idSpecialite) {
		this.idSpecialite = idSpecialite;
	}

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}
}
