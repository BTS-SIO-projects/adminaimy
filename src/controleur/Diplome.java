package controleur;

public class Diplome {
	private int idDiplome;
	private String nom, sigle, faculte;
	
	public Diplome(int idDiplome, String nom, String sigle, String faculte) {
		this.idDiplome = idDiplome;
		this.nom = nom;
		this.sigle = sigle;
		this.faculte = faculte;
	}
	
	public Diplome(String nom, String sigle, String faculte) {
		this.idDiplome = 0;
		this.nom = nom;
		this.sigle = sigle;
		this.faculte = faculte;
	}

	public int getIdDiplome() {
		return idDiplome;
	}

	public void setIdDiplome(int idDiplome) {
		this.idDiplome = idDiplome;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSigle() {
		return sigle;
	}

	public void setSigle(String sigle) {
		this.sigle = sigle;
	}

	public String getFaculte() {
		return faculte;
	}

	public void setFaculte(String faculte) {
		this.faculte = faculte;
	}
}