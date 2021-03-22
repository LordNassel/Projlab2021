/**package game_logic;
package game_test;

import java.util.*;

public class Test {
	
	private void MoveTest()
	{
		System.out.println("Mozgas teszt indul\n");
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
		System.out.println("Sikeres Mozgas, a jelenlegi bolygo :\n");
		System.out.println(player.GetCurrentField().Getname());
		
		this.TestMgr();
	}
	
	private void DrillTest()
	{
		Iron iron = new Iron();
		Asteroid a1 = new Asteroid("a1", iron);
		Settler player = new Settler(a1);
		
		System.out.println("Furas Teszt indul\n");
		System.out.println("Nyomj bamilyen szamot a furashoz, 0-at a befejezeshez\n");
		
		while(this.inputmanager()!=0) {
			player.Drill();
		}
		
		System.out.println("Furas teszt kesz\n");
		this.TestMgr();
	}


        private void MineTest()
        {
            Material minedMaterial = new Material();
            Uranium uranium = new Uranium();
            Asteroid asteroid1 = new Asteroid("asteroid1", uranium);
            Settler player = new Settler(settler1);

            System.out.println("MineTest Started\n");
            System.out.println("Press AnyButton, 0 to finish\n");

            while(this.inputmanager()!=0) {
                minedMaterial = asteroid1.GetMined();
                /**
                 * If inventory is not full already
                 */
                /**player.InventoryMain.add(Material minedMaterial);
            }

            System.out.println("MineTest Finished\n");
            this.TestMgr();
        }

    private void CraftRobotTest()
    {
        protected int numIron = 0, numUranium = 0, numIce = 0, numCoal = 0;
        Settler player = new Settler(settler1);

        System.out.println("CraftRobotTest Started\n");
        System.out.println("Press R to Craft Robot\n");

        while(this.inputmanager() == R) {
            for (int i=0; i < player.InventoryMain.size(); i++){
                if(getType(player.InventoryMain.i) == getType(Iron)){
                    numIron++;
                }
                if(getType(player.InventoryMain.i) == getType(Uranium)){
                    numUranium++;
                }
                if(getType(player.InventoryMain.i) == getType(Ice)){
                    numIce++;
                }
                if(getType(player.InventoryMain.i) == getType(Coal)){
                    numCoal++;
                }
            }

            if(numIron = 1 && numCoal == 1 && numUranium == 1){
                System.out.println("CraftRobotTest Finished\n");
            }
        }
        this.TestMgr();
    }

    private void CraftTeleportTest()
    {
        protected int numIron = 0, numUranium = 0, numIce = 0, numCoal = 0;
        Settler player = new Settler(settler1);

        System.out.println("CraftTeleportTest Started\n");
        System.out.println("Press T to Craft Teleport\n");

        while(this.inputmanager() == R) {
            for (int i=0; i < player.InventoryMain.size(); i++){
                if(getType(player.InventoryMain.i) == getType(Iron)){
                    numIron++;
                }
                if(getType(player.InventoryMain.i) == getType(Uranium)){
                    numUranium++;
                }
                if(getType(player.InventoryMain.i) == getType(Ice)){
                    numIce++;
                }
                if(getType(player.InventoryMain.i) == getType(Coal)){
                    numCoal++;
                }
            }

            if(numIron = 2 && numIce == 2 && numUranium == 1){
                System.out.println("CraftTeleportTest Finished\n");
            }
        }
        this.TestMgr();
    }
	
	private int inputmanager(){
		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return n;
		
		
	}
	
	
	
	
	//Aktualis tesztek listaja
	private void ListTests() {
		System.out.println("Jelenlegi tesztek:\n");
		System.out.println("1. Move Test\n");
		System.out.println("2. Drill Test\n");
		System.out.println("3. Mine Test\n");
		System.out.println("4. CraftRobot Test\n");
		System.out.println("5. Teleport Test\n");
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
}*/
