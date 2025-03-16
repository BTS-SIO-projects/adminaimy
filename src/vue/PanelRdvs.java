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

import controleur.Rdv;
import controleur.Controleur;
import controleur.Tableau;

public class PanelRdvs extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
	private JTextField txtdateRdv= new JTextField();
	private JTextField txtHeureRdv= new JTextField();
	private JTextField txtMotif = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btValider= new JButton("Valider");
	private JButton btSupprimer= new JButton("Supprimer");
	
	private JTable tableRdv;
	private Tableau tableauRdvs;
	
	private JPanel panelFiltre = new JPanel();
	private JTextField txtFiltre = new JTextField();
	private JButton btFiltrer = new JButton();
	
	public PanelRdvs() {
		super("Gestion des Rdvs");
		// TODO Auto-generated constructor stub
		this.panelForm.setBackground(Color.cyan);
		this.panelForm.setBounds(30,50,250,300);
		this.panelForm.setLayout(new GridLayout(6,2));

		this.panelForm.add(new JLabel("dateRdv Rdv : "));
		this.panelForm.add(this.txtdateRdv);
		this.panelForm.add(new JLabel("HeureRdv Rdv : "));
		this.panelForm.add(this.txtHeureRdv);
		this.panelForm.add(new JLabel("Motif : "));
		this.panelForm.add(this.txtMotif);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btValider);
		this.add(this.panelForm);
		
		this.btAnnuler.addActionListener(this);
		this.btValider.addActionListener(this);
		
		String entetes[] = {"ID Rdv", "dateRdv", "HeureRdv", "Motif"};
		
		this.tableauRdvs = new Tableau(this.obtenirRdvs(""), entetes);
		this.tableRdv = new JTable(this.obtenirRdvs(""), entetes);
		JScrollPane uneScroll = new JScrollPane(this.tableRdv);
		uneScroll.setBounds(380, 90, 540, 340);
		this.add(uneScroll);
		
		this.btSupprimer.setBounds(50,420,150,30);
		this.add(btSupprimer);
		this.btSupprimer.setBackground(Color.red);
		this.btSupprimer.setVisible(false);
		this.btSupprimer.addActionListener(this);

		this.tableRdv.addMouseListener(new MouseListener	(){
			
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
					numLligne = tableRdv.getSelectedRow();
					txtdateRdv.setText(tableauRdvs.getValueAt(numLligne, 1).toString());
					txtHeureRdv.setText(tableauRdvs.getValueAt(numLligne, 2).toString());
					txtMotif.setText(tableauRdvs.getValueAt(numLligne, 3).toString());
					
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

	public Object [][] obtenirRdvs(String filtre){
		ArrayList<Rdv> lesRdvs ;
		if(filtre.equals("")) {
			lesRdvs = Controleur.selectAllRdvs();			
		}else {
			lesRdvs = Controleur.selectLikeRdvs(filtre);						
		}
		Object[][] matrice = new Object[lesRdvs.size()][6];
		int i = 0;
		for(Rdv unRdv : lesRdvs) {
			matrice[i][0] = unRdv.getIdRdv();
			matrice[i][1] = unRdv.getDateRdv();
			matrice[i][2] = unRdv.getHeureRdv();
			matrice[i][3] = unRdv.getMotif();
			i++;
		}
		return matrice;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btAnnuler) {
			this.txtdateRdv.setText("");
			this.txtHeureRdv.setText("");
			this.txtMotif.setText("");
			btValider.setText("Valider");
			btSupprimer.setVisible(true);
		}else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Modifier")) {
			
			//on recupere l'ID client
			int idrdv, numLigne;
			numLigne = this.tableRdv.getSelectedRow();
			idrdv = Integer.parseInt(this.tableauRdvs.getValueAt(numLigne, 0).toString());
			
			//on recupere les données 
			String dateRdv = this.txtdateRdv.getText();
			String heureRdv = this.txtHeureRdv.getText();
			String motif = this.txtMotif.getText();
			
			//on inctancie un Rdvavec toutes les données 
			Rdv unRdv = new Rdv(idrdv, dateRdv, heureRdv, motif);
			
			//on appelle la methode update du client via le controleur
			Controleur.updateRdv(unRdv);
			
			JOptionPane.showConfirmDialog(this, "Modification du Rdv.");
			
			//on actualise l'affichage du tableau
			this.tableauRdvs.setDonnes(this.obtenirRdvs(""));
			

			//on vide les champs 
			this.txtdateRdv.setText("");
			this.txtHeureRdv.setText("");
			this.txtMotif.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
			
		}
		else if (e.getSource() ==  this.btValider && this.btValider.getText().equals("Valider")) {
			String dateRdv = this.txtdateRdv.getText();
			String heureRdv = this.txtHeureRdv.getText();
			String motif = this.txtMotif.getText();
			
			
			//instancier la classe Rdv
			Rdv unRdv = new Rdv (dateRdv, heureRdv, motif);
			
			//insertion dans la base de données
			Controleur.insertRdv(unRdv);
			
			JOptionPane.showMessageDialog(this, "Insertion réussie du Rdv.");
			
			//on actualise l'affichage du tableau 
			this.tableauRdvs.setDonnes(this.obtenirRdvs(""));
			
			this.txtdateRdv.setText("");
			this.txtHeureRdv.setText("");
			this.txtMotif.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if (e.getSource() == this.btSupprimer) {
			
			//on récupere l'ID du client 
			int idrdv, numLigne;
			numLigne = this.tableRdv.getSelectedRow();
			idrdv = Integer.parseInt(this.tableauRdvs.getValueAt(numLigne, 0).toString());
			
			//on supprime dans le BDD via le controleur
			Controleur.deleteRdv(idrdv);
			
			//on affiche un message de confirmation
			JOptionPane.showConfirmDialog(this, "Suppresson réussie du client.");
			
			//on actualise l'affichage du tableau
			this.tableauRdvs.setDonnes(this.obtenirRdvs(""));
			
			//on vide tous les champs			
			this.txtdateRdv.setText("");
			this.txtHeureRdv.setText("");
			this.txtMotif.setText("");
			
			btValider.setText("Valider");
			btSupprimer.setVisible(false);
		}
		else if(e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
		}
	}
}