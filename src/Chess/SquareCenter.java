public class SquareCenter
{
	private int x;
	private int y;
	private String id;
	
	public SquareCenter(int x, int y, String id)
	{
		this.x = x;
		this.y = y;
		this.id = id;

	}
	
	public int getX(){return x;}
	public int getY(){return y;}
	public String getID(){return id;}
	public String toString(){return id;}
}