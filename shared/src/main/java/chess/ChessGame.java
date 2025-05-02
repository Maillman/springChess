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

    boolean WQCastle;
    boolean WKCastle;
    boolean BQCastle;
    boolean BKCastle;

    public ChessGame() {
        this.board = new ChessBoard();
        this.board.resetBoard();
        this.teamTurn = TeamColor.WHITE;
        WQCastle = true;
        WKCastle = true;
        BQCastle = true;
        BKCastle = true;
    }

    public ChessGame(ChessBoard board, TeamColor team) {
        this.board = new ChessBoard(board);
        this.teamTurn = team;
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
        ChessPiece piece = board.getPiece(startPosition);
        if (piece == null) {
            return new HashSet<>();
        }
        TeamColor pieceColor = piece.getTeamColor();
        Collection<ChessMove> possibleMoves = piece.pieceMoves(board, startPosition);
        Collection<ChessMove> validMoves = new HashSet<>();
        Collection validMovesForColor = allValidMovesForColor(pieceColor);
        for (ChessMove move : possibleMoves) {
            if (validMovesForColor.contains(move)) {
                validMoves.add(move);
            }
        }
        validMoves.addAll(castlingMoves(validMoves, startPosition, piece, pieceColor));
        return validMoves;
    }

    private Collection<ChessMove> castlingMoves(Collection<ChessMove> validMoves, ChessPosition startPosition, ChessPiece piece, TeamColor pieceColor) {
        if (piece.getPieceType() != ChessPiece.PieceType.KING) {
            return new HashSet<>();
        }
        Collection<ChessMove> castlingMoves = new HashSet<>();
        //int kingRow = pieceColor == TeamColor.WHITE ? 1 : 8;
        switch (pieceColor) {
            case WHITE:
                if (WKCastle) {
                    if (validMoves.contains(new ChessMove(startPosition, new ChessPosition(1, 6), null)) && validMoves(new ChessPosition(1, 8)).contains(new ChessMove(new ChessPosition(1, 8), new ChessPosition(1, 6), null))) {
                        ChessGame checkGame = new ChessGame(this.board, this.teamTurn);
                        ChessMove castlingMove = new ChessMove(startPosition, new ChessPosition(1, 7), null);
                        checkGame.forceMoveWithoutTeamTurn(castlingMove);
                        if (checkGame.isInCheck(pieceColor)) {
                            castlingMoves.add(castlingMove);
                        }
                    }
                }
                if (WQCastle) {

                }
                break;
            case BLACK:

                break;
            default:
                throw new AssertionError();
        }
        return castlingMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to perform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece piece = board.getPiece(move.getStartPosition());
        if (piece == null) {
            throw new InvalidMoveException("No piece there");
        }
        TeamColor colorOfPiece = piece.getTeamColor();
        if (colorOfPiece != this.teamTurn) {
            throw new InvalidMoveException("Piece moved out of turn");
        }
        Collection<ChessMove> validMoves = validMoves(move.getStartPosition());
        if (!validMoves.contains(move)) {
            throw new InvalidMoveException("Piece can't go there");
        }
        forceMoveWithoutTeamTurn(move);
        switch (teamTurn) {
            case WHITE:
                this.teamTurn = TeamColor.BLACK;
                break;
            case BLACK:
                this.teamTurn = TeamColor.WHITE;
                break;
            default:
                this.teamTurn = TeamColor.WHITE;
        }
    }

    public void forceMoveWithoutTeamTurn(ChessMove move) {
        ChessPiece piece = this.board.getPiece(move.getStartPosition());
        if (piece == null) {
            return;
        }
        if (move.getPromotionPiece() != null) {
            ChessPiece newPiece = new ChessPiece(piece.getTeamColor(), move.getPromotionPiece());
            this.board.addPiece(move.getEndPosition(), newPiece);
        } else {
            this.board.addPiece(move.getEndPosition(), piece);
        }
        this.board.addPiece(move.getStartPosition(), null);
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

    private Collection<ChessMove> allValidMovesForColor(TeamColor teamColor) {
        Collection<ChessMove> allValidMoves = allMovesForColor(teamColor);
        Collection<ChessMove> movesToRemove = new HashSet<>();
        for (ChessMove move : allValidMoves) {
            //If the move still yields isInCheck, remove the move from the list
            ChessGame checkGame = new ChessGame(this.board, this.teamTurn);
            checkGame.forceMoveWithoutTeamTurn(move);
            if (checkGame.isInCheck(teamColor)) {
                movesToRemove.add(move);
            }
        }
        for (ChessMove move : movesToRemove) {
            allValidMoves.remove(move);
        }
        return allValidMoves;
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
        //Checkmate means that the current team has no possible valid moves.
        Collection<ChessMove> allValidMoves = allValidMovesForColor(teamColor);
        return allValidMoves.isEmpty();
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
        //Stalemate means that the current team has no possible moves.
        Collection<ChessMove> allValidMoves = allValidMovesForColor(teamColor);
        return allValidMoves.isEmpty();
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
