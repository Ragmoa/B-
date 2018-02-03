<<<<<<< current
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


	 /*public void placer_bateau(int x , int y){
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
						 cases[k]1]=bateaux[i].cases_ocupees()[j][1];
					 }
				 }
			 }
			 else
				 if((bateaux[i].get_taille()+x < 10 && bateaux[i].is_horizontal()) || (bateaux[i].get_taille()+y < 10 && !bateaux[i].is_horizontal() ))
					 if(bateaux[i].get)
		 }

	    }

		 for(int i = 0; i < 5 ; i++);
	 */

			public boolean check_collision (Boat b){
				int i=0, j=0,k=0;
				for (i=0;i<5;i++){
					if (this.bateaux[i]!=null){
						for (j=0;j<this.bateaux[i].get_taille();j++){
							for (k=0,k<b.get_taille();k++){
								if (b.cases_ocupees()[k][0]==this.bateaux[i].cases_ocupees()[j][0] && b.cases_ocupees()[k][1]==this.bateaux[i].cases_ocupees()[j][1]){
									return true;
								}
							}
						}
					}
				}
				return false;
			}

			public boolean check_collision (Boat b, int x, int y){

			}
	    public Boat select_bateau(int x, int y) {//TODO: D�placer dans Joueur_humain
	        int i=0, j=0;
	          for (i=0;i<5;i++){
	            for (j=0;j<this.bateaux[i].get_taille();j++){
	              if (x==this.bateaux[i].cases_ocupees()[j][0] && y==this.bateaux[i].cases_ocupees()[j][1]){
	                return this.bateaux[i];
	              }
	            }
	          }
	          return null;
	      }

	      public void bouger(Boat b, int x, int y){
	        int[] npos=new int[2];
	        npos[0]=x;
					npos[1]=y;
	        b.set_position(npos);
	        return ;
	      }
}
=======
>>>>>>> before discard
