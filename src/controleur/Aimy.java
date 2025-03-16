package controleur;

import vue.VueConnexion;
import vue.VueGenerale;

public class Aimy {	
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	private static Administrateur administrateurConnecte ; 
	
	public static Administrateur getAdministrateurConnecte() {
		return administrateurConnecte;
	}

	public static void setTechConnecte(Administrateur administrateurConnecte) {
		Aimy.administrateurConnecte = administrateurConnecte;
	}

	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
	}
	
	public static void creerVueGenerale(boolean action) {
		if (action == true) {
			uneVueGenerale = new VueGenerale();
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}
	
	public static void rendreVisibleVueConnexion(boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
