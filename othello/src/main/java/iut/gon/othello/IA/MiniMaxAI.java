package iut.gon.othello.IA;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import iut.gon.coordinate.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;

public class MiniMaxAI implements AI {
    private int depthMax;
    private Team myTeam;
    
    /**
     * Crée une IA utilisant l'algorithme Minimax avec élagage alpha-bêta.
     * @param depthMax profondeur maximale de recherche (int)
     * @param state état initial du jeu (IState)
     * @param myTeam équipe contrôlée par l'IA (Team)
     */
    public MiniMaxAI(int depthMax, IState state, Team myTeam) {
        this.depthMax = depthMax;
        this.myTeam = myTeam;
    }

    /**
     * Choisit la meilleure action en évaluant chaque coup possible avec Minimax.
     * @param state état actuel du jeu (IState)
     * @return Action la meilleure action trouvée
     */
    @Override
    public Action chooseMove(IState state) {
        List<Action> actions = getMoves(state);
        Action bestAction = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        for (Action action : actions) {
            try {
                IState nextState = appliquerAction(state, action);
                Node childNode = new Node(nextState, null, action);
                
                double score = minimax(childNode, depthMax - 1, state.turn().other(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                
                if (score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            } catch (Exception e) {
               
            }
        }
        return bestAction;
    }
    
    /**
     * Algorithme Minimax avec élagage alpha-bêta pour évaluer les coups.
     * @param n nœud courant (Node)
     * @param depth profondeur restante (int)
     * @param currentTeam équipe dont c'est le tour (Team)
     * @param alpha valeur alpha pour l'élagage (double)
     * @param beta valeur bêta pour l'élagage (double)
     * @return double meilleur score estimé
     */
    public double minimax(Node n, int depth, Team currentTeam, double alpha, double beta) {
        if (depth == 0 || n.getEtat().winner() != null) {
            return n.evaluate(myTeam); 
        }

        List<Action> actions = getMoves(n.getEtat());

        if (currentTeam == myTeam) { 
            double maxEval = Double.NEGATIVE_INFINITY;
            for (Action action : actions) {
                try {
                    IState nextState = appliquerAction(n.getEtat(), action);
                    Node child = new Node(nextState, n, action);
                    
                    double eval = minimax(child, depth - 1, currentTeam.other(), alpha, beta);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    
                    if (beta <= alpha) break; 
                } catch (Exception e) {}
            }
            return maxEval;
        } else { 
            double minEval = Double.POSITIVE_INFINITY;
            for (Action action : actions) {
                try {
                    IState nextState = appliquerAction(n.getEtat(), action);
                    Node child = new Node(nextState, n, action);
                    
                    double eval = minimax(child, depth - 1, currentTeam.other(), alpha, beta);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    
                    if (beta <= alpha) break; 
                } catch (Exception e) {}
            }
            return minEval;
        }
    }

    private IState appliquerAction(IState state, Action action) throws Exception {
        if (action instanceof Move) {
            return state.move((Move) action);
        } else if (action instanceof RemoveLine) {
            return state.removeLine((RemoveLine) action);
        }
        return state;
    }
    
    /**
     * Génère la liste de toutes les actions possibles depuis un état.
     * @param state état actuel du jeu (IState)
     * @return List<Action> liste des actions possibles
     */
    public List<Action> getMoves(IState state) {
        List<Action> actions = new ArrayList<>();
        
        if (state.lines() != null && !state.lines().isEmpty()) {
            List<Coordinate> teamRings = state.rings().get(state.turn());
            for (Set<Coordinate> line : state.lines()) {
                for (Coordinate ring : teamRings) { 
                    actions.add(new RemoveLine(line, ring));
                }
            }
        } else {
            List<Coordinate> teamRings = state.rings().get(state.turn());
            for (Coordinate ring : teamRings) {
                Set<Coordinate> targets = state.availableMoves(ring);
                if (targets != null) {
                    for (Coordinate target : targets) {
                        actions.add(new Move(ring, target));
                    }
                }
            }
        }
        return actions;
    }
}