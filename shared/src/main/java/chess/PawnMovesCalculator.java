package chess;

import java.util.Collection;
import java.util.HashSet;

public class PawnMovesCalculator extends PieceMovesCalculator {

    private final int startRow;

    private final int forwardMove;

    public PawnMovesCalculator(ChessGame.TeamColor pieceColor) {
        super(false);
        if (pieceColor == ChessGame.TeamColor.WHITE) {
            this.forwardMove = 1;
            this.startRow = 2;
        } else {
            this.forwardMove = -1;
            this.startRow = 7;
        }
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new HashSet<>();
        //if()
        return allMoves;
    }

}
