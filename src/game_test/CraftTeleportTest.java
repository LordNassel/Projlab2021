package game_test;

import java.util.*;
import game_logic.*;

public class CraftTeleportTest{

        private void CraftTeleportTest()
        {
            protected int numIron = 0, numUranium = 0, numIce = 0, numCoal = 0;
            Settler player = new Settler(settler1);

            System.out.println("CraftTeleportTest Started\n");
            System.out.println("Press T to Craft Teleport\n");

            while(this.inputmanager() == R) {
                for (int i=0; i < player.InventoryMain.size(); i++){
                    if(getType(player.InventoryMain.i) == getType(Iron)){
                        numIron++;
                    }
                    if(getType(player.InventoryMain.i) == getType(Uranium)){
                        numUranium++;
                    }
                    if(getType(player.InventoryMain.i) == getType(Ice)){
                        numIce++;
                    }
                    if(getType(player.InventoryMain.i) == getType(Coal)){
                        numCoal++;
                    }
                }

                if(numIron = 2 && numIce == 2 && numUranium == 1){
                    System.out.println("CraftTeleportTest Finished\n");
                }
            }
            this.TestMgr();
        }
}