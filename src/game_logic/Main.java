//Sorry for the english, but eclipse throws all sorts of errors with the HU special characters
package game_logic;
import game_logic.Asteroid;

import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		System.out.println("Every man's sky");
		//tesztpark peldanyositasa
		Advanced_tests currenttests = new Advanced_tests();
		//Az ideiglenes vil�g p�ld�nyos�t�sa
		TempGenWorlds temp = new TempGenWorlds();
		//Hagyom�nyos tesztek
		//Test Normaltest = new Test();
		System.out.println("Mit szeretn�l csin�lni?  2-es elorehaladott tesztek, 3-as uj jatek inditasa");
		switch (inputmanager()){
			case 2:
				currenttests.AdvancedTestMgr();
				break;
			case 3:
				temp.Generateworlds(1);
				break;
		}
	}

	private static int inputmanager() {
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
	}


}
