package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter signature of the
 * existing methods.
 */
public class ChessGame {

    ChessBoard board;

    TeamColor teamTurn;

    public ChessGame() {
        this.board = new ChessBoard();
        this.board.resetBoard();
        this.teamTurn = TeamColor.WHITE;
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.teamTurn;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.teamTurn = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        //Loop through all the pieces of the opposing color, get all the moves.
        //Find the king for our given color and see if any of the moves end at that spot.
        TeamColor oppColor = getOpposingColor(teamColor);
        Collection<ChessMove> allOpposingMoves = allMovesForColor(oppColor);
        ChessPosition kingPosition = getKingPosition(teamColor);
        for (ChessMove move : allOpposingMoves) {
            if (move.getEndPosition().equals(kingPosition)) {
                return true;
            }
        }
        return false;
    }

    private TeamColor getOpposingColor(TeamColor teamColor) {
        return switch (teamColor) {
            case WHITE ->
                TeamColor.BLACK;
            case BLACK ->
                TeamColor.WHITE;
            default ->
                TeamColor.WHITE;
        };
    }

    private Collection<ChessMove> allMovesForColor(TeamColor teamColor) {
        Collection<ChessMove> allMoves = new HashSet<>();
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition curChessPosition = new ChessPosition(i, j);
                ChessPiece curChessPiece = this.board.getPiece(curChessPosition);
                if (curChessPiece != null && curChessPiece.getTeamColor() == teamColor) {
                    allMoves.addAll(curChessPiece.pieceMoves(this.board, curChessPosition));
                }
            }
        }
        return allMoves;
    }

    private ChessPosition getKingPosition(TeamColor teamColor) {
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                ChessPosition curChessPosition = new ChessPosition(i, j);
                ChessPiece curChessPiece = this.board.getPiece(curChessPosition);
                if (curChessPiece != null && curChessPiece.getTeamColor() == teamColor && curChessPiece.getPieceType() == ChessPiece.PieceType.KING) {
                    return curChessPosition;
                }
            }
        }
        return null;
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if (!isInCheck(teamColor)) {
            return false;
        }
        //Do some things for isInCheckmate
        return false;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as
     * having no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        if (isInCheck(teamColor)) {
            return false;
        }
        //Do some things for isInStalemate
        //Stalemate means that the current team has no possible moves.
        return false;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return this.board;
    }
}
