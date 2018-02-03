package modele;

import java.util.ArrayList;

public class PlateauJeu{
	
	private ArrayList<ArrayList<Case>> tablier = new ArrayList<ArrayList<Case>>();
	
	public PlateauJeu(Joueur j1, Joueur j2) {//on crée le plateau
		for(int i=0; i<10; i++) {
			ArrayList<Case> ligne = new ArrayList<Case>();
			for(int j=0; j<10; j++) {
				ligne.add(new Case(Content.sea, i, j));
			}
			tablier.add(ligne);
		}
	}
	
	public Case getCase(int i, int j)
	{
		return tablier.get(i).get(j);
	}
}
