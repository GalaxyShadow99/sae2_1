# SAE Jeu YINSH (Moteur de jeu utilisé par [mon second projet]("https://github.com/GalaxyShadow99/sae2_2") ) - BUT Informatique

Ce projet a été réalisé dans le cadre d’une **SAE (Situation d'Apprentissage et d'Évaluation)** au cours de la première année de **BUT Informatique à l'IUT de Caen Normandie (IUT d'Ifs)**. 

L'objectif principal est de concevoir et développer en Java la logique métier (modèle de données et règles de jeu) et un moteur d'Intelligence Artificielle pour jouer au jeu de plateau abstrait **YINSH** (faisant partie du célèbre Projet GIPF). L'application implémente les règles complètes du jeu, intègre un système robuste de gestion de coordonnées hexagonales (Cube et Doublées) et propose un adversaire virtuel grâce à une **Intelligence Artificielle** paramétrable basée sur l'algorithme Minimax avec élagage Alpha-Bêta, le tout s'exécutant directement en ligne de commande (CUI / Console).

---

## Interface en CLI (Console) 

Le jeu s'exécute entièrement dans le terminal. Voici un aperçu de l'affichage textuel du plateau de jeu et des interactions utilisateur :

![Interface Console](ReadmeImages/Capture_Console.png)

*Légende : `◯` / `●` représentent les anneaux, `♙` / `♟` représentent les pions, et `•` les intersections libres.*

---

## Objectifs du Projet

- **Implémenter les règles complètes du jeu YINSH :** Phase de placement initial des anneaux, déplacements le long des axes de la grille, saut de pions avec inversion de leurs couleurs (mécanique type Othello), détection et suppression des lignes de 5 pions alignés, et retrait obligatoire d'un anneau (victoire si 3 anneaux sont retirés).
- **Gérer la géométrie hexagonale :** Développer un module réutilisable de gestion de coordonnées hexagonales implémentant le système tridimensionnel (coordonnées cubes) et bidimensionnel (coordonnées doublées).
- **Concevoir un moteur d'IA performant :** Permettre le calcul automatique du meilleur coup via un algorithme Minimax optimisé (Alpha-Bêta) capable d'évaluer l'état du jeu selon des critères stratégiques (mobilité, nombre d'anneaux retirés, alignements de pions).
- **Offrir différents modes de jeu en console :** Jeu à deux joueurs humains, jeu contre l'IA ou simulation de combat entre deux IA.

---

## Technologies Utilisées

- **Langage de programmation :** Java 22 (architecture multi-modules).
- **Interface Graphique :** CLI.
- **Tests unitaires :** JUnit 5 .
- **Gestion de projet et build :** Maven.

---

## Modes d'Interaction et Règles Spécifiques (Simulés & Gérés en Java)

L'interaction utilisateur s'appuie sur la console avec des saisies de coordonnées :

- **Placement initial des anneaux :**
  - Placement alterné de 5 anneaux par équipe sur les intersections libres du plateau.
- **Phase de jeu normale (Déplacements) :**
  - Sélection d'un anneau de sa couleur en saisissant ses coordonnées de départ.
  - Saisie des coordonnées de la case d'arrivée valide (déplacement en ligne droite sur les axes du plateau).
  - Les cases vides et les séries ininterrompues de pions peuvent être survolées. Le mouvement se termine sur la première case vide après les pions, inversant ainsi la couleur de tous les pions sautés.
- **Suppression d'alignement :**
  - Dès qu'un alignement de 5 pions de même couleur est détecté, le jeu bascule en mode de suppression.
  - Le joueur doit sélectionner la ligne à supprimer parmi celles détectées, puis choisir un de ses anneaux à retirer du plateau.

---

## Configuration & Optimisation de l'IA

L'Intelligence Artificielle s'appuie sur l'algorithme [MiniMax](https://en.wikipedia.org/wiki/Minimax) avec élagage Alpha-Bêta. L'évaluation de chaque état du plateau par l'IA combine plusieurs facteurs pondérés :

* **Nombre d'anneaux restants (Objectif principal) :** Retirer ses propres anneaux étant la condition de victoire, un anneau de moins pour soi (ou de plus pour l'adversaire) augmente significativement l'évaluation (+1000).
* **Lignes de pions prêtes :** Valorise les configurations menant à un alignement de 5 pions (+50 par alignement détecté).
* **Mobilité des anneaux :** Valorise le nombre de déplacements légaux possibles pour ses propres anneaux afin de ne pas se retrouver bloqué (+0.5 par déplacement disponible).

---

## Structure du Code

Le projet est structuré en deux modules Maven distincts :

- **Module `HexagonalCoordinate` :**
  - `Coordinate.java` : Classe de base abstraite manipulant les coordonnées hexagonales.
  - `CoordinateCube.java` : Système tridimensionnel à trois axes $q, r, s$ où $q+r+s = 0$.
  - `CoordinateDoubled.java` : Système bidimensionnel en coordonnées 2D doublées.
  - `Direction.java` : Énumération des directions sur la grille hexagonale (Nord, Sud, Est, Ouest, etc.).
  - `Point.java` : Classe représentant un point d'affichage cartésien 2D.
- **Module `othello` (Moteur Yinsh & IA) :**
  - **`IA/` :** Moteur Minimax avec élagage Alpha-Bêta (`MiniMaxAI.java`), représentation des nœuds de recherche (`Node.java`) et des résultats (`Resultat.java`).
  - **`model/` :** Logique et règles du jeu YINSH (`State.java` immuable, `Model.java` de façade), gestion des pions et anneaux (`Pawn.java`, `Ring.java`) et fabriques pour l'initialisation (`FactoryCube.java`, `FactoryDoubled.java`).
  - **Points d'entrée :**
    - `CUIMain.java` : Partie locale à 2 joueurs (Humain vs Humain).
    - `IAMain.java` : Partie contre l'ordinateur (Humain vs IA).
    - `AIVsAIMain.java` : Simulation autonome (IA vs IA).

---

## Équipe

Ce projet a été réalisé en collaboration par le **Groupe 15** :
- **Victor Anger-Renault**
- **Nicolas Blaison**
- **Gabriel Gaumont**
- **Nathan Toulorge**
- **Thomas Constantin**
