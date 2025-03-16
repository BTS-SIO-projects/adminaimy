package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Patient;
import controleur.Controleur;
import controleur.Tableau;

public class PanelPatients extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtAdress = new JTextField();
    private JTextField txtAge = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JTextField txtTel = new JTextField();
    private JTextField txtNumeroSecu = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable tablePatient;
    private Tableau tableauPatients;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField(15);
    private JButton btFiltrer = new JButton("Rechercher");
    private JButton btAnnulerFiltre = new JButton("Annuler"); // Nouveau bouton

    public PanelPatients() {
        super("Gestion des Patients");

        // Panel Formulaire
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 50, 250, 300);
        this.panelForm.setLayout(new GridLayout(8, 2, 5, 5));

        this.panelForm.add(new JLabel("Nom : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom : "));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Âge : "));
        this.panelForm.add(this.txtAge);
        this.panelForm.add(new JLabel("Adresse : "));
        this.panelForm.add(this.txtAdress);
        this.panelForm.add(new JLabel("Email : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Téléphone : "));
        this.panelForm.add(this.txtTel);
        this.panelForm.add(new JLabel("N° Sécurité Sociale : "));
        this.panelForm.add(this.txtNumeroSecu);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau des patients
        String entetes[] = { "ID", "Nom", "Prénom", "Âge", "Adresse", "Email", "Téléphone", "N° Sécurité Sociale" };

        this.tableauPatients = new Tableau(this.obtenirPatients(""), entetes);
        this.tablePatient = new JTable(this.tableauPatients);
        JScrollPane uneScroll = new JScrollPane(this.tablePatient);
        uneScroll.setBounds(300, 100, 600, 300);
        this.add(uneScroll);

        this.btSupprimer.setBounds(50, 420, 150, 30);
        this.add(btSupprimer);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.addActionListener(this);

        this.tablePatient.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1) {
                    int numLigne = tablePatient.getSelectedRow();
                    txtNom.setText(tableauPatients.getValueAt(numLigne, 1).toString());
                    txtPrenom.setText(tableauPatients.getValueAt(numLigne, 2).toString());
                    txtAge.setText(tableauPatients.getValueAt(numLigne, 3).toString());
                    txtAdress.setText(tableauPatients.getValueAt(numLigne, 4).toString());
                    txtEmail.setText(tableauPatients.getValueAt(numLigne, 5).toString());
                    txtTel.setText(tableauPatients.getValueAt(numLigne, 6).toString());
                    txtNumeroSecu.setText(tableauPatients.getValueAt(numLigne, 7).toString());

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
        this.panelFiltre.add(this.btAnnulerFiltre); // Ajout du bouton annuler filtre
        this.add(this.panelFiltre);

        this.btFiltrer.addActionListener(this);
        this.btAnnulerFiltre.addActionListener(this); // Action du bouton annuler filtre
    }

    public Object[][] obtenirPatients(String filtre) {
        ArrayList<Patient> lesPatients;
        if (filtre == null || filtre.trim().isEmpty()) {
            lesPatients = Controleur.selectAllPatients();
        } else {
            lesPatients = Controleur.selectLikePatients(filtre.trim());
        }

        Object[][] matrice = new Object[lesPatients.size()][8];
        int i = 0;
        for (Patient unPatient : lesPatients) {
            matrice[i][0] = unPatient.getIdPatient();
            matrice[i][1] = unPatient.getNom();
            matrice[i][2] = unPatient.getPrenom();
            matrice[i][3] = unPatient.getAge();
            matrice[i][4] = unPatient.getAdresse();
            matrice[i][5] = unPatient.getEmail();
            matrice[i][6] = unPatient.getTelephone();
            matrice[i][7] = unPatient.getNumeroSecu();
            i++;
        }
        return matrice;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            viderChamps();
        } else if (e.getSource() == this.btValider) {
            if (this.btValider.getText().equals("Modifier")) {
                modifierPatient();
            } else {
                ajouterPatient();
            }
        } else if (e.getSource() == this.btSupprimer) {
            supprimerPatient();
        } else if (e.getSource() == this.btFiltrer) {
            filtrerPatients();
        } else if (e.getSource() == this.btAnnulerFiltre) { 
            annulerFiltre(); // Appelle la méthode pour annuler le filtre
        }
    }

    private void viderChamps() {
        this.txtNom.setText("");
        this.txtPrenom.setText("");
        this.txtAge.setText("");
        this.txtAdress.setText("");
        this.txtEmail.setText("");
        this.txtTel.setText("");
        this.txtNumeroSecu.setText("");
        btValider.setText("Valider");
        btSupprimer.setVisible(false);
    }

    private void modifierPatient() {
        int numLigne = this.tablePatient.getSelectedRow();
        int idPatient = Integer.parseInt(this.tableauPatients.getValueAt(numLigne, 0).toString());

        // Récupération de l'ancien mot de passe depuis la base de données
        Patient patientAModifier = Controleur.selectWherePatient(idPatient);
        String ancienMotDePasse = patientAModifier.getPassword();
        		
        Patient unPatient = new Patient(idPatient, txtNom.getText(), txtPrenom.getText(),
                Integer.parseInt(txtAge.getText()), txtEmail.getText(), ancienMotDePasse, txtTel.getText(),
                txtAdress.getText(), txtNumeroSecu.getText());

        Controleur.updatePatient(unPatient);
        JOptionPane.showMessageDialog(this, "Patient modifié avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void ajouterPatient() {
        Patient unPatient = new Patient(txtNom.getText(), txtPrenom.getText(), Integer.parseInt(txtAge.getText()),
                txtAdress.getText(), txtEmail.getText(), txtTel.getText(), txtNumeroSecu.getText());

        Controleur.insertPatient(unPatient);
        JOptionPane.showMessageDialog(this, "Patient ajouté avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void supprimerPatient() {
        int numLigne = this.tablePatient.getSelectedRow();
        int idPatient = Integer.parseInt(this.tableauPatients.getValueAt(numLigne, 0).toString());

        Controleur.deletePatient(idPatient);
        JOptionPane.showMessageDialog(this, "Patient supprimé.");
        rafraichirTableau();
        viderChamps();
    }

    private void filtrerPatients() {
        this.tableauPatients.setDonnes(this.obtenirPatients(this.txtFiltre.getText().trim()));
    }

    private void annulerFiltre() {
        this.txtFiltre.setText("");
        rafraichirTableau();
    }

    private void rafraichirTableau() {
        this.tableauPatients.setDonnes(this.obtenirPatients(""));
    }
}
