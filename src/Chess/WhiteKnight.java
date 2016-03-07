import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class WhiteKnight extends Knight
{
	public WhiteKnight(ChessBoard board, int square)
	{
		super(board, square, new Color(255,255,255));
	}
	public WhiteKnight(ChessGame game, int square)
	{
		super(game.getBoard(), square, new Color(255,255,255));
	}
	
}