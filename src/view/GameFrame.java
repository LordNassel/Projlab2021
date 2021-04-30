package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import game_logic.Game;

public class GameFrame extends JFrame{
	
	GameFrame(Game game) throws IOException
	{
		GameBoard gameBoard = new GameBoard(game, this);
		add(gameBoard);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screenSize.width, screenSize.height );
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Aszteroidabanyaszat");
		setLocationRelativeTo(null);

	}

}
