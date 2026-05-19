package tests;
import coordinate.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateDoubledTest {

	@Test
	void testConstructeur() {
		CoordinateDoubled c;
		c = new CoordinateDoubled(6, 8);
		assertEquals(6, c.getX()); 
		assertEquals(8, c.getY()); 
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
	void testSetX() {
		CoordinateDoubled c;
		c = new CoordinateDoubled(6, 8);
		c.setX(9);
		assertEquals(9, c.getX()); 
	}
		
	@Test
	void testSetY() {
		CoordinateDoubled c;
		c = new CoordinateDoubled(6, 8);
		c.setY(9);
		assertEquals(9, c.getY()); 
	}

}
