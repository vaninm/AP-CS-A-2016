import java.awt.*;
import java.util.*;

/**
 * Represesents a rectangular game board, containing Piece objects.
 * @author Anu Datar
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class Board extends BoundedGrid<Piece>
{	
	/**
	 * Constructs a new Board with the given dimensions
	 */
	public Board()
	{
		super(8, 8);
	}

	/**
	 * Precondition:  move has already been made on the board
	 * @param move
	   Postcondition: piece has moved back to its source,
	   and any captured piece is returned to its location
	 */
	public void undoMove(Move move)
	{
		Piece piece = move.getPiece();
		Location source = move.getSource();
		Location dest = move.getDestination();
		Piece victim = move.getVictim();

		piece.moveTo(source);

		if (victim != null)
			victim.putSelfInGrid(piece.getBoard(), dest);
	}
	
	/**
	 * Returns a list of all possible moves that can be made by pieces on 
	 * the board of the given color
	 * @param color: the color of the pieces whose possible moves are to be determined
	 * @return an ArrayList of valid moves that can be made by pieces of the given color
	 */
	public ArrayList<Move> allMoves (Color color)
	{
		ArrayList<Location> occupied = this.getOccupiedLocations();
		ArrayList<Piece> coloredPieces = new ArrayList<Piece>();
		for (Location loc: occupied)
		{
			Piece p = this.get(loc);
			if (p.getColor().equals(color))
			{
				coloredPieces.add(p);
			}
		}
		ArrayList<Move> allMoves = new ArrayList<Move>();
		for (Piece piece: coloredPieces)
		{
			ArrayList<Location> dests = piece.destinations();
			for (Location loc: dests)
			{
				allMoves.add(new Move(piece,loc));
			}
		}
		return allMoves;
	}
	
	/**
	 * Moves pieces to satisfy the given move
	 * @param move: the move to be executed
	 */
	public void executeMove (Move move)
	{
		Piece piece = move.getPiece();
		Location newLoc = move.getDestination();
		piece.moveTo(newLoc);
	}
	
	/**
	 * Determines the status of the game
	 * @return 0 if game is not over; 1 if white has won; 2 if black has won
	 */
	public int gameOver ()
	{
		boolean whiteWin = true;
		boolean blackWin = true;
		for (Location loc: this.getOccupiedLocations())
		{
			if (this.get(loc).getImageFileName().equals("black_king.png"))
			{
				whiteWin = false;
			}
			else if (this.get(loc).getImageFileName().equals("white_king.png"))
			{
				blackWin = false;
			}
		}
		if (whiteWin) return 1;
		if (blackWin) return 2;
		return 0;
	}
}