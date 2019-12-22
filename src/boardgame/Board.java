package boardgame;

public class Board {

    private int row;
    private int column;
    private Piece[][] pieces;

    public Board(int row, int column) {
        this.row = row;
        this.column = column;
        pieces = new Piece[row][column];

    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Piece piece(int row, int column) {
        return pieces[row][column];
    }

    public Piece piece(Position position) {
        return pieces[position.getRow()][position.getColumn()];
    }

    //Lugar das peças
    public void placePiece(Piece piece, Position position) {
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

}
