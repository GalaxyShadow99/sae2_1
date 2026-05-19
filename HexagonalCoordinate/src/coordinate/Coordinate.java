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
		return new CoordinateCube(this.q,this.r, this.s+1);
	}
	
	public Coordinate NE(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.q+1,this.r, this.s);
	}
	
	public Coordinate E(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.x+1,this.y);
	}
	
	public Coordinate O(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.x-1,this.y);
	}
	
	public Coordinate N(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.x,this.y+1);
	}
	
	public Coordinate S(Mode mode) {
		if (mode.equals(Mode.POINTY)){
			throw new InvalidParameterException();
		}
		return new CoordinateDoubled(this.x,this.y-1);
	}
	
	public Coordinate SO(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.q-1,this.r, this.s);
	}
	
	public Coordinate SE(Mode mode) {
		if (mode.equals(Mode.FLAT)){
			throw new InvalidParameterException();
		}
		return new CoordinateCube(this.q,this.r, this.s-1);
	}
	
}
