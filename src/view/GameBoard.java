package view;

import game_logic.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Ezen ker�lnek elhelyez�sre a panelek, labelek �s View lesz�rmazottak.
 */
public class GameBoard extends JPanel {
	/**
	 * Nezethez tartozo game
	 */
	private static Game game;
	/**
	 * Nezethez tartozo frame
	 */
	private static GameFrame frame;
	/**
	 * Kirajzolando field-ek listaja
	 */
	private List<AsteroidView> fieldstoDraw = new ArrayList<>();
	/**
	 * Kirajzolando movable-k listaja
	 */
	private List<View> movablestoDraw = new ArrayList<>();
	/**
	 * Kirajzolando teleportok listaja
	 */
	private List<View> teleportstoDraw = new ArrayList<>();
	/**
	 * A Move() action gombja
	 */
	private JButton move = new JButton("Move");
	/**
	 * A Drill() action gombja
	 */
	private JButton drill = new JButton("Drill");
	/**
	 * A Mine() action gombja
	 */
	private JButton mine = new JButton("Mine");
	/**
	 * A Craft:Robot() action gombja
	 */
	private JButton craft_robot = new JButton("Craft Robot");
	/**
	 * A Craft_Teleport() action gombja
	 */
	private JButton craft_teleport = new JButton("Craft Teleports");
	/**
	 * A Hide() action gombja
	 */
	private JButton hide = new JButton("Hide");
	/**
	 * A Put() action gombja (visszarakja a nyersanyagot)
	 */
	private JButton putback = new JButton("Put back material");
	/**
	 * Az ActivateTeleport() action gombja
	 */
	private JButton activate_teleport = new JButton("Activate teleport");
	/**
	 * A base aszteroidan nyersanyag eltarolas action gombja
	 */
	private JButton store_in_base = new JButton("Store material on base");
	/**
	 * A bazis felepites action gombja
	 */
	private JButton build = new JButton("Build base");

	/**
	 * Az inventory gomb.
	 */
	private JButton inventoryButton = new JButton("Inventory");
	/**
	 * Bal oldali panel, ahol a jatekter kirajzolodik
	 */
	JPanel asteroids = new JPanel(null);
	/**
	 * Jobb oldali panel, ahol a gombok es egyeb informaciok talalhatok talalhatok
	 */
	JPanel buttons = new JPanel(new GridBagLayout());
	/**
	 * Aktiv jatekost kiiro JLabel
	 */
	static JLabel active_player = new JLabel();
	/**
	 * Az aktic jatekos poziciojat kiiro JLabel
	 */
	static JLabel player_pos = new JLabel();
	/**
	 * Jobb oldali panelen belul a gombok listaja
	 */
	JPanel buttonsCentered = new JPanel(new GridLayout(0, 1, 10, 10));

	/**
	 * Az inventory label.
	 */
	JLabel inventory = new JLabel("");
	/**
	 * Tarolja ha az aktiv jatekos lepett-e a korben. Ha igen true
	 */
	volatile private static boolean clicked = false;

	/**
	 * Konstrukor. Bellitja a Panelt
	 */
	GameBoard(Game game, GameFrame frame) throws IOException {
		this.game = game;
		this.frame = frame;
		setLayout(new BorderLayout());
		add(asteroids, BorderLayout.WEST);
		add(buttons, BorderLayout.EAST);
		buttons.setLayout(new FlowLayout());
		buttons.setAlignmentY(BOTTOM_ALIGNMENT);
		buttons.setBackground(new Color(250, 240, 170));
		asteroids.setLayout(null);
		buttonsCentered.setBackground(new Color(250, 240, 170));
		buttons.add(buttonsCentered);
		initAsteroids(game);
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

		actionClickListeners();
	}

	/**
	 * Kirajzolja az egyes aszteridak ala a vastagsagukat es belsejeben tartolt nyersanyag mennyiseget
	 * @param g
	 */
	public void drawAsteroidsInfo(Graphics g) {
		for (int i = 0; i < fieldstoDraw.size(); i++) {
			fieldstoDraw.get(i).drawAsteroidInfos(g);
		}
	}

