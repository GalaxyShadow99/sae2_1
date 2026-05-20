package iut.gon.othello.model.tokens;

import iut.gon.othello.model.Team;

public class Pawn extends Token {

	public Pawn(Team color) {
		super(color);
	}
	
	public void ChangeTeam()
	{
		this.setTeam(this.getTeam().other()); 
	}
	
	public String charRepr() {
		if (getTeam() == Team.WHITE) {
			return "♙";
		}
		else {
			return "♟"; 
		}
	}
	
	public Token clone() {
		return new Pawn(getTeam()); 
	}
	
	
}

