package view;

import game_logic.Teleport;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A Teleport view osztaly
 */
public class TeleportView extends View {

	private static final long serialVersionUID = 7891103595669365281L;

	/**
	 * A Teleport képe.
	 */
	protected Image image;

	private Teleport s;

	public Teleport getSettler() {
		return s;
	}

	public void setSettler(Teleport t) {
		this.s = t;
	}

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public TeleportView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(SettlerView.class.getResource("/kep/teleport.png"));
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
	 * Kirajzolja a Teleport képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}

