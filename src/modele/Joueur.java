package modele; 
 
import java.util.Random;

import application.Boat; 
 
public class Joueur { 
 
  private String pseudo; 
  private boolean ia; 
  private Boat[] bateaux; 
  private boolean peut_bouger;
  private int[] coup_adversaire;
  private int bateauplace;
  private int[][] status; //0=rien, -1=rate, 1=touche
  private int[][] cases_joueur;
  private int[][] cases_portee;
  private boolean horizontal;
  
 
  public Joueur(String pseudo, boolean ia) {
	  System.out.println("new player");
    int i,j=0;
	this.pseudo = pseudo; 
    this.ia = ia;
    bateaux = new Boat[5];
    this.peut_bouger=true;
    this.bateauplace = 0;
    cases_joueur = new int[17][2];
    this.status=new int[10][10];
    for (i=0;i<10;i++) {
    	for (j=0;j<10;j++) {
    		this.status[i][j]=0;
    	}
    }
    this.horizontal = true;
  }

  public int[][] get_status(){
	  return this.status;
  }
  
  public void set_status( int x, int y, int st) {
	  this.status[x][y]=st;
  }
  public boolean get_horizontal(){
	  return this.horizontal;
  }
  
  public void set_horizontal(boolean horizontal) {
	  this.horizontal=horizontal;
  }
  
  public String getPseudo() { 
    return pseudo; 
  } 
 
  public void setPseudo(String pseudo) { 
    this.pseudo = pseudo; 
  } 
  
  public boolean get_peut_bouger() {
	  return peut_bouger;
  }
  
  public void set_peut_bouger( boolean p) {
	  this.peut_bouger=p;
  }
  
  public int [] get_coup_adversaire () {
	  return coup_adversaire;
  }
  public boolean isIa() { 
    return ia; 
  } 
 
  public void setIa(boolean ia) { 
    this.ia = ia; 
  }
  public Boat[] getBateau() { 
	    return bateaux; 
	  }
  
  public int getBateauplace() { 
	    return bateauplace; 
	  }
  public int[][] getCases_joueur() { 
	    return cases_joueur; 
	  }
  public void setCases_joueur(int[][]cases_joueur) { 
	    this.cases_joueur=cases_joueur; 
	  }
  
  public int[][] getCases_portee() { 
	    return cases_portee; 
	  }
public void setCases_portee(int[][]cases_portee) { 
	    this.cases_joueur=cases_portee; 
	  }
  public void setBateauplace(int bateauplace) {
	 this.bateauplace = bateauplace;
}
 
  public String toString() { 
    return pseudo; 
  } 
  //EVITE QUE LES DEUX JOUEURS AIENT LE MEME PSEUDO 
  public void verifierPseudo(Joueur joueur) { 
    if(pseudo.length() == 0) 
      pseudo = "Anonymous"; 
    if(pseudo.length() > 12) 
      pseudo = pseudo.substring(0, 12); 
    if(pseudo.equals(joueur.getPseudo())) { 
      if(joueur.getPseudo().equals("Copieur")) 
        pseudo = "Copiteur"; 
      else 
        pseudo = "Copieur"; 
    } 
  }
  

 
   public int placer_bateau(int x , int y, int i, boolean horizontal){
	   
	   int pos[] = {x,y};
	   Boat b = new Boat(5,2,horizontal,pos); 
    		   if(i==0 && ((b.get_taille()+x <= 10 && b.is_horizontal()) || (b.get_taille()+y <= 10 && !b.is_horizontal() ))){	   
    			   bateaux[0]=new Boat(5,2,horizontal,pos);
    			   i++;
    		   }
    		   else if(i==1)b=new Boat(4,2,horizontal,pos);
    		   else if(i==2)b=new Boat(3,2,horizontal,pos);
    		   else if(i==3)b=new Boat(3,4,horizontal,pos);
    		   else if(i==4)b=new Boat(2,5,horizontal,pos);
    		   
    		   if(i!=0 && !check_collision(b) && ((b.get_taille()+x <= 10 && b.is_horizontal()) || (b.get_taille()+y <= 10 && !b.is_horizontal() ) )){
    			   
    			   if(i==1)bateaux[1]=new Boat(4,2,horizontal,pos);
    			   else if(i==2)bateaux[2]=new Boat(3,2,horizontal,pos);
    			   else if(i==3)bateaux[3]=new Boat(3,4,horizontal,pos);
    			   else if(i==4)bateaux[4]=new Boat(2,5,horizontal,pos);
    			   i++;
    		   }
    		   
    		   return i;
    		 }
   public void placer_bateau_ia() {
	   int i=0, x=0, y=0;
	   int pos[] = new int[2];
	   Boat b = new Boat(5,2,horizontal,pos);
	   do {
		   pos = new int[2];
		 //System.out.print("new boucle");
		  // System.out.println( i);
		   Random r = new Random();
		   x = 0 + r.nextInt(9 - 0);
		   y = 0 + r.nextInt(9 - 0);
		   pos[0]=x;
		   pos[1]=y;
		   
    		   if(i==0 && ((b.get_taille()+x <= 10 && b.is_horizontal()) || (b.get_taille()+y <= 10 && !b.is_horizontal() ))){	   
    			   bateaux[0]=new Boat(5,2,horizontal,pos);
    			   //System.out.println(x+" "+y + " " + i);
    		   }
    		   else if(i==1)b=new Boat(4,2,horizontal,pos);
    		   else if(i==2)b=new Boat(3,2,horizontal,pos);
    		   else if(i==3)b=new Boat(3,4,horizontal,pos);
    		   else if(i==4)b=new Boat(2,5,horizontal,pos);
    		   System.out.println(pos[0]+" "+pos[1] + " " + i);
    		   
    		   if(i!=0 && !check_collision(b) && ((b.get_taille()+x <= 10 && b.is_horizontal()) || (b.get_taille()+y <= 10 && !b.is_horizontal() ) )){
    			   //System.out.println(x+" "+y + " " + i);
    			   if(i==1)bateaux[1]=new Boat(4,2,horizontal,pos);
    			   else if(i==2)bateaux[2]=new Boat(3,2,horizontal,pos);
    			   else if(i==3)bateaux[3]=new Boat(3,4,horizontal,pos);
    			   else if(i==4)bateaux[4]=new Boat(2,5,horizontal,pos);
    			   i++;
    		   }
    		   else if(i==0 && bateaux[0]!=null)
    			   i++;
	   }while(i<5);	   
   }
   
