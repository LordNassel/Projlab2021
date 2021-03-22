package game_logic;

import java.util.Scanner;

public class Test {
	
	private void MoveTest()
	{
		System.out.println("Mozgas teszt indul");
		Iron iron = new Iron();
		Iron ittr = new Iron();
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
		System.out.println("Sikeres Mozgas, a jelenlegi bolygo: " + player.GetCurrentField().Getname() + "\n");
		
		this.TestMgr();
	}
	private void MineTest()
    {
        System.out.println("Furas Teszt indul");
        
        Iron iron = new Iron();
        Asteroid a = new Asteroid("a", iron);
        Settler player = new Settler(a);
        
        System.out.println("Van nyersanyag az aszteroid�ban?");
        System.out.println("0. Igen");
        System.out.println("1. Nem");
        
        switch(this.inputmanager())
        {
        case 0:
        	player.Mine();
    		this.TestMgr();
    		break;
        case 1:
        	a.RemoveAllMaterialFromCore();
        	player.Mine();
    		this.TestMgr();
    		break;
        }
		
		/*Iron iron = new Iron();
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

        this.TestMgr();*/
    }
	
	
	private void DrillTest()
	{
		Asteroid a;
		Iron i = new Iron();
		Settler player;
		
		System.out.println("Milyen vastag a k�reg?");
		System.out.println("0. Pontosan 1");
		System.out.println("1. Nincs m�r k�reg");

		switch(this.inputmanager())
		{
		case 0:
			a = new Asteroid("a", i, false, 1);
			player = new Settler(a);
			player.Drill();
	        this.TestMgr();
			break;
		case 1:
			a = new Asteroid("a", i , false, 0);
			player = new Settler(a);
			player.Drill();
	        this.TestMgr();
			break;
		}

		//Mivel toltott aszteroidat szeretne a tester, csak ez a haromfele viselkedes letezik
		/*System.out.println("Kerem valasszon tolteleket\n" + "0.vas\n" + "1.Uran\n" + "2.Jeg\n");
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
		
		this.TestMgr();*/
			

		//TODO

	}

	private void CraftRobotTest()
	{
		System.out.println("CraftRobotTest Started\n");
		Iron iron = new Iron();
		Coal coal = new Coal();
		Uranium uran = new Uranium();
		Asteroid a = new Asteroid("a", iron);
		Settler player = new Settler(a);

		System.out.println("Van el�g nyersanyag?\n");
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
	
	private void CraftTeleportTest()
	{
		System.out.println("CraftTeleportTest Started\n");
		Iron iron = new Iron();
		Ice ice = new Ice();
		Uranium uran = new Uranium();
		Asteroid a = new Asteroid("a", iron);
		Settler player = new Settler(a);

		System.out.println("Van el�g nyersanyag?\n");
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
			player.CraftTeleports();
			System.out.println("\n");
			this.TestMgr();
			break;
		case 1:
			player.CraftTeleports();
			System.out.println("\n");
			this.TestMgr();
		}
	}
	
