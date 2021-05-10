package view;

import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A Goal_Asteroid view osztaly
 */
public class Goal_AsteroidView extends AsteroidView {

	private Goal_Asteroid a;

    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();
    
	public Goal_Asteroid getAsteroid() {
		return a;
	}

	public void setAsteroid(Goal_Asteroid t) {
		this.a = t;
	}

	/**
	 * Kirajzolja az aszteroida információit (Név, kéreg vastagság, nyersanyag típus és szám)
	 */
	public void drawAsteroidInfos(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Verdana", Font.BOLD, 13));
		/* Aszteroida neve */
		g2d.drawString(a.Getname(), x+15, y+130);
		
		/* Aszteroida vastagsaga */
		String thickness = String.valueOf(a.getThickness());
		g2d.drawString("Thickness: "+thickness, x+10, y+150);
		
		/* Aszteroidaban levo nyersanyag es szama */
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
	public Goal_AsteroidView(Goal_Asteroid a) throws IOException {
		super(a);
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/base.png"));

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
	 * Kirajzolja a Goal Asteroid képét.
	 */
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}

