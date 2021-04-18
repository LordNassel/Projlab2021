package game_test_functions;

import game_logic.Asteroid;
import game_logic.Field;
import java.util.Vector;
//ennek az osztálynak a kizárólagos feladatat, hogy a stats<osztaly> parancsot kezelje
public class Stats_manager {

    public void Asteroid_Stats_Manager(Vector<Field> Field_Temp_List, String[] command) {
        //az aszteroid stats parancsa
            int n = 0;
            while (Field_Temp_List.get(n).Getname().equals(command[2]) == false)
                n += 1;
            //MINDIG aszteroida, a kommand definiálja
            Asteroid A1 = (Asteroid) Field_Temp_List.get(n);
            //itt írom ki a nevét a bolygónak
            System.out.println("Név: " + A1.Getname());
            //A szomszédok kiírása
            System.out.println("Szomszédok:");
            Vector<Field> temp = A1.FindNeighbor();
            if(temp!= null) {
                for (int i = 0; i < temp.size(); i++)
                    System.out.println(temp.get(0).Getname());
            }
            System.out.println("Vastagság:" +  A1.getThickness());

        }
    }

