package iut.gon.othello.model.factory;

import java.util.HashMap;
import java.util.Map;

import coordinate.Coordinate;
import coordinate.CoordinateCube;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

public class FactoryCube implements IFactory {

	// génère un plateau vide mais sans le côté immuable que l'on a dans le return 
	private Map<Coordinate, Token> generateEmptyGrid() {
        Map<Coordinate, Token> terrain = new HashMap<>();
        
        // Ton tableau défini à la main
        int[][] limitesQ = {
            { 1, 4 }, {-1, 5 }, {-2, 5 }, {-3, 5 }, {-4, 5 },
            {-4, 4 }, {-5, 4 }, {-5, 3 }, {-5, 2 }, {-5, 1 }, {-4,-1 }
        };

        for (int r = -5; r <= 5; r++) {
            int qMin = limitesQ[r + 5][0];
            int qMax = limitesQ[r + 5][1];
            for (int q = qMin; q <= qMax; q++) {
                int s = -q - r;
                terrain.put(new CoordinateCube(q, r, s), null); // null = case vide
            }
        }
        return terrain;
    }
	
	@Override
	public IState testState() {
		
		Map<Coordinate, Token> grid = generateEmptyGrid();
		
		try {
	        // PIONS BLANCS 
	        grid.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(3, -3, 0), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));

	        // PIONS NOIRS 
	        grid.put(new CoordinateCube(3, -4, 1), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(2, -2, 0), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-1, -1, 2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-4, 1, 3), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-5, 4, 1), new Pawn(Team.BLACK));

	        // ANNEAUX NOIRS 
	        grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(2, 0, -2), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 3, 1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));

	        // ANNEAUX BLANCS
	        grid.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));

	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }		
		
		return new State(grid);
		
	    };

	@Override
	public IState stateForBlackLinesTest() {
		Map<Coordinate, Token> grid = generateEmptyGrid();
		
		try {
	        // PIONS BLANCS 
	        grid.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(3, -3, 0), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));

	        // PIONS NOIRS 
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

	        grid.put(new CoordinateCube(-2, -1, 1), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-3, -1, 2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-4, -1, 3), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-5, -1, 4), new Pawn(Team.BLACK));

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
	        grid.put(new CoordinateCube(3, -2, 1), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(4, -2, -2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(5, -2, -3), new Pawn(Team.BLACK));

	        
	        // ANNEAUX NOIRS 
	        grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(2, -1, -1), new Ring(Team.BLACK));

	        // ANNEAUX BLANCS
	        grid.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));

	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }		
		
		return new State(grid);
	}

	@Override
	public IState stateForWhiteLinesTest() {
		Map<Coordinate, Token> grid = generateEmptyGrid();
		
		try {
	        // PIONS BLANCS 
	        grid.put(new CoordinateCube(5, -4, -1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(1, -2, 1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(3, -3, 0), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(-2, 5, -3), new Pawn(Team.WHITE));

	        // PIONS NOIRS 
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
	        grid.put(new CoordinateCube(0, 0, 0), new Pawn(Team.BLACK));

	        grid.put(new CoordinateCube(-1, 1, 0), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-2, -1, 1), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-3, -1, 2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-4, -1, 3), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-5, -1, 4), new Pawn(Team.BLACK));

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
	        grid.put(new CoordinateCube(3, -2, 1), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(4, -2, -2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(5, -2, -3), new Pawn(Team.BLACK));

	        
	        // ANNEAUX NOIRS 
	        grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(2, -1, -1), new Ring(Team.BLACK));

	        // ANNEAUX BLANCS
	        grid.put(new CoordinateCube(3, -2, -1), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(4, -2, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));

	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }		
		
		return new State(grid);

	}

	@Override
	public IState emptyState() {
		// terrain vide
        Map<Coordinate, Token> terrain = new HashMap<>();

        // 
        int[][] limitesQ = {
            { 1, 4 },   // r = -5
            {-1, 5 },   // r = -4
            {-2, 5 },   // r = -3
            {-3, 5 },   // r = -2
            {-4, 5 },   // r = -1
            {-4, 4 },   // r =  0 (centre)
            {-5, 4 },   // r =  1
            {-5, 3 },   // r =  2
            {-5, 2 },   // r =  3
            {-5, 1 },   // r =  4
            {-4,-1 }    // r =  5
        };

        for (int r = -5; r <= 5; r++) {
            int index = r + 5;
            int qMin = limitesQ[index][0];
            int qMax = limitesQ[index][1];

            for (int q = qMin; q <= qMax; q++) {
                int s = -q - r; // q + r + s = 0
                
                try {
                    CoordinateCube coord = new CoordinateCube(q, r, s);
                    terrain.put(coord, null);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return new State(terrain); 
	}

	@Override
	public IState doubleLineStateTest() {
		Map<Coordinate, Token> grid = generateEmptyGrid();
		
		try {
	        // PIONS BLANCS 
	        grid.put(new CoordinateCube(1 -2, 1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(0 -1, 1), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(-1 -1, 2), new Pawn(Team.WHITE));
	        grid.put(new CoordinateCube(2 -1, -1), new Pawn(Team.WHITE));

	        // PIONS NOIRS 
	        grid.put(new CoordinateCube(-1, -2, 3), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(0, -2, 2), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(1, -1, 0), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-2, -2, 0), new Pawn(Team.BLACK));
	        grid.put(new CoordinateCube(-3, -2, -1), new Pawn(Team.BLACK));

	        // ANNEAUX NOIRS 
	        grid.put(new CoordinateCube(-3, -1, 4), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(1, 0, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 3, -1), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(-2, 4, -2), new Ring(Team.BLACK));
	        grid.put(new CoordinateCube(2, -1, -1), new Ring(Team.BLACK));

	        // ANNEAUX BLANCS
	        grid.put(new CoordinateCube(3, -3, 0), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(5, -3, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(3, 0, -3), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));
	        grid.put(new CoordinateCube(1, 1, -2), new Ring(Team.WHITE));

	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	    }		
		
		return new State(grid);
	
	}

}