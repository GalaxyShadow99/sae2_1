package iut.gon.othello.model;

import coordinate.Coordinate;
import coordinate.DifferentAxisException;
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
import java.util.stream.Collectors; 

public class Model {
    
    private IState currentState;

    public Model(IState state) {
        this.currentState = state;
    }

    public void setCurrentState(IState state) {
        this.currentState = state;
    }
    
    public Set<Coordinate> movesFrom(Coordinate from) {
        return currentState.availableMoves(from); 
    }
    
    public void moveRing(Coordinate from, Coordinate to) throws DifferentAxisException {
        currentState = currentState.move(new Move(from, to));
    }
    
    public void removeLine(Set<Coordinate> line, Coordinate ring) {
        currentState = currentState.removeLine(new RemoveLine(line, ring));
    }
    
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
    
    public List<Coordinate> getPawns(Team team) {
        List<Coordinate> result = new ArrayList<>();
        
     // currentState.board().entrySet() : recupère les cases du plateau en clé/valeur
     // for (Map.Entry<Coordinate, Token> entry : parcours chaque case
        for (Map.Entry<Coordinate, Token> entry : currentState.board().entrySet()) { 
            if (entry.getValue() instanceof Pawn && entry.getValue().getTeam() == team) {
                result.add(entry.getKey());
                //entry.getValue() : on veut le token de la case
                //result.add(entry.getKey()) : si les 2 conditions sont vrais  ça ajoute la coordonné à la liste
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
}
