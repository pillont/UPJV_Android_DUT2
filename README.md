# Cours DUT 2

### Support de cours :
- [Design](https://docs.google.com/presentation/d/1zt4zRvPfy6_QH8aSdvYgU82hZkRk2liBjA2x1GcRowA/edit?usp=sharing)
- [Interaction](https://docs.google.com/presentation/d/19oiYGQWtXJntZxBs1ZP2uOd6nFYyqGNV5BlFGEJyLQk/edit?usp=sharing)
- [Base de donnée](https://docs.google.com/presentation/d/1K8YE3VQ5abDGTSfirsM5c6iIHFRiz31_owFWuoaXfNM/edit?usp=sharing)
- [Architecture](https://docs.google.com/presentation/d/1rMoGohrW3HSo2BfWyW0_wNfOc8-I6537ZBZT3v8veL0/edit?usp=sharing)

----------------------------

### Visio :

https://meet.google.com/hmo-ytnq-qgq

----------------------------

### Steps TD :

*Groupe 1*

- https://github.com/pillont/2022_Group1

*Groupe 2*

- https://github.com/pillont/2022_Group2


-------------------------


## Projet : 

#### Visuel 

1. une page de menu
    * un bouton **pour aller au jeux**
    * un bouton **pour aller au score**
    
2. une page de jeux
    * un texte qui affiche l opération
    * une zone de texte **(qui prend uniquement des nombres)** pour la réponse
    * un texte qui indique le succès **(en vert)**
    * un texte qui indique l'échec **(en rouge)**
    * un bouton **pour au main**
    * * un bouton **pour valider le resultat**

2. menu de la page de jeu
    * bouton **pour passer à la page des scores**
    * bouton **pour effacer les scores**

3. une page de scores : 
    * un texte qui affiche **le nombre d'opérations**
    * un texte qui affiche **le pourcentage de réussite**
    * un texte qui affiche **la dernière opération avec le bon résultat**
    * **un bouton pour retourner à la page précédente**

4. les resources : 
    * tout est multilangue
    * les couleurs de textes sont des resources

5. les passages d une page à une autre 
    * par les boutons
    * dans les menus

--------------------------

#### Logique

1. un service qui génère une opération
2. un model qui est readonly pour contenir l opération
3. un service de résolution qui vérifie que l utilisateur a le bon résultat ou non
 
4. appel par les activités
   * au service de génération
   * au service de résolution

5. afficher l'opération par une resource paramétrable
6. affichage des cas d erreurs
7. affichage si l'utilisateur se trompe
8. affichage si l'utilisateur a la bonne réponse


--------------------------

#### BDD

1. Passage de la dernière opération d'une activité à l autre 
2. Sauvegarde de l'opération
3. Récupération des opérations précédentes pour faire les pourcentages
4. clean de la base 

--------------------------

### Notation et abscences
- https://docs.google.com/spreadsheets/d/1u623MSkirGRJgMq-qHhDF0B_5I1A6CoC1gstURwxqOE/edit?usp=sharing

----------------------------
### Contact : 
- pillon.tibo@gmail.com
- jerome.gerard02@outlook.fr
