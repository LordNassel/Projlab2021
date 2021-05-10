package view;

import game_logic.Asteroid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A Robot view osztaly
 */
public class RobotView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	/**
	 * A Robot k�pe.
	 */
	protected Image image;

	private Asteroid a;

	public Asteroid getTile() {
		return a;
	}

	public void setTile(Asteroid t) {
		this.a = t;
	}

	/**
	 * Konstrukor, megkapja a koordin�t�kat.
	 */
	public RobotView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(RobotView.class.getResource("/kep/robot.png"));
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
		this.x=x;
		this.y=y;
	}

	/**
	 * Kirajzolja a Robot k�p�t.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}