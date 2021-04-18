package game_test_functions;

import game_logic.Field;

import java.util.Vector;

public class StartSunstorm_function {
    public void StartSunstorm(Vector<Field> fields, String[] command) {
        int n=0;
        while (fields.get(n).Getname().equals(command[1])== false) {
            n++;
        }
        // Map osztalyb�l
        Field field = fields.get(n);
        Vector<Field> neighbors = field.FindNeighbor();
        Vector<Field> secondneighbors = new Vector<Field>();
        field.ReachedBySunStorm();
        for(int y = 0; y < neighbors.size(); y++)
        {
            neighbors.get(y).ReachedBySunStorm(); //minden szomszedra meghivjuk
            secondneighbors.addAll(neighbors.get(y).FindNeighbor()); //Nem effekt�v de nek�nk megteszi
        }
        for(int z=0; z < secondneighbors.size(); z++)
            secondneighbors.get(z).ReachedBySunStorm();
    }
}
