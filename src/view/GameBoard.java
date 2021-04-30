package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import game_logic.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GameBoard extends JPanel {
	private static Game game;
	private static GameFrame frame;
	private List<AsteroidView> fieldstoDraw = new ArrayList<>();
	private JButton move = new JButton("Move");
	private JButton drill = new JButton("Drill");
	JButton mine = new JButton("Mine");
	JButton craft_robot = new JButton("Craft Robot");
	JButton craft_teleport = new JButton("Craft Teleports");
	JButton hide = new JButton("Hide");
	JButton putback = new JButton("Put back material");
	JButton activate_teleport = new JButton("Activate teleport");
	JButton store_in_base = new JButton("Store material on base");
	JButton build = new JButton("Build base");
	volatile private static boolean clicked = false;
	
	
	GameBoard(Game game, GameFrame frame) throws IOException
	{
		this.game = game;
		this.frame = frame;
		initDrawable(game);
		add(move);
		add(drill);
		add(mine);
		add(craft_robot);
		add(craft_teleport);
		add(hide);
		add(putback);
		add(activate_teleport);
		add(store_in_base);
		add(build);
		//setFocusable(true);
		actionClickListeners();
		
		

		
	}
	
	public void chechIntersection()
	{
		
	}
	
	private void initDrawable(Game game) throws IOException //init map lényegében
	{
		fieldstoDraw.clear();
		Map map = game.getMap();
		Vector<Field> fields = map.getFieldList(); //ez it null-t ad vissza
		Random rand = new Random();
		
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(0),100,100));
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(1),300,300));

		//Míg nem találjuk ki a map koordinátákat, de amúgy lehetne így is fancy módon
		/*for(int i=0; i<2; i++)
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
		}*/
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
		//super.paint(g);
		try {
			initDrawable(this.game);
			g.setColor(new Color(250,240,170));
	        g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			drawLines(g);
			drawMap(g); //Vonalra fogja rajzolni az aszteroidákat, nem fordítva -> nem baj ha a vonal átmegy az aszteroidán

			//Graphics2D g2d = (Graphics2D) g;
			//g2d.drawLine(100, 100, 200, 200);
			//g.drawLine(110, 100, 200, 200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void drawMap(Graphics g)
	{

        for(int i=0; i<fieldstoDraw.size(); i++)
        {
        	AsteroidView item = fieldstoDraw.get(i);
        	item.draw(g);
        }        
	}
	
	public void drawLines(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
        
        //TODO: vonalak szomszédok között
        for(int i=0; i<fieldstoDraw.size(); i++)
        {
        	Vector<Field> neighbors = new Vector<Field>();
        	neighbors=fieldstoDraw.get(i).getAsteroid().FindNeighbor();
        	Vector<View> neighborViews = new Vector<View>();
        	for(int h=0; h<neighbors.size(); h++)
        	{
        		for(int x=0; x<fieldstoDraw.size(); x++)
        		{
        			if(fieldstoDraw.get(x).getAsteroid().equals(neighbors.get(h)))
        				neighborViews.add(fieldstoDraw.get(x));
        		}
        	}
        	int offset = 0;
        	for(int y=0; y<neighborViews.size(); y++)
        	{
        		g2d.setColor(Color.red);
        		Stroke stroke = new BasicStroke(4f);
        		g2d.setStroke(stroke);
        		//g2d.drawLine(100+offset, 100+offset, 200+offset, 200+offset);
        		offset+=50;
        		g2d.drawLine(fieldstoDraw.get(i).getPosx()+60, fieldstoDraw.get(i).getPosy()+60, neighborViews.get(y).getPosx()+60, neighborViews.get(y).getPosy()+60);
        	}
        }
	}
	
	public void actionClickListeners()
	{
		move.addActionListener(e -> {
			//TODO: Ha teleportot választunk ki és nincs párja kezelni
			Vector<String> neighbors_name = new Vector<String>();
			Vector<Field> neighbors = new Vector<Field>();
			neighbors=game.getActiveSettler().GetCurrentField().FindNeighbor();
			for(int i=0; i<neighbors.size();i++)
				neighbors_name.add(neighbors.get(i).Getname());
			JTextField choosen = new JTextField("");
			
			JList<String> jlist = new JList<>(neighbors_name);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					String selected = jlist.getSelectedValue();
					choosen.setText(selected);
				}
			});
			
			System.out.println(choosen.getText());
			
			JScrollPane pane = new JScrollPane(jlist);
			pane.setPreferredSize(new Dimension(100,100));
			JPanel buttonPane = new JPanel();
			JButton but = new JButton("But");
			buttonPane.add(choosen);
			buttonPane.add(but);
			
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, buttonPane);
			splitPane.setDividerLocation(250);
	        splitPane.setEnabled(false);
	        while(choosen.getText().length()<2)
	        {
	        	JOptionPane.showMessageDialog(frame, splitPane, "Select neighbor", JOptionPane.QUESTION_MESSAGE);
	        }
			
	        Field target = null;
	        for(int i=0; i<neighbors.size();i++)
	        {
				if(neighbors.get(i).Getname().equals(choosen.getText()))
						target=neighbors.get(i);
	        }
			game.MoveAction(target);
			
			clicked = true;
			this.repaint();
		});
		
		drill.addActionListener(e ->{
			game.DrillAction();
			clicked=true;
			this.repaint();
		});
		
		mine.addActionListener(e ->{
			game.MineAction();
			clicked=true;
		});
		
		craft_robot.addActionListener(e ->{
			game.CraftRobotAction();
			clicked=true;

		});
		
		craft_teleport.addActionListener(e ->{
			JPanel panel = new JPanel(new BorderLayout(5, 5));
			
			JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
		    label.add(new JLabel("Teleport1", SwingConstants.RIGHT));
		    label.add(new JLabel("Teleport2", SwingConstants.RIGHT));
		    panel.add(label, BorderLayout.WEST);
		    
			JPanel teleport_names = new JPanel(new GridLayout(0,1,2,2));
			JTextField teleport1 = new JTextField();
			JTextField teleport2 = new JTextField();
			teleport_names.add(teleport1);
			teleport_names.add(teleport2);
			panel.add(teleport_names,BorderLayout.CENTER);
			JOptionPane.showMessageDialog(frame, panel, "Add teleport names", JOptionPane.QUESTION_MESSAGE);
			//String telep1 = JOptionPane.showInputDialog("Name of the first teleport:");
			//String telep2 = JOptionPane.showInputDialog("Name of the second teleport:");
			game.CraftTeleportAction(teleport1.toString(), teleport2.toString());
			clicked=true;

		});
		
		hide.addActionListener(e ->{
			game.HideAction();
			clicked=true;

		});
		
		putback.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory_DEBUG();
			Iron iron = new Iron();
			//inInventory.add(iron);
			for(int i=0; i<inInventory.size(); i++)
			{
				mat.add(inInventory.get(i));
			}
			
			JTextField choosen = new JTextField("");
			
			JList<Material> jlist = new JList<>(mat);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					Material selected = jlist.getSelectedValue();
					choosen.setText(selected.toString());
				}
			});
						
			JScrollPane pane = new JScrollPane(jlist);
			pane.setPreferredSize(new Dimension(100,100));
			JPanel buttonPane = new JPanel();
			JButton but = new JButton("But");
			buttonPane.add(choosen);
			buttonPane.add(but);
			
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, buttonPane);
			splitPane.setDividerLocation(250);
	        splitPane.setEnabled(false);
	        if(!inInventory.isEmpty())
	        {
	        	while(choosen.getText().length()<2)
	        	{
	        		JOptionPane.showMessageDialog(frame, splitPane, "Select neighbor", JOptionPane.QUESTION_MESSAGE);
	        	}
	        }
	        else
	        	JOptionPane.showMessageDialog(frame, "Empty inventory");
	        Material selectedMaterial = null;
	        
	        for(int i=0; i<inInventory.size(); i++)
			{
				if(inInventory.get(i).toString().equals(choosen.getText()))
					selectedMaterial=inInventory.get(i);
			}
			
			game.PutAction(selectedMaterial);
			clicked=true;

		});
		
		activate_teleport.addActionListener(e ->{
					
			List<Teleport> teleportInventory = new ArrayList<Teleport>();
			Vector<String> teleportNames = new Vector<String>();
			Settler activeSettler = game.getActiveSettler();
			teleportInventory = activeSettler.getTeleportInventory();
			Vector<Teleport> teleports = new Vector<Teleport>();
			Teleport t1 = new Teleport("aas");
			//teleportInventory.add(t1);
			for(int i=0; i<teleportInventory.size(); i++)
			{
				teleportNames.add(teleportInventory.get(i).Getname());
			}
			
			JTextField choosen = new JTextField("");
			
			JList<String> jlist = new JList<>(teleportNames);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					String selected = jlist.getSelectedValue();
					choosen.setText(selected);
				}
			});
						
			JScrollPane pane = new JScrollPane(jlist);
			pane.setPreferredSize(new Dimension(100,100));
			JPanel buttonPane = new JPanel();
			JButton but = new JButton("But");
			buttonPane.add(choosen);
			buttonPane.add(but);
			
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, buttonPane);
			splitPane.setDividerLocation(250);
	        splitPane.setEnabled(false);
	        if(!teleportInventory.isEmpty())
	        {
	        	while(choosen.getText().length()<2)
	        	{
	        		JOptionPane.showMessageDialog(frame, splitPane, "Select teleport", JOptionPane.QUESTION_MESSAGE);
	        	}
	        }
	        else
	        	JOptionPane.showMessageDialog(frame, "Empty inventory");
	        Teleport selectedTeleport = null;
	        
	        for(int i=0; i<teleportInventory.size(); i++)
			{
				if(teleportInventory.get(i).Getname().equals(choosen.getText()))
					selectedTeleport=teleportInventory.get(i);
			}
			
			game.ActivateAction(selectedTeleport);
			clicked=true;

		});
		
		store_in_base.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory_DEBUG();
			Iron iron = new Iron();
			//inInventory.add(iron);
			for(int i=0; i<inInventory.size(); i++)
			{
				mat.add(inInventory.get(i));
			}
			
			JTextField choosen = new JTextField("");
			
			JList<Material> jlist = new JList<>(mat);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
					// TODO Auto-generated method stub
					Material selected = jlist.getSelectedValue();
					choosen.setText(selected.toString());
				}
			});
						
			JScrollPane pane = new JScrollPane(jlist);
			pane.setPreferredSize(new Dimension(100,100));
			JPanel buttonPane = new JPanel();
			JButton but = new JButton("But");
			buttonPane.add(choosen);
			buttonPane.add(but);
			
			JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, buttonPane);
			splitPane.setDividerLocation(250);
	        splitPane.setEnabled(false);
	        if(!inInventory.isEmpty())
	        {
	        	while(choosen.getText().length()<2)
	        	{
	        		JOptionPane.showMessageDialog(frame, splitPane, "Select neighbor", JOptionPane.QUESTION_MESSAGE);
	        	}
	        }
	        else
	        	JOptionPane.showMessageDialog(frame, "Empty inventory");
	        Material selectedMaterial = null;
	        
	        for(int i=0; i<inInventory.size(); i++)
			{
				if(inInventory.get(i).toString().equals(choosen.getText()))
					selectedMaterial=inInventory.get(i);
			}
	        
			game.StoreAction(selectedMaterial);
			clicked=true;

		});
		
		build.addActionListener(e ->{
			game.BuildAction();
			clicked=true;

		});
	}
	
	public static void selectAction()
	{
		while(clicked==false)
		{
			//System.out.println();
		}
		clicked=false;
	}

}
