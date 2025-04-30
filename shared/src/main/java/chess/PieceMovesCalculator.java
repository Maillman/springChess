package chess;

public abstract class PieceMovesCalculator {

    public boolean inBounds(ChessPosition thisPosition) {
        return (thisPosition.getRow() > 0 && thisPosition.getColumn() > 0
                && thisPosition.getRow() < 9 && thisPosition.getColumn() < 8);
    }
}
