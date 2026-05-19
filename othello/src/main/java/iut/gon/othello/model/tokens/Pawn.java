package iut.gon.othello.model.tokens;

public class Pawn extends Token {

	public Pawn(Team color) {
		super(team);
	}
	
	public void ChangeTeam()
	{
		this.setTeam(this.getTeam().other()); 
	}
	
	public String charRepr() {
		if (getTeam() == Team.WHITE) {
			return ".";
		}
		else {
			return "x"; 
		}
	}
	
	public Token clone() {
		return new Pawn(getTeam()); 
	}
	
	
}

