package Chess;
public interface TurnState
{
	public boolean legalPieceSelected(int x, int y);
	public void changeTurns();
	public void setPiece(ChessPiece piece);
}