/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import modele.Case;

/**
 *
 * @author Leroy
 */

//Correspond � un carr� dans le quadrillage 10*10
//Sert � l'affichage et gestion des clics
//Correspond � une Case du cot� des donn�es 

public class Carre extends Parent{
    private Case caseO;
    
    public Carre(Case azerty){
        Rectangle fondCarre = new Rectangle(0,0,50,50);
        Circle pion = new Circle(25,25,15,Color.WHITE);
        pion.setFill(Color.WHITE);
        pion.setStroke(Color.WHITE);
        
        //chaque carr� est li� � une case du plateau
        caseO=azerty;

        fondCarre.setFill(Color.WHITE);
        fondCarre.setStroke(Color.BLACK);
        getChildren().add(fondCarre);
        getChildren().add(pion);
        
        //on d�finit son comportement par rapport a la souris

        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondCarre.setFill(Color.GREY);
                fondCarre.setStroke(Color.BLACK);
                pion.setFill(Color.GREY);
                pion.setStroke(Color.GREY);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondCarre.setFill(Color.WHITE);
                fondCarre.setStroke(Color.BLACK);
                pion.setFill(Color.WHITE);
                pion.setStroke(Color.WHITE);
            }
        });   
        
        this.setOnMousePressed(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){                  
                fondCarre.setFill(Color.GREY);
                fondCarre.setHeight(47);
                fondCarre.setWidth(47);
                fondCarre.setStroke(Color.WHITE);
                fondCarre.setStrokeWidth(2);
                pion.setFill(Color.GREY);
                pion.setStroke(Color.GREY);
                System.out.println(caseO.getTypeC());
                System.out.println(caseO.getColonne() + " " + caseO.getLigne());
            }
        });
        
        this.setOnMouseReleased(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent me){
                fondCarre.setFill(Color.WHITE);
                fondCarre.setStroke(Color.BLACK);
                fondCarre.setHeight(50);
                fondCarre.setWidth(50); 
                fondCarre.setStrokeWidth(1);
                pion.setFill(Color.WHITE);
                pion.setStroke(Color.WHITE);
            }
        });
            
    }
    
    public Case getCase(){
        return caseO;
    }

}   
    

