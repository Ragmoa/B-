import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;

public class Plateau extends Application {
	
	
	public Rectangle sea_p1 = new Rectangle();
	public Rectangle sea_p2 = new Rectangle();
	Rectangle[] boat = new Rectangle[17];
	public static final int SCENE_SIZE = 800;
	public Case[][] plateau1;
	public Case[][] plateau2;
	Path path = new Path();
	int a=0,b=0, k=0, c=0, stop=0;
	
	int size = 1200, board = 500;
	
	
	public static void main(String[] args) { launch(args); }
	public Plateau() {
		plateau1 = new Case[10][10];
		plateau2 = new Case[10][10];
				 		
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				plateau1[j][i] = new Case(Content.sea);
				plateau2[j][i] = new Case(Content.sea);
			}
		}
	}

    public void start(Stage stage) {
	        Group root = new Group();
	        
	        for(int j=0;j<17;j++)
		    {
		    	boat[j] = new Rectangle(board/10,board/10);
		    	boat[j].setFill(Color.GREEN);		    	
		    }
	        
	        //Plateau joueur actif
	        Scene scene = new Scene(root, size, size/2);
	        sea_p1.setWidth(board);
			sea_p1.setHeight(board);
			sea_p1.setFill(Color.BLUE);
			root.getChildren().add(sea_p1);
			
			//Plateau joueur adverse
			sea_p2.setX((size-board));
			sea_p2.setWidth(board);
			sea_p2.setHeight(board);
			sea_p2.setFill(Color.BLUE);
			root.getChildren().add(sea_p2);
			
	        //draw rooms
			for (int i = 0; i <= 10; i++) {
				path.getElements().add(new MoveTo(-board/10, (board/10 * i)));
				path.getElements().add(new HLineTo(board));
				path.getElements().add(new MoveTo((board/10 * i), -board/10));
				path.getElements().add(new VLineTo(board));
			}
			
			for (int i = 0; i <= 10; i++) {
				path.getElements().add(new MoveTo((size-board), (board/10 * i)));
				path.getElements().add(new HLineTo(size));
				path.getElements().add(new MoveTo((board/10 * i) + (size-board), -board/10));
				path.getElements().add(new VLineTo(board));
			}
			root.getChildren().add(path);
			
			plateau1[2][5].setTypeC(Content.boat);
			plateau1[2][6].setTypeC(Content.boat);
			plateau1[2][7].setTypeC(Content.boat_hit);
			plateau1[3][7].setTypeC(Content.boat_range);
			plateau1[5][5].setTypeC(Content.miss);
			
			
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
					if(plateau1[i][j].getTypeC()==Content.boat) {
						System.out.println("bla");
						boat[k].setX(board/10 * i);
						boat[k].setY(board/10* j);
						boat[k].setFill(Color.GREY);
						root.getChildren().add(boat[k]);
						//root.getChildren().remove(boat[k]);
						k++;
					}
					
					else if(plateau1[i][j].getTypeC()==Content.boat_hit){
						
						boat[k].setX(board/10 * i);
						boat[k].setY(board/10* j);
					 	boat[k].setWidth(board/10);
						boat[k].setHeight(board/10);
						boat[k].setFill(Color.GREY);
						boat[k].setStroke(Color.RED);
						root.getChildren().add(boat[k]);
						//root.getChildren().remove(boat[k]);
						k++;
					}
					else if(plateau1[i][j].getTypeC()==Content.boat_range) {
						Circle c1 = new Circle(board/20+board/10*i, board/20+board/10*j, board/25);
						c1.setFill(Color.GOLD);
						root.getChildren().add(c1);
					}
					else if(plateau1[i][j].getTypeC()==Content.miss);
					else;													
				}
			}
			
			plateau2[5][5].setTypeC(Content.hit);
			plateau2[6][5].setTypeC(Content.miss);
			for (int i = 0; i <= 9; i++) {
				for (int j = 0; j <= 9; j++) {
					if(plateau2[i][j].getTypeC()==Content.miss) {
						Circle c2 = new Circle(board/20+board/10*i + (size-board), board/20+board/10*j, board/25);
						c2.setFill(Color.BLUE);
						c2.setStroke(Color.RED);
						root.getChildren().add(c2);
						
					}
					else if(plateau2[i][j].getTypeC()==Content.hit) {
						Line line1 = new Line(10 + board/10*i + (size-board), 10 + board/10*j, board/10*(i+1) -10 + (size-board), board/10*(j+1) - 10);
						line1.setStroke(Color.RED);
						Line line2 = new Line(board/10*(i+1) -10 + (size-board), board/10*j +10, board/10*i + 10 + (size-board), board/10*(j+1)-10);
						line2.setStroke(Color.RED);					
						root.getChildren().add(line1);
						root.getChildren().add(line2);
					}
					
				}
			}

	        stage.setTitle("My JavaFX Application");
	        stage.setScene(scene);
	        stage.show();
    }	
}
