package iut.gon.othello.model.actions;
import java.util.Set;

import iut.gon.coordinate.Coordinate;

public class RemoveLine extends Action {
	private Set<Coordinate> line;
	private Coordinate ring;
	
	/**
	 * Crée une action de suppression de ligne et d'anneau.
	 * @param line ensemble des coordonnées de la ligne (Set<Coordinate>)
	 * @param ring coordonnée de l'anneau à retirer (Coordinate)
	 */
	public RemoveLine(Set<Coordinate> line, Coordinate ring) {
		this.line = line;
		this.ring = ring;
	}

	public Set<Coordinate> getLine() {
		return line;
	}

	public void setLine(Set<Coordinate> line) {
		this.line = line;
	}

	public Coordinate getRing() {
		return ring;
	}

	public void setRing(Coordinate ring) {
		this.ring = ring;
	}
}
