import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
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
	public abstract void updatePossibleMoves();
}