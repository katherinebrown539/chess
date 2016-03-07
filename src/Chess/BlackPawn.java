import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
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
		moves = new ArrayList<SquareCenter>();
		int new_x = loc.getX();
		int new_y = loc.getY() + 2*square_size;
		
		boolean in_front = board.anyPieceOnSquare(new_x, new_y - square_size) == null;	
		if(times_moved == 0 && board.anyPieceOnSquare(new_x, new_y) == null && in_front)
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
		new_y -= square_size;
		if(board.anyPieceOnSquare(new_x, new_y) == null )
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
		
		
		ChessPiece piece = board.anyPieceOnSquare(loc.getX() - square_size, loc.getY() + square_size);
		if(piece != null) 
		{
			SquareCenter n = new SquareCenter(loc.getX() - square_size, loc.getY() + square_size, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
			
		}
		piece = board.anyPieceOnSquare(loc.getX() + square_size, loc.getY() + square_size);
		if(piece != null) 
		{
			SquareCenter n = new SquareCenter(loc.getX() + square_size, loc.getY() + square_size, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
	}
}