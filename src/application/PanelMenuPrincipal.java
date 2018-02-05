package application;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import modele.MenuPrincipal;

//Affichage du menu principal
//Correspond à MenuPrincipal du coté données

public class PanelMenuPrincipal {
    
	private GridPane gridMenu;
	private MenuPrincipal menu;
    
	private Jeu mainJeu;
    
	private Scene scene;

	//OBJETS DU MENU
	private Label titre, j1, j2, vs, mode;
	private VBox joueur1, joueur2;
	private TextField pseudoJ1, pseudoJ2;
	private final Button jcj = new Button(), jce = new Button();
	private Button jouer;
	
	public PanelMenuPrincipal() {
		
	}
	
	public void afficherMenu(Stage primaryStage) {
		this.menu= new MenuPrincipal();
		this.gridMenu = new GridPane();
		
		gridMenu = new GridPane();
		gridMenu.setHgap(20); 
		gridMenu.setVgap(20);
		gridMenu.setAlignment(Pos.CENTER);
		gridMenu.setId("pane");
		
		scene = new Scene(gridMenu, 1040, 650);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		//LIGNE 0 : TITRE
		titre = new Label("BATTLESHIP+");
		titre.setId("titre");
		titre.setContentDisplay(ContentDisplay.CENTER);
		gridMenu.add(titre, 1, 0, 3, 1);
		
		//LIGNE 1 : JOUEURS
			//COLONNE 0 : "J1"
		j1 = new Label("J1");
		j1.setId("j1");
		gridMenu.add(j1, 0, 1);
			//COLONNE 1 : PARAMETRES J1
		joueur1 = new VBox(20);
		pseudoJ1 = new TextField();
		pseudoJ1.setPadding(new Insets(3));
		pseudoJ1.setId("pseudoJ1");
		pseudoJ1.setPrefWidth(150);
		pseudoJ1.textProperty().addListener((event) -> {
			menu.getJ1().setPseudo(pseudoJ1.getText());
			menu.getJ1().verifierPseudo(menu.getJ2());
		});
		joueur1.getChildren().addAll(pseudoJ1);
		joueur1.setAlignment(Pos.CENTER);
		gridMenu.add(joueur1, 1, 1);
			//COLONNE 2 : "VS"
		vs = new Label("VS");
		vs.setId("vs");
		vs.setMinWidth(174);
		vs.setAlignment(Pos.CENTER);
		gridMenu.add(vs, 2, 1);
			//COLONNE 3 : PARAMETRES J2
		joueur2 = new VBox(20);
		pseudoJ2 = new TextField();
		pseudoJ2.setPadding(new Insets(3));
		pseudoJ2.setId("pseudoJ2");
		pseudoJ2.setPrefWidth(150);
		pseudoJ2.textProperty().addListener((event) -> {
			menu.getJ2().setPseudo(pseudoJ2.getText());
			menu.getJ2().verifierPseudo(menu.getJ1());
		});
		joueur2.getChildren().addAll(pseudoJ2);
		joueur2.setAlignment(Pos.CENTER_LEFT);
		gridMenu.add(joueur2, 3, 1);
			//COLONNE 4 : "J2"
		j2 = new Label("J2");
		j2.setId("j2");
		gridMenu.add(j2, 4, 1);
		
		//LIGNE 3 : CHOIX MODE
			//COLONNE 1 : JOUEUR VS JOUEUR
		jcj.setText("JOUEUR VS JOUEUR");
		jcj.setId("jcj");
		jcj.setPrefWidth(150);
		jcj.setOnMouseClicked((event) -> {
				menu.getJ2().setIa(false);
				jcj.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
				jce.setStyle("-fx-effect: none");
		});
		gridMenu.add(jcj, 1, 3);
			//COLONNE 2 : "MODE"
		mode = new Label("MODE");
		mode.setId("mode");
		mode.setMinWidth(174);
		mode.setAlignment(Pos.CENTER);
		gridMenu.add(mode, 2, 3);
			//COLONNE 3 : JOUEUR VS IA
		jce.setText("JOUEUR VS IA");
		jce.setId("jce");
		jce.setPrefWidth(150);
		jce.setOnMouseClicked((event) -> {
			menu.getJ2().setIa(true);
			jcj.setStyle("-fx-effect: none");
			jce.setStyle("-fx-effect: dropshadow(three-pass-box, #16BEEB, 20, 0.5, 0, 0)");
		});
		gridMenu.add(jce, 3, 3);
		
		//LIGNE 6 : JOUER
		jouer = new Button("JOUER");
		jouer.setId("jouer");
		jouer.setPrefWidth(174);
		jouer.setOnMouseClicked((event) -> {
			//LANCEMENT JEU
			mainJeu = new Jeu(menu.getJ1(), menu.getJ2(), scene);
			mainJeu.jouer(primaryStage);
		});
		gridMenu.add(jouer, 2, 7);
		
		primaryStage.setScene(scene);
	}
}
