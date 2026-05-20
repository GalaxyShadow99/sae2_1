package iut.gon.othello.IA;


import iut.gon.coordinate.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public class Node {
    IState etat;
    Node parent;
    Action before;
    double score;
    
    /**
     * Crée un nœud de l'arbre de jeu.
     * @param etat état du jeu (IState)
     * @param parent nœud parent (Node)
     * @param before action menant à cet état (Action)
     */
    public Node(IState etat, Node parent, Action before) {
        this.etat = etat;
        this.parent = parent;
        this.before = before;
    }
    
    /**
     * Évalue la qualité de l'état pour une équipe donnée.
     * @param myTeam l'équipe à évaluer (Team)
     * @return double le score d'évaluation
     */
    public double evaluate(Team myTeam) {
        Team opponent = myTeam.other();
        double currentScore = 0;
        
        int myRings = etat.rings().get(myTeam).size();
        int oppRings = etat.rings().get(opponent).size();
        currentScore += (oppRings - myRings) * 1000;
        
        if (etat.lines() != null && !etat.lines().isEmpty()) {
            if (etat.turn() == myTeam) {
                currentScore += 50 * etat.lines().size();
            } else {
                currentScore -= 50 * etat.lines().size();
            }
        }
        
        double myMobility = 0;
        for (Coordinate ring : etat.rings().get(myTeam)) {
            myMobility += etat.availableMoves(ring).size();
        }
        
        double oppMobility = 0;
        for (Coordinate ring : etat.rings().get(opponent)) {
            oppMobility += etat.availableMoves(ring).size();
        }
        currentScore += (myMobility - oppMobility) * 0.5;
        
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