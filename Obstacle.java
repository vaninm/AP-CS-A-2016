import java.awt.Color;
import java.util.ArrayList;

/**
 * An Obstacle is a type of non-round Shape, but contains methods to govern how it interacts with the ball and reacts
 * to collisions with the ball.
 * @author Vani Mohindra
 * @version May 17, 2017
 */
public class Obstacle extends Shape
{
	/**
	 * Constructs an Obstacle object with the given specifications
	 * @param color: the color of the obstacle
	 * @param x: the x-coordinate of the position where the obstacle would appear on a grid
	 * @param y: the y-coordinate of the position where the obstacle would appear on a grid
	 * @param width: the width of the obstacle
	 * @param height: the height of the obstacle
	 */
	public Obstacle (Color color, double x, double y, double width, double height)
	{
		super (false, color, x, y, width, height);
	}
	
	/**
	 * Determines if the specified point is contained within the region covered by this Obstacle
	 * @param x: the x-coordinate of the point to be checked for overlap
	 * @param y: the y-coordinate of the point to be checked for overlap
	 * @return true, if the point is contained within the region covered by this Obstacle; false otherwise
	 */
	private boolean containsPoint (double x, double y)
	{
		boolean xrange = (x >= this.getX() && x <= this.getX() + this.getWidth());
		boolean yrange = (y >= this.getY() && y <= this.getY() + this.getHeight());
		return xrange && yrange;
	}
	
	/**
	 * Determines if any of the four endpoints the given ball (top, bottom, left, right) overlap with this Obstacle 
	 * @param ball: the ball to check for overlap
	 * @return an ArrayList of Integers. The ArrayList can contain any number of sets with the digits 1, 2, 3 and 4.
	 *  1 if the topmost point on the circle is contained within the obstacle, 
	 *  2 if the leftmost point on the circle is contained within the obstacle,
	 *  3 if the rightmost point on the circle is contained within the obstacle, 
	 *  and 4 if the bottommost point on the circle is contained within the obstacle
	 */
	public ArrayList<Integer> overlapsWith (Ball ball)
	{
		ArrayList <Integer> intersections = new ArrayList <Integer> ();
		
		double x = ball.getX();
		double y = ball.getY();
		double width = ball.getWidth();
		double height = ball.getHeight();
		
		boolean top = this.containsPoint (x+width/2, y);
		boolean left = this.containsPoint(x, y+height/2);
		boolean right = this.containsPoint(x+width, y+height/2);
		boolean bottom = this.containsPoint(x+width/2, y+height);
		
		if (top) intersections.add(1);
		if (left) intersections.add(2);
		if (right) intersections.add(3);
		if (bottom) intersections.add(4);
		return intersections;
	}
	
	/**
	 * Determines how the Obstacle should respond to collision with the ball. If this Obstacle is a Brick,
	 * it should be removed from the grid. Otherwise, it should modify the velocity of the ball such that 
	 * the ball rebounds off this Obstacle. 
	 * @precondition The ball must overlap with the Obstacle
	 * @param ball: the ball that overlaps with the obstacle
	 * @return true, if this Obstacle needs to be removed from its grid; false otherwise
	 */
	public boolean handleCollision (Ball ball)
	{
		ArrayList<Integer> intersections = overlapsWith(ball);
		if (intersections.size() == 0 ) return false;
		if (this.getClass().equals(Brick.class))
		{
			return true;
		}
		if (intersections.contains(1) || intersections.contains(4))
		{
			ball.setVelocityY(ball.getVelocityY()*-1);
		}
		if (intersections.contains(2) || intersections.contains(3))
		{
			ball.setVelocityX(ball.getVelocityX()*-1);
		}
		return false;
	}
}
