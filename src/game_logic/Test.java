package game_logic;

import java.util.Scanner;

public class Test {
	
	private void MoveTest()
	{
		System.out.println("Mozgas teszt indul");
		Iron iron = new Iron();
		//Konstruktor guide -> asteroid -> name, material
		Asteroid a1 = new Asteroid("a1",iron);
		Asteroid a2 = new Asteroid("a2",iron);
		Asteroid a3 = new Asteroid("a3",iron);
		
		//Konstruktor guide -> settler -> asteroid
		Settler player = new Settler(a1);
		
		//Bolygok kapcsolatai
		a1.SetNeighbor(a3);
		a1.SetNeighbor(a2);
		
		//a mozgo fgv.
		player.FindDirections();
		
		//kiiras hogy ellenorizheto legyen
		System.out.println("Sikeres Mozgas, a jelenlegi bolygo :" + player.GetCurrentField().Getname() + "\n");
		this.TestMgr();
	}
	
	private void MineTest()
	{
		//peldanyositas
		Iron iron = new Iron();
		Asteroid a1 = new Asteroid("a1", iron);
		Settler player = new Settler(a1);
		//kommunikacio a userrel
		System.out.println("Furas Teszt indul");
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez");
		
		while(this.inputmanager()!=0) {
			player.Drill();
		}
			
		System.out.println("Banyaszas teszt kesz \n");
		
		this.TestMgr();
	}
	
	
	private void Drill_Advanced_Test()
	{
		//Mivel toltott aszteroidat szeretne a tester, csak ez a haromfele viselkedes letezik
		System.out.println("Kerem valasszon tolteleket\n" + "0.vas\n" + "1.Uran\n" + "2.Jeg\n");
		Settler player;
		Asteroid a1;
		switch(this.inputmanager()) {
		//ugy peldanyositunk ahogy a tesztelo igenyli
		case 0:
			Iron iron = new Iron();
			a1 = new Asteroid("a1", iron);
			player = new Settler(a1);
		case 1:	
			Uranium uran = new Uranium();
			a1 = new Asteroid("a1", uran);
			player = new Settler(a1);
		case 2:
			Ice ice = new Ice();
			a1 = new Asteroid("a1", ice);
			player = new Settler(a1);
		default:
			Ice ice2 = new Ice();
			a1 = new Asteroid("a1", ice2);
			player = new Settler(a1);
		}
		
		//kifurjuk manualisan az aszteroidat - kicsit fapados megoldas de csak 10 reteg lehet.
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez");
		
		while(this.inputmanager()!=0) {
			player.Drill();
		}
		System.out.println("Furas teszt kesz");
		
		this.TestMgr();
			
	}
	
	private void CraftRobotTest()
	{
		//TODO
	}
	
	private void CraftTeleportTest()
	{
		//TODO
	}
	
	private int inputmanager(){
		@SuppressWarnings("resource")
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
		
		
	}
	
	
	
	
	//Aktualis tesztek listaja
	private void ListTests() {
		System.out.println("Jelenlegi tesztek:");
		System.out.println("1. Move Test");
		System.out.println("2. Mine Test");
		System.out.println("3. Drill Test");
		System.out.println("4. CraftRobot Test");
		System.out.println("5. Teleport Test");
	}
	//Igy van egy osszefoglalo testmanager amit lehet hivni
	public void TestMgr() {
		//Listazom a teszteket
		this.ListTests();
	//Egyelore ez az egy mod van
		//az teszesetek szam szerint. Boviteni lefele celszeru de mashogy sem lehetetlen
		switch(this.inputmanager()) {
		case 1:
			this.MoveTest();
		case 2:
			this.MineTest();
		case 3:
			this.Drill_Advanced_Test();
		case 4:
			this.CraftRobotTest();
		case 5:
			this.CraftTeleportTest();
		default:
			return;
		}
	}
}
