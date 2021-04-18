package game_test_functions;

import game_logic.Asteroid;
import game_logic.Field;
import java.util.Vector;
//ennek az oszt�lynak a kiz�r�lagos feladatat, hogy a stats<osztaly> parancsot kezelje
public class Stats_manager {

    public void Asteroid_Stats_Manager(Vector<Field> Field_Temp_List, String[] command) {
        //az aszteroid stats parancsa
            int n = 0;
            while (Field_Temp_List.get(n).Getname().equals(command[2]) == false)
                n += 1;
            //MINDIG aszteroida, a kommand defini�lja
            Asteroid A1 = (Asteroid) Field_Temp_List.get(n);
            //itt �rom ki a nev�t a bolyg�nak
            System.out.println("N�v: " + A1.Getname());
            //A szomsz�dok ki�r�sa
            System.out.println("Szomsz�dok:");
            Vector<Field> temp = A1.FindNeighbor();
            if(temp!= null) {
                for (int i = 0; i < temp.size(); i++)
                    System.out.println(temp.get(0).Getname());
            }
            System.out.println("Vastags�g:" +  A1.getThickness());

        }
    }

