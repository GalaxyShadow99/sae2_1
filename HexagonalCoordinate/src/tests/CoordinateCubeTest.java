package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import coordinate.CoordinateCube;
import coordinate.Mode;
import coordinate.DifferentAxisException;
import coordinate.Coordinate;

import java.security.InvalidParameterException;
import java.util.List;

public class CoordinateCubeTest {


    @Test
    void testConstructeurCentreValide() {
        assertDoesNotThrow(() -> {
            CoordinateCube c = new CoordinateCube(0, 0, 0);
            assertEquals(0, c.getQ());
            assertEquals(0, c.getR());
            assertEquals(0, c.getS());
        }, "Le centre [0, 0, 0] doit être une coordonnée valide.");
    }

    @Test
    void testConstructeurVoisinValide() {
        assertDoesNotThrow(() -> {
            CoordinateCube c = new CoordinateCube(1, 0, -1);
            assertEquals(1, c.getQ());
            assertEquals(0, c.getR());
            assertEquals(-1, c.getS());
        }, "Le voisin de droite [1, 0, -1] doit être valide.");
    }

    @Test
    void testConstructeurInvalideLeveException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CoordinateCube(1, 1, 1);
        }, "Une coordonnée où q+r+s != 0 doit lever une IllegalArgumentException.");
    }
    
    @Test
    void testConstructeurInvalideAvecNegatifs() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CoordinateCube(-2, 1, 0);
        }, "Une coordonnée négative dont la somme n'est pas 0 doit lever une IllegalArgumentException.");
    }

    
    @Test
    void testDirectionsPointyValides() {
        CoordinateCube centre = new CoordinateCube(0, 0, 0);
        
        CoordinateCube est = (CoordinateCube) centre.E(Mode.POINTY);
        assertEquals(1, est.getQ());
        assertEquals(0, est.getR());
        assertEquals(-1, est.getS());

        CoordinateCube ouest = (CoordinateCube) centre.O(Mode.POINTY);
        assertEquals(-1, ouest.getQ());
        assertEquals(0, ouest.getR());
        assertEquals(1, ouest.getS());
    }

    @Test
    void testDirectionsPointyInvalides() {
        CoordinateCube centre = new CoordinateCube(0, 0, 0);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.N(Mode.POINTY);
        }, "La direction N n'est pas acceptée en mode POINTY.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.S(Mode.POINTY);
        }, "La direction S n'est pas acceptée en mode POINTY.");
    }

    @Test
    void testDirectionsFlatValides() {
        CoordinateCube centre = new CoordinateCube(0, 0, 0);
        
        CoordinateCube nord = (CoordinateCube) centre.N(Mode.FLAT);
        assertEquals(0, nord.getQ());
        assertEquals(-1, nord.getR());
        assertEquals(1, nord.getS());

        CoordinateCube sud = (CoordinateCube) centre.S(Mode.FLAT);
        assertEquals(0, sud.getQ());
        assertEquals(1, sud.getR());
        assertEquals(-1, sud.getS());
    }

    @Test
    void testDirectionsFlatInvalides() {
        CoordinateCube centre = new CoordinateCube(0, 0, 0);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.E(Mode.FLAT);
        }, "La direction E n'est pas acceptée en mode FLAT.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.O(Mode.FLAT);
        }, "La direction O n'est pas acceptée en mode FLAT.");
    }

    @Test
    void testDirectionsDiagonalesCommunes() {
        CoordinateCube centre = new CoordinateCube(0, 0, 0);
        
        CoordinateCube ne = (CoordinateCube) centre.NE(Mode.POINTY);
        assertEquals(1, ne.getQ());
        assertEquals(-1, ne.getR());
        assertEquals(0, ne.getS());
        
        CoordinateCube so = (CoordinateCube) centre.SO(Mode.FLAT);
        assertEquals(-1, so.getQ());
        assertEquals(1, so.getR());
        assertEquals(0, so.getS());
    }


    @Test
    void testBetweenMemeAxe() throws DifferentAxisException {
        CoordinateCube depart = new CoordinateCube(0, 0, 0);
        CoordinateCube arrivee = new CoordinateCube(0, 3, -3); // Même axe q (q=0)
        
        List<Coordinate> intermediaires = depart.between(Mode.POINTY, arrivee);
        
        assertEquals(2, intermediaires.size(), "Il devrait y avoir 2 cases entre [0,0,0] et [0,3,-3]");
        
        CoordinateCube case1 = (CoordinateCube) intermediaires.get(0);
        assertEquals(0, case1.getQ());
        assertEquals(1, case1.getR());
        assertEquals(-1, case1.getS());
        
        CoordinateCube case2 = (CoordinateCube) intermediaires.get(1);
        assertEquals(0, case2.getQ());
        assertEquals(2, case2.getR());
        assertEquals(-2, case2.getS());
    }

    @Test
    void testBetweenAxesDifferents() {
        CoordinateCube depart = new CoordinateCube(0, 0, 0);
        CoordinateCube arrivee = new CoordinateCube(1, 1, -2);
        
        assertThrows(DifferentAxisException.class, () -> {
            depart.between(Mode.POINTY, arrivee);
        }, "Doit lever une DifferentAxisException si les coordonnées ne partagent aucun axe.");
    }
}