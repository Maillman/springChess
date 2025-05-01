package chess;

import java.util.Collection;
import java.util.HashSet;

public class RookMovesCalculator extends PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new HashSet<>();
        allMoves.addAll(addAllMoves(board, myPosition, 1, 0, true));
        allMoves.addAll(addAllMoves(board, myPosition, -1, 0, true));
        allMoves.addAll(addAllMoves(board, myPosition, 0, 1, true));
        allMoves.addAll(addAllMoves(board, myPosition, 0, -1, true));
        return allMoves;
    }

}
