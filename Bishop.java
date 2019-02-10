import java.awt.Color;
import java.util.ArrayList;
/**
 * A Bishop piece can move diagonally in any direction and is worth 3 points
 * @author Vani Mohindra
 * @version April 3, 2017
 *
 */
public class Bishop extends Piece 
{
	/**
	 * creates a new Bishop that is represented graphically by the image with the given fileName 
	 * and that plays on the side of the given color
	 * @param col: the color of the Bishop
	 * @param fileName: the name of the image that will represent this Bishop
	 */
	public Bishop (Color col, String fileName)
	{
		super(col, fileName, 3);
	}

	/**
	 * Implementation of Piece's abstract method: returns a list of all possible valid destinations for this Bishop
	 * @return an ArrayList of all possible valid destinations for this Bishop
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		this.sweep(locs,Location.NORTHEAST);
		this.sweep(locs, Location.NORTHWEST);
		this.sweep(locs, Location.SOUTHEAST);
		this.sweep(locs, Location.SOUTHWEST);
		return locs;
	}
}
