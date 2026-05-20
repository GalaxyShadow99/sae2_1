package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Test;

import coordinate.*;



class CoordinateTest {
	@Test
	void testToDir() {
        Coordinate coord = new CoordinateCube(0, 0, 0); 


        assertNotNull(coord.toDir(Mode.FLAT, Direction.NE), "La direction NE devrait retourner une coordonnée");
        
        assertThrows(InvalidParameterException.class, () -> coord.toDir(Mode.POINTY, Direction.N),
            "Doit lever une InvalidParameterException si la direction n'accepte pas le mode fourni");
    }

	@Test
    void testGetNeighbors() {
        // Case centrale de la grille Doubled
        Coordinate coord = new CoordinateDoubled(5, 9);
        Mode mode = Mode.FLAT;

        List<Coordinate> neighbors = coord.getNeighbors(mode);
        
        // Le sujet impose d'avoir strictement 6 voisins renvoyés !
        assertEquals(6, neighbors.size(), "La liste doit contenir exactement 6 voisins pour ce mode.");
    }
}