package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel{
	private Object donnes[][];
	private String entetes[];
	
	public Tableau(Object[][] donnes, String[] entetes) {
		super();
		this.donnes = donnes;
		this.entetes = entetes;
	}

	@Override
	public int getRowCount() {
		return this.donnes.length;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return this.donnes[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return this.entetes[column];
	}

	public void setDonnes(Object matrice[][] ) {
		this.donnes = matrice;
		this.fireTableDataChanged();
	}
	
	
}
