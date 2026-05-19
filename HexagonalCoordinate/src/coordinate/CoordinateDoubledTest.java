package coordinate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateDoubledTest {

	@Test
	void testConstructeur() {
		CoordinateDoubled c = new CoordinateDoubled(7, 8); 
		
		assertEquals(7, c.getX()); 
		assertEquals(8, c.getY()); 
	}
	
	@Test
	void testSetX() {
		CoordinateDoubled c = new CoordinateDoubled(7, 8); 
		c.setX(9);
		
		assertEquals(9, c.getX());
	}
	
	
	@Test
	void testSetY() {
		CoordinateDoubled c = new CoordinateDoubled(7, 8); 
		c.setY(9);
		
		assertEquals(9, c.getY());
	}

}
