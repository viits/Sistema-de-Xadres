package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;
import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private int turn;
    private Color currentPlayer;
    private Board board;
    
    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8);
        this.turn = 1;
        this.currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessPiece[][] getPieces() {
        ChessPiece mat[][] = new ChessPiece[board.getRow()][board.getColumn()];
        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getColumn(); j++) {
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validatePosition(position);
        return board.piece(position).possiblesMove();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validatePosition(source);
        validadeTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private void validadeTargetPosition(Position source, Position target) {
        if (!board.piece(source).possiblesMove(target)) {
            throw new ChessException("A peca escolhida nao pode ir para essa posicao");
        }
    }

    private Piece makeMove(Position source, Position target) {
        Piece p = board.removePiece(source);
        Piece captured = board.removePiece(target);
        board.placePiece(p, target);
        if(captured != null){
            piecesOnTheBoard.remove(captured);
            capturedPieces.add(captured);
        }
        return captured;
    }

    private void validatePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("Nao tem peça nessa posição");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A peca escolhida nao e sua");
        }
        if (!board.piece(position).isThereAnyPossibleMove()) {
            throw new ChessException("Nao existe movimentos possiveis para a peça escolhida");
        }
    }

    private void nextTurn() {
        this.turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

    }

    private void placeNewPiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    

    private void initialSetup() {
        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }

}
