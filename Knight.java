import java.awt.Color;
import java.util.ArrayList;
/**
 * A Knight can hop either 2 spaces horizontally or vertically followed by 1 space horizontally or vertically, or vice versa.
 * A knight is worth 3 points. 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class Knight extends Piece 
{
	/**
	 * Constructs a knight that plays for the given color's side and that is represented by the image with the given name
	 * @param col: the side the knight plays for
	 * @param fileName: then name of the image that represents the knight
	 */
	public Knight (Color col, String fileName)
	{
		super(col, fileName, 3);
	}

	/**
	 * Returns a list of all possible valid destinations for the knight. 
	 * A knight can move either 1 or 2 units horizontally, followed by 
	 * 2 or 1 units, respectively, vertically
	 * @return an ArrayList of valid destinations for this Knight
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		int r = this.getLocation().getRow();
		int c = this.getLocation().getCol();
		
		Location loc1 = new Location (r-2, c+1);
		if (isValidDestination(loc1)) locs.add(loc1);
		Location loc2 = new Location (r-2, c-1);
		if (isValidDestination(loc2)) locs.add(loc2);
		Location loc3 = new Location (r-1, c+2);
		if (isValidDestination(loc3)) locs.add(loc3);
		Location loc4 = new Location (r-1, c-2);
		if (isValidDestination(loc4)) locs.add(loc4);
		Location loc5 = new Location (r+1, c-2);
		if (isValidDestination(loc5)) locs.add(loc5);
		Location loc6 = new Location (r+1, c+2);
		if (isValidDestination(loc6)) locs.add(loc6);
		Location loc7 = new Location (r+2, c-1);
		if (isValidDestination(loc7)) locs.add(loc7);
		Location loc8 = new Location (r+2, c+1);
		if (isValidDestination(loc8)) locs.add(loc8);
		return locs;
	}
}