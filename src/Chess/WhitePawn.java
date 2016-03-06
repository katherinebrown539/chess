import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WhitePawn extends Pawn
{
	public WhitePawn(ChessBoard board, int square)
	{
		super(board, square, new Color(255,255,255));
	}
	public WhitePawn(ChessGame game, int square)
	{
		super(game.getBoard(), square, new Color(255,255,255));
	}
	
	public void updatePossibleMoves()
	{
		int new_x = loc.getX();
		int new_y = loc.getY() - 2*square_size;
		
		if(times_moved == 0 && board.anyPieceOnSquare(new_x, new_y) == null)
		{
			moves.add(new SquareCenter(new_x, new_y, null));
		}
		new_y += square_size;
		if(board.anyPieceOnSquare(new_x, new_y) == null)
		{
			moves.add(new SquareCenter(new_x, new_y, null));
		}
		

		ChessPiece piece = board.anyPieceOnSquare(loc.getX() - square_size, loc.getY() - square_size);
		if(piece != null) board.highlightSquareWithPiece(loc.getX() - square_size, loc.getY() - square_size);
		piece = board.anyPieceOnSquare(loc.getX() + square_size, loc.getY() + square_size);
		if(piece != null) board.highlightSquareWithPiece(loc.getX() + square_size, loc.getY() - square_size);
	
	}
}