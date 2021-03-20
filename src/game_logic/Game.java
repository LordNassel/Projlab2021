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
    	Material mat = new Material();
    	Asteroid a = new Asteroid(mat);
    	Settler s = new Settler();
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