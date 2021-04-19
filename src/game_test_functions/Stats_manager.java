package game_test_functions;

import game_logic.*;

import java.util.List;
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
            System.out.println("Name: " + A1.Getname());
            //napkozeli e
            System.out.println("IsSunSide: " + String.valueOf(A1.getSunSide()));
            //vastagsaga
            System.out.println("Thickness:" +  A1.getThickness());
            //A szomszédok kiírása
            System.out.println("Neighbours: ");
            Vector<Field> temp = A1.FindNeighbor();
            if(temp!= null) {
                for (int i = 0; i < temp.size(); i++)
                    System.out.println(temp.get(0).Getname());
            }
            //Materials benne
            System.out.println("Materials: ");
            List<Material> mats = A1.getMats();
            for(int i=0; i<A1.getMats().size(); i++){
                if (A1.getMats().get(i) instanceof Uranium )
                    System.out.println("Uranium, ");
                else if (A1.getMats().get(i) instanceof Ice )
                    System.out.println("Ice, ");
                else if (A1.getMats().get(i) instanceof Coal)
                    System.out.println("Coal, ");
                else if (A1.getMats().get(i) instanceof Iron )
                    System.out.println("Iron, ");
                else
                	System.out.println("Uranium, ");
                	
                }

        }
    public void Settler_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
        int n=0; 
        while (Movable_Temp_List.get(n).Getname().equals(command[1]) == false)
            n++;
        System.out.println(Movable_Temp_List.get(n).Getname());
        System.out.println(Movable_Temp_List.get(n).GetCurrentField().Getname());
        //Tudom hogy settler
        Settler S1 = (Settler) Movable_Temp_List.get(n);
        for(int i=0; i<S1.GetInventory_DEBUG().size(); i++){
            if (S1.GetInventory_DEBUG().get(i) instanceof Uranium )
                System.out.println("Uranium");
            if (S1.GetInventory_DEBUG().get(i) instanceof Ice )
                System.out.println("Ice");
            if (S1.GetInventory_DEBUG().get(i) instanceof Coal)
                System.out.println("Coal");
            if (S1.GetInventory_DEBUG().get(i) instanceof Iron )
                System.out.println("Iron");
            }
        }

    public void Robot_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
        int n=0;
        while (Movable_Temp_List.get(n).Getname().equals(command[2]) == false)
            n++;
        System.out.println("Név :" + Movable_Temp_List.get(n).Getname());
        System.out.println("Szomszédos aszteroidák :" + Movable_Temp_List.get(n).GetCurrentField().Getname());
    }
    public void Alien_StatsManager(Vector<Movable> Movable_Temp_List, String[] command) {
        int n=0;
        while (Movable_Temp_List.get(n).Getname().equals(command[2]) == false)
            n++;
        System.out.println(Movable_Temp_List.get(n).Getname());
        System.out.println(Movable_Temp_List.get(n).GetCurrentField().Getname());
    }
    public void Teleport_StatsManager(Vector<Field> Field_Temp_List, String[] command) {
        int n=0;
        while (Field_Temp_List.get(n).Getname().equals(command[2]) == false)
            n++;
        Teleport T1 = (Teleport) Field_Temp_List.get(n);
        System.out.println("Aktivitás: " + Field_Temp_List.get(n).Getname());
        System.out.println("Aktivitás: " + Field_Temp_List.get(n).Getname());
    }
 }