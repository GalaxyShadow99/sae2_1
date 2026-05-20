package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public abstract class Token {
	protected Team team;

	public Token(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	} 
	
	/**
	 * Retourne la représentation en caractère du token.
	 * @return String représentation du token
	 */
	public abstract String charRepr();

	/**
	 * Crée une copie du token.
	 * @return Token copie du token
	 */
	public abstract Token clone(); 
}

