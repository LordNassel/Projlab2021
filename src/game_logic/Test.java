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
		System.out.println("Sikeres Mozgas, a jelenlegi bolygo :");
		System.out.println(player.GetCurrentField().Getname());
		
		this.TestMgr();
	}
	
	private void DrillTest()
	{
		Iron iron = new Iron();
		Asteroid a1 = new Asteroid("a1", iron);
		Settler player = new Settler(a1);
		
		System.out.println("Furas Teszt indul");
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez");
		
		while(this.inputmanager()!=0) {
			player.Drill();
		}
		
		System.out.println("Furas teszt kesz");
		this.TestMgr();
	}
	
	
	private void MineTest()
	{
		//TODO
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
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
		
		
	}
	
	
	
	
	//Aktualis tesztek listaja
	private void ListTests() {
		System.out.println("Jelenlegi tesztek:");
		System.out.println("1. Move Test");
		System.out.println("2. Drill Test");
		System.out.println("3. Mine Test");
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
			this.DrillTest();
		case 3:
			this.MineTest();
		case 4:
			this.CraftRobotTest();
		case 5:
			this.CraftTeleportTest();
		default:
			return;
		}
	}
}
