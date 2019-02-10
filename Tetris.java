/**
 * The Tetris Class provides all methods necessary to play a game of Tetris
 * @author Vani Mohindra
 * @version 3/20/17
 */
public class Tetris implements ArrowListener
{
	private MyBoundedGrid<Block> grid;
	private BlockDisplay display;
	private Tetrad current;
	private int score;

	/**
	 * Creates a new tetris game. The default board size is 20 by 10 and has the title "Tetris"
	 * Blocks move down on one second intervals. When one block reaches the bottom, another block is released
	 * from the top. If a row becomes full, it will be cleared. To manipulate the blocks, the up arrow key is used
	 * to rotate it by 90 degrees. The other arrow keys move the block one unit in their direction. Clearing a row earns
	 * 40 points and the object of the game is to gain as many points before no more blocks can enter the grid. 
	 */
	public Tetris ()
	{
		grid = new MyBoundedGrid<Block> (20,10);
		display = new BlockDisplay (grid);
		display.setTitle("Tetris");
		display.setArrowListener(this); 
		current = new Tetrad(grid);
		display.showBlocks();
		play();
	}
	
	/**
	 * Moves the current block down by one unit for every second. It still allows, however, for the player
	 * to manipulate the current block using the arrow keys. The player earns 40 points for every row cleared. 
	 */
	public void play ()
	{
		while (true)
		{
			int count = 0;
			while (current.translate(1, 0))
			{
				try
				{
					Thread.sleep(1000);
					display.showBlocks();
					count ++;
				}
				catch (InterruptedException e)
				{
				}
			}
			int cleared = clearCompletedRows();
			score += cleared*40;
			System.out.println("Score: " + score);
			if (count == 0) 
				{
					System.out.println("GAME OVER");
					return;
				}
			current = new Tetrad(grid);
		}
	}

	@Override
	/**
	 * @precondition the UP arrow key is pressed
	 * rotate the block by 90 degrees clockwise
	 */
	public void upPressed() 
	{
		current.rotate();
		display.showBlocks();
	}

	/**
	 * @precondition the DOWN arrow key is pressed
	 * shift the block down by one row
	 */
	@Override
	public void downPressed() 
	{
		current.translate(1, 0);
		display.showBlocks();
	}

	/**
	 * @precondition the LEFT arrow key is pressed
	 * shift the block one column to the left
	 */
	@Override
	public void leftPressed() 
	{
		try 
		{
	          Thread.sleep(1000);
	    }
	     catch(InterruptedException e)
	     {
	    	 
	     }
		current.translate(0, -1);
		display.showBlocks();
	}

	/**
	 * @precondition the RIGHT arrow key is pressed
	 * shift the block one column to the right
	 */
	@Override
	public void rightPressed()
	{
		current.translate(0, 1);
		display.showBlocks();
	}
	
	public static void main (String [] args)
	{
		Tetris t = new Tetris();
	}
	
	/**
	 * Determines whether or not a given row is completely full 
	 * @precondition 0 <= row < number of rows
	 * @param row: the row to be inspected
	 * @return true if every cell in the given row is occupied; false otherwise
	 */
	private boolean isCompletedRow (int row)
	{
		boolean b = true;
		for (int i=0; i<grid.getNumCols(); i++)
		{
			if (grid.get(new Location(row,i))==null)
			{
				b = false;
			}
		}
		return b;
	}
	
	/**
	 * Removes every block in the given row and moves every block above the given row down by one row
	 * @precondition 0 <= row < number of rows; row is filled completely
	 * @param row: the row to clear of its elements
	 */
	private void clearRow (int row)
	{
		for (int i=0; i<grid.getNumCols(); i++)
		{
			Block b = grid.get(new Location(row, i));
			b.removeSelfFromGrid();
		}
		for (int i=row-1; i>=0; i--)
		{
			for (int j=0; j<grid.getNumCols(); j++)
			{
				Block b = grid.get(new Location(i,j));
				if (b!=null)
				{
					b.moveTo(new Location(i+1,j));
				}
			}
		}
	}
	
	/**
	 * Clears all rows that are completely filled and adjusts blocks above those rows by moving
	 * them down 
	 * @return the number of rows cleared
	 */
	private int clearCompletedRows ()
	{
		int numCleared = 0;
		int row = grid.getNumRows()-1;
		for (int i=0; i<4; i++)
		{
			if (isCompletedRow(row-i))
			{
				clearRow(row-i);
				numCleared++;
			}
		}
		return numCleared;
	}

	@Override
	public void spacePressed() 
	{
		while (current.translate(1, 0))
		{
			
		}
	}
}
