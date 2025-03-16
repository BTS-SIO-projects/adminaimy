package modele;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Administrateur;
import controleur.Diplome;
import controleur.Document;
import controleur.Lieu;
import controleur.Medecin;
import controleur.Patient;
import controleur.Rdv;
import controleur.Specialite;
import java.sql.Connection;

public class Modele {
	//instanciation d'une connexion

	private static Connexion uneConnexion = new Connexion("localhost:3306", "aimy", "root", "");
	/********* Gestion des patients ********/
	public static void insertPatient(Patient unPatient) {
	    String requete = "INSERT INTO patient (idpatient, nom, prenom, age, email, password, telephone, adresse, numeroSecu) VALUES ('null', '"
	        + unPatient.getNom() + "', '"
	        + unPatient.getPrenom() + "', "
	        + unPatient.getAge() + ", '"
	        + unPatient.getEmail() + "', '"
	        + unPatient.getPassword() + "', '"
	        + unPatient.getTelephone() + "', '"
	        + unPatient.getAdresse() + "', '"
	        + unPatient.getNumeroSecu() + "');";
	    executeRequete(requete);
	}

	public static ArrayList<Patient> selectAllPatients() {
	    String requete = "SELECT * FROM patient;";
	    ArrayList<Patient> lesPatients = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);
	        while (desResultats.next()) {
	            Patient unPatient = new Patient(
	                desResultats.getInt("idpatient"),
	                desResultats.getString("nom"),
	                desResultats.getString("prenom"),
	                desResultats.getInt("age"),
	                desResultats.getString("email"),
	                desResultats.getString("password"),
	                desResultats.getString("telephone"),
	                desResultats.getString("adresse"),
	                desResultats.getString("numeroSecu")
	            );
	            lesPatients.add(unPatient);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesPatients;
	}

	public static ArrayList<Patient> selectLikePatients(String filtre) {
	    String requete = "SELECT * FROM patient WHERE nom LIKE '%" + filtre
	        + "%' OR prenom LIKE '%" + filtre + "%' OR adresse LIKE '%"
	        + filtre + "%' OR email LIKE '%" + filtre + "%' OR telephone LIKE '%"
	        + filtre + "%' OR numeroSecu LIKE '%" + filtre + "%';";
	    ArrayList<Patient> lesPatients = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);
	        while (desResultats.next()) {
	            Patient unPatient = new Patient(
	                desResultats.getInt("idpatient"),
	                desResultats.getString("nom"),
	                desResultats.getString("prenom"),
	                desResultats.getInt("age"),
	                desResultats.getString("email"),
	                desResultats.getString("password"),
	                desResultats.getString("telephone"),
	                desResultats.getString("adresse"),
	                desResultats.getString("numeroSecu")
	            );
	            lesPatients.add(unPatient);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return lesPatients;
	}
	
	public static void deletePatient(int idpatient) {
	    // Supprimer les documents associés aux rendez-vous du patient
	    String deleteDocuments = "DELETE FROM document WHERE idrdv IN (SELECT idrdv FROM rdv WHERE idpatient = " + idpatient + ");";
	    executeRequete(deleteDocuments);

	    // Supprimer les rendez-vous associés au patient
	    String deleteRdv = "DELETE FROM rdv WHERE idpatient = " + idpatient + ";";
	    executeRequete(deleteRdv);

	    // Supprimer le patient
	    String deletePatient = "DELETE FROM patient WHERE idpatient = " + idpatient + ";";
	    executeRequete(deletePatient);
	}

	public static void updatePatient(Patient unPatient) {
	    String requete = "UPDATE patient SET nom = '" + unPatient.getNom()
	        + "', prenom = '" + unPatient.getPrenom()
	        + "', age = " + unPatient.getAge()
	        + ", email = '" + unPatient.getEmail()
	        + "', password = '" + unPatient.getPassword()
	        + "', telephone = '" + unPatient.getTelephone()
	        + "', adresse = '" + unPatient.getAdresse()
	        + "', numeroSecu = '" + unPatient.getNumeroSecu()
	        + "' WHERE idpatient = " + unPatient.getIdPatient() + " ;";
	    executeRequete(requete);
	}

