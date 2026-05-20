package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

class StateTest {

    @Test
    void testBoard() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        Map<Coordinate, Token> board = state.board();

        assertNotNull(board);
        assertFalse(board.isEmpty());
    }

    @Test
    void testTurn() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        assertEquals(Team.WHITE, state.turn());
    }

    @Test
    void testLines() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        assertNotNull(state.lines());
    }

    @Test
    void testRings() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.testState();

        Map<Team, List<Coordinate>> rings = state.rings();

        assertNotNull(rings);
        assertTrue(rings.containsKey(Team.WHITE));
        assertTrue(rings.containsKey(Team.BLACK));
    }

    @Test
    void testWinner() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.testState();

        Team winner = state.winner();

        assertNull(winner);
    }

    @Test
    void testAvailableMoves() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.testState();

        Coordinate from = new CoordinateDoubled(3, 9);

        Set<Coordinate> moves = state.availableMoves(from);

        assertNotNull(moves);
    }
    
    @Test
    void testMoveSimple() {
        // plateau vide généré par la Factory
        IFactory factory = new FactoryDoubled();
        IState emptyState = factory.emptyState();
        
        HashMap<Coordinate, Token> customBoard = new HashMap<>(emptyState.board());
        // place UN anneau blanc au centre 
        Coordinate from = new CoordinateDoubled(9, 5);
        customBoard.put(from, new Ring(Team.WHITE));
        
        // on crée l'état (au tour de team WHITE)
        IState customState = new State(customBoard, Team.WHITE, new ArrayList<>());
        Coordinate to = new CoordinateDoubled(11, 5);
        Move move = new Move(from, to);
        
        try {
            IState newState = customState.move(move);
            
            assertNotNull(newState, "Le nouvel état généré ne doit pas être null.");
            assertTrue(newState.board().get(to) instanceof Ring, "Il devrait y avoir un Anneau sur la case d'arrivée.");
            assertEquals(Team.WHITE, newState.board().get(to).getTeam(), "L'anneau d'arrivée doit être Blanc.");
            assertTrue(newState.board().get(from) instanceof Pawn, "Un Pion devrait avoir remplacé l'anneau sur la case de départ.");
            assertEquals(Team.WHITE, newState.board().get(from).getTeam(), "Le Pion laissé derrière doit être de la même couleur (Blanc).");
            
        } catch (Exception e) {
            System.out.println("Echec du test de déplacement via fct Move ");
            e.printStackTrace();
            fail("Move doit pas lancer d'exception sur déplacement libre et valide : " + e.getMessage());
        }
    }
    
    @Test
    void testRemoveLine() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.doubleLineStateTest();

        List<Set<Coordinate>> lines = state.getPawnsLines(state.board());

        if (!lines.isEmpty()) {

            Set<Coordinate> line = lines.get(0);

            Coordinate ring = state.rings().get(Team.WHITE).get(0);

            RemoveLine removeLine = new RemoveLine(line, ring);

            IState newState = state.removeLine(removeLine);

            assertNotNull(newState);
        }
    }

    @Test
    void testGetPawnsLines() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.doubleLineStateTest();

        List<Set<Coordinate>> lines = state.getPawnsLines(state.board());

        assertNotNull(lines);
    }

    @Test
    void testRemoveToken() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.testState();

        Coordinate coord = new CoordinateDoubled(3, 9);

        IState newState = state.removeToken(coord);

        assertNull(newState.board().get(coord));
    }

    @Test
    void testToggleToken() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        Coordinate coord = new CoordinateDoubled(0, 0);

        IState newState = state.toggleToken(coord, Team.WHITE, Pawn.class);

        assertNotNull(newState.board().get(coord));
        assertEquals(Team.WHITE, newState.board().get(coord).getTeam());
    }

    @Test
    void testToggleTokenRemove() {
        IFactory factory = new FactoryDoubled();

        IState state = factory.emptyState();

        Coordinate coord = new CoordinateDoubled(0, 0);

        state = state.toggleToken(coord, Team.WHITE, Ring.class);

        state = state.toggleToken(coord, Team.WHITE, Ring.class);

        assertNull(state.board().get(coord));
    }

    @Test
    void testWinnerBlackWins() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> customBoard = new HashMap<>(factory.emptyState().board());
        
        customBoard.put(new CoordinateDoubled(11, 5), new Ring(Team.WHITE));
        customBoard.put(new CoordinateDoubled(13, 5), new Ring(Team.WHITE));
        customBoard.put(new CoordinateDoubled(15, 5), new Ring(Team.WHITE));
        
        customBoard.put(new CoordinateDoubled(9, 5), new Ring(Team.BLACK));
        customBoard.put(new CoordinateDoubled(7, 5), new Ring(Team.BLACK));
        
        IState state = new State(customBoard, Team.WHITE, new ArrayList<>());
        
        assertEquals(Team.BLACK, state.winner(), "Equipe Noire n'a plus que 2 anneaux, doivent être déclarés gagnants !");
    }

    @Test
    void testAvailableMovesLogic() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> customBoard = new HashMap<>(factory.emptyState().board());
        
        Coordinate center = new CoordinateDoubled(9, 5);
        customBoard.put(center, new Ring(Team.WHITE));
        IState state = new State(customBoard, Team.WHITE, new ArrayList<>());
        
        Set<Coordinate> moves = state.availableMoves(center);
        
        assertFalse(moves.isEmpty(), "plateau vide donc pas de mvt possibles");
        assertFalse(moves.contains(center), "anneau peut pas faire de sur place");
        
        Coordinate eastMove = new CoordinateDoubled(11, 5);
        assertTrue(moves.contains(eastMove), "case à l'EST doit etre dans la liste des coups possibles");
    }
    
    @Test
    void testMoveExceptions() {
        IFactory factory = new FactoryDoubled();
        IState state = factory.testState(); // au blancs de jouer
        
        //  déplacer un Pion au lieu d'un Anneau 
        Coordinate pawnPos = new CoordinateDoubled(15, 1);
        Move movePawn = new Move(pawnPos, new CoordinateDoubled(13, 1));
        assertThrows(IllegalArgumentException.class, () -> state.move(movePawn), 
            "Déplacer un pion doit lever une IllegalArgumentException.");
        
        // déplacer l'anneau de l'adversaire
        Coordinate blackRingPos = new CoordinateDoubled(11, 5); // Anneau noir
        Move moveEnemy = new Move(blackRingPos, new CoordinateDoubled(9, 5));
        assertThrows(IllegalArgumentException.class, () -> state.move(moveEnemy), 
            "Jouer l'anneau adverse doit lever une IllegalArgumentException.");
    }
    
    @Test
    void testRemoveLineException() {
        IFactory factory = new FactoryDoubled();
        IState emptyState = factory.emptyState(); 
        
        Set<Coordinate> fakeLine = new HashSet<>();
        fakeLine.add(new CoordinateDoubled(9, 5));
        RemoveLine invalidAction = new RemoveLine(fakeLine, new CoordinateDoubled(9, 5));
        
        assertThrows(RuntimeException.class, () -> emptyState.removeLine(invalidAction), 
            "supprimer une ligne quand y en a pas doit lever RuntimeException.");
    }
    
    @Test
    void testIsInField() {
        HashMap<Coordinate, Token> board = new HashMap<>();

        Coordinate coord = new CoordinateDoubled(0, 0);
        Coordinate horsPlat = new CoordinateDoubled(100, 100);

        board.put(coord, null);

        State state = new State(board, Team.WHITE, new ArrayList<>());

        assertTrue(state.isInField(coord));       
        assertFalse(state.isInField(horsPlat));     
    }
    
    ///////// SCENARIOS DE JEU A TESTER 
    
    @Test
    void testMoveRingSautEtRetournement() throws DifferentAxisException {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> board = new HashMap<>(factory.emptyState().board());
        
        // Un anneau blanc, suivi de deux pions NOIRS 
        Coordinate start = new CoordinateDoubled(9, 5);
        Coordinate pawn1 = new CoordinateDoubled(11, 5);
        Coordinate pawn2 = new CoordinateDoubled(13, 5);
        Coordinate arrival = new CoordinateDoubled(15, 5); // Case libre après
        
        board.put(start, new Ring(Team.WHITE));
        board.put(pawn1, new Pawn(Team.BLACK));
        board.put(pawn2, new Pawn(Team.BLACK));
        
        IState state = new State(board, Team.WHITE, new ArrayList<>());
        Move move = new Move(start, arrival);
        
        IState nextState = state.move(move);
        
        assertTrue(nextState.board().get(arrival) instanceof Ring, "L'anneau doit avoir sauté en (15,5).");
        assertTrue(nextState.board().get(start) instanceof Pawn, "La case de départ (9,5) reçoit un pion blanc.");
        
        // Les deux pions survolés --> devenus BLANCS
        assertEquals(Team.WHITE, nextState.board().get(pawn1).getTeam(), "Le premier pion sauté doit être retourné en BLANC.");
        assertEquals(Team.WHITE, nextState.board().get(pawn2).getTeam(), "Le deuxième pion sauté doit être retourné en BLANC.");
    }
    
    @Test
    void testMoveRingImpossibleSauterRing() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> board = new HashMap<>(factory.emptyState().board());
        
        Coordinate start = new CoordinateDoubled(9, 5);
        Coordinate obstacleRing = new CoordinateDoubled(11, 5); // anneau sur le chemin
        Coordinate arrival = new CoordinateDoubled(13, 5);
        
        board.put(start, new Ring(Team.WHITE));
        board.put(obstacleRing, new Ring(Team.BLACK)); // bloque la route
        
        IState state = new State(board, Team.WHITE, new ArrayList<>());
        Move move = new Move(start, arrival);
        
        // en théorie le coup ne passe pas
        assertThrows(IllegalArgumentException.class, () -> state.move(move),
            "Un anneau ne doit pas pouvoir sauter par-dessus un autre anneau.");
    }

}