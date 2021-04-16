package game_logic;

import java.util.*;


public class Game {

	private boolean running = true;
	protected ArrayList<Movable> SteppableList = new ArrayList<Movable>();

    // Default constructor
    public Game() {
    	Map map = new Map();
    }

    // If the materials are collected this is the method called
    public void Wingame() {
    	System.out.println("Wingame called");
        
    }

    // If the settlers are dead this is the method called
    public void Losegame() {
    	System.out.println("Losegame called");
        
    }

   // Creates the playable map for the users
    public void GenerateMap() {
    	System.out.println("GenerateMap called");
    	
    	Coal c = new Coal();
    	Ice i = new Ice();
    	
        Asteroid a1 = new Asteroid("a1", i);
        Asteroid a2 = new Asteroid("a2", c);
        
        Vector<Material> GoalMaterials = new Vector<Material>();
        
        Goal_Asteroid ga = new Goal_Asteroid("ga", i, GoalMaterials);
        
        Settler s = new Settler(ga);
        ga.AcceptPlayer(s);
    }

   // Starts the game, by generating the map
    public void StartGame() {
    	System.out.println("StartGame called");
    	//GenerateMap(); //0 Egyelõre külön osztályban generáljuk a mapot 
    	while(running)
    	{
    		for(int i=0; i<SteppableList.size(); i++)
    			SteppableList.get(i).Step();
    	}
    }

    // Adds a new movable object to the game
    public void AddMovable(Movable Playable) {
    	System.out.println("AddMovable called");
    	Ice i = new Ice();
        Asteroid a1 = new Asteroid("a1", i);
    	
    	Playable.SetCurrentField(a1);
    	a1.AcceptPlayer(Playable);
    	
    }
    
    public void setSteppable(Movable m)
    {
    	SteppableList.add(m);
    }

   //Step funkcio overrideja
    public void Step() {
        
    }

}