package game_logic;

import java.util.Scanner;

public class Test {
	
	private void MoveTest()
	{
		Iron iron = new Iron();
		//Konstruktor guide -> asteroid -> name, material
		Asteroid a1 = new Asteroid("a1",iron);
		Asteroid a2= new Asteroid("a2",iron);
		Asteroid a3 = new Asteroid("a3",iron);
		
		//Konstruktor guide -> settler -> asteroid
		Settler player = new Settler(a1);
		
		//Bolygok kapcsolatai
		a1.SetNeighbor(a3);
		a1.SetNeighbor(a2);
		
		//a mozgo fgv.
		player.FindDirections();
		
		//kiiras hogy ellenorizheto legyen
		System.out.println("Sikeres Mozas, a jelenlegi bolygo :");
		System.out.println(player.GetCurrentField().Getname());
		
		this.ListTests();
	}
	
	private void DrillTest()
	{
		//TODO
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
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
	//Egyelore ez az egy mod van
	while(true) {
		//az teszesetek szam szerint. Boviteni lefele celszeru de mashogy sem lehetetlen
		switch(n) {
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
}
