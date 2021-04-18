package game_test_functions;

import game_logic.Field;
import java.util.Vector;
//ennek az osztálynak a kizárólagos feladatat, hogy a stats<osztaly> parancsot kezelje
public class Stats_manager {

    public void Asteroid_Stats_Manager(Vector<Field> Field_Temp_List, String[] command) {
        //az aszteroid stats parancsa
            int n = 0;
            while (Field_Temp_List.get(n).Getname().equals(command[2]) == false)
                n += 1;
            //itt írom ki a nevét a bolygónak
            System.out.println(Field_Temp_List.get(n).Getname());
        }
    }

