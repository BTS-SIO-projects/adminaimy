package controleur;

import java.sql.Date;
import java.sql.Time;

public class Rdv {
	private int idRdv;
	private String motif;
	private Date dateRdv;
	private Time heureRdv;
	private int idMedecin, idPatient, idLieu;
	
	public Rdv(int idRdv, Date dateRdv, Time heureRdv, String motif, int idMedecin, int idPatient, int idLieu) {
		this.idRdv = idRdv;
		this.dateRdv = dateRdv;
		this.heureRdv = heureRdv;
		this.motif = motif;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		this.idLieu = idLieu;
	}
	
	public Rdv(Date dateRdv, Time heureRdv, String motif, int idMedecin, int idPatient, int idLieu) {
		this.idRdv = 0;
		this.dateRdv = dateRdv;
		this.heureRdv = heureRdv;
		this.motif = motif;
		this.idMedecin = idMedecin;
		this.idPatient = idPatient;
		this.idLieu = idLieu;
	}

	public int getIdRdv() {
		return idRdv;
	}

	public void setIdRdv(int idRdv) {
		this.idRdv = idRdv;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public Time getHeureRdv() {
		return heureRdv;
	}

	public void setHeureRdv(Time heureRdv) {
		this.heureRdv = heureRdv;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
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

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}
}