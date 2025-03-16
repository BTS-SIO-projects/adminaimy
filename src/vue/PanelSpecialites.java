package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

import controleur.Specialite;
import controleur.Controleur;
import controleur.Tableau;

public class PanelSpecialites extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtCategorie = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable tableSpecialite;
    private Tableau tableauSpecialites;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField(15);
    private JButton btFiltrer = new JButton("Rechercher");
    private JButton btAnnulerFiltre = new JButton("Annuler");

    public PanelSpecialites() {
        super("Gestion des Spécialités");

        // Panel Formulaire
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 50, 250, 150);
        this.panelForm.setLayout(new GridLayout(2, 2));

        this.panelForm.add(new JLabel("Nom Spécialité : "));
        this.panelForm.add(this.txtCategorie);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        // Tableau des spécialités
        String entetes[] = { "ID spécialité", "Catégorie" };

        this.tableauSpecialites = new Tableau(this.obtenirSpecialites(""), entetes);
        this.tableSpecialite = new JTable(this.tableauSpecialites);
        JScrollPane uneScroll = new JScrollPane(this.tableSpecialite);
        uneScroll.setBounds(380, 90, 540, 340);
        this.add(uneScroll);

        this.btSupprimer.setBounds(50, 420, 150, 30);
        this.add(btSupprimer);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.addActionListener(this);

        this.tableSpecialite.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne = tableSpecialite.getSelectedRow();
                if (numLigne == -1) {
                    return;
                }

                Object valeur = tableauSpecialites.getValueAt(numLigne, 1);
                txtCategorie.setText(valeur != null ? valeur.toString() : "");

                btValider.setText("Modifier");
                btSupprimer.setVisible(true);
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        // Panel Filtre
        this.panelFiltre.setBackground(Color.LIGHT_GRAY);
        this.panelFiltre.setLayout(new GridLayout(1, 4, 5, 5));
        this.panelFiltre.setBounds(380, 50, 540, 30);
        this.panelFiltre.add(new JLabel("Filtrer par : "));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.panelFiltre.add(this.btAnnulerFiltre);
        this.add(this.panelFiltre);

        this.btFiltrer.addActionListener(this);
        this.btAnnulerFiltre.addActionListener(this);

        this.btSupprimer.setBounds(50, 420, 150, 30);
        this.add(btSupprimer);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.addActionListener(this);

    }

    public Object[][] obtenirSpecialites(String filtre) {
        ArrayList<Specialite> lesSpecialites;
        if (filtre.isEmpty()) {
            lesSpecialites = Controleur.selectAllSpecialites();
        } else {
            lesSpecialites = Controleur.selectLikeSpecialites(filtre);
        }

        Object[][] matrice = new Object[lesSpecialites.size()][2];
        int i = 0;
        for (Specialite uneSpecialite : lesSpecialites) {
            matrice[i][0] = uneSpecialite.getIdSpecialite();
            matrice[i][1] = uneSpecialite.getCategorie();
            i++;
        }
        return matrice;
    }

    private void rafraichirTableau() {
        this.tableauSpecialites.setDonnes(this.obtenirSpecialites(""));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            viderChamps();
        } else if (e.getSource() == this.btValider) {
            if (this.btValider.getText().equals("Modifier")) {
                modifierSpecialite();
            } else {
                ajouterSpecialite();
            }
        } else if (e.getSource() == this.btSupprimer) {
            supprimerSpecialite();
        } else if (e.getSource() == this.btFiltrer) {
            filtrerSpecialites();
        } else if (e.getSource() == this.btAnnulerFiltre) {
            annulerFiltre();
        }
    }

    private void viderChamps() {
        this.txtCategorie.setText("");
        btValider.setText("Valider");
        btSupprimer.setVisible(false);
    }

    private void modifierSpecialite() {
        int numLigne = this.tableSpecialite.getSelectedRow();
        if (numLigne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une spécialité à modifier.");
            return;
        }

        int idSpecialite = Integer.parseInt(this.tableauSpecialites.getValueAt(numLigne, 0).toString());
        String categorie = this.txtCategorie.getText();

        Specialite uneSpecialite = new Specialite(idSpecialite, categorie);
        Controleur.updateSpecialite(uneSpecialite);

        JOptionPane.showMessageDialog(this, "Spécialité modifiée avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void ajouterSpecialite() {
        String categorie = this.txtCategorie.getText();
        if (categorie.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez entrer une catégorie.");
            return;
        }

        Specialite uneSpecialite = new Specialite(categorie);
        Controleur.insertSpecialite(uneSpecialite);

        JOptionPane.showMessageDialog(this, "Spécialité ajoutée avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void supprimerSpecialite() {
        int numLigne = this.tableSpecialite.getSelectedRow();

        if (numLigne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner une spécialité à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idSpecialite;
        try {
            idSpecialite = Integer.parseInt(this.tableauSpecialites.getValueAt(numLigne, 0).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erreur : Impossible de récupérer l'ID de la spécialité.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment supprimer cette spécialité ?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                Controleur.deleteSpecialite(idSpecialite);
                JOptionPane.showMessageDialog(this, "Spécialité supprimée avec succès.");

                rafraichirTableau();
                viderChamps();

            } catch (Exception e) {
                if (e.getMessage().contains("foreign key constraint fails")) {
                    JOptionPane.showMessageDialog(this, 
                        "Suppression impossible. Des médecins sont encore associés à cette spécialité.\nVeuillez d'abord modifier leur spécialité avant de supprimer.", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }




    private void filtrerSpecialites() {
        String filtre = this.txtFiltre.getText().trim();
        this.tableauSpecialites.setDonnes(this.obtenirSpecialites(filtre));
    }

    private void annulerFiltre() {
        this.txtFiltre.setText("");
        rafraichirTableau();
    }
}
