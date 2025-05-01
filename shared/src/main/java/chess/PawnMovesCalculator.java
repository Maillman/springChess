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

    public void checkAndAddPosition(Collection<ChessMove> allMoves, ChessBoard board, ChessPosition myPosition, ChessPosition checkPosition, int row) {
        if (inBounds(checkPosition) && board.getPiece(checkPosition) == null) {
            if (row == 9 - startRow) {
                allMoves.add(new ChessMove(myPosition, checkPosition, ChessPiece.PieceType.QUEEN));
                allMoves.add(new ChessMove(myPosition, checkPosition, ChessPiece.PieceType.ROOK));
                allMoves.add(new ChessMove(myPosition, checkPosition, ChessPiece.PieceType.BISHOP));
                allMoves.add(new ChessMove(myPosition, checkPosition, ChessPiece.PieceType.KNIGHT));
            } else {
                allMoves.add(new ChessMove(myPosition, checkPosition, null));
            }
        }
    }

    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        Collection<ChessMove> allMoves = new HashSet<>();
        int row = myPosition.getRow();
        int column = myPosition.getColumn();
        ChessPosition forwardPosition = new ChessPosition(row + forwardMove, column);
        //Forward
        if (board.getPiece(forwardPosition) == null) {
            if (row == 9 - startRow) {
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.QUEEN));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.ROOK));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.BISHOP));
                allMoves.add(new ChessMove(myPosition, forwardPosition, ChessPiece.PieceType.KNIGHT));
            } else {
                allMoves.add(new ChessMove(myPosition, forwardPosition, null));
            }
            if (row == startRow) {
                ChessPosition extraForwardPosition = new ChessPosition(row + forwardMove * 2, column);
                if (board.getPiece(extraForwardPosition) == null) {
                    allMoves.add(new ChessMove(myPosition, extraForwardPosition, null));
                }
            }
        }
        //Capturing
        ChessPosition forwardLeftPosition = new ChessPosition(row + forwardMove, column - 1);
        checkAndAddPosition(allMoves, board, myPosition, forwardLeftPosition, row);
        ChessPosition forwardRightPosition = new ChessPosition(row + forwardMove, column - 1);
        checkAndAddPosition(allMoves, board, myPosition, forwardRightPosition, row);
        return allMoves;
    }

}
