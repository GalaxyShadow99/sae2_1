package coordinate;

public enum Direction {
	NO,
	N,
	NE,
	E,
	SE,
	S,
	SO,
	O;
	
	public Direction opposite() {
		switch (this) {
		case Direction.NO :
			return Direction.SE;
		case Direction.N :
			return Direction.S;
		case Direction.NE :
			return Direction.SO;
		case Direction.E :
			return Direction.O;
		case Direction.SE :
			return Direction.NO;
		case Direction.S :
			return Direction.N;
		case Direction.SO :
			return Direction.NE;
		case Direction.O :
			return Direction.E;
		default :
			return null;
		}
	}
}
