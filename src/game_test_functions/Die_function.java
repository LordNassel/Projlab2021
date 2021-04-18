package game_test_functions;

import game_logic.Movable;

import java.util.Vector;

public class Die_function {
    public void Die(Vector<Movable> movables, String[] command) {
        int n = 0;
        while(movables.get(n).Getname().equals(command[1]) == false)
            n++;
        movables.get(n).Die();
    }
}
