package game_logic;

import game_test_functions.*;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

/**
 * Az előrehaladott, fileból is beolvasható tesztek osztálya.
 */
public class Advanced_tests {
    //A tesztelesi keretrendszer az elorehaladott teszteknek:
    TempGenWorlds currentworlds = new TempGenWorlds();
    Reader Main_file_Reader = new Reader();
    Vector<String[]> SelectedTestInstructions;

    //A tesztel�si funkci�k p�ld�nyos�t�sa
    Comment_function CurrentComment = new Comment_function();
    Stats_manager CurrentStatsManager = new Stats_manager();
    SetNeighbor_funcion CurrentNeighborManager = new SetNeighbor_funcion();
    Drill_function CurrentDrillManager = new Drill_function();
    Mine_function CurrentMineManager = new Mine_function();		
    PutMaterial_function CurrentPutMaterialManager = new  PutMaterial_function();
    Move_function CurrentMoveManager = new Move_function();
    PlaceTeleport_function CurrentPlaceTeleportManager = new PlaceTeleport_function();
    CraftTeleport_function CurrentCraftTeleportManager = new CraftTeleport_function();
    StartSunstorm_function CurrentStartSunstormManager = new StartSunstorm_function();
    Hide_function currentHideManager = new  Hide_function();
    ExplodeMovable_function CurrentExplodeMovableManager = new  ExplodeMovable_function();
    Die_function CurrentDieManager = new  Die_function();
    CraftRobot_function CurrentCraftRobotManager = new  CraftRobot_function();
    GiveItem_function CurrentGiveitemManager = new GiveItem_function();
    CompleteMaterial_function CurrentCompleteMaterialManager = new CompleteMaterial_function();
    		
    // a game elementek
    Vector<Field> Field_Test_List = new Vector<>();
    Vector<Movable> Movable_Test_List = new Vector<>();
    

    /**
     * Manageli a tesztek megnyitasat.
     */
    public void AdvancedTestMgr(){
        String current_tests[] = null;

        current_tests = this.Main_file_Reader.magicfiles();
        for( int i=0; i<current_tests.length; i++) {
            System.out.println(i+1 + " " + current_tests[i]);
        }

        //itt t�lt�m be a megfelel� instruction setet
        SelectedTestInstructions = Main_file_Reader.readtest(current_tests[inputmanager()-1]);
        //ezt itt nem igaz�n �rtem mi�rt < mint size -1. Valaki ezt n�zze meg pls :D :D
        for(int i = 0; i< SelectedTestInstructions.size(); i++) {
            interpreter(SelectedTestInstructions.get(i));
        }
    }

    /**
     * Aszteroidékat peldanyosit.
     */
    private void AsteroidCreator(String[] command){
        Asteroid temp;
        //Att�l f�gg hogy milyen hossz�, hogy melyik konstruktort h�vtam meg
        //V�ltozott az aszteroida konstruktor ! Tudom hogy nem fordul �gy, de akkor is �gy j�. artur
        switch (command.length){
            case 3:
                temp = new Asteroid(command[1], command[2]);
                Field_Test_List.add(temp);
                break;
            case 4:
                temp = new Asteroid(command[1], command[2], Boolean.parseBoolean(command[3]));
                Field_Test_List.add(temp);
                break;
            case 5:
                //temp = new Asteroid(command[1], command[2], Boolean.parseBoolean(command[3]), Integer.parseInt(command[4]));
                temp = new Asteroid(command[1], command[2], Boolean.parseBoolean(command[3]), Integer.parseInt(command[4]));

                Field_Test_List.add(temp);
                break;
        }
    } 

    /**
     * Movable-t peldanyosit.
     */
    private void Movable(String[] command) {
    	Asteroid as = new Asteroid("temp");
    	for(int y=0; y<Field_Test_List.size();y++)
    	{
    		if(Field_Test_List.get(y).Getname().equals(command[3]))
    			as=((Asteroid)Field_Test_List.get(y));
    	}
    	if(command[1].equals("Robot")) {
    		//Asteroid ar = new Asteroid(command[3]);
    		Robot r = new Robot(command[2],as);
    		Movable_Test_List.add(r);
    	}
    	else if(command[1].equals("Settler")) {
    		//Asteroid as = new Asteroid(command[3]);
    		Settler s = new Settler(command[2],as);
    		Movable_Test_List.add(s);
    	}
    	else if(command[1].equals("Alien")) {
    		//Asteroid aa = new Asteroid(command[3]);
    		Alien a = new Alien(command[2], as); 
    		Movable_Test_List.add(a);
    	}
    }

