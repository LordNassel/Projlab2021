package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_logic.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AsteroidView extends View {
	//private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	
	
    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();
    protected Image image;

	public Asteroid getAsteroid() {
		return a;
	}

	public void setAsteroid(Asteroid t) {
		this.a = t;
	}
	
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
		//ImageIcon tmp = new ImageIcon(AsteroidView.class.getResource("/images/tent.png"));
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		} 
		this.a = a;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}
}

