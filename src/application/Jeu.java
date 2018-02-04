package application;


import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
        final Scene scene = new Scene(groupJeu, 1040, 700, Color.web("#303030"));
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        PanelJeu panelJoueur1 = new PanelJeu();//on crée un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelJoueur1);
        PanelJeu panelAdversaire1 = new PanelJeu();//on crée un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelAdversaire1);
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        HBox infoPartie = makeHbox();
        
        border.setLeft(panelJoueur1.addGridPane(true));//plateau a gauche
        border.setRight(panelAdversaire1.addGridPane(false));//plateau a droite
        border.setBottom(infoPartie);//infos en bas
        
        groupJeu.getChildren().add(border);
        
        panelJoueur1.getGrid().setOnMouseClicked((event)-> { 
        	int ligneTemp=-1, colonneTemp=-1;
        	
        	for( Node node: panelJoueur1.getGrid().getChildren()) {
                if( node instanceof Parent) {
                    if( node.getBoundsInParent().contains(event.getSceneX(),  event.getSceneY())) {
                        //System.out.println( "Carre " + GridPane.getColumnIndex( node) + "/" + GridPane.getRowIndex( node));
                        ligneTemp=GridPane.getRowIndex( node);
                        colonneTemp=GridPane.getColumnIndex( node);
                    }
                }
            }
        	if(ligneTemp!=-1 && colonneTemp!=-1) {
        		clic(panelJoueur1, colonneTemp, ligneTemp, true);
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
	
	public HBox makeHbox()
	{
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12)); //haut,droit,bas,gauche
	    hbox.setSpacing(10);
	    hbox.setId("infoPartie");
	    //setStyle("-fx-background-color: #336699;");
	    
        Label nom1 = new Label(j1.getPseudo());
        nom1.setFont(Font.font("Arial", FontWeight.BOLD, 35));
        nom1.setStyle("-fx-effect: dropshadow(three-pass-box, #FFFFFF, 3, 0.8, 0, 0);");

	    hbox.getChildren().addAll(nom1);

	    return hbox;
	}
}
