package iut.gon.othello.model.state;

import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;

public class State implements IState {
	Map<Coordinate, Token> board;
	Team turn;
	List<Set<Coordinate>> lines;
	
	public void State(Map<Coordinate> board, Team turn, List<Set<Coordinate>> lines) {
		
	}
	
	public boolean isInField(Coordinate c) {
		return null;
	}
	
	public Team winner() {
		return null;
	}
	
	public boolean equals(Object o) {
		return null;  // à générer à la fin
	}
	
	public int hashcode() {
		return null;  // à générer à la fin
	}

	@Override
	public Set<Coordinate> availableMoves(Coordinate from) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Set<Coordinate>> lines() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IState move(Move move) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IState removeLine(RemoveLine removeLine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Coordinate, Token> board() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Team, List<Coordinate>> rings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team turn() {
		// TODO Auto-generated method stub
		return null;

	@Override
	public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> board) {
		// TODO Auto-generated method stub
		return null;
	}
}