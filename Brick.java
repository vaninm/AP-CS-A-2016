import java.awt.Color;
/**
 * The Brick is a type of obstacle. It cannot be modified after creation. 
 * @author Vani Mohindra
 * @version May 17, 2017
 */
public class Brick extends Obstacle
{
	/**
	 * Constructs a brick of specified color and position. The dimensions of all bricks is 6x4.
	 * @param color: the color of the brick
	 * @param x: the x-coordinate of the brick on a grid
	 * @param y: the y-coordinate of the brick on a grid
	 */
	public Brick (Color color, double x, double y)
	{
		super (color, x, y, 6, 4);
	}
}
