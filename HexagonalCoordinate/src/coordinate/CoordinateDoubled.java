package coordinate;

public class CoordinateDoubled extends Coordinate{
	
	private int x; 
	private int y; 
	
	
	public CoordinateDoubled(int x, int y) throws DifferentAxisException {
		
		if (Math.abs(x % 2) != Math.abs(y % 2)) {
	        throw new DifferentAxisException(
	            "Coordonnée invalide [" + y + ", " + x + "] : " +
	            "La ligne et la colonne doivent avoir la même parité sur ce plateau hexagonal."
	        );
	    }
		this.x = x;
		this.y = y; 
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	@Override
	public int getQ() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getR() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getS() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
