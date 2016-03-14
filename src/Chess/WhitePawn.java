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
		moves = new ArrayList<SquareCenter>();
		int new_x = loc.getX();
		int new_y = loc.getY()-2*square_size; //pawns can move two steps forward on their first turn
		
		boolean in_front = board.anyPieceOnSquare(new_x, new_y+square_size) == null;
		if(times_moved == 0 && in_front && board.anyPieceOnSquare(new_x, new_y) == null)
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
		
		new_y = new_y + square_size; //get square directly in front
		if(board.anyPieceOnSquare(new_x, new_y) == null)
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
		
		new_y = loc.getY() - square_size;//up
		new_x = loc.getX() - square_size;//left

		ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
		can_capture = (piece != null)&&(board.blackPieceOnSquare(new_x,new_y));
		if(can_capture) //need to check black piece 
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
			
		}
		new_x= loc.getX() + square_size; //right
		piece = board.anyPieceOnSquare(new_x, new_y);
		can_capture = (piece != null)&&(board.blackPieceOnSquare(new_x,new_y));
		if(can_capture) //need to check black piece	
		{
			SquareCenter n = new SquareCenter(new_x, new_y, null);
			n.setID(board.getIDFromLocation(n));
			moves.add(n);
		}
		repaint();
		setAttackedByWhite();
	}
	
	
}