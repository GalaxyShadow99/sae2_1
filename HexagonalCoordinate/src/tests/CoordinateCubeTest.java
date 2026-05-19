package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import coordinate.CoordinateCube;

public class CoordinateCubeTest {

	
	@Test
    void testConstructeurCentreValide() {
        // D'après 3.5.2, la case centrale [0,0,0] est valide (0+0+0 = 0)
        assertDoesNotThrow(() -> {
            CoordinateCube c = new CoordinateCube(0, 0, 0);
            assertEquals(0, c.getQ());
            assertEquals(0, c.getR());
            assertEquals(0, c.getS());
        }, "Le centre [0, 0, 0] doit être une coordonnée valide.");
    }

    @Test
    void testConstructeurVoisinValide() {
        // D'après 3.5.2, le voisin de droite d'une case [q,r,s] est [q+1, r, s-1]
        assertDoesNotThrow(() -> {
            CoordinateCube c = new CoordinateCube(1, 0, -1);
            assertEquals(1, c.getQ());
            assertEquals(0, c.getR());
            assertEquals(-1, c.getS());
        }, "Le voisin de droite [1, 0, -1] doit être valide.");
    }

    @Test
    void testConstructeurInvalideLeveException() {
        // Si la somme n'est pas égale à 0 (ex: 1 + 1 + 1 = 3) DOIT lever une IllegalArgumentException (selon 3.5.1)
        assertThrows(IllegalArgumentException.class, () -> {
            new CoordinateCube(1, 1, 1);
        }, "Une coordonnée où q+r+s != 0 doit lever une IllegalArgumentException.");
    }
    
    @Test
    void testConstructeurInvalideAvecNegatifs() {
        // nombres négatifs au cas où
        assertThrows(IllegalArgumentException.class, () -> {
            new CoordinateCube(-2, 1, 0);
        }, "Une coordonnée négative dont la somme n'est pas 0 doit lever une IllegalArgumentException.");
    }
    
}
