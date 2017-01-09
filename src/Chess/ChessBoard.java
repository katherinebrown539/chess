package Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Graphics;
import java.lang.Math;
import java.util.Iterator;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

public class ChessBoard extends JPanel implements MouseListener
{
	private ChessGame cb;
	private Graphics g;
	private int board_size;
	private int height;
	private int width;
	//selects a single square
	private int high_x;
	private int high_y;
	private ArrayList<SquareCenter> highlight_locs;
	private ArrayList<SquareCenter> deselect_locs;
	private ArrayList<SquareCenter> white_moves;
	private ArrayList<SquareCenter> black_moves;
	private int last_x;
	private int last_y;
	private int square_size;
	private Color dark = new Color(102,60,0);
	private Color light = new Color(226,204,171);
	private Color highlight = new Color(0, 102, 204);
	private ArrayList<SquareCenter> centers;
	private ArrayList<SquareCenter> white_attacked;
	private ArrayList<SquareCenter> black_attacked;
	private ArrayList<ChessPiece> pieces; //list of pieces to draw
	private boolean in_check;
	private MoveState move; //polymorphic master
	private SelectSquareState select_square;
	private SelectPieceState select_piece;
	private TurnState turn;
	private WhiteTurnState white;
	private BlackTurnState black; 
	
	public boolean inCheck(){return in_check;}
	public void setInCheck()
	{
		boolean white = isWhiteKingAttacked();
		boolean black = isBlackKingAttacked();
		in_check = white || black;
	}
	public SelectSquareState getSelectSquareState(){return select_square;}
	public SelectPieceState getSelectPieceState(){return select_piece;}
	public void changeState(MoveState ms){move = ms;}
	
	public void changeTurnState(TurnState state){turn = state;}
	
	public WhiteTurnState getWhiteTurnState(){return white;}
	public BlackTurnState getBlackTurnState(){return black;}
	public void changeTurns(){turn.changeTurns();	}
	public boolean legalMoveSelected(int x, int y){return turn.legalPieceSelected(x,y);}
	public void setPiece(ChessPiece p){turn.setPiece(p);}
	
	public void setGame(ChessGame c){cb = c;}
	
