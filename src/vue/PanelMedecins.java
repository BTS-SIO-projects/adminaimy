package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Medecin;
import controleur.Patient;
import controleur.Specialite;
import controleur.Controleur;
import controleur.Lieu;
import controleur.Tableau;

public class PanelMedecins extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();

    private JComboBox<String> cbStatut;
    private JComboBox<String> cbSpecialite;
    private JComboBox<String> cbLieu;

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable tableMedecin;
    private Tableau tableauMedecins;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField(15);
    private JButton btFiltrer = new JButton("Rechercher");
    private JButton btAnnulerFiltre = new JButton("Annuler");

    public PanelMedecins() {
        super("Gestion des Médecins");

        // Panel Formulaire
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 50, 250, 300);
        this.panelForm.setLayout(new GridLayout(8, 2, 5, 5));

        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom : "));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Email : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Téléphone : "));
        this.panelForm.add(this.txtTel);// Remplace les champs texte par des JComboBox
        String[] statuts = {"valider", "en attente", "refuser"}; // Remplacez par les valeurs réelles de l'ENUM
        this.cbStatut = new JComboBox<>(statuts);

        // Chargement dynamique des spécialités et lieux
        this.cbSpecialite = new JComboBox<>(this.obtenirSpecialites());
        this.cbLieu = new JComboBox<>(this.obtenirLieux());

        // Ajout au formulaire
        this.panelForm.add(new JLabel("Spécialité : "));
        this.panelForm.add(this.cbSpecialite);
        this.panelForm.add(new JLabel("Lieu : "));
        this.panelForm.add(this.cbLieu);
        this.panelForm.add(new JLabel("Statut du profil : "));
        this.panelForm.add(this.cbStatut);

        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau des médecins
        String entetes[] = { "ID Médecin", "Nom", "Prénom", "Email", "Password", "Téléphone", "Spécialité", "Lieu", "Statut" };

        this.tableauMedecins = new Tableau(this.obtenirMedecins(""), entetes);
        this.tableMedecin = new JTable(this.tableauMedecins);
        JScrollPane uneScroll = new JScrollPane(this.tableMedecin);
        uneScroll.setBounds(300, 100, 600, 300);
        this.add(uneScroll);

        this.btSupprimer.setBounds(50, 420, 150, 30);
        this.add(btSupprimer);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.addActionListener(this);

        this.tableMedecin.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    int numLigne = tableMedecin.getSelectedRow();
                    txtNom.setText(tableauMedecins.getValueAt(numLigne, 1).toString());
                    txtPrenom.setText(tableauMedecins.getValueAt(numLigne, 2).toString());
                    txtEmail.setText(tableauMedecins.getValueAt(numLigne, 3).toString());
                    txtTel.setText(tableauMedecins.getValueAt(numLigne, 5).toString());
                    cbSpecialite.setSelectedItem(tableauMedecins.getValueAt(numLigne, 6).toString());
                    cbLieu.setSelectedItem(tableauMedecins.getValueAt(numLigne, 7).toString());
                    cbStatut.setSelectedItem(tableauMedecins.getValueAt(numLigne, 8).toString());

                    btValider.setText("Modifier");
                    btSupprimer.setVisible(true);
                }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        // Panel Filtre
        this.panelFiltre.setBackground(Color.LIGHT_GRAY);
        this.panelFiltre.setLayout(new GridLayout(1, 4, 5, 5));
        this.panelFiltre.setBounds(300, 50, 600, 40);
        this.panelFiltre.add(new JLabel("Rechercher :"));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.panelFiltre.add(this.btAnnulerFiltre);
        this.add(this.panelFiltre);

        this.btFiltrer.addActionListener(this);
        this.btAnnulerFiltre.addActionListener(this);
    }

    public Object[][] obtenirMedecins(String filtre) {
        ArrayList<Medecin> lesMedecins;
        if (filtre.isEmpty()) {
            lesMedecins = Controleur.selectAllMedecins();
        } else {
            lesMedecins = Controleur.selectLikeMedecins(filtre);
        }

        Object[][] matrice = new Object[lesMedecins.size()][9];
        int i = 0;
        for (Medecin unMedecin : lesMedecins) {
            matrice[i][0] = unMedecin.getIdMedecin();
            matrice[i][1] = unMedecin.getNom();
            matrice[i][2] = unMedecin.getPrenom();
            matrice[i][3] = unMedecin.getEmail();
            matrice[i][4] = unMedecin.getPassword();
            matrice[i][5] = unMedecin.getTelephone();
            matrice[i][6] = Controleur.selectWhereSpecialite(unMedecin.getIdSpecialite()).getCategorie();
            matrice[i][7] = Controleur.selectWhereLieu(unMedecin.getIdLieu()).getNom();
            matrice[i][8] = unMedecin.getStatut();
            i++;
        }
        return matrice;
    }
    private String[] obtenirSpecialites() {
        ArrayList<String> specialites = new ArrayList<>();
        for (Specialite specialite : Controleur.selectAllSpecialites()) {
            specialites.add(specialite.getCategorie());
        }
        return specialites.toArray(new String[0]);
    }

    private String[] obtenirLieux() {
        ArrayList<String> lieux = new ArrayList<>();
        for (Lieu lieu : Controleur.selectAllLieux()) {
            lieux.add(lieu.getNom());
        }
        return lieux.toArray(new String[0]);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            viderChamps();
        } else if (e.getSource() == this.btValider) {
            if (this.btValider.getText().equals("Modifier")) {
                modifierMedecin();
            } else {
                ajouterMedecin();
            }
        } else if (e.getSource() == this.btSupprimer) {
            supprimerMedecin();
        } else if (e.getSource() == this.btFiltrer) {
            filtrerMedecins();
        } else if (e.getSource() == this.btAnnulerFiltre) {
            rafraichirTableau();
        }
    }

    private void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtEmail.setText("");
        this.txtTel.setText("");
        this.cbStatut.setSelectedIndex(-1); // Désélectionne tout      
        this.cbSpecialite.setSelectedIndex(-1); // Désélectionne tout
        this.cbLieu.setSelectedIndex(-1);
        btValider.setText("Valider");
        btSupprimer.setVisible(false);
    }

    private void modifierMedecin() {
        int numLigne = this.tableMedecin.getSelectedRow();
        int idMedecin = Integer.parseInt(this.tableauMedecins.getValueAt(numLigne, 0).toString());

        // Récupération de l'ancien mot de passe depuis la base de données
        Medecin medecinAModifier = Controleur.selectWhereMedecin(idMedecin);
        String ancienMotDePasse = medecinAModifier.getPassword();
        
        String statut = cbStatut.getSelectedItem().toString();

        String specialite = cbSpecialite.getSelectedItem().toString();
        String lieu = cbLieu.getSelectedItem().toString();

        int idSpecialite = Controleur.selectLikeSpecialite(specialite);
        int idLieu= Controleur.selectLikeLieu(lieu);

        Medecin unMedecin = new Medecin(idMedecin, txtNom.getText(), txtPrenom.getText(),
                txtEmail.getText(), ancienMotDePasse, txtTel.getText(), statut, medecinAModifier.getPhoto(), idSpecialite, idLieu);

        Controleur.updateMedecin(unMedecin);
        JOptionPane.showMessageDialog(this, "Médecin modifié avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void ajouterMedecin() {
        String statut = cbStatut.getSelectedItem().toString();

        String specialite = cbSpecialite.getSelectedItem().toString();
        String lieu = cbLieu.getSelectedItem().toString();

        int idSpecialite = Controleur.selectLikeSpecialite(specialite);
        int idLieu= Controleur.selectLikeLieu(lieu);

        Medecin unMedecin = new Medecin(txtNom.getText(), txtPrenom.getText(), txtEmail.getText(),
        		txtTel.getText(), statut, null, idSpecialite, idLieu);

        Controleur.insertMedecin(unMedecin);
        JOptionPane.showMessageDialog(this, "Médecin ajouté avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void supprimerMedecin() {
        int numLigne = this.tableMedecin.getSelectedRow();
        int idMedecin = Integer.parseInt(this.tableauMedecins.getValueAt(numLigne, 0).toString());

        Controleur.deleteMedecin(idMedecin);
        JOptionPane.showMessageDialog(this, "Médecin supprimé.");
        rafraichirTableau();
        viderChamps();
    }

    private void filtrerMedecins() {
        this.tableauMedecins.setDonnes(this.obtenirMedecins(this.txtFiltre.getText()));
    }

    private void rafraichirTableau() {
        this.tableauMedecins.setDonnes(this.obtenirMedecins(""));
    }
}
