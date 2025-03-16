package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Administrateur;
import controleur.Aimy;

public class PanelAdministrateurs extends PanelPrincipal implements ActionListener {

    private Administrateur adminConnecte;
    private JPanel panelForm = new JPanel();
    private JTextField txtNom = new JTextField();
    private JTextField txtPrenom = new JTextField();
    private JTextField txtEmail = new JTextField();
    private JPasswordField txtMdp = new JPasswordField();
    private JTextField txtTel = new JTextField();

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btValider = new JButton("Valider");
    private JButton btModifier = new JButton("Modifier");
    private JTextArea txtInfos = new JTextArea();

    public PanelAdministrateurs() {
        super("Gestion des administrateurs");

        // Supposons que vous ayez une méthode pour obtenir l'administrateur connecté
        this.adminConnecte = Aimy.getAdministrateurConnecte(); 

        // Infos administrateur
        this.txtInfos.setText(
                "\n_______________ INFOS ADMINISTRATEUR _________________\n\n"
                + "    NOM Administrateur    : " + this.adminConnecte.getNom() + "\n\n"
                + "    Prénom Administrateur : " + this.adminConnecte.getPrenom() + "\n\n"
                + "    Email Administrateur  : " + this.adminConnecte.getEmail() + "\n\n"
                + "    TEL  Administrateur   : " + this.adminConnecte.getTelephone() + "\n\n"
                + "\n______________________________________________"
        );
        this.txtInfos.setBackground(Color.cyan);
        this.txtInfos.setBounds(50, 100, 400, 200);
        this.add(this.txtInfos);

        // Configuration du panel de formulaire
        this.panelForm.setBackground(Color.cyan);
        this.panelForm.setLayout(new GridLayout(7, 2));
        this.panelForm.setBounds(460, 100, 400, 250);
        this.panelForm.add(new JLabel("Nom Administrateur : "));
        this.panelForm.add(this.txtNom);
        this.panelForm.add(new JLabel("Prénom Administrateur : "));
        this.panelForm.add(this.txtPrenom);
        this.panelForm.add(new JLabel("Email Administrateur : "));
        this.panelForm.add(this.txtEmail);
        this.panelForm.add(new JLabel("Mot de passe : "));
        this.panelForm.add(this.txtMdp);
        this.panelForm.add(new JLabel("Téléphone : "));
        this.panelForm.add(this.txtTel);
        this.panelForm.add(this.btAnnuler);
        this.panelForm.add(this.btValider);

        this.add(this.panelForm);

        this.btAnnuler.addActionListener(this);
        this.btValider.addActionListener(this);
        this.btModifier.addActionListener(this);

        this.btModifier.setBounds(200, 370, 120, 40);
        this.add(this.btModifier);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btAnnuler) {
            this.panelForm.setVisible(false);
            this.txtNom.setText("");
            this.txtPrenom.setText("");
            this.txtEmail.setText("");
            this.txtMdp.setText("");
            this.txtTel.setText("");
        } else if (e.getSource() == this.btValider) {
            String nom = this.txtNom.getText();
            String prenom = this.txtPrenom.getText();
            String email = this.txtEmail.getText();
            String mdp = new String(this.txtMdp.getPassword());
            String tel = this.txtTel.getText();

            if (nom.equals("") || prenom.equals("") || email.equals("") || mdp.equals("") || tel.equals("")) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
            } else {
                // Mise à jour de l'administrateur
                this.adminConnecte.setNom(nom);
                this.adminConnecte.setPrenom(prenom);
                this.adminConnecte.setEmail(email);
                this.adminConnecte.setPassword(mdp);
                this.adminConnecte.setTelephone(tel);

                Controleur.updateAdministrateur(adminConnecte);

                JOptionPane.showMessageDialog(this, "Modification Profil Réussie");

                // Actualisation des infos affichées
                this.txtInfos.setText(
                        "\n_______________ INFOS ADMINISTRATEUR _________________\n\n"
                        + "    NOM Administrateur    : " + this.adminConnecte.getNom() + "\n\n"
                        + "    Prénom Administrateur : " + this.adminConnecte.getPrenom() + "\n\n"
                        + "    Email Administrateur  : " + this.adminConnecte.getEmail() + "\n\n"
                        + "    TEL  Administrateur   : " + this.adminConnecte.getTelephone() + "\n\n"
                        + "\n______________________________________________"
                );

                this.panelForm.setVisible(false);
            }
        } else if (e.getSource() == this.btModifier) {
            // Affichage du formulaire de modification
            this.panelForm.setVisible(true);
            this.txtNom.setText(this.adminConnecte.getNom());
            this.txtPrenom.setText(this.adminConnecte.getPrenom());
            this.txtEmail.setText(this.adminConnecte.getEmail());
            this.txtTel.setText(this.adminConnecte.getTelephone());
        }
    }
}
