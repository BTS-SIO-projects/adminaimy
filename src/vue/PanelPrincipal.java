package vue;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel {
    
    public PanelPrincipal(String titre) {
        this.setBackground(new Color(42, 157, 143));
        this.setBounds(20, 80, 940, 480);
        this.setLayout(null);
        
        // Titre du panel
        JLabel lbTitre = new JLabel(titre);
        lbTitre.setFont(new Font("Arial", Font.BOLD, 18)); // Police plus lisible
        lbTitre.setForeground(Color.WHITE); // Meilleur contraste
        lbTitre.setBounds(370, 10, 400, 30); // Ajustement de la taille du titre
        this.add(lbTitre);
        
        this.setVisible(false);
    }
}
