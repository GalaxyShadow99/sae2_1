package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import coordinate.Point;

class PointTest {

	@Test
    void testConstructeurEtGetters() {
        Point p = new Point(5, 9);
        assertEquals(5, p.x(), "getter x() doit renvoyer 5");
        assertEquals(9, p.y(), "getter y() doit renvoyer 9");
    }

    @Test
    void testToStringPersonalise() {
        Point p = new Point(5, 9);
        assertEquals("[5, 9]", p.toString(), "la toString doit correspondre à [x, y]");
    }

    @Test
    void testEgaliteNativeDuRecord() {
        Point p1 = new Point(5, 9);
        Point p2 = new Point(5, 9);
        Point p3 = new Point(1, 2);

        assertEquals(p1, p2, "Deux points avec les mêmes coordonnées doivent être égaux");
        assertEquals(p1.hashCode(), p2.hashCode(), "Leurs hashCodes doivent être identiques");
        assertNotEquals(p1, p3, "Deux points avec des coordonnées différentes ne doivent pas être égaux");
    }
    
}
