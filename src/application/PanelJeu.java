package application;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import modele.Joueur;
import modele.PlateauJeu;

//Classe pour l'affichage d'un quadrillage
//Correspond à PlateauJeu coté données

public class PanelJeu extends Parent {
    
    private GridPane grid = new GridPane();
    private ArrayList<ArrayList<Carre>> liste = new ArrayList<ArrayList<Carre>>();
    
    public PanelJeu()
    {
      
    }
    
    public GridPane addGridPane(PlateauJeu plate)
    {
    	//la gridpane affiche ses éléments en grille
        int i,j;
        //grid.setGridLinesVisible(true);
        for(i=0;i<10;i++)
        {
            ArrayList<Carre> ligne = new ArrayList<Carre>();
            for(j=0;j<10;j++)
            {
                Carre bouton = new Carre(plate.getCase(i,j));//on le compose de carrés
                ligne.add(bouton);
                grid.add(bouton, i, j);
            }
            liste.add(ligne);
        }
        grid.setPadding(new Insets(10,0,0,5));//haut,droit,bas,gauche
        return grid;
    }
    
    public GridPane getGrid(){
        return grid;
    }
}
