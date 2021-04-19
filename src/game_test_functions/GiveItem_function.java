package game_test_functions;

import java.util.Vector;

import game_logic.Coal;
import game_logic.Ice;
import game_logic.Iron;
import game_logic.Movable;
import game_logic.Uranium;
import game_logic.Settler;

public class GiveItem_function {
	public void GiveItem(Vector<Movable> movables, String[] command) {
		int n= 0;
		while(movables.get(n).Getname().equals(command[1]) == false)
			n++;
		if(command[2] == "Ice") {
    		Ice i = new Ice();
    		((Settler) movables.get(n)).Store(i);
    	}
    	if(command[2] == "Uranium") {
    		Uranium u = new Uranium();
    		((Settler) movables.get(n)).Store(u);
    	}
    	if(command[2] == "Coal") {
    		Coal c = new Coal();
    		((Settler) movables.get(n)).Store(c);
    	}
    	if(command[2] == "Iron") {
    		Iron i = new Iron();
    		((Settler) movables.get(n)).Store(i);
	}
}
}
