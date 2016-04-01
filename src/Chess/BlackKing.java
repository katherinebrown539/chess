import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class BlackKing extends King
{
	public BlackKing(ChessBoard board, int square)
	{
		super(board, square, new Color(0,0,0));
	}
	public BlackKing(ChessGame game, int square)
	{
		super(game.getBoard(), square, new Color(0,0,0));
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

			if(new_x  - square_size > 135)
			{
				new_x -= square_size;
				System.out.println(new_x);
				//if(new_x <= 125) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					//if(can_capture) break;
				}
				
			}
		//right horizontal
		//y stays the same, x increases
		new_x = x;
		new_y = y;
			if(new_x+square_size <= 1040)
			{
				new_x += square_size;
				//if(new_x >= 1040) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					//if(can_capture) break;
				}
				
			}
			
		//up vertical
		
		new_x = x;
		new_y = y;
		if(new_y - square_size  > 135)
			{
				new_y -= square_size;
				System.out.println(new_x);
				//if(new_y <= 125) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					//if(can_capture) break;
				}
				
			}
		//down vertical
		new_x = x;
		new_y = y;
		if(new_y+square_size <= 1040)
			{
				new_y += square_size;
				//if(new_y >= 1040) break;
				ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
				can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
				if(piece == null || can_capture) //or instanceof others
				{
					SquareCenter new_move = new SquareCenter(new_x, new_y, null);
					new_move.setID(board.getIDFromLocation(new_move));
					moves.add(new_move);
					//if(can_capture) break;
				}
			
			}
		
		new_x = x;
		new_y = y;
		//right up diagonal
		//x increases y decreases
		//1040, 137
		if(new_x+square_size <= 1040 && new_y - square_size  >= 137)
		{
			new_x += square_size;
			new_y -= square_size;
			//if(!(new_x <= 1040 && new_y >= 137)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				
				moves.add(new_move);
				//if(can_capture || piece != null) break;
				
			}
			
		}
		
		
		//right dpwn diagonal
		new_x = x;
		new_y = y;
		if(new_x  - square_size >= 137 && new_y +square_size<= 1040)
		{
			new_x -= square_size;
			new_y += square_size;
			//if(!(new_x >= 137 && new_y <= 1040)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				
				moves.add(new_move);
			///	if(can_capture || piece != null) break;
			}
			
		}
		
		//left up diagonal
		new_x = x;
		new_y = y;
		//137, 137
		if(new_y - square_size  >= 137 && new_x - square_size>= 137)
		{
			new_x -= square_size;
			new_y -= square_size;
			//if(!(new_y >= 137 && new_x >= 137)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				
				moves.add(new_move);
			//	if(can_capture||piece != null) break;
			}
			
		}
		
		//left down diagonal
		new_x = x;
		new_y = y;
		if(new_y+square_size <= 1040 && new_x +square_size<= 1040)
		{
			new_x += square_size;
			new_y += square_size;
			//if(!(new_y <= 1040 && new_x <= 1040)) break;
			ChessPiece piece = board.anyPieceOnSquare(new_x, new_y);
			can_capture = (piece != null) && (board.whitePieceOnSquare(new_x, new_y));
			if(piece == null || can_capture) //or instanceof others
			{
				SquareCenter new_move = new SquareCenter(new_x, new_y, null);
				new_move.setID(board.getIDFromLocation(new_move));
				
				moves.add(new_move);
			//	if(can_capture || piece != null) break;
			}
			
		}
		
		canCastle();
		setAttackedByBlack();
	}
	
	public boolean checkForCheckmate()
	{
		if(!checkForCheck() || moves.size() == 0) return false; //stalemate or okay
		updatePossibleMoves();
		
		for(int i = 0; i < moves.size(); i++){
				if(moves.get(i).isAttackedByWhite()){moves.remove(moves.get(i));}
		}
		
		return moves.size() == 0;
	}
	
	public boolean checkForCheck()
	{
		return loc.isAttackedByWhite();
	}
	
	public boolean checkForStalemate()
	{
		for(int i = 0; i < moves.size(); i++){
				if(moves.get(i).isAttackedByWhite()){moves.remove(moves.get(i));}
		}
		
		return moves.size() == 0;
	}
}