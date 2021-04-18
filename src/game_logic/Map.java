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

    // radiation level in the asteroid belt
    private double radiation;

   //
    public void Step() {
    	/*Game g = new Game();
    	if(!AnyoneAlive)
    		g.Losegame();*/
    	sugarzas++;
    	System.out.println("\nA sugarzas nagysaga: " + sugarzas);
    	if(sugarzas==11) // Egyel�re �n �ll�tom be, hogy tudjam tesztelni
    		StartSunstorm();
    	
    }

    // Starts the Sun storm for all asteroids
    public void StartSunstorm() {
    	System.out.println("StartSunStorm called");
    	Asteroid a = getRandomAsteroid();
    	a.ReachedBySunStorm();
    	for(int i = 0; i < a.FindNeighbors().size(); i++) {
    		a.FindNeighbors().get(i).ReachedBySunStorm();
    		for(int j = 0; j < a.FindNeighbors().get(i).FindNeighbors().size(); j++) 
    			a.FindNeighbors().get(i).FindNeighbors().get(j).ReachedBySunStorm();
    		}
    }

    //place a teleport on a field
    public void Place_teleport(Teleport t) {
        System.out.println("Place Teleport called");
        
        Coal c = new Coal();
        Asteroid a = new Asteroid("a", "Coal");
        Teleport t1 = new Teleport("tp1");
        
        t1.SetNeighbor(a);
        
    }
    
    public Asteroid getRandomAsteroid() {
    	Random rand = new Random();
    	return (Asteroid) FieldList.get(rand.nextInt(FieldList.size()));
    }

}