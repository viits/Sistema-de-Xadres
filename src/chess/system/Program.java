package chess.system;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        ChessMatch partida = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();
        while (true) {
            try {
                UI.clearScreen();
                UI.printMatch(partida, captured);
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(ler);

                boolean[][] possibleMoves = partida.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(partida.getPieces(), possibleMoves);

                System.out.println();

                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(ler);

                ChessPiece capturedPiece = partida.performChessMove(source, target);
                if (capturedPiece != null) {
                    captured.add(capturedPiece);
                }
            } catch (ChessException e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                ler.nextLine();
            }
        }

    }

}
