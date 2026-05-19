package coordinate;

import java.security.InvalidParameterException;
import java.util.List;

public abstract class Coordinate {
	public Point to2DCoordinate() {
		return Point;
	}
	
	public Coordinate toDir(Mode mode, Direction direction){
		return Coordinate;
	}
	
	public List<Coordinate> getNeighbors(Mode mode) {
		return List<Coordinate>;
	}
	
	public List<Coordinate> beetween(Mode mode, Coordinate to){
		return List<Coordinate>;
	}
	
	public Coordinate NO(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.getQ(),this.getR(), this.getS()+1);
	}
	
	public Coordinate NE(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.getQ()+1,this.getR(), this.getS());
	}
	
	public Coordinate E(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.getX()+1,this.getY());
	}
	
	public Coordinate O(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.getX()-1,this.getY());
	}
	
	public Coordinate N(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.getX(),this.getY()+1);
	}
	
	public Coordinate S(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.getX(),this.getY()-1);
	}
	
	public Coordinate SO(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.getQ()-1,this.getR(), this.getS());
	}
	
	public Coordinate SE(Mode mode) {s
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.getQ(),this.getR(), this.getS()-1);
	}
	
	
	public abstract int getX();
	public abstract int getY();
	public abstract int getQ();
	public abstract int getR();
	public abstract int getS();
	
}
