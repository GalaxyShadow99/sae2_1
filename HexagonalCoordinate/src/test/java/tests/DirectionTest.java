package tests;
import iut.gon.coordinate.Direction;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DirectionTest {

	@Test
	void testOpposite() {
		Direction d1 = Direction.NO;
		assertTrue(d1.opposite().equals(Direction.SE));
		Direction d2 = Direction.N;
		assertTrue(d2.opposite().equals(Direction.S));
		Direction d3 = Direction.NE;
		assertTrue(d3.opposite().equals(Direction.SO));
		Direction d4 = Direction.E;
		assertTrue(d4.opposite().equals(Direction.O));
		Direction d5 = Direction.SE;
		assertTrue(d5.opposite().equals(Direction.NO));
		Direction d6 = Direction.S;
		assertTrue(d6.opposite().equals(Direction.N));
		Direction d7 = Direction.SO;
		assertTrue(d7.opposite().equals(Direction.NE));
		Direction d8 = Direction.O;
		assertTrue(d8.opposite().equals(Direction.E));
	}

}
