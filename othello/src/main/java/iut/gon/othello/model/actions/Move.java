package iut.gon.othello.model.actions;

import iut.gon.coordinate.Coordinate;

public class Move extends Action{
	private Coordinate from;
	private Coordinate to;
	
	/**
	 * Crée un déplacement d'une coordonnée source vers une destination.
	 * @param from coordonnée de départ (Coordinate)
	 * @param to coordonnée d'arrivée (Coordinate)
	 */
	public Move(Coordinate from, Coordinate to) {
		this.from = from;
		this.to = to;
	}

	public Coordinate getFrom() {
		return from;
	}

	public void setFrom(Coordinate from) {
		this.from = from;
	}

	public Coordinate getTo() {
		return to;
	}

	public void setTo(Coordinate to) {
		this.to = to;
	}
}
