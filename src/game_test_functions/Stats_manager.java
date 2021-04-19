package game_test_functions;

import game_logic.Asteroid;
import game_logic.Field;
import game_logic.Movable;

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
            System.out.println("Name: " + A1.Getname());
            //napkozeli e
            System.out.println("IsSunSide: " + A1.getSunSide());
            //vastagsaga
            System.out.println("Thickness:" +  A1.getThickness());
            //A szomsz�dok ki�r�sa
            System.out.println("Neighbours: ");
            Vector<Field> temp = A1.FindNeighbor();
            if(temp!= null) {
                for (int i = 0; i < temp.size(); i++)
                    System.out.println(temp.get(0).Getname());
            }
            //Materials benne
            System.out.println("Materials: ");
            //TO-DO

        }
    public void Settler_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
    		//TO-DO
    }
    public void Robot_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
    		//TO-DO
    }
    public void Alien_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
			//TO-DO
    }
    public void Teleport_StatsManager(Vector<Field> Field_Temp_List, String[] command) {
    		//TO-DO
    }
 }

