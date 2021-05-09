package game_test_functions;

import game_logic.Asteroid;
import game_logic.Field;
import game_logic.Teleport;

import java.util.Vector;

public class SetNeighbor_funcion {

    public Vector<Field> TeenyTinyNeighboursetty(Vector<Field> fields, String[] command){
        int n=0, h=0;
        while (fields.get(n).Getname().equals(command[1])== false) {
            n++;
        }
        while (fields.get(h).Getname().equals(command[2])== false)
            h++;
        fields.get(n).SetNeighbor(fields.get(h));
        fields.get(h).SetNeighbor(fields.get(n));
    return fields;
    }

}
