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

import coordinate.Coordinate;
import coordinate.DifferentAxisException;
import coordinate.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AIVsAIMain {
    
    public static void main(String[] args) throws DifferentAxisException {
        
        System.out.println("=== SIMULATION IA vs IA : OTHELLO HEXAGONAL ===");
        
        IState state;
        
        try {
            IFactory factory = new FactoryDoubled();
            state = factory.emptyState();
        } catch (Exception e) {
            System.out.println("Erreur : Impossible de charger le terrain vierge.");
            return;
        }
        
        state = initializeRandomRings(state);
        System.out.println("Terrain initialise avec 5 anneaux par equipe places aleatoirement.\n");
        
        // Initialisation des deux IA (tu peux varier la profondeur pour voir laquelle gagne)
        AI aiWhite = new MiniMaxAI(3, state, Team.WHITE); 
        AI aiBlack = new MiniMaxAI(3, state, Team.BLACK); 
        
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard(state);

            Team winner = state.winner();
            if (winner != null) {
                System.out.println("\n=========================================");
                System.out.println("Felicitations ! L'equipe " + winner + " a gagne la partie !");
                System.out.println("=========================================");
                break;
            }

            // Détermination de l'IA dont c'est le tour
            AI currentAI = (state.turn() == Team.WHITE) ? aiWhite : aiBlack;

            // Pause pour pouvoir observer la partie humainement (1.5 secondes)
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // PHASE 1 : GESTION DES LIGNES
            if (state.lines() != null && !state.lines().isEmpty()) {
                System.out.println("Alignement de 5 pions detecte ! L'IA (" + state.turn() + ") doit supprimer une ligne et un anneau...");
                
                Action aiAction = currentAI.chooseMove(state);
                try {
                    state = state.removeLine((RemoveLine) aiAction);
                    System.out.println("Succes : L'IA " + state.turn() + " a retire une ligne et un anneau.");
                } catch (Exception e) {
                    System.out.println("Erreur fatale de l'IA " + state.turn() + " lors du RemoveLine : " + e.getMessage());
                    gameRunning = false;
                }
                continue; 
            }

            // PHASE 2 : GESTION DES DEPLACEMENTS
            System.out.println("C'est au tour de l'equipe : " + state.turn() + ". L'IA reflechit...");
            
            Action aiAction = currentAI.chooseMove(state);
            try {
                state = state.move((Move) aiAction);
                System.out.println("L'IA " + state.turn() + " a effectue son deplacement.");
            } catch (Exception e) {
                System.out.println("Erreur fatale de l'IA " + state.turn() + " lors du Move : " + e.getMessage());
                gameRunning = false;
            }
        }

        System.out.println("=== FIN DE LA SIMULATION ===");
    }

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
        System.out.println("              [ Axe X (Ouest / Est) ]");
        
        System.out.print("            ");
        for (int x = minX; x <= maxX; x++) {
            int absX = Math.abs(x);
            System.out.print((absX >= 10 ? (absX / 10) : " ") + " ");
        }
        System.out.println();
        
        System.out.print("            ");
        for (int x = minX; x <= maxX; x++) {
            int absX = Math.abs(x);
            System.out.print((absX % 10) + " ");
        }
        System.out.println("\n            " + "--".repeat(width));

        for (int y = minY; y <= maxY; y++) {
            System.out.printf("[Axe Y] %2d | ", y);
            
            for (int x = minX; x <= maxX; x++) {
                System.out.print(grid[y - minY][x - minX] + " ");
            }
            System.out.println();
        }
        System.out.println("======================================================================");
    }

    private static char getTokenChar(Token token) {
        if (token == null) return '•';
        if (token instanceof Ring) return token.getTeam() == Team.WHITE ? '◯' : '●';
        if (token instanceof Pawn) return token.getTeam() == Team.WHITE ? '♙' : '♟';
        return '?';
    }
}