package modele;

import application.Boat;
import javafx.scene.paint.Color;

public class Joueur {

	private String pseudo;
	private boolean ia;
	private Boat[] bateaux;
	
	public Joueur(String pseudo, boolean ia) {
		this.pseudo = pseudo;
		this.ia = ia;
		int pos[] = {0,0};
//		bateaux[0] = new Boat(5, 2, true, pos);
//		bateaux[1] = new Boat(4, 2, true, pos);
//		bateaux[2] = new Boat(3, 2, true, pos);
//		bateaux[3] = new Boat(3, 4, true, pos);
//		bateaux[4] = new Boat(2, 5, true, pos);		
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public boolean isIa() {
		return ia;
	}

	public void setIa(boolean ia) {
		this.ia = ia;
	}

	public String toString() {
		return pseudo;
	}
	
	//EVITE QUE LES DEUX JOUEURS AIENT LE MEME PSEUDO
	public void verifierPseudo(Joueur joueur) {
		if(pseudo.length() == 0)
			pseudo = "Anonymous";
		if(pseudo.length() > 12)
			pseudo = pseudo.substring(0, 12);
		if(pseudo.equals(joueur.getPseudo())) {
			if(joueur.getPseudo().equals("Copieur"))
				pseudo = "Copiteur";
			else
				pseudo = "Copieur";
		}
	}
	
	
//	 public void placer_bateau(int x , int y){
//
//		 for(int i = 0; i < 5 ; i++);
//	    }
//	    
//	    public void update_boat(Boat bateau, PlateauJeu plate) {
//	    	int posi[][] = bateau.cases_ocupees();
//	    	for(int i = 0 ;  i < posi[0].length ; i++) {
//	    		for(int j = 0 ; j < posi[1].length ; j++) {
//	    				plate.getCase(posi[i][0], posi[j][1]).setTypeC(Content.boat);
//	    		}  		
//	    	}
//	    }
//	    
//	    public void tirer(PlateauJeu plate, int i, int j) {
//	    	if(plate.getCase(i,j).getTypeC()==Content.boat) {
//	    		plate.getCase(i, j).setTypeC(Content.boat_hit);
//	    		System.out.println("touché");
//	    	}
//	    	else if(plate.getCase(i, j).getTypeC()==Content.boat_hit) {
//	    		System.out.println("bateau déjà abimé");	    			    		
//	    	}
//	    	else
//	    		System.out.println("raté");
//	    }
//	    
//	    public void select_bateau(PlateauJeu plate, int i, int j) {
//	    	if(plate.getCase(i,j).getTypeC()==Content.boat) {
//	    		
//	    	}
//	    }
//	    public void bouger(PlateauJeu plate, int i, int j){
//	    	
//	    }
}
