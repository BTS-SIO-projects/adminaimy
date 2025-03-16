package vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class PanelAccueil extends JPanel {

    public PanelAccueil() {
        this.setBackground(new Color(42, 157, 143)); // Couleur de fond principale
        this.setBounds(20, 80, 940, 480);
        this.setLayout(new GridBagLayout()); // Utilisation de GridBagLayout pour le centrage parfait

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new java.awt.Insets(10, 10, 10, 10); // Marge autour du titre

        // **Titre principal**
        JLabel titre = new JLabel("Bienvenue sur notre plateforme de gestion des rendez-vous médicaux", JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 20)); // Police plus grande et en gras
        titre.setForeground(new Color(33, 45, 62)); // Bleu foncé pour un look professionnel
        titre.setOpaque(true);
        titre.setBackground(new Color(200, 220, 240)); // Fond légèrement bleu pour un contraste doux
        titre.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Ajout d'un padding
        this.add(titre, gbc);

        // **Texte d'introduction**
        JTextArea textePresentation = new JTextArea(
            "Notre site web vous permet de prendre facilement rendez-vous avec des médecins qualifiés, " +
            "tout en offrant une gestion sécurisée et efficace des informations. Nous assurons une " +
            "vérification minutieuse de tous les médecins inscrits, garantissant que seuls des " +
            "professionnels légitimes figurent dans notre base de données.\n\n" +
            "En tant qu'administrateurs, nous avons la possibilité de modifier et de supprimer les " +
            "informations des médecins et des patients pour garantir la mise à jour et la fiabilité " +
            "de notre système. Vous pouvez également consulter vos rendez-vous et accéder à des " +
            "informations pertinentes concernant votre santé.\n\n" +
            "Grâce à une interface simple et intuitive, nous rendons l'accès aux soins plus rapide et " +
            "plus sécurisé pour tous."
        );
        
        textePresentation.setFont(new Font("Arial", Font.PLAIN, 16));
        textePresentation.setForeground(Color.BLACK);
        textePresentation.setBackground(Color.WHITE);
        textePresentation.setWrapStyleWord(true);
        textePresentation.setLineWrap(true);
        textePresentation.setEditable(false);
        textePresentation.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // **Ajout d'un cadre autour du texte pour un effet professionnel**
        JPanel panelTexte = new JPanel();
        panelTexte.setLayout(new GridBagLayout());
        panelTexte.setBackground(Color.WHITE);
        panelTexte.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(33, 45, 62), 2), // Bordure bleu foncé
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Padding interne
        ));

        // **Ajout d'un JScrollPane pour gérer le texte long**
        JScrollPane scrollPane = new JScrollPane(textePresentation);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new java.awt.Dimension(800, 300));
        panelTexte.add(scrollPane);

        // **Ajout du panel de texte au GridBagLayout**
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        this.add(panelTexte, gbc);

        this.setVisible(false);
    }
}
