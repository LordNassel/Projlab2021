package game_logic;

import java.util.*;


public class Map implements Steppable {

	private int sugarzas = 1;
	protected Vector<Field> FieldList;
	// Default constructor
    public Map() { AnyoneAlive = true; }

    public Map(boolean alive, Vector<Field> newdb)
    { 
    	AnyoneAlive = alive;
    	FieldList = newdb;
    }


    // Storage for the asteroids 
   // protected Vector<Field> FieldList = new Vector<Field>();

   // true, if at least 1 player is alive
    private boolean AnyoneAlive;


   //
    public void Step() {
    	/*Game g = new Game();
    	if(!AnyoneAlive)
    		g.Losegame();*/
    	System.out.println("Stepbol a filedlist: " + FieldList.size());
    	System.out.println("Sugarzas eggyel nott");
    	sugarzas++;
    	System.out.println("A sugarzas nagysaga: " + sugarzas);
    	if(sugarzas==4)
    		StartSunstorm();
    	
    }

    // Starts the Sun storm for all asteroids
    public void StartSunstorm() {
    	System.out.println("StartSunStorm called");
    	System.out.println("Fieldlist: " + FieldList.size()); //Ideiglenesen, hogy tudjam ellenõrizni
    	for(int i = 0; i < FieldList.size(); i++)
    		FieldList.get(i).SunStorm();
    	sugarzas = 0;
    }

    //place a teleport on a field
    public void Place_teleport() {
        System.out.println("Place Teleport called");
        
        Coal c = new Coal();
        Asteroid a = new Asteroid("a", c);
        Teleport t1 = new Teleport("tp1");
        
        t1.SetNeighbor(a);
        
    }

}