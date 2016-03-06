import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class BlackPawn extends Pawn
{
	public BlackPawn(ChessBoard board, int square)
	{
		super(board, square, new Color(0,0,0));
	}
	public BlackPawn(ChessGame game, int square)
	{
		super(game.getBoard(), square, new Color(0,0,0));
	}
	
	public void updatePossibleMoves()
	{
		int new_x = loc.getX();
		int new_y = loc.getY() + 2*square_size;
		
		if(times_moved == 0 && board.anyPieceOnSquare(new_x, new_y) == null)
		{
			moves.add(new SquareCenter(new_x, new_y, null));
		}
		new_y -= square_size;
		if(board.anyPieceOnSquare(new_x, new_y) == null)
		{
			moves.add(new SquareCenter(loc.getX(), loc.getY()+square_size, null));
		}
		

		ChessPiece piece = board.anyPieceOnSquare(loc.getX() - square_size, loc.getY() + square_size);
		if(piece != null) board.highlightSquareWithPiece(loc.getX() - square_size, loc.getY() + square_size);
		piece = board.anyPieceOnSquare(loc.getX() + square_size, loc.getY() + square_size);
		if(piece != null) board.highlightSquareWithPiece(loc.getX() + square_size, loc.getY() + square_size);
	}
}