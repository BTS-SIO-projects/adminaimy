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

import controleur.Document;
import controleur.Controleur;
import controleur.Tableau;

public class PanelDocuments extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
	private JTextField txtNom= new JTextField();
	private JTextField txtPrenom= new JTextField();
	private JTextField txtAdress = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JTextField txtTel= new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider= new JButton("Valider");
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTable tableDocument;
	private Tableau tableauDocuments;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton();
	
	public PanelDocuments() {
		super("Gestion des Documents");
		// TODO Auto-generated constructor stub
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(30,50,250,300);
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("nom Document : "));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("prénom Document : "));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("adresse : "));
		this.panelForm.add(this.txtAdress);
		this.panelForm.add(new JLabel("email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("telephone : "));
		this.panelForm.add(this.txtTel);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes[] = {"ID Document", "typeDoc", " url", "datedepot", "  description", " idmedecin","idpatient","Idrdv"};
		
		this.tableauDocuments = new Tableau(this.obtenirDocuments(""), entetes);
		this.tableDocument= new JTable(this.obtenirSDocuments(""), entetes);
		JScrollPane uneScroll = new JScrollPane(this.tableDocument);
		uneScroll.setBounds(380, 90, 540, 340);
		this.add(uneScroll);
		
		this.btSupprimer.setBounds(50,420,150,30);
		this.add(btSupprimer);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.setVisible(false);
		this.btSupprimer.addActionListener(this);

		this.tableDocument.addMouseListener(new MouseListener	(){
			
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
					numLligne = tableDocument.getSelectedRow();
					txtNom.setText(tableauDocuments.getValueAt(numLligne, 1).toString());
					txtPrenom.setText(tableauDocuments.getValueAt(numLligne, 2).toString());
					txtAdress.setText(tableauDocuments.getValueAt(numLligne, 3).toString());
					txtEmail.setText(tableauDocuments.getValueAt(numLligne, 4).toString());
					txtTel.setText(tableauDocuments.getValueAt(numLligne, 5).toString());
					
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

	public Object [][] obtenirSpecialites(String filtre){
		ArrayList<Document> lesDocuments ;
		if(filtre.equals("")) {
			lesDocuments = Controleur.selectAllDocuments();			
		}else {
			lesDocuments = Controleur.selectLikeDocuments(filtre);						
		}
		Object[][] matrice = new Object[lesDocuments.size()][6];
		int i = 0;
		for(Patient unDocument : lesDocuments) {
			matrice[i][0] = unDocument.getIdPatient();
			matrice[i][1] = unDocument.getNom();
			matrice[i][2] = unDocument.getPrenom();
			matrice[i][3] = unDocument.getAdresse();
			matrice[i][4] = unDocument.getEmail();
			matrice[i][5] = unDocument.getTel();
			i++;
		}
		return matrice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdress.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			btValider.setText("Valider");
			btSupprimer.setVisible(true);
		}else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on recupere l'ID client
			int idDocument, numLigne;
			numLigne = this.tableDocument.getSelectedRow();
			idDocument= Integer.parseInt(this.tableauDocuments.getValueAt(numLigne, 0).toString());
			
			//on recupere les données 
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdress.getText();
			String email = this.txtEmail.getText();
			String tel = this.txtTel.getText();
			
			//on inctancie un Document avec toutes les données 
			Patient unDocument = new Document (ID Document, typeDoc,  url, datedepot,  description,  idmedecin,idpatient,Idrdv);

			//on appelle la methode update du client via le controleur
			Controleur.updateDocument(unDocument);
			
			JOptionPane.showConfirmDialog(this, "Modification du Document.");
			
			//on actualise l'affichage du tableau
			this.tableauDocuments.setDonnes(this.obtenirDocuments(""));
			

			//on vide les champs 
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdress.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
			
		}
		else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Valider")) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String adresse = this.txtAdress.getText();
			String email = this.txtEmail.getText();
			String tel = this.txtTel.getText();
			
			
			//instancier la classe Document
			Document unDocument = new Document(idDocument, typeDoc,  url, datedepot,  description,  idmedecin,idpatient,Idrdv);
			
			//insertion dans la base de données
			Controleur.insertDocument(unDocument);
			
			JOptionPane.showMessageDialog(this, "Insertion réussie du Document.");
			
			//on actualise l'affichage du tableau 
			this.tableauDocumentes.setDonnes(this.obtenirDocuments(""));
			
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdress.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'ID du Documents 
			int idDocument, numLigne;
			numDocument = this.tableDocument.getSelectedRow();
			idDocument = Integer.parseInt(this.tableauDocuments.getValueAt(numLigne, 0).toString());
			
			//on supprime dans le BDD via le controleur
			Controleur.deleteDocument(idDocument);
			
			//on affiche un message de confirmation
			JOptionPane.showConfirmDialog(this, "Suppresson réussie du client.");
			
			//on actualise l'affichage du tableau
			this.tableauDocuments.setDonnes(this.obtenirDocuments(""));
			
			//on vide tous les champs			
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtAdress.setText("");
			this.txtEmail.setText("");
			this.txtTel.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if(e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
		}
	}
}