package iut.gon.othello.IA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.state.IState;

public class MiniMaxAI implements AI{
	private int depthMax;
	private IState state;
	private Team myTeam;
	
	public MiniMaxAI(int depthMax, IState state, Team myTeam) {
		this.depthMax = depthMax;
		this.state = state;
		this.myTeam = myTeam;
	}

	@Override
	public Action chooseMove(IState state) {
		return null;
	}

	
	public void evaluation(Coordinate from, Coordinate to) {
		
	}
	
	public Map<Coordinate, Set<Coordinate>> coups(IState state, Team team) {
		Map<Coordinate, Set<Coordinate>> coups= new HashMap<Coordinate, Set<Coordinate>>();
		List<Coordinate> rings = state.rings().get(team); 
		for (Coordinate r : rings) {
			coups.put(r, state.availableMoves(r));
		}
		return coups;
	}
	
	public void minimax(Node n, int depth, Team t) {
		if (depth == depthMax) {
			
		}
	}
	
}