   public int tir_ia() {
   	int i=0,j=0;
   	int x=0, y=0;
   	Random r = new Random();
	   x = 0 + r.nextInt(9 - 0);
	   y = 0 + r.nextInt(9 - 0);
   	for (i=0;i<5;i++) {
   		if (this.bateaux[i]!=null) {
   			for (j=0;j<this.bateaux[i].get_taille();j++) {
   				if ( x==this.bateaux[i].cases_ocupees()[j][0] && y==this.bateaux[i].cases_ocupees()[j][1] ) {
   					this.bateaux[i].hit(j);
   					this.peut_bouger=false;
   					if (this.bateaux[i].doit_couler()) {
   						this.bateaux[i]=null;
   						return 2;//COULÉ!
   					} else {
   						return 1; //TOUCHÉ!
   					}
   				}
   		}
   	}
   	}
   	return 0; // DANS L'EAU!
   }
   public Boat select_bateau() {//TODO: D�placer dans Joueur_humain 
       int i=0, j=0;
       int x=0, y=0;      
       Random r = new Random();      
	   x = 0 + r.nextInt(9 - 0);
	   y = 0 + r.nextInt(9 - 0);
         for (i=0;i<5;i++){ 
           for (j=0;j<this.bateaux[i].get_taille();j++){ 
             if (x==this.bateaux[i].cases_ocupees()[j][0] && y==this.bateaux[i].cases_ocupees()[j][1]){ 
               return this.bateaux[i]; 
             } 
           } 
         }    
         return null; 
     }
   
   public void bouger_ia(Boat b){
	   int x=0, y=0;
	   int pos[] = {x,y};
       Random r = new Random();      
	   x = 0 + r.nextInt(9 - 0);
	   y = 0 + r.nextInt(9 - 0);      
       b.set_position(pos); 
       return ; 
     }
 
      public boolean check_collision (Boat b){ 
        int i=0, j=0,k=0; 
        for (i=0;i<5;i++){ 
          if (this.bateaux[i]!=null){ 
            for (j=0;j<this.bateaux[i].get_taille();j++){ 
              for (k=0;k<b.get_taille();k++){ 
                if (b.cases_ocupees()[k][0]==this.bateaux[i].cases_ocupees()[j][0] && b.cases_ocupees()[k][1]==this.bateaux[i].cases_ocupees()[j][1]){
                  return true; 
                } 
              } 
            } 
          } 
        } 
        return false; 
      } 
 
      public boolean check_collision (Boat b, int x, int y){ 
          int ox=b.get_position()[0]; 
          int oy=b.get_position()[1]; 
          int i=0, j=0,k=0; 
          bouger(b,x,y); 
          for (i=0;i<5;i++){ 
            if (this.bateaux[i]!=null && this.bateaux[i].get_taille()!=b.get_taille() && this.bateaux[i].get_portee()!=b.get_portee()){ 
              for (j=0;j<this.bateaux[i].get_taille();j++){ 
                for (k=0;k<b.get_taille();k++){ 
                  if (b.cases_ocupees()[k][0]==this.bateaux[i].cases_ocupees()[j][0] && b.cases_ocupees()[k][1]==this.bateaux[i].cases_ocupees()[j][1]){ 
                    bouger(b,ox,oy); 
                    return true; 
                  } 
                } 
              } 
            } 
          } 
          bouger(b,ox,oy);// 
          return false;
      }
          
