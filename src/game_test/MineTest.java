/**package game_test;

import java.util.*;
import game_logic.*;

public class MineTest{
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
      /**      player.InventoryMain.add(Material minedMaterial);
        }

        System.out.println("MineTest Finished\n");
        this.TestMgr();
    }
}*/