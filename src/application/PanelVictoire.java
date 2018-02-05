package application;

import java.sql.*;
import java.util.Calendar;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import modele.Joueur;

public class PanelVictoire {

	private GridPane gridVictoire;
	private Scene scene;
	
	private Label victoire, gagnant;
	private Button menuPrincipal;
	
	public PanelVictoire() {
		
	}
	
	public void afficherVictoire(Stage primaryStage, Scene sceneMenu, Joueur vainqueur) {
		gridVictoire = new GridPane();
		gridVictoire.setHgap(20); 
		gridVictoire.setVgap(20);
		gridVictoire.setAlignment(Pos.CENTER);
		gridVictoire.setId("pane");
		
		scene = new Scene(gridVictoire, 1000, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		//AFFICHE LE GAGNANT
		HBox boxVictoire = new HBox(20);
		victoire = new Label("Victoire de");
		victoire.setId("victoire");
		boxVictoire.getChildren().add(victoire);
		gagnant = new Label(vainqueur.getPseudo());
		gagnant.setId("gagnant");
		boxVictoire.getChildren().add(gagnant);
		gridVictoire.add(boxVictoire, 0, 0);
		
//		HBox boxMenu = new HBox(20);
//		menuPrincipal = new Button("MENU PRINCIPAL");
//		menuPrincipal.setId("menuPrincipal");
//		menuPrincipal.setPrefWidth(300);
//		menuPrincipal.setOnMouseClicked((event) -> {
//			primaryStage.setScene(sceneMenu);
//		});
//		boxMenu.getChildren().add(menuPrincipal);
//		boxMenu.setAlignment(Pos.CENTER);
//		gridVictoire.add(boxMenu, 0, 4);

		primaryStage.setScene(scene);
	}
}
