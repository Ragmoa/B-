package modele;

//Correspond à une case dans le plateau 10*10
//Correspond à un Carre du coté de l'affichage 

public class Case 
{
	protected int ligne;
	protected int colonne;
	private Content typeC;
	
	public Case(Content typeC, int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
		this.typeC=typeC;
	}
	
	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}

	public int getLigne() {
		return ligne;
	}

	public void setLigne(int ligne) {
		this.ligne = ligne;
	}

	public Content getTypeC() 
	{
		return typeC;
	}

	public void setTypeC(Content typeC) 
	{
		this.typeC = typeC;
	}
	
}
