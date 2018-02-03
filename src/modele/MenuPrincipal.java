package modele;

import javafx.scene.paint.Color;

//Stockage liée au menu principal
//Correspond à PanelMenuPrincipal du coté affichage

public class MenuPrincipal {

	private Joueur j1, j2;
	
	public MenuPrincipal() {
		j1 = new Joueur("Joueur 1", false);
		j2 = new Joueur("Joueur 2", false);
	}

	public Joueur getJ1() {
		return j1;
	}

	public void setJ1(Joueur j1) {
		this.j1 = j1;
	}

	public Joueur getJ2() {
		return j2;
	}

	public void setJ2(Joueur j2) {
		this.j2 = j2;
	}

}
