package iut.gon.othello.model;

import java.awt.Color;
import java.text.Collator;

public enum Team {
	BLACK(Color.BLACK),
	WHITE(Color.WHITE);
	
	private Color color;
	
	Team(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}
	
	/**
	 * Retourne l'équipe adverse.
	 * @return Team l'équipe opposée
	 */
	public Team other() {
		if(this == Team.WHITE) {
			return Team.BLACK;
		}
		return Team.WHITE;
	}
	
}
