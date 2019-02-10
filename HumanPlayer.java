import java.awt.Color;
import java.util.ArrayList;

/**
 * A HumanPlayer can play chess by solicitng a human behind the console for moves. The player behind the console 
 * can manipulate pieces by interacting with the display. 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class HumanPlayer extends Player
{
	private BoardDisplay display;
	
	/**
	 * Creates a new HumanPlayer with the given attributes
	 * @param board: the board that the HumanPlayer plays on
	 * @param name: the name of the HumanPlayer
	 * @param color: the side that the HumanPlayer plays for
	 * @param display: the display used to show the board 
	 */
	public HumanPlayer(Board board, String name, Color color, BoardDisplay display)
	{
		super(board, name, color);
		this.display = display;
	}


	/**
	 * Check if the move is valid
	 * @param move: the move being checked for validity
	 * @return true if the user has done a valid move
	 */
	private boolean isMoveValid(Move move)
	{
		if (!move.getPiece().getColor().equals(this.getColor()))
			return false;
		
		ArrayList<Location> dests = move.getPiece().destinations();
		for (Location loc: dests)
		{
			if (loc.equals(move.getDestination()))
				return true;
		}
		
		return false;
		
//		ArrayList<Move> validMoves = this.getBoard().allMoves(this.getColor());
//		return (validMoves.contains(move));
	}
		
	
	/**
	 * Asks the user behind the console for a move and ascertains that this move is valid. 
	 * If the move is not valid, continue to ask the user for moves until he or she selects 
	 * a valid one. 
	 * @return: the move chosen by the user and validated by this HumanPlayer
	 */
	public Move nextMove() 
	{
		ArrayList<Move> moves = this.getBoard().allMoves(this.getColor());
		for (Move m: moves )
		{
			display.setColor(m.getDestination(), Color.GREEN);
		}
		Move move;
		while (true){
			move = display.selectMove();
			if (isMoveValid(move)) 
				break;		
		}
		return move;
	}
}