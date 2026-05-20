package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Ring extends Token {

	/**
	 * Crée un anneau de l'équipe donnée.
	 * @param color équipe de l'anneau (Team)
	 */
	public Ring(Team color) {
		super(color);
	}
	
	/**
	 * Retourne la représentation graphique de l'anneau selon son équipe.
	 * @return String représentation de l'anneau
	 */
	public String charRepr() {
		if (getTeam() == Team.WHITE) {
			return "◯";
		}
		else {
			return "●"; 
		}
	}
	
	/**
	 * Crée une copie de l'anneau.
	 * @return Token copie de l'anneau
	 */
	public Token clone() {
		return new Ring(getTeam()); 
	}

}

