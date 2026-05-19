package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Ring extends Token {

	public Ring(Team color) {
		super(color);
	}
	
	public String charRepr() {
		if (getTeam() == Team.WHITE) {
			return "o";
		}
		else {
			return "O"; 
		}
	}
	
	public Token clone() {
		return new Ring(getTeam()); 
	}

}

