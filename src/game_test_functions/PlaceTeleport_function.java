package game_test_functions;

import java.util.Vector;

import game_logic.Field;
import game_logic.Movable;
import game_logic.Settler;
import game_logic.Teleport;

public class PlaceTeleport_function {
	public void PlaceTeleport(Vector<Movable> movables, Vector<Field> fields, String[] command) {
		int n = 0, j = 0;
		while(fields.get(n).Getname().equals(command[2]) == false)
			n++;
		while(movables.get(j).Getname().equals(command[1]) == false)
			j++;
		((Settler)movables.get(j)).ActivateTeleport((Teleport)fields.get(n));
	}
}
