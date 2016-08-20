package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import board.Tile;

public class MyCanvas extends JPanel {

	Controller control;
	public MyCanvas(Controller ctrl){
        setBorder(BorderFactory.createLineBorder(Color.black));
        control=ctrl;
    }

	public Dimension getPreferredSize() {
		return new Dimension(500,500);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int basesize= Math.min(getHeight(),getWidth());
		int squaresize= basesize/25;
		Tile[][] tiles=control.getTiles();

		for(int row=0; row<25; row++){
			for(int col=0;col<25;col++){
				int i=col*squaresize;
				int z=row*squaresize;
				switch(tiles[row][col].getType()){
				case ("wall"):
					g.setColor(Color.black);
				case ("floor"):
					g.setColor(Color.yellow);
				case ("door"):
					g.setColor(Color.MAGENTA);
				case ("start"):
					g.setColor(Color.green);
				case ("room"):
					g.setColor(Color.white);
				default:
					g.setColor(Color.red);
				}

				g.fillRect(i,z, squaresize,squaresize);
				g.setColor(Color.lightGray);
				g.drawRect(i,z, squaresize,squaresize);
			}
		}


	}

}
