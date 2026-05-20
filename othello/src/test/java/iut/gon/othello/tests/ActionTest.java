package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.CoordinateDoubled;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;

class ActionTest {

    //TESTS DE MOVE

    @Test
    void testConstructeurMove() {
        Coordinate from = new CoordinateDoubled(2, 4);
        Coordinate to = new CoordinateDoubled(6, 8);

        Move move = new Move(from, to);

        assertEquals(from, move.getFrom());
        assertEquals(to, move.getTo());
    }

    @Test
    void testGetFrom() {
        Coordinate from = new CoordinateDoubled(1, 3);
        Coordinate to = new CoordinateDoubled(5, 7);

        Move move = new Move(from, to);

        assertEquals(from, move.getFrom(),
                "Le getter getFrom() doit retourner la bonne coordonnée de départ.");
    }

    @Test
    void testSetFrom() {
        Coordinate from = new CoordinateDoubled(1, 3);
        Coordinate to = new CoordinateDoubled(5, 7);

        Move move = new Move(from, to);

        Coordinate nouveauFrom = new CoordinateDoubled(9, 11);
        move.setFrom(nouveauFrom);

        assertEquals(nouveauFrom, move.getFrom(),
                "Le setter setFrom() doit modifier la coordonnée de départ.");
    }

    @Test
    void testGetTo() {
        Coordinate from = new CoordinateDoubled(1, 3);
        Coordinate to = new CoordinateDoubled(5, 7);

        Move move = new Move(from, to);

        assertEquals(to, move.getTo(),
                "Le getter getTo() doit retourner la bonne coordonnée d'arrivée.");
    }

    @Test
    void testSetTo() {
        Coordinate from = new CoordinateDoubled(1, 3);
        Coordinate to = new CoordinateDoubled(5, 7);

        Move move = new Move(from, to);

        Coordinate nouveauTo = new CoordinateDoubled(13, 15);
        move.setTo(nouveauTo);

        assertEquals(nouveauTo, move.getTo(),
                "Le setter setTo() doit modifier la coordonnée d'arrivée.");
    }

    //TESTS DE REMOVELINE

    @Test
    void testConstructeurRemoveLine() {
        Set<Coordinate> ligne = new HashSet<>();
        ligne.add(new CoordinateDoubled(1, 1));
        ligne.add(new CoordinateDoubled(1, 3));

        Coordinate ring = new CoordinateDoubled(5, 5);

        RemoveLine removeLine = new RemoveLine(ligne, ring);

        assertEquals(ligne, removeLine.getLine());
        assertEquals(ring, removeLine.getRing());
    }

    @Test
    void testGetLine() {
        Set<Coordinate> ligne = new HashSet<>();
        ligne.add(new CoordinateDoubled(2, 2));

        Coordinate ring = new CoordinateDoubled(4, 4);

        RemoveLine removeLine = new RemoveLine(ligne, ring);

        assertEquals(ligne, removeLine.getLine(),
                "Le getter getLine() doit retourner la ligne.");
    }

    @Test
    void testSetLine() {
        Set<Coordinate> ligne = new HashSet<>();
        ligne.add(new CoordinateDoubled(2, 2));

        Coordinate ring = new CoordinateDoubled(4, 4);

        RemoveLine removeLine = new RemoveLine(ligne, ring);

        Set<Coordinate> nouvelleLigne = new HashSet<>();
        nouvelleLigne.add(new CoordinateDoubled(8, 8));

        removeLine.setLine(nouvelleLigne);

        assertEquals(nouvelleLigne, removeLine.getLine(),
                "Le setter setLine() doit modifier la ligne.");
    }

    @Test
    void testGetRing() {
        Set<Coordinate> ligne = new HashSet<>();
        ligne.add(new CoordinateDoubled(2, 2));

        Coordinate ring = new CoordinateDoubled(4, 4);

        RemoveLine removeLine = new RemoveLine(ligne, ring);

        assertEquals(ring, removeLine.getRing(),
                "Le getter getRing() doit retourner le ring.");
    }

    @Test
    void testSetRing() {
        Set<Coordinate> ligne = new HashSet<>();
        ligne.add(new CoordinateDoubled(2, 2));

        Coordinate ring = new CoordinateDoubled(4, 4);

        RemoveLine removeLine = new RemoveLine(ligne, ring);

        Coordinate nouveauRing = new CoordinateDoubled(10, 10);

        removeLine.setRing(nouveauRing);

        assertEquals(nouveauRing, removeLine.getRing(),
                "Le setter setRing() doit modifier le ring.");
    }
    
}
