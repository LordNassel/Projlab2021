package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	public AlienView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/alien.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		this.image = newimg;
		tmp = new ImageIcon(newimg);
		//ImageIcon tmp = new ImageIcon(AsteroidView.class.getResource("/images/tent.png"));
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
		this.posX=x;
		this.posY=y;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, posX, posY, null);
	}
}

