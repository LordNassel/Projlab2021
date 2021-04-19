package game_test_functions;

import java.util.Vector;

import game_logic.Coal;
import game_logic.Goal_Asteroid;
import game_logic.Ice;
import game_logic.Iron;
import game_logic.Uranium;
import game_logic.Field;

public class CompleteMaterial_function {
	public void CompleteMaterial(Vector<Field> fields, String[] command) {
		int n = 0;
		while(fields.get(n).Getname().equals(command[1]) == false)
			n++;
		if(command[2] == "Ice") {
    		Ice i = new Ice();
    		((Goal_Asteroid)fields.get(n)).CompleteMaterial(i);
    	}
    	if(command[2] == "Uranium") {
    		Uranium u = new Uranium();
    		((Goal_Asteroid)fields.get(n)).CompleteMaterial(u);
    	}
    	if(command[2] == "Coal") {
    		Coal c = new Coal();
    		((Goal_Asteroid)fields.get(n)).CompleteMaterial(c);
    	}
    	if(command[2] == "Iron") {
    		Iron i = new Iron();
    		((Goal_Asteroid)fields.get(n)).CompleteMaterial(i);
	}
	}
}
