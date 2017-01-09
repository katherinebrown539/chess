package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
public class BlackKnight extends Knight
{
	public BlackKnight(ChessBoard board, int square)
	{
		super(board, square, "resources/pieces/black/knight");
	}
	public BlackKnight(ChessGame game, int square)
	{
		super(game.getBoard(), square, "resources/pieces/black/knight");
	}
	
}