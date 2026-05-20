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
import iut.gon.othello.IA.Node;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.factory.FactoryCube;
import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.state.State;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

class IATest {

    @Test
    void testNodeStructureAndGetters() {
        IFactory factory = new FactoryDoubled();
        IState state = factory.emptyState();
        
        Coordinate from = new CoordinateDoubled(9, 5);
        Coordinate to = new CoordinateDoubled(11, 5);
        Action action = new Move(from, to);
        
        Node rootNode = new Node(state, null, null);
        Node childNode = new Node(state, rootNode, action);
        
        // check structure de l'arbre
        assertEquals(state, childNode.getEtat(), "Le getter getEtat() doit retourner l'état encapsulé.");
        assertEquals(rootNode, childNode.getParent(), "Le getter getParent() doit retourner le nœud parent correct.");
        assertEquals(action, childNode.getBefore(), "Le getter getBefore() doit retourner l'action ayant mené à cet état.");
        assertEquals(0, rootNode.getScore(), "Le score initial d'un nœud non évalué doit être de 0.");
    }

    @Test
    void testEvaluateAdvantageRings() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> board = new HashMap<>(factory.emptyState().board());
        
        // Test situation de victoire un presquen une equipe à l'avantage à voir si l'IA répond correctement
        board.put(new CoordinateDoubled(9, 5), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(11, 5), new Ring(Team.WHITE));
        
        board.put(new CoordinateDoubled(13, 5), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(15, 5), new Ring(Team.BLACK));
        board.put(new CoordinateDoubled(17, 5), new Ring(Team.BLACK));
        
        IState state = new State(board, Team.WHITE, new ArrayList<>());
        Node node = new Node(state, null, null);
        
        double scoreForWhite = node.evaluate(Team.WHITE);
        
        // (oppRings - myRings) * 1000 -> (3 - 2) * 1000 = 1000 points d'avantage 
        assertTrue(scoreForWhite > 500, "score calculé doit refléter l'avantage de l'équipe avec le moins d'anneaux.");
        assertEquals(node.getScore(), scoreForWhite, "score sauvegardé dans attribut doit correspondre à la valeur du return");
    }

    @Test
    void testEvaluateLinesDetected() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> board = new HashMap<>(factory.emptyState().board());
        
        // avantage à 0
        board.put(new CoordinateDoubled(9, 5), new Ring(Team.WHITE));
        board.put(new CoordinateDoubled(11, 5), new Ring(Team.BLACK));
        
        // présence d'une ligne à détruire
        List<Set<Coordinate>> simulatedLines = new ArrayList<>();
        Set<Coordinate> fakeLine = new HashSet<>();
        fakeLine.add(new CoordinateDoubled(1, 1));
        simulatedLines.add(fakeLine);
        
        // tour des WHITE et une ligne présente
        IState stateWithLine = new State(board, Team.WHITE, simulatedLines);
        Node node = new Node(stateWithLine, null, null);        
        double scoreWithLine = node.evaluate(Team.WHITE);
        
        IState stateWithoutLine = new State(board, Team.WHITE, new ArrayList<>());
        Node nodeWithoutLine = new Node(stateWithoutLine, null, null);
        double scoreWithoutLine = nodeWithoutLine.evaluate(Team.WHITE);
        
        assertTrue(scoreWithLine > scoreWithoutLine, "La présence d'une ligne alliée à récupérer doit augmenter le score d'évaluation.");
    }

    @Test
    void testEvaluateMobilityControl() {
        IFactory factory = new FactoryDoubled();
        HashMap<Coordinate, Token> board = new HashMap<>(factory.emptyState().board());
        
        Coordinate center = new CoordinateDoubled(9, 5);
        board.put(center, new Ring(Team.WHITE));
        
        // place un anneau noir au bord inférieur du 1er
        Coordinate edge = new CoordinateDoubled(1, 9);
        board.put(edge, new Ring(Team.BLACK));
        
        IState state = new State(board, Team.WHITE, new ArrayList<>());
        Node node = new Node(state, null, null);
        
        double scoreWhite = node.evaluate(Team.WHITE);
        double scoreBlack = node.evaluate(Team.BLACK);
        
        // anneau central doit être supérieure, à cause de la mobilité
        assertTrue(scoreWhite > scoreBlack, "L'évaluation de l'IA doit attribuer un meilleur score au joueur ayant la plus grande mobilité sur la grille.");
    }
}