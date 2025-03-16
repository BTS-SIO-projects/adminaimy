package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VueGenerale extends JFrame implements ActionListener {

    private JButton btPatients = new JButton("Patients");
  //  private JButton btAdministrateurs = new JButton("Administrateurs");
    private JButton btMedecins = new JButton("Medecins");
    private JButton btSpecialites = new JButton("Specialites");
    private JButton btLieux = new JButton("Lieux");
    private JButton btQuitter = new JButton("Quitter");

    private JPanel panelMenu = new JPanel();
    private static PanelAccueil unPanelAccueil = new PanelAccueil();
  //  private static PanelAdministrateurs unPanelAdministrateur= new PanelAdministrateurs();
    private static PanelPatients unPanelPatient = new PanelPatients();
    private static PanelMedecins unPanelMedecin = new PanelMedecins();
    private static PanelSpecialites unPanelSpecialite = new PanelSpecialites();
    private static PanelLieux unPanelLieu = new PanelLieux();

    public VueGenerale() {
        this.setTitle("Application Aimy Gestion 2025");
        this.setBounds(100, 100, 1000, 600);
        this.getContentPane().setBackground(new Color(42, 157, 143));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        // Construire le panel Menu
        this.panelMenu.setBackground(Color.darkGray);
        this.panelMenu.setBounds(50, 10, 900, 40);
        this.panelMenu.setLayout(new GridLayout(1, 6));
   //     this.panelMenu.add(this.btAdministrateurs);
        this.panelMenu.add(this.btPatients);
        this.panelMenu.add(this.btMedecins);
        this.panelMenu.add(this.btSpecialites);
        this.panelMenu.add(this.btLieux);
        this.panelMenu.add(this.btQuitter);
        this.add(this.panelMenu);

        // rendre les boutons cliquables
  //      this.btAdministrateurs.addActionListener(this);
        this.btPatients.addActionListener(this);
        this.btMedecins.addActionListener(this);
        this.btSpecialites.addActionListener(this);
        this.btLieux.addActionListener(this);
        this.btQuitter.addActionListener(this);

        // Ajout des panels
        this.add(unPanelAccueil);
   //     this.add(unPanelAdministrateur);
        this.add(unPanelPatient);
        this.add(unPanelMedecin);
        this.add(unPanelSpecialite);
        this.add(unPanelLieu);

        // 🔥 Afficher l'accueil par défaut
        rendreVisible(0);

        this.setVisible(true);
    }

    public void rendreVisible(int choix) {
        // Cacher tous les panels
        unPanelAccueil.setVisible(false);
   //     unPanelAdministrateur.setVisible(false);
        unPanelPatient.setVisible(false);
        unPanelMedecin.setVisible(false);
        unPanelSpecialite.setVisible(false);
        unPanelLieu.setVisible(false);

        // Afficher celui sélectionné
        switch (choix) {
            case 0:
                unPanelAccueil.setVisible(true);
                break;
   /*         case 1:
                unPanelAdministrateur.setVisible(true);
                break;
     */       case 2:
                unPanelPatient.setVisible(true);
                break;
            case 3:
                unPanelMedecin.setVisible(true);
                break;
            case 4:
                unPanelSpecialite.setVisible(true);
                break;
            case 5:
                unPanelLieu.setVisible(true);
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btQuitter) {
            int retour = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?", 
                    "Quitter l'application", JOptionPane.YES_NO_OPTION);
            if (retour == 0) {
                System.exit(0);
            }
    /*    } else if (e.getSource() == this.btAdministrateurs) {
            rendreVisible(1);
      */  } else if (e.getSource() == this.btPatients) {
            rendreVisible(2);
        } else if (e.getSource() == this.btMedecins) {
            rendreVisible(3);
        } else if (e.getSource() == this.btSpecialites) {
            rendreVisible(4);
        } else if (e.getSource() == this.btLieux) {
            rendreVisible(5);
        }
    }
}
