package iut.gon.othello.model.state;

import java.util.List;
import java.util.Map;
import java.util.Set;

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.DifferentAxisException;
import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.tokens.Token;

public interface IState {

    /**
     * Retourne le plateau de jeu (carte des coordonnées vers les tokens).
     * @return Map<Coordinate, Token> le plateau
     */
    Map<Coordinate, Token> board();

    /**
     * Retourne l'équipe dont c'est le tour.
     * @return Team l'équipe courante
     */
    Team turn();

    /**
     * Retourne la liste des lignes de 5 pions détectées.
     * @return List<Set<Coordinate>> les lignes de pions
     */
    List<Set<Coordinate>> lines();

    /**
     * Applique un déplacement d'anneau et retourne le nouvel état.
     * @param move le déplacement à appliquer (Move)
     * @return IState le nouvel état après déplacement
     * @throws DifferentAxisException si les coordonnées ne sont pas sur le même axe
     */
    IState move(Move move) throws DifferentAxisException;

    /**
     * Supprime une ligne de pions et un anneau, retourne le nouvel état.
     * @param line la ligne à supprimer (RemoveLine)
     * @return IState le nouvel état après suppression
     */
    IState removeLine(RemoveLine line);

    /**
     * Retourne les déplacements disponibles depuis un anneau.
     * @param ringPosition coordonnée de l'anneau (Coordinate)
     * @return Set<Coordinate> les positions accessibles
     */
    Set<Coordinate> availableMoves(Coordinate ringPosition);

    /**
     * Retourne la carte des anneaux par équipe.
     * @return Map<Team, List<Coordinate>> les anneaux de chaque équipe
     */
    Map<Team,List<Coordinate>> rings();

    /**
     * Détermine l'équipe gagnante si la partie est finie.
     * @return Team l'équipe gagnante, ou null si pas de vainqueur
     */
    Team winner();

    /**
     * Recherche les lignes de 5 pions sur le plateau donné.
     * @param board le plateau à analyser (Map<Coordinate, Token>)
     * @return List<Set<Coordinate>> les lignes trouvées
     */
    List<Set<Coordinate>> getPawnsLines(Map<Coordinate,Token> board);

    /**
     * Retire un token à la position donnée.
     * @param position coordonnée ciblée (Coordinate)
     * @return IState le nouvel état
     */
    IState removeToken(Coordinate position);

    /**
     * Active ou désactive un token à une position.
     * @param position coordonnée ciblée (Coordinate)
     * @param team équipe propriétaire (Team)
     * @param tokenClass classe du token (Class<?>)
     * @return IState le nouvel état
     */
    IState toggleToken(Coordinate position,Team team,Class<?> tokenClass);

    /**
     * Vérifie si une coordonnée est dans le champ de jeu.
     * @param coordinate coordonnée à vérifier (Coordinate)
     * @return boolean true si la coordonnée est dans le champ
     */
    boolean isInField(Coordinate coordinate);
}