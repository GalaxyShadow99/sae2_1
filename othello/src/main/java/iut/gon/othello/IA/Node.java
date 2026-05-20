package iut.gon.othello.IA;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;


public class Node {
	IState etat;
	Node parents;
	Action before;
	public Node(IState etat, Node parents, Action before) {
		super();
		this.etat = etat;
		this.parents = parents;
		this.before = before;
	}
	
}
