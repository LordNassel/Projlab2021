//Sorry for the english, but eclipse throws all sorts of errors with the HU special characters
package game_logic;
import game_logic.Asteroid;
import view.GameView;
import view.MenuView;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Main{
	static int num =4;
	public void setNum(int szam) {num = szam;}
	public static void main(String[] args) throws IOException {
		System.out.println("Every man's sky");

		//tesztpark peldanyositasa
		//Advanced_tests currenttests = new Advanced_tests();
		//Az ideiglenes világ példányosítása
		//TempGenWorlds temp = new TempGenWorlds();
		//Game gm = temp.Generateworlds(num);
		//Hagyományos tesztek
		//Test Normaltest = new Test();
		/*System.out.println("Mit szeretnél csinálni?  2-es elorehaladott tesztek, 3-as uj jatek inditasa");
		switch (inputmanager()){
			case 2:
				currenttests.AdvancedTestMgr();
				break;
			case 3:
				temp.Generateworlds(1);
				break;
			default:
				break;
		}
		Scanner input = new Scanner(System.in);
		String in = input.next();*/
		//MenuView menu = new MenuView();
		///asteroids.get(1).Explode();
		GameView game = new GameView();
		game.setVisible(true);
		//gm.StartGame();
		//menu.setVisible(true);
	}

	private static int inputmanager() {
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
	}



}
