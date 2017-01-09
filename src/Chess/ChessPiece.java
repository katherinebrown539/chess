package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.File;
import java.awt.Graphics;
import java.lang.Math;
import java.awt.image.BufferedImage;
import java.net.URL;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.Iterator;


public abstract class ChessPiece extends JButton
{	
	protected int score; //piece's value when captured
	protected int x_mov; //how many spaces on x axis piece can move
	protected int y_mov; //how many spaces on y axis piece can move
	protected int diag_mov;
	protected int times_moved; //number of times a piece has moved
	protected int square_size; //size of square piece is on
	protected boolean in_play; //is the piece in play or has it been captured
	protected boolean can_capture; //can the piece capture anything?
	protected boolean is_selected; //is piece selected by player
	protected SquareCenter loc; //current location of piece
	
	protected ArrayList<SquareCenter> moves; //possible legal moves for piece
	protected Font font = new Font("Impact", Font.BOLD, 72);
	protected Color highlight = new Color(0, 102, 204);
	protected Color piece_color;
	protected Color background_color;
	protected ChessBoard board;
	//protected ImageIcon image;
	protected String piece_id;
	protected String piece_name;
	protected String piece_loc;
	protected Image image;
	protected ImageIcon icon;
	public boolean isWhitePiece()
	{ return piece_color == Color.WHITE || piece_color == Color.white;}
	
	public boolean isBlackPiece()
	{ return piece_color == Color.BLACK || piece_color == Color.black;}
	
	public abstract ArrayList<SquareCenter> getAttackedSquares();
	public void highlightMoves()
	{
		for(SquareCenter m : moves)
		{
			m.setID(board.getIDFromLocation(m));
			board.highlightSquare(m.getX(), m.getY());
			//if(this instanceof Whit)
		}
		
		repaint();
	}
	

	
	public boolean doesMovePutWhiteKingInCheck(SquareCenter loc)
	{
		SquareCenter current_loc = this.loc;
		//make temporary move change
		this.loc = loc;
		board.updateAttackedForAllPieces();
		boolean king_is_attacked = board.isWhiteKingAttacked();
		System.out.println(king_is_attacked);
		//reset original location
		this.loc = current_loc;
		board.updateAttackedForAllPieces();
		return king_is_attacked;
		
	}
	
	public boolean doesMovePutBlackKingInCheck(SquareCenter loc)
	{
		SquareCenter current_loc = this.loc;
		//make temporary move change
		this.loc = loc;
		board.updateAttackedForAllPieces();
		boolean king_is_attacked = board.isBlackKingAttacked();
		System.out.println(king_is_attacked);
		//reset original location
		this.loc = current_loc;
		board.updateAttackedForAllPieces();
		return king_is_attacked;
		
	}
	
	public boolean legalMoveSelected(SquareCenter final_loc)
	{
		for(SquareCenter loc : moves)
		{
			
			boolean isLegal = loc.toString().equalsIgnoreCase(final_loc.toString());
			if(isLegal) 
			{
				return true;
			}
		}
		
		return false;
	}

	public void addMove(SquareCenter end)
	{
		moves.add(end);
	}
	public void setAttackedByWhite(boolean tf)
	{
		for(SquareCenter m:moves)
		{
			//System.out.println(m.toString());
			m.setAttackedByWhite(tf);
		}
	}
	public void setAttackedByBlack(boolean tf)
	{
		for(SquareCenter m:moves)
		{
			//System.out.println(m.toString());
			m.setAttackedByBlack(tf);
		}
	}
	public void setAttackedByWhite()
	{
		for(SquareCenter m:moves)
		{
			//System.out.println(m.toString());
			m.setAttackedByWhite(true);
		}
	}
	public void setAttackedByBlack()
	{
		for(SquareCenter m:moves)
		{
			//System.out.println(m.toString());
			m.setAttackedByBlack(true);
		}
	}
	
	/*
	 * public void deselectAttacked()
	{
		for(SquareCenter m:moves)
		{
			m.setAttackedByWhite(false);
			m.setAttackedByBlack(false);
		}
	}
	 */
	public void deselectMoves()
	{
		if(!is_selected) return;
		
		for(SquareCenter m : moves)
		{
			board.clearSquare(m.getX(), m.getY());
		}
		
		//repaint();
		
	}
	public abstract ArrayList<SquareCenter> updatePossibleMoves();
	
