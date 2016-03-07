import java.util.ArrayList;
import java.util.Iterator;
public class BlackPlayer
{
	private ArrayList<BlackPawn> pawns;
	private ArrayList<BlackRook> rooks;
	private ArrayList<BlackKnight> knights;
	private ArrayList<BlackBishop> bishops;
	private ArrayList<BlackQueen> queens;
	private BlackKing king;
	
	private ChessGame game;
	
	
	public BlackPlayer(ChessGame game, int square_size)
	{
		this.game = game;
		pawns = new ArrayList<BlackPawn>();
		createAndAddPawns(square_size);
		createAndAddQueen(square_size);
		createAndAddRooks(square_size);
		createAndAddBishops(square_size);
		createAndAddKing(square_size);
		createAndAddKnights(square_size);
	}
	
	public void createAndAddKnights(int square_size)
	{
		knights = new ArrayList<BlackKnight>();
		String[] knights_starts = {"B8","G8"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			BlackKnight knight = new BlackKnight(game, square_size);
			knights.add(knight);
			game.addPiece(knight, knights_starts[i]);
		}
	}
	public void createAndAddKing(int square_size)
	{
		king = new BlackKing(game, square_size);
		String king_start =  "E8";
		game.addPiece(king, king_start);
	}
	public void createAndAddRooks(int square_size)
	{
		rooks = new ArrayList<BlackRook>();
		String[] rook_starts = {"A8","H8"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			BlackRook rook = new BlackRook(game, square_size);
			rooks.add(rook);
			game.addPiece(rook, rook_starts[i]);
		}
	}
	public void createAndAddBishops(int square_size)
	{
		bishops = new ArrayList<BlackBishop>();
		String[] bishop_starts = {"C8","F8"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			BlackBishop bishop = new BlackBishop(game, square_size);
			bishops.add(bishop);
			game.addPiece(bishop, bishop_starts[i]);
		}
	}
	public void createAndAddQueen(int square_size)
	{
		queens = new ArrayList<BlackQueen>();
		String queen_start = "D8";
		queens.add(new BlackQueen(game, square_size));
		game.addPiece(queens.get(0), queen_start);
	}
	public void createAndAddPawns(int square_size)
	{
		pawns = new ArrayList<BlackPawn>();
		String[] pawn_starts = {"A7", "B7", "C7", "D7", "E7", "F7", "G7", "H7"};
		for(int i = 0; i < 8; i++)
		{
			BlackPawn pawn = new BlackPawn(game, square_size);
			pawns.add(pawn);
			//if(game != null) System.out.println("null game");
			game.addPiece(pawn, pawn_starts[i]);
		}
	}
}