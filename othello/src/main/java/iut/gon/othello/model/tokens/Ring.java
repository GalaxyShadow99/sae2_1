package iut.gon.othello.model.tokens;

public class Ring extends Token {

	public Ring(Team color) {
		super(team);
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

