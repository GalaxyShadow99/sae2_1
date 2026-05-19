package iut.gon.othello.model.state;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.HashMap;
<<<<<<< HEAD
=======
>>>>>>> othello-factory
=======
import java.util.HashSet;
>>>>>>> origin/othello-state_
import java.util.List;
import java.util.Map;
import java.util.Set;

import coordinate.Coordinate;
import coordinate.Mode;

import iut.gon.othello.model.Team;

import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;

import iut.gon.othello.model.tokens.Token;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;

<<<<<<< HEAD
public record State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {
	Map<Coordinate, Token> board;
	Team turn;
	List<Set<Coordinate>> lines;
	
	public State(Map<Coordinate, Token> grid, Team white, ArrayList<Set<Coordinate>> arrayList) {
		// TODO Auto-generated constructor stub
	}


	public void State(Map<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) {
		
	}
	
	
	public boolean isInField(Coordinate c) {
		return (Boolean) null;
	}
	
	public Team winner() {
		return null;
	}
	
	public boolean equals(Object o) {
		return (Boolean) null;  // à générer à la fin
	}
	
	public int hashcode() {
		return (Integer) null;  // à générer à la fin
	}


    @Override
    public boolean isInField(Coordinate c) {
        return this.board.containsKey(c);
    }
=======
public record State(
        HashMap<Coordinate,Token> board,
        Team turn,
        List<Set<Coordinate>> lines ) implements IState
>>>>>>> origin/othello-state_

    @Override
    public Map<Team, List<Coordinate>> rings() {
        Map<Team, List<Coordinate>> ringsMap = new HashMap<>();
        ringsMap.put(Team.BLACK, new ArrayList<>());
        ringsMap.put(Team.WHITE, new ArrayList<>());

        for (Map.Entry<Coordinate, Token> entry : this.board.entrySet()) {
            Token token = entry.getValue();
            if (token != null && token instanceof Ring) {
                ringsMap.get(token.getTeam()).add(entry.getKey());
            }
        }
        return ringsMap;
    }

    @Override
    public Team winner() {
        Map<Team, List<Coordinate>> currentRings = this.rings();
        
        if (currentRings.get(Team.BLACK).size() <= 2) {
            return Team.BLACK;
        }
        if (currentRings.get(Team.WHITE).size() <= 2) {
            return Team.WHITE;
        }
        
        return null;
    }
    
    @Override
    public IState move(Move move) {
        if (this.lines != null && !this.lines.isEmpty()) {
            throw new RuntimeException("Impossible de jouer, il y a un problem de ligne");
        }
        
        Coordinate from = move.getFrom();
        Coordinate to = move.getTo();


        if (!isInField(from) || !isInField(to)) {
            throw new IndexOutOfBoundsException();
        }


        Token token = this.board.get(from);
        
        if (token == null || !(token instanceof Ring) || token.getTeam() != this.turn) {
            throw new IllegalArgumentException();
        }

        if (!availableMoves(from).contains(to)) {
            throw new IllegalArgumentException("Déplacement impossible.");
        }

        Map<Coordinate, Token> newBoard = new HashMap<>(this.board);

        newBoard.put(to, token);
        newBoard.put(from, new Pawn(this.turn));

        List<Coordinate> path = from.between(Mode.FLAT, to);
        for (Coordinate c : path) {
            Token t = newBoard.get(c);
            if (t instanceof Pawn) {
                newBoard.put(c, new Pawn(t.getTeam().other()));
            }
        }

        List<Set<Coordinate>> newLines = getPawnsLines(newBoard);

        Team nextTurn = newLines.isEmpty() ? this.turn.other() : this.turn;

        return new State(newBoard, nextTurn, newLines);
    }

