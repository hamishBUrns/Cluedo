package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import board.Tile;

public class MyCanvas extends JPanel {
	int squaresize;
	Controller control;

	ArrayList<JLabel> labels;

	public MyCanvas(Controller ctrl){
        setBorder(BorderFactory.createLineBorder(Color.black));
        control=ctrl;
        setFocusable(true);
        addKeyListener(control);
        addMouseListener(control);
        int basesize= (int)getPreferredSize().getHeight();
		squaresize= basesize/25;

        labels= new ArrayList<JLabel>();
        createTokenLabels();


    }

	public Dimension getPreferredSize() {
		return new Dimension(600, 600);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (int row = 0; row < 25; row++) {
			for (int col = 0; col < 25; col++) {
				int i = col * squaresize;
				int z = row * squaresize;
				int xoffset = squaresize / 4;
				int yoffset = (squaresize * 3) / 4;
				switch (control.getTile(row, col).getType()) {

				case ("wall"):
					g.setColor(Color.black);
				break;
				case ("floor"):
					g.setColor(Color.yellow);
				break;
				case ("door"):
					g.setColor(Color.orange);
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
				if((row==24 && col==21)||
						(row==19 && col==0)||
						(row==5 && col==23)||
						(row==1 && col==5)){
				g.setColor(Color.magenta);
				}
				g.fillRect(i, z, squaresize, squaresize);

				if(control.getTile(row,col).getToken()!=null){
					switch(control.getTile(row,col).getToken().id()){
					case("Miss Scarlett"):
						labels.get(0).setLocation(i, z);
					break;
					case("Colonel Mustard"):
						labels.get(1).setLocation(i, z);
					break;
					case("Mrs White"):
						labels.get(2).setLocation(i, z);
					break;
					case("The Reverend Green"):
						labels.get(3).setLocation(i, z);
					break;
					case("Mrs Peacock"):
						labels.get(4).setLocation(i, z);
					break;
					case("Professor Plum"):
						labels.get(5).setLocation(i, z);
					break;

					case("("):
						labels.get(6).setLocation(i, z);
					break;
					case("*"):
						labels.get(7).setLocation(i, z);
					break;
					case("~"):
						labels.get(8).setLocation(i, z);
					break;
					case("@"):
						labels.get(9).setLocation(i, z);
					break;
					case("^"):
						labels.get(10).setLocation(i, z);
					break;
					case("$"):
						labels.get(11).setLocation(i, z);
					break;
					}
				g.setColor(Color.lightGray);
				g.drawRect(i,z, squaresize,squaresize);

				}
			}
		}




	}
	private void createTokenLabels(){

        labels.add(createLabel("images/missScarlet.png","ms"));
        labels.add(createLabel("images/colonelMustard.jpg","cm"));
        labels.add(createLabel("images/mrsWhite.png","mw"));
        labels.add(createLabel("images/thereverendgreen.jpg","trg"));
        labels.add(createLabel("images/mrspeacock.jpg","professor plum"));
        labels.add(createLabel("images/profPlum.jpg","pp"));

        labels.add(createLabel("images/pipe.jpeg","pp"));
        labels.add(createLabel("images/spanner.png","pp"));
        labels.add(createLabel("images/rope.jpeg","pp"));
        labels.add(createLabel("images/revolver.jpeg","pp"));
        labels.add(createLabel("images/dagger.png","pp"));
        labels.add(createLabel("images/candlestick.jpeg","pp"));
	}

	private JLabel createLabel(String path, String description){
		ImageIcon imageicon= createImageIcon(path,description);
        imageicon.setImage(getScaledImage(imageicon.getImage(),squaresize,squaresize));
        JLabel label=new JLabel(imageicon);
        label.setOpaque(true);
        this.add(label);
        return label;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected ImageIcon createImageIcon(String path,
	                                           String description) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL, description);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

	private Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
