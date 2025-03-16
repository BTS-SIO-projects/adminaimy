package controleur;

import java.sql.Date;

public class Document {
	private int idDocument;
	private String typeDoc, url, description;
	private Date dateDepot;
	private int idMedecin, idPatient, idRdv;
	
	public Document(int idDocument, String typeDoc, String url, Date dateDepot, String description, int idMedecin, int idPatient, int idRdv) {
		this.idDocument = idDocument;
		this.typeDoc = typeDoc;
		this.url = url;
		this.dateDepot = dateDepot;
		this.description = description;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		this.idRdv = idRdv;
	}
	
	public Document(String typeDoc, String url, Date dateDepot, String description, int idMedecin, int idPatient, int idRdv) {
		this.idDocument = 0;
		this.typeDoc = typeDoc;
		this.url = url;
		this.dateDepot = dateDepot;
		this.description = description;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		this.idRdv = idRdv;
	}

	public int getIdDocument() {
		return idDocument;
	}

	public void setIdDocument(int idDocument) {
		this.idDocument = idDocument;
	}

	public String getTypeDoc() {
		return typeDoc;
	}

	public void setTypeDoc(String typeDoc) {
		this.typeDoc = typeDoc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDateDepot() {
		return dateDepot;
	}

	public void setDateDepot(Date dateDepot) {
		this.dateDepot = dateDepot;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(int idMedecin) {
		this.idMedecin = idMedecin;
	}

	public int getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(int idPatient) {
		this.idPatient = idPatient;
	}

	public int getIdRdv() {
		return idRdv;
	}

	public void setIdRdv(int idRdv) {
		this.idRdv = idRdv;
	}
}