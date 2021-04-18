package game_test_functions;

import java.util.Vector;

import game_logic.Movable;

public class Drill_function {
	   public void Drill(Vector<Movable> movables, String[] command) {
		   int n = 0;
	    	while(movables.get(n).Getname().equals(command[1]) == false)
	    		n++;
	    	movables.get(n).GetCurrentField().GetDrilled();
	    }
}
