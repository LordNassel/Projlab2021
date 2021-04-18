package game_test_functions;

import game_logic.Movable;
import game_logic.Settler;

import java.util.Vector;

public class CraftTeleport_function {
    public void CraftTeleport(Vector<Movable> movables, String[] command) {
        int n = 0;
        while(movables.get(n).Getname().equals(command[1]) == false)
            n++;
        ((Settler)movables.get(n)).CraftTeleports(command[2], command[3]);
    }
}
