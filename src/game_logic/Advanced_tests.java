package game_logic;

import game_test_functions.*;
import java.util.Scanner;
import java.util.Vector;
//Az elõrehaladott fileból is beolvasható tesztek osztálya.
public class Advanced_tests {
    //A tesztelesi keretrendszer az elorehaladott teszteknek:
    TempGenWorlds currentworlds = new TempGenWorlds();
    Reader Main_file_Reader = new Reader();
    Vector<String[]> SelectedTestInstructions;

    //A tesztelési funkciók példányosítása
    Comment_function CurrentComment = new Comment_function();
    Stats_manager CurrentStatsManager = new Stats_manager();
    SetNeighbor_funcion CurrentNeighborManager = new SetNeighbor_funcion();
    Drill_function CurrentDrilManager = new Drill_function();
    Mine_function CurrentMineManager = new Mine_function();		
    PutMaterial_function currentPutMaterialManager = new  PutMaterial_function();
    Move_function currentMoveManager = new Move_function();
    PlaceTeleport_function currentPlaceTeleportManager = new PlaceTeleport_function();
    CraftTeleport_function currentCraftTeleportManager = new CraftTeleport_function();
    StartSunstorm_function CurrentStartSunstormManager = new StartSunstorm_function();
    Hide_function currentHideManager = new  Hide_function();
    ExplodeMovable_function CurrentExplodeMovableManager = new  ExplodeMovable_function();
    Die_function CurrentDieManager = new  Die_function();
    CraftRobot_function CurrentCraftRobotManager = new  CraftRobot_function();
    		
    // a game elementek
    Vector<Field> Field_Test_List = new Vector<>();
    Vector<Movable> Movable_Test_List = new Vector<>();
    

    //Ez manageli a tesztek megnyitasat
    public void AdvancedTestMgr(){
        String current_tests[];

        current_tests = this.Main_file_Reader.magicfiles();
        for( int i=0; i<current_tests.length; i++) {
            System.out.println(i+1 + " " + current_tests[i]);
        }
        //itt töltöm be a megfelelõ instruction setet
        SelectedTestInstructions = Main_file_Reader.readtest(current_tests[inputmanager()-1]);
        //ezt itt nem igazán értem miért < mint size -1. Valaki ezt nézze meg pls :D :D
        for(int i = 0; i< SelectedTestInstructions.size(); i++) {
            interpreter(SelectedTestInstructions.get(i));
        }
    }



    //itt példányosítom az aszteroidákat a szövegfileból
    private void AsteroidCreator(String[] command){
        Asteroid temp;
        //Attól függ hogy milyen hosszú, hogy melyik konstruktort hívtam meg
        //Változott az aszteroida konstruktor ! Tudom hogy nem fordul így, de akkor is így jó. artur
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
                temp = new Asteroid(command[1], command[2],Boolean.parseBoolean(command[3]), Integer.parseInt(command[4]));
                Field_Test_List.add(temp);
                break;
        }
    }    
    
    	private void Movable(String[] command) {
    	if(command[1] == "Robot") {
    		Asteroid ar = new Asteroid(command[3]);
    		Robot r = new Robot(command[2],ar);
    		Movable_Test_List.add(r);
    	}
    	if(command[1] == "Settler") {
    		Asteroid as = new Asteroid(command[3]);
    		Settler s = new Settler(command[2],as);
    		Movable_Test_List.add(s);
    	}
    	if(command[1] == "Alien") {
    		Asteroid aa = new Asteroid(command[3]);
    		Alien a = new Alien(command[2], aa); 
    		Movable_Test_List.add(a);
    	}
    }
    

//ez a privát fgv fordítja le a megfelelõ stringeket

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
            case "Drill":
            	this.CurrentDrilManager.Drill(Movable_Test_List, comdline);
            case "Mine":
            	this.CurrentMineManager.Mine(Movable_Test_List, comdline);
            case "PutMaterial":
            	this.currentPutMaterialManager.Putmaterial(Field_Test_List, comdline);
            case "Move": 
            	this.currentMoveManager.Move(Field_Test_List, Movable_Test_List, comdline);
            case "PlaceTeleport":
            	this.currentPlaceTeleportManager.PlaceTeleport(Movable_Test_List, Field_Test_List, comdline);
            case "CraftTeleport":
            	this.currentCraftTeleportManager.CraftTeleport(Movable_Test_List, comdline);
            case "StartSunstorm":
                this.CurrentStartSunstormManager.StartSunstorm(Field_Test_List, comdline);
            case "Hide":
                this.currentHideManager.Hide(Movable_Test_List, comdline);
            case "ExplodeMovable":
                this.CurrentExplodeMovableManager.ExplodeMovable(Movable_Test_List, comdline);
            case "Die":
                this.CurrentDieManager.Die(Movable_Test_List, comdline);
            case "CraftRobot":
                this.CurrentCraftRobotManager.CraftRobot(Movable_Test_List, comdline);
            default:
                System.out.println("ejjoj");
        }

    }

    //Ez a függvény hívja meg a Stats_manager megfelel? függvényeit
    private void Stats_Command_manager(String[] command){
        if(command[1].equals("Asteroid")){
            this.CurrentStatsManager.Asteroid_Stats_Manager(this.Field_Test_List, command);
        }
    }

    //Egy egyszeru rendszer amely beolvas az inputrol. Lehet lesz ennek bovitese
    private int inputmanager() {
        Scanner myinput =new Scanner(System.in);
        int n=0;
        n= myinput.nextInt();
        return n;
    }


}

