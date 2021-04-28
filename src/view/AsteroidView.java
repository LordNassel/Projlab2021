package view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import game_logic.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class AsteroidView extends View {
	//private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	
    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();


	public Asteroid getAsteroid() {
		return a;
	}

	public void setAsteroid(Asteroid t) {
		this.a = t;
	}

	public AsteroidView() throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/asteroid.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		tmp = new ImageIcon(newimg);
		//ImageIcon tmp = new ImageIcon(AsteroidView.class.getResource("/images/tent.png"));
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
	}
	
	public void draw()
	{
		
	}
}

