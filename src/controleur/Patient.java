package controleur;

public class Patient {
	private int idPatient;
	private String nom, prenom, email, telephone, password, adresse, numeroSecu;
	private int age;
	
	public Patient(int idPatient, String nom, String prenom, int age, String email, String password, String telephone, String adresse, String numeroSecu) {
		this.idPatient = idPatient;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.password = password;
		this.telephone = telephone;
		this.adresse = adresse;
		this.numeroSecu = numeroSecu;
	}
	
	public Patient(String nom, String prenom, int age, String email, String telephone, String adresse, String numeroSecu) {
		this.idPatient = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.age = age;
		this.email = email;
		this.telephone = telephone;
		this.adresse = adresse;
		this.numeroSecu = numeroSecu;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumeroSecu() {
		return numeroSecu;
	}

	public void setNumeroSecu(String numeroSecu) {
		this.numeroSecu = numeroSecu;
	}
}
