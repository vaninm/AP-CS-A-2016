import java.awt.Color;
import java.util.ArrayList;
/**
 * A RandomPlayer plays chess by selecting a random move from a random piece
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class RandomPlayer extends Player
{
	/**
	 * Constructs a new RandomPlayer
	 * @param board: the board that contains this player's pieces
	 * @param name: the name of this player
	 * @param color: the side that this player plays for
	 */
	public RandomPlayer (Board board, String name, Color color)
	{
		super (board, name, color);
	}
	
	/**
	 * Returns the next random move to be made
	 * @return the next random move to be made
	 */
	public Move nextMove()
	{
		ArrayList<Move> moves = this.getBoard().allMoves(this.getColor());
		int index = (int)(Math.random()*moves.size());
		return moves.get(index);
	}
}
