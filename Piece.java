import java.awt.*;
import java.util.*;
/**
 * The abstract class Pieces encompasses all the game pieces used in a game of chess and 
 * contains methods to access properties such as point-value, graphic representation, the board 
 * that this piece is in, and its location in the grid. A piece can also be moved within the grid
 * or out of the grid and can capture other pieces of the opposite color. 
 * @author Anu Datar
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public abstract class Piece
{
	//the board this piece is on
	private Board board;

	//the location of this piece on the board
	private Location location;

	//the color of the piece
	private Color color;

	//the file used to display this piece
	private String imageFileName;

	//the approximate value of this piece in a game of chess
	private int value;

	/**
	 * Constructs a piece with the given attributes
	 * @param col: the color of the piece
	 * @param fileName: the name of the image that will represent the piece
	 * @param val: the point-value of the piece
	 */
	public Piece(Color col, String fileName, int val)
	{
		color = col;
		imageFileName = fileName;
		value = val;
	}

	/**
	 * returns the board that this piece is on
	 * @return the board that this piece is on
	 */
	public Board getBoard()
	{
		return board;
	}

	/**
	 * returns the location of this piece on the board
	 * @return the location of this piece on the board
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * returns the color of this piece
	 * @return the color of this piece
	 */
	public Color getColor()
	{
		return color;
	}

	/**
	 * returns the name of the file used to display this piece
	 * @return the name of the file used to display this piece
	 */
	public String getImageFileName()
	{
		return imageFileName;
	}

	/**
	 * returns a number representing the relative value of this piece
	 * @return a number representing the relative value of this piece
	 */
	public int getValue()
	{
		return value;
	}

    /**
     * Puts this piece into a board. If there is another piece at the given
     * location, it is removed.
     * Precondition: (1) This piece is not contained in a grid (2)
     * <code>loc</code> is valid in <code>gr</code>
     * @param brd the board into which this piece should be placed
     * @param loc the location into which the piece should be placed
     */
    public void putSelfInGrid(Board brd, Location loc)
    {
        if (board != null)
            throw new IllegalStateException(
                    "This piece is already contained in a board.");

        Piece piece = brd.get(loc);
        if (piece != null)
            piece.removeSelfFromGrid();
        brd.put(loc, this);
        board = brd;
        location = loc;
    }

    /**
     * Removes this piece from its board. 
     * Precondition: This piece is contained in a board
     */
    public void removeSelfFromGrid()
    {
        if (board == null)
            throw new IllegalStateException(
                    "This piece is not contained in a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");

        board.remove(location);
        board = null;
        location = null;
    }

    /**
     * Moves this piece to a new location. If there is another piece at the
     * given location, it is removed. 
     * Precondition: (1) This piece is contained in a grid (2)
     * <code>newLocation</code> is valid in the grid of this piece
     * @param newLocation the new location
     */
    public void moveTo(Location newLocation)
    {
        if (board == null)
            throw new IllegalStateException("This piece is not on a board.");
        if (board.get(location) != this)
            throw new IllegalStateException(
                    "The board contains a different piece at location "
                            + location + ".");
        if (!board.isValid(newLocation))
            throw new IllegalArgumentException("Location " + newLocation
                    + " is not valid.");

        if (newLocation.equals(location))
            return;
        board.remove(location);
        Piece other = board.get(newLocation);
        if (other != null)
            other.removeSelfFromGrid();
        location = newLocation;
        board.put(location, this);
    }
  
    /**
     * Determines whether or not the given destination is valid in the board containing this piece
     * @param loc: the destination for the piece
     * @return true, if destination is valid and is either empty or occupied by
     * another piece of a different color; false otherwise
     */
    public boolean isValidDestination(Location loc)
    {
    	if (!board.isValid(loc)) return false;
    	if (board.get(loc)==null) return true;
    	Piece p = board.get(loc);
    	return p.getColor()!=this.getColor();
    }
    
    /**
     * Abstract method: returns all possible valid destinations for this piece
     * @return an ArrayList of all valid destinations on the board for this piece
     */
    public abstract ArrayList<Location> destinations();
    
    /**
     * Modifies a list of possible destinations for the piece by adding valid destinations in the given direction
     * @param dests: possible destinations for the piece
     * @param direction: the direction in which the piece will move 
     */
    public void sweep (ArrayList<Location> dests, int direction)
    {
    	if (direction == Location.NORTH)
    	{
    		Location loc = new Location (this.getLocation().getRow()-1, this.getLocation().getCol());
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()-1, loc.getCol());
    		}
    	}
    	else if (direction == Location.SOUTH)
    	{
    		Location loc = new Location (this.getLocation().getRow()+1, this.getLocation().getCol());
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()+1, loc.getCol());
    		}
    	}
    	else if (direction == Location.EAST)
    	{
    		Location loc = new Location (this.getLocation().getRow(), this.getLocation().getCol()+1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow(), loc.getCol()+1);
    		}
    	}
    	else if (direction == Location.WEST)
    	{
    		Location loc = new Location (this.getLocation().getRow(), this.getLocation().getCol()-1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow(), loc.getCol()-1);
    		}
    	}
    	else if (direction == Location.NORTHEAST)
    	{
    		Location loc = new Location (this.getLocation().getRow()-1, this.getLocation().getCol()+1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()-1, loc.getCol()+1);
    		}
    	}
    	else if (direction == Location.NORTHWEST)
    	{
    		Location loc = new Location (this.getLocation().getRow()-1, this.getLocation().getCol()-1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()-1, loc.getCol()-1);
    		}
    	}
    	else if (direction == Location.SOUTHEAST)
    	{
    		Location loc = new Location (this.getLocation().getRow()+1, this.getLocation().getCol()+1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()+1, loc.getCol()+1);
    		}
    	}
    	else if (direction == Location.SOUTHWEST)
    	{
    		Location loc = new Location (this.getLocation().getRow()+1, this.getLocation().getCol()-1);
    		while (isValidDestination(loc))
    		{
    			dests.add(loc);
    			if (this.getBoard().get(loc)!=null) return;
    			loc = new Location (loc.getRow()+1, loc.getCol()-1);
    		}
    	}
    }
}