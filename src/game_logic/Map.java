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

   
    private bool AnyoneAlive;

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
        Movable mov = new Movable();
        Asteroid a = new Asteroid(mat);
        Material mat = new Material();
    }

    /**
     * @return
     */
    public Field Place_teleport() {
        System.out.println("Place Teleport called");
        Material m = new Material();
        Asteroid a = new Asteroid(m);
        Teleport t1 = new Teleport();
        Teleport t2 = new Teleport();
        Settler s = new Settler(a);
    }

}