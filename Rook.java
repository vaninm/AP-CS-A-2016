import java.awt.Color;
import java.util.ArrayList;
/**
 * The rook piece can move horizontally or vertically in one direction 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class Rook extends Piece 
{
	/**
	 * Constructs a rook that looks like the image referred to by fileName and that plays for the given color
	 * @param col: the color that the rook plays for
	 * @param fileName: the name of the image that represents the rook
	 */
	public Rook (Color col, String fileName)
	{
		super(col, fileName, 5);
	}

	/**
	 * Returns of all possible valid destinations for this rook
	 * @return an array of all possible valid destinations for this rook
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		this.sweep(locs,Location.NORTH);
		this.sweep(locs, Location.SOUTH);
		this.sweep(locs, Location.EAST);
		this.sweep(locs, Location.WEST);
		return locs;
	}
}