package view;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import game_logic.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import game_logic.Game;

public class GameBoard extends JPanel {
	private Game game;
	private List<AsteroidView> fieldstoDraw = new ArrayList<>();
	private JButton move = new JButton("Move");
	
	GameBoard(Game game) throws IOException
	{
		this.game = game;
		initDrawable(game);
		add(move);
		setFocusable(true);
		
		move.addActionListener(e -> {
			System.out.println("move");
		});
		
	}
	
	public void chechIntersection()
	{
		
	}
	
	private void initDrawable(Game game) throws IOException
	{
		fieldstoDraw.clear();
		Map map = game.getMap();
		Vector<Field> fields = map.getFieldList(); //ez it null-t ad vissza
		Random rand = new Random();
		
		for(int i=0; i<2; i++)
		{
			int x = rand.nextInt(500);
			int y = rand.nextInt(500);
			Field field = fields.get(i);
			if(field instanceof Asteroid)
				fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(i),x,y));
			//else if(field instanceof Teleport)
			//	fieldstoDraw.add(new TeleportView());
			//x+=100;
			//y+=100;
		}
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		try {
			initDrawable(this.game);
			drawMap(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawMap(Graphics g)
	{
		g.setColor(new Color(250,240,170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for(int i=0; i<fieldstoDraw.size(); i++)
        {
        	AsteroidView item = fieldstoDraw.get(i);
        	item.draw(g);
        }
        
	}

}