    @Override
    public IState removeLine(RemoveLine removeLine) {
        if (this.lines == null || this.lines.isEmpty()) {
            throw new RuntimeException("Aucune possibilité de supprimer un ligne.");
        }

        Set<Coordinate> lineToRemove = removeLine.getLine();
        Coordinate ringToRemove = removeLine.getRing();

        if (!this.lines.contains(lineToRemove)) {
            throw new RuntimeException("la ligne ne peut pas être supprimer");
        }

        Map<Coordinate, Token> newBoard = new HashMap<>(this.board);

        for (Coordinate c : lineToRemove) {
            newBoard.put(c, null); 
        }

        newBoard.put(ringToRemove, null);

        List<Set<Coordinate>> remainingLines = new ArrayList<>(this.lines);
        remainingLines.remove(lineToRemove);

        Team nextTurn = remainingLines.isEmpty() ? this.turn.other() : this.turn;

        return new State(newBoard, nextTurn, remainingLines);
    }

    @Override
    public Set<Coordinate> availableMoves(Coordinate from) {
        Set<Coordinate> moves = new HashSet<>();
        
        for (coordinate.Direction dir : coordinate.Direction.values()) {
            Coordinate current = from;
            boolean jumpedPawn = false;
            
            while (true) {
                try {
                    current = current.toDir(coordinate.Mode.POINTY, dir);
                } catch (IllegalArgumentException | java.security.InvalidParameterException e) {
                    break; 
                }
                
                if (!isInField(current)) {
                    break;
                }
                
                Token tokenOnCell = this.board.get(current);
                
                if (tokenOnCell == null) {
                    moves.add(current);
                    if (jumpedPawn) {
                        break; 
                    }
                } else if (tokenOnCell instanceof Ring) {
                    break;
                } else
                    jumpedPawn = true;
                }
            }
        }
        
        return moves;
    }

	@Override
	public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> boardToScan) {
	    Set<Set<Coordinate>> uniqueLines = new HashSet<>();
	    
	    for (Coordinate startCoord : boardToScan.keySet()) {
	        Token startToken = boardToScan.get(startCoord);
	        
	        if (startToken == null || !(startToken.getClass().getSimpleName().equals("Pawn"))) {
	            continue;
	        }
	        
	        Team teamToMatch = startToken.getTeam();
	        
	        for (coordinate.Direction dir : coordinate.Direction.values()) {
	            Set<Coordinate> currentLine = new HashSet<>();
	            currentLine.add(startCoord);
	            
	            Coordinate current = startCoord;
	            boolean isValidLine = true;
	            
	            for (int i = 0; i < 4; i++) {
	                try {
	                    current = current.toDir(coordinate.Mode.POINTY, dir);
	                    
	                    if (!boardToScan.containsKey(current)) {
	                        isValidLine = false;
	                        break;
	                    }
	                    
	                    Token nextToken = boardToScan.get(current);
	                    if (nextToken == null || !(nextToken.getClass().getSimpleName().equals("Pawn")) || nextToken.getTeam() != teamToMatch) {
	                        isValidLine = false;
	                        break;
	                    }
	                    
	                    currentLine.add(current);
	                    
	                } catch (IllegalArgumentException | java.security.InvalidParameterException e) {
	                    isValidLine = false;
	                    break;
	                }
	            }
	            
	            if (isValidLine && currentLine.size() == 5) {
	                uniqueLines.add(currentLine);
	            }
	        }
	    }
	    
	    return new ArrayList<>(uniqueLines);
	}
	
	@Override
    public IState removeToken(Coordinate c) {
        Map<Coordinate, Token> newBoard = new HashMap<>(this.board);
        
        if (newBoard.containsKey(c)) {
            newBoard.put(c, null);
        }
        
        return new State(newBoard, this.turn, this.lines);
    }

    @Override
    public IState toggleToken(Coordinate position, Team team, Class<?> tokenClass) {
        Map<Coordinate, Token> newBoard = new HashMap<>(this.board);
        Token existingToken = newBoard.get(position);

        try {
            java.lang.reflect.Constructor<?> constructor = tokenClass.getConstructors()[0];
            
            Token generatedToken = (Token) constructor.newInstance(team);
            if (existingToken != null && 
                existingToken.getClass().equals(tokenClass) && 
                existingToken.getTeam() == team) {
                newBoard.put(position, null);
            } else {
                newBoard.put(position, generatedToken);
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur de token : " + e.getMessage());
        }

        return new State(newBoard, this.turn, this.lines);
    }
}