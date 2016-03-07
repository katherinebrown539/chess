import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class Rook extends ChessPiece
{
	public Rook(ChessBoard board, int square, Color color)
	{
		super(5, 8, 8, 0, null, "R", "Rook", board, square,color);
	}
	
	public abstract void updatePossibleMoves();
}