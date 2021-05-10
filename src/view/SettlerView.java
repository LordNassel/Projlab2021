package view;

import game_logic.Settler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A Settler view osztaly
 */
public class SettlerView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6360001034753594970L;
	
	/**
	 * A Settler képe.
	 */
	protected Image image;

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public SettlerView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(SettlerView.class.getResource("/kep/settler.png"));
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
	 * Kirajzolja a Settler képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, this.x, this.y, null);
	}
}

