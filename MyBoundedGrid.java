import java.util.ArrayList;

/**
 * @author Vani Mohindra
 *
 * A MyBoundedGrid is a rectangular grid with a finite number of rows and columns.
 * 
 * @param <E>: The type of object stored in the grid 
 */
public class MyBoundedGrid<E>
{
	private Object[][] occupantArray; // the array storing the grid elements

	
	/**
	 * Constructs an empty MyBoundedGrid with the given dimension
	 * 
	 * @param rows: the number in the rows in the grid
	 * @param cols
	 * 
	 * @precondition rows > 0 and cols > 0 
	 */
	public MyBoundedGrid(int rows, int cols)
	{
		occupantArray = new Object[rows][cols];
	}
	
	/**
	 * Returns the number of rows in this grid
	 * 
	 * @return the number of rows in this grid
	 */
	public int getNumRows()
	{
		return occupantArray.length;
	}

	/**
	 * Returns the number of columns in this grid
	 * 
	 * @return the number of columns in this grid
	 */
	public int getNumCols()
	{
		return occupantArray[0].length;
	}

	/**
	 * Returns true if the location is valid in this grid; false otherwise
	 * 
	 * @param loc the location to be checked for validity 
	 * @precondition: loc is not null 
	 *
	 * @return true if the specified location is in the grid; false otherwise
	 */
	public boolean isValid(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		if (row<0 || col<0) return false;
		if (col < occupantArray[0].length)
			if (row < occupantArray.length)
				return true;
		return false;
	}
	
	/**
	 * Returns the object at the specified location (or null if the location is unoccupied)
	 * 
	 * @param loc: the location whose contents are to be returned
	 * 
	 * @precondition the specified location is valid in this grid
	 * 
	 * @return the object at location loc (or null if the location is unoccupied)
	 */
	public E get(Location loc)
	{
		int row = loc.getRow();
		int col = loc.getCol();
		return (E) occupantArray[row][col];
	}

	/**
	 * Puts the given object at a specified position in the grid and returns the object previously
	 * stored there (or null if the location was unoccupied)
	 * 
	 * @param loc: the location to be updated
	 * @param obj: the objct to be placed in the given location
	 * 
	 * @precondition loc is valid in this grid puts obj at location loc in this grid 
	 * and returns the object previously at that location (or null if the location is unoccupied)
	 * 
	 * @return the object previously at that location (or null if the location is unoccupied)
	 */
	public E put(Location loc, E obj)
	{
		int row = loc.getRow(); int col = loc.getCol();
		E temp = (E) occupantArray[row][col];
		occupantArray[row][col] = obj;
		return temp;
	}

	
	/**
	 * Removes the object at location loc from this grid and returns the object that was removed
	 *  (or null if the location is unoccupied)
	 * 
	 * @param loc: the location of the object to be removed
	 * 
	 * @precondition loc is valid in this grid
	 * 
	 * @return the object that was removed (or null if the location is unoccupied)
	 */
	public E remove(Location loc)
	{
		int row = loc.getRow(); int col = loc.getCol();
		E temp = (E) occupantArray[row][col];
		occupantArray[row][col] = null;
		return temp;
	}

	/**
	 * Returns an array list of all occupied locations in this grid
	 * 
	 * @return an array list of all occupied locations in this grid
	 */
	public ArrayList<Location> getOccupiedLocations()
	{
		ArrayList<Location> occupiedSpots = new ArrayList<Location>();
		for (int i=0; i< occupantArray.length; i++)
		{
			for (int j=0; j <occupantArray[i].length; j++)
			{
				if (occupantArray[i][j]!=null)
				{
					occupiedSpots.add(new Location(i, j));
				}
			}
		}
		return occupiedSpots;
	}
}