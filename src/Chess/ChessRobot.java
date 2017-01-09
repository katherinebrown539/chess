package Chess;
import java.awt.Robot;
import java.awt.AWTException;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import com.sun.glass.events.KeyEvent;
public class ChessRobot
{
	public static void main(String[] args) throws AWTException
	{
		try
		{
			robot = new Robot();
			robot.setAutoDelay(200);	
			ArrayList<String> moves = getMovesFromFile("resources/chessgame.txt");
			//play();
			
			
			while(true) 
			{
				game = new ChessGame(1200, 900);
				playFullGame(moves);
			
			}
		}
		catch(Exception e){}
		
	}	
	
	private static Robot robot;
	private static ChessGame game;
	private static int left_button = InputEvent.BUTTON1_MASK;
	
	public static ArrayList<String> getMovesFromFile(String filename)
	{
		ArrayList<String> to_return = new ArrayList<String>();
		ReadTextFile rtf = new ReadTextFile(filename);
		String line = rtf.readLine();
		while(!rtf.EOF())
		{
			to_return.add(line);
			line = rtf.readLine();
		}
		
		return to_return;
	}
	
	public static void playFullGame(ArrayList<String> moves)
	{
		for(String m:moves)
		{
			String[] tokens = m.split(" ");
			move(tokens[0], tokens[1]);
		}
		
		game.alertWhiteWinner();
		selectOKAY();
	}
	
	public static void play()
	{
		ruyLopez();
		castleWhiteKingside();
		forceBlackEnPassant();
		forceBlackPromotion();
		forceWhiteEnPassantAndPromotion();
	}
	
	
	public static void selectOKAY()
	{
		robot.mouseMove(675, 650);
		robotMouseClick();
	}
	public static void forceBlackPromotion()
	{
		move("D1", "E2");
		move("D3", "E2");
		move("F1", "D1");
		move("E2", "E1");
		selectOKAY();
		move("D1", "D8");
		move("E1", "C1");
	}
	
	public static void forceWhiteEnPassantAndPromotion()
	{
		move("A2", "A4");
		move("C1", "G5");
		move("B5", "C6");
		move("D8", "D7");
		move("A3", "A4");
		move("E5", "E4");
		move("A4", "A5");
		move("B7", "B5");
		move("A5", "B6");
		move("A7", "A6");
		move("B6", "B7");
		move("E8", "E7");
		move("B7","A8");
		chooseRook();
		
	}
	public static void ruyLopez()
	{
		move("E2","E4");
		move("E7", "E5");
		move("G1", "F3");
		move("B8", "C6");
		move("F1", "B5");
		move("D7", "D5");
		
	}
	
	public static void pressKey(int key)
	{
		robot.keyPress(key);
		robot.keyRelease(key);
	}
	
	public static void forceBlackEnPassant()
	{
		move("D5", "E4");
		move("D2", "D4");
		move("E4;", "D3");
		
	}
	
	
	public static void chooseQueen()
	{
		selectOKAY();
	}
	
	public static void chooseKnight()
	{
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_ENTER);
		pressKey(KeyEvent.VK_ENTER);

	}
	
	public static void chooseRook()
	{
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_ENTER);
		pressKey(KeyEvent.VK_ENTER);
	}
	
	public static void chooseBishop()
	{
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_DOWN);
		pressKey(KeyEvent.VK_ENTER);
		pressKey(KeyEvent.VK_ENTER);
	}
	
	
	public static void castleWhiteKingside()
	{
		move("E1", "G1"); //white o-o
		
	}
	public static void firstDemo()
	{
		move("E2", "E4"); //wh e3-e4
		move("E7", "E5");//bl e7-e5
		move("F1","D3");//wh bf1-d3
		move("D8","F6");//bl qd8-f6
		move("G1", "F3");//wh ng1-f2
		move("B8", "C6");//bl nb8-c6
		move("D1", "E2");//wh qd1-f3
		move("D7", "D6");//bl d7-d6
		move("E1", "G1"); //white o-o
		move("C8", "C7");
		move("C8", "A1");
		move("C8", "D7");
		move("H8", "H1");
		move("F1", "E1");
		move("E8","C8"); 	
	}
	
	public static void move(String start, String end)
	{
		clickSquare(start);
		clickSquare(end);
	}
	
	public static void clickSquare(String squareID)
	{
		SquareCenter c = game.clickSquare(squareID);
		robot.mouseMove(c.getX(), c.getY()+50);
		robotMouseClick();
		
	}
	
	public static void robotMouseClick()
	{
		robot.mousePress(left_button);
		robot.mouseRelease(left_button);
	}
}