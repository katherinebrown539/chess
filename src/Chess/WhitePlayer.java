package Chess;
import java.util.ArrayList;
import java.util.Iterator;
public class WhitePlayer
{
	private ArrayList<WhitePawn> pawns;
	private ArrayList<WhiteRook> rooks;
	private ArrayList<WhiteKnight> knights;
	private ArrayList<WhiteBishop> bishops;
	private ArrayList<WhiteQueen> queens;
	private WhiteKing king;
	
	private ChessGame game;
	
	
	public WhitePlayer(ChessGame game, int square_size)
	{
		this.game = game;
		createAndAddPawns(square_size);
		createAndAddQueen(square_size);
		createAndAddRooks(square_size);
		createAndAddBishops(square_size);
		createAndAddKing(square_size);
		createAndAddKnights(square_size);
		
	}
	
	public void createAndAddKnights(int square_size)
	{
		knights = new ArrayList<WhiteKnight>();
		String[] knights_starts = {"B1","G1"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			WhiteKnight knight = new WhiteKnight(game, square_size);
			knights.add(knight);
			game.addPiece(knight, knights_starts[i]);
		}
	}
	public void createAndAddKing(int square_size)
	{
		king = new WhiteKing(game, square_size);
		String king_start =  "E1";
		game.addPiece(king, king_start);
	}
	public void createAndAddRooks(int square_size)
	{
		rooks = new ArrayList<WhiteRook>();
		String[] rook_starts = {"A1","H1"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			WhiteRook rook = new WhiteRook(game, square_size);
			rooks.add(rook);
			game.addPiece(rook, rook_starts[i]);
		}
	}
	public void createAndAddBishops(int square_size)
	{
		bishops = new ArrayList<WhiteBishop>();
		String[] bishop_starts = {"C1","F1"};
		//String[] rook_starts = {"D4","E4"};
		for(int i = 0; i < 2; i++)
		{
			WhiteBishop bishop = new WhiteBishop(game, square_size);
			bishops.add(bishop);
			game.addPiece(bishop, bishop_starts[i]);
		}
	}
	public void createAndAddQueen(int square_size)
	{
		queens = new ArrayList<WhiteQueen>();
		//String queen_start = "D1";
		String queen_start = "D1";
		queens.add(new WhiteQueen(game, square_size));
		game.addPiece(queens.get(0), queen_start);
	}
	public void createAndAddPawns(int square_size)
	{
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
	
	public WhiteKing getKing()
	{
		return king;
	}
	
	public void removeMoves()
	{
		
	}
	
	public void addMoves()
	{
		
	}
}