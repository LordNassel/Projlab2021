package game_test_functions;

import game_logic.Field;
import java.util.Vector;
//ennek az oszt�lynak a kiz�r�lagos feladatat, hogy a stats<osztaly> parancsot kezelje
public class Stats_manager {

    public void Asteroid_Stats_Manager(Vector<Field> Field_Temp_List, String[] command) {
        //az aszteroid stats parancsa
            int n = 0;
            while (Field_Temp_List.get(n).Getname().equals(command[2]) == false)
                n += 1;
            //itt �rom ki a nev�t a bolyg�nak
            System.out.println(Field_Temp_List.get(n).Getname());
        }
    }

