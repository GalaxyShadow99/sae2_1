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

    /**
     * Retourne l'état du jeu de ce nœud.
     * @return IState état du jeu
     */
    public IState getEtat() {
        return etat;
    }

    /**
     * Retourne le nœud parent.
     * @return Node nœud parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Retourne l'action menant à ce nœud.
     * @return Action action précédente
     */
    public Action getBefore() {
        return before;
    }
    
    /**
     * Retourne le score évalué du nœud.
     * @return double score du nœud
     */
    public double getScore() {
        return score;
    }
}