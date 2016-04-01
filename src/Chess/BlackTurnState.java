public class BlackTurnState implements TurnState
{
	private ChessBoard board;
	private ChessPiece piece;
	
	public void setPiece(ChessPiece piece)
	{	this.piece = piece;
		if(piece != null) {piece.highlightMoves();}
	}
	
	public BlackTurnState(ChessBoard board)
	{
		this.board = board;
	}
	
	public boolean legalPieceSelected(int x, int y)
	{
		ChessPiece piece = board.anyPieceOnSquare(x,y);
		return (board.blackPieceOnSquare(x,y) || piece instanceof BlackKing);//don't want to change turns until move is completed
	}
	
	public void changeTurns()
	{
		board.changeTurnState(board.getWhiteTurnState());
	}
	
	public String toString(){return "Black's Turn";}
}