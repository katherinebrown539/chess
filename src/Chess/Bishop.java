import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class Bishop extends ChessPiece
{
	public Bishop(ChessBoard board, int square, Color color)
	{
		super(3, 0, 0, 8, null, "B", "Bishop", board, square,color);
	}
	
	public abstract void updatePossibleMoves();
}