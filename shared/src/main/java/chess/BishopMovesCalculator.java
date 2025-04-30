package chess;

import java.util.Collection;
import java.util.HashSet;

public class BishopMovesCalculator extends PieceMovesCalculator {

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new HashSet<>();
        allMoves.addAll(addAllMoves(board, myPosition, 1, 1, true));
        allMoves.addAll(addAllMoves(board, myPosition, -1, 1, true));
        allMoves.addAll(addAllMoves(board, myPosition, 1, -1, true));
        allMoves.addAll(addAllMoves(board, myPosition, -1, -1, true));
        return allMoves;
    }

}
