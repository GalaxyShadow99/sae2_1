package iut.gon.coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoordinateCube extends Coordinate {
    private int q;
    private int r;
    private int s;
    
    /**
     * Crée une coordonnée cubique (q, r, s) avec q + r + s = 0.
     * @param q coordonnée q (int)
     * @param r coordonnée r (int)
     * @param s coordonnée s (int)
     */
    public CoordinateCube(int q, int r, int s) {
        if (q + r + s != 0) {
            throw new IllegalArgumentException("La somme de q, r et s doit être égale à 0");
        }
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



	/**
	 * Retourne la coordonnée au Nord-Ouest de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au NO
	 */
	@Override
    public Coordinate NO(Mode mode) {
        return new CoordinateCube(this.q -1, this.r, this.s + 1);
    }

    /**
     * Retourne la coordonnée au Nord-Est de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée au NE
     */
    @Override
    public Coordinate NE(Mode mode) {
        return new CoordinateCube(this.q + 1, this.r - 1, this.s);
    }

    /**
     * Retourne la coordonnée à l'Est de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée à l'Est
     */
    @Override
    public Coordinate E(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("E n'accepte pas le mode FLAT");
        return new CoordinateCube(this.q + 1, this.r, this.s - 1);
    }

    /**
     * Retourne la coordonnée à l'Ouest de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée à l'Ouest
     */
    @Override
    public Coordinate O(Mode mode) {
        if (mode == Mode.FLAT) throw new InvalidParameterException("O n'accepte pas le mode FLAT");
        return new CoordinateCube(this.q - 1, this.r, this.s + 1);
    }

    /**
     * Retourne la coordonnée au Nord de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée au Nord
     */
    @Override
    public Coordinate N(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("N n'accepte pas le mode POINTY");
        return new CoordinateCube(this.q, this.r - 1, this.s + 1); 
    }

    /**
     * Retourne la coordonnée au Sud de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée au Sud
     */
    @Override
    public Coordinate S(Mode mode) {
        if (mode == Mode.POINTY) throw new InvalidParameterException("S n'accepte pas le mode POINTY");
        return new CoordinateCube(this.q, this.r + 1, this.s - 1); 
    }

    /**
     * Retourne la coordonnée au Sud-Ouest de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée au SO
     */
    @Override
    public Coordinate SO(Mode mode) {
        return new CoordinateCube(this.q - 1, this.r + 1, this.s);
    }

    /**
     * Retourne la coordonnée au Sud-Est de celle-ci.
     * @param mode mode d'affichage (Mode)
     * @return Coordinate coordonnée au SE
     */
    @Override
    public Coordinate SE(Mode mode) {
        return new CoordinateCube(this.q, this.r + 1, this.s - 1);
    }

    /**
     * Calcule les coordonnées intermédiaires entre cette coordonnée et une destination sur le même axe.
     * @param mode mode d'affichage (Mode)
     * @param to coordonnée de destination (Coordinate)
     * @return List<Coordinate> coordonnées entre les deux points
     * @throws DifferentAxisException si les coordonnées ne sont pas sur le même axe
     */
    @Override
    public List<Coordinate> between(Mode mode, Coordinate to) throws DifferentAxisException {
        if (!(to instanceof CoordinateCube)) throw new InvalidParameterException();
        CoordinateCube target = (CoordinateCube) to;
        
        if ((this.q != target.getQ()) && (this.r != target.getR()) && (this.s != target.getS())) {
            throw new DifferentAxisException("Not on the same axis");
        }

        List<Coordinate> resultat = new ArrayList<>();
        int pasQ = Integer.compare(target.getQ(), this.q);
        int pasR = Integer.compare(target.getR(), this.r);
        int pasS = Integer.compare(target.getS(), this.s);

        int qActuel = this.q + pasQ;
        int rActuel = this.r + pasR;
        int sActuel = this.s + pasS;

        while (qActuel != target.getQ() || rActuel != target.getR() || sActuel != target.getS()) {
            resultat.add(new CoordinateCube(qActuel, rActuel, sActuel));
            qActuel += pasQ;
            rActuel += pasR;
            sActuel += pasS;
        }
        return resultat;
    }

    /**
     * Convertit cette coordonnée cubique en coordonnée 2D (Point).
     * @return Point coordonnée 2D correspondante
     */
    @Override
    public Point to2DCoordinate() {
        int y = this.r + 5;
        int x = 2 * this.q + this.r + 9;
        
        return new Point(x, y);
    }



	@Override
	public int hashCode() {
		return Objects.hash(q, r, s);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoordinateCube other = (CoordinateCube) obj;
		return q == other.q && r == other.r && s == other.s;
	}
}