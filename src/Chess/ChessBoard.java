import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;
import java.util.Iterator;
import javax.swing.border.EmptyBorder;

public class ChessBoard extends JPanel implements MouseListener
{
	private Graphics g;
	private int board_size;
	private int height;
	private int width;
	//selects a single square
	private int high_x;
	private int high_y;
	private ArrayList<SquareCenter> highlight_locs;
	private ArrayList<SquareCenter> deselect_locs;
	private int last_x;
	private int last_y;
	private int square_size;
	private Color dark = new Color(102,60,0);
	private Color light = new Color(226,204,171);
	private Color highlight = new Color(0, 102, 204);
	private ArrayList<SquareCenter> centers;
	private ArrayList<ChessPiece> pieces; //list of pieces to draw
	
	private MoveState move; //polymorphic master
	private SelectSquareState select_square;
	private SelectPieceState select_piece;
	
	//	public void 
	public SelectSquareState getSelectSquareState(){return select_square;}
	public SelectPieceState getSelectPieceState(){return select_piece;}
	public void changeState(MoveState ms){move = ms;}
	
	public boolean whitePieceOnSquare(int x, int y)
	{
		ChessPiece piece = anyPieceOnSquare(x,y);
		if(piece == null){ return false; } 
		
		if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight || piece instanceof WhiteBishop || piece instanceof WhiteQueen)
		{return true;}
	
