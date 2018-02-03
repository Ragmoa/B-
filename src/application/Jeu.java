package application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import modele.*;

//Gestion du jeu
//Met en place la scen et les panel pour l'affichage
//Gestion des tours ici aussi?

public class Jeu{
	
	private Joueur j1;
	private Joueur j2;
	private Joueur j_actuel;
	private PlateauJeu plateau;
	
	private Scene sceneMenu;
	
	public Jeu(Joueur j1, Joueur j2, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;
        plateau = new PlateauJeu(j1, j2); 

        this.sceneMenu = sceneMenu;
	}
	
	public void jouer(Stage primaryStage){        
		if(j2.isIa()){
        	//pve(primaryStage); //joueur versus IA
        }
        else{
        	pvp(primaryStage); //joueur versus joueur
        }
	}
	
	public void pvp(Stage primaryStage)
	{	
        j_actuel=j1;

        Group groupJeu = new Group();
        final Scene scene = new Scene(groupJeu, 1000, 700, Color.web("#303030"));
        primaryStage.setScene(scene);
        primaryStage.show();
        PanelJeu panel = new PanelJeu();//on crée un panel pour afficher le plateau
        groupJeu.getChildren().add(panel);    
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        border.setCenter(panel.addGridPane(plateau));//plateau au centre
        groupJeu.getChildren().add(border);
        
	}
}
