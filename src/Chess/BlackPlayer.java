import java.util.ArrayList;
import java.util.Iterator;
public class BlackPlayer
{
	private ArrayList<BlackPawn> pawns;
	//private ArrayList<BlackRook> rooks;
	//private ArrayList<BlackKnight> knights;
	//private ArrayList<BlackBishop> bishops;
	//private ArrayList<BlackQueen> queens;
	//private BlackKing king;
	
	private ChessGame game;
	
	
	public BlackPlayer(ChessGame game, int square_size)
	{
		this.game = game;
		pawns = new ArrayList<BlackPawn>();
		String[] pawn_starts = {"A2", "B2", "C2", "D2", "E2", "F2", "G2", "H2"};
		for(int i = 0; i < 8; i++)
		{
			BlackPawn pawn = new BlackPawn(game, square_size);
			pawns.add(pawn);
			//if(game != null) System.out.println("null game");
			game.addPiece(pawn, pawn_starts[i]);
		}
		
	}
}