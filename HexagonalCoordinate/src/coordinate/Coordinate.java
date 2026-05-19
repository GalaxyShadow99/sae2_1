package coordinate;

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
}
