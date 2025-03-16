package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Lieu;
import controleur.Controleur;
import controleur.Tableau;

public class PanelLieux extends PanelPrincipal implements ActionListener {

    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtAdresse = new JTextField();
    private JTextField txtTypeLieu = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btSupprimer = new JButton("Supprimer");

    private JTable tableLieu;
    private Tableau tableauLieux;

    private JPanel panelFiltre = new JPanel();
    private JTextField txtFiltre = new JTextField(15);
    private JButton btFiltrer = new JButton("Rechercher");
    private JButton btAnnulerFiltre = new JButton("Annuler");

    public PanelLieux() {
        super("Gestion des Lieux");

        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setBounds(30, 50, 250, 300);
        this.panelForm.setLayout(new GridLayout(4, 2));

        this.panelForm.add(new JLabel("Nom Lieu : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Adresse : "));
        this.panelForm.add(this.txtAdresse);
        this.panelForm.add(new JLabel("Type Lieu : "));
        this.panelForm.add(this.txtTypeLieu);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);
        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);

        String entetes[] = {"ID", "Nom", "Adresse", "Type Lieu"};

        this.tableauLieux = new Tableau(this.obtenirLieux(""), entetes);
        this.tableLieu = new JTable(this.tableauLieux);
        JScrollPane uneScroll = new JScrollPane(this.tableLieu);
        uneScroll.setBounds(380, 90, 540, 340);
        this.add(uneScroll);

        this.btSupprimer.setBounds(50, 420, 150, 30);
        this.add(btSupprimer);
        this.btSupprimer.setBackground(Color.red);
        this.btSupprimer.setVisible(false);
        this.btSupprimer.addActionListener(this);

        this.tableLieu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int numLigne;
                if (e.getClickCount() >= 1) {
                    numLigne = tableLieu.getSelectedRow();
                    txtNom.setText(tableauLieux.getValueAt(numLigne, 1).toString());
                    txtAdresse.setText(tableauLieux.getValueAt(numLigne, 2).toString());
                    txtTypeLieu.setText(tableauLieux.getValueAt(numLigne, 3).toString());

                    btValider.setText("Modifier");
                    btSupprimer.setVisible(true);
                }
            }

            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });

        this.panelFiltre.setBackground(Color.LIGHT_GRAY);
        this.panelFiltre.setLayout(new GridLayout(1, 4));
        this.panelFiltre.setBounds(400, 50, 500, 40);
        this.panelFiltre.add(new JLabel("Rechercher : "));
        this.panelFiltre.add(this.txtFiltre);
        this.panelFiltre.add(this.btFiltrer);
        this.panelFiltre.add(this.btAnnulerFiltre);
        this.add(this.panelFiltre);

        this.btFiltrer.addActionListener(this);
        this.btAnnulerFiltre.addActionListener(this);
    }

    public Object[][] obtenirLieux(String filtre) {
        ArrayList<Lieu> lesLieux;
        if (filtre.isEmpty()) {
            lesLieux = Controleur.selectAllLieux();
        } else {
            lesLieux = Controleur.selectLikeLieux(filtre);
        }

        if (lesLieux == null) {
            lesLieux = new ArrayList<>();
        }

        Object[][] matrice = new Object[lesLieux.size()][4];
        int i = 0;
        for (Lieu unLieu : lesLieux) {
            matrice[i][0] = unLieu.getIdLieu();
            matrice[i][1] = unLieu.getNom();
            matrice[i][2] = unLieu.getAdresse();
            matrice[i][3] = unLieu.getTypeLieu();
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
                modifierLieu();
            } else {
                ajouterLieu();
            }
        } else if (e.getSource() == this.btSupprimer) {
            supprimerLieu();
        } else if (e.getSource() == this.btFiltrer) {
            filtrerLieux();
        } else if (e.getSource() == this.btAnnulerFiltre) {
            annulerFiltre();
        }
    }

    private void viderChamps() {
        this.txtNom.setText("");
        this.txtAdresse.setText("");
        this.txtTypeLieu.setText("");
        btValider.setText("Valider");
        btSupprimer.setVisible(false);
    }

    private void modifierLieu() {
        int numLigne = this.tableLieu.getSelectedRow();
        int idLieu = Integer.parseInt(this.tableauLieux.getValueAt(numLigne, 0).toString());

        Lieu unLieu = new Lieu(idLieu, txtNom.getText(), txtAdresse.getText(), txtTypeLieu.getText());

        Controleur.updateLieu(unLieu);
        JOptionPane.showMessageDialog(this, "Lieu modifié avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void ajouterLieu() {
        Lieu unLieu = new Lieu(txtNom.getText(), txtAdresse.getText(), txtTypeLieu.getText());

        Controleur.insertLieu(unLieu);
        JOptionPane.showMessageDialog(this, "Lieu ajouté avec succès.");
        rafraichirTableau();
        viderChamps();
    }

    private void supprimerLieu() {
        int numLigne = this.tableLieu.getSelectedRow();

        if (numLigne == -1) {
            JOptionPane.showMessageDialog(this, "Veuillez sélectionner un lieu à supprimer.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idLieu;
        try {
            idLieu = Integer.parseInt(this.tableauLieux.getValueAt(numLigne, 0).toString());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Erreur : Impossible de récupérer l'ID du lieu.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirmation = JOptionPane.showConfirmDialog(this, 
            "Voulez-vous vraiment supprimer ce lieu ?", "Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            try {
                Controleur.deleteLieu(idLieu);
                JOptionPane.showMessageDialog(this, "Lieu supprimé avec succès.");

                rafraichirTableau();
                viderChamps();
            } catch (Exception e) {
                // Vérifier si l'erreur vient d'une contrainte de clé étrangère
                if (e.getMessage().contains("foreign key constraint fails")) {
                    JOptionPane.showMessageDialog(this, 
                        "Suppression impossible. Des médecins exercent dans ce lieu.\nVeuillez d'abord modifier leur lieu de travail.", 
                        "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erreur lors de la suppression : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }



    private void filtrerLieux() {
        this.tableauLieux.setDonnes(this.obtenirLieux(this.txtFiltre.getText()));
    }

    private void annulerFiltre() {
        this.txtFiltre.setText("");
        rafraichirTableau();
    }

    private void rafraichirTableau() {
        this.tableauLieux.setDonnes(this.obtenirLieux(""));
    }
}
