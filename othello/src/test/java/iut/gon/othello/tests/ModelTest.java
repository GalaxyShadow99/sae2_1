package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import coordinate.Coordinate;
import coordinate.CoordinateCube;
import coordinate.CoordinateDoubled;
import coordinate.DifferentAxisException;
import iut.gon.othello.model.Model;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

class ModelTest {

    @Test
    void testConstructeurModel() {
        IFactory factory = new FactoryDoubled();
        IState state = factory.emptyState();

        Model model = new Model(state);

        assertNotNull(model);
        assertEquals(state, model.getCurrentState());
    }

    @Test
    void testSetCurrentState() {
        IFactory factory = new FactoryDoubled();

        IState state1 = factory.emptyState();
        IState state2 = factory.testState();

        Model model = new Model(state1);

        model.setCurrentState(state2);

        assertEquals(state2, model.getCurrentState());
    }

    @Test
    void testGetBoard() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.testState());

        Map<Coordinate, Token> board = model.getBoard();

        assertNotNull(board);
        assertFalse(board.isEmpty());
    }

    @Test
    void testGetTokenAt() {
        IFactory factory = new FactoryDoubled();
        Model model = new Model(factory.stateForWhiteLinesTest());

        Coordinate coord = new CoordinateDoubled(0, 8); // Pawn BLACK (CoordonnesCommunes)

        Token token = model.getTokenAt(coord);

        assertNotNull(token);
        assertEquals(Team.BLACK, token.getTeam());
    }

    @Test
    void testIsInField() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.emptyState());

        Coordinate valid = new CoordinateDoubled(9, 5);
        Coordinate invalid = new CoordinateDoubled(100, 100);

        assertTrue(model.isInField(valid));
        assertFalse(model.isInField(invalid));
    }

    @Test
    void testGetRings() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.testState());

        List<Coordinate> whiteRings = model.getRings(Team.WHITE);

        assertNotNull(whiteRings);
        assertFalse(whiteRings.isEmpty());
    }

    @Test
    void testGetPawns() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.testState());

        List<Coordinate> blackPawns = model.getPawns(Team.BLACK);

        assertNotNull(blackPawns);
        assertFalse(blackPawns.isEmpty());
    }

    @Test
    void testGetTurn() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.emptyState());

        assertEquals(Team.WHITE, model.getTurn());
    }

    @Test
    void testMovesFrom() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.testState());

        Coordinate from = new CoordinateDoubled(6, 3);

        Set<Coordinate> moves = model.movesFrom(from);

        assertNotNull(moves);
    }

    @Test
    void testMoveRingSimple() {
        IFactory factory = new FactoryDoubled();
        IState emptyState = factory.emptyState();
        
        HashMap<Coordinate, Token> customBoard = new HashMap<>(emptyState.board());    
        Coordinate startPos = new CoordinateDoubled(9, 5);
        customBoard.put(startPos, new Ring(Team.WHITE));
        IState customState = new State(customBoard, Team.WHITE, new ArrayList<>());
        Model model = new Model(customState);
        Coordinate endPos = new CoordinateDoubled(11, 5);
        
        try {
            model.moveRing(startPos, endPos);
            
            IState newState = model.getCurrentState();
            assertTrue(newState.board().get(endPos) instanceof Ring, 
                       "devrait y avoir Anneau sur case d'arrivée.");
            assertEquals(Team.WHITE, newState.board().get(endPos).getTeam(), 
                         "anneau d'arrivée doit être Blanc.");
            assertTrue(newState.board().get(startPos) instanceof Pawn, 
                       "Pion devrait avoir remplacé l'anneau sur case départ.");
            assertEquals(Team.WHITE, newState.board().get(startPos).getTeam(), 
                         "Pion laissé derrière --> doit être Blanc.");
        } catch (Exception e) {
            System.out.println("Echec déplacement  : " + e.getMessage());
            e.printStackTrace(); 
            fail("déplacement sur un plateau complètement vide ne devrait pas fail");
        }
    }

        @Test
    void testRemoveLine() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.doubleLineStateTest());

        List<Set<Coordinate>> lines = model.getPawnsLines();

        assertNotNull(lines);

        if (!lines.isEmpty()) {
            Set<Coordinate> line = lines.get(0);

            Coordinate ring = model.getRings(Team.WHITE).get(0);

            model.removeLine(line, ring);

            assertNotNull(model.getCurrentState());
        }
    }

    @Test
    void testGetPawnsLines() {
        IFactory factory = new FactoryDoubled();

        Model model = new Model(factory.doubleLineStateTest());

        List<Set<Coordinate>> lines = model.getPawnsLines();

        assertNotNull(lines);
    }

    @Test
    void testGetCurrentState() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        Model model = new Model(state);

        assertEquals(state, model.getCurrentState());
    }
    
    @Test
    void testRemoveToken() {
        IFactory factory = new FactoryDoubled();
        Model model = new Model(factory.testState());

        Coordinate coord = new CoordinateDoubled(3, 13); // Ring WHITE
        assertNotNull(model.getTokenAt(coord));

        model.removeToken(coord);
        assertNull(model.getTokenAt(coord));
    }
 
    
    @Test
    void testToggleToken() {
        IFactory factory = new FactoryDoubled();
 
        Model model = new Model(factory.emptyState());
 
        Coordinate coord = new CoordinateDoubled(0, 0);
 
        assertNull(model.getTokenAt(coord));
 
        model.toggleToken(coord, Team.WHITE, Ring.class);
        Token token = model.getTokenAt(coord);
        assertNotNull(token);
        assertInstanceOf(Ring.class, token);
        assertEquals(Team.WHITE, token.getTeam());
 
        model.toggleToken(coord, Team.WHITE, Ring.class);
        assertNull(model.getTokenAt(coord));
    }

}