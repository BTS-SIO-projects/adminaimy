package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Diplome;
import controleur.Controleur;
import controleur.Tableau;

public class PanelDiplomes extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
	private JTextField txtNom= new JTextField();
	private JTextField txtsigle = new JTextField();
	private JTextField txtfaculte = new JTextField();
	
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider= new JButton("Valider");
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTable tableDiplome;
	private Tableau tableauDiplomes;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton();
	
	public PanelDiplomes() {
		super("Gestion des Diplomes");
		// TODO Auto-generated constructor stub
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(30,50,250,300);
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("nom Diplome : "));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("sigle Diplome : "));
		this.panelForm.add(this.txtsigle );
		this.panelForm.add(new JLabel("Facultée : "));
		this.panelForm.add(this.txtfaculte);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes[] = {"ID Diplome", "Nom", "Sigle", "Facultée"};
		
		this.tableauDiplomes = new Tableau(this.obtenirDiplomes(""), entetes);
		this.tableDiplome = new JTable(this.obtenirDiplomes(""), entetes);
		JScrollPane uneScroll = new JScrollPane(this.tableDiplome);
		uneScroll.setBounds(380, 90, 540, 340);
		this.add(uneScroll);
		
		this.btSupprimer.setBounds(50,420,150,30);
		this.add(btSupprimer);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.setVisible(false);
		this.btSupprimer.addActionListener(this);

		this.tableDiplome.addMouseListener(new MouseListener	(){
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLligne;
				if(e.getClickCount()>=1) {
					numLligne = tableDiplome.getSelectedRow();
					txtNom.setText(tableauDiplomes.getValueAt(numLligne, 1).toString());
					txtsigle .setText(tableauDiplomes.getValueAt(numLligne, 2).toString());
					txtfaculte.setText(tableauDiplomes.getValueAt(numLligne, 3).toString());
				
					
					btValider.setText("Valider");
					btSupprimer.setVisible(true);
				}
			}
		});
		this.panelFiltre.setBackground(Color.cyan);
		this.panelFiltre.setLayout(new GridLayout(1, 3));
		this.panelFiltre.setBounds(400, 50, 400, 20);
		this.panelFiltre.add(new JLabel("Filtrer par: "));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		this.btFiltrer.addActionListener(this);
	}

	public Object [][] obtenirDiplomes(String filtre){
		ArrayList<Diplome> lesDiplomes ;
		if(filtre.equals("")) {
			lesDiplomes = Controleur.selectAllDiplomes();			
		}else {
			lesDiplomes = Controleur.selectLikeDiplomes(filtre);						
		}
		Object[][] matrice = new Object[lesDiplomes.size()][6];
		int i = 0;
		for(Diplome unDiplome : lesDiplomes) {
			matrice[i][0] = unDiplome.getIdDiplome();
			matrice[i][1] = unDiplome.getNom();
			matrice[i][2] = unDiplome.getSigle();
			i++;
		}
		return matrice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtsigle .setText("");
			this.txtfaculte.setText("");
		
			btValider.setText("Valider");
			btSupprimer.setVisible(true);
		}else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on recupere l'ID client
			int idDiplome, numLigne;
			numLigne = this.tableDiplome.getSelectedRow();
			idDiplome = Integer.parseInt(this.tableauDiplomes.getValueAt(numLigne, 0).toString());
			
			//on recupere les données 
			String nom = this.txtNom.getText();
			String sigle  = this.txtsigle .getText();
			String faculte = this.txtfaculte.getText();
		
			
			//on inctancie un Diplomeavec toutes les données 
			Diplome unDiplome = new Diplome (idDiplome, nom,sigle,faculte,);
			
			//on appelle la methode update du client via le controleur
			Controleur.updateDiplome(unDiplome);
			
			JOptionPane.showConfirmDialog(this, "Modification du Diplome.");
			
			//on actualise l'affichage du tableau
			this.tableauDiplomes.setDonnes(this.obtenirDiplomes(""));
			

			//on vide les champs 
			this.txtNom.setText("");
			this.txtsigle.setText("");
			this.txtfaculte.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
			
		}
		else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Valider")) {
			String nom = this.txtNom.getText();
			String sigle  = this.txtsigle.getText();
			String faculte = this.txtfaculte.getText();
		
			
			
			//instancier la classe Diplome
			Diplome unDiplome = new Diplome (nom,  sigle , faculte);
			
			//insertion dans la base de données
			Controleur.insertDiplome(unDiplome);
			
			JOptionPane.showMessageDialog(this, "Insertion réussie du Diplome.");
			
			//on actualise l'affichage du tableau 
			this.tableauDiplomes.setDonnes(this.obtenirDiplomes(""));
			
			this.txtNom.setText("");
			this.setText("");
			this.txtfaculte.setText("");
		
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'ID du client 
			int idDiplome, numLigne;
			numLigne = this.tableDiplome.getSelectedRow();
			idDiplome = Integer.parseInt(this.tableauDiplomes.getValueAt(numLigne, 0).toString());
			
			//on supprime dans le BDD via le controleur
			Controleur.deleteDiplome(idDiplome);
			
			//on affiche un message de confirmation
			JOptionPane.showConfirmDialog(this, "Suppresson réussie du client.");
			
			//on actualise l'affichage du tableau
			this.tableauDiplomes.setDonnes(this.obtenirDiplomes(""));
			
			//on vide tous les champs			
			this.txtNom.setText("");
			this.txtsigle .setText("");
			this.txtfaculte.setText("");
		
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if(e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
		}
	}
}