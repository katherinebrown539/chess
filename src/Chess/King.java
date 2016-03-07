import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class King extends ChessPiece
{
	public King(ChessBoard board, int square, Color color)
	{
		super(0, 1, 1, 1, null, "K", "King", board, square,color);
	}
	
	public abstract void updatePossibleMoves();
}