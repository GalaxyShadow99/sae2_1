package iut.gon.othello.model.state;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.DifferentAxisException;
import iut.gon.coordinate.Direction;
import iut.gon.coordinate.Mode;
import iut.gon.othello.model.Team;

import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;

import iut.gon.othello.model.tokens.Token;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;

/**
 * Enregistrement représentant l'état du jeu.
 * @param board le plateau de jeu (HashMap<Coordinate, Token>)
 * @param turn l'équipe dont c'est le tour (Team)
 * @param lines les lignes de 5 pions détectées (List<Set<Coordinate>>)
 */
public record State(HashMap<Coordinate, Token> board, Team turn, List<Set<Coordinate>> lines) implements IState {

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

	/**
	 * Détermine le gagnant : une équipe gagne si elle a 2 anneaux ou moins.
	 * @return Team l'équipe gagnante, ou null si la partie continue
	 */
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

	/**
	 * Déplace un anneau, retourne les pions traversés et détecte les lignes.
	 * @param move le déplacement à effectuer (Move)
	 * @return IState le nouvel état après le déplacement
	 * @throws DifferentAxisException si from et to ne sont pas sur le même axe
	 */
	@Override
	public IState move(Move move) throws DifferentAxisException {
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

		HashMap<Coordinate, Token> newBoard = new HashMap<>(this.board);

		newBoard.put(to, token);
		newBoard.put(from, new Pawn(this.turn));

		List<Coordinate> path = from.between(Mode.POINTY, to);
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

	/**
	 * Supprime une ligne de pions et l'anneau associé du plateau.
	 * @param removeLine contient la ligne et l'anneau à supprimer (RemoveLine)
	 * @return IState le nouvel état après suppression
	 */
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

		HashMap<Coordinate, Token> newBoard = new HashMap<>(this.board);

		for (Coordinate c : lineToRemove) {
			newBoard.put(c, null);
		}

		newBoard.put(ringToRemove, null);

		List<Set<Coordinate>> remainingLines = new ArrayList<>(this.lines);
		remainingLines.remove(lineToRemove);

		Team nextTurn = remainingLines.isEmpty() ? this.turn.other() : this.turn;

		return new State(newBoard, nextTurn, remainingLines);
	}

	/**
	 * Calcule l'ensemble des déplacements possibles depuis un anneau.
	 * @param from coordonnée de départ de l'anneau (Coordinate)
	 * @return Set<Coordinate> les positions accessibles
	 */
	@Override
	public Set<Coordinate> availableMoves(Coordinate from) throws java.security.InvalidParameterException {
		Set<Coordinate> moves = new HashSet<>();

		for (Direction dir : Direction.values()) {
			Coordinate current = from;
			boolean jumpedPawn = false;

			while (true) {
				try {
					current = current.toDir(Mode.POINTY, dir);
				} catch (InvalidParameterException e) {
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
		return moves;
	}

	/**
	 * Parcourt le plateau et détecte toutes les lignes de 5 pions alignés.
	 * @param boardToScan le plateau à analyser (Map<Coordinate, Token>)
	 * @return List<Set<Coordinate>> les lignes de 5 pions trouvées
	 */
	@Override
	public List<Set<Coordinate>> getPawnsLines(Map<Coordinate, Token> boardToScan) throws java.security.InvalidParameterException {
		Set<Set<Coordinate>> uniqueLines = new HashSet<>();

		for (Coordinate startCoord : boardToScan.keySet()) {
			Token startToken = boardToScan.get(startCoord);

			if (startToken == null || !(startToken instanceof Pawn)) {
			    continue;
			}

			Team teamToMatch = startToken.getTeam();

			for (Direction dir : Direction.values()) {
				Set<Coordinate> currentLine = new HashSet<>();
				currentLine.add(startCoord);

				Coordinate current = startCoord;
				boolean isValidLine = true;

				for (int i = 0; i < 4; i++) {
					try {
						current = current.toDir(Mode.POINTY, dir);

						if (!boardToScan.containsKey(current)) {
							isValidLine = false;
							break;
						}

						Token nextToken = boardToScan.get(current);
						if (nextToken == null || !(nextToken instanceof Pawn) || nextToken.getTeam() != teamToMatch) {
							isValidLine = false;
							break;
						}

						currentLine.add(current);

					} catch (IllegalArgumentException e) {
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

	/**
	 * Retire le token à la coordonnée spécifiée.
	 * @param c coordonnée du token à retirer (Coordinate)
	 * @return IState le nouvel état sans le token
	 */
	@Override
	public IState removeToken(Coordinate c) {
		HashMap<Coordinate, Token> newBoard = new HashMap<>(this.board);

		if (newBoard.containsKey(c)) {
			newBoard.put(c, null);
		}

		return new State(newBoard, this.turn, this.lines);
	}

	/**
	 * Ajoute ou retire un token à une position donnée.
	 * @param position coordonnée ciblée (Coordinate)
	 * @param team équipe propriétaire (Team)
	 * @param tokenClass classe du token (Class<?>)
	 * @return IState le nouvel état modifié
	 */
	@Override
	public IState toggleToken(Coordinate position, Team team, Class<?> tokenClass) {
		HashMap<Coordinate, Token> newBoard = new HashMap<>(this.board);
		Token existingToken = newBoard.get(position);

		try {
			java.lang.reflect.Constructor<?> constructor = tokenClass.getConstructors()[0];

			Token generatedToken = (Token) constructor.newInstance(team);
			if (existingToken != null && existingToken.getClass().equals(tokenClass)
					&& existingToken.getTeam() == team) {
				newBoard.put(position, null);
			} else {
				newBoard.put(position, generatedToken);
			}

		} catch (Exception e) {
			throw new RuntimeException("Erreur de token : " + e.getMessage());
		}

		return new State(newBoard, this.turn, this.lines);
	}

	/**
	 * Vérifie si une coordonnée appartient au plateau de jeu.
	 * @param coordinate coordonnée à vérifier (Coordinate)
	 * @return boolean true si la coordonnée est dans le champ
	 */
	@Override
	public boolean isInField(Coordinate coordinate) {
	    return this.board != null && this.board.containsKey(coordinate);
	}
}