import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public abstract class Bishop extends ChessPiece
{
	public Bishop(ChessBoard board, int square, Color color)
	{
		super(3, 0, 0, 8, null, "B", "Bishop", board, square,color);
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