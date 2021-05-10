package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Az Alien view osztaly
 */
public class AlienView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5543443860155187335L;
	
	/**
	 * Az Alien képe.
	 */
	protected Image image;

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public AlienView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/alien.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image newimg = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		this.image = newimg;
		tmp = new ImageIcon(newimg);
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
		this.x=x;
		this.y=y;
	}

	/**
	 * Kirajzolja az Alien képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}

