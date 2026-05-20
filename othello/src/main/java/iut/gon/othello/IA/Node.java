package iut.gon.othello.IA;

import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;
import coordinate.Coordinate;

public class Node {
    IState etat;
    Node parent;
    Action before;
    double score;
    
    public Node(IState etat, Node parent, Action before) {
        this.etat = etat;
        this.parent = parent;
        this.before = before;
    }
    
    // On donne l'équipe de l'IA en argument pour que l'évaluation soit orientée 
    public double evaluate(Team myTeam) {
        Team opponent = myTeam.other();
        double currentScore = 0;
        
        // 1. ANNEAU EN MOINS (+/- 1000)
        // Dans le jeu, on gagne en retirant ses propres anneaux. 
        // Moins on a d'anneaux par rapport à l'adversaire, plus on est proche de la victoire.
        int myRings = etat.rings().get(myTeam).size();
        int oppRings = etat.rings().get(opponent).size();
        currentScore += (oppRings - myRings) * 1000;
        
        // 2. LIGNE DE PIONS (+/- 50)
        // Vérifie si des lignes sont formées et prêtes à être récupérées ce tour-ci
        if (etat.lines() != null && !etat.lines().isEmpty()) {
            if (etat.turn() == myTeam) {
                currentScore += 50 * etat.lines().size(); // Avantage immédiat pour nous
            } else {
                currentScore -= 50 * etat.lines().size(); // Menace immédiate de l'adversaire
            }
        }
        
        // 3. MOBILITÉ (+/- 0.5)
        // Plus on a de mouvements possibles, plus on contrôle le plateau
        double myMobility = 0;
        for (Coordinate ring : etat.rings().get(myTeam)) {
            myMobility += etat.availableMoves(ring).size();
        }
        
        double oppMobility = 0;
        for (Coordinate ring : etat.rings().get(opponent)) {
            oppMobility += etat.availableMoves(ring).size();
        }
        currentScore += (myMobility - oppMobility) * 0.5;
        
        // Sauvegarde et retourne le score
        this.score = currentScore;
        return currentScore;
    }

    public IState getEtat() {
        return etat;
    }

    public Node getParent() {
        return parent;
    }

    public Action getBefore() {
        return before;
    }
    
    public double getScore() {
        return score;
    }
}