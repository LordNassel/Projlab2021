/**package game_test;

import java.util.*;
import game_logic.*;

public class MoveTest {

    private void MoveTest() {
        System.out.println("Mozgas teszt indul\n");
        Iron iron = new Iron();
        //Konstruktor guide -> asteroid -> name, material
        Asteroid a1 = new Asteroid("a1", iron);
        Asteroid a2 = new Asteroid("a2", iron);
        Asteroid a3 = new Asteroid("a3", iron);

        //Konstruktor guide -> settler -> asteroid
        Settler player = new Settler(a1);

        //Bolygok kapcsolatai
        a1.SetNeighbor(a3);
        a1.SetNeighbor(a2);

        //a mozgo fgv.
        player.FindDirections();

        //kiiras hogy ellenorizheto legyen
        System.out.println("Sikeres Mozgas, a jelenlegi bolygo :\n");
        System.out.println(player.GetCurrentField().Getname());

        this.TestMgr();
    }
}*/
