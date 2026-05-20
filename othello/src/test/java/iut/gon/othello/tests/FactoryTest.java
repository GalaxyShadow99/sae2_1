package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.CoordinateCube;
import iut.gon.coordinate.CoordinateDoubled;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

class FactoryTest {

	//TESTS DE FACTORY DOUBLE

	@Test
    void testEmptyStateDoubled() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        assertNotNull(state);
        assertEquals(Team.WHITE, state.turn());

        Map<Coordinate, Token> board = state.board();

        assertNotNull(board);
        assertFalse(board.isEmpty());

        Coordinate coord = new CoordinateDoubled(5, 9);
        assertTrue(board.containsKey(coord));
        assertNull(board.get(coord));
    }
	
	@Test
    void testTestStateDoubled() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.testState();

        Map<Coordinate, Token> board = state.board();

        Coordinate whitePawn = new CoordinateDoubled(1, 15);
        assertTrue(board.get(whitePawn) instanceof Pawn);
        assertEquals(Team.WHITE, board.get(whitePawn).getTeam());

        Coordinate blackPawn = new CoordinateDoubled(1, 11);
        assertTrue(board.get(blackPawn) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(blackPawn).getTeam());

        Coordinate whiteRing = new CoordinateDoubled(3, 13);
        assertNotNull(board.get(whiteRing));
        assertTrue(board.get(whiteRing) instanceof Ring);
        assertEquals(Team.WHITE, board.get(whiteRing).getTeam());
    }
    @Test
    void testStateForBlackLinesTestDoubled() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.stateForBlackLinesTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate coord = new CoordinateDoubled(4, 2);

        assertTrue(board.get(coord) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(coord).getTeam());
    }

    @Test
    void testStateForWhiteLinesTestDoubled() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.stateForWhiteLinesTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate coord = new CoordinateDoubled(5, 9);

        assertTrue(board.get(coord) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(coord).getTeam());
    }
    @Test
    void testDoubleLineStateTestDoubled() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.doubleLineStateTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate whitePawn = new CoordinateDoubled(4, 6);
        assertTrue(board.get(whitePawn) instanceof Pawn);
        assertEquals(Team.WHITE, board.get(whitePawn).getTeam());

        Coordinate blackRing = new CoordinateDoubled(3, 3);
        assertTrue(board.get(blackRing) instanceof Ring);
        assertEquals(Team.BLACK, board.get(blackRing).getTeam());
    }

    //TESTS DE FACTORY CUBE

    @Test
    void testEmptyStateCube() {
        IFactory factory = new FactoryCube();

        IState state = factory.emptyState();

        assertNotNull(state);
        assertEquals(Team.WHITE, state.turn());

        Map<Coordinate, Token> board = state.board();

        assertNotNull(board);
        assertFalse(board.isEmpty());

        Coordinate coord = new CoordinateCube(0, 0, 0);

        assertTrue(board.containsKey(coord));
        assertNull(board.get(coord));
    }

    @Test
    void testTestStateCube() {
        IFactory factory = new FactoryCube();

        IState state = factory.testState();

        Map<Coordinate, Token> board = state.board();

        Coordinate whitePawn = new CoordinateCube(5, -4, -1);

        assertTrue(board.get(whitePawn) instanceof Pawn);
        assertEquals(Team.WHITE, board.get(whitePawn).getTeam());

        Coordinate blackPawn = new CoordinateCube(3, -4, 1);

        assertTrue(board.get(blackPawn) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(blackPawn).getTeam());

        Coordinate whiteRing = new CoordinateCube(3, -2, -1);

        assertTrue(board.get(whiteRing) instanceof Ring);
        assertEquals(Team.WHITE, board.get(whiteRing).getTeam());
    }

    @Test
    void testStateForBlackLinesTestCube() {
        IFactory factory = new FactoryCube();

        IState state = factory.stateForBlackLinesTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate coord = new CoordinateCube(-5, -1, 6);

        assertTrue(board.get(coord) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(coord).getTeam());
    }

    @Test
    void testStateForWhiteLinesTestCube() {
        IFactory factory = new FactoryCube();

        IState state = factory.stateForWhiteLinesTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate coord = new CoordinateCube(0, 0, 0);

        assertTrue(board.get(coord) instanceof Pawn);
        assertEquals(Team.BLACK, board.get(coord).getTeam());
    }

    @Test
    void testDoubleLineStateTestCube() {
        IFactory factory = new FactoryCube();

        IState state = factory.doubleLineStateTest();

        Map<Coordinate, Token> board = state.board();

        Coordinate whitePawn = new CoordinateCube(0, -1, 1);

        assertTrue(board.get(whitePawn) instanceof Pawn);
        assertEquals(Team.WHITE, board.get(whitePawn).getTeam());

        Coordinate blackRing = new CoordinateCube(-3, -1, 4);

        assertTrue(board.get(blackRing) instanceof Ring);
        assertEquals(Team.BLACK, board.get(blackRing).getTeam());
    }
}
