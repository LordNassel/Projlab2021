package view;

import game_logic.Game;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * A GameFrame osztály.
 */
public class GameFrame extends JFrame{

	/**
	 * Konstrukor, létre hozza a GameBoard-ot.
	 */
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
