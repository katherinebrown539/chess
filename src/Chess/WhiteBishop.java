import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WhiteBishop extends Bishop
{
	public WhiteBishop(ChessBoard board, int square)
	{
		super(board, square, new Color(255,255,255));
	}
	public WhiteBishop(ChessGame game, int square)
	{
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
		
		
		new_x = x;
		new_y = y;
		//right up diagonal
		//x increases y decreases
		//1040, 137
		while(new_x <= 1040 && new_y >= 137)
		{
			new_x += square_size;
			new_y -= square_size;
			if(!(new_x <= 1040 && new_y >= 137)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.blackPieceOnSquare(new_x,new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				moves.add(new_move);
				if(can_capture) break;
			}
			else
			{break;}
			
		}
		
		
		//right dpwn diagonal
		new_x = x;
		new_y = y;
		while(new_x >= 137 && new_y <= 1040)
		{
			new_x -= square_size;
			new_y += square_size;
			if(!(new_x >= 137 && new_y <= 1040)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.blackPieceOnSquare(new_x,new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				moves.add(new_move);
				if(can_capture) break;
			}
			else{break;}
			
		}
		
		//left up diagonal
		new_x = x;
		new_y = y;
		//137, 137
		while(new_y >= 137 && new_x >= 137)
		{
			new_x -= square_size;
			new_y -= square_size;
			if(!(new_y >= 137 && new_x >= 137)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.blackPieceOnSquare(new_x,new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				moves.add(new_move);
				if(can_capture) break;
			}
			else{break;}
			
		}
		
		//left down diagonal
		new_x = x;
		new_y = y;
		while(new_y <= 1040 && new_x <= 1040)
		{
			new_x += square_size;
			new_y += square_size;
			if(!(new_y <= 1040 && new_x <= 1040)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.blackPieceOnSquare(new_x,new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				moves.add(new_move);
				if(can_capture) break;
			}
			else{break;}
			
			
		}
	}
}