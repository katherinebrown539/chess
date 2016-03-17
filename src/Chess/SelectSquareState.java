public class SelectSquareState implements MoveState
{
	private ChessBoard board;
	private ChessPiece piece;
	
	public SelectSquareState(ChessBoard board){this.board = board;}
	public void setSelectedPiece(ChessPiece piece){	this.piece = piece;}
	public void mouseClicked(int x, int y)
	{
		System.out.println("In select square state");
		if(piece == null) return; //no piece is selected
		System.out.println(piece);
		//get square selected
		SquareCenter square_selected = board.getSquareClicked(x,y);
		if(square_selected != null)
		{
			//see if capture
			ChessPiece to_capture = board.anyPieceOnSquare(x,y);
			
			
			//move
			boolean res = piece.move(square_selected);
			System.out.println(res);
			if(res)
			{
				piece.deselectAttacked();
				//change back to selectPieceState, deselecting piece
				board.changeState(board.getSelectPieceState());
				if(to_capture != null)
				{
					//do stuff to capture piece
					to_capture.isCaptured();
					board.removePiece(to_capture);
				}
				piece.incrementMoveCount();
				piece.setSelected(false);
				board.changeTurns();
				
				//set the attacked pieces
				if(board.blackPieceOnSquare(piece.getCenterLocation().getX(),piece.getCenterLocation().getY() )) piece.setAttackedByBlack(true);
				if(board.whitePieceOnSquare(piece.getCenterLocation().getX(),piece.getCenterLocation().getY() )) piece.setAttackedByWhite(true);
				
				//if king is attacked
				//put board in check
				
				//System.out.println(piece + "moved");
			}
			//else{System.out.println(piece + " not moved");}
		}
		else
		{
			//change back, an illegal selections means they want to cancel action
			piece.setSelected(false);
			board.changeState(board.getSelectPieceState());
			piece = null; 
			System.out.println(piece + " not moved");
		}
			
	}
	
}