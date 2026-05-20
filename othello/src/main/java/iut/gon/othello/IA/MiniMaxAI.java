package iut.gon.othello.IA;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import coordinate.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;

public class MiniMaxAI implements AI {
    private int depthMax;
    private Team myTeam;
    
    public MiniMaxAI(int depthMax, IState state, Team myTeam) {
        this.depthMax = depthMax;
        this.myTeam = myTeam;
    }

    @Override
    public Action chooseMove(IState state) {
        List<Action> actions = getMoves(state);
        Action bestAction = null;
        double bestScore = Double.NEGATIVE_INFINITY;

        // On teste chaque action possible à la racine
        for (Action action : actions) {
            try {
                IState nextState = appliquerAction(state, action);
                Node childNode = new Node(nextState, null, action);
                
                // Appel du Minimax pour l'adversaire
                double score = minimax(childNode, depthMax - 1, state.turn().other(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
                
                if (score > bestScore) {
                    bestScore = score;
                    bestAction = action;
                }
            } catch (Exception e) {
                // Ignore les mouvements invalides
            }
        }
        return bestAction;
    }
    
    public double minimax(Node n, int depth, Team currentTeam, double alpha, double beta) {
        // Condition d'arrêt (Profondeur atteinte ou fin de partie)
        if (depth == 0 || n.getEtat().winner() != null) {
            return n.evaluate(myTeam); // On évalue toujours du point de vue de myTeam
        }

        List<Action> actions = getMoves(n.getEtat());

        if (currentTeam == myTeam) { // Nœud MAX
            double maxEval = Double.NEGATIVE_INFINITY;
            for (Action action : actions) {
                try {
                    IState nextState = appliquerAction(n.getEtat(), action);
                    Node child = new Node(nextState, n, action);
                    
                    double eval = minimax(child, depth - 1, currentTeam.other(), alpha, beta);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    
                    if (beta <= alpha) break; // Élagage Bêta
                } catch (Exception e) {}
            }
            return maxEval;
        } else { // Nœud MIN
            double minEval = Double.POSITIVE_INFINITY;
            for (Action action : actions) {
                try {
                    IState nextState = appliquerAction(n.getEtat(), action);
                    Node child = new Node(nextState, n, action);
                    
                    double eval = minimax(child, depth - 1, currentTeam.other(), alpha, beta);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    
                    if (beta <= alpha) break; // Élagage Alpha
                } catch (Exception e) {}
            }
            return minEval;
        }
    }

    // Petite méthode utilitaire pour gérer Move ou RemoveLine
    private IState appliquerAction(IState state, Action action) throws Exception {
        if (action instanceof Move) {
            return state.move((Move) action);
        } else if (action instanceof RemoveLine) {
            return state.removeLine((RemoveLine) action);
        }
        return state;
    }
    
    public List<Action> getMoves(IState state) {
        List<Action> actions = new ArrayList<>();
        
        // S'il y a des lignes à supprimer, la règle OBLIGE à retirer une ligne
        if (state.lines() != null && !state.lines().isEmpty()) {
            List<Coordinate> teamRings = state.rings().get(state.turn());
            for (Set<Coordinate> line : state.lines()) {
                for (Coordinate ring : teamRings) { // On peut choisir n'importe lequel de nos anneaux à retirer
                    actions.add(new RemoveLine(line, ring));
                }
            }
        } else {
            // Sinon, l'action est un déplacement normal (Move)
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