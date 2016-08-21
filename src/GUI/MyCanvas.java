package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import board.Tile;

public class MyCanvas extends JPanel {

	Controller control;
	public MyCanvas(Controller ctrl){
        setBorder(BorderFactory.createLineBorder(Color.black));
        control=ctrl;
        setFocusable(true);
        addKeyListener(control);
        addMouseListener(control);
    }

	public Dimension getPreferredSize() {
		return new Dimension(600,600);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int basesize= Math.min(getHeight(),getWidth());
		int squaresize= basesize/25;
		control.printBoard();

		for(int row=0; row<25; row++){
			for(int col=0;col<25;col++){
				int i=col*squaresize;
				int z=row*squaresize;
				int xoffset=squaresize/4;
				int yoffset=(squaresize*3)/4;
				switch(control.getTile(row, col).getType()){

				case ("wall"):
					g.setColor(Color.black);
					break;
				case ("floor"):
					g.setColor(Color.yellow);
					break;
				case ("door"):
					g.setColor(Color.MAGENTA);
					break;
				case ("start"):
					g.setColor(Color.green);
					break;
				case ("room"):
					g.setColor(Color.white);
					break;
				default:
					g.setColor(Color.red);
					break;
				}

				g.fillRect(i,z, squaresize,squaresize);
				g.setColor(Color.lightGray);
				g.drawRect(i,z, squaresize,squaresize);
				g.setColor(Color.blue);
				g.setFont(new Font("TimesRoman", Font.PLAIN, squaresize));
				if(control.getTile(row, col).getToken()!=null){

					g.drawString(control.getTile(row, col).toString(), i+xoffset, z+yoffset);
				}

			}
		}


	}

}
