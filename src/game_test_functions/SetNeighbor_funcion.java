package game_test_functions;

import game_logic.Asteroid;
import game_logic.Field;
import game_logic.Teleport;

import java.util.Vector;
//Egyszeru függvény két field típus egymás szomszédjává tételére -> elvileg a goalasteroiddal is mukodnie kell
public class SetNeighbor_funcion {
    Vector<Field> temp = new Vector<>();
    //elso override: A két aszteroida
    private Vector<Field> setneighbor (Asteroid A1, Asteroid A2){

        //igen ez ilyen primitív, hogy csak 2 bolygóval m?ködik. Lehetne jobb is de jó eséllyel nem lenne neki haszna
        A1.SetNeighbor(A2);
        A2.SetNeighbor(A1);

        temp.add(A1);
        temp.add(A2);
        return temp;
    }
//teleport
    private Vector<Field> setneighbor (Teleport T1, Asteroid A1){
        A1.setTeleportOnAsteroid(T1);
        T1.SetNeighbor(A1);

        temp.add(T1);
        temp.add(A1);
        return temp;
    }

}
