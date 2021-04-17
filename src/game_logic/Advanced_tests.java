package game_logic;

import java.util.Scanner;
import java.util.Vector;
//Az elõrehaladott fileból is beolvasható tesztek osztálya.
public class Advanced_tests {
//A tesztelesi keretrendszer az elorehaladott teszteknek:
TempGenWorlds currentworlds = new TempGenWorlds();
Reader Main_file_Reader = new Reader();
Vector<String[]> instructionset;


// a game elementek
Vector<Field> Field_Temp_List = new Vector<>();



    //Ez manageli a tesztek megnyitasat
    public void AdvancedTestMgr(){
        String current_tests[];

        current_tests = this.Main_file_Reader.magicfiles();
        for( int i=0; i<current_tests.length; i++) {
            System.out.println(i+1 + " " + current_tests[i]);
        }
        //itt töltöm be a megfelelõ instruction setet
        instructionset = Main_file_Reader.readtest(current_tests[inputmanager()-1]);
        //ezt itt nem igazán értem miért < mint size -1. Valaki ezt nézze meg pls :D :D
        for(int i=0; i<instructionset.size(); i++) {
            interpreter(instructionset.get(i));
            }
        }

    //ez az anyagpeldanyosito dolog -> erre lehet kellene valami jobb megoldás
    private Material MaterialCreator(String command){
     switch (command) {
         case "iron":
             Iron i = new Iron();
             return i;

         case "ice":
             Ice n = new Ice();
             return n;
         case "uranium":
             Uranium u = new Uranium();
             return u;

         case "coal":
             Coal c = new Coal();
             return c;

     }
     Iron nz = new Iron();
     return nz;
}
    //itt példányosítom az aszteroidákat a szövegfileból
    private void AsteroidCreator(String[] command){
    Asteroid temp;
    //Attól függ hogy milyen hosszú, hogy melyik konstruktort hívtam meg
     switch (command.length){
         case 3:
              temp = new Asteroid(command[1], MaterialCreator(command[2]));
             Field_Temp_List.add(temp);
             break;
         case 4:
              temp = new Asteroid(command[1], MaterialCreator(command[2]), Boolean.parseBoolean(command[3]));
             Field_Temp_List.add(temp);
             break;
         case 5:
              temp = new Asteroid(command[1], MaterialCreator(command[2]),Boolean.parseBoolean(command[3]), Integer.parseInt(command[4]));
             Field_Temp_List.add(temp);
             break;
         default:
     }


}

    private void CommentPrinter(String[] command) {
    String temp = "";
    //igen i=1, a commandot magát nem akarom kiírni
        for(int i=1; i<command.length; i++) {
            //erre is biztos van könyvtári fgv de gondolom ugyanezt csinálná -> utánanézni
            temp+=command[i];
        }
        System.out.println(temp);

    }


    //Ez a függvény írja ki egy adott objektum statisztikáit
    private void Stats_Command_manager(String[] command){
        if(command[1].equals("Asteroid")){
            int n=0;

            while(Field_Temp_List.get(n).Getname().equals(command[2])== false)
                n += 1;
            //itt írom ki a nevét a bolygónak
                System.out.println(Field_Temp_List.get(n).Getname());
        }
    }

//ez a privát fgv fordítja le a megfelelõ stringeket

    private void interpreter(String[] comdline){
        switch (comdline[0]){
            case "Comment":
                this.CommentPrinter(comdline);
                break;
            case "Asteroid":
                this.AsteroidCreator(comdline);
                break;
            case "Stats":
                this.Stats_Command_manager(comdline);
                break;
            default:
                System.out.println("ejjoj");
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

