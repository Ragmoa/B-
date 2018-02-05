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
	int statut; //0=En attente, 1=tir reussi, 2=bateau coule, 3=tir rate, 4=changement de tour, 5=clic droit annule, 6=vertical, 7=horizontal
	private Label texteStatut = new Label(); //rate, touche, coule...
	private Label texteJoueurAct = new Label();
	private int etapeJeu;//0=placement de bateau, 1=tir, 2=attente apres tir, 3=deplacement de bateau
	private Label texteEtapeJeu = new Label(); ;
	private Scene sceneMenu;
	private PanelVictoire panelVictoire;
	private Stage primaryStage;
	private Boat bateauTemp;
	
	public Jeu(Joueur j1, Joueur j2, Scene sceneMenu)
	{		
		this.j1=j1;
		this.j2=j2;
		this.etapeJeu=0;
		this.statut=0;
        this.sceneMenu = sceneMenu;
	}
	
	public void jouer(Stage primaryStage){   
		this.primaryStage=primaryStage;
		pvp();
	}
	
	public void pvp()
	{	
        j_actuel=j1;

        Group groupJeu = new Group();
        final Scene scene = new Scene(groupJeu, 1040, 650, Color.web("#303030"));
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
        this.statut=7;
        //On recupere le clic et on lance une action 
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
        		if (event.getButton() == MouseButton.SECONDARY) {
            		clicDroit(panelJoueur1, colonneTemp, ligneTemp, true, panelAdversaire1); //true si panel de gauche, avec bateaux
                }
        		else {
            		clic(panelJoueur1, colonneTemp, ligneTemp, true, panelAdversaire1); //true si panel de gauche, avec bateaux
        		}
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
        		if (event.getButton() == MouseButton.SECONDARY) {
            		clicDroit(panelAdversaire1, colonneTemp, ligneTemp, false, panelJoueur1); //false si panel de droite, celui de l'adversaire
        		}
        		else {
            		clic(panelAdversaire1, colonneTemp, ligneTemp, false, panelJoueur1); //false si panel de droite, celui de l'adversaire
        		}
        		majHbox();
        	}
        });
	}
	
	public void clic(PanelJeu panel, int colonne, int ligne, boolean playerSide, PanelJeu autrePanel) {
		System.out.println("case " + colonne + " " + ligne);		
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau d�but partie
			if(playerSide==true){
				j_actuel.setBateauplace(j_actuel.placer_bateau(colonne, ligne, j_actuel.getBateauplace(),j_actuel.get_horizontal()));
				j_actuel.setCases_joueur(j_actuel.get_player_boat()); 
			       
			    for(int i=0;i<j_actuel.get_player_boat().length;i++) { 
			    	panel.majPanel(j_actuel.get_player_boat()[i][0], j_actuel.get_player_boat()[i][1], Content.boat);
			    	
			    } 
				if(j_actuel.getBateauplace()==5) {
					panel.resetPanel();
					autrePanel.resetPanel();
					if(!j2.isIa())changeTour();
					if(j_actuel.getPseudo().equals(j2.getPseudo()) || j2.isIa()) {
						this.etapeJeu=3;
						this.statut=0;
					}	
					changeTour();
					
				}
			}
			break;
		case 1 : //tir
			if(playerSide==false) {
				int reponse;
				boolean valid=false;
				for(int i=0;i<j_actuel.get_player_range().length;i++) { 
					if(j_actuel.get_player_range()[i]!=null && colonne==j_actuel.get_player_range()[i][0] && ligne==j_actuel.get_player_range()[i][1]) {
						valid=true;						 
					}
			    }
				if(valid) {
					if(j_actuel.getPseudo().equals(j1.getPseudo())) {
						reponse=j2.tir_ennemi(colonne, ligne);
						System.out.println("tir sur" + j2.getPseudo() + " : " +reponse);
					}
					else {
						reponse=j1.tir_ennemi(colonne, ligne);
						System.out.println("tir sur" + j1.getPseudo() + " : " +reponse);
					}
					//gestion des touches/rate
					//TODO verifier la range
					if(reponse==0) {
						panel.majPanel(colonne, ligne, Content.miss);
						j_actuel.set_status(colonne, ligne, -1);
						this.statut=3;
					}
					else if(reponse==1){
						panel.majPanel(colonne, ligne, Content.hit);
						j_actuel.set_status(colonne, ligne, 1);
						this.statut=1;
					}
					else {
						panel.majPanel(colonne, ligne, Content.hit);
						j_actuel.set_status(colonne, ligne, 1);
						this.statut=2;
					}
					//Condition de victoire
					if(j1.a_perdu() || j2.a_perdu()) {
						this.etapeJeu=0;
						panelVictoire = new PanelVictoire();
	    				panelVictoire.afficherVictoire(primaryStage, sceneMenu, j_actuel);
					}
					this.etapeJeu=2;
				}
				else this.etapeJeu=1;
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
				this.statut=0;
				majHitMissRange(panel, autrePanel);
				//On place les cases bateau touche
				majBoatHit(autrePanel);
				if(!j_actuel.get_peut_bouger()) {
					this.etapeJeu=1;
				}
				else {
					this.etapeJeu=4;
				}
				
			}
			break;
		case 4 : //deplacement d'un bateau - choix du bateau
			if(playerSide==true) {
				if(j_actuel.select_bateau(colonne, ligne)!=null) {
					this.bateauTemp=j_actuel.select_bateau(colonne, ligne);
					this.etapeJeu=5;
					this.statut=5;
				}
			}
			break;
		case 5 : //deplacement d'un bateau - choix du mouvement
			if(playerSide==true && !j_actuel.check_collision(this.bateauTemp, colonne, ligne) 
				&& ((this.bateauTemp.get_taille()+colonne <= 10 && this.bateauTemp.is_horizontal()) || 
				(bateauTemp.get_taille()+ligne <= 10 && !bateauTemp.is_horizontal()) ) &&
				Math.abs(bateauTemp.get_position()[0]-colonne)+Math.abs(bateauTemp.get_position()[1]-ligne) <= 2) {
				for(int i=0; i<bateauTemp.cases_ocupees().length; i++) {
					panel.majPanel(bateauTemp.cases_ocupees()[i][0], bateauTemp.cases_ocupees()[i][1], Content.sea);
				}
				j_actuel.bouger(this.bateauTemp, colonne, ligne);
				for(int i=0;i<bateauTemp.get_taille();i++) { 
					if (this.bateauTemp.is_hit(i)) {
						panel.majPanel(bateauTemp.cases_ocupees()[i][0], bateauTemp.cases_ocupees()[i][1], Content.boat_hit);//Ne marche pas non plus... �trange.
					} else {
						panel.majPanel(bateauTemp.cases_ocupees()[i][0], bateauTemp.cases_ocupees()[i][1], Content.boat);
					}
			    } 
				//On place les cases bateau touche
				//majBoatHit(panel); // Ne marche pas... bizarre
				//On met � jour la port�e
				autrePanel.resetPanel();
				majHitMissRange(autrePanel, panel);
				this.statut=0;
				this.etapeJeu=1;
			}
			break;
		default :
			break;
		}	
	}
	
	public void clicDroit(PanelJeu panel, int colonne, int ligne, boolean playerSide, PanelJeu autrePanel) {
		switch(this.getEtapeJeu()) {
		case 0:
			if(j_actuel.get_horizontal()==true) {
				j_actuel.set_horizontal(false);
				this.statut=6;
			}
			else{
				j_actuel.set_horizontal(true);
				this.statut=7;
			}
			this.etapeJeu=0;
			break;
		case 1 :
			break;
		case 2 :
			break;
		case 3 :
			break;
		case 4 :
			break;
		default :
			break;
		case 5 :
			this.statut=0;
			this.etapeJeu=4;
			break;
		}
	}
	
	public void changeTour()
	{
		if(!j2.isIa()) {
			if(j_actuel.getPseudo().equals(j1.getPseudo())) {
				j_actuel=j2;
			}
			else {
				j_actuel=j1;
			}
		}
		else
		{
			if(this.etapeJeu==0)
				j2.placer_bateau_ia();
		}
			
		
		
	}
	
	//A appeler uniquement avec le panel de gauche
	public void majBoatHit(PanelJeu panel) {
		System.out.println("hey");
		int[][] caseTouchee;
		for(int i=0; i<5; i++) {
			caseTouchee=j_actuel.cases_touchees(i);
			if(caseTouchee!=null && caseTouchee[0]!=null) {
		        panel.majPanel(caseTouchee[0][0], caseTouchee[0][1], Content.boat_hit);	
				if(caseTouchee[1]!=null) {
			        panel.majPanel(caseTouchee[1][0], caseTouchee[1][1], Content.boat_hit);	
				}
			}
			caseTouchee=null;
		}
	}
	
	//A appeler uniquement avec le panel de droite en premier
	public void majHitMissRange(PanelJeu panel, PanelJeu autrePanel) {
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
		//On place la range
		for(int i=0;i<j_actuel.get_player_boat().length;i++) { 
	        autrePanel.majPanel(j_actuel.get_player_boat()[i][0], j_actuel.get_player_boat()[i][1], Content.boat);
        }
		for(int i=0;i<j_actuel.get_player_range().length;i++) {  
		 	if (j_actuel.get_player_range()[i]!=null) {
				if(tableauDroite[j_actuel.get_player_range()[i][0]][j_actuel.get_player_range()[i][1]]==-1) {	 
					panel.majPanel(j_actuel.get_player_range()[i][0], j_actuel.get_player_range()[i][1], Content.boat_range_and_miss);
				}
				else if(tableauDroite[j_actuel.get_player_range()[i][0]][j_actuel.get_player_range()[i][1]]==1) {	 
					panel.majPanel(j_actuel.get_player_range()[i][0], j_actuel.get_player_range()[i][1], Content.boat_range_and_hit);
				}
				else{
					panel.majPanel(j_actuel.get_player_range()[i][0], j_actuel.get_player_range()[i][1], Content.boat_range); 
				} 
		 	}
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
        texteEtapeJeu.setText(" | Placez vos bateaux \n | clic droit pour changer l'orientation");
        texteEtapeJeu.setTextFill(Color.WHITE);
        texteStatut.setPadding(new Insets(0, 0, 0, 20)); //haut,droit,bas,gauche
        
        texteStatut.setId("statut");
        texteStatut.setText(" | Bateau horizontal");
        texteStatut.setTextFill(Color.WHITE);
        texteStatut.setPadding(new Insets(0, 0, 0, 40)); //haut,droit,bas,gauche
        
	    hbox.getChildren().addAll(texteJoueurAct);
	    hbox.getChildren().addAll(texteEtapeJeu);
	    hbox.getChildren().addAll(texteStatut);

	    return hbox;
	}
	
	public void majHbox() {
		switch(this.getEtapeJeu()) {
		case 0 : //placement bateau debut partie
			texteEtapeJeu.setText(" | Placez vos bateaux \n | clic droit pour changer l'orientation");
			break;
		case 1 : //tir
			texteEtapeJeu.setText(" | Choisissez votre cible");
			break;
		case 2 : //attente apres tir
			texteEtapeJeu.setText(" | Cliquez sur une case a \n | droite pour finir le tour");
			break;
		case 3 : //attente avant de changer de joueur
			texteEtapeJeu.setText(" | Cliquez sur une case a \n | droite pour commencer");
			break;
		case 4 : //choix d'un bateau
			texteEtapeJeu.setText(" | Choisissez un bateau a \n | deplacer");
			break;
		case 5 : //choix du mouvement
			texteEtapeJeu.setText(" | Choisissez sa position \n | 2 cases max");
		default :
			break;
		}
		
		switch(this.getStatut()) {
		case 0 : //En attente
			texteStatut.setText(" | En attente...");
			break;
		case 1 : 
			texteStatut.setText(" | Tir reussi !");
			break;
		case 2 : 
			texteStatut.setText(" | Bateau coule !");
			break;
		case 3 :
			texteStatut.setText(" | Tir rate...");
			break;
		case 4 :
			texteStatut.setText(" | Changement de joueur");
			break;
		case 5 :
			texteStatut.setText(" | Clic droit pour annuler\n | la s�lection");
			break;
		case 6 :
			texteStatut.setText(" | Bateau vertical");
			break;
		case 7 : 
			texteStatut.setText(" | Bateau horizontal");
			break;
		default :
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