package game_logic;

import java.util.*;

/**
 * 
 */
public class Map implements Steppable {

    /**
     * Default constructor
     */
    public Map() {
    	AnyoneAlive = true;
    	
    }

   
    private boolean AnyoneAlive;

    /**
     * 
     */
    private Set<Field> Field;

    /**
     * 
     */
    private Game Map;


    /**
     * 
     */
    public void Step() {
    	
    }

    /**
     * @return
     */
    public void StartSunstorm() {
    	System.out.println("SunStorm called");
       /* Movable mov = new Movable();
        Material mat = new Material();*/ 	//Absztrakt osztály nem lehet példányosítani
    	Iron i = new Iron();
        Asteroid a = new Asteroid("name", i);
        Settler s = new Settler(a);
    }

    /**
     * @return
     */
    public void /*Field*/ Place_teleport() { //Ha Field-et ad vissza (miért?) akkor return kell
        System.out.println("Place Teleport called");
        //Material m = new Material();
        Coal c = new Coal();
        Asteroid a = new Asteroid("name", c);
        Teleport t1 = new Teleport("name");
        Teleport t2 = new Teleport("name");
        Settler s = new Settler(a);
    }

}