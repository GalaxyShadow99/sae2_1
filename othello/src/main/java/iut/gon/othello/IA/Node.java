package iut.gon.othello.IA;
import java.util.List;

import iut.gon.coordinate.Coordinate;
import iut.gon.othello.model.actions.Action;


public class Node {
	List<Coordinate> etat;
	Node parents;
	Action before;
	public Node(List<Coordinate> etat, Node parents, Action before) {
		super();
		this.etat = etat;
		this.parents = parents;
		this.before = before;
	}
	
}
