package game_logic;

import java.util.Scanner;

public class Test {
	//Van egy kulon beolvaso fgv

	//A mozgasteszt

	private void MoveTest()
	{
		System.out.println("Mozgas teszt indul");
		Iron iron = new Iron();
		Iron ittr = new Iron();
		//Konstruktor guide -> asteroid -> name, material
		Asteroid a1 = new Asteroid("a1", "Iron");
		Asteroid a2 = new Asteroid("a2","Iron");
		Asteroid a3 = new Asteroid("a3","Iron");
		
		//Konstruktor guide -> settler -> asteroid
		Settler player = new Settler(a1);
		
		//Bolygok kapcsolatai
		a1.SetNeighbor(a3);
		a1.SetNeighbor(a2);
		
		//a mozgo fgv.
		//player.FindDirections();
		
		//kiiras hogy ellenorizheto legyen
		System.out.println("Sikeres Mozgas, a jelenlegi bolygo: " + player.GetCurrentField().Getname() + "\n");
		
		this.TestMgr();
	}
	//A banyaszasi teszt
	private void MineTest()
    {
        System.out.println("Furas Teszt indul");
        
        Iron iron = new Iron();
        Asteroid a;
        Settler player;
        
        System.out.println("Van nyersanyag az aszteroidában?");
        System.out.println("0. Igen");
        System.out.println("1. Nem");
        
        switch(this.inputmanager())
        {
        case 0:
        	a = new Asteroid("a", "Iron");
        	player = new Settler(a);
        	player.Mine();
    		this.TestMgr();
    		break;
        case 1:
			a = new Asteroid("a");
			player = new Settler(a);
        	player.Mine();
    		this.TestMgr();
    		break;
        }
    }
	
