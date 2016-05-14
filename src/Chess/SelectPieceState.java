public class SelectPieceState implements MoveState
{
	private ChessBoard board;
	
	public SelectPieceState(ChessBoard board){this.board = board;}
	public void mouseClicked(int x, int y)
	{
		//System.out.println("in select piece state");
		ChessPiece selected = board.anyPieceOnSquare(x,y);
		//System.out.println(selected);
		boolean legal = board.legalMoveSelected(x,y); //was a legal piece selected for the current player
		if(legal) 
		{	
			board.setPiece(selected);//tell the turn what piece was selected
			if(selected != null) selected.setSelected(true);
		}
		
		//String message = legal?"A legal piece was selected!":"An illegal piece was selected!";
		//System.out.println(message);
		if(!legal && selected != null)
		{
			selected.setSelected(false); 
			board.getSelectSquareState().setSelectedPiece(null);
			return;
		}
		if(selected != null  && legal && selected.isSelected())
		{
			//board.refreshAttacked();
			if(selected instanceof King){((King) selected).getLegalMoves(board.getPiecesWhiteAttacks(), board.getPiecesBlackAttacks(), board);}
			selected.highlightMoves();
			SelectSquareState sss = board.getSelectSquareState();
			//change state to SelectSquareState
			board.changeState(sss);
			//pass along selected piece
			sss.setSelectedPiece(selected);
			//System.out.println(selected + " selected.");
		}
		//piece was deselected
		//System.out.println(selected + "deselected.");
	}
	
	//nothing of importance was selected
}