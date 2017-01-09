package Chess;
public class SquareCenter
{
	private int x;
	private int y;
	private String id;
	private boolean is_selected;	
	private boolean is_attacked_by_white;
	private boolean is_attacked_by_black;
	
	public SquareCenter(int x, int y, String id)
	{
		this.x = x;
		this.y = y;
		this.id = id;

	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public void setID(String id){this.id = id;}
	public String getID(){return id;}
	public String toString(){return id;}
	public boolean isSelected(){return is_selected;}
	public void setSelected(boolean tf){is_selected = tf;}
	public boolean isAttackedByWhite(){return is_attacked_by_white;}
	public void setAttackedByWhite(boolean tf){is_attacked_by_white = tf;}
	public boolean isAttackedByBlack(){return is_attacked_by_black;}
	public void setAttackedByBlack(boolean tf){is_attacked_by_black = tf;}
}