package controleur;

public class Lieu {
	private int idLieu;
	private String nom, adresse, typeLieu;
	
	public Lieu(int idLieu, String nom, String adresse, String typeLieu) {
		this.idLieu = idLieu;
		this.nom = nom;
		this.adresse = adresse;
		this.typeLieu = typeLieu;
	}
	
	public Lieu(String nom, String adresse, String typeLieu) {
		this.idLieu = 0;
		this.nom = nom;
		this.adresse = adresse;
		this.typeLieu = typeLieu;
	}

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getTypeLieu() {
		return typeLieu;
	}

	public void setTypeLieu(String typeLieu) {
		this.typeLieu = typeLieu;
	}
}
