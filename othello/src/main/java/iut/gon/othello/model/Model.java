package iut.gon.othello.model;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.DifferentAxisException;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Token;

import java.util.ArrayList;
import java.util.List; 
import java.util.Set;
import java.util.Map; 

public class Model {
    
    private IState currentState;

    public Model(IState state) {
        this.currentState = state;
    }

    public void setCurrentState(IState state) {
        this.currentState = state;
    }
    
    /**
     * Retourne l'ensemble des coordonnées accessibles depuis une position donnée.
     * @param from Coordonnée de départ (Coordinate)
     * @return Set<Coordinate> ensemble des déplacements possibles
     */
    public Set<Coordinate> movesFrom(Coordinate from) {
        return currentState.availableMoves(from); 
    }
    
    /**
     * Déplace un anneau d'une case à une autre et met à jour l'état du jeu.
     * @param from Coordonnée de départ (Coordinate)
     * @param to Coordonnée d'arrivée (Coordinate)
     * @throws DifferentAxisException si les coordonnées ne sont pas sur le même axe
     */
    public void moveRing(Coordinate from, Coordinate to) throws DifferentAxisException {
        currentState = currentState.move(new Move(from, to));
    }
    
    /**
     * Supprime une ligne de pions et l'anneau associé du plateau.
     * @param line ensemble des coordonnées formant la ligne (Set<Coordinate>)
     * @param ring coordonnée de l'anneau à retirer (Coordinate)
     */
    public void removeLine(Set<Coordinate> line, Coordinate ring) {
        currentState = currentState.removeLine(new RemoveLine(line, ring));
    }
    
    /**
     * Retourne la liste des lignes de 5 pions détectées sur le plateau.
     * @return List<Set<Coordinate>> liste des lignes de pions
     */
    public List<Set<Coordinate>> getPawnsLines() {
        return currentState.getPawnsLines(currentState.board());
    }
  
    public Map<Coordinate, Token> getBoard(){
        return currentState.board(); 
    }
    
    public Token getTokenAt(Coordinate c) {
        return currentState.board().get(c); 
    }
    
    public boolean isInField(Coordinate c) {
        return ((State) currentState).isInField(c); 
    }
    
    public List<Coordinate> getRings(Team team){
        return currentState.rings().get(team); 
    }
    
    /**
     * Retourne la liste des coordonnées des pions d'une équipe donnée.
     * @param team équipe dont on cherche les pions (Team)
     * @return List<Coordinate> liste des coordonnées des pions
     */
    public List<Coordinate> getPawns(Team team) {
        List<Coordinate> result = new ArrayList<>();
        
     // currentState.board().entrySet() : recupère les cases du plateau en clé/valeur
     // for (Map.Entry<Coordinate, Token> entry : parcours chaque case
        for (Map.Entry<Coordinate, Token> entry : currentState.board().entrySet()) { 
            if (entry.getValue() instanceof Pawn && entry.getValue().getTeam() == team) {
                result.add(entry.getKey());
                //entry.getValue() : on veut le token de la case
                //result.add(entry.getKey()) : si les 2 conditions sont vrais  ça ajoute la coordonnée à la liste
            }
        }
        return result;
    }
    
    public Team getTurn() {
        return currentState.turn(); 
    }
    
    public IState getCurrentState() {
        return currentState; 
    }
    
    /**
     * Retire le token situé à la coordonnée spécifiée.
     * @param c coordonnée du token à retirer (Coordinate)
     */
    public void removeToken(Coordinate c) {
        currentState = currentState.removeToken(c);
    }
 
    /**
     * Active ou désactive un token à la position donnée pour une équipe.
     * @param position coordonnée ciblée (Coordinate)
     * @param team équipe propriétaire du token (Team)
     * @param token classe du token à placer (Class<?>)
     */
    public void toggleToken(Coordinate position, Team team, Class<?> token) {
        currentState = currentState.toggleToken(position, team, token);
    }

}
