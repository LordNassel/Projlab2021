package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import game_logic.TempGenWorlds;

public class MenuView implements ActionListener {
	 SettingsView settings;
	 GameView game;
	JFrame frame;
	JButton button_start_game = new JButton("Start Game");
	JButton button_settings = new JButton("Settings");
	private static ArrayList<AsteroidView> asteroidViews = new ArrayList<AsteroidView>();
	JPanel panel;

	public MenuView()
	{
		frame = new JFrame();
		frame.setTitle("Aszteroidabanyaszat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		frame.setVisible(true);
		//settings = new SettingsView();
		//game = new GameView();
		
		panel = new JPanel() {
			@Override
			public Dimension getPreferredSize()
			{
				return new Dimension(300,300);
			}
		};
		panel.setLayout(null);
		
		//ActionListener al = new ActionListener()
		button_start_game.addActionListener(this);
				
		
		panel.add(button_start_game);
		panel.add(button_settings);
		frame.add(panel);
		for (AsteroidView bv : asteroidViews) {
			panel.add(bv);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String valami = e.getActionCommand();
		System.out.println(valami);
		button_start_game = (JButton)e.getSource();
		settings = new SettingsView();
		System.out.println("Bazdmeg");
		System.out.println(valami);

		
		button_settings = (JButton)e.getSource();
		//TempGenWorlds temp = new TempGenWorlds();
		//temp.Generateworlds(1);
	}
	
	public static void addView(AsteroidView v) {
		asteroidViews.add(v);
	}
}