    /**
     *  Ez a függvény fordítja le a megfelelő stringeket.
     */
    private void interpreter(String[] comdline){
        switch (comdline[0]){
            case "Comment":
                this.CurrentComment.CommentPrinter(comdline);
                break;
            case "Asteroid":
                this.AsteroidCreator(comdline);
                break;
            case "Stats":
                this.Stats_Command_manager(comdline);
                break;
            case "SetNeighbor":
              this.Field_Test_List =  this.CurrentNeighborManager.TeenyTinyNeighboursetty(Field_Test_List, comdline);
                break;
            case "Movable":
            	this.Movable(comdline);
            	break;
            case "Drill":
            	this.CurrentDrillManager.Drill(Movable_Test_List, comdline);
            	break;
            case "Mine":
            	this.CurrentMineManager.Mine(Movable_Test_List, comdline);
            	break;
            case "PutMaterial":
            	this.CurrentPutMaterialManager.Putmaterial(Field_Test_List, comdline);
            	break;
            case "Move": 
            	this.CurrentMoveManager.Move(Field_Test_List, Movable_Test_List, comdline);
            	break;
            case "PlaceTeleport":
            	this.CurrentPlaceTeleportManager.PlaceTeleport(Movable_Test_List, Field_Test_List, comdline);
            	break;
            case "CraftTeleport":
            	this.CurrentCraftTeleportManager.CraftTeleport(Movable_Test_List, comdline);
            	break;
            case "StartSunstorm":
                this.CurrentStartSunstormManager.StartSunstorm(Field_Test_List, comdline);
                break;
            case "Hide":
                this.currentHideManager.Hide(Movable_Test_List, comdline);
                break;
            case "ExplodeMovable":
                this.CurrentExplodeMovableManager.ExplodeMovable(Movable_Test_List, comdline);
                break;
            case "Die":
                this.CurrentDieManager.Die(Movable_Test_List, comdline);
                break;
            case "CraftRobot":
                this.CurrentCraftRobotManager.CraftRobot(Movable_Test_List, comdline);
                break;
            case "GiveItem":
            	this.CurrentGiveitemManager.GiveItem(Movable_Test_List, comdline);
            	break;
            case "CompleteMaterial":
            	this.CurrentCompleteMaterialManager.CompleteMaterial(Field_Test_List, comdline);
            	break;
            case "List":
            	this.List_Universe();
            	break;
            default:
                System.out.println("ejjoj");
                break;
        }

    }

    /**
     *  Ez a függvény hívja meg a Stats_manager megfelelő függvényit.
     */
    private void Stats_Command_manager(String[] command){
        if(command[1].equals("Asteroid")){
            this.CurrentStatsManager.Asteroid_Stats_Manager(this.Field_Test_List, command);
        }
        if(command[1].equals("Alien")){
            this.CurrentStatsManager.Alien_StatsManager(this.Movable_Test_List, command);
        }
        if(command[1].equals("Robot")){
            this.CurrentStatsManager.Robot_StatsManager(this.Movable_Test_List, command);
        }
        if(command[1].equals("Settler")){
            this.CurrentStatsManager.Settler_StatsManager(this.Movable_Test_List, command);
        }
        if(command[1].equals("Teleport")){
            this.CurrentStatsManager.Teleport_StatsManager(this.Field_Test_List, command);
        }
    }

    /**
     *  Listázó függvény.
     */
    private void List_Universe()
    {
    	for(int i=0; i<Field_Test_List.size(); i++)
    	{
    		if(Field_Test_List.get(i).getClass().equals(Asteroid.class))
    		{
    			//MINDIG aszteroida, a kommand definiálja
    			Asteroid A1 = (Asteroid)Field_Test_List.get(i);
                //itt írom ki a nevét a bolygónak
                System.out.println("Name: " + A1.Getname());
                //napkozeli e
                System.out.println("IsSunSide: " + String.valueOf(A1.getSunSide()));
                //vastagsaga
                System.out.println("Thickness:" +  A1.getThickness());
                //A szomszédok kiírása
                System.out.println("Neighbours: ");
                Vector<Field> temp = A1.FindNeighbor();
                if(temp!= null) {
                    for (int y = 0; y < temp.size(); y++)
                        System.out.println(temp.get(y).Getname());
                }
                //Materials benne
                System.out.println("Materials: ");
                List<Material> mats = A1.getMats();
                for(int x=0; x<A1.getMats().size(); x++){
                    if (A1.getMats().get(x).getname() == "Uranium" )
                        System.out.println("Uranium, ");
                    else if (A1.getMats().get(x).getname() == "Ice")
                        System.out.println("Ice, ");
                    else if (A1.getMats().get(x).getname() == "Coal")
                        System.out.println("Coal, ");
                    else if (A1.getMats().get(x).getname() == "Iron")
                        System.out.println("Iron, ");
                    else
                    	System.out.println("Uranium, ");
                    	
                    }
    		}
    	}
    }

    /**
     *  Egy egyszeru rendszer amely beolvas az inputrol.
     */
    private int inputmanager() {
        Scanner myinput =new Scanner(System.in);
        int n=0;
        n= myinput.nextInt();
        return n;
    }
}