	public static Patient selectWherePatient(int idpatient) {
	    String requete = "SELECT * FROM patient WHERE idpatient = " + idpatient + ";";
	    Patient unPatient = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet unResultat = unStat.executeQuery(requete);
	        if (unResultat.next()) {
	            unPatient = new Patient(
	                unResultat.getInt("idpatient"),
	                unResultat.getString("nom"),
	                unResultat.getString("prenom"),
	                unResultat.getInt("age"),
	                unResultat.getString("email"),
	                unResultat.getString("password"),
	                unResultat.getString("telephone"),
	                unResultat.getString("adresse"),
	                unResultat.getString("numeroSecu")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête : " + requete);
	    }
	    return unPatient;
	}
	/********* Gestion des lieux ********/ 
	public static void insertLieu(Lieu unLieu) {
	    String requete = "INSERT INTO lieu (nom, adresse, typeLieu) VALUES (?, ?, ?);";
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setString(1, unLieu.getNom());
	        ps.setString(2, unLieu.getAdresse());
	        ps.setString(3, unLieu.getTypeLieu());
	        ps.executeUpdate();
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de l'insertion du lieu : " + exp.getMessage());
	    }
	}

	public static ArrayList<Lieu> selectAllLieux() {
	    String requete = "SELECT * FROM lieu;";
	    ArrayList<Lieu> lesLieux = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);
	        while (desResultats.next()) {
	            Lieu unLieu = new Lieu(
	                desResultats.getInt("idlieu"),
	                desResultats.getString("nom"),
	                desResultats.getString("adresse"),
	                desResultats.getString("typeLieu")
	            );
	            lesLieux.add(unLieu);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la récupération des lieux : " + exp.getMessage());
	    }
	    return lesLieux;
	}

	public static ArrayList<Lieu> selectLikeLieux(String filtre) {
	    String requete = "SELECT * FROM lieu WHERE nom LIKE ? OR adresse LIKE ? OR typeLieu LIKE ?;";
	    ArrayList<Lieu> lesLieux = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setString(1, "%" + filtre + "%");
	        ps.setString(2, "%" + filtre + "%");
	        ps.setString(3, "%" + filtre + "%");
	        ResultSet desResultats = ps.executeQuery();
	        while (desResultats.next()) {
	            Lieu unLieu = new Lieu(
	                desResultats.getInt("idlieu"),
	                desResultats.getString("nom"),
	                desResultats.getString("adresse"),
	                desResultats.getString("typeLieu")
	            );
	            lesLieux.add(unLieu);
	        }
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la recherche des lieux : " + exp.getMessage());
	    }
	    return lesLieux;
	}

	public static int selectLikeLieu(String nom) {
	    String requete = "SELECT * FROM lieu WHERE nom LIKE ? ;";
	    Lieu unLieu = null;
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setString(1, nom);       
	        ResultSet unResultat = ps.executeQuery();
	        if (unResultat.next()) {
	            unLieu = new Lieu(
	                unResultat.getInt("idlieu"),
	                unResultat.getString("nom"),
	                unResultat.getString("adresse"),
	                unResultat.getString("typeLieu")
	            );
	        }
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la recherche du lieu : " + exp.getMessage());
	    }
	    return unLieu.getIdLieu();
	}
	public static void deleteLieu(int idLieu) {
	    String requete = "DELETE FROM lieu WHERE idlieu = ?;";
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setInt(1, idLieu);
	        ps.executeUpdate();
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        throw new RuntimeException("Erreur lors de la suppression du lieu : " + exp.getMessage(), exp);
	    }
	}


	public static void updateLieu(Lieu unLieu) {
	    String requete = "UPDATE lieu SET nom = ?, adresse = ?, typeLieu = ? WHERE idlieu = ?;";
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setString(1, unLieu.getNom());
	        ps.setString(2, unLieu.getAdresse());
	        ps.setString(3, unLieu.getTypeLieu());
	        ps.setInt(4, unLieu.getIdLieu());
	        ps.executeUpdate();
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la mise à jour du lieu : " + exp.getMessage());
	    }
	}

	public static Lieu selectWhereLieu(int idLieu) {
	    String requete = "SELECT * FROM lieu WHERE idlieu = ?;";
	    Lieu unLieu = null;
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setInt(1, idLieu);
	        ResultSet unResultat = ps.executeQuery();
	        if (unResultat.next()) {
	            unLieu = new Lieu(
	                unResultat.getInt("idlieu"),
	                unResultat.getString("nom"),
	                unResultat.getString("adresse"),
	                unResultat.getString("typeLieu")
	            );
	        }
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la récupération du lieu : " + exp.getMessage());
	    }
	    return unLieu;
	}

	/*********Gestion des documents********/
	public static void insertDocument(Document unDocument) {
	    String requete = "INSERT INTO document VALUES (null, '"
	        + unDocument.getTypeDoc() + "', '"
	        + unDocument.getUrl() + "', '"
	        + unDocument.getDateDepot() + "', '"
	        + unDocument.getDescription() + "', "
	        + unDocument.getIdMedecin() + ", "
	        + unDocument.getIdPatient() + ", "
	        + unDocument.getIdRdv() + ");";
	    executeRequete(requete);
	}

	
	public static ArrayList<Document> selectAllDocuments() {
	    String requete = "SELECT * FROM document;";
	    ArrayList<Document> lesDocuments = new ArrayList<>();

	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);

	        while (desResultats.next()) {
	            Document unDocument = new Document(
	                desResultats.getInt("iddocument"),
	                desResultats.getString("typeDoc"),
	                desResultats.getString("url"),
	                desResultats.getDate("datedepot"),
	                desResultats.getString("description"),
	                desResultats.getInt("idmedecin"),
	                desResultats.getInt("idpatient"),
	                desResultats.getInt("idrdv")
	            );
	            lesDocuments.add(unDocument);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête :" + requete);
	    }
	    return lesDocuments;
	}

	public static ArrayList<Document> selectLikeDocuments(String filtre){
		String requete = "select * from document where url like '%"+filtre
				+"%' or datedepot like '%" + filtre +"%' or description like '%"
				+filtre + "%' ;";
		ArrayList<Document> lesDocuments = new ArrayList<Document>();
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(requete);
			while (desResultats.next()) {
				Document unDocument = new Document(
		                desResultats.getInt("iddocument"),
		                desResultats.getString("typeDoc"),
		                desResultats.getString("url"),
		                desResultats.getDate("datedepot"),
		                desResultats.getString("description"),
		                desResultats.getInt("idmedecin"),
		                desResultats.getInt("idpatient"),
		                desResultats.getInt("idrdv")
		            );
				lesDocuments.add(unDocument);
			}
			unStat.close();
			uneConnexion.seDeconnecter();
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :"+requete);
		}
		return lesDocuments;
	}
	
	public static void deleteDocument(int iddocument) {
	    String requete = "DELETE FROM document WHERE iddocument = " + iddocument + ";";
	    executeRequete(requete);
	}

	public static void updateDocument(Document unDocument) {
	    String requete = "UPDATE document SET typeDoc = '"
	        + unDocument.getTypeDoc() + "', url = '"
	        + unDocument.getUrl() + "', datedepot = '"
	        + unDocument.getDateDepot() + "', description = '"
	        + unDocument.getDescription() + "', idmedecin = "
	        + unDocument.getIdMedecin() + ", idpatient = "
	        + unDocument.getIdPatient() + ", idrdv = "
	        + unDocument.getIdRdv()
	        + " WHERE iddocument = " + unDocument.getIdDocument() + ";";
	    executeRequete(requete);
	}

	public static Document selectWhereDocument(int iddocument) {
	    String requete = "SELECT * FROM document WHERE iddocument = " + iddocument + ";";
	    Document unDocument = null;

	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet unResultat = unStat.executeQuery(requete);

	        if (unResultat.next()) {
	            unDocument = new Document(
	                unResultat.getInt("iddocument"),
	                unResultat.getString("typeDoc"),
	                unResultat.getString("url"),
	                unResultat.getDate("datedepot"),
	                unResultat.getString("description"),
	                unResultat.getInt("idmedecin"),
	                unResultat.getInt("idpatient"),
	                unResultat.getInt("idrdv")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête :" + requete);
	    }
	    return unDocument;
	}

	
	/********gestion specialites ********/
	public static void insertSpecialite(Specialite uneSpecialite) {
	    String requete = "INSERT INTO specialite VALUES (null, '" + uneSpecialite.getCategorie() + "');";
	    executeRequete(requete);
	}

	public static ArrayList<Specialite> selectAllSpecialites() {
	    String requete = "SELECT * FROM specialite;";
	    ArrayList<Specialite> lesSpecialites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);
	        while (desResultats.next()) {
	            Specialite uneSpecialite = new Specialite(
	                desResultats.getInt("idspecialite"),
	                desResultats.getString("categorie")
	            );
	            lesSpecialites.add(uneSpecialite);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête :" + requete);
	    }
	    return lesSpecialites;
	}

	public static ArrayList<Specialite> selectLikeSpecialites(String filtre) {
	    String requete = "SELECT * FROM specialite WHERE categorie LIKE '%" + filtre + "%';";
	    ArrayList<Specialite> lesSpecialites = new ArrayList<>();
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet desResultats = unStat.executeQuery(requete);
	        while (desResultats.next()) {
	            Specialite uneSpecialite = new Specialite(
	                desResultats.getInt("idspecialite"),
	                desResultats.getString("categorie")
	            );
	            lesSpecialites.add(uneSpecialite);
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête :" + requete);
	    }
	    return lesSpecialites;
	}

	public static int selectLikeSpecialite(String categorie) {
	    String requete = "SELECT * FROM specialite WHERE categorie LIKE ? ;";
	    Specialite uneSpecialite = null;
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setString(1, categorie);       
	        ResultSet unResultat = ps.executeQuery();
	        if (unResultat.next()) {
	            uneSpecialite = new Specialite(
	                unResultat.getInt("idspecialite"),
	                unResultat.getString("categorie")
	            );
	        }
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur lors de la recherche de la spécialité : " + exp.getMessage());
	    }
	    return uneSpecialite.getIdSpecialite();
	}

	public static void deleteSpecialite(int idSpecialite) {
	    String requete = "DELETE FROM specialite WHERE idspecialite = ?;";
	    try {
	        uneConnexion.seConnecter();
	        PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	        ps.setInt(1, idSpecialite);
	        ps.executeUpdate();
	        ps.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        throw new RuntimeException("Erreur lors de la suppression de la spécialité : " + exp.getMessage(), exp);
	    }
	}

	public static void updateSpecialite(Specialite uneSpecialite) {
	    String requete = "UPDATE specialite SET categorie = '" + uneSpecialite.getCategorie() + "' WHERE idspecialite = " + uneSpecialite.getIdSpecialite() + ";";
	    executeRequete(requete);
	}

	public static Specialite selectWhereSpecialite(int idSpecialite) {
	    String requete = "SELECT * FROM specialite WHERE idspecialite = " + idSpecialite + ";";
	    Specialite uneSpecialite = null;
	    try {
	        uneConnexion.seConnecter();
	        Statement unStat = uneConnexion.getMaConnexion().createStatement();
	        ResultSet unResultat = unStat.executeQuery(requete);
	        if (unResultat.next()) {
	            uneSpecialite = new Specialite(
	                unResultat.getInt("idspecialite"),
	                unResultat.getString("categorie")
	            );
	        }
	        unStat.close();
	        uneConnexion.seDeconnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur d'exécution de la requête :" + requete);
	    }
	    return uneSpecialite;
	}
	
	/*********Gestion des Diplomes********/
	public static void insertDiplome(Diplome unDiplome) { 
        String requete = "INSERT INTO diplome (nom, sigle, faculte) VALUES ('"
            + unDiplome.getNom() + "', '"
            + unDiplome.getSigle() + "', '"
            + unDiplome.getFaculte() + "');";
        executeRequete(requete);
    }

    public static ArrayList<Diplome> selectAllDiplomes() {
        String requete = "SELECT * FROM diplome;";
        ArrayList<Diplome> lesDiplomes = new ArrayList<>();
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            while (desResultats.next()) {
                Diplome unDiplome = new Diplome(
                    desResultats.getInt("iddiplome"),
                    desResultats.getString("nom"),
                    desResultats.getString("sigle"),
                    desResultats.getString("faculte")
                );
                lesDiplomes.add(unDiplome);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesDiplomes;
    }

    public static ArrayList<Diplome> selectLikeDiplomes(String filtre) {
        String requete = "SELECT * FROM diplome WHERE nom LIKE '%" + filtre
            + "%' OR sigle LIKE '%" + filtre + "%' OR faculte LIKE '%" + filtre + "%';";
        ArrayList<Diplome> lesDiplomes = new ArrayList<>();
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            while (desResultats.next()) {
                Diplome unDiplome = new Diplome(
                    desResultats.getInt("iddiplome"),
                    desResultats.getString("nom"),
                    desResultats.getString("sigle"),
                    desResultats.getString("faculte")
                );
                lesDiplomes.add(unDiplome);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesDiplomes;
    }

    public static void deleteDiplome(int idDiplome) {
        String requete = "DELETE FROM diplome WHERE iddiplome = " + idDiplome + ";";
        executeRequete(requete);
    }

    public static void updateDiplome(Diplome unDiplome) {
        String requete = "UPDATE diplome SET nom = '" + unDiplome.getNom()
            + "', sigle = '" + unDiplome.getSigle()
            + "', faculte = '" + unDiplome.getFaculte()
            + "' WHERE iddiplome = " + unDiplome.getIdDiplome() + ";";
        executeRequete(requete);
    }

    public static Diplome selectWhereDiplome(int idDiplome) {
        String requete = "SELECT * FROM diplome WHERE iddiplome = " + idDiplome + ";";
        Diplome unDiplome = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if (unResultat.next()) {
                unDiplome = new Diplome(
                    unResultat.getInt("iddiplome"),
                    unResultat.getString("nom"),
                    unResultat.getString("sigle"),
                    unResultat.getString("faculte")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return unDiplome;
    }
	
	/***************** Gestion Medecin ***************************/
    public static void insertMedecin(Medecin unMedecin) { 
        String requete = "INSERT INTO medecin (nom, prenom, email, password, telephone, photo, statut, idspecialite, idlieu) VALUES ('"
            + unMedecin.getNom() + "', '"
            + unMedecin.getPrenom() + "', '"
            + unMedecin.getEmail() + "', '"
            + unMedecin.getPassword() + "', '"
            + unMedecin.getTelephone() + "', ?, '"
            + unMedecin.getStatut() + "', "
            + unMedecin.getIdSpecialite() + ", "
            + unMedecin.getIdLieu() + ");";
        
        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setBlob(1, unMedecin.getPhoto()); // Gestion du LONGBLOB
            ps.executeUpdate();
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de l'exécution de la requête : " + requete);
        }
    }

    public static ArrayList<Medecin> selectAllMedecins() {
        String requete = "SELECT * FROM medecin;";
        ArrayList<Medecin> lesMedecins = new ArrayList<>();
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            while (desResultats.next()) {
                Medecin unMedecin = new Medecin(
                    desResultats.getInt("idmedecin"),
                    desResultats.getString("nom"),
                    desResultats.getString("prenom"),
                    desResultats.getString("email"),
                    desResultats.getString("password"),
                    desResultats.getString("telephone"),
                    desResultats.getString("statut"),
                    desResultats.getBlob("photo"),
                    desResultats.getInt("idspecialite"),
                    desResultats.getInt("idlieu")
                );
                lesMedecins.add(unMedecin);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesMedecins;
    }

    public static ArrayList<Medecin> selectLikeMedecins(String filtre) {
        String requete = "SELECT * FROM medecin WHERE nom LIKE '%" + filtre
            + "%' OR prenom LIKE '%" + filtre
            + "%' OR email LIKE '%" + filtre
            + "%' OR telephone LIKE '%" + filtre + "%';";
        
        ArrayList<Medecin> lesMedecins = new ArrayList<>();
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);
            while (desResultats.next()) {
                Medecin unMedecin = new Medecin(
                    desResultats.getInt("idmedecin"),
                    desResultats.getString("nom"),
                    desResultats.getString("prenom"),
                    desResultats.getString("email"),
                    desResultats.getString("password"),
                    desResultats.getString("telephone"),
                    desResultats.getString("statut"),
                    desResultats.getBlob("photo"),
                    desResultats.getInt("idspecialite"),
                    desResultats.getInt("idlieu")
                );
                lesMedecins.add(unMedecin);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return lesMedecins;
    }

	public static void deleteMedecin(int idmedecin) {
	    // Supprimer les documents associés aux rendez-vous du patient
	    String deleteDocuments = "DELETE FROM document WHERE idrdv IN (SELECT idrdv FROM rdv WHERE idmedecin = " + idmedecin + ");";
	    executeRequete(deleteDocuments);

	    // Supprimer les rendez-vous associés au patient
	    String deleteRdv = "DELETE FROM rdv WHERE idmedecin = " + idmedecin + ";";
	    executeRequete(deleteRdv);

	    // Supprimer le patient
	    String deletePatient = "DELETE FROM medecin WHERE idmedecin = " + idmedecin + ";";
	    executeRequete(deletePatient);
	}
    public static void updateMedecin(Medecin unMedecin) {
        String requete = "UPDATE medecin SET nom = '" + unMedecin.getNom()
            + "', prenom = '" + unMedecin.getPrenom()
            + "', email = '" + unMedecin.getEmail()
            + "', password = '" + unMedecin.getPassword()
            + "', telephone = '" + unMedecin.getTelephone()
            + "', photo = ?, statut = '" + unMedecin.getStatut()
            + "', idspecialite = " + unMedecin.getIdSpecialite()
            + ", idlieu = " + unMedecin.getIdLieu()
            + " WHERE idmedecin = " + unMedecin.getIdMedecin() + ";";
        
        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setBlob(1, unMedecin.getPhoto()); // Gestion du LONGBLOB
            ps.executeUpdate();
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de l'exécution de la requête : " + requete);
        }
    }

    public static Medecin selectWhereMedecin(int idMedecin) {
        String requete = "SELECT * FROM medecin WHERE idmedecin = " + idMedecin + ";";
        Medecin unMedecin = null;
        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet unResultat = unStat.executeQuery(requete);
            if (unResultat.next()) {
                unMedecin = new Medecin(
                    unResultat.getInt("idmedecin"),
                    unResultat.getString("nom"),
                    unResultat.getString("prenom"),
                    unResultat.getString("email"),
                    unResultat.getString("password"),
                    unResultat.getString("telephone"),
                    unResultat.getString("statut"),
                    unResultat.getBlob("photo"),
                    unResultat.getInt("idspecialite"),
                    unResultat.getInt("idlieu")
                );
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur d'exécution de la requête : " + requete);
        }
        return unMedecin;
    }

	/***************** Gestion Rdv ***************************/
    public static void insertRdv(Rdv unRdv) { 
        String requete = "INSERT INTO rdv (daterdv, heureRdv, motif, idmedecin, idpatient, idlieu) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setDate(1, unRdv.getDateRdv());
            ps.setTime(2, unRdv.getHeureRdv());
            ps.setString(3, unRdv.getMotif());
            ps.setInt(4, unRdv.getIdMedecin());
            ps.setInt(5, unRdv.getIdPatient());
            ps.setInt(6, unRdv.getIdLieu());

            ps.executeUpdate();
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de l'insertion du RDV : " + exp.getMessage());
        }
    }

    public static ArrayList<Rdv> selectAllRdvs() {
        String requete = "SELECT * FROM rdv;";
        ArrayList<Rdv> lesRdvs = new ArrayList<>();

        try {
            uneConnexion.seConnecter();
            Statement unStat = uneConnexion.getMaConnexion().createStatement();
            ResultSet desResultats = unStat.executeQuery(requete);

            while (desResultats.next()) {
                Rdv unRdv = new Rdv(
                    desResultats.getInt("idrdv"),
                    desResultats.getDate("daterdv"),
                    desResultats.getTime("heureRdv"),
                    desResultats.getString("motif"),
                    desResultats.getInt("idmedecin"),
                    desResultats.getInt("idpatient"),
                    desResultats.getInt("idlieu")
                );
                lesRdvs.add(unRdv);
            }
            unStat.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de la récupération des RDVs : " + exp.getMessage());
        }
        return lesRdvs;
    }

    public static ArrayList<Rdv> selectLikeRdvs(String filtre) {
        String requete = "SELECT * FROM rdv WHERE motif LIKE ?";
        ArrayList<Rdv> lesRdvs = new ArrayList<>();

        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setString(1, "%" + filtre + "%");
            ResultSet desResultats = ps.executeQuery();

            while (desResultats.next()) {
                Rdv unRdv = new Rdv(
                    desResultats.getInt("idrdv"),
                    desResultats.getDate("daterdv"),
                    desResultats.getTime("heureRdv"),
                    desResultats.getString("motif"),
                    desResultats.getInt("idmedecin"),
                    desResultats.getInt("idpatient"),
                    desResultats.getInt("idlieu")
                );
                lesRdvs.add(unRdv);
            }
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de la recherche des RDVs : " + exp.getMessage());
        }
        return lesRdvs;
    }

    public static void deleteRdv(int idRdv) {
        String requete = "DELETE FROM rdv WHERE idrdv = ?;";

        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setInt(1, idRdv);
            ps.executeUpdate();
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de la suppression du RDV : " + exp.getMessage());
        }
    }

    public static void updateRdv(Rdv unRdv) {
        String requete = "UPDATE rdv SET daterdv = ?, heureRdv = ?, motif = ?, idmedecin = ?, idpatient = ?, idlieu = ? WHERE idrdv = ?";

        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setDate(1, unRdv.getDateRdv());
            ps.setTime(2, unRdv.getHeureRdv());
            ps.setString(3, unRdv.getMotif());
            ps.setInt(4, unRdv.getIdMedecin());
            ps.setInt(5, unRdv.getIdPatient());
            ps.setInt(6, unRdv.getIdLieu());
            ps.setInt(7, unRdv.getIdRdv());

            ps.executeUpdate();
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de la mise à jour du RDV : " + exp.getMessage());
        }
    }

    public static Rdv selectWhereRdv(int idRdv) {
        String requete = "SELECT * FROM rdv WHERE idrdv = ?;";
        Rdv unRdv = null;

        try {
            uneConnexion.seConnecter();
            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
            ps.setInt(1, idRdv);
            ResultSet unResultat = ps.executeQuery();

            if (unResultat.next()) {
                unRdv = new Rdv(
                    unResultat.getInt("idrdv"),
                    unResultat.getDate("daterdv"),
                    unResultat.getTime("heureRdv"),
                    unResultat.getString("motif"),
                    unResultat.getInt("idmedecin"),
                    unResultat.getInt("idpatient"),
                    unResultat.getInt("idlieu")
                );
            }
            ps.close();
            uneConnexion.seDeconnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur lors de la récupération du RDV : " + exp.getMessage());
        }
        return unRdv;
    }
	
	/***************** Gestion Avoir ***************************/
    /*
	public static void insertAvoir(Avoir unAvoir) { 
		String requete = "insert into avoir values (null, '"+unAvoir.getNom()
		+ "' , '" +unAvoir.getPrenom()+"' , '" + unAvoir.getAdresse() 
		+"' , '" +unAvoir.getEmail()+"' , '" +unAvoir.getTel()+"' );";
		executeRequete(requete); 
	}
	
	public static ArrayList<Avoir> selectAllAvoirs() {
		String requete = "select * from avoir;";
		ArrayList<Avoir> lesAvoirs = new ArrayList<>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete); 
			while(desResultats.next()) {
				Avoir unAvoir = new Avoir (
					desResultats.getInt("idavoir"), 
					desResultats.getString("nom"),
					desResultats.getString("prenom"),
					desResultats.getString("adresse"),
					desResultats.getString("email"),
					desResultats.getString("tel")
				);
				lesAvoirs.add(unAvoir);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return lesAvoirs; 
	}
	
	public static ArrayList<Avoir> selectLikeAvoirs(String filtre) {
		String requete = "select * from avoir where nom like '%"+filtre
				+"%'  or prenom like '%" + filtre +"%'  or adresse like '%"
				+filtre + "%'  or email like '%"+filtre + "%'  or tel like '%"
				+filtre+"%';";
		ArrayList<Avoir> lesAvoirs = new ArrayList<>(); 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); 
			ResultSet desResultats = unStat.executeQuery(requete); 
			while(desResultats.next()) {
				Avoir unAvoir = new Avoir (
					desResultats.getInt("idavoir"), 
					desResultats.getString("nom"),
					desResultats.getString("prenom"),
					desResultats.getString("adresse"),
					desResultats.getString("email"),
					desResultats.getString("tel")
				);
				lesAvoirs.add(unAvoir);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return lesAvoirs; 
	}
	
	public static void deleteAvoir(int idAvoir) {
		String requete ="delete from avoir where idavoir = "+idAvoir+";";
		executeRequete(requete); 
	}
	
	public static void updateAvoir(Avoir unAvoir) {
		String requete ="update avoir set nom= '"+unAvoir.getNom()
		+ "' , prenom='" +unAvoir.getPrenom()+"' , adresse='" + unAvoir.getAdresse() 
		+"' , email = '" +unAvoir.getEmail()+"' , tel ='" +unAvoir.getTel()
		+"'  where idavoir = "+unAvoir.getIdAvoir()+";";
		executeRequete(requete); 
	}
	
	public static Avoir selectWhereAvoir(int idAvoir) {
		String requete = "select * from avoir where idavoir = " + idAvoir + ";" ; 
		Avoir unAvoir = null; 
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete); 
			if(unResultat.next()) {
				unAvoir = new Avoir (
					unResultat.getInt("idavoir"), 
					unResultat.getString("nom"),
					unResultat.getString("prenom"),
					unResultat.getString("adresse"),
					unResultat.getString("email"),
					unResultat.getString("tel")
				);
			}
			unStat.close();
			uneConnexion.seDeConnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :" + requete);
		}
		return unAvoir; 
	}
	*/
	/*********Gestion des Administrateurs ********/
	 public static void insertAdministrateur(Administrateur unAdministrateur) { 
	        String requete = "INSERT INTO administrateur (nom, prenom, email, password, telephone) VALUES (?, ?, ?, ?, ?)";
	        
	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setString(1, unAdministrateur.getNom());
	            ps.setString(2, unAdministrateur.getPrenom());
	            ps.setString(3, unAdministrateur.getEmail());
	            ps.setString(4, unAdministrateur.getPassword());
	            ps.setString(5, unAdministrateur.getTelephone());

	            ps.executeUpdate();
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de l'insertion de l'administrateur : " + exp.getMessage());
	        }
	    }

	    public static ArrayList<Administrateur> selectAllAdministrateurs() {
	        String requete = "SELECT * FROM administrateur;";
	        ArrayList<Administrateur> lesAdministrateurs = new ArrayList<>();

	        try {
	            uneConnexion.seConnecter();
	            Statement unStat = uneConnexion.getMaConnexion().createStatement();
	            ResultSet desResultats = unStat.executeQuery(requete);

	            while (desResultats.next()) {
	                Administrateur unAdministrateur = new Administrateur(
	                    desResultats.getInt("idAdministrateur"),
	                    desResultats.getString("nom"),
	                    desResultats.getString("prenom"),
	                    desResultats.getString("email"),
	                    desResultats.getString("password"),
	                    desResultats.getString("telephone")
	                );
	                lesAdministrateurs.add(unAdministrateur);
	            }
	            unStat.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la récupération des administrateurs : " + exp.getMessage());
	        }
	        return lesAdministrateurs;
	    }

	    public static ArrayList<Administrateur> selectLikeAdministrateurs(String filtre) {
	        String requete = "SELECT * FROM administrateur WHERE nom LIKE ? OR prenom LIKE ? OR email LIKE ? OR telephone LIKE ?";
	        ArrayList<Administrateur> lesAdministrateurs = new ArrayList<>();

	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setString(1, "%" + filtre + "%");
	            ps.setString(2, "%" + filtre + "%");
	            ps.setString(3, "%" + filtre + "%");
	            ps.setString(4, "%" + filtre + "%");

	            ResultSet desResultats = ps.executeQuery();
	            while (desResultats.next()) {
	                Administrateur unAdministrateur = new Administrateur(
	                    desResultats.getInt("idAdministrateur"),
	                    desResultats.getString("nom"),
	                    desResultats.getString("prenom"),
	                    desResultats.getString("email"),
	                    desResultats.getString("password"),
	                    desResultats.getString("telephone")
	                );
	                lesAdministrateurs.add(unAdministrateur);
	            }
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la recherche des administrateurs : " + exp.getMessage());
	        }
	        return lesAdministrateurs;
	    }

	    public static void deleteAdministrateur(int idAdministrateur) {
	        String requete = "DELETE FROM administrateur WHERE idAdministrateur = ?;";
	        
	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setInt(1, idAdministrateur);
	            ps.executeUpdate();
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la suppression de l'administrateur : " + exp.getMessage());
	        }
	    }

	    public static void updateAdministrateur(Administrateur unAdministrateur) {
	        String requete = "UPDATE administrateur SET nom = ?, prenom = ?, email = ?, password = ?, telephone = ? WHERE idAdministrateur = ?";
	        
	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setString(1, unAdministrateur.getNom());
	            ps.setString(2, unAdministrateur.getPrenom());
	            ps.setString(3, unAdministrateur.getEmail());
	            ps.setString(4, unAdministrateur.getPassword());
	            ps.setString(5, unAdministrateur.getTelephone());
	            ps.setInt(6, unAdministrateur.getIdAdministrateur());

	            ps.executeUpdate();
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la mise à jour de l'administrateur : " + exp.getMessage());
	        }
	    }

	    public static Administrateur selectWhereAdministrateur(int idAdministrateur) {
	        String requete = "SELECT * FROM administrateur WHERE idAdministrateur = ?;";
	        Administrateur unAdministrateur = null;

	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setInt(1, idAdministrateur);
	            ResultSet unResultat = ps.executeQuery();

	            if (unResultat.next()) {
	                unAdministrateur = new Administrateur(
	                    unResultat.getInt("idAdministrateur"),
	                    unResultat.getString("nom"),
	                    unResultat.getString("prenom"),
	                    unResultat.getString("email"),
	                    unResultat.getString("password"),
	                    unResultat.getString("telephone")
	                );
	            }
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la récupération de l'administrateur : " + exp.getMessage());
	        }
	        return unAdministrateur;
	    }

	    public static Administrateur selectWhereAdministrateur(String email, String password) {
	        String requete = "SELECT * FROM administrateur WHERE email = ? AND password = ?;";
	        Administrateur unAdministrateur = null;

	        try {
	            uneConnexion.seConnecter();
	            PreparedStatement ps = uneConnexion.getMaConnexion().prepareStatement(requete);
	            ps.setString(1, email);
	            ps.setString(2, password);
	            ResultSet unResultat = ps.executeQuery();

	            if (unResultat.next()) {
	                unAdministrateur = new Administrateur(
	                    unResultat.getInt("idAdministrateur"),
	                    unResultat.getString("nom"),
	                    unResultat.getString("prenom"),
	                    unResultat.getString("email"),
	                    unResultat.getString("password"),
	                    unResultat.getString("telephone")
	                );
	            }
	            ps.close();
	            uneConnexion.seDeconnecter();
	        } catch (SQLException exp) {
	            System.out.println("Erreur lors de la connexion de l'administrateur : " + exp.getMessage());
	        }
	        return unAdministrateur;
	    }
	/********************Autres méthodes ******************/
	public static void executeRequete (String requete) {
		try {
			uneConnexion.seConnecter();
			Statement unStat = uneConnexion.getMaConnexion().createStatement(); //prepare
			unStat.execute(requete); //execute
			unStat.close(); //ferme
			uneConnexion.seDeconnecter(); //deconnection
		}
		catch(SQLException exp) {
			System.out.println("Erreur d'execution de la requete :"+requete);
		}
	}

}

