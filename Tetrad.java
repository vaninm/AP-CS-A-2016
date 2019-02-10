import java.util.concurrent.Semaphore;
import java.awt.Color;
/**
 * The Tetrad class stores information about a tetrad, including its grid (if it is in one) and its position in the grid. 
 * Additionally, it provides methods to mutate both the grid that the tetrad is in and its location. 
 * @author Vani Mohindra
 * @version 3/20/17
 */
public class Tetrad 
{
	private Block [] tetrad = new Block[4];
	private MyBoundedGrid <Block> grid;
	private Semaphore lock;
	
	 /**
	  * Adds this block to a particular location in the specified grid
     * precondition:  blocks are not in any grid;
     *                locs.length = 4.
     * postcondition: The locations of blocks match locs,
     *                and blocks have been put in the grid.
     *                
     * @param grid: the grid to put the block in
     * @param locs: the location in the grid that the block is supposed to go in
     */
	private void addToLocation (MyBoundedGrid<Block> grid, Location[] locs)
	{
		for (int i=0; i<4; i++)
		{
			if (tetrad[i]==null)
			{
				tetrad[i] = new Block();
			}
			Block block = tetrad[i];
			Location loc = locs[i];
			block.putSelfInGrid(grid, loc);
		}
	}
	
	/**
	 * Creates a random tetrad and places it in the middle of the top row of the specified grid.
	 * Tetrads come in four types, ech one having a different shape and a different color
	 * @param grid
	 */
	public Tetrad (MyBoundedGrid<Block> grid)
	{
		this.grid = grid;
		lock = new Semaphore(1,true);
		int i = (int)(Math.random()*7);
		int midRow = 0;
		int midCol = grid.getNumCols()/2;
		switch(i)
		{
			case 0:
				Location [] locs0 = {new Location(midRow, midCol), new Location(midRow+1, midCol), 
						new Location(midRow+2, midCol), new Location(midRow+3, midCol)};
				addToLocation(grid, locs0);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.RED);
				}
				break;
			case 1:
				Location [] locs1 = {new Location(midRow, midCol), new Location(midRow, midCol-1), 
						new Location(midRow, midCol+1), new Location(midRow+1, midCol)};
				addToLocation(grid, locs1);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.GRAY);
				}
				break;
			case 2:
				Location [] locs2 = {new Location(midRow, midCol), new Location(midRow, midCol-1), 
						new Location(midRow+1, midCol), new Location(midRow+1, midCol-1)};
				addToLocation(grid, locs2);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.CYAN);
				}
				break;
			case 3:
				Location [] locs3 = {new Location(midRow, midCol), new Location(midRow+1, midCol), 
						new Location(midRow+2, midCol), new Location(midRow+2, midCol+1)};
				addToLocation(grid, locs3);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.YELLOW);
				}
				break;
			case 4:
				Location [] locs4 = {new Location(midRow, midCol), new Location(midRow+1, midCol), 
						new Location(midRow+2, midCol), new Location(midRow+2, midCol-1)};
				addToLocation(grid, locs4);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.MAGENTA);
				}
				break;
			case 5:
				Location [] locs5 = {new Location(midRow, midCol), new Location(midRow, midCol+1), 
						new Location(midRow+1, midCol), new Location(midRow+1, midCol-1)};
				addToLocation(grid, locs5);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.BLUE);
				}
				break;
			case 6:
				Location [] locs6 = {new Location(midRow, midCol), new Location(midRow, midCol-1), 
						new Location(midRow+1, midCol), new Location(midRow+1, midCol+1)};
				addToLocation(grid, locs6);
				for (int j=0; j<tetrad.length; j++)
				{
					tetrad[j].setColor(Color.GREEN);
				}
				break;
		}
	}
	
	
	/**
	 * Removes blocks from the grid and returns their former locations
	 * 
	 * @precondition blocks are in the grid
	 * 
	 * @return old locations of blocks
	 */
    private Location[] removeBlocks()
    {
    	Location [] locs = new Location[4];
    	for (int i=0; i<4; i++)
    	{
    		locs[i] = tetrad[i].getLocation();
    		tetrad[i].removeSelfFromGrid();
    	}
    	return locs;
    }

    /**
     * Determines if the given locations in a specified grid are valid and empty
     * 
     * @param grid: the grid whose locations are to be tested
     * @param locs: the locations to check for validity and emptiness
     * 
     * @return true if each of the locations is valid and empty in the grid; false otherwise
     */
    private boolean areEmpty (MyBoundedGrid<Block> grid, Location[] locs)
    {
    	boolean b = true;
    	for (int i=0; i<locs.length; i++)
    	{
    		if (!(grid.isValid(locs[i])) || grid.get(locs[i])!=null)
    		{
    			b = false;
    		}
    	}
    	return b;
    }
    
    /**
     * Attempts to move this tetrad deltaRow rows down and deltaCol
     * columns to the right, if those positions are valid and empty
     * 
     * @param deltaRow: rows to move down
     * @param deltaCol: columns to move to the right
     * 
     * @return true if the new position of the tetrad is valid and empty; false otherwise
     */
    public boolean translate (int deltaRow, int deltaCol)
    {
    	try
    	{
    		lock.acquire();
    		Location [] oldLocs = new Location [4];
        	Location [] newLocs = new Location [4];
        	for (int i=0; i<4; i++)
        	{
        		Location oldLoc = tetrad[i].getLocation();
        		oldLocs[i] = oldLoc;
        		tetrad[i].removeSelfFromGrid();
        		Location newLoc = new Location(oldLoc.getRow()+deltaRow, oldLoc.getCol()+deltaCol);
        		newLocs[i]  = newLoc;
        	}
        	if (!areEmpty(this.grid, newLocs))
        	{
        		this.addToLocation(grid, oldLocs);
        		return false;
        	}
        	this.addToLocation(grid, newLocs);
        	return true;
    	}
    	catch (InterruptedException e)
    	{
    		return false;
    	}
    	finally
    	{
    		lock.release();
    	}
    }
    
    
    /**
     * Attempts to rotate this tetrad clockwise by 90 degrees about its center,
     * if the necessary positions are empty; returns true if succesful and false otherwise
     * 
     * @return true if succesfully rotated the tetrad; false otherwise
     */
    public boolean rotate()
    {
    	try
    	{
    		lock.acquire();
    		Location [] oldLocs = new Location[4];
        	Location [] newLocs = new Location[4];
        	int r0 = tetrad[0].getLocation().getRow();
    		int c0 = tetrad[0].getLocation().getCol();
        	for (int i=0; i<4; i++)
        	{
        		oldLocs[i] = tetrad[i].getLocation();
        		tetrad[i].removeSelfFromGrid();
        		Location loc = oldLocs[i];
        		Location newLoc = new Location(r0-c0+loc.getCol(), r0+c0-loc.getRow());
        		newLocs[i] = newLoc;
        	}
        	if (!areEmpty(this.grid, newLocs))
        	{
        		this.addToLocation(grid, oldLocs);
        		return false;
        	}
        	this.addToLocation(grid, newLocs);
        	return true;
    	}
    	catch (InterruptedException e)
    	{
    		return false;
    	}
    	finally
    	{
    		lock.release();
    	}
    }
    
    public boolean atBottom()
    {
    	boolean b = false;
    	for (int i=0; i<4; i++)
    	{
    		Location loc = tetrad[i].getLocation();
    		int row = loc.getRow();
    		if (row == this.grid.getNumRows()-1)
    		{
    			b = true;
    		}
    	}
    	return b;
    }
}
