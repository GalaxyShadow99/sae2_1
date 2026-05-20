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
import java.util.Scanner;
import java.util.Set;

public class IAMain {
    
    public static void main(String[] args) throws DifferentAxisException {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== BIENVENUE DANS OTHELLO HEXAGONAL ===");
        
        IState state;
        
        try {
            IFactory factory = new FactoryDoubled();
            state = factory.emptyState();
        } catch (Exception e) {
            System.out.println("Erreur : Impossible de charger le terrain vide.");
            scanner.close();
            return;
        }
        
        state = initializeRandomRings(state);
        System.out.println("Terrain initialisé avec 5 anneaux par équipe placés aleatoirement.\n");
        
        System.out.print("Choisissez la profondeur de l'IA (chiffre entier) : ");
        int profondeur = readInt(scanner);
        
        AI ai = new MiniMaxAI(profondeur, state, Team.BLACK); 
        
        
        boolean gameRunning = true;

        while (gameRunning) {
            displayBoard(state);

            Team winner = state.winner();
            if (winner != null) {
                System.out.println("Félicitations ! L'equipe " + winner + " a gagné la partie !");
                break;
            }

            boolean isHumanTurn = (state.turn() == Team.WHITE);

            if (state.lines() != null && !state.lines().isEmpty()) {
                System.out.println("Alignement de 5 pions detecte ! L'équipe " + state.turn() + " doit supprimer une ligne et un anneau.");
                
                if (isHumanTurn) {
                    state = handleRemoveLine(state, scanner);
                } else {
                    System.out.println("L'IA (BLACK) reflechit pour supprimer une ligne...");
                    Action aiAction = ai.chooseMove(state);
                    try {
                        state = state.removeLine((RemoveLine) aiAction);
                        System.out.println("L'IA a retiré une ligne et un anneau.");
                    } catch (Exception e) {
                        System.out.println("Erreur IA lors du RemoveLine : " + e.getMessage());
                        gameRunning = false;
                    }
                }
                continue; 
            }

            System.out.println("C'est au tour de l'equipe : " + state.turn());
            
            if (isHumanTurn) {
                state = handlePlayerMove(state, scanner);
            } else {
                System.out.println("L'IA (BLACK) reflechit a son deplacement...");
                Action aiAction = ai.chooseMove(state);
                try {
                    state = state.move((Move) aiAction);
                    System.out.println("L'IA a joue.");
                } catch (Exception e) {
                    System.out.println("Erreur IA lors du Move : " + e.getMessage());
                    gameRunning = false;
                }
            }
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
            System.out.println("\n--- Deplacement d'un anneau ---");
            System.out.println("Regardez le plateau pour trouver un anneau de votre equipe (◯ ou ●).");
            
            System.out.print("Entrez la colonne (X) de l'anneau de depart : ");
            int fromX = readInt(scanner);
            System.out.print("Entrez la ligne (Y) de l'anneau de depart : ");
            int fromY = readInt(scanner);
            Coordinate from = findCoordinate(state, fromX, fromY);

            System.out.print("Entrez la colonne (X) de la case d'arrivée : ");
            int toX = readInt(scanner);
            System.out.print("Entrez la ligne (Y) de la case d'arrivée : ");
            int toY = readInt(scanner);
            Coordinate to = findCoordinate(state, toX, toY);

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
            System.out.println("\n--- Choix de la ligne a supprimer ---");
            for (int i = 0; i < availableLines.size(); i++) {
                System.out.print("Ligne " + (i + 1) + " : ");
                for (Coordinate c : availableLines.get(i)) {
                    Point p = c.to2DCoordinate();
                    System.out.print("[X:" + p.x() + ", Y:" + p.y() + "] ");
                }
                System.out.println();
            }

            System.out.print("Entrez le NUMERO de la ligne a supprimer : ");
            int choice = readInt(scanner);
            if (choice < 1 || choice > availableLines.size()) {
                System.out.println("Numero de ligne invalide. Reessayez.");
                continue;
            }
            Set<Coordinate> chosenLine = availableLines.get(choice - 1);

            System.out.println("Choix de l'anneau à retirer du plateau :");
            System.out.print("Colonne (X) de l'anneau : ");
            int ringX = readInt(scanner);
            System.out.print("Ligne (Y) de l'anneau : ");
            int ringY = readInt(scanner);
            Coordinate chosenRing = findCoordinate(state, ringX, ringY);

            if (chosenRing == null) {
                System.out.println("Coordonnées de l'anneau introuvables. Réessayez.");
                continue;
            }

            try {
                return state.removeLine(new RemoveLine(chosenLine, chosenRing));
            } catch (Exception e) {
                System.out.println("Suppression impossible : " + e.getMessage());
            }
        }
    }

    private static Coordinate findCoordinate(IState state, int realX, int realY) {
        for (Coordinate coord : state.board().keySet()) {
            Point p = coord.to2DCoordinate();
            if (p.x() == realX && p.y() == realY) {
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

    private static char getTokenChar(Token token) {
        if (token == null) return '•';
        if (token instanceof Ring) return token.getTeam() == Team.WHITE ? '◯' : '●';
        if (token instanceof Pawn) return token.getTeam() == Team.WHITE ? '♙' : '♟';
        return '?';
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.print("Saisie invalide. Saisis un nombre entier : ");
            }
        }
    }
}