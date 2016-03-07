import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class Queen extends ChessPiece
{
	public Queen(ChessBoard board, int square, Color color)
	{
		super(9, 8, 8, 8, null, "Q", "Queen", board, square,color);
	}
	
	public abstract void updatePossibleMoves();
}