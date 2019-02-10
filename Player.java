import java.awt.Color;
/**
 * A Player plays on a given board and for the given color's side and can determine its next move 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public abstract class Player 
{
	private Board board;
	private String name;
	private Color color;
	
	/**
	 * Constructs a player object that will play on a specified board, has the given name, and 
	 * can manipulate the pieces of the given side
	 * @param board: the board that this Player will play on
	 * @param name: the name of this Player
	 * @param color: the side that this Player will play on
	 */
	public Player (Board board, String name, Color color)
	{
		this.board = board;
		this.name = name;
		this.color = color;
	}
	
	/**
	 * Returns the board that this Player plays on
	 * @return the board on which this Player plays
	 */
	public Board getBoard()
	{
		return board;
	}
	
	/**
	 * Returns the name of this Player
	 * @return the name of this Player
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Returns the side that this Player plays for
	 * @return the side that this Player plays for- in other words, the type of pieces that this Player can manipulate
	 */
	public Color getColor()
	{
		return color;
	}
	
	public abstract Move nextMove();
}