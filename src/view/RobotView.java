package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import game_logic.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class RobotView extends View {
	private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	protected Image image;

	public Asteroid getTile() {
		return a;
	}

	public void setTile(Asteroid t) {
		this.a = t;
	}

	public RobotView(int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(RobotView.class.getResource("/kep/robot.png"));
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