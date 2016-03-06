import java.util.ArrayList;
import java.util.Iterator;
public class WhitePlayer
{
	private ArrayList<WhitePawn> pawns;
	//private ArrayList<WhiteRook> rooks;
	//private ArrayList<WhiteKnight> knights;
	//private ArrayList<WhiteBishop> bishops;
	//private ArrayList<WhiteQueen> queens;
	//private WhiteKing king;
	
	private ChessGame game;
	
	
	public WhitePlayer(ChessGame game, int square_size)
	{
		this.game = game;
		pawns = new ArrayList<WhitePawn>();
		String[] pawn_starts = {"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2"};
		for(int i = 0; i < 8; i++)
		{
			WhitePawn pawn = new WhitePawn(game, square_size);
			pawns.add(pawn);
			//if(game != null) System.out.println("null game");
			game.addPiece(pawn, pawn_starts[i]);
		}
		
	}
}