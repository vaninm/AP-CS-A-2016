import java.awt.Color;
import java.util.ArrayList;
/**
 * A pawn is worth one point and can only move one square foreward, except (1) to capture another another piece, in 
 * which case it can move one square diagonally in any direction, or (2) on its first turn, when it can move 2 squares forewards
 * @author Vani Mohindra
 * @version April 3, 2018
 */
public class Pawn extends Piece 
{
	/**
	 * Constructs a Pawn that plays for the given color and is represented by the image of the given name
	 * @param col: the color that this pawn plays for
	 * @param fileName: the name of the image that will be used to represent this pawn
	 */
	public Pawn (Color col, String fileName)
	{
		super(col, fileName, 1);
	}

	/**
	 * Returns a list of possible destinations for this Pawn
	 * @return an ArrayList of possible destinations for this Pawn
	 */
	public ArrayList<Location> destinations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		int row = this.getLocation().getRow();
		int col = this.getLocation().getCol();
		
		if (this.getColor().equals(Color.WHITE))
		{
			// if there is an opponent piece 1 unit forward and diagonal, the pawn can capture it; otherwise it may not move diagonally
			Location diagonalLeft = new Location (row-1, col-1);
			if (isValidDestination(diagonalLeft) && this.getBoard().get(diagonalLeft)!=null) locs.add(diagonalLeft);
			Location diagonalRight = new Location (row-1, col+1);
			if (isValidDestination(diagonalRight) && this.getBoard().get(diagonalRight)!=null) locs.add(diagonalRight);
			// the pawn can only move one unit forward if that spot is empty
			Location oneAway = new Location (row-1, col);
			if (isValidDestination(oneAway) && this.getBoard().get(oneAway)==null) 
			{
				locs.add(oneAway);
				if (row == 6)
				{
					Location twoAway = new Location (row-2, col);
					if (isValidDestination(twoAway) && this.getBoard().get(twoAway)==null) locs.add(twoAway);
				}
			}
		}
		
		// same thing for black pawns
		if (this.getColor().equals(Color.BLACK))
		{
			Location diagonalLeft = new Location (row+1, col-1);
			if (isValidDestination(diagonalLeft) && this.getBoard().get(diagonalLeft)!=null) locs.add(diagonalLeft);
			Location diagonalRight = new Location (row+1, col+1);
			if (isValidDestination(diagonalRight) && this.getBoard().get(diagonalRight)!=null) locs.add(diagonalRight);
			Location oneAway = new Location (row+1, col);
			if (isValidDestination(oneAway) && this.getBoard().get(oneAway)==null)
			{
				locs.add(oneAway);
				if (row == 1)
				{
					Location twoAway = new Location (row+2, col);
					if (isValidDestination(twoAway) && this.getBoard().get(twoAway)==null) locs.add(twoAway);
				}
			}
		}
		return locs;
	}
}