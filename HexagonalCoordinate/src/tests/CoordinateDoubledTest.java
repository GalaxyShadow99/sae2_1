package tests;
import coordinate.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.security.InvalidParameterException;
import java.util.List;

class CoordinateDoubledTest {

    // --- TESTS INITIAUX ---

    @Test
    void testConstructeur() {
        CoordinateDoubled c = new CoordinateDoubled(6, 8); // y=6, x=8
        assertEquals(8, c.getX()); 
        assertEquals(6, c.getY()); 
    }
    
    @Test
    void testCoordCentre() {
        assertDoesNotThrow(() -> {
            CoordinateDoubled c = new CoordinateDoubled(5, 9);
            assertEquals(9, c.getX(), "Le getter getX() doit retourner la coordonnée x (colonne)");
            assertEquals(5, c.getY(), "Le getter getY() doit retourner la coordonnée y (ligne)");
        });
    }
    
    @Test
    void testSetX() {
        CoordinateDoubled c = new CoordinateDoubled(6, 8); // y=6, x=8
        c.setX(9);
        assertEquals(9, c.getX()); 
    }
        
    @Test
    void testSetY() {
        CoordinateDoubled c = new CoordinateDoubled(6, 8); // y=6, x=8
        c.setY(9);
        assertEquals(9, c.getY()); 
    }

    // --- TESTS DES DIRECTIONS ET MODES ---

    @Test
    void testDirectionsPointyValides() {
        CoordinateDoubled centre = new CoordinateDoubled(5, 9); // [5,9]
        
        CoordinateDoubled est = (CoordinateDoubled) centre.E(Mode.POINTY);
        assertEquals(5, est.getY());
        assertEquals(11, est.getX()); // x + 2

        CoordinateDoubled ouest = (CoordinateDoubled) centre.O(Mode.POINTY);
        assertEquals(5, ouest.getY());
        assertEquals(7, ouest.getX()); // x - 2
    }

    @Test
    void testDirectionsPointyInvalides() {
        CoordinateDoubled centre = new CoordinateDoubled(5, 9);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.N(Mode.POINTY);
        }, "La direction N n'est pas acceptée en mode POINTY.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.S(Mode.POINTY);
        }, "La direction S n'est pas acceptée en mode POINTY.");
    }

    @Test
    void testDirectionsFlatValides() {
        CoordinateDoubled centre = new CoordinateDoubled(5, 9);
        
        CoordinateDoubled nord = (CoordinateDoubled) centre.N(Mode.FLAT);
        assertEquals(3, nord.getY()); // y - 2
        assertEquals(9, nord.getX());

        CoordinateDoubled sud = (CoordinateDoubled) centre.S(Mode.FLAT);
        assertEquals(7, sud.getY()); // y + 2
        assertEquals(9, sud.getX());
    }

    @Test
    void testDirectionsFlatInvalides() {
        CoordinateDoubled centre = new CoordinateDoubled(5, 9);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.E(Mode.FLAT);
        }, "La direction E n'est pas acceptée en mode FLAT.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.O(Mode.FLAT);
        }, "La direction O n'est pas acceptée en mode FLAT.");
    }

    @Test
    void testDirectionsDiagonalesCommunes() {
        CoordinateDoubled centre = new CoordinateDoubled(5, 9);
        
        CoordinateDoubled ne = (CoordinateDoubled) centre.NE(Mode.POINTY);
        assertEquals(4, ne.getY());
        assertEquals(10, ne.getX()); 
        
        CoordinateDoubled so = (CoordinateDoubled) centre.SO(Mode.FLAT);
        assertEquals(6, so.getY()); 
        assertEquals(8, so.getX()); 
    }

    // --- TESTS DE LA METHODE BETWEEN ---

    @Test
    void testBetweenMemeAxeHorizontal() throws DifferentAxisException {
        CoordinateDoubled depart = new CoordinateDoubled(5, 5);
        CoordinateDoubled arrivee = new CoordinateDoubled(5, 11); 
        
        List<Coordinate> intermediaires = depart.between(Mode.POINTY, arrivee);
        
        assertEquals(2, intermediaires.size(), "Il devrait y avoir 2 cases entre (5,5) et (5,11)");
        
        CoordinateDoubled case1 = (CoordinateDoubled) intermediaires.get(0);
        assertEquals(5, case1.getY());
        assertEquals(7, case1.getX());
        
        CoordinateDoubled case2 = (CoordinateDoubled) intermediaires.get(1);
        assertEquals(5, case2.getY());
        assertEquals(9, case2.getX());
    }

    @Test
    void testBetweenAxesDifferents() {
        CoordinateDoubled depart = new CoordinateDoubled(5, 9);
        CoordinateDoubled arrivee = new CoordinateDoubled(6, 15);
        
        assertThrows(DifferentAxisException.class, () -> {
            depart.between(Mode.POINTY, arrivee);
        }, "Doit lever une DifferentAxisException si les coordonnées ne sont pas sur le même axe.");
    }

    // --- TESTS DU CONVERTISSEUR 2D ---

    @Test
    void testTo2DCoordinate() {
        CoordinateDoubled c = new CoordinateDoubled(5, 9);
        Point p = c.to2DCoordinate();
        
        assertNotNull(p, "La conversion ne doit pas renvoyer null.");
        assertEquals(9, p.x(), "Le point doit avoir x = 9.");
        assertEquals(5, p.y(), "Le point doit avoir y = 5.");
    }
}