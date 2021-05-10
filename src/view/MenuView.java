package view;

import game_logic.Game;
import game_logic.TempGenWorlds;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.IOException;
import java.util.Vector;

/**
 * A MenuView osztály.
 * Pályaválasztáshoz
 */
public class MenuView extends JFrame {

	/**
	 * Konstrukor.
	 * Meghívja a selectMap() függvényt.
	 * Legenerálja és elindítja a kiválasztott pályát.
	 */
	public MenuView()
	{
		JButton start_game = new JButton("Start Game");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(400,100));
		this.setTitle("Aszteroidabanyaszat");
		this.setLocationRelativeTo(null);
		
		JPanel buttonContainer = new JPanel();
		this.add(buttonContainer);
		buttonContainer.setLayout(new GridLayout());
		buttonContainer.add(start_game);
		
		start_game.addActionListener(e -> {
			GameFrame gameView;
			try {
				int selected = selectMap();
				TempGenWorlds temp = new TempGenWorlds();
				Game game = temp.Generateworlds(selected);
				
				Thread thread = new Thread() {
					public void run() {
						game.StartGame();
					}
				};
				thread.start();
				
				gameView = new GameFrame(game);
				gameView.setVisible(true);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			this.dispose();
		});
	}

	/**
	 * A játszani kivánt pálya kiválasztása.
	 */
	public int selectMap()
	{
		int selected = 0;
		Vector<String> maps = new Vector<String>();
		maps.add("1. Normal map");
		maps.add("2. Uranium explode test map");
		maps.add("3. Move with teleport test map");
		maps.add("4. Crafting test map");
		
		JList<String> jlist = new JList<>(maps);
		JTextField choosen = new JTextField("");

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
		
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pane, buttonPane);
		splitPane.setDividerLocation(250);
        splitPane.setEnabled(false);
        JOptionPane.showMessageDialog(this, splitPane, "Select map", JOptionPane.QUESTION_MESSAGE);
        if(choosen.getText().equals("1. Normal map"))
        	selected=1;
        else if(choosen.getText().equals("2. Uranium explode test map"))
        	selected=2;
        else if(choosen.getText().equals("3. Move with teleport test map"))
        	selected=3;
        else if(choosen.getText().equals("4. Crafting test map"))
        	selected=4;
		return selected;
	}
	

}
