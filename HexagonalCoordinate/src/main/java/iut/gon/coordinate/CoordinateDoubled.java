package iut.gon.coordinate;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoordinateDoubled extends Coordinate {
	private int y;
	private int x;

	/**
	 * Crée une coordonnée en coordonnées doublées.
	 * @param x coordonnée x (int)
	 * @param y coordonnée y (int)
	 */
	public CoordinateDoubled(int x, int y) {
		this.y = y;
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	/**
	 * Retourne la coordonnée au Nord-Ouest de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au NO
	 */
	@Override
	public Coordinate NO(Mode mode) {
		return new CoordinateDoubled(this.x - 1, this.y - 1);
	}

	/**
	 * Retourne la coordonnée au Nord-Est de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au NE
	 */
	@Override
	public Coordinate NE(Mode mode) {
		return new CoordinateDoubled(this.x + 1, this.y - 1);
	}

	/**
	 * Retourne la coordonnée à l'Est de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée à l'Est
	 */
	@Override
	public Coordinate E(Mode mode) {
		if (mode == Mode.FLAT)
			throw new InvalidParameterException("E n'accepte pas le mode FLAT");
		return new CoordinateDoubled(this.x + 2, this.y);
	}

	/**
	 * Retourne la coordonnée à l'Ouest de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée à l'Ouest
	 */
	@Override
	public Coordinate O(Mode mode) {
		if (mode == Mode.FLAT)
			throw new InvalidParameterException("O n'accepte pas le mode FLAT");
		return new CoordinateDoubled(this.x - 2, this.y);
	}

	/**
	 * Retourne la coordonnée au Nord de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au Nord
	 */
	@Override
	public Coordinate N(Mode mode) {
		if (mode == Mode.POINTY)
			throw new InvalidParameterException("N n'accepte pas le mode POINTY");
		return new CoordinateDoubled(this.x, this.y - 2);
	}

	/**
	 * Retourne la coordonnée au Sud de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au Sud
	 */
	@Override
	public Coordinate S(Mode mode) {
		if (mode == Mode.POINTY)
			throw new InvalidParameterException("S n'accepte pas le mode POINTY");
		return new CoordinateDoubled(this.x, this.y + 2);
	}

	/**
	 * Retourne la coordonnée au Sud-Ouest de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au SO
	 */
	@Override
	public Coordinate SO(Mode mode) {
		return new CoordinateDoubled(this.x - 1, this.y + 1);
	}

	/**
	 * Retourne la coordonnée au Sud-Est de celle-ci.
	 * @param mode mode d'affichage (Mode)
	 * @return Coordinate coordonnée au SE
	 */
	@Override
	public Coordinate SE(Mode mode) {
		return new CoordinateDoubled(this.x + 1, this.y + 1);
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
		if (!(to instanceof CoordinateDoubled))
			throw new InvalidParameterException();
		CoordinateDoubled target = (CoordinateDoubled) to;

		int dx = target.getX() - this.x;
		int dy = target.getY() - this.y;
		
		boolean sameAxis = Math.abs(dx) == Math.abs(dy);
		if (mode == Mode.POINTY) {
			if (this.y == target.y) {
				sameAxis = true;
			}
		}
		if (mode == Mode.FLAT) {
			if (this.x == target.x) {
				sameAxis = true;
			}
		}

		if (!sameAxis)
			throw new DifferentAxisException();

		List<Coordinate> resultat = new ArrayList<>();
		int pasX = Integer.compare(target.getX(), this.x);
		int pasY = Integer.compare(target.getY(), this.y);

		if (pasY == 0)
			pasX = (target.getX() > this.x) ? 2 : -2;

		int xActuel = this.x + pasX;
		int yActuel = this.y + pasY;

		while (xActuel != target.getX() || yActuel != target.getY()) {
			resultat.add(new CoordinateDoubled(xActuel, yActuel));
			xActuel += pasX;
			yActuel += pasY;
		}
		return resultat;
	}

	/**
	 * Convertit cette coordonnée doublée en coordonnée 2D (Point).
	 * @return Point coordonnée 2D correspondante
	 */
	@Override
	public Point to2DCoordinate() {
		return new Point(this.x, this.y);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
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
}