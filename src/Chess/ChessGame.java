import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.text.*;
import java.util.Date;
import java.awt.Graphics;
import java.lang.Math;
public class ChessGame extends JFrame
{
	private ChessBoard board;
	private int height;
	private int width;
	
	public ChessGame(int width, int height)
	{
		super("Chess!");
		this.width = width;
		this.height = height;
		board = new ChessBoard(width,height);
		this.add(board);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(this.width+250, this.height+250);
		this.setVisible(true);
	}
}