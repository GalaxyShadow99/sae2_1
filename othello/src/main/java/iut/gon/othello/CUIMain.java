package iut.gon.othello;

import iut.gon.othello.model.Team;
import iut.gon.othello.model.actions.Move;
import iut.gon.othello.model.actions.RemoveLine;
import iut.gon.othello.model.state.IState;
import iut.gon.othello.model.tokens.Pawn;
import iut.gon.othello.model.tokens.Ring;
import iut.gon.othello.model.tokens.Token;

import iut.gon.othello.model.factory.FactoryDoubled;
import iut.gon.othello.model.factory.IFactory; 

import coordinate.Coordinate;
import coordinate.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CUIMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== BIENVENUE DANS OTHELLO HEXAGONAL ===");
        
        IState state;
        try {
            IFactory factory = new FactoryDoubled();
            state = factory.emptyState();
        } catch (Exception e) {
            System.out.println("❌ Erreur : Impossible de charger le terrain vierge.");
            scanner.close();
            return;
        }

        state = initializeRandomRings(state);
        System.out.println("Terrain initialisé avec 5 anneaux par équipe placés aléatoirement.\n");

        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard(state);

            Team winner = state.winner();
            if (winner != null) {
                System.out.println("🏆 Félicitations ! L'équipe " + winner + " a gagné la partie ! 🏆");
                break;
            }

            if (state.lines() != null && !state.lines().isEmpty()) {
                System.out.println("⚡ Alignement de 5 pions détecté ! " + state.turn() + " doit supprimer une ligne et un anneau.");
                state = handleRemoveLine(state, scanner);
                continue;
            }

            System.out.println("👉 C'est au tour de l'équipe : " + state.turn());
            state = handlePlayerMove(state, scanner);
        }

        scanner.close();
        System.out.println("=== FIN DE LA PARTIE ===");
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

    
    private static IState handlePlayerMove(IState state, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Déplacement d'un anneau ---");
            System.out.print("Coordonnées de l'anneau à DEPLACER (Format: y x) : ");
            int fromY = readInt(scanner);
            int fromX = readInt(scanner);
            Coordinate from = findCoordinate(state, fromY, fromX);

            System.out.print("Coordonnées de la case d'ARRIVEE (Format: y x) : ");
            int toY = readInt(scanner);
            int toX = readInt(scanner);
            Coordinate to = findCoordinate(state, toY, toX);

            if (from == null || to == null) {
                System.out.println("Erreur : Coordonnées introuvables. Réessayez.");
                continue;
            }

            try {
                return state.move(new Move(from, to));
            } catch (Exception e) {
                System.out.println("Mouvement invalide : " + e.getMessage() + " Veuillez réessayer.");
            }
        }
    }

    
    private static IState handleRemoveLine(IState state, Scanner scanner) {
        List<Set<Coordinate>> availableLines = state.lines();
        
        while (true) {
            System.out.println("\n--- Choix de la ligne à supprimer ---");
            for (int i = 0; i < availableLines.size(); i++) {
                System.out.print("Ligne " + (i + 1) + " : ");
                for (Coordinate c : availableLines.get(i)) {
                    Point p = c.to2DCoordinate();
                    System.out.print("[" + p.y() + "," + p.x() + "] ");
                }
                System.out.println();
            }

            System.out.print("Entrez le NUMERO de la ligne à supprimer : ");
            int choice = readInt(scanner);
            if (choice < 1 || choice > availableLines.size()) {
                System.out.println("Numéro de ligne invalide. Réessayez.");
                continue;
            }
            Set<Coordinate> chosenLine = availableLines.get(choice - 1);

            System.out.print("Coordonnées de l'ANNEAU à retirer (Format: y x) : ");
            int ringY = readInt(scanner);
            int ringX = readInt(scanner);
            Coordinate chosenRing = findCoordinate(state, ringY, ringX);

            if (chosenRing == null) {
                System.out.println(" Coordonnées introuvables. Réessayez.");
                continue;
            }

            try {
                return state.removeLine(new RemoveLine(chosenLine, chosenRing));
            } catch (Exception e) {
                System.out.println("Supression impossible"+ e.getMessage());
            }
        }
    }

    
    private static Coordinate findCoordinate(IState state, int y, int x) {
        for (Coordinate coord : state.board().keySet()) {
            Point p = coord.to2DCoordinate();
            if (p.y() == y && p.x() == x) {
                return coord;
            }
        }
        return null;
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

        int height = maxY - minY + 1;
        int width = maxX - minX + 1;
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

        System.out.println("\n==================== PLATEAU DE JEU ====================");
        
        System.out.print("    ");
        for (int x = minX; x <= maxX; x++) {
            System.out.print(x % 10 == 0 ? (x / 10) : " ");
        }
        System.out.println();
        
        System.out.print("    ");
        for (int x = minX; x <= maxX; x++) {
            System.out.print(Math.abs(x % 10));
        }
        System.out.println("\n    " + "—".repeat(width));

        for (int y = 0; y < height; y++) {
            int currentY = minY + y;
            System.out.printf("%2d | ", currentY);
            for (int x = 0; x < width; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
        System.out.println("========================================================");
    }

    
    private static char getTokenChar(Token token) {
        if (token == null) return '_';
        if (token instanceof Ring) return token.getTeam() == Team.WHITE ? 'o' : 'O';
        if (token instanceof Pawn) return token.getTeam() == Team.WHITE ? '.' : 'x';
        return '?';
    }

    
    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Saisie invalide. Saisir un nombre entier : ");
            }
        }
    }
}