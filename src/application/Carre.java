/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import modele.Content;

/**
 *
 * @author Leroy
 */

//Correspond à un carré dans le quadrillage 10*10
//Sert à l'affichage et gestion des clics

public class Carre extends Parent{
    private Boolean playerSide; //1=appartient au plateau avec les bateaux du joueur, 0=appartient au plateau avec les tirs vers l'adversaire
    int ligne;
    int colonne;    
    
    public Carre(Boolean player, Content contenu, int colonne, int ligne){
    	this.playerSide=player;
    	this.ligne=ligne;
    	this.colonne=colonne;
    	
        Rectangle fondCarre = new Rectangle(0,0,50,50);
        fondCarre.setFill(Color.WHITE);
        fondCarre.setStroke(Color.BLACK);
        getChildren().add(fondCarre);
        
        //on définit son comportement par rapport a la souris
        switch(contenu) {
		case boat:
			fondCarre.setFill(Color.WHITE);
			break;
		case boat_hit:
			fondCarre.setFill(Color.BLACK);
			break;
		case boat_range:
			fondCarre.setFill(Color.RED);
			break;
		case hit:
			break;
		case miss:
			break;
		case sea:
			fondCarre.setFill(Color.LIGHTBLUE);
			this.setOnMouseEntered(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	                fondCarre.setFill(Color.GREY);
	                fondCarre.setStroke(Color.BLACK);
	            }
	        });

	        this.setOnMouseExited(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	                fondCarre.setFill(Color.LIGHTBLUE);
	                fondCarre.setStroke(Color.BLACK);
	            }
	        }); 
			break;
		default:
			this.setOnMouseEntered(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	                fondCarre.setFill(Color.GREY);
	                fondCarre.setStroke(Color.BLACK);
	            }
	        });

	        this.setOnMouseExited(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	                fondCarre.setFill(Color.WHITE);
	                fondCarre.setStroke(Color.BLACK);
	            }
	        });
	        
	        this.setOnMousePressed(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){                  
	                fondCarre.setFill(Color.GREY);
	                fondCarre.setHeight(47);
	                fondCarre.setWidth(47);
	                fondCarre.setStroke(Color.WHITE);
	                fondCarre.setStrokeWidth(2);
	            }
	        });
	        
	        this.setOnMouseReleased(new EventHandler<MouseEvent>(){
	            public void handle(MouseEvent me){
	                fondCarre.setFill(Color.WHITE);
	                fondCarre.setStroke(Color.BLACK);
	                fondCarre.setHeight(50);
	                fondCarre.setWidth(50); 
	                fondCarre.setStrokeWidth(1);
	            }
	        });
			break;
        }
    }
    
    public void majCarre(Content contenu)
    {
//    	//on change l'affichage et le comportement en fonction du (nouveau) contenu de sa case
//        Rectangle fondCarre = new Rectangle(0,0,84,84);
//        fondCarre.setFill(Color.WHITE);
//        fondCarre.setStroke(Color.BLACK);
//        if(caseO.isaJoueur()==true)
//        {
//            Circle pion = new Circle();
//            pion.setCenterX(42);
//            pion.setCenterY(42);
//            pion.setRadius(30);
//            
//            pion.setFill(caseO.getJoueur().getCouleur());
//            pion.setStroke(Color.BLACK);
//            pion.setEffect(new DropShadow(10, Color.BLACK));
//      
//            this.setOnMouseEntered(new EventHandler<MouseEvent>(){
//                public void handle(MouseEvent me){
//                    fondCarre.setFill(Color.WHITE);
//                    fondCarre.setStroke(Color.BLACK);
//
//                }
//            });
//            //on enlève les anciens fonds et pions et on ajoute les nouveaux
//            this.getChildren().remove(1);
//            this.getChildren().remove(0);
//            this.getChildren().add(fondCarre);          
//            this.getChildren().add(pion);   
//        }
    }
    
    public int getColonne() {
		return colonne;
	}
    
	public int getLigne() {
		return ligne;
	}
    
    public Boolean getPlayerSide() {
    	return playerSide;
    }

    
}   
    

