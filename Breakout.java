import java.awt.Color;
import java.awt.event.*;
import java.util.Iterator;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
/**
 * The Breakout class provides all necessary methods to create a new game of Breakout,
 *  in which a paddle is used to aim a ball to explode the bricks in place (they explode
 *  upon contact) and to keep it from falling off the screen. A game ends when the ball falls
 *  off the screen and score is based on how long the player is able to survive. Either "High Score"
 *  or "Game Over" is displayed at the end of the game. The length of the duration is printed to the console.
 *  
 * @author Vani Mohindra
 * @version May 17, 2017
 */
public class Breakout extends KeyAdapter
{
	private ShapeDisplay display;
	private Ball ball;
	Obstacle [] sides;
	Paddle paddle;
	boolean rightPressed;
	boolean leftPressed;
	
	/**
	 * Constructs a new breakout game. The display is 100 by 100 and walls line the left, top, and right sides
	 * of the display. The display contains a functional ball and paddle, and the ball continuously moves along 
	 * its velocity vector, which is altered when it strikes obstacles, which include the paddle, the walls, and bricks.
	 */
	private Breakout ()
	{
		display = new ShapeDisplay();
		display.setTitle("Breakout");
		display.addKeyListener(this);
		ball = new Ball (50, 80);
		Obstacle o1 = new Obstacle (Color.ORANGE, 0, 0, 4, 100);
		Obstacle o2 = new Obstacle (Color.ORANGE, 96, 0, 4, 100);
		Obstacle o3 = new Obstacle (Color.ORANGE, 0, 0, 100, 4);
		o1.overlapsWith(ball);
		display.add(ball);
		display.add(o1);
		display.add(o2);
		display.add(o3);
		sides = new Obstacle[3];
		sides[0] = o1;
		sides[1] = o2;
		sides[2] = o3;
		for (int row = 5; row <= 89; row += 7)
		{
			for (int col = 15; col <= 50; col += 5)
			{
				Color c;
				if (col <= 30) c = Color.RED;
				else c = Color.GREEN;
				Brick b = new Brick (c, row, col);
				display.add(b);
			}
		}	
		paddle = new Paddle (40,90);
		display.add(paddle);
		display.repaint();
	}
	
	/**
	 * Conducts a game of breakout. The ball repeatedly moves and strikes objects, notably causing the bricks to explode
	 * upon contact. The game is considered over when the ball falls below the lower bound of the window.The length of the game 
	 * is printed to the console, and either "High Score" or "Game Over" is displayed. The ball is also subject to gravity.
	 * @param high: the player's highscore
	 */
	private void play (double high)
	{
		double timer = 0;
		double highscore = high;
		while (true)
		{
			try 
			{ 
				if (ball.getY() >= 100)
				{
					if (timer > highscore)
					{
						System.out.println("New high score of " + (int)timer + " seconds . Old "
								+ "high score was " + (int)highscore + " seconds ");
						highscore = timer;
						display.highScore();
						Thread.sleep(3000);
					}
					else 
					{
						System.out.println("You lasted " + (int)timer + " seconds.");
						display.youLose();
						Thread.sleep(3000);
					}
					timer = 0;
					Breakout newgame = new Breakout ();
					newgame.play(highscore);
				}
				timer += 0.025;
				ball.setVelocityY(ball.getVelocityY()+0.009);
				ball.move();
				this.checkForCollisions();
				this.movePaddle();
				display.repaint();
				Thread.sleep(25);
			}
			catch(InterruptedException e){}
		}
	}
	
	/**
	 * If any of the obstacles are in contact with the ball, the handleCollision method of the offending
	 * obstacle is called to resolve the conflict. 
	 */
	private void checkForCollisions ()
	{
		Iterator <Shape> it = display.shapes();
		while (it.hasNext())
		{
			Shape temp = it.next();
			if (temp instanceof Obstacle)
			{
				if (((Obstacle) temp).handleCollision(ball))
				{
					it.remove();
				}
			}
		}
	}
	
	/**
	 * Detects which key is pressed (if any) and alters the corresponding instance variables
	 * accordingly. 
	 */
	 public void keyPressed(KeyEvent e)
	 {
	        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
	        {
	            rightPressed=true;
	        }
	        if(e.getKeyCode()==KeyEvent.VK_LEFT)
	        {
	            leftPressed=true;
	        }
	   }
	 
	 /**
	  * Detects which keys have been released (if any) and modifies the correspoding instance variables
	  * accordingly
	  */
	 public void keyReleased(KeyEvent e)
	 {
		 if(e.getKeyCode()==KeyEvent.VK_RIGHT) rightPressed=false;
	     if(e.getKeyCode()==KeyEvent.VK_LEFT) leftPressed=false; 
	 }
	
	/**
	 *  Moves the paddle in the direction of the pressed keys if the movement will guarantee that 
	 *  the paddle stays within the bounds of the display.
	 */
	private void movePaddle()
	{
		if (rightPressed && paddle.getX()<75.5)
		{
			paddle.setX(paddle.getX()+1);
		}
		if (leftPressed && paddle.getX() > 4.5)
		{
			paddle.setX(paddle.getX()-1);
		}
	}
	
	/**
	 * Begins a game of breakout
	 */
	public static void newGame ()
	{
		Breakout game = new Breakout();
		game.play(0);
	}
	
	public static void main (String [] args)
	{
		Breakout.newGame();
	}
}