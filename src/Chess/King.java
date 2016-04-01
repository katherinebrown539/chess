import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class King extends ChessPiece
{
	private boolean can_castle;
	
	public King(ChessBoard board, int square, Color color)
	{
		super(0, 1, 1, 1, null, "K", "King", board, square,color);
		can_castle = true;
	}
	
	public abstract void updatePossibleMoves();
	public abstract boolean checkForCheckmate();
	
	public abstract boolean checkForCheck();
		
	public abstract boolean checkForStalemate();
	
	public void canCastle()
	{
		if(!can_castle) return;
		//king hasn't moved yet.
		boolean king_okay = this.getMoveCount() == 0;
		//clear path
		boolean kingside = false;
		boolean queenside = false;
		boolean clear_path;
		if(this instanceof WhiteKing && king_okay)
		{
			//kingside
			//a6 and a7
			SquareCenter a6 = board.getCenterFromID("G1");
			SquareCenter a7 = board.getCenterFromID("F1");
			kingside = (board.anyPieceOnSquare(a6.getX(), a6.getY()) == null) && (board.anyPieceOnSquare(a7.getX(), a7.getY()) == null);
			
			//queenside
			//a2 a3 a4
			SquareCenter a2 = board.getCenterFromID("B1");
			SquareCenter a3 = board.getCenterFromID("C1");
			SquareCenter a4 = board.getCenterFromID("D1");
			queenside = (board.anyPieceOnSquare(a4.getX(), a4.getY()) == null) && (board.anyPieceOnSquare(a3.getX(), a3.getY()) == null) && (board.anyPieceOnSquare(a2.getX(), a2.getY()) == null);
		}
		if(this instanceof BlackKing && king_okay)
		{
			//kingside
			//a6 and a7
			SquareCenter a6 = board.getCenterFromID("F8");
			SquareCenter a7 = board.getCenterFromID("G8");
			kingside = (board.anyPieceOnSquare(a6.getX(), a6.getY()) == null) && (board.anyPieceOnSquare(a7.getX(), a7.getY()) == null);
			
			//queenside
			//a2 a3 a4
			SquareCenter a2 = board.getCenterFromID("B8");
			SquareCenter a3 = board.getCenterFromID("C8");
			SquareCenter a4 = board.getCenterFromID("D8");
			queenside = (board.anyPieceOnSquare(a4.getX(), a4.getY()) == null) && (board.anyPieceOnSquare(a3.getX(), a3.getY()) == null) && (board.anyPieceOnSquare(a2.getX(), a2.getY()) == null) ;
		
		}
		
		//no check, later
		
		//rook hasn't moved yet
		if(this instanceof WhiteKing)
		{
			//kingside
			if(kingside)
			{
				SquareCenter r = board.getCenterFromID("H1");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				if(rook instanceof WhiteRook && rook.getMoveCount() == 0)
				{
					SquareCenter a7 = board.getCenterFromID("G1");
					moves.add(a7);
					rook.addMove(board.getCenterFromID("F1"));
				}
			}
			//queenside
			if(queenside)
			{
				SquareCenter r = board.getCenterFromID("A1");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				if(rook instanceof WhiteRook && rook.getMoveCount() == 0)
				{
					SquareCenter a7 = board.getCenterFromID("C1");
					rook.addMove(board.getCenterFromID("D1"));
					moves.add(a7);
				}
			}
			return;
		}
		if(this instanceof BlackKing)
		{
			//kingside
			if(kingside)
			{
				SquareCenter r = board.getCenterFromID("H8");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				if(rook instanceof BlackRook && rook.getMoveCount() == 0)
				{
					SquareCenter a7 = board.getCenterFromID("G8");
					rook.addMove(board.getCenterFromID("F8"));
					moves.add(a7);
				}
			}
			//queenside
			if(queenside)
			{
				SquareCenter r = board.getCenterFromID("A8");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				if(rook instanceof BlackRook && rook.getMoveCount() == 0)
				{
					SquareCenter a7 = board.getCenterFromID("C8");
					rook.addMove(board.getCenterFromID("D8"));
					moves.add(a7);
				}
			}
		}
		return;
		
	}
	
	public boolean castle(SquareCenter end)
	{
		//g1 c1
		if(!can_castle) return false;
		if(this instanceof WhiteKing)
		{
			SquareCenter g1 = board.getCenterFromID("G1");
			if((g1.getX() == end.getX()) && (g1.getY() == end.getY()))
			{
				SquareCenter r = board.getCenterFromID("H1");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				if(rook instanceof WhiteRook) System.out.println("White");
				if(rook instanceof BlackRook) System.out.println("Black");
				r = board.getCenterFromID("F1");
				this.move(g1);
				rook.move(r);
				moves.remove(board.getCenterFromID("C1"));
				this.can_castle = false;
				return true;
			}
			
			SquareCenter c1 = board.getCenterFromID("C1");
			if((c1.getX() == end.getX()) && (c1.getY() == end.getY()))
			{
				SquareCenter r = board.getCenterFromID("A1");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				r = board.getCenterFromID("D1");
				this.move(c1);
				rook.move(r);
				moves.remove(board.getCenterFromID("G1"));
				this.can_castle = false;
				return true;
			}
		}
		
		//g8 c8
		if(this instanceof BlackKing)
		{
			SquareCenter g8 = board.getCenterFromID("G8");
			if((g8.getX() == end.getX()) && (g8.getY() == end.getY()))
			{
				SquareCenter r = board.getCenterFromID("H8");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				r = board.getCenterFromID("F8");
				this.move(g8);
				rook.move(r);
				moves.remove(board.getCenterFromID("C8"));
				this.can_castle = false;
				return true;
			}
			
			SquareCenter c8 = board.getCenterFromID("C8");
			if((c8.getX() == end.getX()) && (c8.getY() == end.getY()))
			{
				SquareCenter r = board.getCenterFromID("A8");
				ChessPiece rook = board.anyPieceOnSquare(r.getX(), r.getY());
				r = board.getCenterFromID("D8");
				this.move(c8);
				rook.move(r);
				moves.remove(board.getCenterFromID("G8"));
				this.can_castle = false;
				return true;
			}
		}
		return false;
	}
		
	
}