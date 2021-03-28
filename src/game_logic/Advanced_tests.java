package game_logic;

import java.util.Scanner;

public class Advanced_tests {
//A tesztelesi keretrendszer az elorehaladott teszteknek:


//Egy egyszeru rendszer amely beolvas az inputrol. Lehet lesz ennek bovitese
private String inputmanager(){
    Scanner myinput =new Scanner(System.in);
    String n=" ";
    n= myinput.next();
    return n;


}
    private void ListTests() {
        System.out.println("Jelenlegi tesztek:");

    }
    //Igy van egy osszefoglalo testmanager amit lehet hivni
    public void TestMgr() {
        //Listazom a teszteket
        this.ListTests();
        switch(Integer.parseInt(this.inputmanager())) {
            case 1:
                System.out.println("fasz");
            break;
        }
    }
}

