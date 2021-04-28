package view;

import java.io.IOException;

import javax.swing.JFrame;

import game_logic.Game;

public class GameFrame extends JFrame{
	
	GameFrame(Game game) throws IOException
	{
		GameBoard gameBoard = new GameBoard(game);
		add(gameBoard);
		setSize(600,600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Aszteroidabanyaszat");
		setLocationRelativeTo(null);
	}

}
