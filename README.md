# B+ : Un Battleship +  en Java

*Par Nicolas Leroy, Francis Manzanilla & Guillaume Ryckaert*

Développé dans le cadre du cours 8INF957 (Programmation Orientée Objet) de l'UQAC.

### Classes

 - [X] ~~Bateau~~
      - ~~Case Haut-Gauche~~
      - ~~Horizontal/Vertical~~
      - ~~Liste de cases touchées~~
      - ~~**Portée**~~
      - ~~**Taille**~~
      - ~~*Constructeur*~~
      - ~~*Cases occupées*~~
      - ~~*Cases a portée*~~
      - ~~*Doit être coulé*~~

- [ ] Joueur (Abstraite)
    - ~~Liste de Bateaux~~
    - Tableau de cases touchées/ratées
    - ~~Peut se déplacer a ce tour-ci~~
    - ~~Dernière case jouée par l'adversaire~~
    - *Tir*
    - ~~*Déplacer*~~
    - *Liste de cases valides pour le déplacement d'un bateau*
    - ~~Identifier et détruire les bateaux coulés*~~
    - ~~*Vérifier la case jouée par l'adversaire*~~
    - ~~*Infliger des dégats a mon bateau*~~


 - [ ] Joueur_humain (Hérite de Joueur)
    - *Constructeur*
    - *Liste de cases valides pour le tir*


  - [ ] Joueur_IA (Hérite de Joueur)
    - *Constructeur*
    - *Jouer en temps qu'IA*


 - [ ] Main
      - Joueurs
      - JvJ ou JvIA
      - *Boucle de jeu*  

### Mécaniques
- Il faut toucher un bateau sur des cases différentes pour lui infliger des dégats.
- Si on retouche un bateau a une case detruite, le joueur ne peut pas déplacer ses bateaux.
- A plus de 2 cases détruites, le bateau est coulé.
