package chess;

import java.util.Collection;
import java.util.HashSet;

public class QueenMovesCalculator extends PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new HashSet<>();
        allMoves.addAll(new BishopMovesCalculator().pieceMoves(board, myPosition));
        allMoves.addAll(new RookMovesCalculator().pieceMoves(board, myPosition));
        return allMoves;
    }

}
