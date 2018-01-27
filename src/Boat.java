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

}
