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
import java.util.Iterator;

public class ChessBoard extends JPanel implements MouseListener
{
	private Graphics g;
	private int board_size;
	private int height;
	private int width;
	private int high_x;
	private int high_y;
	private int square_size;
	private Color dark = new Color(102,60,0);
	private Color light = new Color(226,204,171);
	private Color highlight = new Color(0, 102, 204);
	private ArrayList<SquareCenter> centers = new ArrayList<SquareCenter>();
	
	public ChessBoard(int width, int height)
	{
		
		board_size = width > height ? height:width;
		this.width = width+10;
		this.height = height+10;
		addMouseListener(this);
		//this.add(board);
		
	}
	public void mouseClicked(MouseEvent e)
	{
		//System.out.println(e.getX() + " " + e.getY());
		
		//where was the mouse clicked?
		int x = e.getX(); 
		int y = e.getY();
		
		//go through each center, and if it's center is inbetween a square's boundaries, fill it out
		Iterator<SquareCenter> iter = centers.iterator();
		while(iter.hasNext())
		{
			//get x and y boundaries from the center
			SquareCenter center = iter.next();
			int square_x_start = center.getX() - (square_size/2) ;
			int square_x_end  = center.getX() + (square_size/2);
			int square_y_start  = center.getY() - (square_size/2);
			int square_y_end = center.getY() + (square_size/2);
			
			
			if(square_x_start <=  x && x <= square_x_end && square_y_start <=  y && y <= square_y_end)
			{
				//a mouse click is in some boundaries			
				if((square_x_start == high_x && square_y_start == high_y))
				{
					//same square, so deselect it
					high_x = 0;
					high_y = 0;
				}
				else
				{
					
					high_x = square_x_start;
					high_y = square_y_start;
				}
				repaint();
				return;
			}	
		}				
	}
	
	public void paintComponent(Graphics g)
	{
		this.g = g;
		super.paintComponent(g);
	}
	
	public void paint(Graphics g)
	{
		//System.out.println("in paint");
		this.g = g;
		int start_x = Math.abs(height-width)/2 - 10;
		int start_y = Math.abs(height-width)/2 - 10;
		square_size = board_size/8;
		//System.out.println(square_size);
		//g.fillRect(Math.abs(height-width)/2, Math.abs(height-width)/2, board_size, board_size);
		g.drawRect(start_x, start_y, board_size, board_size);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		g2.drawRect(start_x, start_y, board_size, board_size);
		Color curr = light;
		//draw board
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				g.setColor(curr);
				g.fillRect(start_x, start_y, square_size, square_size);
				centers.add(new SquareCenter(start_x+square_size/2, start_y+square_size/2));
				start_x += square_size;
				if(j != 7) curr = (curr==light)?dark:light;		
			}
			start_y += square_size;
			start_x = Math.abs(height-width)/2 - 10;
		}
			g.setColor(highlight);
			highlightSquare(high_x, high_y, g);
			 
	}
	
	public void mouseEntered(MouseEvent e){};
	public void mouseExited(MouseEvent e){};
	public void mousePressed(MouseEvent e){};
	public void mouseReleased(MouseEvent e){};
	
	public void highlightSquare(int center_x, int center_y, Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10));
		if(high_x >= 100 && high_y >= 100) g.drawRect(high_x, high_y, square_size, square_size);
	}
	

}