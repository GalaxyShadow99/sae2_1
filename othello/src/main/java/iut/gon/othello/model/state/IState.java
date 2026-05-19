package iut.gon.othello.model.state;

import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.tokens.Token;

public interface IState {
	public IState move(Move move);
	public IState removeLine(RemoveLine removeLine);
	public Set<Coordinate> availableMoves(Coordinate from);
	public Map<Coordinate, Token> board();
	public Map<Team, List<Coordinate>> rings();
	public List<Set<Coordinate>> lines();
	public Team turn();
	public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board);
}