		return false;
	}
	public boolean blackPieceOnSquare(int x, int y)
	{
		ChessPiece piece = anyPieceOnSquare(x,y);
		if(piece == null){ return false; } 
		
		if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackQueen)
		{return true;}
	
		return false;
	}
	public void printCenters()
	{
		for(SquareCenter sc : centers)
		{
			System.out.println(sc.getX() + " , " + sc.getY());
		}
	}
	public void addPiece(ChessPiece cp, String id)
	{
		this.setLayout(null);
		SquareCenter loc = getCenterFromID(id);
		cp.setLocation(loc);
		pieces.add(cp);
		cp.setBounds(loc.getX()-square_size/2 , loc.getY()-square_size/2, square_size, square_size);
		cp.setOpaque(false);
		this.add(cp);
	}
	
	public void updatePiece(ChessPiece cp, SquareCenter c)
	{
		pieces.add(cp);
		cp.setBounds(c.getX()-square_size/2 , c.getY()-square_size/2, square_size, square_size);
		cp.setOpaque(false);
		this.add(cp);
		
	}

	
	public SquareCenter getCenterFromID(String id)
	{
		return centers.get(getIndexFromID(id));
	}
	
	public int getIndexFromID(String id)
	{
		int letter = (int)id.charAt(0)-65;
		int number = 8 - ((int) id.charAt(1) -48);
				
		int loc  =8*number + letter;
		return loc;
	}
	
	public ChessBoard(int width, int height)
	{
		board_size = width > height ? height:width;
		this.width = width+10;
		this.height = height+10;
		addMouseListener(this);
		//this.add(board);
		select_piece = new SelectPieceState(this);
		select_square = new SelectSquareState(this);
		move = select_piece;
		 centers = new ArrayList<SquareCenter>();
		int start_x = Math.abs(height-width)/2 - 10;
		int start_y = Math.abs(height-width)/2 - 10;
		square_size = board_size/8;
		System.out.println(square_size);
		pieces = new ArrayList<ChessPiece>();
		String[] letters = {"A","B","C","D","E","F","G","H"};
		
		for(int i =7; i >= 0; i--)
		{
			for(int j = 0; j < 8; j++)
			{
				
				String id = letters[j]+(i+1);
				centers.add(new SquareCenter(start_x+square_size/2, start_y+square_size/2, id));
				start_x += square_size;
				
			}
			start_y += square_size;
			start_x = Math.abs(height-width)/2 - 10;
		}
		highlight_locs = new ArrayList<SquareCenter>();
		////System.out.println(centers.size());
	}
	public void deselectAllSquares()
	{
		for(SquareCenter c: centers)
		{
			clearSquare(c.getX(), c.getY());
		}
	}
	public ArrayList<SquareCenter> getCenters(){ return centers; }
	public void printSelectedCenters()
	{
		for(SquareCenter c: centers)
		{
			if(c.isSelected()) System.out.println(c);
		}
		System.out.println("---------------");
	}
	public void paintSelectedCenters(Graphics g)
	{
		for(SquareCenter c: centers)
		{
			g.setColor(highlight);
			if(c.isSelected()){ g.drawRect(c.getX() - square_size/2, c.getY()-square_size/2, square_size, square_size);}
			
		}
		
	}
	public boolean isAnyPieceSelected()
	{
		for(ChessPiece p:pieces)
		{
			if(p.isSelected()) return true;
		}
		return false;	
	}
	
	public void setAllPiecesUnselected()
	{
		for(ChessPiece p : pieces)
		{
			//if(p.isSelected()) break; //maintain the selection
			p.setSelected(false);
		}
	}
	public void setAllSquaresUnselected()
	{
		for(SquareCenter c: centers)
		{
			c.setSelected(false);
		}
	}
	public void setAllUnselected()
	{
		setAllSquaresUnselected();
		setAllPiecesUnselected();
		repaint();
	}
	public void mouseClicked(MouseEvent e)
	{
		//deselectAllSquares();
		//where was the mouse clicked?
		int x = e.getX(); 
		int y = e.getY();
	

		//go through each center, and if it's center is in between a square's boundaries and not a button, fill it out
		Iterator<SquareCenter> iter = centers.iterator();
		while(iter.hasNext())
		{
			//get x and y boundaries from the center	
			SquareCenter center = iter.next();
			int square_x_start = center.getX() - (square_size/2) ;
			int square_x_end  = center.getX() + (square_size/2);
			int square_y_start  = center.getY() - (square_size/2);
			int square_y_end = center.getY() + (square_size/2);
			
			
			if(square_x_start <=  x && x <= square_x_end && square_y_start <=  y && y <= square_y_end)
			{
				if(center.isSelected()) {center.setSelected(false);}
				else center.setSelected(true);			
			}	
			else{
				if(center.isSelected()){center.setSelected(false);}
			}
			//repaint();
			takeMove(x,y);
		}
			
			repaint();		
	}
	public void takeMove(int x, int y)
	{
		move.mouseClicked(x,y);
	}
	public SquareCenter getSquareClicked(int x, int y)
	{
		Iterator<SquareCenter> iter = centers.iterator();
		while(iter.hasNext())
		{
			SquareCenter center = iter.next();
			int square_x_start = center.getX() - (square_size/2) ;
			int square_x_end  = center.getX() + (square_size/2);
			int square_y_start  = center.getY() - (square_size/2);
			int square_y_end = center.getY() + (square_size/2);
			if(square_x_start <=  x && x <= square_x_end && square_y_start <=  y && y <= square_y_end)
			{
				return center;
			}
		}
		
		return null;
	}
	public void paint(Graphics g)
	{
		
		this.g = g;
		//printSelectedCenters();
		int start_x = Math.abs(height-width)/2 - 10;
		int start_y = Math.abs(height-width)/2 - 10;
		
		g.drawRect(start_x, start_y, board_size, board_size);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		g2.drawRect(start_x, start_y, board_size, board_size);
		Color curr = light;
		
		int col = 1;
		for(int j = 0; j < centers.size(); j++)
		{
			SquareCenter loc = centers.get(j);
			g.setColor(curr);
			g.fillRect(loc.getX() - square_size/2, loc.getY() -square_size/2, square_size, square_size);
			g.setColor(highlight);
			
			if(j != col*(8)-1) curr = (curr==light)?dark:light; 
			else col++;
		}
		
		paintSelectedCenters(g);
		
		//Paint each piece 
		boolean trigger = false;
		Iterator<ChessPiece> piece_iter = pieces.iterator();
		while(piece_iter.hasNext())
		{
			ChessPiece cp = piece_iter.next();
			cp.draw(g);
		} 
		
		
	}
	
	public String getIDFromLocation(SquareCenter c)
	{
		Iterator<SquareCenter> iter = centers.iterator();
		//System.out.println(c.getX() + " , " + c.getY());
		while(iter.hasNext())
		{
			SquareCenter d = iter.next();
			//System.out.println(d.getX()+ " , " + d.getY());
			if(c.getX() == d.getX() && c.getY() == d.getY()) return d.getID();
		}
		
		return null;
	}

	public void clearSquare(int x, int  y)
	{
		for(SquareCenter c: centers)
		{
			if(c.getX() == x && c.getY() == y)
			{
				c.setSelected(false);
			}
		}
	}
	
	public void clearSquare(int center_x, int center_y, Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		if(high_x >= 100 && high_y >= 100) 
		{
			this.setBorder(new EmptyBorder(center_x+(square_size/2),center_x-(square_size/2),center_y+(square_size/2),center_y-(square_size/2)));
		}
		
	}
	
	public void highlightSquareWithPiece(int x, int y)
	{
		high_x = 0;
		high_y= 0;
		ChessPiece piece = anyPieceOnSquare(x,y);
		if(piece != null)
		{
			piece.setSelected(true);
		}
		repaint();
	}
	
	public ChessPiece anyPieceOnSquare(int x, int y)
	{
		Iterator<ChessPiece> iter = pieces.iterator();
		while(iter.hasNext())
		{
			ChessPiece cp = iter.next();
			
			if(cp.getCenterLocation().getX() == x && cp.getCenterLocation().getY() == y ) return cp;
		}
		return null;
	}
	
	public void highlightSquare(int ix, int iy)
	{
		for(SquareCenter c: centers)
		{
			if(c.getX() == ix && c.getY() == iy)
			{
				c.setSelected(true);
			}
		}
	}
	
	public void highlightSquare(int center_x, int center_y, Graphics g)
	{
		g.setColor(highlight);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));

		if(center_x > 100 && center_y > 100) g.drawRect(center_x - square_size/2, center_y-square_size/2, square_size, square_size);
		//return false;
	}
	

	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};
	public void mousePressed(MouseEvent e){};
	public void mouseReleased(MouseEvent e){};
}