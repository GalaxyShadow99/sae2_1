package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import iut.gon.othello.model.Team;

class TeamTest {

    @Test
    void testTeamOther() {
        // pas grand chose à tester mais chaque classe doit l'être ....
        assertEquals(Team.BLACK, Team.WHITE.other(), "L'opposé de WHITE doit être BLACK.");
        assertEquals(Team.WHITE, Team.BLACK.other(), "L'opposé de BLACK doit être WHITE.");
    }
}