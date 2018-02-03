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
		bateaux[0] = new Boat(5, 2, true, pos);
		bateaux[1] = new Boat(4, 2, true, pos);
		bateaux[2] = new Boat(3, 2, true, pos);
		bateaux[3] = new Boat(3, 4, true, pos);
		bateaux[4] = new Boat(2, 5, true, pos);		
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
	
	
	 public void placer_bateau(int x , int y){
		 int[][] cases=new int[17][2];		 
		 int pos[] = {x,y};
		 int k = 0;
		 for(int i = 0; i < 5 ; i++) {
			 if(i==0)
			 {
				 
				 if((bateaux[i].get_taille()+x < 10 && bateaux[i].is_horizontal()) || (bateaux[i].get_taille()+y < 10 && !bateaux[i].is_horizontal() ))
				 { 
					 bateaux[i].set_position(pos);
					 for(int j = 0 ; j < bateaux[i].get_taille() ; j++) {
						 cases[k][0]=bateaux[i].cases_ocupees()[j][0];
						 cases[k][0]=bateaux[i].cases_ocupees()[j][1];
					 }
				 }
			 }
			 else
				 if((bateaux[i].get_taille()+x < 10 && bateaux[i].is_horizontal()) || (bateaux[i].get_taille()+y < 10 && !bateaux[i].is_horizontal() ))
					 if(bateaux[i].get)
			 
			 
			 bateaux[i].set_position(pos);
			 bateaux[i].cases_ocupees();
		 }
		 
	    }
	    

}
