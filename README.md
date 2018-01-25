# B+ : Un Battleship +  en Java

*Par Nicolas Leroy, Francis Manzanilla & Guillaume Ryckaert*

Développé dans le cadre du cours 8INF957 (Programmation Orientée Objet) de l'UQAC.

### Classes

 - [ ] Bateau
      - Case Haut-Gauche
      - Horizontal/Vertical
      - Liste de cases touchées
      - Portée
      - Taille
      - *Constructeur*
      - *Cases occupées*
      - *Cases a portée*


- [ ] Joueur (Abstraite)
    - Liste de Bateaux
    - Tableau de cases touchées/ratées
    - Peut se déplacer a ce tour-ci
    - Est une IA
    - Dernière case jouée par l'adversaire.
    - *Constructeur*
    - *Tir*
    - *Déplacer*

  - [ ] Joueur_humain (Hérite de Joueur)
      - *Constructeur*
      - *Liste de cases vaildes pour le déplacement du bateau*
      - *Listes de cases valides pour le tir*

  - [ ] Joueur_IA (Hérite de Joueur)
      - *Jouer en temps qu'IA*


 - [ ] Main
      - Joueurs

### Mécaniques
- Il faut toucher un bateau sur 2 cases différentes pour le couler.
- Si on retouche un bateau a une case detruite, le joueur ne peut pas déplacer ses bateaux.
