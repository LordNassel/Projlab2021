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
    	sugarzas++;
    	System.out.println("\nA sugarzas nagysaga: " + sugarzas);
    	if(sugarzas==11) // Egyelõre én állítom be, hogy tudjam tesztelni
    		StartSunstorm();
    	
    }

    // Starts the Sun storm for all asteroids
    public void StartSunstorm() {
    	System.out.println("StartSunStorm called");
    	/*for(Field fifi : FieldList)
    		fifi.ReachedBySunStorm();*/
    	Field field = getRandomAsteroid();
    	Vector<Field> neighbors = field.FindNeighbor();
    	Vector<Field> secondneighbors = new Vector<Field>();
    	field.ReachedBySunStorm();
    	for(int y = 0; y<neighbors.size(); y++)
    	{
    		neighbors.get(y).ReachedBySunStorm(); //minden szomszedra meghivjuk
    		secondneighbors.addAll(neighbors.get(y).FindNeighbor()); //Nem effektív de nekünk megteszi

    	}
    	for(int z=0; z< secondneighbors.size(); z++)
			secondneighbors.get(z).ReachedBySunStorm();
    	sugarzas = 1;
    }

    //place a teleport on a field
    public void Place_teleport() {
        System.out.println("Place Teleport called");
        
        Coal c = new Coal();
        Asteroid a = new Asteroid("a", "Coal");
        Teleport t1 = new Teleport("tp1");
        
        t1.SetNeighbor(a);
        
    }
    
    public Field getRandomAsteroid()
    {
    	Random rand = new Random();
    	int idx = rand.nextInt(FieldList.size()-1);
    	Field asteroid = FieldList.get(idx);
    	return asteroid;
    }

}