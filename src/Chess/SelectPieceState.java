public class SelectPieceState implements MoveState
{
	private ChessBoard board;
	
	public SelectPieceState(ChessBoard board){this.board = board;}
	public void mouseClicked(int x, int y)
	{
		//System.out.println("in select piece state");
		ChessPiece selected = board.anyPieceOnSquare(x,y);
		
		if(selected != null )
		{
			if(selected.isSelected())
			{
				SelectSquareState sss = board.getSelectSquareState();
				//change state to SelectSquareState
				board.changeState(sss);
				//pass along selected piece
				sss.setSelectedPiece(selected);
				//System.out.println(selected + " selected.");
			}
		}
		//piece was deselected
		//System.out.println(selected + "deselected.");
	}
	
	//nothing of importance was selected
}