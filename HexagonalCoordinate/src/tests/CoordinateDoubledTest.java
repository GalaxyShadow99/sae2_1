package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import coordinate.CoordinateDoubled;
import coordinate.DifferentAxisException;

class CoordinateDoubledTest {

	@Test
	void testConstructeur() {
		CoordinateDoubled c;
		try {
			c = new CoordinateDoubled(6, 8);
			assertEquals(6, c.getX()); 
			assertEquals(8, c.getY()); 

		} catch (DifferentAxisException e) {
			e.printStackTrace();
		} 
	}
	
	@Test
    void testCoordCentre() {
        assertDoesNotThrow(() -> {
            CoordinateDoubled c = new CoordinateDoubled(5, 9);
            assertEquals(5, c.getX(), "Le getter getX() doit retourner la coordonnée x (colonne)");
            assertEquals(9, c.getY(), "Le getter getY() doit retourner la coordonnée y (ligne)");
        });
    }
	
	@Test
    void testConstructeurMauvaiseCoord() {
        assertThrows(DifferentAxisException.class, () -> {
            new CoordinateDoubled(5, 8); 
        }, "Le constructeur doit lever une DifferentAxisException pour des coordonnées hexagonales incohérentes");
    }
	
	@Test
	void testSetX() {
		CoordinateDoubled c;
		try {
			c = new CoordinateDoubled(6, 8);
			c.setX(9);
			assertEquals(9, c.getX());
		} catch (DifferentAxisException e) {
			e.printStackTrace();
		} 
	}
		
	@Test
	void testSetY() {
		CoordinateDoubled c;
		try {
			c = new CoordinateDoubled(6, 8);
			c.setY(9);
			assertEquals(9, c.getY());
		} catch (DifferentAxisException e) {
			e.printStackTrace();
		} 
	}

}
