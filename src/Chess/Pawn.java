package Chess;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public abstract class Pawn extends ChessPiece
{
	//protected String[] en_passant_locs;
	protected boolean can_en_passant = false;
	protected boolean two_forward_last_turn = false;
	public Pawn(ChessBoard board, int square, Color color)
	{
		super(1,1,0,1, null, "P", "Pawn", board, square,color);
	}
	
	public Pawn(ChessBoard board, int square, String image)
	{
		super(1,1,0,1, null, "P", "Pawn", board, square,image);
	}
	
	public abstract ArrayList<SquareCenter> updatePossibleMoves();
	
	public boolean canPieceEnPassant(){return can_en_passant;}
	
	public void setTwoForward(boolean tf)
	{
		two_forward_last_turn = tf;
	}
	
	public boolean didPawnMoveTwoForwardLastTurn(){return two_forward_last_turn;}
	
	public ChessPiece enPassant()
	{
		String[] white = {"A6","B6", "C6","D6","E6","F6","G6","H6"};
		String[] black =  {"A3","B3", "C3","D3","E3","F3","G3","H3"};
		String[] whereabouts = (this instanceof WhitePawn)? white:black;
		String loc = this.loc.getID();
		//System.out.println("Currently: "+loc);
		String curr = "";
		for(String s:whereabouts)
		{
			//System.out.println(s);
			if(s.equalsIgnoreCase(loc) && can_en_passant)
			{
				curr = s;
				break;
			}
			
		}
		if(curr == "") return null;
		//System.out.println("I am here");
		char[] blah = new char[2];
		blah[0] = loc.charAt(0);
		blah[1] = loc.charAt(1);
		blah[1] = (this instanceof WhitePawn)? (char)((int)blah[1]-1):(char)((int)blah[1]+1);
		String end = new String(blah);
		//System.out.println("Capturing piece: " + end);
		SquareCenter c = board.getCenterFromID(end);
		ChessPiece piece = board.anyPieceOnSquare(c.getX(), c.getY());
		if((piece instanceof BlackPawn && this instanceof WhitePawn) || (piece instanceof WhitePawn && this instanceof BlackPawn))
		{
			piece.isCaptured();
			return piece;
		}
		return null;
	}
	
	public void canEnPassant()
	{
		String curr = "";
		boolean pawn_on_legit_square; 
		//is pawn on en passant square
		String[] en_passant_locs = new String[8];
		if(this instanceof WhitePawn)
		{
			String[] haha ={"A5","B5", "C5","D5","E5","F5","G5","H5"}; //screw off java
			en_passant_locs = haha;
			
		}
		else
		{
			String[] in_yo_face_java = {"A4","B4", "C4","D4","E4","F4","G4","H4"}; //yeah, you heard me
			en_passant_locs = in_yo_face_java;
		}
		for(String s : en_passant_locs)
		{
			//String s = en_passant_locs[i];
			//System.out.println(s);
			if(s.equalsIgnoreCase(loc.toString()))
			{
				curr = s;
				pawn_on_legit_square = true;
				//System.out.println("Can En Passant");
				break;
			}
			
		}
		if(curr == ""){ //System.out.println("Cannot en passant"); 
			return ;}
		
		//is there a pawn to en passant
		ArrayList<Pawn> opponents = new ArrayList<Pawn>();
		
		String id;
		SquareCenter n;
		ChessPiece test = null;
		
		//check right
		char blah[] = new char[2];
		blah[0]= (char)((int)curr.charAt(0)+1);
		blah[1] = test instanceof WhitePawn? (char)((int)curr.charAt(1)):(char)((int)curr.charAt(1));
		//System.out.println((int)curr.charAt(1));
		String id1 = new String(blah); 
		//System.out.println(id1);
		n = board.getCenterFromID(id1);
		test =  board.anyPieceOnSquare(n.getX(), n.getY());
		if((test instanceof BlackPawn && this instanceof WhitePawn)|| (test instanceof WhitePawn && this instanceof BlackPawn)) 
			{opponents.add((Pawn)test);}
		
		//check left
		blah = new char[2];
		blah[0]= (char)((int)curr.charAt(0)-1);
		blah[1] = test instanceof WhitePawn? (char)((int)curr.charAt(1)):(char)((int)curr.charAt(1));
		//System.out.println((int)curr.charAt(1));
		id1 = new String( blah); 
		//System.out.println(id1);
		n = board.getCenterFromID(id1);
		test =  board.anyPieceOnSquare(n.getX(), n.getY());
		if((test instanceof BlackPawn && this instanceof WhitePawn)|| (test instanceof WhitePawn && this instanceof BlackPawn)) 
			{opponents.add((Pawn)test);}

		boolean ep = false;
		can_en_passant = false;
		for(Pawn p : opponents )
		{
			if(p.getMoveCount() == 1 && p.didPawnMoveTwoForwardLastTurn()) //must check last turn
			{
				curr = p.getCenterLocation().toString();
				blah = new char[2];
				blah[0]= (char)((int)curr.charAt(0));
				blah[1] = p instanceof WhitePawn? (char)((int)curr.charAt(1)-1):(char)((int)curr.charAt(1)+1);
				id1 = new String(blah);
				//System.out.println("End location:" + id1);
				n = board.getCenterFromID(id1);
				//System.out.println(n);
				moves.add(n);
				can_en_passant = true;
			}
		}
		
		
		//return ep;
		
	}
		
	public abstract ArrayList<SquareCenter> getAttackedSquares();
	public abstract ArrayList<SquareCenter> getAttackedSquares(ArrayList<SquareCenter> white, ArrayList<SquareCenter> black, ChessBoard board);
}