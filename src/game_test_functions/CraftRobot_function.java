package game_test_functions;

import game_logic.Movable;
import game_logic.Settler;

import java.util.Vector;

public class CraftRobot_function {
    public void CraftRobot(Vector<Movable> movables, String[] command) {
        int n = 0;
        while(movables.get(n).Getname().equals(command[1]) == false)
            n++;
        ((Settler)movables.get(n)).CraftRobot();
    }
}
