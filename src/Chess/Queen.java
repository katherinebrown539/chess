package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public abstract class Queen extends ChessPiece
{
	public Queen(ChessBoard board, int square, Color color)
	{
		super(9, 8, 8, 8, null, "Q", "Queen", board, square,color);
	}
	
	public Queen(ChessBoard board, int square, String image)
	{
		super(9, 8, 8, 8, null, "Q", "Queen", board, square,image);
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