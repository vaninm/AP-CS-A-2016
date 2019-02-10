import java.awt.Color;
import java.util.ArrayList;

/**
 * The Paddle is a blue, square object with dimension 20 by 5. When a Ball collides
 * with a Paddle, the Paddle causes the Ball to rebound.
 * @author Vani Mohindra
 * @version May 17, 2017
 */
public class Paddle extends Obstacle
{
	/**
	 * Constructs a new paddle object with the given specifications. All paddles
	 * are blue and have dimensions twenty by five.
	 * 
	 * @param x: the x-position of this Paddle in a grid
	 * @param y: the y-position of this Paddle in a grid
	 */
	public Paddle (double x, double y)
	{
		super (Color.BLUE, x, y, 20, 5);
	}
	
	/**
	 * Returns false, because Paddles should not be removed from their displays.
	 * Re-directs the ball so that it bounces off in a direction determined by the 
	 * place where the ball strikes the paddle. This also gives the ball a slight
	 * boost in the y-direction. 
	 * 
	 * @param ball: the ball colliding with this Paddle
	 */
	public boolean handleCollision (Ball ball)
	{
		super.handleCollision(ball);
		if (super.overlapsWith(ball).size()!=0)
		{
			double left = this.getX();
			double right = left + this.getWidth();
			double ballX = ball.getX() + 1/2*ball.getWidth();
			
			double relX = (ballX - (left + right)/2)/((right-left)/2);
			
			double heading = Math.atan2(ball.getVelocityY(), ball.getVelocityX());
			double delta = 15*relX*0.0174533;
			heading = heading + delta;
			
			ball.setVelocityX(Math.cos(heading));
			ball.setVelocityY(Math.sin(heading)-0.25);
		}
		return false;
	}
}
