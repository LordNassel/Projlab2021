package game_test_functions;

import java.util.Vector;

import game_logic.Asteroid;
import game_logic.Coal;
import game_logic.Ice;
import game_logic.Iron;
import game_logic.Uranium;
import game_logic.Field;

public class PutMaterial_function {
	public void Putmaterial(Vector<Field> fields, String[] command) {
		   int n = 0;
	    	while(fields.get(n).Getname().equals(command[1]) == false)
	    		n++;
	    	if(command[2] == "Ice") {
	    		Ice i = new Ice();
	    		((Asteroid) fields.get(n)).StoreMaterial(i);
	    	}
	    	if(command[2] == "Uranium") {
	    		Uranium u = new Uranium();
	    		((Asteroid) fields.get(n)).StoreMaterial(u);
	    	}
	    	if(command[2] == "Coal") {
	    		Coal c = new Coal();
	    		((Asteroid) fields.get(n)).StoreMaterial(c);
	    	}
	    	if(command[2] == "Iron") {
	    		Iron i = new Iron();
	    		((Asteroid) fields.get(n)).StoreMaterial(i);
	    	}
	    }
}
