public class Boat {

    private int[] position; //Représente la case la plus haut, en vertical ou le plus a gauche en Horizontal.
    private boolean horizontal;// Le bateau est-il horizontal ou vertical.
    private boolean[] cases_touchees;//pour chaque case du bateau, est-elle touchée ou non.
    private int taille; // la taille du bateau.
    private int portee; // La portée de tir du bateau.

    public Boat(int taille, int portee,boolean horizontal, int[] pos ){
      this.taille=taille;
      this.portee=portee;
      this.horizontal=horizontal;
      this.position=pos;
      this.cases_touchees=new boolean[taille];
    }

    public void set_position(int[] position){
       this.position=position;
    }
    public int[] get_position(){
      return position;
    }
    public boolean is_horizontal(){
      return horizontal;
    }
    public void hit(int c){
      if (c<0 && c>taille){
        return;
      } else{
        this.cases_touchees[c]=true;
      }
    }
    public boolean is_hit(int c){
      if (c<0 && c>taille){
        return false;
      } else{
        return cases_touchees[c];
      }
    }
    public int get_taille(){
      return taille;
    }
    public int get_portee(){
      return portee;
    }
    public int[][] cases_ocupees(){
      int[][] cases=new int[taille][2];
      int i=0;
      for (i=0;i<taille;i++){
        if (this.horizontal){
        cases[i][0]=this.position[0]+i;
        cases[i][1]=this.position[1];
      }else {
        cases[i][0]=this.position[0];
        cases[i][1]=this.position[1]+i;
      }
      }
      return cases;
    }
    public int[][] cases_portee(){
      int[][] cases=new int[((2*portee)+1)*taille][2];
      int i=0, j=0;
      for (i=0;i<(2*portee)+1;i++){
        if (this.horizontal){ // BATEAEU HORIZONTAL
          if(this.position[1]-portee+i>0 && this.position[1]-portee+i<10){// CASES VALIDES
            for (j=0;j<taille;j++){
              cases[i*taille+j][0]=this.position[0]+j;
              cases[i*taille+j][1]=this.position[1]-portee+i;
            }
          }else{// CASES NON-VALIDES
            for (j=0;j<taille;j++){
              cases[i*taille+j]=null;
            }
          }
        }else{// BATEAU VERTICAL
          if (this.position[0]-portee+i>0 && this.position[0]-portee+i<10){//CASES VALIDES
            for (j=0;j<taille;j++){
              cases[i*taille+j][0]=this.position[0]-portee+i;
              cases[i*taille+j][1]=this.position[1]+j;
            }
          } else {
            for (j=0;j<taille;j++){
              cases[i*taille+j]=null;
            }
          }
        }
      }
      return cases;
    }

    public boolean doit_couler(){
      int dmg=0,i=0;
      for (i=0;i<taille;i++){
        if (this.cases_touchees[i]){
          dmg++;
        }
      }
      if (dmg==this.taille || dmg==3){
        return true;
      }
      else{
        return false;
      }
    }


}
