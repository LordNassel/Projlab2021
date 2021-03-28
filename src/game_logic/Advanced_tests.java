package game_logic;

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class Advanced_tests {
//A tesztelesi keretrendszer az elorehaladott teszteknek:
TempGenWorlds currentworlds = new TempGenWorlds();

private void FreeRoam(){
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

//Egy egyszeru rendszer amely beolvas az inputrol. Lehet lesz ennek bovitese
private String inputmanager(){
    Scanner myinput =new Scanner(System.in);
    String n=" ";
    n= myinput.next();
    return n;
}
    private void ListTests() {

        System.out.println(

        "Jelenlegi tesztek:\n" +
        "1. Teszt, inicializalas teszt - inditas \"inittest\"\n"+
        "2. Teszt, freeroam teszt - inditas \"freeroam\" A player move es mine uzemszeru mukodeset teszteli, a mar kifurt bolygo esetet is \n"



        );


    }
    //Igy van egy osszefoglalo testmanager amit lehet hivni
    public void TestMgr() {
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
        }
    }
}

