
/**
 * Represents a single move, in which a piece moves to a destination location.
 * Since a move can be undone, also keeps track of the source location and any captured victim
 * 
 * @author Anu Datar
 */
public class Move
{
	private Piece piece;          //the piece being moved
	private Location source;      //the location being moved from
	private Location destination; //the location being moved to
	private Piece victim;         //any captured piece at the destination

	/**
	 * Constructs a new move for moving the given piece to the given destination
	 * @param piece: the piece to be moved
	 * @param destination: the destination of the given piece
	 */
	public Move(Piece piece, Location destination)
	{
		this.piece = piece;
		this.source = piece.getLocation();
		this.destination = destination;
		this.victim = piece.getBoard().get(destination);

		if (source.equals(destination))
			throw new IllegalArgumentException("Both source and dest are " + source);
	}

	/**
	 * returns the piece being moved
	 * @return the piece being moved
	 */
	public Piece getPiece()
	{
		return piece;
	}

	/**
	 * returns the location being moved from
	 * @return the location being moved from
	 */
	public Location getSource()
	{
		return source;
	}

	/**
	 * Returns the lcoation being moved to 
	 * @return the location being moved to
	 */
	public Location getDestination()
	{
		return destination;
	}

	/**
	 * returns the piece being captured at the destination, if any
	 * @return the piece being captured at the destination, if any
	 */
	public Piece getVictim()
	{
		return victim;
	}

	/**
	 * returns a string description of the move
	 * @return a string description of the move
	 */
	public String toString()
	{
		return piece + " from " + source + " to " + destination + " containing " + victim;
	}

	/**
	 * Returns true if this move is equivalent to the given one
	 * @return true if this move is equivalent to the given one; false otherwise
	 */
	public boolean equals(Object x)
	{
		Move other = (Move)x;
		return piece == other.getPiece() && source.equals(other.getSource()) &&
			destination.equals(other.getDestination()) && victim == other.getVictim();
	}

	/**
	 * returns a hash code for this move, such that equivalent moves have the same hash code
	 * @return a hashcode for this move
	 */
	public int hashCode()
	{
		return piece.hashCode() + source.hashCode() + destination.hashCode();
	}
}