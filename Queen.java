import java.awt.Color;
import java.util.ArrayList;
/**
 * The Queen piece can move in any direction for any distance and is worth 9 points. 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class Queen extends Piece 
{
	/**
	 * Constructs a queen object that looks like the image referred to by the given file name and that is on the given color's side
	 * @param col: the side that this Queen is on
	 * @param fileName: the name of the image that will represent this Queen
	 */
	public Queen (Color col, String fileName)
	{
		super(col, fileName, 9);
	}

	/**
	 * Returns all possible places for the queen to go. A queen can move in any direction 
	 * until running into an obstacle
	 * @return all valid locations for the queen to go
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		this.sweep(locs,Location.NORTHEAST);
		this.sweep(locs, Location.NORTHWEST);
		this.sweep(locs, Location.SOUTHEAST);
		this.sweep(locs, Location.SOUTHWEST);
		this.sweep(locs,Location.NORTH);
		this.sweep(locs, Location.SOUTH);
		this.sweep(locs, Location.EAST);
		this.sweep(locs, Location.WEST);
		return locs;
	}
}
