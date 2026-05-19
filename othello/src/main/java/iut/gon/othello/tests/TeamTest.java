package iut.gon.othello.model;
import Team;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    @Test
    public void testOther() {
        assertEquals(Team.BLACK, Team.WHITE.other(), "L'opposé de WHITE doit être BLACK");
        assertEquals(Team.WHITE, Team.BLACK.other(), "L'opposé de BLACK doit être WHITE");
    }
    
    @Test
    public void testGetColor() {
        assertEquals(Color.BLACK, Team.BLACK.getColor(), "La couleur de BLACK doit être JavaFX Color.BLACK");
        assertEquals(Color.WHITE, Team.WHITE.getColor(), "La couleur de WHITE doit être JavaFX Color.WHITE");
    }

    @Test
    public void testEnumValues() {
        assertEquals(2, Team.values().length, "Il ne doit y avoir que deux équipes dans l'enum");
    }
}