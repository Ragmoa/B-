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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
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
    int form;
    
    public Carre(Boolean player, Content contenu, int colonne, int ligne){
    	this.playerSide=player;
    	this.ligne=ligne;
    	this.colonne=colonne;
    	this.form=1;
    	
        Rectangle fondCarre = new Rectangle(0,0,50,50);
        getChildren().add(fondCarre);     
        majCarre(contenu);
    }
    
    public void majCarre(Content contenu)
    {
    	Rectangle fondCarre = new Rectangle(0,0,50,50);
		fondCarre.setFill(Color.LIGHTBLUE);
		fondCarre.setStroke(Color.BLACK);
		
//		for(int i=0; i<this.getForm(); i++)
//		{
//			System.out.println(i);
//			this.getChildren().remove(i);
//		}
		this.getChildren().removeAll();
      
		switch(contenu) {
 		case boat:
 			fondCarre.setFill(Color.WHITE);
 			setForm(1);
 			this.getChildren().add(fondCarre); 
 			break;
 		case boat_hit:
 			fondCarre.setFill(Color.GREY);
 			setForm(1);
 			this.getChildren().add(fondCarre); 
 			break;
 		case boat_range:
 			setForm(1);
 			fondCarre.setStroke(Color.RED);
 			this.setOnMouseEntered(new EventHandler<MouseEvent>(){
 	            public void handle(MouseEvent me){
 	                fondCarre.setFill(Color.GREY);
 	                fondCarre.setStroke(Color.RED);
 	            }
 	        });

 	        this.setOnMouseExited(new EventHandler<MouseEvent>(){
 	            public void handle(MouseEvent me){
 	                fondCarre.setFill(Color.LIGHTBLUE);
 	                fondCarre.setStroke(Color.RED);
 	            }
 	        }); 
 	        this.getChildren().add(fondCarre); 
 			break;
 		case hit:
 			this.setForm(2);
 			Circle line1 = new Circle(25, 25, 15);
 		    line1.setStroke(Color.BLACK);	
 		    this.getChildren().add(fondCarre); 
 		    this.getChildren().add(line1);
 			break;
 		case miss:
 			this.getChildren().add(fondCarre); 
 			setForm(1);
 			break;
 		case sea:
 			setForm(1);
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
 	        this.getChildren().add(fondCarre); 
 			break;
 		default:
 			break;
        }
		
		//this.getChildren().add(fondCarre); 
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
    
    public int getForm() {
    	return this.form;
    }
    
    public void setForm(int form) {
    	this.form=form;
    }
}   
    

