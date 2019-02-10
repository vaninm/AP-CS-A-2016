import java.awt.Color;

/**
 * The Shape has properties of roundness, color, position, width and height. Only its position
 * can be modified after creation.
 * 
 * @author Vani Mohindra
 * @version May 17, 2017
 */
public class Shape 
{
	private boolean isRound;
	private Color color;
	private double x;
	private double y;
	private double width;
	private double height;

	/**
	 * Constructs a new Shape object with the specified attributes.
	 * 
	 * @param isRound: true if the Shape is intended to be round; false otherwise
	 * @param color: the color of the Shape to be created
	 * @param x: the x-coordinate of this Shape on a grid
	 * @param y: the y-coordinate of this Shape on a grid
	 * @param width: the width of this shape
	 * @param height: the height of this shape
	 */
	public Shape(boolean isRound, Color color, double x, double y,
			double width, double height) 
	{
		this.isRound = isRound;
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Returns a boolean that informs if this Shape is round or not 
	 * @return true if this Shape is round; false otherwise
	 */
	public boolean isRound()
	{
		return isRound;
	}
	
	/**
	 * Returns the color of this Shape
	 * @return the color of this Shape
	 */
	public Color getColor()
	{
		return color;
	}
	
	/**
	 * Returns the x-coordinate of this Shape in a grid
	 * @return the x-coordinate of this Shape
	 */
	public double getX() 
	{
		return x;
	}
	
	/**
	 * Returns the y-coordinate of this Shape in a grid
	 * @return the y-coordinate of this Shape
	 */
	public double getY() 
	{
		return y;
	}
	
	/**
	 * Retuns the width of this Shape
	 * @return the width of this Shape
	 */
	public double getWidth() 
	{
		return width;
	}
	
	/**
	 * Returns the height of this Shape
	 * @return the height of this Shape
	 */
	public double getHeight()
	{
		return height;
	}
	
	/**
	 * Changes the x-coordinate of this shape to the given value 
	 * @param x: the new x-coordinate of this Shape
	 */
	public void setX (double x)
	{
		this.x = x;
	}
	
	/**
	 * Changes the y-coordinate of this shape to the given value 
	 * @param y: the new y-coordinate of this Shape
	 */
	public void setY (double y)
	{
		this.y = y;
	}
}
