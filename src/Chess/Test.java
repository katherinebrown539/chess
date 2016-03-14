import javax.swing.*;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class Test extends JFrame
{
	private JButton jb;
	private ImageIcon image;
	
	
	public static void main(String[] args)
	{
		Test t = new Test();
	}
	
	public Test()
	{
		super("I'M GOING TO GET THIS WORKING!!!!!!!!!!!");
		
		String path = "resources/pieces/white/rook.jpg";
		 
		  Image img = null;   

		  try
		  {
			  BufferedImage bi = ImageIO.read(new File(path));
			 //BufferedImage bi = ImageIO.read(getClass().getResource(file_name));
			 img = Toolkit.getDefaultToolkit().createImage(bi.getSource());
		  } 
		  catch (IOException e){System.out.println("Let me guess, something went wrong...");}
		
		
		//if(this.getClass().getResource(path) == null){System.out.println("Null");return;}//image = new ImageIcon();
		//if(image == null) {System.out.println("null"); return;}
		ImageIcon image = new ImageIcon(img);
		jb = new JButton(image);
		jb.setIcon(image);
		this.add(jb);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}