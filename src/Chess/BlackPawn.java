package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class BlackPawn extends Pawn
{
	public BlackPawn(ChessBoard board, int square)
	{
		super(board, square,"resources/pieces/black/pawn");
		
	}
	public BlackPawn(ChessGame game, int square)
	{
		super(game.getBoard(), square, "resources/pieces/black/pawn");

	}
	


	public ArrayList<SquareCenter> updatePossibleMoves()
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
		
		setAttackedByBlack();
		return moves;
	}
	
	public ArrayList<SquareCenter> getAttackedSquares()
	{
		ArrayList<SquareCenter> to_return = new ArrayList<SquareCenter>();
		
		SquareCenter n = new SquareCenter(loc.getX() - square_size, loc.getY() + square_size, null);
		n.setID(board.getIDFromLocation(n));
		to_return.add(n);
			
		n = new SquareCenter(loc.getX() + square_size, loc.getY() + square_size, null);
		n.setID(board.getIDFromLocation(n));
		to_return.add(n);
		
		return to_return;
	}
	
	public ArrayList<SquareCenter> getAttackedSquares(ArrayList<SquareCenter> white, ArrayList<SquareCenter> black, ChessBoard board)
	{
		return updatePossibleMoves();
	}
}