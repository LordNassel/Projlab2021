package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SettlerView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	protected Image image;

	public Asteroid getTile() {
		return a;
	}

	public void setTile(Asteroid t) {
		this.a = t;
	}

	public SettlerView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(SettlerView.class.getResource("/kep/settler.png"));
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
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}

