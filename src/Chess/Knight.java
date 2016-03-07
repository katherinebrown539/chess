import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public abstract class Knight extends ChessPiece
{
	public Knight(ChessBoard board, int square, Color color)
	{
		super(3, 3, 3, 0, null, "N", "Knight", board, square,color);
	}
	
	
	public void updatePossibleMoves()
	{
		moves = new ArrayList<SquareCenter>();
		
		int x1 = loc.getX() + 2*square_size;
		int y1 = loc.getY();
		
		if(x1 <= 1040)
		{
			if(y1 + square_size <= 1040)
			{
				SquareCenter m1 = new SquareCenter(x1, y1+square_size, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x1, y1+square_size);
				
				if((this instanceof WhiteKnight) && !(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
			if(y1 - square_size >= 130)
			{
				SquareCenter m1 = new SquareCenter(x1, y1-square_size, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x1, y1-square_size);
				
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
				
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
		}
		int x2 = loc.getX() - 2*square_size;
		int y2 = loc.getY();
		if(x2 >= 137)
		{
			if(y2 + square_size <= 1040)
			{
				SquareCenter m1 = new SquareCenter(x2, y2+square_size, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x2, y2+square_size);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
				
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
			if(y2 - square_size >= 130)
			{
				SquareCenter m1 = new SquareCenter(x2, y2-square_size, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x2, y2-square_size);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
		}
			
		int x3 = loc.getX();
		int y3 = loc.getY() + 2*square_size;
		if(y3 <= 1040)
		{
			if(x3 + square_size <= 1040)
			{
				SquareCenter m1 = new SquareCenter(x3+square_size, y3, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x3+square_size, y3);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
			if(x3 - square_size >= 130)
			{
				SquareCenter m1 = new SquareCenter(x3-square_size, y3, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x3-square_size, y3);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
		}
			
		int x4 = loc.getX();
		int y4 = loc.getY() - 2*square_size;
		if(y4 >= 137)
		{
			if(x4+square_size  <= 1040)
			{
				SquareCenter m1 = new SquareCenter(x4+square_size, y4, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x4+square_size, y4);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			}
			if(x4 - square_size >= 130)
			{
				SquareCenter m1 = new SquareCenter(x4-square_size, y4, null);
				m1.setID(board.getIDFromLocation(m1));
				ChessPiece piece = board.anyPieceOnSquare(x4-square_size, y4);
				if(this instanceof WhiteKnight)
				if(!(piece instanceof WhiteKnight || piece instanceof WhiteKing || piece instanceof WhitePawn ||piece instanceof WhiteBishop || piece instanceof WhiteRook || piece instanceof WhiteQueen) )
				moves.add(m1);
			
				if(this instanceof BlackKnight)
				if(!(piece instanceof BlackKnight || piece instanceof BlackKing || piece instanceof BlackPawn ||piece instanceof BlackBishop || piece instanceof BlackRook || piece instanceof BlackQueen) )
				moves.add(m1);
			
			}
		}
	}
}