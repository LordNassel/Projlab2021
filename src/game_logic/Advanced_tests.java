package game_logic;

import game_test_functions.Comment_function;
import game_test_functions.SetNeighbor_funcion;
import game_test_functions.Stats_manager;

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
    // a game elementek
    Vector<Field> Field_Test_List = new Vector<>();



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

