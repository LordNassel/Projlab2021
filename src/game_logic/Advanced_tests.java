package game_logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;
//Az el�rehaladott fileb�l is beolvashat� tesztek oszt�lya.
public class Advanced_tests {
//A tesztelesi keretrendszer az elorehaladott teszteknek:
TempGenWorlds currentworlds = new TempGenWorlds();
Reader Main_file_Reader = new Reader();
Vector<String[]> instructionset;

/*private void FreeRoam(){
    //A freeroam teszt dolgai
    //peldanyositas
    Vector<Field> Mezok = currentworlds.Generateworlds(1);
    //tudom hogy asteroid, mindig is tudni fogom. En generalom a terkepet.
    Asteroid A = (Asteroid) Mezok.get(0);
    Settler Player = new Settler(A);
    System.out.println("Ez egy szabadmozgasi teszt. Szabadon lehet mozogni ( :D ). A \"move\" parancs es a \"drill\" el de az minden mennyisegben. \"exit\" paranncsal lehet kilepni");
    //a parancskezelo resz.
    String s ;
    while((s = inputmanager())!= "exit") {
        switch (s){
            case "move":
                Player.FindDirections();
                //kiiras hogy ellenorizheto legyen
                System.out.println("Sikeres Mozgas, a jelenlegi bolygo: " + Player.GetCurrentField().Getname() + "\n");
                break;
            case "drill":
                Player.Drill();
                break;
            default:
                break;
        }
    }
    this.TestMgr();
}

private void InitTest(){
    Vector<Field> temp = currentworlds.Generateworlds(1);
    System.out.println("Listazom a bolygokat es szomszedjaikat");
    for(int i=0; i<temp.size(); i++){
        System.out.println("Bolygo neve: " + temp.get(i).Getname() + "\nSzomszedai:");
                for(int n=0; n<temp.get(i).FindNeighbor().size(); n++){
                    System.out.println(temp.get(i).FindNeighbor().get(n).Getname() );
                }
    }
    this.TestMgr();
}

private void SimpleRobot(){

}

 */
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
        for(int i=0; i<instructionset.size()-1; i++) {
            interpreter(instructionset.get(i));
            }
        }

    

    private void commentprinter(String[] command) {
    String temp = "";
    //igen i=1, a commandot mag�t nem akarom ki�rni
        for(int i=1; i<command.length; i++) {
            //erre is biztos van k�nyvt�ri fgv de gondolom ugyanezt csin�ln� -> ut�nan�zni
            temp+=command[i];
        }
        System.out.println(temp);

    }


    //ez a priv�t fgv ford�tja le a megfelel� stringeket

    private void interpreter(String[] comdline){
        switch (comdline[0]){
            case "comment":
                this.commentprinter(comdline);
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

    //Igy van egy osszefoglalo testmanager amit lehet hivni
  /*  public void TestMgr() {
        //Listazom a teszteket
        this.ListTests();
        String s;
        switch (this.inputmanager()) {
            case "inittest":
                this.InitTest();
                break;
            case "freeroam":
                this.FreeRoam();
                break;
            case "robotadv":
                this.SimpleRobot();
        }
    }*/
}

