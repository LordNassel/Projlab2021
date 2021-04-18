package game_test_functions;

import java.util.Vector;
import game_logic.Field;

public class IncreaseUraniumCnt_function {
	public void IncreaseUraniumCnt(Vector<Field> fields, String[] command) {
		int n = 0;
		while(fields.get(n).Getname().equals(command[1]) == false)
			n++;
		// todo befejezni
}
}
