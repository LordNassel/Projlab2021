package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TeleportView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Teleport s;
	protected Image image;

	public Teleport getSettler() {
		return s;
	}

	public void setSettler(Teleport t) {
		this.s = t;
	}

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

	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}

