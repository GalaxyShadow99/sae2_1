package iut.gon.othello.IA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;
import coordinate.DifferentAxisException;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.state.IState;

public class MiniMaxAI implements AI{
	private int depthMax;
	private Team myTeam;
	
	public MiniMaxAI(int depthMax, IState state, Team myTeam) {
		this.depthMax = depthMax;
		this.myTeam = myTeam;
	}

	@Override
	public Action chooseMove(IState state) {
		return null;
	}
	
	public Node minimax(Node n, int depth, Team team, double alpha, double beta) throws DifferentAxisException {
		if (depth == 0 || (depth == 1 && team == myTeam)) {
			return n;
		}
		if (team != myTeam) {
			double value = Double.POSITIVE_INFINITY;
			for (Move m : getMoves()) {
				value = Math.min(value, minimax(new Node(n.getEtat().move(m), n, m), depth-1, team.other(),  alpha, beta).evaluate());
				if (alpha >= value) {
					return 
				}
			}
			
		}
	}

	private Resultat getParent(Node n) {
		if (n.getParent() == null) {
			return new Resultat(n.before, n.evaluate());
		}
		else {
			return getParent(n.getParent());
		}
	}
	
	public List<Move> getMoves(){
		return null;
	}
}
