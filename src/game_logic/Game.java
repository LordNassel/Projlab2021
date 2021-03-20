package game_logic;

import java.util.*;
/**
 * 
 */
public class Game {

    /**
     * Default constructor
     */
    public Game() {
    	Map map = new Map();
    	//Material mat = new Material();
    	Coal c = new Coal();
    	Iron i = new Iron();
    	Uranium u = new Uranium();
    	Ice ice = new Ice();
    	Asteroid a = new Asteroid(i);
    	Settler s = new Settler(a);
    	//...
    }



    /**
     * @return
     */
    public void Wingame() {
    	System.out.println("Wingame called");
        
    }

    /**
     * @return
     */
    public void Losegame() {
    	System.out.println("Losegame called");
        
    }

    /**
     * @return
     */
    public void GenerateMap() {
    	System.out.println("GenerateMap called");
        Map m = new Map();
    }

    /**
     * @return
     */
    public void StartRound() {
    	System.out.println("StartRound called");
       
    }

    /**
     * @param Playable 
     * @return
     */
    public void AddMovable(Movable Playable) {
    	System.out.println("AddMovable called");
        
    }

    /**
     * 
     */
    public void Step() {
        
    }

}