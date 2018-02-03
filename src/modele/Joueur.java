package modele;

import javafx.scene.paint.Color;

public class Joueur {

	private String pseudo;
	private Color couleur;
	private boolean ia;
	
	public Joueur(String pseudo, boolean ia) {
		this.pseudo = pseudo;
		this.ia = ia;
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

	public Color getCouleur() {
		return couleur;
	}

	public void setCouleur(Color couleur) {
		this.couleur = couleur;
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
}
