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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1428911138691355996L;
	/**
	 * A Robot képe.
	 */
	protected Image image;

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public RobotView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(RobotView.class.getResource("/kep/robot.png"));
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
	 * Kirajzolja a Robot képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}