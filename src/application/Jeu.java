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
	int statut; //0=En attente, 1=tir reussi, 2=tir rate, 3=changement de tour
	private Label texteStatut = new Label(); //rat�, r�ussi, etc
	private Label texteJoueurAct = new Label();
	private int etapeJeu;//0=placement de bateau, 1=tir, 2=attente apres tir, 3=deplacement de bateau
	private Label texteEtapeJeu = new Label(); ;
	private Scene sceneMenu;
	
	
	public Jeu(Joueur j1, Joueur j2, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;
		this.etapeJeu=0;
		this.statut=0;
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
        PanelJeu panelJoueur1 = new PanelJeu();//on cr�e un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelJoueur1);
        PanelJeu panelAdversaire1 = new PanelJeu();//on cr�e un panel pour afficher le plateau avec les bateaux du joueur
        groupJeu.getChildren().add(panelAdversaire1);
        
        BorderPane border = new BorderPane();//le borderpane nous permet d'organiser nos elements dans la fenetre
        
        HBox infoPartie = makeHbox();
        
        border.setLeft(panelJoueur1.addGridPane(true));//plateau a gauche
        border.setRight(panelAdversaire1.addGridPane(false));//plateau a droite
        border.setBottom(infoPartie);//infos en bas
        
        groupJeu.getChildren().add(border);
        
        //On r�cup�re le clic et on lance une action 
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
        		clic(panelJoueur1, colonneTemp, ligneTemp, true, panelAdversaire1); //true si panel de gauche, avec bateaux
        		majHbox();
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
        		clic(panelAdversaire1, colonneTemp, ligneTemp, false, panelJoueur1); //false si panel de droite, celui de l'adversaire
        		majHbox();
        	}
        });
	}
	
	public void clic(PanelJeu panel, int colonne, int ligne, boolean playerSide, PanelJeu autrePanel) {
		System.out.println("case " + colonne + " " + ligne);		
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau d�but partie
			if(playerSide==true){
				panel.majPanel(colonne, ligne, Content.boat_range);
				j_actuel.setBateauplace(j_actuel.placer_bateau(colonne, ligne, j_actuel.getBateauplace(), true));
				if(j_actuel.getBateauplace()==5) {
					panel.resetPanel();
					autrePanel.resetPanel();		
					if(j_actuel.getPseudo().equals(j2.getPseudo())) {
						this.etapeJeu=1;
					}	
					changeTour();
				}
			}
			break;
		case 1 : //tir
			if(playerSide==false) {
				int reponse;
				if(j_actuel.getPseudo().equals(j1.getPseudo())) {
					reponse=j2.tir_ennemi(colonne, ligne);
					System.out.println("tir sur" + j2.getPseudo() + " : " +reponse);
				}
				else {
					reponse=j1.tir_ennemi(colonne, ligne);
					System.out.println("tir sur" + j1.getPseudo() + " : " +reponse);
				}
				//gestion des touch�s/rat�s
				//TODO verifier la range
				if(reponse==0) {
					panel.majPanel(colonne, ligne, Content.miss);
					j_actuel.set_status(colonne, ligne, -1);
					this.statut=2;
				}
				else {
					panel.majPanel(colonne, ligne, Content.hit);
					j_actuel.set_status(colonne, ligne, 1);
					this.statut=1;
				}
				this.etapeJeu=2;					
			}
			break;
		case 2 : //attente apres tir
			if(playerSide==false) {
				this.etapeJeu=3;
				this.statut=0;	
				panel.resetPanel();
				autrePanel.resetPanel();
				changeTour();
			}
			break;
		case 3 : //attente avant de changer de joueur
			if(playerSide==false) {
				//On replace les hit/miss pour la suite
				int tableauDroite[][]=j_actuel.get_status();
				for(int i=0; i<10; i++) {
					for(int j=0; j<10; j++) {
						if(tableauDroite[i][j]==-1) {
							panel.majPanel(i, j, Content.miss);
						}
						else if(tableauDroite[i][j]==1) {
							panel.majPanel(i, j, Content.hit);
						}
					}
				}
				this.etapeJeu=1;
			}
			break;
		case 4 : //deplacement d'un bateau
			
			this.etapeJeu=1;
			break;
		default :
			break;
		}	
	}
	
	public void changeTour()
	{
		if(j_actuel.getPseudo().equals(j1.getPseudo())) {
			j_actuel=j2;
		}
		else {
			j_actuel=j1;
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
        texteEtapeJeu.setText(" | Placez vos bateaux");
        texteEtapeJeu.setTextFill(Color.WHITE);
        
        texteStatut.setId("statut");
        texteStatut.setText(" | En attente...");
        texteStatut.setTextFill(Color.WHITE);
        
	    hbox.getChildren().addAll(texteJoueurAct);
	    hbox.getChildren().addAll(texteEtapeJeu);
	    hbox.getChildren().addAll(texteStatut);

	    return hbox;
	}
	
	public void majHbox() {
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau debut partie
			texteEtapeJeu.setText(" | Placez vos bateaux");
			break;
		case 1 : //tir
			texteEtapeJeu.setText(" | Choisissez votre cible");
			break;
		case 2 : //attente apres tir
			texteEtapeJeu.setText(" | Cliquez sur une case � droite pour finir le tour");
			break;
		case 3 : //attente avant de changer de joueur
			texteEtapeJeu.setText(" | Cliquez sur une case � droite pour commencer");
			break;
		case 4 : //deplacement d'un bateau
			texteEtapeJeu.setText(" | Deplacez un bateau");
			break;
		default :
			break;
		}
		
		switch(this.getStatut()) {
		case 0 : //En attente
			texteStatut.setText(" | En attente...");
			break;
		case 1 : 
			texteStatut.setText(" | Tir r�ussi !");
			break;
		case 2 :
			texteStatut.setText(" | Tir rat�...");
			break;
		case 3 :
			texteStatut.setText(" | Changement de joueur");
			break;
			
		}
		texteJoueurAct.setText(j_actuel.getPseudo());	
	}
	
	public int getEtapeJeu() {
		return this.etapeJeu;
	}
	
	public int getStatut() {
		return this.statut;
	}
}