      public Boat select_bateau(int x, int y) {//TODO: D�placer dans Joueur_humain 
          int i=0, j=0; 
            for (i=0;i<5;i++){ 
              for (j=0;j<this.bateaux[i].get_taille();j++){ 
                if (x==this.bateaux[i].cases_ocupees()[j][0] && y==this.bateaux[i].cases_ocupees()[j][1]){ 
                  return this.bateaux[i]; 
                } 
              } 
            } 
            return null; 
        } 
 
        public void bouger(Boat b, int x, int y){ 
          int[] npos=new int[2]; 
          npos[0]=x; 
          npos[1]=y; 
          b.set_position(npos); 
          return ; 
        }
        
        
        public int tir_ennemi(int x, int y) {
        	int i=0,j=0;
        	for (i=0;i<5;i++) {
        		if (this.bateaux[i]!=null) {
        			for (j=0;j<this.bateaux[i].get_taille();j++) {
        				if ( x==this.bateaux[i].cases_ocupees()[j][0] && y==this.bateaux[i].cases_ocupees()[j][1] ) {
        					this.bateaux[i].hit(j);
        					this.peut_bouger=false;
        					if (this.bateaux[i].doit_couler()) {
        						this.bateaux[i]=null;
        						return 2;//COULÉ!
        					} else {
        						return 1; //TOUCHÉ!
        					}
        				}
        		}
        	}
        	}
        	this.peut_bouger=true;
        	return 0; // DANS L'EAU!
        }
        public int[][] get_player_boat(){
     	
		 	int taille=0;
		 	for(int i=0;i<this.getBateauplace();i++)
		 	{    
		 		if(bateaux[i]!=null) {
		 			taille+=bateaux[i].get_taille();
		 		}	 		
		 	}
		 	int k=0;
		 	int[][]cases = new int[taille][2];
		 	
		 	for(int i=0;i<this.bateauplace;i++) {
		 		if(bateaux[i]!=null) {
		 			for(int j=0;j<bateaux[i].cases_ocupees().length;j++) {
			 			cases[k][0]=bateaux[i].cases_ocupees()[j][0];
			 			cases[k][1]=bateaux[i].cases_ocupees()[j][1];
			 			k++;
			 		}
		 		}
		 	}
		 	return cases;
        }
        
        public boolean a_perdu() {
        	if (bateaux[0]==null && bateaux[1]==null && bateaux[2]==null && bateaux[3]==null && bateaux[4]==null) {
        		return true;
        	}
        	return false;
        }
        
        public int [][] cases_touchees(int number){// Si le bateau est détruit, retourne null.
        	int [][] res= new int[2][2];
        	int i=0,j=0;
        	if (this.bateaux[number]==null) {
        		res=null;

        		return res;
        	}
        	for (i=0;i<this.bateaux[number].get_taille();i++) {
        		if (this.bateaux[number].is_hit(i)) {
        			res[j][0]=this.bateaux[number].cases_ocupees()[i][0];
        			res[j][1]=this.bateaux[number].cases_ocupees()[i][1];
        			j++;
        		}
        	}
        	if (j<2) {
        		res[j]=null;
        	}
        	return res;
        }
        
        public int[][] get_player_range(){
        	//System.out.println("-----");
        	int taille=0;
         	for(int i=0;i<5;i++)
         	{   
         		if(bateaux[i]!=null) {
         			taille+=bateaux[i].cases_portee().length;
         		}	
         	}
         	int k=0;      	
         	int[][]cases = new int[taille][2];
         	//System.out.println(bateaux[0].cases_portee()[0][0]);
         	//System.out.println(bateaux[0].cases_portee()[0][1]);
         	
         	for(int i=0;i<5;i++) {
         		if(bateaux[i]!=null) {
	         		for(int j=0;j<bateaux[i].cases_portee().length;j++) {
	         			if(bateaux[i].cases_portee()[j]==null) {
	         				cases[k]=null;
	         				k++;
	         			}
	         			else
	         			{
	         				cases[k][0]=bateaux[i].cases_portee()[j][0];
	         				cases[k][1]=bateaux[i].cases_portee()[j][1];
		         			/*System.out.print(cases[k][0]);
		         			System.out.print(" , ");
		         			System.out.println(cases[k][1]);*/
	         				k++;
		         		}
		         	}
         		}
         	}
        return cases;    
         	}
} 