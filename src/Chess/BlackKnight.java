import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class BlackKnight extends Knight
{
	public BlackKnight(ChessBoard board, int square)
	{
		super(board, square, new Color(0,0,0));
	}
	public BlackKnight(ChessGame game, int square)
	{
		super(game.getBoard(), square, new Color(0,0,0));
	}
	
}