	/**
	 * A jatek indulasakor elhelgyezi sorban egymas utan a mapbol bolvasott aszteroidakat.
	 * Ha elerte az 5-ot, akkor uj sort kezd
	 * @param game
	 */
	private void initAsteroids(Game game)
	{
		Map map = game.getMap();
		Vector<Field> fields = map.getFieldList(); 
		
		int offsetx = 0;
		int offsety = 0;
		int cnt = 0;

		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);			
			
			/*Aszteroid�k*/
			AsteroidView view = (AsteroidView) field.getFieldView();
			view.setViewPosition(70+offsetx, 100+offsety);
			fieldstoDraw.add(view);
			cnt++;
			if (cnt < 5)
				offsetx += 270;
			if (cnt == 5) {
				offsetx = 0;
				offsety += 270;
				cnt = 0;
			}
		}
	}

	/**
	 * Az aszteroidaovben elofordulo dolgok inicializalasa
	 * @param game
	 * @throws IOException
	 */
	private void initDrawable(Game game) throws IOException //init map l�nyeg�ben
	{
		fieldstoDraw.clear();
		movablestoDraw.clear();
		teleportstoDraw.clear();
		Map map = game.getMap();
		Vector<Field> fields = map.getFieldList(); 

		List<Movable> movables = new ArrayList<Movable>();
		List<Teleport> teleport = new ArrayList<Teleport>();

		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);			
			
			/*Teleportok*/
			teleport.addAll(((Asteroid) field).getTeleportsOnAsteroid());
			
			/* Movalbek*/
			movables.addAll(field.getMovableList());
			
			/*Aszteroid�k*/
			AsteroidView view = (AsteroidView) field.getFieldView();
			fieldstoDraw.add(view);
		}
		
		for(int x=0; x<movables.size(); x++)
		{
			movables.get(x).createMovableView();
			movablestoDraw.add(movables.get(x).getView());
		}
		
		for(int y=0; y<teleport.size(); y++)
		{
			teleport.get(y).createFieldView();
			teleportstoDraw.add(teleport.get(y).getFieldView());
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			initDrawable(this.game);
			g.setColor(new Color(250, 240, 170));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.red);
			drawLines(g);
			drawMap(g); 
			drawAsteroidsInfo(g);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * A beovlasott palya tartalmat kirajzolja a screenre
	 * @param g
	 */
	public void drawMap(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Verdana", Font.BOLD, 13));
		g2d.drawString("Radiation: " + game.getradiation(), this.frame.size().width/2, 25 );
		/* Fieldek kirajzol�sa */
		for (int i = 0; i < fieldstoDraw.size(); i++)
		{
			fieldstoDraw.get(i).draw(g);;
		}
		
		/* Karakterek kirajzol�sa */
		for(int y=0; y<movablestoDraw.size(); y++)
		{
			movablestoDraw.get(y).draw(g);;
		}
		
		/* Teleportok kirajol�sa */
		for(int x=0; x<teleportstoDraw.size(); x++)
		{
			teleportstoDraw.get(x).draw(g);
		}
	}

	/**
	 * Szomszedos aszteroidakat egy vonal koti ossze
	 * @param g
	 */
	public void drawLines(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

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
				offset += 50;
				g2d.drawLine(fieldstoDraw.get(i).getPosx() + 60, fieldstoDraw.get(i).getPosy() + 60, neighborViews.get(y).getPosx() + 60, neighborViews.get(y).getPosy() + 60);
			}
		}
	}

	/**
	 * Kulonbozo gobnyomasok hatasara torteno akciok kezelese
	 */
	public void actionClickListeners() {
		/* Mozgas kezelese*/
		move.addActionListener(e -> {
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

		// Furas
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

		//Banyaszas
		mine.addActionListener(e ->{
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
		
		//Robot craftolas
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

		//Teleport craftolas
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

		//Bujas
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

		//Nyersanyag vissza helyezes
		putback.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory();
			int mats = inInventory.size();
			for(int i=0; i<inInventory.size(); i++)
			{
				mat.add(inInventory.get(i));
			}
			
			JTextField choosen = new JTextField("");
			
			JList<Material> jlist = new JList<>(mat);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
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
	        		{
	        			JOptionPane.showMessageDialog(frame, "Empty inventory");
	        			return;
	        		}
	        	

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

		// Teleport aktivalas
		activate_teleport.addActionListener(e ->{
			
			List<Teleport> teleportInventory = new ArrayList<Teleport>();
			Vector<String> teleportNames = new Vector<String>();
			Settler activeSettler = game.getActiveSettler();
			teleportInventory = activeSettler.getTeleportInventory();
			Vector<Teleport> teleports = new Vector<Teleport>();
			for(int i=0; i<teleportInventory.size(); i++)
			{
				teleportNames.add(teleportInventory.get(i).Getname());
			}
			
			JTextField choosen = new JTextField("");
			
			JList<String> jlist = new JList<>(teleportNames);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
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
	        	{
	        		JOptionPane.showMessageDialog(frame, "Empty inventory");
	        		return;
	        	}
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

		// Base aszteroidan nyersanyag gyujtese -> lereakas
		store_in_base.addActionListener(e ->{
			List<Material> inInventory = new ArrayList<Material>();
			Vector<Material> mat = new Vector<Material>();
			Settler activeSettler = game.getActiveSettler();
			inInventory = activeSettler.GetInventory();
			for(int i=0; i<inInventory.size(); i++)
			{
				mat.add(inInventory.get(i));
			}
			
			JTextField choosen = new JTextField("");
			
			JList<Material> jlist = new JList<>(mat);
			jlist.addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(ListSelectionEvent e) {
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
		
		// Base felepitese
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
	
		
		//Az inventory ki�r�sa
		inventoryButton.addActionListener(e -> {

			List<Material> temp = game.getActiveSettler().GetInventory();
			String s = new String();
			if (temp.size() == 0) {
				inventory.setText("The inventory is empty!");
				JPanel jp = new JPanel(new BorderLayout(5, 5));
				jp.setSize(100, 200);
				jp.add(inventory, BorderLayout.CENTER);
				JOptionPane.showMessageDialog(frame, jp);
				return;
			}

			int[] inventorycnt = MaterialDictionary(temp);
			s += "Coal: " + String.valueOf(inventorycnt[0]) + ", ";
			s += "Ice: " + String.valueOf(inventorycnt[1]) + ", ";
			s += "Iron: " + String.valueOf(inventorycnt[2]) + ", ";
			s += "Uranium: " + String.valueOf(inventorycnt[3])+ ", ";

			inventory.setText(s);

			JPanel jp = new JPanel(new BorderLayout(5, 5));
			jp.setSize(100, 200);
			jp.add(inventory, BorderLayout.CENTER);
			JOptionPane.showMessageDialog(frame, jp);
		});

	}

	/**
	 * Nyersanyag megfeleltetes neve es tipusa kozott
	 * @param M
	 * @return
	 */
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

	/**
	 * Jatekos kore, valamilyen actiont v�gre kell hajtania
	 */
	public static void selectAction() {
		active_player.setText("Active player: " + game.getActiveSettler().Getname());
		player_pos.setText("Position: " + game.getActiveSettler().GetCurrentField().Getname());
		while (clicked == false) {
			//System.out.println();
		}
		clicked = false;
	}

	private int[] mtrcntr(Ice ice, int[] temp){
		temp[1]++;
		return temp;
	}

	/**
	 * fieldstoDraw getterje
	 * @return
	 */
	public List<AsteroidView> getFieldstoDraw() {
		return fieldstoDraw;
	}

	/**
	 * Gyozelem eseten win kepernyo megjelenitese
	 */
	public static void Wingame() {

		SpecialFrame sf = new SpecialFrame();
		try {
			sf.WinScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.dispose();
	}

	/**
	 * Vereseg eseten lose kepernyo megjelenitese
	 */
	public static void Losegame() {

		SpecialFrame sf = new SpecialFrame();
		try {
			sf.LoseScreen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.dispose();
	}
}
