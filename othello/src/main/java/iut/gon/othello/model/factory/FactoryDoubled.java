package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;
import coordinate.CoordinateDoubled;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryDoubled implements IFactory {

    private CoordinateDoubled toDoubled(int q, int r) {
        int x = 2 * q + r;
        int y = r;
        return new CoordinateDoubled(x, y);
    }

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
                terrain.put(toDoubled(q, r), null); 
            }
        }
        return terrain;
    }

    private void CoordonnesCommunes(Map<Coordinate, Token> grid) {
        // PIONS BLANCS COMMUNS
        grid.put(toDoubled(5, -4), new Pawn(Team.WHITE));
        grid.put(toDoubled(1, -2), new Pawn(Team.WHITE));
        grid.put(toDoubled(3, -3), new Pawn(Team.WHITE));
        grid.put(toDoubled(-2, 5), new Pawn(Team.WHITE));

        // PIONS NOIRS COMMUNS
        grid.put(toDoubled(2, -5), new Pawn(Team.BLACK));
        grid.put(toDoubled(2, -4), new Pawn(Team.BLACK));
        grid.put(toDoubled(2, -3), new Pawn(Team.BLACK));
        grid.put(toDoubled(2, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(3, -4), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -4), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -3), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(-1, -1), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -1), new Pawn(Team.BLACK));
        
        grid.put(toDoubled(-2, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-3, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-4, 1), new Pawn(Team.BLACK));
        
        grid.put(toDoubled(-5, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-5, 2), new Pawn(Team.BLACK));
        grid.put(toDoubled(-5, 3), new Pawn(Team.BLACK));
        grid.put(toDoubled(-5, 4), new Pawn(Team.BLACK));
        grid.put(toDoubled(3, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, 2), new Pawn(Team.BLACK));
        grid.put(toDoubled(-1, 3), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, 3), new Pawn(Team.BLACK));
        grid.put(toDoubled(1, 3), new Pawn(Team.BLACK));
        grid.put(toDoubled(2, 3), new Pawn(Team.BLACK));
        grid.put(toDoubled(-1, 5), new Pawn(Team.BLACK));
        grid.put(toDoubled(3, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(4, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(5, -2), new Pawn(Team.BLACK));

        // ANNEAUX NOIRS COMMUNS
        grid.put(toDoubled(-3, -1), new Ring(Team.BLACK));
        grid.put(toDoubled(1, 0), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 3), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 4), new Ring(Team.BLACK));
        grid.put(toDoubled(2, -1), new Ring(Team.BLACK));

        // ANNEAUX BLANCS COMMUNS
        grid.put(toDoubled(3, -2), new Ring(Team.WHITE));
        grid.put(toDoubled(4, -2), new Ring(Team.WHITE));
        grid.put(toDoubled(3, 0), new Ring(Team.WHITE));
        grid.put(toDoubled(1, 1), new Ring(Team.WHITE));
    }

    // --- IMPLEMENTATION INTERFACE ---

    @Override
    public IState emptyState() {
        return new State((HashMap<Coordinate, Token>)generateEmptyGrid(), Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState testState() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        
        // PIONS BLANCS
        grid.put(toDoubled(5, -4), new Pawn(Team.WHITE));
        grid.put(toDoubled(1, -2), new Pawn(Team.WHITE));
        grid.put(toDoubled(3, -3), new Pawn(Team.WHITE));
        grid.put(toDoubled(-2, 5), new Pawn(Team.WHITE));

        // PIONS NOIRS
        grid.put(toDoubled(3, -4), new Pawn(Team.BLACK));
        grid.put(toDoubled(2, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(-1, -1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-4, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-5, 4), new Pawn(Team.BLACK));

        // ANNEAUX NOIRS
        grid.put(toDoubled(-1, 3), new Ring(Team.BLACK));
        grid.put(toDoubled(1, 0), new Ring(Team.BLACK));
        grid.put(toDoubled(2, 0), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 3), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 4), new Ring(Team.BLACK));

        // ANNEAUX BLANCS
        grid.put(toDoubled(3, -2), new Ring(Team.WHITE));
        grid.put(toDoubled(4, -2), new Ring(Team.WHITE));
        grid.put(toDoubled(3, 0), new Ring(Team.WHITE));
        grid.put(toDoubled(1, 1), new Ring(Team.WHITE));
        
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForBlackLinesTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        CoordonnesCommunes(grid);
        
        grid.put(toDoubled(-5, -1), new Pawn(Team.BLACK));
        
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForWhiteLinesTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        CoordonnesCommunes(grid);
        
        grid.put(toDoubled(0, 0), new Pawn(Team.BLACK));
        grid.put(toDoubled(-1, 1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-5, -1), new Pawn(Team.BLACK));
        
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState doubleLineStateTest() {
        Map<Coordinate, Token> grid = generateEmptyGrid();
        
        // PIONS BLANCS
        grid.put(toDoubled(1, -2), new Pawn(Team.WHITE));
        grid.put(toDoubled(0, -1), new Pawn(Team.WHITE));
        grid.put(toDoubled(-1, -1), new Pawn(Team.WHITE));
        grid.put(toDoubled(2, -1), new Pawn(Team.WHITE));

        // PIONS NOIRS
        grid.put(toDoubled(-1, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(0, -2), new Pawn(Team.BLACK));
        grid.put(toDoubled(1, -1), new Pawn(Team.BLACK));
        grid.put(toDoubled(-2, 2), new Pawn(Team.BLACK)); 
        grid.put(toDoubled(-3, 2), new Pawn(Team.BLACK)); 

        // ANNEAUX NOIRS
        grid.put(toDoubled(-3, -1), new Ring(Team.BLACK));
        grid.put(toDoubled(1, 0), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 3), new Ring(Team.BLACK));
        grid.put(toDoubled(-2, 4), new Ring(Team.BLACK));
        grid.put(toDoubled(2, -1), new Ring(Team.BLACK));

        // ANNEAUX BLANCS
        grid.put(toDoubled(3, -3), new Ring(Team.WHITE));
        grid.put(toDoubled(5, -3), new Ring(Team.WHITE));
        grid.put(toDoubled(3, 0), new Ring(Team.WHITE));
        grid.put(toDoubled(1, 1), new Ring(Team.WHITE));
        
        return new State((HashMap<Coordinate, Token>) grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }
}