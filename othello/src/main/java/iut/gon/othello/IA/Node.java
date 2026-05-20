package iut.gon.othello.IA;

import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;


public class Node {
	IState etat;
	Node parent;
	Action before;
	double Score;
	
	public Node(IState etat, Node parents, Action before) {
		super();
		this.etat = etat;
		this.parent = parents;
		this.before = before;
	}
	
	public double evaluate() {
		return 0;
	}

	public IState getEtat() {
		return etat;
	}

	public Node getParent() {
		return parent;
	}

	public Action getBefore() {
		return before;
	}
	
	public double getScore() {
		return score;
	}
	
}
