package controleur;

import java.util.ArrayList;
import modele.Modele;

public class Controleur {
	/********* Gestion des Patients ********/
	public static void insertPatient(Patient unPatient) {
		Modele.insertPatient(unPatient);
	}
	
	public static ArrayList<Patient> selectAllPatients() {
		return Modele.selectAllPatients();
	}
	
	public static ArrayList<Patient> selectLikePatients(String filtre) {
		return Modele.selectLikePatients(filtre);
	}
	
	public static void deletePatient(int idPatient) {
		Modele.deletePatient(idPatient);
	}
	
	public static void updatePatient(Patient unPatient) {
		Modele.updatePatient(unPatient);
	}
	
	public static Patient selectWherePatient(int idPatient) {
		return Modele.selectWherePatient(idPatient);
	}

	/********* Gestion des Médecins ********/
	public static void insertMedecin(Medecin unMedecin) {
		Modele.insertMedecin(unMedecin);
	}
	
	public static ArrayList<Medecin> selectAllMedecins() {
		return Modele.selectAllMedecins();
	}
	
	public static ArrayList<Medecin> selectLikeMedecins(String filtre) {
		return Modele.selectLikeMedecins(filtre);
	}
	
	public static void deleteMedecin(int idMedecin) {
		Modele.deleteMedecin(idMedecin);
	}
	
	public static void updateMedecin(Medecin unMedecin) {
		Modele.updateMedecin(unMedecin);
	}
	
	public static Medecin selectWhereMedecin(int idMedecin) {
		return Modele.selectWhereMedecin(idMedecin);
	}

	/********* Gestion des RDV ********/
	public static void insertRdv(Rdv unRdv) {
		Modele.insertRdv(unRdv);
	}
	
	public static ArrayList<Rdv> selectAllRdvs() {
		return Modele.selectAllRdvs();
	}
	
	public static ArrayList<Rdv> selectLikeRdvs(String filtre) {
		return Modele.selectLikeRdvs(filtre);
	}
	
	public static void deleteRdv(int idRdv) {
		Modele.deleteRdv(idRdv);
	}
	
	public static void updateRdv(Rdv unRdv) {
		Modele.updateRdv(unRdv);
	}
	
	public static Rdv selectWhereRdv(int idRdv) {
		return Modele.selectWhereRdv(idRdv);
	}

	/********* Gestion des Documents ********/
	public static void insertDocument(Document unDocument) {
		Modele.insertDocument(unDocument);
	}
	
	public static ArrayList<Document> selectAllDocuments() {
		return Modele.selectAllDocuments();
	}
	
	public static ArrayList<Document> selectLikeDocuments(String filtre) {
		return Modele.selectLikeDocuments(filtre);
	}
	
	public static void deleteDocument(int idDocument) {
		Modele.deleteDocument(idDocument);
	}
	
	public static void updateDocument(Document unDocument) {
		Modele.updateDocument(unDocument);
	}
	
	public static Document selectWhereDocument(int idDocument) {
		return Modele.selectWhereDocument(idDocument);
	}

	/********* Gestion des Diplômes ********/
	public static void insertDiplome(Diplome unDiplome) {
		Modele.insertDiplome(unDiplome);
	}
	
	public static ArrayList<Diplome> selectAllDiplomes() {
		return Modele.selectAllDiplomes();
	}
	
	public static ArrayList<Diplome> selectLikeDiplomes(String filtre) {
		return Modele.selectLikeDiplomes(filtre);
	}
	
	public static void deleteDiplome(int idDiplome) {
		Modele.deleteDiplome(idDiplome);
	}
	
	public static void updateDiplome(Diplome unDiplome) {
		Modele.updateDiplome(unDiplome);
	}
	
	public static Diplome selectWhereDiplome(int idDiplome) {
		return Modele.selectWhereDiplome(idDiplome);
	}
	
	/********* Gestion des Specialités ********/
	public static void insertSpecialite(Specialite uneSpecialite) {
		Modele.insertSpecialite(uneSpecialite);
	}
	
	public static ArrayList<Specialite> selectAllSpecialites() {
		return Modele.selectAllSpecialites();
	}
	
	public static ArrayList<Specialite> selectLikeSpecialites(String filtre) {
		return Modele.selectLikeSpecialites(filtre);
	}
	
	public static int selectLikeSpecialite(String categorie) {
		return Modele.selectLikeSpecialite(categorie);
	}
	
	public static void deleteSpecialite(int idSpecialite) {
		Modele.deleteSpecialite(idSpecialite);
	}
	
	public static void updateSpecialite(Specialite unSpecialite) {
		Modele.updateSpecialite(unSpecialite);
	}
	
	public static Specialite selectWhereSpecialite(int idSpecialite) {
		return Modele.selectWhereSpecialite(idSpecialite);
	}

	/********* Gestion des Administrateurs ********/
	public static void insertAdministrateur(Administrateur unAdministrateur) {
		Modele.insertAdministrateur(unAdministrateur);
	}
	
	public static ArrayList<Administrateur> selectAllAdministrateurs() {
		return Modele.selectAllAdministrateurs();
	}
	
	public static ArrayList<Administrateur> selectLikeAdministrateurs(String filtre) {
		return Modele.selectLikeAdministrateurs(filtre);
	}
	
	public static void deleteAdministrateur(int idAdministrateur) {
		Modele.deleteAdministrateur(idAdministrateur);
	}
	
	public static void updateAdministrateur(Administrateur unAdministrateur) {
		Modele.updateAdministrateur(unAdministrateur);
	}
	
	public static Administrateur selectWhereAdministrateur(int idAdministrateur) {
		return Modele.selectWhereAdministrateur(idAdministrateur);
	}

	public static Administrateur selectWhereAdministrateur(String email, String password) {
		return Modele.selectWhereAdministrateur(email, password);
	}

	/********* Gestion des Lieux ********/
	public static void insertLieu(Lieu unLieu) {
	    Modele.insertLieu(unLieu);
	}

	public static ArrayList<Lieu> selectAllLieux() {
	    return Modele.selectAllLieux();
	}

	public static ArrayList<Lieu> selectLikeLieux(String filtre) {
	    return Modele.selectLikeLieux(filtre);
	}

	public static int selectLikeLieu(String nom) {
	    return Modele.selectLikeLieu(nom);
	}

	public static void deleteLieu(int idLieu) {
	    Modele.deleteLieu(idLieu);
	}

	public static void updateLieu(Lieu unLieu) {
	    Modele.updateLieu(unLieu);
	}

	public static Lieu selectWhereLieu(int idLieu) {
	    return Modele.selectWhereLieu(idLieu);
	}

}