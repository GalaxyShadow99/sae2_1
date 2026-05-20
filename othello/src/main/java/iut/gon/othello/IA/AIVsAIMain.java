package iut.gon.othello.IA;

import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Action;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory; 

import iut.gon.coordinate.Coordinate;
import iut.gon.coordinate.DifferentAxisException;
import iut.gon.coordinate.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AIVsAIMain {
    
    /**
     * Point d'entrée du jeu avec deux IA s'affrontant.
     * @param args arguments de la ligne de commande (String[])
     * @throws DifferentAxisException si les coordonnées ne sont pas alignées
     */
    public static void main(String[] args) throws DifferentAxisException {
        
        System.out.println("=== BIENVENUE DANS OTHELLO HEXAGONAL ===");
        
        IState state;
        
        try {
            IFactory factory = new FactoryDoubled();
            state = factory.emptyState();
        } catch (Exception e) {
            System.out.println("Erreur : Impossible de charger le terrain vide.");
            return;
        }
        
        state = initializeRandomRings(state);
        System.out.println("Terrain initialisé avec 5 anneaux par équipe placés aleatoirement.\n");
        
        AI aiWhite = new MiniMaxAI(3, state, Team.WHITE); 
        AI aiBlack = new MiniMaxAI(3, state, Team.BLACK); 
        
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard(state);

            Team winner = state.winner();
            if (winner != null) {
                System.out.println("Félicitations ! L'équipe " + winner + " a gagné la partie !");
                break;
            }

            AI currentAI = (state.turn() == Team.WHITE) ? aiWhite : aiBlack;

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (state.lines() != null && !state.lines().isEmpty()) {
                System.out.println("Alignement de 5 pions détecté ! L'équipe " + state.turn() + " doit supprimer une ligne et un anneau.");
                System.out.println("L'IA (" + state.turn() + ") réflechit pour supprimer une ligne...");
                
                Action aiAction = currentAI.chooseMove(state);
                try {
                    state = state.removeLine((RemoveLine) aiAction);
                    System.out.println("L'IA a retiré une ligne et un anneau.");
                } catch (Exception e) {
                    System.out.println("Erreur IA lors du RemoveLine : " + e.getMessage());
                    gameRunning = false;
                }
                continue; 
            }

            System.out.println("C'est au tour de l'equipe : " + state.turn());
            System.out.println("L'IA (" + state.turn() + ") réflechit a son déplacement...");
            
            Action aiAction = currentAI.chooseMove(state);
            try {
                state = state.move((Move) aiAction);
                System.out.println("L'IA a joué.");
            } catch (Exception e) {
                System.out.println("Erreur de l'IA lors du déplacement : " + e.getMessage());
                gameRunning = false;
            }
        }

        System.out.println("=== FIN DE LA PARTIE ===");
    }

    /**
     * Place aléatoirement 5 anneaux par équipe sur le plateau.
     * @param state état initial vide (IState)
     * @return IState état avec les anneaux placés
     */
    private static IState initializeRandomRings(IState state) {
        List<Coordinate> availableCoordinates = new ArrayList<>(state.board().keySet());
        java.util.Collections.shuffle(availableCoordinates); 

        int whitePlaced = 0;
        int blackPlaced = 0;

        for (Coordinate coord : availableCoordinates) {
            if (whitePlaced < 5) {
                state = state.toggleToken(coord, Team.WHITE, Ring.class);
                whitePlaced++;
            } else if (blackPlaced < 5) {
                state = state.toggleToken(coord, Team.BLACK, Ring.class);
                blackPlaced++;
            } else {
                break;
            }
        }
        return state;
    }

    /**
     * Affiche le plateau de jeu dans la console.
     * @param state état du jeu à afficher (IState)
     */
    private static void displayBoard(IState state) {
        if (state.board().isEmpty()) {
            System.out.println("[Plateau de jeu vide]");
            return;
        }

        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Coordinate coord : state.board().keySet()) {
            Point p = coord.to2DCoordinate();
            if (p.x() < minX) minX = p.x();
            if (p.x() > maxX) maxX = p.x();
            if (p.y() < minY) minY = p.y();
            if (p.y() > maxY) maxY = p.y();
        }

        int width = maxX - minX + 1;
        int height = maxY - minY + 1;
        char[][] grid = new char[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = ' ';
            }
        }

        for (Map.Entry<Coordinate, Token> entry : state.board().entrySet()) {
            Coordinate coord = entry.getKey();
            Token token = entry.getValue();
            Point p = coord.to2DCoordinate();
            grid[p.y() - minY][p.x() - minX] = getTokenChar(token);
        }

        System.out.println("\n=========================== PLATEAU DE JEU ===========================");
        
        System.out.println("               [ Axe X (Ouest / Est) ]");
        
        System.out.print("             ");
        for (int x = 0; x < width; x++) {
            System.out.print((x >= 10 ? (x / 10) : " ") + " ");
        }
        System.out.println();
        
        System.out.print("             ");
        for (int x = 0; x < width; x++) {
            System.out.print((x % 10) + " ");
        }
        System.out.println("\n            -" + "--".repeat(width));

        for (int y = 0; y < height; y++) {
            
            if (y == height / 2) {
                System.out.printf("[Axe Y] %2d | ", y);
            } else {
                System.out.printf("        %2d | ", y);
            }
            
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("======================================================================");
    }

    /**
     * Retourne le caractère représentant un token pour l'affichage.
     * @param token token à représenter (Token)
     * @return char caractère du token
     */
    private static char getTokenChar(Token token) {
        if (token == null) return '•';
        if (token instanceof Ring) return token.getTeam() == Team.WHITE ? '◯' : '●';
        if (token instanceof Pawn) return token.getTeam() == Team.WHITE ? '♙' : '♟';
        return '?';
    }
}