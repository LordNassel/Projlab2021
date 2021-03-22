/**package game_test;

import java.util.*;
import game_logic.*;

public class DrillTest{
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
}*/