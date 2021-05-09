package game_test_functions;

import java.util.Vector;

import game_logic.Asteroid;
import game_logic.Field;

public class IncreaseUraniumCnt_function {
	public void IncreaseUraniumCnt(Vector<Field> fields, String[] command) {
		int n = 0;
		while(fields.get(n).Getname().equals(command[1]) == false)
			n++;
		Asteroid A1 = (Asteroid) fields.get(n);
		A1.IncUntCnt_DEBUG();
		fields.add(n, A1);
	}
}