	private void HideTest()
	{
		System.out.println("B�j�s teszt\n");
		Iron i = new Iron();
		Asteroid a = new Asteroid("a",i);
		Settler s = new Settler(a);
		a.AcceptPlayer(s);
		System.out.println("\n�res az aszteroida?\n");
		System.out.println("0. Igen");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			a.RemoveAllMaterialFromCore();
			s.Hide();
			this.TestMgr();
			break;
		case 1:
			s.Hide();
			this.TestMgr();
			break;
		default:
			this.TestMgr();		
		}
	}
	
	private void WinGameTest() 
	{
		System.out.println("Nyert j�tszma teszt\n");
		Game g = new Game();
		
		System.out.println("\nMegvannak a nyersanyagok?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		
		switch(this.inputmanager())
		{
		case 0:
			g.Wingame();
			System.out.println("Nyert�nk\n");
			this.TestMgr();
			break;
		case 1:
			System.out.println("M�g nem Nyert�nk\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	
	private void LoseGameTest() 
	{
		System.out.println("Vesztett j�tszma teszt\n");
		Game g = new Game();
		System.out.println("�l e m�g valaki?\n");
		
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		
		switch(this.inputmanager())
		{
		case 0:
			g.Losegame();
			System.out.println("Vesztett�nk\n");
			this.TestMgr();
			break;
		case 1:
			System.out.println("M�g valaki �letben van\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	
	
	private void StartGameTest() 
	{
		System.out.println("J�t�k kezd�se teszt\n");
		
		Game g = new Game();
		g.StartGame();
	}
	
	private void AddMovableTest()
	{
		System.out.println("Felvesz�nk egy �j movable objektumot a rendszerbe teszt\n");
		
		Game g = new Game();
		
		System.out.println("El�sz�r egy settlert vesz�nk fel\n");
		Ice i = new Ice();
		Asteroid a = new Asteroid("a1", i);
		Settler s = new Settler(a);
		
		g.AddMovable(s);

		System.out.println("Ut�na pedig egy robotot\n");
		Asteroid a2 = new Asteroid("a2", i);
		Robot r = new Robot(a2);
		
		g.AddMovable(r);
		
	}
	
	private void Place_TeleportTest()
	{
		System.out.println("\"Letesz�nk egy teleportot teszt\n");
		
		Map m = new Map();
		m.Place_teleport();
		
	}
	
	private void PutMaterialIntoAsteroidTest()
	{
		System.out.println("Nyersanyag vissza helyez�se teszt\n");
		Iron i = new Iron();
		Asteroid a = new Asteroid("a", i);
		Settler s = new Settler(a);
		System.out.println("0. Aszteroida �res vagy nincs tele �s olyan nyersanyagot tartalmaz amilyet berakunk");
		System.out.println("1. Az aszteroida teli vagy nincs tele �s nem olyan nyersanyagot tartalmaz amilyet berakunk");

		switch(this.inputmanager())
		{
		case 0:
			a.RemoveMaterialFromCore();
			s.PutMaterial(i);
			this.TestMgr();
			break;
		case 1:
			s.PutMaterial(i);
			this.TestMgr();
			break;
		default:
			System.out.println("�rv�nytelen input param�ter\n");
			this.TestMgr();
			break;
		
		}
		
	}
	
	private void IceGetsExposedTest()
	{
		
		System.out.println("V�zj�g szublim�l teszt\n");
		System.out.println("Az aszteroida teljesen megf�rt?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			System.out.println("Az aszteroid�nak nincs k�rge.\n");
			break;
		case 1:
			System.out.println("Az aszteroid�nak m�g van k�rge.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
		Ice ice = new Ice();
		System.out.println("Az aszteroida �ppen napk�zelben van?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			Asteroid a1 = new Asteroid("a1", ice, true);
			ice.GetExposed(true, a1);
			this.TestMgr();
			break;
		case 1:
			Asteroid a2 = new Asteroid("a2", ice, false);
			ice.GetExposed(false, a2);
			System.out.println("A v�zj�g nem szublim�l.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}

	private void SunHitsUraniumTest()
	{
		System.out.println("Radioakt�v maggal rendelkez� aszteroida teszt\n");
		System.out.println("Az aszteroida teljesen megf�rt?\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			System.out.println("Az aszteroid�nak nincs k�rge.\n");
			break;
		case 1:
			System.out.println("Az aszteroid�nak nincs k�rge.\n");
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
		Uranium uran = new Uranium();
		System.out.println("Az aszteroid�nak nincs k�rge.\n");
		System.out.println("0. Igen ");
		System.out.println("1. Nem\n");
		switch(this.inputmanager())
		{
		case 0:
			Asteroid a1 = new Asteroid("a1", uran, true);
			uran.GetExposed(true, a1);
			System.out.println("Radioakt�v maggal rendelkez� aszteroida felrobbant.\n");
			this.TestMgr();
			break;
		case 1:
			Asteroid a2 = new Asteroid("a2", uran, false);
			uran.GetExposed(false, a2);
			System.out.println("Radioakt�v maggal rendelkez� aszteroida nem robbant fel\n");
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
        Asteroid a1 = new Asteroid("a1", uran, true);

		System.out.println("Mi van az aszteroidan?\n");
		System.out.println("0. Settler ");
		System.out.println("1. Robot\n");
		switch(this.inputmanager())
		{
		case 0:
			Settler settler = new Settler(a1);
			a1.Explode();
			this.TestMgr();
			break;
		case 1:
			Robot robot = new Robot(a1);
			a1.Explode();
			this.TestMgr();
			break;
		default:
			this.TestMgr();	
		}
	}
	
	private void SunStormStartsTest() {
		//Todo
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
			this.DrillTest();
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
		case 15:
			this.ExplodeTest();
		case 16:
			this.SunStormStartsTest();
		default:
			return;
		}
	}
}
