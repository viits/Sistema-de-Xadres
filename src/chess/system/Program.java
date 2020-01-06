package chess.system;

import boardgame.Board;
import boardgame.Position;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        ChessMatch partida = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(partida.getPieces());
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(ler);

                System.out.println();

                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(ler);

                ChessPiece capturedPiece = partida.performChessMove(source, target);
            }catch(ChessException e){
                System.out.println(e.getMessage());
                ler.nextLine();
            }catch(InputMismatchException e){
                System.out.println(e.getMessage());
                ler.nextLine();
            }
        }

    }

}
