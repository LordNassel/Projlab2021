//Sorry for the english, but eclipse throws all sorts of errors with the HU special characters
package game_logic;
import game_logic.Asteroid;
import graphics.TestClass_For_Getting_Our_Bearings;

import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		System.out.println("Every man's sky");
		//tesztpark peldanyositasa
		Advanced_tests currenttests = new Advanced_tests();
		//Az ideiglenes világ példányosítása
		TempGenWorlds temp = new TempGenWorlds();
		//Hagyományos tesztek
		//Test Normaltest = new Test();

		TestClass_For_Getting_Our_Bearings test = new TestClass_For_Getting_Our_Bearings();
		test.thethingtocall();

		System.out.println("Mit szeretnél csinálni?  2-es elorehaladott tesztek, 3-as uj jatek inditasa");
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
		String in = input.next();
	}

	private static int inputmanager() {
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
	}


}
