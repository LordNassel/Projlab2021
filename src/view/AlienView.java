package view;

import game_logic.Asteroid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Az Alien view osztaly
 */
public class AlienView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	protected Image image;
	protected int posX;
	protected int posY;

	public Asteroid getTile() {
		return a;
	}

	public void setTile(Asteroid t) {
		this.a = t;
	}

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public AlienView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/alien.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		this.image = newimg;
		tmp = new ImageIcon(newimg);
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
		this.posX=x;
		this.posY=y;
	}

	/**
	 * Kirajzolja az Alien képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, posX, posY, null);
	}
}