	public ArrayList<SquareCenter> getPiecesWhiteAttacks() {return white_attacked;}
	public ArrayList<SquareCenter> getPiecesBlackAttacks() {return black_attacked;}
	
	
	public boolean isWhiteKingAttacked()
	{
		SquareCenter king_loc = cb.getWhiteKing().getCenterLocation();
		for(SquareCenter curr : black_attacked)
		{
			if(curr.getID().equalsIgnoreCase(king_loc.getID()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isBlackKingAttacked()
	{
		SquareCenter king_loc = cb.getBlackKing().getCenterLocation();
		for(SquareCenter curr : white_attacked)
		{
			if(curr.getID().equalsIgnoreCase(king_loc.getID()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	
	public void updateAttackedForAllPieces()
	{
		//System.out.println("adding attacked");
		white_attacked = new ArrayList<SquareCenter>();
		black_attacked = new ArrayList<SquareCenter>();
		Iterator<ChessPiece> iter = pieces.iterator();
		while(iter.hasNext())
		{
			ChessPiece curr = iter.next();
			if(curr.isActive())
			{
				addAttacked(curr);
			}
		}
	}
	
	public boolean doesWhiteAttackSquare(SquareCenter sc)
	{
		for(SquareCenter curr : white_attacked)
		{
			if(sc.getID().equalsIgnoreCase(curr.getID()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public boolean doesBlackAttackSquare(SquareCenter sc)
	{
		for(SquareCenter curr : black_attacked)
		{
			if(sc.getID().equalsIgnoreCase(curr.getID()))
			{
				return true;
			}
		}
		
		return false;
	}
	
	public void removeAttacked(ChessPiece piece)
	{
		boolean isBlack = blackPieceOnSquare(piece.getCenterLocation().getX(), piece.getCenterLocation().getY());
		
		ArrayList<SquareCenter> attacked = piece.updatePossibleMoves();
		
		//System.out.println("Refreshing attacked: ");
		Iterator<SquareCenter> iter = attacked.iterator();
		
		
		while(iter.hasNext())//for(SquareCenter s : attacked)
		{
			SquareCenter s = iter.next();
			String id = s.getID();
			
			SquareCenter loc = getCenterFromID(id);
			//System.out.println(id + " ==== " + loc.getID());
			
			
			if(isBlack) 
			{
				if(black_attacked.contains(loc))
				{
					black_attacked.remove(loc);
				}
			}
			else
			{ 
				if(white_attacked.contains(loc))
				{
					white_attacked.remove(loc);
				}
			}
		}
	}
	
	public void addAttacked(ChessPiece piece)
	{
		boolean isBlack = blackPieceOnSquare(piece.getCenterLocation().getX(), piece.getCenterLocation().getY());
		ArrayList<SquareCenter> attacked = piece.updatePossibleMoves();
		
		//System.out.println("Refreshing attacked: ");
		Iterator<SquareCenter> iter = attacked.iterator();
		
		
		while(iter.hasNext())//for(SquareCenter s : attacked)
		{
			SquareCenter s = iter.next();
			String id = s.getID();
			
			SquareCenter loc = getCenterFromID(id);
			//System.out.println(id + " ==== " + loc.getID());
			
			
			if(isBlack) 
			{
				if(!black_attacked.contains(loc))
				{
					black_attacked.add(loc);
				}
				
			}
			else
			{ 
				if(!white_attacked.contains(loc))
				{
					white_attacked.add(loc);
				}
			}
		}
	} 
	
	public void pawnPromotion(Pawn p)
	{
		//String[] options = {"Queen", "Knight", "Bishop", "Rook"};
		BlackQueen bq = new BlackQueen(this, square_size);
		bq.setLocation(p.getCenterLocation());
		BlackRook br = new BlackRook(this, square_size);
		br.setLocation(p.getCenterLocation());
		BlackKnight bk = new BlackKnight(this, square_size);
		bk.setLocation(p.getCenterLocation());
		BlackBishop bb = new BlackBishop(this, square_size);
		bb.setLocation(p.getCenterLocation());
		WhiteQueen wq = new WhiteQueen(this, square_size);
		wq.setLocation(p.getCenterLocation());
		WhiteRook wr = new WhiteRook(this, square_size);
		wr.setLocation(p.getCenterLocation());
		WhiteKnight wk = new WhiteKnight(this, square_size);
		wk.setLocation(p.getCenterLocation());
		WhiteBishop wb = new WhiteBishop(this, square_size);
		wb.setLocation(p.getCenterLocation());
		ChessPiece[] white = {wq,wr,wk,wb};
		ChessPiece[] black = {bq,br,bk,bb};
		ChessPiece[] options = (p instanceof BlackPawn)?black : white;
		ChessPiece value = null;
		while(value == null)
		{
			value= (ChessPiece)JOptionPane.showInputDialog(this,"What do you wish to promote your pawn to?", "Pawn Promotion!", JOptionPane.QUESTION_MESSAGE,  null, options, options[0]);
			//System.out.println(value);
		}
		
		SquareCenter loc = p.getCenterLocation();
		value.setLocation(loc);
		//System.out.println(value);
		updatePiece(value, loc);
		removePiece(p);
		//pieces.add(value);
		repaint();
		
		//System.out.println(isAnyPieceOnSelected(p.getX(), p.getY()));
		
	}
	
	
	
	public boolean whitePieceOnSquare(ChessPiece piece)
	{
		if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight || piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
		{return true;}
	
		return false;
	}
	
	public boolean blackPieceOnSquare(ChessPiece piece)
	{
		if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
		{return true;}
	
		return false;
	}
	public boolean whitePieceOnSquare(int x, int y)
	{
		ChessPiece piece = anyPieceOnSquare(x,y);
		if(piece == null){ return false; } 
		
		if(piece instanceof WhitePawn || piece instanceof WhiteRook || piece instanceof WhiteKnight || piece instanceof WhiteBishop || piece instanceof WhiteQueen || piece instanceof WhiteKing)
		{return true;}
	
		return false;
	}
	public boolean blackPieceOnSquare(int x, int y)
	{
		ChessPiece piece = anyPieceOnSquare(x,y);
		if(piece == null){ return false; } 
		
		if(piece instanceof BlackPawn || piece instanceof BlackRook || piece instanceof BlackKnight || piece instanceof BlackBishop || piece instanceof BlackQueen || piece instanceof BlackKing)
		{return true;}
	
		return false;
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
		pieces.remove(cp);
		pieces.add(cp);
		cp.setBounds(c.getX()-square_size/2 , c.getY()-square_size/2, square_size, square_size);
		cp.setOpaque(false);
		this.add(cp);
		
	}
	
	public void updatePiecePrediction(ChessPiece cp, SquareCenter c)
	{
		pieces.remove(cp);
		pieces.add(cp);
		updateAttackedForAllPieces();
	}
	public void removePiece(ChessPiece cp)
	{
		pieces.remove(cp);
		this.remove(cp);
	}

	
	public SquareCenter getCenterFromID(String id)
	{
		int loc = getIndexFromID(id);
		if(loc > 64 || loc < 0) return null;
		return centers.get(getIndexFromID(id));
	}
	
	public int getIndexFromID(String id)
	{
		int letter = (int)id.charAt(0)-65;
		int number = 8 - ((int) id.charAt(1) -48);
				
		int loc  =8*number + letter;
		if(loc > 64 || loc < 0) return -1;
		return loc;
	}
	
	public ChessBoard(int width, int height)
	{
		board_size = width > height ? height:width;
		this.width = width+10;
		this.height = height+10;
		addMouseListener(this);
		//this.add(board);
		in_check = false;
		white_attacked = new ArrayList<SquareCenter>();
		black_attacked = new ArrayList<SquareCenter>();
		white_moves = new ArrayList<SquareCenter>();
		black_moves = new ArrayList<SquareCenter>();
		select_piece = new SelectPieceState(this);
		select_square = new SelectSquareState(this);
		white = new WhiteTurnState(this);
		black = new BlackTurnState(this);
		turn = white;
		move = select_piece;
		 centers = new ArrayList<SquareCenter>();
		int start_x = Math.abs(height-width)/2 - 10;
		int start_y = Math.abs(height-width)/2 - 10;
		square_size = board_size/8;
		//System.out.println(square_size);
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
		for(ChessPiece p:pieces)
		{
			if(blackPieceOnSquare(p.getCenterLocation().getX(),p.getCenterLocation().getY() )) p.setAttackedByBlack(true);
			if(whitePieceOnSquare(p.getCenterLocation().getX(),p.getCenterLocation().getY() )) p.setAttackedByWhite(true);
		}
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
	/*
	public void printSelectedCenters()
	{
		for(SquareCenter c: centers)
		{
			if(c.isSelected()) System.out.println(c);
		}
		System.out.println("---------------");
	}*/	
	
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
		//System.out.println(x+","+y);

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
				//System.out.println(center);
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
			//
			SquareCenter center = iter.next();
			int square_x_start = center.getX() - (square_size/2) ;
			int square_x_end  = center.getX() + (square_size/2);
			int square_y_start  = center.getY() - (square_size/2);
			int square_y_end = center.getY() + (square_size/2);
			if(square_x_start <=  x && x <= square_x_end && square_y_start <=  y && y <= square_y_end)
			{
				//System.out.println(center);
				return center;
			}
		}
		
		return null;
	}
	
	public void paint(Graphics g)
	{
		cb.paint(g);
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
			ChessPiece piece = anyPieceOnSquare(loc.getX(), loc.getY());
			if(piece != null) piece.setBackgroundColor(curr);
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
	
	public void alertPlayer()
	{
		boolean checkmate = false;// in_check && (white_moves.isEmpty() || black_moves.isEmpty());
		boolean stalemate = false; //(white_moves.isEmpty() || black_moves.isEmpty());
		boolean white = false;
		boolean black = false;
		if(checkmate)
		{
			white = white_moves.isEmpty();
			if(white) cb.alertWhiteWinner();
			else cb.alertBlackWinner();
		}
		else if(stalemate)
		{
			cb.alertStalemate();
		}
		else if(in_check)
		{
			white = isWhiteKingAttacked();
			if(white) SimpleDialogs.normalOutput("White King is in CHECK!", "CHECK!");
			else SimpleDialogs.normalOutput("Black King is in CHECK!", "Check!");
		}
		
	}
	
	public void addWhiteMoves(ArrayList<SquareCenter> moves)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		
		while(iter.hasNext())
		{
			white_moves.add(iter.next());
		}	
	}
	
	public void addBlackMoves(ArrayList<SquareCenter> moves)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		
		while(iter.hasNext())
		{
			black_moves.add(iter.next());
		}	
	}
	
	public void removeWhiteMoves(ArrayList<SquareCenter> moves)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		
		while(iter.hasNext())
		{
			white_moves.remove(iter.next());
		}	
	}
	
	public void removeBlackMoves(ArrayList<SquareCenter> moves)
	{
		Iterator<SquareCenter> iter = moves.iterator();
		
		while(iter.hasNext())
		{
			black_moves.remove(iter.next());
		}	
	}
}