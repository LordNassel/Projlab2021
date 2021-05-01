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

public class SettlerView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Settler s;
	protected Image image;

	public Settler getSettler() {
		return s;
	}

	public void setSettler(Settler t) {
		this.s = t;
	}

	public SettlerView(Settler settler, int x, int y) throws IOException {
		super();
		this.s=settler;
		BufferedImage image = ImageIO.read(SettlerView.class.getResource("/kep/settler.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(75, 75, Image.SCALE_SMOOTH);
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
	
	/*public void drawSettlerInfos(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Verdana", Font.BOLD, 13));
		/* Settler neve */
		/*g2d.drawString(s.Getname(), x+1000, y+130);
		
		/* Aszteroida vastagsaga */
		//String thickness = String.valueOf(s.getThickness());
		//g2d.drawString("Thickness: "+thickness, x+10, y+150);
	/*}*/
	
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
}

