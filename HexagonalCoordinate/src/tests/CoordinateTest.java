package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import java.util.List;

import org.junit.jupiter.api.Test;

import coordinate.Coordinate;
import coordinate.DifferentAxisException;
import coordinate.Direction;
import coordinate.Mode;
import coordinate.Point;



// A FAIRE UNE FOIS LE PROJET RELATIVEMENT TERMINE !




//  On crée une fausse classe , toute simple juste pour pouvoir tester l'abstrait
class FausseCoordinate extends Coordinate {
    @Override
    public Point to2DCoordinate() { return null; }
    @Override
    public Coordinate toDir(Mode mode, Direction direction) { return this; }
    @Override
    public List<Coordinate> getNeighbors(Mode mode) { return null; }
    
    public List<Coordinate> between(Mode mode, Coordinate to) throws DifferentAxisException { return null; }
	@Override
	public Coordinate NO(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate NE(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate E(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate O(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate N(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate S(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate SO(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Coordinate SE(Mode mode) {
		// TODO Auto-generated method stub
		return null;
	}
	
    
    
}

// Ici donc nos tests basiques sur la classe Abstraite (sur un objet FausseCoordinate plus précisément...)
class CoordinateTest {

    @Test
    void testNordEnModePointyLeveException() {
        Coordinate c = new FausseCoordinate();
        assertThrows(InvalidParameterException.class, () -> {
            c.N(Mode.POINTY); // Nord n'existe si hexagone pointe vers le haut
        });
    }

    @Test
    void testSudEnModePointyLeveException() {
        Coordinate c = new FausseCoordinate();
        assertThrows(InvalidParameterException.class, () -> {
            c.S(Mode.POINTY); //  Sud existe pas non plus en POINTY
        });
    }

    @Test
    void testEstEnModeFlatLeveException() {
        Coordinate c = new FausseCoordinate();
        assertThrows(InvalidParameterException.class, () -> {
            c.E(Mode.FLAT); // L'Est pur n'existe pas si côté plat vertical
        });
    }

    @Test
    void testOuestEnModeFlatLeveException() {
        Coordinate c = new FausseCoordinate();
        assertThrows(InvalidParameterException.class, () -> {
            c.O(Mode.FLAT); // L'Ouest non plus FLAT
        });
    }

    @Test
    void testEstEnModePointyNeLevePasException() {
        Coordinate c = new FausseCoordinate();
        assertDoesNotThrow(() -> {
            c.E(Mode.POINTY);
        });
    }

    @Test
    void testNordEnModeFlatNeLevePasException() {
        Coordinate c = new FausseCoordinate();
        assertDoesNotThrow(() -> {
            c.N(Mode.FLAT);
        });
    }

    @Test
    void testDiagonalesNeLeventJamaisException() {
        Coordinate c = new FausseCoordinate();
        assertDoesNotThrow(() -> {
            // Test de quelques diagonales pour s'assurer que ça passe dans les deux modes
            c.NO(Mode.POINTY);
            c.SE(Mode.POINTY);
            c.NE(Mode.FLAT);
            c.SO(Mode.FLAT);
        });
    }
}