package game_test_functions;

import java.util.Vector;

import game_logic.Movable;

public class Mine_function {
	public void Mine(Vector<Movable> movables, String[] command) {
		   int n = 0;
	    	while(movables.get(n).Getname().equals(command[1]) == false)
	    		n++;
	    	movables.get(n).Mine();
	    }
}
