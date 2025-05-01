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
        ChessPosition forwardPosition = new ChessPosition(myPosition.getRow() + forwardMove, myPosition.getColumn());
        //Forward
        if (board.getPiece(forwardPosition) == null) {
            if (myPosition.getRow() == 9 - startRow) {
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.QUEEN));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.ROOK));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.BISHOP));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.KNIGHT));
            } else {
                allMoves.add(new ChessMove(myPosition, forwardPosition, null));
            }
            if (myPosition.getRow() == startRow) {
                ChessPosition extraForwardPosition = new ChessPosition(myPosition.getRow() + forwardMove * 2, myPosition.getColumn());
                if (board.getPiece(extraForwardPosition) == null) {
                    allMoves.add(new ChessMove(myPosition, extraForwardPosition, null));
                }
            }
        }
        //Capturing
        return allMoves;
    }

}
