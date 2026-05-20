package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Pawn extends Token {

	/**
	 * Crée un pion de l'équipe donnée.
	 * @param color équipe du pion (Team)
	 */
	public Pawn(Team color) {
		super(color);
	}
	
	/**
	 * Change l'équipe du pion (retourne le pion).
	 */
	public void ChangeTeam()
	{
		this.setTeam(this.getTeam().other()); 
	}
	
	/**
	 * Retourne la représentation graphique du pion (selon son équipe).
	 * @return String représentation du pion
	 */
	public String charRepr() {
		if (getTeam() == Team.WHITE) {
			return "♙";
		}
		else {
			return "♟"; 
		}
	}
	
	/**
	 * Crée une copie du pion.
	 * @return Token copie du pion
	 */
	public Token clone() {
		return new Pawn(getTeam()); 
	}
	
	
}

