package game_test_functions;

import java.util.Vector;

import game_logic.Field;
import game_logic.Movable;

public class Move_function {
	public void Move(Vector<Field> fields, Vector<Movable> movables, String[] command) {
		int n = 0, j = 0;
		while(fields.get(n).Getname().equals(command[2])== false)
			n++;
		while(movables.get(n).Getname().equals(command[1]) == false)
			j++;
		movables.get(j).Move(fields.get(n));
	}
}
