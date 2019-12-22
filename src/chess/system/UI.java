package chess.system;

import chess.ChessPiece;

public class UI {

    public static void printBoard(ChessPiece[][] piece) {
        for (int i = 0; i < piece.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < piece.length; j++) {
                printPiece(piece[i][j]);
            }
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }

    private static void printPiece(ChessPiece piece) {
        if (piece == null) {
            System.out.print("-");
        } else {
            System.out.print(piece);
        }
        System.out.print(" ");
    }

}
