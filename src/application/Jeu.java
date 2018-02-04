package application;


import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Content;
import modele.Joueur;

//Gestion du jeu
//Met en place la scen et les panel pour l'affichage
//Gestion des tours ici aussi?

public class Jeu{
	
	private Joueur j1;
	private Joueur j2;
	private Joueur j_actuel;
	
	private Scene sceneMenu;
	
	public Jeu(Joueur j1, Joueur j2, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;

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
        final Scene scene = new Scene(groupJeu, 1200, 700, Color.web("#303030"));
        primaryStage.setScene(scene);
        primaryStage.show();
        PanelJeu panelJoueur1 = new PanelJeu();//on crée un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelJoueur1);
        PanelJeu panelAdversaire1 = new PanelJeu();//on crée un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelAdversaire1);
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        
        border.setLeft(panelJoueur1.addGridPane(true));//plateau a gauche
        border.setRight(panelAdversaire1.addGridPane(false));//plateau a droite
        groupJeu.getChildren().add(border);
        
        panelJoueur1.getGrid().setOnMouseClicked((event)-> {        	
        	for( Node node: panelJoueur1.getGrid().getChildren()) {
                if( node instanceof Parent) {
                    if( node.getBoundsInParent().contains(event.getSceneX(),  event.getSceneY())) {
                        //System.out.println( "Carre " + GridPane.getColumnIndex( node) + "/" + GridPane.getRowIndex( node));
                        clic(panelJoueur1, GridPane.getColumnIndex( node), GridPane.getRowIndex( node), true);
                    }
                }
            }
        	if (event.getButton() == MouseButton.SECONDARY) {
               panelJoueur1.resetPanel();
            }
        });
        
	}
	
	public void clic(PanelJeu panel, int colonne, int ligne, boolean playerSide) {
		System.out.println("case " + colonne + " " + ligne);
		panel.majPanel(colonne, ligne, Content.hit);
	}
}
