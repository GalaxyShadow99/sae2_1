package iut.gon.othello.IA;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public interface AI {
	public Action chooseMove(IState state);
}
