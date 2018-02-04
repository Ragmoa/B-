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
	private Label texteStatut = new Label(); //ratï¿½, rï¿½ussi, etc
	private Label texteJoueurAct = new Label();
	private int etapeJeu;//0=placement de bateau, 1=tir, 2=deplacement de bateau
	private Label texteEtapeJeu = new Label(); ;
	private Scene sceneMenu;
	
	public Jeu(Joueur j1, Joueur j2, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;
		this.etapeJeu=0;
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
        PanelJeu panelJoueur1 = new PanelJeu();//on crï¿½e un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelJoueur1);
        PanelJeu panelAdversaire1 = new PanelJeu();//on crï¿½e un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelAdversaire1);
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        HBox infoPartie = makeHbox();
        
        border.setLeft(panelJoueur1.addGridPane(true));//plateau a gauche
        border.setRight(panelAdversaire1.addGridPane(false));//plateau a droite
        border.setBottom(infoPartie);//infos en bas
        
        groupJeu.getChildren().add(border);
        
        //On rï¿½cupï¿½re le clic et on lance une action 
        panelJoueur1.getGrid().setOnMouseClicked((event)-> { 
        	int ligneTemp=-1, colonneTemp=-1;
        	
        	for( Node node: panelJoueur1.getGrid().getChildren()) {
                if( node instanceof Parent) {
                    if( node.getBoundsInParent().contains(event.getSceneX(),  event.getSceneY())) {
                        ligneTemp=GridPane.getRowIndex( node);
                        colonneTemp=GridPane.getColumnIndex( node);
                    }
                }
            }
        	if(ligneTemp!=-1 && colonneTemp!=-1) {
        		clic(panelJoueur1, colonneTemp, ligneTemp, true); //true si panel de gauche, avec bateaux
        	}
        });
        
        panelAdversaire1.getGrid().setOnMouseClicked((event)-> { 
        	int ligneTemp=-1, colonneTemp=-1;
        	for( Node node1: panelAdversaire1.getGrid().getChildren()) {
                if( node1 instanceof Parent) {
                	if( node1.getBoundsInParent().contains(event.getSceneX()-500-15,  event.getSceneY())) // AJUSTEMENT EN PIXELS ICI
                    {	
                        ligneTemp=GridPane.getRowIndex( node1);
                        colonneTemp=GridPane.getColumnIndex( node1);
                    }
                }
            }
        	if(ligneTemp!=-1 && colonneTemp!=-1) {
        		clic(panelAdversaire1, colonneTemp, ligneTemp, false); //false si panel de droite, celui de l'adversaire
        	}
        });
	}
	
	public void clic(PanelJeu panel, int colonne, int ligne, boolean playerSide) {
		System.out.println("case " + colonne + " " + ligne);
		panel.majPanel(colonne, ligne, Content.hit);
		int i = 0;
		j1.placer_bateau(colonne, ligne, i, true);		
		System.out.println(j1.getBateau()[0].cases_ocupees()[0][0] + " " + j1.getBateau()[0].cases_ocupees()[0][1]);
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau début partie
			panel.majPanel(colonne, ligne, Content.boat_range_and_miss);
			break;
		case 1 : //tir
			break;
		case 2 : //deplacement d'un bateau
			break;
		default :
			break;
		}	
	}
	
	public HBox makeHbox()
	{
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12)); //haut,droit,bas,gauche
	    hbox.setSpacing(10);
	    hbox.setId("infoPartie");
	    //setStyle("-fx-background-color: #336699;");
	    
        texteJoueurAct.setId("joueurAct");
        texteJoueurAct.setText(j_actuel.getPseudo());
        
        texteEtapeJeu.setId("etapeJeu");
        texteEtapeJeu.setText("Placez vos bateaux");
        texteEtapeJeu.setTextFill(Color.WHITE);
        
        texteStatut.setId("statut");
        texteStatut.setText("statut");
        texteStatut.setTextFill(Color.WHITE);
        
	    hbox.getChildren().addAll(texteJoueurAct);
	    hbox.getChildren().addAll(texteEtapeJeu);
	    hbox.getChildren().addAll(texteStatut);

	    return hbox;
	}
	
	public void majHbox() {
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau dï¿½but partie
			texteEtapeJeu.setText("Placez vos bateaux");
			break;
		case 1 : //tir
			texteEtapeJeu.setText("Choisissez votre cible");
			break;
		case 2 : //deplacement d'un bateau
			texteEtapeJeu.setText("Deplacez un bateau");
			break;
		default :
			break;
		}
		
		texteJoueurAct.setText(j_actuel.getPseudo());
		
	}
	
	public int getEtapeJeu() {
		return this.etapeJeu;
	}
}
