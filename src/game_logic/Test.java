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
		Asteroid a1 = new Asteroid("a1", iron, false, 1);
		Settler player = new Settler(a1);
		
		System.out.println("Furas Teszt indul");
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez");
		
		if(this.inputmanager()!=0) {
			player.Drill();
		}
		
		
		System.out.println("Nyomj barmilyen szamot banyaszashoz, 0-t a befejezeshez");
		if(this.inputmanager()!=0) {
			player.Mine();
		}
		
		System.out.println("Banyaszas teszt kesz");
		
		System.out.println("Furas teszt kesz");

		this.TestMgr();
	}
	
	
	private void MineTest()
	{

		//Mivel toltott aszteroidat szeretne a tester, csak ez a haromfele viselkedes letezik
		System.out.println("Kerem valasszon tolteleket\n" + "0.vas\n" + "1.Uran\n" + "2.Jeg\n");
		Settler player;
		Asteroid a1;
		int n=0;
		n = this.inputmanager();
		//ugy peldanyositunk ahogy a tesztelo igenyli
		if (n==0){
			Iron iron = new Iron();
			a1 = new Asteroid("a1v", iron, true, 1);
			player = new Settler(a1);
		}
		else if(n==1) {	
			Uranium uran = new Uranium();
			a1 = new Asteroid("a1u", uran, true, 1);
			player = new Settler(a1);
		}
		else if(n==2) {
			Ice ice = new Ice();
			a1 = new Asteroid("a1j", ice, true, 1);
			player = new Settler(a1);
		}
		else{
			Ice ice = new Ice();
			a1 = new Asteroid("a1d", ice, true, 1);
			player = new Settler(a1);
		}
		//kifurjuk manualisan az aszteroidat - kicsit fapados megoldas de csak 10 reteg lehet.
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez");
		
		while(this.inputmanager()!=0) {
			player.Drill();
			
		}
		System.out.println("Furas teszt kesz");
		
		this.TestMgr();
			

		//TODO

	}
	
	private void CraftRobotTest()
	{
		//todo
	}
	
	private void CraftTeleportTest()
	{
		//TODO
	}
	
	private void HideTest()
	{
		System.out.println("Bújás teszt\n");
		Iron i = new Iron();
		Asteroid a = new Asteroid("a",i);
		Settler s = new Settler(a);
		System.out.println("\nÜres az aszteroida?\n");
		System.out.println("0. Igen");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			s.Hide();
			System.out.println("Az elbújás megtörtént\n");
			this.TestMgr();
			break;
		case 1:
			System.out.println("Az aszteroida nem üres, az elbújás sikertelen\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();		
		}
		

		
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
		System.out.println("2. Mine Test");
		System.out.println("3. Drill Test");
		System.out.println("4. CraftRobot Test");
		System.out.println("5. Teleport Test");
		System.out.println("6. Hide Test");
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
		case 6:
			this.HideTest();
		default:
			return;
		}
	}
}
