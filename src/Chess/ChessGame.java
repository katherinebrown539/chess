import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.awt.Graphics;
import java.lang.Math;
public class ChessGame extends JFrame
{
	private ChessBoard board;
	private int height;
	private int width;
	private ArrayList<SquareCenter> square_centers;
	private ArrayList<ChessPiece> pieces; //will be refactored by player, just storing pieces I'm testing here
	private String[] locs = { "A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7" };
	private WhitePlayer white;
	
	public ChessBoard getBoard(){return board;}
	public ChessGame(int width, int height)
	{
		super("Chess!");
		board = new ChessBoard(width,height);
		pieces = new ArrayList<ChessPiece>();	
		int square = (width > height)? width/8:height/8;
		white = new WhitePlayer(this, square);
		
		
		for(int i = 0; i < 8; i++)
		{
			pieces.add(new BlackPawn(board, square));
		}
		initGUI(width, height);		
		
		
	}
	
	public void addPiece(ChessPiece cp, String id)
	{
		board.addPiece(cp, id);
	}
	
	public SquareCenter getCenterFromID(String id)
	{
		return board.getCenterFromID(id);
	}
	public void initGUI(int width, int height)
	{
		
		this.width = width;
		this.height = height;
		
		this.add(board);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.width+250, this.height+250);
		this.setVisible(true);
		int count = 0;
		for(ChessPiece p:pieces)
		{
			board.addPiece(p, locs[count]);
			count++;
		}
		
	}
}