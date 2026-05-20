package iut.gon.othello.IA;

import iut.gon.othello.model.actions.Action;

public class Resultat {
	private Action action;
	private double score;
	
	/**
	 * Crée un résultat associant une action à son score.
	 * @param action l'action évaluée (Action)
	 * @param score le score de l'action (double)
	 */
	public Resultat(Action action, double score) {
		this.action = action;
		this.score = score;
	}

	public Action getAction() {
		return action;
	}

	public double getScore() {
		return score;
	}
}
