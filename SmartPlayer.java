import java.awt.Color;
import java.util.ArrayList;
/**
 * SmartPlayer plays chess by selecting the move that will give it the highest immediete score
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class SmartPlayer extends Player
{
	public SmartPlayer(Board board, String name, Color color) 
	{
		super(board, name, color);
	}
	
	/**
	 * Selects the move that yields the highest immediate score
	 * @return the move that will yield the highest possible score
	 */
	public Move nextMove()
	{
		Move bestMove = null;
		int highScore = -500; 
		ArrayList<Move> moves = this.getBoard().allMoves(this.getColor());
		for (Move move: moves)
		{
			this.getBoard().executeMove(move);
			int score = score();
			if (score > highScore)
			{
				highScore = score;
				bestMove = move;
			}
			this.getBoard().undoMove(move);
		}
		return bestMove;
	}
	
	/**
	 * The score is computed by subtracting the point-value of the opponent's pieces from this player's pieces. 
	 * @return the score 
	 */
	public int score ()
	{
		int score = 0;
		ArrayList<Location> occupied = this.getBoard().getOccupiedLocations();
		for (Location loc: occupied)
		{
			Piece p = this.getBoard().get(loc);
			if (p.getColor().equals(this.getColor())) score += p.getValue();
			else score -= p.getValue();
		}
		return score;
	}
}