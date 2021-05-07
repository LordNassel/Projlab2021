package view;

import javax.swing.JButton;

import game_logic.Game;
import game_logic.TempGenWorlds;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.*;

public class GameView extends JFrame {
	
	public GameView()
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
			//Game game = Game.getInstance();
			// Map bet�lt�se: game.init -> map = new Map stb. mint nek�nk a TempGenWorld
			GameFrame gameView;
			try {
				selectMap();
				TempGenWorlds temp = new TempGenWorlds();
				Game game = temp.Generateworlds(2);
				
				Thread thread = new Thread() {
					public void run() {
						game.StartGame();
					}
				};
				thread.start();
				
				gameView = new GameFrame(game);
				gameView.setVisible(true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.dispose();
		});
	}
	
	public void selectMap()
	{
		
	}
	

}