	//a telepes fur
	private void SettlerDrillTest()
	{
		Asteroid a;
		Iron i = new Iron();
		Uranium uranium = new Uranium();
		Settler player;
		
		System.out.println("Milyen vastag a kéreg?");
		System.out.println("0. Nagyobb mint 1");
		System.out.println("1. Pontosan 1");

		switch(this.inputmanager())
		{
		case 0:
			a = new Asteroid("a", "Iron", false, 1);
			player = new Settler(a);
			player.Drill();
	        this.TestMgr();
			break;
		case 1:
			System.out.println("Az aszteroidában urán van?");
			System.out.println("0. Igen");
			System.out.println("1. Nem");
			
			switch(this.inputmanager())
			{
			case 1:
				a = new Asteroid("a", "Iron", false, 1);
				player = new Settler(a);
				a.AcceptPlayer(player);
				player.Drill();
		        this.TestMgr();
				break;
			case 0:
				System.out.println("Napközelben van?");
				System.out.println("0. Igen");
				System.out.println("1. Nem");
				switch(this.inputmanager())
				{
				case 0:
					a = new Asteroid("a", "Uranium", true, 1);
					player = new Settler(a);
					a.AcceptPlayer(player);
					//a.RemoveAllMaterialFromCore();
					a.StoreMaterial(uranium);
					player.Drill();
			        this.TestMgr();
					break;
				case 1:
					a = new Asteroid("a", "Uranium", false, 1);
					player = new Settler(a);
					a.AcceptPlayer(player);
					//a.RemoveAllMaterialFromCore();
					a.StoreMaterial(uranium);
					player.Drill();
			        this.TestMgr();
					break;
				}
			
			}
		}

	}
	//Letrejon egy robot
	private void CraftRobotTest()
	{
		System.out.println("CraftRobotTest Started\n");
		Iron iron = new Iron();
		Coal coal = new Coal();
		Uranium uran = new Uranium();
		Asteroid a = new Asteroid("a", "Iron");
		Settler player = new Settler(a);

		System.out.println("Van elég nyersanyag?\n");
		System.out.println("0. Igen");
		System.out.println("1. Nem");
		
		switch(this.inputmanager())
		{
		case 0:
			player.Store(iron);
			player.Store(coal);
			player.Store(uran);
			player.CraftRobot();
			System.out.println("\n");
			this.TestMgr();
			break;
		case 1:
			player.CraftRobot();
			System.out.println("\n");
			this.TestMgr();
		}

	}
	//Letrejon egy teleport
	private void CraftTeleportTest()
	{
		System.out.println("CraftTeleportTest Started\n");
		Iron iron = new Iron();
		Ice ice = new Ice();
		Uranium uran = new Uranium();
		Asteroid a = new Asteroid("a", "Iron");
		Settler player = new Settler(a);

		System.out.println("Van elég nyersanyag?\n");
		System.out.println("0. Igen");
		System.out.println("1. Nem");
		
		switch(this.inputmanager())
		{
		case 0:
			player.Store(iron);
			player.Store(iron);
			player.Store(ice);
			player.Store(ice);
			player.Store(uran);
			//player.CraftTeleports();
			System.out.println("\n");
			this.TestMgr();
			break;
		case 1:
			//player.CraftTeleports();
			System.out.println("\n");
			this.TestMgr();
		}
	}
	//Elrejtozes ellenorzese
	private void HideTest()
	{
		System.out.println("Bújás teszt\n");
		Iron i = new Iron();
		Asteroid a;
		Settler s;
		System.out.println("\nÜres az aszteroida?\n");
		System.out.println("0. Igen");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			a= new Asteroid("a");
			s= new Settler(a);
			a.AcceptPlayer(s);
			s.Hide();
			this.TestMgr();
			break;
		case 1:
			a= new Asteroid("a","Iron");
			s = new Settler(a);
			a.AcceptPlayer(s);
			s.Hide();
			this.TestMgr();
			break;
		default:
			this.TestMgr();		
		}
	}
	//A jatek veget meghatarozo teszt
	private void WinGameTest() 
	{
		System.out.println("Nyert játszma teszt\n");
		Game g = new Game();
		
		System.out.println("\nMegvannak a nyersanyagok?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		
		switch(this.inputmanager())
		{
		case 0:
			g.Wingame();
			System.out.println("Nyertünk\n");
			this.TestMgr();
			break;
		case 1:
			System.out.println("Még nem Nyertünk\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	//Elvesztettem a jatekot teszt
	private void LoseGameTest() 
	{
		System.out.println("Vesztett játszma teszt\n");
		Game g = new Game();
		System.out.println("Él e még valaki?\n");
		
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		
		switch(this.inputmanager())
		{
		case 0:
			g.Losegame();
			System.out.println("Még valaki életben van\n");

			this.TestMgr();
			break;
		case 1:
			System.out.println("Vesztettünk\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	
	
	private void StartGameTest() 
	{
		System.out.println("Játék kezdése teszt\n");
		
		Game g = new Game();
		g.StartGame();
	}
	
	private void AddMovableTest()
	{
		System.out.println("Felveszünk egy új movable objektumot a rendszerbe teszt\n");
		
		Game g = new Game();
		
		System.out.println("Elöször egy settlert veszünk fel\n");
		Ice i = new Ice();
		Asteroid a = new Asteroid("a1", "Iron");
		Settler s = new Settler(a);
		
		g.AddMovable(s, a);

		System.out.println("Utána pedig egy robotot\n");
		Asteroid a2 = new Asteroid("a2", "Iron");
		Robot r = new Robot(a2);
		
		g.AddMovable(r, a2);
		
	}
	
	private void Place_TeleportTest()
	{
		System.out.println("\"Leteszünk egy teleportot teszt\n");
		
		Map m = new Map();
		m.Place_teleport();
		
	}
	
	private void PutMaterialIntoAsteroidTest()
	{
		System.out.println("Nyersanyag vissza helyezése teszt\n");
		Iron i = new Iron();
		Asteroid a;
		Settler s;
		System.out.println("0. Aszteroida üres vagy nincs tele és olyan nyersanyagot tartalmaz amilyet berakunk");
		System.out.println("1. Az aszteroida teli vagy nincs tele és nem olyan nyersanyagot tartalmaz amilyet berakunk");

		switch(this.inputmanager())
		{
		case 0:
			a= new Asteroid("a");
			s = new Settler(a);
			s.PutMaterial(i);
			this.TestMgr();
			break;
		case 1:
			a= new Asteroid("a", "Iron");
			s = new Settler(a);
			s.PutMaterial(i);
			this.TestMgr();
			break;
		default:
			System.out.println("Érvénytelen input paraméter\n");
			this.TestMgr();
			break;
		
		}
		
	}
	
	private void IceGetsExposedTest()
	{
		
		System.out.println("Vízjég szublimál teszt\n");
		System.out.println("Az aszteroida teljesen megfúrt?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			System.out.println("Az aszteroidának nincs kérge.\n");
			break;
		case 1:
			System.out.println("Az aszteroidának még van kérge.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
		Ice ice = new Ice();
		System.out.println("Az aszteroida éppen napközelben van?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			Asteroid a1 = new Asteroid("a1", "ice", true);
			ice.GetExposed(true, a1);
			this.TestMgr();
			break;
		case 1:
			Asteroid a2 = new Asteroid("a2", "ice", false);
			ice.GetExposed(false, a2);
			System.out.println("A vízjég nem szublimál.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}

	private void SunHitsUraniumTest()
	{
		System.out.println("Radioaktív maggal rendelkezõ aszteroida teszt\n");
		System.out.println("Az aszteroida teljesen megfúrt?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			System.out.println("Az aszteroidának nincs kérge.\n");
			break;
		case 1:
			System.out.println("Az aszteroidának nincs kérge.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
		Uranium uran = new Uranium();
		System.out.println("Az aszteroidának nincs kérge.\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			Asteroid a1 = new Asteroid("a1", "uran", true);
			uran.GetExposed(true, a1);
			System.out.println("Radioaktív maggal rendelkezõ aszteroida felrobbant.\n");
			this.TestMgr();
			break;
		case 1:
			Asteroid a2 = new Asteroid("a2", "uran", false);
			uran.GetExposed(false, a2);
			System.out.println("Radioaktív maggal rendelkezõ aszteroida nem robbant fel\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	
	private void ExplodeTest()
	{
		System.out.println("Aszteroia  felrobban teszt\n");

        Uranium uran = new Uranium();
        Asteroid a1 = new Asteroid("a1", "uran", true);

		System.out.println("Mi van az aszteroidan?\n");
		System.out.println("0. Settler ");
		System.out.println("1. Robot\n");
		switch(this.inputmanager())
		{
		case 0:
			Settler settler = new Settler(a1);
			a1.AcceptPlayer(settler);
			a1.Explode();
			this.TestMgr();
			break;
		case 1:
			Robot robot = new Robot(a1);
			a1.AcceptPlayer(robot);
			Asteroid a2 = new Asteroid("a2", "Uranium");
			System.out.println("Van szomszedos aszteroida?");
			System.out.println("0. Igen");
			System.out.println("1. Nem");
			switch(this.inputmanager())
			{
			case 0:
				a2.SetNeighbor(a1);
				a1.SetNeighbor(a2);
				a1.Explode();
				System.out.println("\n");
				this.TestMgr();
				break;
			case 1:
				a1.Explode();
				System.out.println("\n");
				this.TestMgr();
				break;
			}
		default:
			this.TestMgr();	
		}
	}
	
	private void SunStormStartsTest() {
		
		System.out.println("Napvihar indul teszt\n");
		
		System.out.println("Mennyit mutat a sugárzásmérö?\n");
		System.out.println("0-6 között: 0 ");
		System.out.println("7-10 között: 1\n");
		
		Map m = new Map();
		switch(this.inputmanager())
		{
		case 0:
			System.out.println("még nem következett be napvihar\n");
			this.TestMgr();
			break;
		case 1:
			m.StartSunstorm();
			System.out.println("Napvihar az aszteroidában\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
}

	private void TeleportTravelTest(){
		//Egyszeru peldanyositas
		Teleport T1, T2;
		T1= new Teleport("Egyes teleport");
		T2 = new Teleport("Kettes teleport");
		Iron iron = new Iron();
		Asteroid a1 = new Asteroid("a1", "Iron");
		Settler player = new Settler(a1);
		//Teleportok osszeparositasa
		T1.setPair(T2);
		T2.setPair(T1);
		//elsp utazazas
		System.out.println("Teleport utazasi teszt indul, elerheto teleportok 1. " + T1.Getname() +" 2." + T2.Getname());
		switch(inputmanager()) {
			case 1:
				player.Move(T1);
				System.out.println("Sikeres Mozgas, a jelenlegi hely: " + player.GetCurrentField().Getname() + "\n");
			case 2:
				player.Move(T2);
				System.out.println("Sikeres Mozgas, a jelenlegi hely: " + player.GetCurrentField().Getname() + "\n");
		}
		//tovabbi utazasok
		System.out.println("Szeretnel meg utazni? Barmilyen szam igen, 0 nem");
		while(inputmanager()!=0) {
			System.out.println("Elerheto teleportok 1. " + T1.Getname() + " 2." + T2.Getname());
			switch (inputmanager()) {
				case 1:
					player.Move(T1);
					System.out.println("Sikeres Mozgas, a jelenlegi hely: " + player.GetCurrentField().Getname() + "\n");
				case 2:
					player.Move(T2);
					System.out.println("Sikeres Mozgas, a jelenlegi hely: " + player.GetCurrentField().Getname() + "\n");
			}
			System.out.println("Szeretnel meg utazni? Barmilyen szam igen, 0 nem");
		}
		System.out.println("Vege a teleporttesztnek");
		this.TestMgr();
	}
	//Egyszeru fgv a bevitel kezelesere
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
		System.out.println("5. Craft Teleport Test");
		System.out.println("6. Hide Test");
		System.out.println("7. WinGame Test");
		System.out.println("8. Losegame Test");
		System.out.println("9. Startround Test");
		System.out.println("10. AddMovable Test");
		System.out.println("11. Place Teleport Test");
		System.out.println("12. Put Material Into Asteroid Test");
		System.out.println("13. Ice Gets Exposed Test");
		System.out.println("14. Sun Hits Uranium Test");
		System.out.println("15. Explode Test");
		System.out.println("16. Sun Storm Starts Test");
		System.out.println("17. Teleport Travel Test");
		System.out.println("18. Automatizalt tesztek DEMO!!!");


		
	}



	//Igy van egy osszefoglalo testmanager amit lehet hivni
	public void TestMgr() {
		//Listazom a teszteket
		this.ListTests();
		switch(this.inputmanager()) {
		case 1:
			this.MoveTest();
		case 2:
			this.MineTest();
		case 3:
			this.SettlerDrillTest();
		case 4:
			this.CraftRobotTest();
		case 5:
			this.CraftTeleportTest();
		case 6:
			this.HideTest();
		case 7: 
			this.WinGameTest();
		case 8:
			this.LoseGameTest();
		case 9:
			this.StartGameTest();
		case 10:
			this.AddMovableTest();
		case 11: 
			this.Place_TeleportTest();
		case 12:
			this.PutMaterialIntoAsteroidTest();
		case 13:
			this.IceGetsExposedTest();
		case 14:
			this.SunHitsUraniumTest();
			break;
		case 15:
			this.ExplodeTest();
			break;
		case 16:
			this.SunStormStartsTest();
			break;
		case 17:
			this.TeleportTravelTest();
			break;
		case 18:
		default:
			return;
		}
	}
}
