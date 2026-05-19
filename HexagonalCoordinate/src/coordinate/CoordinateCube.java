package coordinate;

public class CoordinateCube extends Coordinate{
	private int q;
	private int r;
	private int s;
	
	public CoordinateCube(int q, int r, int s) throws IllegalArgumentException{
		if(q + r + s != 0) throw new IllegalArgumentException();
		this.q = q;
		this.r = r;
		this.s = s;
	}

	public int getQ() {
		return q;
	}

	public int getR() {
		return r;
	}

	public int getS() {
		return s;
	}
}
