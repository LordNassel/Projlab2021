package game_logic;

import java.util.Scanner;
import java.util.Vector;
//Az el�rehaladott fileb�l is beolvashat� tesztek oszt�lya.
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
        //itt t�lt�m be a megfelel� instruction setet
        instructionset = Main_file_Reader.readtest(current_tests[inputmanager()-1]);
        //ezt itt nem igaz�n �rtem mi�rt < mint size -1. Valaki ezt n�zze meg pls :D :D
        for(int i=0; i<instructionset.size(); i++) {
            interpreter(instructionset.get(i));
            }
        }


}
    //itt p�ld�nyos�tom az aszteroid�kat a sz�vegfileb�l
    private void AsteroidCreator(String[] command){
    Asteroid temp;
    //Att�l f�gg hogy milyen hossz�, hogy melyik konstruktort h�vtam meg
    //V�ltozott az aszteroida konstruktor ! Tudom hogy nem fordul �gy, de akkor is �gy j�. artur
     switch (command.length){
         case 3:
             temp = new Asteroid(command[1], command[2]);
             Field_Temp_List.add(temp);
             break;
         case 4:
             temp = new Asteroid(command[1], command[2], Boolean.parseBoolean(command[3]));
             Field_Temp_List.add(temp);
             break;
         case 5:
             temp = new Asteroid(command[1], command[2],Boolean.parseBoolean(command[3]), Integer.parseInt(command[4]));
             Field_Temp_List.add(temp);
             break;

     }


}

    private void CommentPrinter(String[] command) {
    String temp = "";
    //igen i=1, a commandot mag�t nem akarom ki�rni
        for(int i=1; i<command.length; i++) {
            //erre is biztos van k�nyvt�ri fgv de gondolom ugyanezt csin�ln� -> ut�nan�zni
            temp+=command[i];
        }
        System.out.println(temp);

    }


    //Ez a f�ggv�ny �rja ki egy adott objektum statisztik�it
    private void Stats_Command_manager(String[] command){
        if(command[1].equals("Asteroid")){
            int n=0;

            while(Field_Temp_List.get(n).Getname().equals(command[2])== false)
                n += 1;
            //itt �rom ki a nev�t a bolyg�nak
                System.out.println(Field_Temp_List.get(n).Getname());
        }
    }

//ez a priv�t fgv ford�tja le a megfelel� stringeket

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

