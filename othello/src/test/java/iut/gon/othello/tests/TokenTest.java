package iut.gon.othello.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import iut.gon.othello.model.Team;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

class TokenTest {

    //TESTS DE PAWN

    @Test
    void testConstructeurPawn() {
        Token token = new Pawn(Team.BLACK);

        assertEquals(Team.BLACK, token.getTeam());
    }

    @Test
    void testGetTeamPawn() {
        Token token = new Pawn(Team.WHITE);

        assertEquals(Team.WHITE, token.getTeam(),
                "Le getter doit retourner la bonne équipe.");
    }

    @Test
    void testSetTeamPawn() {
        Token token = new Pawn(Team.BLACK);

        token.setTeam(Team.WHITE);

        assertEquals(Team.WHITE, token.getTeam(),
                "Le setter doit modifier l'équipe.");
    }

    @Test
    void testCharReprPawnWhite() {
        Token token = new Pawn(Team.WHITE);
        assertNotNull(token.charRepr(), "La représentation d'un pion blanc ne doit pas être nulle.");
        assertFalse(token.charRepr().isEmpty(), "La représentation d'un pion blanc ne doit pas être vide.");
    }

    @Test
    void testCharReprPawnBlack() {
        Token token = new Pawn(Team.BLACK);
        Token whiteToken = new Pawn(Team.WHITE);
        assertNotEquals(whiteToken.charRepr(), token.charRepr(), 
                "les pions doivent avoir des repr différentes");
    }

    @Test
    void testClonePawn() {
        Token token = new Pawn(Team.WHITE);

        Token clone = token.clone();

        assertNotNull(clone, "Le clone ne doit pas être null.");
        assertNotSame(token, clone, "Le clone doit être une nouvelle instance.");
        assertEquals(token.getTeam(), clone.getTeam(),
                "Le clone doit conserver la même équipe.");
        assertEquals(token.charRepr(), clone.charRepr(),
                "Le clone doit conserver la même représentation.");
    }

    @Test
    void testChangeTeamPawn() {
        Pawn pawn = new Pawn(Team.WHITE);

        pawn.ChangeTeam();

        assertEquals(Team.BLACK, pawn.getTeam(),
                "ChangeTeam() doit inverser l'équipe.");
    }

    //TESTS DE RING 

    @Test
    void testConstructeurRing() {
        Token token = new Ring(Team.BLACK);

        assertEquals(Team.BLACK, token.getTeam());
    }

    @Test
    void testGetTeamRing() {
        Token token = new Ring(Team.WHITE);

        assertEquals(Team.WHITE, token.getTeam(),
                "Le getter doit retour    //TESTS DE PAWN\n"
                + "ner la bonne équipe.");
    }

    @Test
    void testSetTeamRing() {
        Token token = new Ring(Team.BLACK);

        token.setTeam(Team.WHITE);

        assertEquals(Team.WHITE, token.getTeam(),
                "Le setter doit modifier l'équipe.");
    }

    @Test
    void testCharReprRingWhite() {
        Token token = new Ring(Team.WHITE);
        assertNotNull(token.charRepr());
        assertFalse(token.charRepr().isEmpty());
    }

    @Test
    void testCharReprRingBlack() {
        Token token = new Ring(Team.BLACK);
        Token whiteRing = new Ring(Team.WHITE);
        assertNotEquals(whiteRing.charRepr(), token.charRepr(), 
                " anneau noir et anneau blanc --> des représentations différentes.");
    }

    @Test
    void testCloneRing() {
        Token token = new Ring(Team.BLACK);

        Token clone = token.clone();

        assertNotNull(clone, "Le clone ne doit pas être null.");
        assertNotSame(token, clone, "Le clone doit être une nouvelle instance.");
        assertEquals(token.getTeam(), clone.getTeam(),
                "Le clone doit conserver la même équipe.");
        assertEquals(token.charRepr(), clone.charRepr(),
                "Le clone doit conserver la même représentation.");
    }
}