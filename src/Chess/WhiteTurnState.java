public class WhiteTurnState implements TurnState
{
	private ChessBoard board;
	private ChessPiece piece;
	public void setPiece(ChessPiece piece)
	{	this.piece = piece;
		if(piece != null) {piece.highlightMoves();}
	}
	public WhiteTurnState(ChessBoard board)
	{
		this.board = board;
	}
	
	public boolean legalPieceSelected(int x, int y)
	{
		//ChessPiece piece = board.anyPieceOnSquare(x,y);
		
		return (board.whitePieceOnSquare(x,y) || piece instanceof WhiteKing);//don't want to change turns until move is completed
	}
	
	public void changeTurns()
	{
		board.changeTurnState(board.getBlackTurnState());
	}
	
	public String toString(){return "White's Turn";}
	
}