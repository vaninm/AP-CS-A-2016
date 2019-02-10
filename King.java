import java.awt.Color;
import java.util.ArrayList;
/**
 * A King can move  one space in any direction
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class King extends Piece
{
	/**
	 * Constructs a king of the given color that is represented by an image of the given name
	 * @param col: the the side that this King plays for
	 * @param fileName: the name of the image that represents this King
	 */
	public King (Color col, String fileName)
	{
		super(col, fileName, 1000);
	}
	
	/**
	 * Returns a list of all possible destinations for this King. A King can move
	 * 1 unit in any direction
	 * @return an arraylist of all possible valid destinations for this King
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		int row = this.getLocation().getRow();
		int col = this.getLocation().getCol();
		for (int i=row-1; i<=row+1; i++) 
		{
			for (int j=col-1; j<=col+1; j++)
			{
				Location loc = new Location(i,j);
				if (isValidDestination(loc)) 
				{
					locs.add(loc);
				}
			}
		}
		return locs;
	}
}
