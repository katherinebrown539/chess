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
	protected ChessBoard board;
	//protected Image piece;
	protected String piece_id;
	protected String piece_name;
	
	public void highlightMoves(){ return; }
	public abstract void updatePossibleMoves();
	public void move(SquareCenter end)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		while(iter.hasNext())
		{
			SquareCenter move = iter.next();
			if(move.getX() == end.getX() && move.getY() == end.getY()){loc = end;}
		}
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
		this.addActionListener(new PieceListener());
		
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
	
	public void draw(Graphics g)
	{
		//System.out.println("piece paint");
		//board.draw(g);
		if(!in_play) return;
		if(is_selected)g.setColor(highlight);
		else g.setColor(piece_color);
		//System.out.println(is_selected);
		
		g.setFont(font);
		//g.setColor(new Color(0,0,0));	
		g.drawString(piece_id, loc.getX() - 20, loc.getY() + 20);
		
	}
	
	class PieceListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
				
			if(!is_selected)
			{
				if(board.isAnyPieceSelected()) board.setAllPiecesUnselected();
				is_selected = true;
			}
			else
			{
				is_selected = false;
			}
			System.out.println(piece_name + " at " + loc.toString() + " selected");
			repaint();
		}
	}
	
}