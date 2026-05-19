package tests;
import coordinate.CoordinateCube;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CoordinateCubeTest {

    @Test
    public void testConstructeurCoordonneesValides(){
        CoordinateCube coord = new CoordinateCube(1, -1, 0);

        assertEquals(1, coord.getQ());
        assertEquals(-1, coord.getR());
        assertEquals(0, coord.getS());
    }

    @Test
    public void testConstructeurDeclencheErreur(){
        assertThrows(IllegalArgumentException.class, () -> {
            new CoordinateCube(1, 1, 1);
        });
    }
}