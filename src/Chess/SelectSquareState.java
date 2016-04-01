public class SelectSquareState implements MoveState
{
	private ChessBoard board;
	private ChessPiece piece;
	
	public SelectSquareState(ChessBoard board){this.board = board;}
	public void setSelectedPiece(ChessPiece piece){	this.piece = piece;}
	public void mouseClicked(int x, int y)
	{
		//System.out.println("In select square state");
		if(piece == null) return; //no piece is selected
		//System.out.println(piece);
		//get square selected
		SquareCenter square_selected = board.getSquareClicked(x,y);
		boolean legal = board.legalMoveSelected(x,y);
		if(square_selected != null)
		{
			//see if castle
			if(piece instanceof King)
			{
				if(((King)piece).castle(square_selected))
				{
					move(null);
					return;
				}
			}
			//see if move is capture
			ChessPiece to_capture = board.anyPieceOnSquare(x,y);
			if((board.whitePieceOnSquare(piece)&&board.whitePieceOnSquare(to_capture)) || (board.blackPieceOnSquare(piece)&&board.blackPieceOnSquare(to_capture)))
			{
				piece.setSelected(false);
				piece = to_capture;
				piece.highlightMoves();
			}
			//if to_capture is an ally
			//set current piece selected to false
			//set currentpiece to to_capture
			
			//highlight moves
			//move
			boolean res = piece.move(square_selected);
			System.out.println(res);
			if(res)
			{
				move(to_capture);
			}
			//else{System.out.println(piece + " not moved");}
		}
		else
		{
			//change back, an illegal selections means they want to cancel action
			 
			cancel();
		}
			
	}
	
	public void cancel()
	{
		piece.setSelected(false);
		board.changeState(board.getSelectPieceState());
		//System.out.println(piece + " not moved");
		piece = null;
	}
	
	public void move(ChessPiece to_capture)
	{
		piece.deselectAttacked(); //piece is in new loc so the old squares may/may not be attacked now.
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
}