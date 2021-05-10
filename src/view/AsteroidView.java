package view;

import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Az Asteroid view osztaly
 */
public class AsteroidView extends View {

	/**
	 * A kirajzolni k�v�nt aszteroida.
	 */
	private Asteroid a;

    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();

	/**
	 * Az Asteroid k�pe.
	 */
    protected Image image;

	/**
	 * Visszaadja a kirajzolni k�v�nt aszteroid�t.
	 */
	public Asteroid getAsteroid() {
		return a;
	}

	/**
	 * Be�ll�tja a kirajzolni k�v�nt aszteroid�t.
	 */
	public void setAsteroid(Asteroid t) {
		this.a = t;
	}

	/**
	 * Kirajzolja az aszteroida inform�ci�it (N�v, k�reg vastags�g, nyersanyag t�pus �s sz�m)
	 */
	public void drawAsteroidInfos(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Verdana", Font.BOLD, 13));

		/**
		 * Aszteroida neve
		 */
		g2d.drawString(a.Getname(), x+15, y+130);
		
		/**
		 * Aszteroida vastagsaga
		 */
		String thickness = String.valueOf(a.getThickness());
		g2d.drawString("Thickness: "+thickness, x+10, y+150);
		
		/**
		 * Aszteroidaban levo nyersanyag es szama
		 */
		String materialtype = "";
		if(!a.getMats().isEmpty())
		{
			if(a.getMats().get(0) instanceof Uranium)
				materialtype = "Uranium: ";
			else if(a.getMats().get(0) instanceof Iron)
				materialtype = "Iron: ";
			else if(a.getMats().get(0) instanceof Coal)
				materialtype = "Coal: ";
			else if(a.getMats().get(0)instanceof Ice)
				materialtype = "Ice: ";
		}
		else
		{
			g2d.drawString("Empty", x+10, y+170);
			return;
		}
		String numbofmat = String.valueOf(a.getMats().size());
		g2d.drawString(materialtype + numbofmat, x+10, y+170);
	}

	/**
	 * Konstrukor.
	 */
	public AsteroidView(Asteroid a) throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/asteroid.png"));
		if(a.getSunSide() == true)
			image = ImageIO.read(AsteroidView.class.getResource("/kep/sunside_asteroid.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
		this.image = newimg;
		tmp = new ImageIcon(newimg);
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		} 
		this.a = a;
	}

	/**
	 * Kirajzolja az Asteroid k�p�t.
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}

