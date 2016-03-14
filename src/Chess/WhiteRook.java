import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WhiteRook extends Rook
{
	public WhiteRook(ChessBoard board, int square)
	{

		//super(board, square, "/resources/pieces/white/rook.jpeg");
		super(board, square, new Color(255,255,255));
		
	}
	public WhiteRook(ChessGame game, int square)
	{
		
		//super(game.getBoard(), square, "/resources/pieces/white/rook.jpeg");
		super(game.getBoard(), square, new Color(255,255,255));
	}
	
	public void updatePossibleMoves()
	{
		moves = new ArrayList<SquareCenter>();
		int new_x = loc.getX();
		int new_y = loc.getY();
		int x = new_x;
		int y = new_y;
		System.out.println(loc);
		
		//left horizontal
		//y stays the same, left decreases
		new_x = x;
		new_y =y;
			while(new_x > 135)
			{
				new_x -= square_size;
				System.out.println(new_x);
				if(new_x <= 125) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && ( piece instanceof BlackPawn || piece instanceof BlackQueen /* || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackKing*/);
				if(piece == null || can_capture) 
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					if(can_capture) break;
				}
				else{break;}
			}
		//right horizontal
		//y stays the same, x increases
		new_x = x;
		new_y =y;
			while(new_x <= 1040)
			{
				new_x += square_size;
			
				if(new_x >= 1040) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && ( piece instanceof BlackPawn || piece instanceof BlackQueen/*|| piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackKing*/);
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					if(can_capture) break;
				}
				else{break;}
			}
			
		//up vertical
		
		new_x = x;
		new_y =y;
		while(new_y > 135)
			{
				new_y -= square_size;
				System.out.println(new_x);
				if(new_y <= 125) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && ( piece instanceof BlackPawn || piece instanceof BlackQueen/* || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackKing*/);
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					if(can_capture) break;
				}
				else{break;}
			}
		//down vertical
		new_x = x;
		new_y =y;
		while(new_y <= 1040)
			{
				new_y += square_size;
				if(new_y >= 1040) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && ( piece instanceof BlackPawn || piece instanceof BlackQueen/* || 	piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackKing*/);
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					if(can_capture) break;
				}
				else{break;}
			}
		
		setAttackedByWhite();
	}
}