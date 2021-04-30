package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game_logic.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AsteroidView extends View {
	//private static final long serialVersionUID = 7891103595669365281L;

	private Asteroid a;
	
    private ArrayList<SettlerView> parts = new ArrayList<SettlerView>();
    protected Image image;
    JTextField asteroid_name = new JTextField();
    JPanel panel;

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
		this.x = x;
		this.y = y;
		this.a = a;
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
	//	this.add(asteroid_name);
		panel.add(this);
		panel.add(asteroid_name);
		panel.setVisible(true);
		
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
		asteroid_name.setText(a.Getname());
		asteroid_name.setBounds(500, 500, WIDTH, HEIGHT);
		asteroid_name.setVisible(true);
		//this.add(asteroid_name);
		
		List<Movable> list = a.getMovableList();
		for(int i=0; i<list.size(); i++)
		{
			if(list.get(i) instanceof Settler)
			{
				try {
					SettlerView sw = new SettlerView(this.x, this.y-80);
					sw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(list.get(i) instanceof Robot)
			{
				try {
					RobotView rw = new RobotView(this.x+80, this.y-80);
					rw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			else if(list.get(i) instanceof Alien)
			{
				try {
					AlienView aw = new AlienView(this.x+130, this.y);
					aw.draw(g);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		}
	}
}

