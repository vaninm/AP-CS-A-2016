import java.awt.Color;

/**
 * The Ball class contains methods to create, move, and alter the velocity of a ball. 
 * @author Vani Mohindra
 * @version May 17, 2017
 */

public class Ball extends Shape
{
	private double velX;
	private double velY;

	/**
	 * Constructs a Ball object given x-position and y-position. All balls have a radius of 2
	 * and are white in color
	 * @param x: the x-position of the location where the ball should appear on a grid
	 * @param y: the y-position of the location where the ball should appear on a grid
	 */
	public Ball (double x, double y)
	{
		super (true, Color.WHITE, x, y, 2, 2);
		velX = 0.309;
		velY = -0.951;
	}
	
	/**
	 * Moves the ball along its velocity vector
	 */
	public void move ()
	{
		super.setX(super.getX()+velX);
		super.setY(super.getY()+velY);
	}
	
	/**
	 * Returns the current velocity of the ball in the x-direction
	 * @return the current velocity of the ball in the x-direction
	 */
	public double getVelocityX()
	{
		return velX;
	}
   
	/**
	 * Returns the current velocity of the ball in the y-directon
	 * @return the current velocity of the ball in the y-directon
	 */
	public double getVelocityY()
	{
		return velY;
	}
   
	/**
	 * Sets the velocity of the ball in the x-directon to the specified value
	 * @param newVelX:  the new velocity of the ball in the x-direction
	 */
	public void setVelocityX(double newVelX)
    {
    	velX = newVelX;
    }
   
	/**
	 * Sets the velocity of the ball in the y-directon to the specified value
	 * @param newVelY:  the new velocity of the ball in the y-direction
	 */
	public void setVelocityY(double newVelY)
	{
		velY = newVelY;
	}
}
