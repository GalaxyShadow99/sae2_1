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

    Map<Coordinate, Token> board();
    Team turn();
    List<Set<Coordinate>> lines();
    IState move(Move move);
    IState removeLine(RemoveLine line);
    List<Coordinate> availableMoves(Coordinate ringPosition);
    Map<Team,List<Coordinate>> rings();
    Team winner();
    List<Set<Coordinate>> getPawnsLines(Map<Coordinate,Token> board);
    IState removeToken(Coordinate position);
    IState toggleToken(Coordinate position,Team team,Class<?> tokenClass);
    boolean isInField(Coordinate coordinate);
}