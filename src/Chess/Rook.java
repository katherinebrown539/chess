package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public abstract class Rook extends ChessPiece
{
	public Rook(ChessBoard board, int square, Color color)
	{
		super(5, 8, 8, 0, null, "R", "Rook", board, square,color);
	}
	
	public Rook(ChessBoard board, int square, String image)
	{
		super(5,8,8,0,null,"R","Rook",board, square, image);
	}
	public abstract ArrayList<SquareCenter> updatePossibleMoves();
	
	public ArrayList<SquareCenter> getAttackedSquares()
	{
		return updatePossibleMoves();
	}
	
	public ArrayList<SquareCenter> getAttackedSquares(ArrayList<SquareCenter> white, ArrayList<SquareCenter> black, ChessBoard board)
	{
		return updatePossibleMoves();
	}
}