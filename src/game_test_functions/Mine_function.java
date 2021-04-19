package game_test_functions;

import java.util.Vector;

import game_logic.Movable;
import game_logic.Settler;
import game_logic.Alien;

public class Mine_function {
	public void Mine(Vector<Movable> movables, String[] command) {
		   int n = 0;
	    	while(movables.get(n).Getname().equals(command[1]) == false)
	    		n++;
	    	if(movables.get(n).getClass() == Settler.class)
	    		((Settler)movables.get(n)).Mine();
	    	if(movables.get(n).getClass() == Alien.class)
	    		((Alien)movables.get(n)).Mine();
	    }
}
