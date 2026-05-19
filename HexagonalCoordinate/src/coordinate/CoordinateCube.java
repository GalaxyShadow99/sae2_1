package coordinate;

public class CoordinateCube extends Coordinate{
	private int q;
	private int r;
	private int s;
	
	public CoordinateCube(int q, int r, int s) throws DifferentAxisException{
		if(q + r + s != 0) throw new DifferentAxisException("Not on the great Axis.");
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

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
