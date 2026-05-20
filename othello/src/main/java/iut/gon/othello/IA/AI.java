package iut.gon.othello.IA;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public interface AI {
	/**
	 * Choisit la meilleure action à effectuer selon l'état donné.
	 * @param state l'état actuel du jeu (IState)
	 * @return Action l'action choisie
	 */
	public Action chooseMove(IState state);
}
