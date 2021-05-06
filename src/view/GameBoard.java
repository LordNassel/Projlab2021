package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import game_logic.*;

import javax.swing.JButton;
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
	private Game game;
	private static GameFrame frame;
	private List<AsteroidView> fieldstoDraw = new ArrayList<>();
	private List<View> movablestoDraw = new ArrayList<>();
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
	//Az inventory gomv
	JButton inventoryButton = new JButton("Inventory");
	JPanel asteroids = new JPanel(null);
	JPanel buttons = new JPanel(new GridBagLayout());
	JLabel active_player = new JLabel();
	JLabel player_pos = new JLabel();
	JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));
	//Az inventory label
	JLabel inventory = new JLabel("");
	volatile private static boolean clicked = false;


	GameBoard(Game game, GameFrame frame) throws IOException {
		setLayout(new BorderLayout());
		add(asteroids, BorderLayout.WEST);
		add(buttons, BorderLayout.EAST);
		//  buttons.setAlignmentY(Component.CENTER_ALIGNMENT);
		buttons.setLayout(new FlowLayout());
		buttons.setAlignmentY(BOTTOM_ALIGNMENT);
		buttons.setBackground(new Color(250, 240, 170));
		asteroids.setLayout(null);
		// JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));
		buttonsCentered.setBackground(new Color(250, 240, 170));
		buttons.add(buttonsCentered);
		//setLayout(null);
		this.game = game;
		this.frame = frame;
		initDrawable(game);

		buttonsCentered.add(active_player);
		buttonsCentered.add(player_pos);
		buttonsCentered.add(inventory);
		buttonsCentered.add(move, BorderLayout.SOUTH);
		buttonsCentered.add(drill, BorderLayout.SOUTH);
		buttonsCentered.add(mine, BorderLayout.SOUTH);
		buttonsCentered.add(craft_robot, BorderLayout.SOUTH);
		buttonsCentered.add(craft_teleport, BorderLayout.SOUTH);
		buttonsCentered.add(hide, BorderLayout.SOUTH);
		buttonsCentered.add(putback, BorderLayout.SOUTH);
		buttonsCentered.add(activate_teleport, BorderLayout.SOUTH);
		buttonsCentered.add(store_in_base, BorderLayout.SOUTH);
		buttonsCentered.add(build, BorderLayout.SOUTH);
		buttonsCentered.add(inventoryButton, BorderLayout.SOUTH);

		//setFocusable(true);
		actionClickListeners();


	}

	public void drawAsteroidsInfo(Graphics g) {
		for (int i = 0; i < fieldstoDraw.size(); i++) {
			fieldstoDraw.get(i).drawAsteroidInfos(g);
		}
	}

	public void chechIntersection() {

	}

	private void initDrawable(Game game) throws IOException //init map lényegében
	{
		fieldstoDraw.clear();
		movablestoDraw.clear();
		Map map = game.getMap();
		Vector<Field> fields = map.getFieldList(); //ez it null-t ad vissza
		Random rand = new Random();
		List<Movable> movables = new ArrayList<Movable>();
		
		/*fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(0),100,100));
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(1),400,100));
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(2),100,400));
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(3),400,400));
		fieldstoDraw.add(new AsteroidView((Asteroid)fields.get(4),100,650));*/


		int offsetx = 0;
		int offsety = 0;
		int cnt = 0;

		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			/*if (field instanceof Goal_Asteroid) {
				fieldstoDraw.add(new Goal_AsteroidView((Goal_Asteroid) fields.get(i), 70 + offsetx, 100 + offsety));
				cnt++;
			} else if (field instanceof Asteroid) {
				fieldstoDraw.add(new AsteroidView((Asteroid) fields.get(i), 70 + offsetx, 100 + offsety));
				cnt++;
			}*/
			
			movables.addAll(field.getMovableList());
			
			AsteroidView view = (AsteroidView) field.getFieldView();
			view.setViewPosition(70+offsetx, 100+offsety);
					//field.createFieldView(70+offsetx, 100+offsety);
			fieldstoDraw.add(view);
			cnt++;

			if (cnt < 5)
				offsetx += 270;
			if (cnt == 5) {
				offsetx = 0;
				offsety += 270;
				cnt = 0;
			}

			//else if(field instanceof Teleport)
			//	fieldstoDraw.add(new TeleportView());
		}
		
		for(int x=0; x<movables.size(); x++)
		{
			movables.get(x).createMovableView();
			movablestoDraw.add(movables.get(x).getView());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//super.paint(g);
		try {
			if(!(game.getActiveSettler()==null))
			{
				active_player.setText("Active player: " + game.getActiveSettler().Getname());
				player_pos.setText("Position: " + game.getActiveSettler().GetCurrentField().Getname());
			}
			initDrawable(this.game);
			g.setColor(new Color(250, 240, 170));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			drawLines(g);
			drawMap(g); //Vonalra fogja rajzolni az aszteroidákat, nem fordítva -> nem baj ha a vonal átmegy az aszteroidán
			drawAsteroidsInfo(g);
			

			//Graphics2D g2d = (Graphics2D) g;
			//g2d.drawLine(100, 100, 200, 200);
			//g.drawLine(110, 100, 200, 200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void drawMap(Graphics g) {

		for (int i = 0; i < fieldstoDraw.size(); i++) {
			fieldstoDraw.get(i).draw(g);;
			//item.draw(g);
			//fieldstoDraw.get(i).drawName(asteroids);
		}
		
		for(int y=0; y<movablestoDraw.size(); y++)
		{
			movablestoDraw.get(y).draw(g);;
			//view.draw(g);
		}
	}

	public void drawLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		//TODO: vonalak szomszédok között
		for (int i = 0; i < fieldstoDraw.size(); i++) {
			Vector<Field> neighbors = new Vector<Field>();
			neighbors = fieldstoDraw.get(i).getAsteroid().FindNeighbor();
			Vector<View> neighborViews = new Vector<View>();
			for (int h = 0; h < neighbors.size(); h++) {
				for (int x = 0; x < fieldstoDraw.size(); x++) {
					if (fieldstoDraw.get(x).getAsteroid().equals(neighbors.get(h)))
						neighborViews.add(fieldstoDraw.get(x));
				}
			}
			int offset = 0;
			for (int y = 0; y < neighborViews.size(); y++) {
				g2d.setColor(Color.red);
				Stroke stroke = new BasicStroke(4f);
				g2d.setStroke(stroke);
				//g2d.drawLine(100+offset, 100+offset, 200+offset, 200+offset);
				offset += 50;
				g2d.drawLine(fieldstoDraw.get(i).getPosx() + 60, fieldstoDraw.get(i).getPosy() + 60, neighborViews.get(y).getPosx() + 60, neighborViews.get(y).getPosy() + 60);
			}
		}
	}

	public void actionClickListeners() {
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
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel hidden = new JLabel("You are hidden!");
			JLabel nopair = new JLabel("No pair!");
			
			if(game.getActiveSettler().GetIsHidden()) 
			{
				jp.add(hidden, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
			}
			else if(target.getClass() == Teleport.class && ((Teleport) target).getPair().getIsActive() == false) 
			{
				jp.add(nopair, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
			}
			if(!(target.getClass() == Teleport.class && ((Teleport) target).getPair().getIsActive() == false)) {
			clicked = true;
			this.repaint();
			}
		});

		drill.addActionListener(e ->{
			int thick = ((Asteroid) game.getActiveSettler().GetCurrentField()).getThickness();
			game.DrillAction();
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel zero = new JLabel("Thickness is zero!");
			jp.setSize(100, 200);
				
			if(thick == 0)
			{
				jp.add(zero, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
			}

			clicked=true;
			this.repaint();
		});

		mine.addActionListener(e ->{
			//List<Material> settlermats = game.getActiveSettler().GetInventory_DEBUG();
			int matNum = ((Asteroid) game.getActiveSettler().GetCurrentField()).getMats().size();
			game.MineAction();
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel empty = new JLabel("The asteroid is empty!");
			JLabel thick = new JLabel("Thickness is not zero!");
			jp.setSize(100, 200);
			if(((Asteroid) game.getActiveSettler().GetCurrentField()).getThickness() != 0)
			{
				jp.add(thick, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
			}
			else if(matNum == 0) 
			{
				jp.add(empty, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
			}
			
			clicked=true;
			this.repaint();
		});
		craft_robot.addActionListener(e -> {
			Coal coal = new Coal();

			int ncoal = game.getActiveSettler().getMaterialTypeNumber(coal);

			game.CraftRobotAction();
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel success = new JLabel("Successfull Crafting!");
			JLabel failure = new JLabel("Not enough Materials!");
			jp.setSize(100, 200);
			if (ncoal > game.getActiveSettler().getMaterialTypeNumber(coal)) {
				jp.add(success, BorderLayout.CENTER);
			} else {
				jp.add(failure, BorderLayout.CENTER);
			}
			JOptionPane.showMessageDialog(frame, jp);
			clicked = true;
			this.repaint();
		});

		craft_teleport.addActionListener(e -> {
			Uranium uran = new Uranium();

			int nuran = game.getActiveSettler().getMaterialTypeNumber(uran);
			JPanel panel = new JPanel(new BorderLayout(5, 5));

			JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
			label.add(new JLabel("Teleport1", SwingConstants.RIGHT));
			label.add(new JLabel("Teleport2", SwingConstants.RIGHT));
			panel.add(label, BorderLayout.WEST);

			JPanel teleport_names = new JPanel(new GridLayout(0, 1, 2, 2));
			JTextField teleport1 = new JTextField();
			JTextField teleport2 = new JTextField();
			teleport_names.add(teleport1);
			teleport_names.add(teleport2);
			panel.add(teleport_names, BorderLayout.CENTER);
			JOptionPane.showMessageDialog(frame, panel, "Add teleport names", JOptionPane.QUESTION_MESSAGE);
			//String telep1 = JOptionPane.showInputDialog("Name of the first teleport:");
			//String telep2 = JOptionPane.showInputDialog("Name of the second teleport:");
			game.CraftTeleportAction(teleport1.getText(), teleport2.getText());
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel success = new JLabel("Successfull Crafting!");
			JLabel failure = new JLabel("Not enough Materials!");
			jp.setSize(100, 200);
			if (nuran > game.getActiveSettler().getMaterialTypeNumber(uran)) {
				jp.add(success, BorderLayout.CENTER);
			} else {
				jp.add(failure, BorderLayout.CENTER);
			}
			JOptionPane.showMessageDialog(frame, jp);
			clicked = true;
			this.repaint();
		});

		hide.addActionListener(e ->{
			game.HideAction();
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel successhidden = new JLabel("You are now hidden!");
			JLabel successnothidden = new JLabel("You are now not hidden!");
			JLabel failure = new JLabel("Can't hide there!");
			jp.setSize(100, 200);
			if(!game.getActiveSettler().GetIsHidden())
			{
				if(((Asteroid) game.getActiveSettler().GetCurrentField()).getThickness() == 0 && ((Asteroid)game.getActiveSettler().GetCurrentField()).getMats().isEmpty()) 
				{
					jp.add(successnothidden, BorderLayout.CENTER);
				}
				else 
				{
					jp.add(failure, BorderLayout.CENTER);
				}				
			}
			else 
			{
				jp.add(successhidden, BorderLayout.CENTER);
			}
			JOptionPane.showMessageDialog(frame, jp);
			clicked=true;
			this.repaint();
		});

		putback.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory();
			int mats = inInventory.size();
			//Iron iron = new Iron();
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
	        			JOptionPane.showMessageDialog(frame, splitPane, "Select material", JOptionPane.QUESTION_MESSAGE);
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
			
			JPanel jp = new JPanel(new BorderLayout(5, 5));
			JLabel hidden = new JLabel("You are hidden!");
			JLabel wrong = new JLabel("Wrong Material!");
			JLabel thick = new JLabel("Thickness is not 0!");
			jp.setSize(100, 200);
			if(game.getActiveSettler().GetIsHidden()) 
			{
				jp.add(hidden);
				JOptionPane.showMessageDialog(frame, jp);
			}
			else if (((Asteroid) game.getActiveSettler().GetCurrentField()).getThickness() != 0)
			{
				jp.add(thick);
				JOptionPane.showMessageDialog(frame, jp);
			}
			else if(mats == game.getActiveSettler().GetInventory().size()) 
			{
				jp.add(wrong);
				JOptionPane.showMessageDialog(frame, jp);
			}
			
			clicked=true;
			this.repaint();
		});

		activate_teleport.addActionListener(e ->{
			
			List<Teleport> teleportInventory = new ArrayList<Teleport>();
			Vector<String> teleportNames = new Vector<String>();
			Settler activeSettler = game.getActiveSettler();
			teleportInventory = activeSettler.getTeleportInventory();
			Vector<Teleport> teleports = new Vector<Teleport>();
			//Teleport t1 = new Teleport("aas");
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
	        JPanel jp = new JPanel(new BorderLayout(5, 5));
	        JLabel hidden = new JLabel("You are hidden!");
	        if(!game.getActiveSettler().GetIsHidden()) 
	        {
	        	if(!teleportInventory.isEmpty())
	        	{
	        		while(choosen.getText().length()<2)
	        		{
	        			JOptionPane.showMessageDialog(frame, splitPane, "Select teleport", JOptionPane.QUESTION_MESSAGE);
	        		}
	        	}
	        	else
	        		JOptionPane.showMessageDialog(frame, "Empty inventory");
	        }
	        else 
	        {
	          	jp.add(hidden, BorderLayout.CENTER);
	        	JOptionPane.showMessageDialog(frame, hidden);	
	        }
	        Teleport selectedTeleport = null;
	        
	        for(int i=0; i<teleportInventory.size(); i++)
			{
				if(teleportInventory.get(i).Getname().equals(choosen.getText()))
					selectedTeleport=teleportInventory.get(i);
			}
			
			game.ActivateAction(selectedTeleport);
			clicked=true;
			this.repaint();
		});

		store_in_base.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory();
			//Iron iron = new Iron();
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
	        JPanel jp = new JPanel(new BorderLayout(5, 5));
	        JLabel notgood = new JLabel("Not goal Asteroid!");
	        JLabel hidden = new JLabel("You are hidden!");
	        if(!game.getActiveSettler().GetIsHidden()) 
	        {	        
	        	if(game.getActiveSettler().GetCurrentField().getClass() != Goal_Asteroid.class) 
	        	{
	        		jp.add(notgood);
	        		JOptionPane.showMessageDialog(frame, jp);
	        	}      
	        	else if(!inInventory.isEmpty())
	        	{
	        		while(choosen.getText().length()<2)
	        		{
	        			JOptionPane.showMessageDialog(frame, splitPane, "Select material", JOptionPane.QUESTION_MESSAGE);	        	
	        		}
	        	}
	        	else
	        		JOptionPane.showMessageDialog(frame, "Empty inventory");
	        }
	        else 
	        {
	        	jp.add(hidden, BorderLayout.CENTER);
	        	JOptionPane.showMessageDialog(frame, hidden);
	        }
	        Material selectedMaterial = null;	        
	        
	        for(int i=0; i<inInventory.size(); i++)
			{
				if(inInventory.get(i).toString().equals(choosen.getText()))
					selectedMaterial=inInventory.get(i);
			}
	        
			game.StoreInBaseAction(selectedMaterial);
			clicked=true;
			this.repaint();
		});
		
		build.addActionListener(e ->{
			game.BuildAction();
			JPanel jp = new JPanel(new BorderLayout(5, 5));
	        JLabel notgood = new JLabel("Not goal Asteroid!");
	        JLabel notEnough = new JLabel("Not enough materials!");
	        JLabel hidden = new JLabel("You are hidden!");
	        if(!game.getActiveSettler().GetIsHidden()) 
	        {
		        if(game.getActiveSettler().GetCurrentField().getClass() != Goal_Asteroid.class) 
		        {
		        	jp.add(notgood);
		        	JOptionPane.showMessageDialog(frame, jp);
		        }
		        else if(!((Goal_Asteroid) game.getActiveSettler().GetCurrentField()).GetGamewin())
		        {
		        	jp.add(notEnough);
		        	JOptionPane.showMessageDialog(frame, jp);
		        }
	        }
	        else 
	        {
	        	jp.add(hidden, BorderLayout.CENTER);
	        	JOptionPane.showMessageDialog(frame, hidden);
	        }
			clicked=true;
			this.repaint();
		});
	
		
		//Az inventory kiírása
		inventoryButton.addActionListener(e -> {

			List<Material> temp = game.getActiveSettler().GetInventory();
			String s = new String();
			if (temp.size() == 0) {
				inventory.setText("The inventory is empty!");
				return;
			}

			int[] inventorycnt = MaterialDictionary(temp);
			s += "Coal: " + String.valueOf(inventorycnt[0]) + ", ";
			s += "Ice: " + String.valueOf(inventorycnt[1]) + ", ";
			s += "Iron: " + String.valueOf(inventorycnt[2]) + ", ";
			s += "Uranium: " + String.valueOf(inventorycnt[3]);


			inventory.setText(s);
		});

	}

	private int[] MaterialDictionary(List<Material> M) {

		int[] temp = new int[5];
		for (int i = 0; i < M.size(); i++) {
			if (M.get(i).getname() == "Coal") temp[0] += 1;
			if (M.get(i).getname() == "Ice") temp[1] += 1;
			if (M.get(i).getname() == "Iron") temp[2] += 1;
			if (M.get(i).getname() == "Uranium") temp[3] += 1;
		}
		return temp;

	}


	public static void selectAction() {
		while (clicked == false) {
			//System.out.println();
		}
		clicked = false;
	}

	private int[] mtrcntr(Ice ice, int[] temp){
		temp[1]++;
		return temp;
	}


	public List<AsteroidView> getFieldstoDraw() {
		return fieldstoDraw;
	}

	public static void Wingame() {

		Specialframe sf = new Specialframe();
		sf.WinScreen();
		frame.dispose();
	}

	public static void Losegame() {

		Specialframe sf = new Specialframe();
		sf.LoseScreen();
		frame.dispose();
	}
}
