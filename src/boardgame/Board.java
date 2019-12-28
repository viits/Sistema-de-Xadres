package boardgame;

public class Board {

    private int row;
    private int column;
    private Piece[][] pieces;

    public Board(int row, int column) {
        if (row < 1 || column < 1) {
            throw new BoardException("Erro criando tabuleiro, deve ter pelo menos uma linha e uma coluna");
        }
        this.row = row;
        this.column = column;
        pieces = new Piece[row][column];

    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Piece piece(int row, int column) {
        if (!positionExist(row, column)) {
            throw new BoardException("Posição nao esta no tabuleiro");
        }
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Posição nao esta no tabuleiro");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    //Lugar das peças
    public void placePiece(Piece piece, Position position) {
        if (thereIsAPiece(position)) {
            throw new BoardException("Ja tem uma peça nessa posição" + position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    public Piece removePiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Posição nao esta no tabuleiro");
        }
        if (piece(position) == null) {
            return null;
        }
        Piece aux = piece(position);
        aux.position = null;
        pieces[position.getRow()][position.getColumn()] = null;
        return aux;
    }

    private boolean positionExist(int rows, int columns) {
        return rows >= 0 && rows < this.row && columns >= 0 && columns < this.column;
    }

    public boolean positionExists(Position position) {
        return positionExist(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        if (!positionExists(position)) {
            throw new BoardException("Posição nao esta no tabuleiro");
        }
        return piece(position) != null;
    }

}
