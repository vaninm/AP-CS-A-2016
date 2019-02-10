import java.awt.Color;
/**
 * Starts a new chess game 
 * @author Vani Mohindra
 * @version April 3, 2017
 */
public class Game 
{
	/**
	 * 
	 * Allows the player to select a move and then executes the move, highlighting both the 
	 * original spot and the destination of the piece that is moved
	 * @param board: the grid containing the player's pieces
	 * @param display: the display that shows the grid
	 * @param player: the player whose turn it is
	 */
	private static void nextTurn (Board board, BoardDisplay display, Player player)
	{
		display.setTitle(player.getName());
		Move move = player.nextMove();
		board.executeMove(move);
		display.clearColors();
		display.setColor(move.getSource(), Color.YELLOW);
		display.setColor(move.getDestination(), Color.YELLOW);
		try {Thread.sleep(500);} catch(InterruptedException e) {}
	}
	
	/**
	 * Assembles the pieces and repeatedly asks the players for moves one at a time 
	 * until of the kings has been captured, causing the game to end
	 * 
	 * @param board: the board to be played on
	 * @param display: the display to show the board
	 * @param white: the first player
	 * @param black: the second player
	 */
	public static void play (Board board, BoardDisplay display, Player white, Player black)
	{
		Piece blackKing = new King (Color.BLACK, "black_king.png");
		blackKing.putSelfInGrid(board, new Location(0,4));
		Piece blackQueen = new Queen (Color.BLACK, "black_queen.png");
		blackQueen.putSelfInGrid(board, new Location(0,3));
		Piece blackBishop1 = new Bishop (Color.BLACK, "black_bishop.png");
		blackBishop1.putSelfInGrid(board, new Location(0,2));
		Piece blackBishop2 = new Bishop (Color.BLACK, "black_bishop.png");
		blackBishop2.putSelfInGrid(board, new Location(0,5));
		Piece blackKnight1 = new Knight (Color.BLACK, "black_knight.png");
		blackKnight1.putSelfInGrid(board, new Location(0,1));
		Piece blackKnight2 = new Knight (Color.BLACK, "black_knight.png");
		blackKnight2.putSelfInGrid(board, new Location(0,6));
		Piece blackRook1 = new Rook (Color.BLACK, "black_rook.png");
		blackRook1.putSelfInGrid(board, new Location(0,0));
		Piece blackRook2 = new Rook (Color.BLACK, "black_rook.png");
		blackRook2.putSelfInGrid(board, new Location(0,7));
		Piece bp1 = new Pawn (Color.BLACK, "black_pawn.png");
		bp1.putSelfInGrid(board, new Location(1,0));
		Piece bp2 = new Pawn (Color.BLACK, "black_pawn.png");
		bp2.putSelfInGrid(board, new Location(1,1));
		Piece bp3 = new Pawn (Color.BLACK, "black_pawn.png");
		bp3.putSelfInGrid(board, new Location(1,2));
		Piece bp4 = new Pawn (Color.BLACK, "black_pawn.png");
		bp4.putSelfInGrid(board, new Location(1,3));
		Piece bp5 = new Pawn (Color.BLACK, "black_pawn.png");
		bp5.putSelfInGrid(board, new Location(1,4));
		Piece bp6 = new Pawn (Color.BLACK, "black_pawn.png");
		bp6.putSelfInGrid(board, new Location(1,5));
		Piece bp7 = new Pawn (Color.BLACK, "black_pawn.png");
		bp7.putSelfInGrid(board, new Location(1,6));
		Piece bp8 = new Pawn (Color.BLACK, "black_pawn.png");
		bp8.putSelfInGrid(board, new Location(1,7));
		Piece whiteKing = new King (Color.WHITE, "white_king.png");
		whiteKing.putSelfInGrid(board, new Location(7,4));
		Piece whiteQueen = new Queen (Color.WHITE, "white_queen.png");
		whiteQueen.putSelfInGrid(board, new Location(7,3));
		Piece whiteBishop1 = new Bishop (Color.WHITE, "white_bishop.png");
		whiteBishop1.putSelfInGrid(board, new Location(7,2));
		Piece whiteBishop2 = new Bishop (Color.WHITE, "white_bishop.png");
		whiteBishop2.putSelfInGrid(board, new Location(7,5));
		Piece whiteKnight1 = new Knight (Color.WHITE, "white_knight.png");
		whiteKnight1.putSelfInGrid(board, new Location(7,1));
		Piece whiteKnight2 = new Knight (Color.WHITE, "white_knight.png");
		whiteKnight2.putSelfInGrid(board, new Location(7,6));
		Piece whiteRook1 = new Rook (Color.WHITE, "white_rook.png");
		whiteRook1.putSelfInGrid(board, new Location(7,0));
		Piece whiteRook2 = new Rook (Color.WHITE, "white_rook.png");
		whiteRook2.putSelfInGrid(board, new Location(7,7));
		Piece wp1 = new Pawn (Color.WHITE, "white_pawn.png");
		wp1.putSelfInGrid(board, new Location(6,0));
		Piece wp2 = new Pawn (Color.WHITE, "white_pawn.png");
		wp2.putSelfInGrid(board, new Location(6,1));
		Piece wp3 = new Pawn (Color.WHITE, "white_pawn.png");
		wp3.putSelfInGrid(board, new Location(6,2));
		Piece wp4 = new Pawn (Color.WHITE, "white_pawn.png");
		wp4.putSelfInGrid(board, new Location(6,3));
		Piece wp5 = new Pawn (Color.WHITE, "white_pawn.png");
		wp5.putSelfInGrid(board, new Location(6,4));
		Piece wp6 = new Pawn (Color.WHITE, "white_pawn.png");
		wp6.putSelfInGrid(board, new Location(6,5));
		Piece wp7 = new Pawn (Color.WHITE, "white_pawn.png");
		wp7.putSelfInGrid(board, new Location(6,6));
		Piece wp8 = new Pawn (Color.WHITE, "white_pawn.png");
		wp8.putSelfInGrid(board, new Location(6,7));
		display.showBoard();
		
		int i;
		while (true)
		{
			nextTurn(board, display, white);
			i = board.gameOver();
			if (i!=0) break;
			
			nextTurn(board, display, black);
			i = board.gameOver();
			if (i!=0) break;
		}

		if (i ==1)
			System.out.println("WHITE WINS");
		else if (i==2)
			System.out.println("BLACK WINS");
	}
	
	/**
	 * Main method: creates a board, a display, a Human Player (white), and a Smart Player (black), and begins a game
	 */
	public static void main (String [] args)
	{
		Board board = new Board();
		BoardDisplay display = new BoardDisplay(board);
		Player p1 = new HumanPlayer (board, "You", Color.WHITE, display);
		Player p2 = new SmartPlayer (board, "chess-bot", Color.BLACK);
		play(board, display, p1, p2);
	}
}