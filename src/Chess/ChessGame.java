import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.awt.Graphics;
import java.lang.Math;
public class ChessGame extends JFrame
{
	private ChessBoard board;
	private int height;
	private int width;
	private ArrayList<SquareCenter> square_centers;
	private ArrayList<ChessPiece> pieces; //will be refactored by player, just storing pieces I'm testing here
	private String[] locs = {"A8","B8","C8","D8","E8","F8","G8","A7","B7","C7","D7","E7","F7","G7","A6","B6","C6","D6","E6","F6","G6","A5","B5","C5","D5","E5","F5","G5","A4","B4","C4","D4","E4","F4","G4","A3","B3","C3","D3","E3","F3","G3","A2","B2","C2","D2","E2","F2","G2","A1","B1","C1","D1","E1","F1","G1"};
	private WhitePlayer white;
	private BlackPlayer black;
	
	public ChessBoard getBoard(){return board;}
	public ChessGame(int width, int height)
	{
		super("Chess!");
		board = new ChessBoard(width,height);
		//pieces = new ArrayList<ChessPiece>();	
		int square = (width < height)? width/8:height/8;
		white = new WhitePlayer(this, square);
		black = new BlackPlayer(this, square);
		
		
		initGUI(width, height);		
		//System.out.println(square);
		//board.printCenters();
		
		
	}
	
	
	public SquareCenter getSquareClicked(int x, int y){
		return board.getSquareClicked(x,y);
	}
	public ChessPiece isPieceSelected(int x, int y)
	{
		return board.anyPieceOnSquare(x, y);
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
	}
	

}