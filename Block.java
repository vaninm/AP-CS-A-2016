import java.awt.Color;
/**
 * The Block class contains information about this block, including the grid that it is in and the location in this grid.
 * It provides methods to remove, add, and reposition this block, updating the information of the grid as well. 
 * @author Vani Mohindra
 * @version 3/20/17
 */
public class Block
{
    private MyBoundedGrid<Block> grid;
    private Location location;
    private Color color;

    /**
     * Constructs a blue block, because blue is the greatest color ever!
     */
    public Block()
    {
        color = Color.BLUE;
        grid = null;
        location = null;
    }

    /**
     * Gets the color of this block
     * 
     * @return the color of this block
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * @param newColor: the color to change this block to 
     * 
     * Changes the color of this block to the given color.
     */
    public void setColor(Color newColor)
    {
        color = newColor;
    }

    /**
     * Returns the grid that the block is in, or null if the block is not contained in a grid
     * 
     * @return the grid that this block is in
     */
    public MyBoundedGrid<Block> getGrid()
    {
        return grid;
    }

    /**
     * Gets the location of this block, or null if this block is not contained in a grid
     * @return the location of the block if contained in a grid; null otherwise
     */
    public Location getLocation()
    {
    	return location;
    }

    /**
     * @precondition: this block is contained in a grid
     * 
     * Removes this block from its grid
     */
    public void removeSelfFromGrid()
    {
    	grid.remove(location);
        grid = null;
        location = null;
    }

    /**
     * @precondition This block is not contained in a grid; The given location exists in the given grid
     * 
     * Puts this block into a specified location in a specified grid.
     * If there is another block at that place, it is removed. 
     * 
     * @param gr:
     * @param loc
     */
    public void putSelfInGrid(MyBoundedGrid<Block> gr, Location loc)
    {
    	if (!gr.isValid(loc)) return;
    	if (gr.get(loc)!=null)
    	{
    		Block b = gr.get(loc);
        	b.removeSelfFromGrid();
    	}
    	gr.put(loc, this);
    	location = loc;
    	grid = gr;
    }

    /**
     * @precondition: this block is contained in a grid
     * @precondition: the location supplied to move the block to exists in the current grid
     * 
     * Moves this block to a new location on the grid. If there is another block at the new location, it is removed. 
     * 
     * @param newLocation: where to move the block to on the current grid
     */
    public void moveTo(Location newLocation)
    {
    	MyBoundedGrid g = grid;
    	this.removeSelfFromGrid();
    	grid = g;
    	putSelfInGrid(grid, newLocation);
    }

    /**
     * Returns a string with the location and color of this block
     * @return a string representation of the block, containing both location and color
     */
    public String toString()
    {
        return "Block[location=" + location + ",color=" + color + "]";
    }
}