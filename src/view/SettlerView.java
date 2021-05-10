package view;

import game_logic.Settler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A Settler view osztaly
 */
public class SettlerView extends View {

	private static final long serialVersionUID = 7891103595669365281L;

	/**
	 * A Settler képe.
	 */
	protected Image image;

	private Settler s;

	public Settler getSettler() {
		return s;
	}

	public void setSettler(Settler t) {
		this.s = t;
	}

	/**
	 * Konstrukor, megkapja a koordinátákat.
	 */
	public SettlerView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(SettlerView.class.getResource("/kep/settler.png"));
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

	/**
	 * Kirajzolja a Settler képét.
	 */
	@Override
	public void draw(Graphics g)
	{
		g.drawImage(image, this.x, this.y, null);
	}
}