	public void setStringLocation(SquareCenter loc)
	{
		String id = board.getIDFromLocation(loc);
		
		loc.setID(id);
	}
	
	public int getMoveCount(){return times_moved;}
	
	public void incrementMoveCount()
	{
		times_moved++;
	}
	
	public boolean move(SquareCenter end)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		
		while(iter.hasNext())
		{
			SquareCenter move = iter.next();
			if(move.getX() == end.getX() && move.getY() == end.getY()){
				loc = end; 
				board.updatePiece(this, end);
				return true;
			}
		}
		return false;
	}
	
	
	
	public int isCaptured()
	{
		is_selected = false;
		can_capture = false;
		in_play = false;
		return score;
		
	}
	
	public void setCanCapture(boolean tf)
	{
		can_capture = tf;
	}
	
	public ChessPiece(int scroe, int x, int y, int diag, SquareCenter loc, String id, String name, ChessBoard board, int size, String img)
	{
		//this.piece_color = pieceColor;
		String file_extension = ".png";
		
		ImageLoader il = ImageLoader.getImageLoader();
		//System.out.println(img+file_extension);
		Image image = il.getImage(img+file_extension);
		if(image != null)
		{
			image= image.getScaledInstance( 100, 100,  java.awt.Image.SCALE_SMOOTH ) ;  
			icon = new ImageIcon( image );
			
			//super.setIcon(icon);
		}
		if(image == null)
		{
			System.out.println("Failure. . .");
			System.exit(1);
		}
		
		this.square_size = size;
		this.board = board;
		this.score = score;
		this.x_mov = x;
		this.y_mov = y;
		this.diag_mov = diag;
		times_moved = 0;
		is_selected = false;
		in_play = true;
		can_capture = true;
		this.loc = loc;
		piece_id = id;
		piece_name = name;
		moves = new ArrayList<SquareCenter>();
		
		//updatePossibleMoves();
		this.addActionListener(new PieceListener());
		
		//System.out.println(name);
	}
	public ChessPiece(int score, int x, int y, int diag, SquareCenter loc, String id, String name, ChessBoard board, int size, Color pieceColor)
	{
		this.piece_color = pieceColor;
		this.square_size = size;
		this.board = board;
		this.score = score;
		this.x_mov = x;
		this.y_mov = y;
		this.diag_mov = diag;
		times_moved = 0;
		is_selected = false;
		in_play = true;
		can_capture = true;
		this.loc = loc;
		piece_id = id;
		piece_name = name;
		moves = new ArrayList<SquareCenter>();
		
		//updatePossibleMoves();
		this.addActionListener(new PieceListener());
		//System.out.println(name);
	}
	public SquareCenter getCenterLocation(){return loc;}
	public boolean isSelected()
	{
		return is_selected;
	}
	public void setSelected(boolean tf)
	{
		is_selected = tf;
	}
	public void setLocation(SquareCenter loc){this.loc = loc;}
	
	public void setBackgroundColor(Color c)
	{
		background_color = c;
	}
	
	public void draw(Graphics g)
	{
		
		g.setColor(background_color);
		g.fillRect(loc.getX() - square_size/2, loc.getY()-square_size/2, square_size, square_size);
		icon.paintIcon(this, g, loc.getX()  - square_size/2 + 5,  loc.getY() - square_size/2 + 5);
		super.setIcon(icon);
		
	}
	
	
	public boolean isActive()
	{
		return in_play;
	}
	
	public String toString(){return piece_name + " at " + piece_loc;}
	class PieceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			
			moves = new ArrayList<SquareCenter>();
			if(!is_selected)
			{
				
				board.setAllUnselected();
				is_selected = true;
				if(ChessPiece.this instanceof King) ((King) ChessPiece.this).updatePossibleMoves(board.getPiecesWhiteAttacks(), board.getPiecesBlackAttacks(), board);
				else  updatePossibleMoves();
				
				System.out.println(piece_name + " at " + loc.toString() + " selected");
				repaint();
			}
			else
			{
				board.setAllUnselected();
				System.out.println(piece_name + " at " + loc.toString() + " deselected");
				is_selected = false;
				repaint();
			}
			
			board.takeMove(loc.getX(), loc.getY());
			
			repaint();
		}
	}

}