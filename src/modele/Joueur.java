package modele; 
 
import application.Boat; 
 
public class Joueur { 
 
  private String pseudo; 
  private boolean ia; 
  private Boat[] bateaux; 
  private boolean peut_bouger;
  private int[] coup_adversaire;
  private int bateauplace;
  
 
  public Joueur(String pseudo, boolean ia) { 
    this.pseudo = pseudo; 
    this.ia = ia;
    bateaux = new Boat[5];
    this.peut_bouger=true;
    this.bateauplace = 0;
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
	   Boat b = new Boat(5,2,true,pos); 
    		   if(i==0 && (b.get_taille()+x < 10 && b.is_horizontal()) || (b.get_taille()+y < 10 && !b.is_horizontal() )) {	   
    			   bateaux[0]=new Boat(5,2,true,pos);
    			   i++;
    		   }
    		   else if(i==1)b=new Boat(4,2,true,pos);
    		   else if(i==2)b=new Boat(3,2,true,pos);
    		   else if(i==3)b=new Boat(3,4,true,pos);
    		   else if(i==4)b=new Boat(2,5,true,pos);
    		   if(i!=0 && !check_collision(b) && (b.get_taille()+x < 10 && b.is_horizontal()) || (b.get_taille()+y < 10 && !b.is_horizontal() ) ){
    			   System.out.println("allo");
    			   if(i==1)bateaux[1]=new Boat(4,2,true,pos);
    			   else if(i==2)bateaux[2]=new Boat(3,2,true,pos);
    			   else if(i==3)bateaux[3]=new Boat(3,4,true,pos);
    			   else if(i==4)bateaux[4]=new Boat(2,5,true,pos);
    			   i++;
    		   }
    		   return i;
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
        	return 0; // DANS L'EAU!
        }
        
} 