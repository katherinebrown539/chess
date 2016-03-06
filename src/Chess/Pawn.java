import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public abstract class Pawn extends ChessPiece
{
	public Pawn(ChessBoard board, int square, Color color)
	{
		super(1,1,0,1, null, "P", "Pawn", board, square,color);
	}
	
	public abstract void updatePossibleMoves();
}