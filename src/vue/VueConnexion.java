package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Aimy;
import controleur.Administrateur;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{

	private JPanel panelCon = new JPanel();
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtEmail = new JTextField("melissa@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("melissa");
	
	public VueConnexion() {
		this.setTitle("Application Aimy gestion du site.");
		this.setBounds(100, 100, 550, 300);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.darkGray);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon uneImage = new ImageIcon("src/images/logo.png");
		JLabel unLogo = new JLabel(uneImage);
		unLogo.setBounds(20, 20, 200, 200);
		this.add(unLogo);
		
		//construire un formulaire
		this.panelCon.setBounds(260, 60, 280, 180);
		this.panelCon.setLayout(new GridLayout(3,2));
		this.panelCon.add(new JLabel("Email :"));
		this.panelCon.add(this.txtEmail);
		this.panelCon.add(new JLabel("MDP :"));
		this.panelCon.add(this.txtMdp);
		this.panelCon.add(this.btAnnuler);
		this.panelCon.add(this.btSeConnecter);
		this.panelCon.setBackground(Color.darkGray);
		this.add(this.panelCon);
		
		//les deux boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		else if (e.getSource() == this.btSeConnecter) {
			this.traitement();
		}
		
		
	}

	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		//verification de la connexion du administrateur par email et mdp
		Administrateur unAdministrateur= Controleur.selectWhereAdministrateur(email, mdp);
		if(unAdministrateur == null) {
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants.",
					"Erreur de Connexion", JOptionPane.ERROR_MESSAGE);
		}
		else {
            JOptionPane.showMessageDialog(this, "Bienvenue à l'application Aimy\n"
                    + "Vous êtes : " + unAdministrateur.getNom() +"\n"
                    + "Prénom : " + unAdministrateur.getPrenom() +"\n",
                    "Connexion à Aimy", JOptionPane.INFORMATION_MESSAGE);
            
            // ouverture du logiciel
            Aimy.rendreVisibleVueConnexion(false);
            Aimy.creerVueGenerale(true);
        }
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
