package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class SettlerView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;

	public Asteroid getTile() {
		return a;
	}

	public void setTile(Asteroid t) {
		this.a = t;
	}

	public SettlerView() throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/settler.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tmp = new ImageIcon(newimg);
		//ImageIcon tmp = new ImageIcon(AsteroidView.class.getResource("/images/tent.png"));
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
	}
}

