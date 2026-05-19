package coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public abstract class Coordinate {
	public Point to2DCoordinate() {
		return new Point(getX(), getY());
	}
	
	public Coordinate toDir(Mode mode, Direction direction){
		return null;
	}
	
	public List<Coordinate> getNeighbors(Mode mode) {
		return null;
	}
	
	public List<Coordinate> beetween(Mode mode, Coordinate to) throws DifferentAxisException{
		List<Coordinate> resultat = new ArrayList<>();
		if (mode.equals(Mode.FLAT)) {
			if ((this.getX() != to.getX()) || (this.getY() != to.getY())) {
				throw new DifferentAxisException();
			}
		}else {
			if ((this.getQ() != to.getQ()) || (this.getR() != to.getR()) || (this.getS() != to.getS())) {
				throw new DifferentAxisException();
			}
		}
		
		
		int pasQ = 0; if (this.getQ() < to.getQ()) pasQ = 1; else if (this.getQ() > to.getQ()) pasQ = -1;
		int pasR = 0; if (this.getR() < to.getR()) pasR = 1; else if (this.getR() > to.getR()) pasR = -1;
		int pasS = 0; if (this.getS() < to.getS()) pasS = 1; else if (this.getS() > to.getS()) pasS = -1;

		int qActuel = this.getQ() + pasQ;
		int rActuel = this.getR() + pasR;
		int sActuel = this.getS() + pasS;

		while (qActuel != to.getQ() || rActuel != to.getR() || sActuel != to.getS()) {
			resultat.add(new CoordinateCube(qActuel, rActuel, sActuel));
			qActuel += pasQ;
			rActuel += pasR;
			sActuel += pasS;
		}
		return resultat;
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
	
	public Coordinate SE(Mode mode) {
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
