package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.List;

import coordinate.Coordinate;
import coordinate.CoordinateCube;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryCube implements IFactory {

    private Map<Coordinate, Token> generateEmptyGrid() {
        Map<Coordinate, Token> terrain = new HashMap<>();
        
        int[][] limitesQ = {
            { 1, 4 }, {-1, 5 }, {-2, 5 }, {-3, 5 }, {-4, 5 },
            {-4, 4 }, {-5, 4 }, {-5, 3 }, {-5, 2 }, {-5, 1 }, {-4,-1 }
        };

        for (int r = -5; r <= 5; r++) {
            int qMin = limitesQ[r + 5][0];
            int qMax = limitesQ[r + 5][1];
            for (int q = qMin; q <= qMax; q++) {
                int s = -q - r;
                terrain.put(new CoordinateCube(q, r, s), null); 
            }
        }
        return terrain;
    }

    /**
     * Regroupe tout le placement commun partagé par BlackLinesTest et WhiteLinesTest.
     */
    private void CoordonnesCommunes(Map<Coordinate, Token> grid) {
        // PIONS BLANCS COMMUNS
        grid.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
        grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
        grid.put(new CoordinateCube(3, -3, 0), new Pawn(Team.WHITE));
        grid.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));

        grid.put(new CoordinateCube(2, -5, 3), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(2, -4, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(2, -3, 1), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, -4, 4), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, -3, 3), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, -1, 1), new Pawn(Team.BLACK));
        
        grid.put(new CoordinateCube(-2, 1, 1), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-3, 1, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
        
        grid.put(new CoordinateCube(-5, 1, 4), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-5, 2, 3), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-5, 3, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(3, 1, -4), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, 2, -2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-1, 3, -2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(0, 3, -3), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(1, 3, -4), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(2, 3, -5), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(-1, 5, -4), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(4, -2, -2), new Pawn(Team.BLACK));
        grid.put(new CoordinateCube(5, -2, -3), new Pawn(Team.BLACK));

        // ANNEAUX NOIRS COMMUNS
        grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
        grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
        grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
        grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
        grid.put(new CoordinateCube(2, -1, -1), new Ring(Team.BLACK));

        // ANNEAUX BLANCS COMMUNS
        grid.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
        grid.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
        grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
    }


    // --- IMPLEMENTATION INTERFACE 

    @Override
    public IState emptyState() {
        return new State((HashMap<Coordinate, Token>) generateEmptyGrid(), Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState testState() {
        HashMap<Coordinate, Token> grid = (HashMap<Coordinate, Token>) generateEmptyGrid();
        try {
            grid.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(3, -3, 0), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));

            grid.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));

            grid.put(new CoordinateCube(-1, 3, -2), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK)); // fix pour somme = 0
            grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));

            grid.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }       
        return new State(grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForBlackLinesTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        try {
            CoordonnesCommunes(grid);
            
            // Placement spécifique à BlackLinesTest
            grid.put(new CoordinateCube(-5, -1, 6), new Pawn(Team.BLACK)); // Corrigé pour s=6
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }       
        return new State((HashMap<Coordinate, Token>)grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForWhiteLinesTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        try {
            CoordonnesCommunes(grid);
            
            // spécifique à WhiteLinesTest
            grid.put(new CoordinateCube(0, 0, 0), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(-1, 1, 0), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(-5, -1, 6), new Pawn(Team.BLACK));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }       
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }
    
    @Override
    public IState doubleLineStateTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        try {
            grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(0, -1, 1), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.WHITE));
            grid.put(new CoordinateCube(2, -1, -1), new Pawn(Team.WHITE));

            grid.put(new CoordinateCube(-1, -2, 3), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
            grid.put(new CoordinateCube(1, -1, 0), new Pawn(Team.BLACK));
            
            grid.put(new CoordinateCube(-2, 2, 0), new Pawn(Team.BLACK)); 
            grid.put(new CoordinateCube(-3, 2, 1), new Pawn(Team.BLACK)); 

            grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
            grid.put(new CoordinateCube(2, -1, -1), new Ring(Team.BLACK));

            grid.put(new CoordinateCube(3, -3, 0), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(5, -3, -2), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
            grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }       
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
       
}
}