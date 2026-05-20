package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Test;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.CoordinateDoubled;
import iut.gon.coordinate.DifferentAxisException;
import iut.gon.coordinate.Mode;
import iut.gon.coordinate.Point;

class CoordinateDoubledTest {


    @Test
    void testConstructeur() {
        CoordinateDoubled c = new CoordinateDoubled(8, 6); 
        assertEquals(8, c.getX()); 
        assertEquals(6, c.getY()); 
    }
    
    @Test
    void testCoordCentre() {
        assertDoesNotThrow(() -> {
            CoordinateDoubled c = new CoordinateDoubled(9, 5);
            assertEquals(9, c.getX(), "Le getter getX() doit retourner la coordonnée x (colonne)");
            assertEquals(5, c.getY(), "Le getter getY() doit retourner la coordonnée y (ligne)");
        });
    }


    @Test
    void testDirectionsPointyValides() {
        CoordinateDoubled centre = new CoordinateDoubled(9, 5); 
        
        CoordinateDoubled est = (CoordinateDoubled) centre.E(Mode.POINTY);
        assertEquals(11, est.getX());
        assertEquals(5, est.getY()); 

        CoordinateDoubled ouest = (CoordinateDoubled) centre.O(Mode.POINTY);
        assertEquals(7, ouest.getX());
        assertEquals(5, ouest.getY()); 
    }

    @Test
    void testDirectionsPointyInvalides() {
        CoordinateDoubled centre = new CoordinateDoubled(9, 5);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.N(Mode.POINTY);
        }, "La direction N n'est pas acceptée en mode POINTY.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.S(Mode.POINTY);
        }, "La direction S n'est pas acceptée en mode POINTY.");
    }

    @Test
    void testDirectionsFlatValides() {
        CoordinateDoubled centre = new CoordinateDoubled(9, 5);
        
        CoordinateDoubled nord = (CoordinateDoubled) centre.N(Mode.FLAT);
        assertEquals(9, nord.getX());
        assertEquals(3, nord.getY());

        CoordinateDoubled sud = (CoordinateDoubled) centre.S(Mode.FLAT);
        assertEquals(9, sud.getX());
        assertEquals(7, sud.getY());
    }

    @Test
    void testDirectionsFlatInvalides() {
        CoordinateDoubled centre = new CoordinateDoubled(9, 5);
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.E(Mode.FLAT);
        }, "La direction E n'est pas acceptée en mode FLAT.");
        
        assertThrows(InvalidParameterException.class, () -> {
            centre.O(Mode.FLAT);
        }, "La direction O n'est pas acceptée en mode FLAT.");
    }

    @Test
    void testDirectionsDiagonalesCommunes() {
        CoordinateDoubled centre = new CoordinateDoubled(9, 5);
        
        CoordinateDoubled ne = (CoordinateDoubled) centre.NE(Mode.POINTY);
        assertEquals(10, ne.getX());
        assertEquals(4, ne.getY()); 
        
        CoordinateDoubled so = (CoordinateDoubled) centre.SO(Mode.FLAT);
        assertEquals(8, so.getX()); 
        assertEquals(6, so.getY()); 
    }


    @Test
    void testBetweenMemeAxeHorizontal() throws DifferentAxisException {
        CoordinateDoubled depart = new CoordinateDoubled(5, 5);
        CoordinateDoubled arrivee = new CoordinateDoubled(11, 5); 
        
        List<Coordinate> intermediaires = depart.between(Mode.POINTY, arrivee);
        
        assertEquals(2, intermediaires.size(), "Il devrait y avoir 2 cases entre (5,5) et (11,5)");
        
        CoordinateDoubled case1 = (CoordinateDoubled) intermediaires.get(0);
        assertEquals(7, case1.getX());
        assertEquals(5, case1.getY());
        
        CoordinateDoubled case2 = (CoordinateDoubled) intermediaires.get(1);
        assertEquals(9, case2.getX());
        assertEquals(5, case2.getY());
    }

    @Test
    void testBetweenAxesDifferents() {
        CoordinateDoubled depart = new CoordinateDoubled(9, 5);
        CoordinateDoubled arrivee = new CoordinateDoubled(9, 3);
        
        assertThrows(DifferentAxisException.class, () -> {
            depart.between(Mode.POINTY, arrivee);
        }, "Doit lever une DifferentAxisException si les coordonnées ne sont pas sur le même axe.");
    }


    @Test
    void testTo2DCoordinate() {
        CoordinateDoubled c = new CoordinateDoubled(9, 5);
        Point p = c.to2DCoordinate();
        
        assertNotNull(p, "La conversion ne doit pas renvoyer null.");
        assertEquals(9, p.x(), "Le point doit avoir x = 9.");
        assertEquals(5, p.y(), "Le point doit avoir y = 5.");
    }
}