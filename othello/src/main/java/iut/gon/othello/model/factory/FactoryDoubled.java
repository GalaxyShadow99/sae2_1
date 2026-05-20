package iut.gon.othello.model.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.CoordinateDoubled;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryDoubled implements IFactory {

    private CoordinateDoubled toDoubled(int q, int r) {
        int y = r + 5;
        int x = (2 * q + r) + 9;
        return new CoordinateDoubled(x, y);
    }

    private HashMap<Coordinate, Token> generateEmptyGrid() {
        HashMap<Coordinate, Token> terrain = new HashMap<>();
        
        int[][] limitesX = {
                { 6, 12 }, // Ligne 0
                { 3, 15 }, 
                { 2, 16 }, 
                { 1, 17 }, 
                { 0, 18 }, 
                { 1, 17 }, 
                { 0, 18 }, 
                { 1, 17 }, 
                { 2, 16 }, 
                { 3, 15 }, 
                { 6, 12 }  // Ligne 10
            };

            for (int y = 0; y <= 10; y++) {
                int xMin = limitesX[y][0];
                int xMax = limitesX[y][1];
                
                for (int x = xMin; x <= xMax; x += 2) {
                    terrain.put(new CoordinateDoubled(x, y), null); 
                }
            }
            return terrain;    
           }

    private void CoordonnesCommunes(Map<Coordinate, Token> grid) {
        // PIONS BLANCS COMMUNS
        grid.put(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(2, 12), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));

        // PIONS NOIRS COMMUNS
        grid.put(new CoordinateDoubled(0, 8), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(1, 9), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(2, 10), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(1, 5), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(2, 6), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 8), new Pawn(Team.BLACK));
        
        grid.put(new CoordinateDoubled(6, 6), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(6, 4), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
        
        grid.put(new CoordinateDoubled(6, 0), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(7, 1), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(6, 16), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(7, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 10), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 12), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 14), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 16), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(10, 12), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 15), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 17), new Pawn(Team.BLACK));

        // ANNEAUX NOIRS COMMUNS
        grid.put(new CoordinateDoubled(4, 2), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 12), new Ring(Team.BLACK));

        // ANNEAUX BLANCS COMMUNS
        grid.put(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
    }


    @Override
    public IState emptyState() {
        return new State(generateEmptyGrid(), Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState testState() {
        HashMap<Coordinate, Token> grid = generateEmptyGrid();
        
        // PIONS BLANCS
        grid.put(new CoordinateDoubled(1, 15), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(3, 9), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(2, 12), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(10, 10), new Pawn(Team.WHITE));

        // PIONS NOIRS
        grid.put(new CoordinateDoubled(1, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 6), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(6, 2), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(9, 3), new Pawn(Team.BLACK));

        // ANNEAUX NOIRS
        grid.put(new CoordinateDoubled(8, 10), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(5, 11), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(5, 13), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 8), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(9, 9), new Ring(Team.BLACK));

        // ANNEAUX BLANCS
        grid.put(new CoordinateDoubled(3, 13), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(3, 15), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(5, 15), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(6, 12), new Ring(Team.WHITE));
        
        return new State(grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForBlackLinesTest() {
        HashMap<Coordinate, Token> grid = generateEmptyGrid();
        CoordonnesCommunes(grid);
        
        grid.put(new CoordinateDoubled(4, 2), new Pawn(Team.BLACK));
        
        return new State(grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState stateForWhiteLinesTest() {
        HashMap<Coordinate, Token> grid = generateEmptyGrid();
        CoordonnesCommunes(grid);
        
        grid.put(new CoordinateDoubled(5, 9), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(6, 8), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(4, -2), new Pawn(Team.BLACK));
        
        return new State(grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }

    @Override
    public IState doubleLineStateTest() {
        HashMap<Coordinate, Token> grid = generateEmptyGrid();
        
        // 1. PIONS NOIRS 
        grid.put(new CoordinateDoubled(3, 7), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 9), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 11), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 13), new Pawn(Team.BLACK));
        grid.put(new CoordinateDoubled(3, 15), new Pawn(Team.BLACK));

        // 2. PIONS BLANCS
        grid.put(new CoordinateDoubled(4, 6), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 8), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 10), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 12), new Pawn(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 14), new Pawn(Team.WHITE));

        // 3. ANNEAUX NOIRS 
        grid.put(new CoordinateDoubled(3, 3), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 12), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(4, 14), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(8, 10), new Ring(Team.BLACK));
        grid.put(new CoordinateDoubled(9, 11), new Ring(Team.BLACK));

        // 4. ANNEAUX BLANCS
        grid.put(new CoordinateDoubled(1, 15), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(2, 12), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 16), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(4, 18), new Ring(Team.WHITE));
        grid.put(new CoordinateDoubled(5, 13), new Ring(Team.WHITE));

        return new State(grid, Team.WHITE, new ArrayList<Set<Coordinate>>());
    }
}