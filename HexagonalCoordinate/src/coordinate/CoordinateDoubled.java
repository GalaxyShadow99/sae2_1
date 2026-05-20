package coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoordinateDoubled extends Coordinate {
    private int y; 
    private int x; 
    
    public CoordinateDoubled(int y, int x) {
        this.y = y;
        this.x = x; 
    }



    public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	@Override
    public Coordinate NO(Mode mode) {
        return new CoordinateDoubled(this.y - 1, this.x - 1);
    }

    @Override
    public Coordinate NE(Mode mode) {
        return new CoordinateDoubled(this.y - 1, this.x + 1);
    }

    @Override
    public Coordinate E(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("E n'accepte pas le mode FLAT");
        return new CoordinateDoubled(this.y, this.x + 2); 
    }

    @Override
    public Coordinate O(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("O n'accepte pas le mode FLAT");
        return new CoordinateDoubled(this.y, this.x - 2);
    }

    @Override
    public Coordinate N(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("N n'accepte pas le mode POINTY");
        return new CoordinateDoubled(this.y - 2, this.x); 
    }

    @Override
    public Coordinate S(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("S n'accepte pas le mode POINTY");
        return new CoordinateDoubled(this.y + 2, this.x);
    }

    @Override
    public Coordinate SO(Mode mode) {
        return new CoordinateDoubled(this.y + 1, this.x - 1);
    }

    @Override
    public Coordinate SE(Mode mode) {
        return new CoordinateDoubled(this.y + 1, this.x + 1);
    }

    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) throws DifferentAxisException {
        if (!(to instanceof CoordinateDoubled)) throw new InvalidParameterException();
        CoordinateDoubled target = (CoordinateDoubled) to;

        if (this.x != target.x && this.y != target.y) {
            throw new DifferentAxisException("Not on the same axis");
        }

        List<Coordinate> resultat = new ArrayList<>();
        int pasX = Integer.compare(target.x, this.x);
        int pasY = Integer.compare(target.y, this.y);
        
        if (pasY == 0) pasX = (target.x > this.x) ? 2 : -2;

        int xActuel = this.x + pasX;
        int yActuel = this.y + pasY;

        while (xActuel != target.x || yActuel != target.y) {
            resultat.add(new CoordinateDoubled(yActuel, xActuel));
            xActuel += pasX;
            yActuel += pasY;
        }
        return resultat;
    }

	@Override
	public Point to2DCoordinate() {
		return new Point(this.x, this.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinateDoubled other = (CoordinateDoubled) obj;
		return x == other.x && y == other.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
	
}