package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	private PanelMenuPrincipal panelMenuPrincipal = new PanelMenuPrincipal();
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Battleship+ (un battleship avec un peu de plus)");
	        primaryStage.setResizable(false);
			
	        panelMenuPrincipal.afficherMenu(primaryStage);
	        
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
