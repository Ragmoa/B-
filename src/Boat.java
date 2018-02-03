public class Boat {

    private int[] position; //Représente la case la plus haut, en vertical ou le plus a gauche en Horizontal.
    private boolean horizontal;// Le bateau est-il horizontal ou vertical.
    private boolean[] cases_touchees;//pour chaque case du bateau, est-elle touchÃ©e ou non.
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

}
