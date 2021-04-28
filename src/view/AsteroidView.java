package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_logic.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class AsteroidView extends View {
	//private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	
    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();
    protected Image image;
    private int posX;
    private int posY;

	public Asteroid getAsteroid() {
		return a;
	}

	public void setAsteroid(Asteroid t) {
		this.a = t;
	}

	public AsteroidView(Asteroid a, int x, int y) throws IOException {
		super();
		BufferedImage image = ImageIO.read(AsteroidView.class.getResource("/kep/asteroid.png"));
		ImageIcon tmp = new ImageIcon(image);
		Image img = tmp.getImage();
		Image newimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		this.image = newimg;
		tmp = new ImageIcon(newimg);
		//ImageIcon tmp = new ImageIcon(AsteroidView.class.getResource("/images/tent.png"));
		if(tmp != null)
		{
			icon = tmp;
			this.setIcon(icon);
		}
		this.posX = x;
		this.posY = y;
		this.a = a;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, posX, posY, null);
		List<Movable> list = a.getMovableList();
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i) instanceof Settler)
			{
				try {
					SettlerView sw = new SettlerView(this.posX, this.posY-80);
					sw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(list.get(i) instanceof Robot)
			{
				try {
					RobotView rw = new RobotView(this.posX+80, this.posY-80);
					rw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(list.get(i) instanceof Alien)
			{
				try {
					AlienView aw = new AlienView(this.posX+130, this.posY);
					aw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}
}

