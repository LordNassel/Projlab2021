package game_logic;

import java.util.*;


public class Game {

	private boolean running;
	private Map map;
	private static ArrayList<Steppable> steppableList = new ArrayList<Steppable>();

    // Default constructor
    public Game() {
    	map = new Map();
    	running = true;
    }
    
    public Game(Map generatedmap) {
    	map = generatedmap;
    	running = true;
    }

    // If the materials are collected this is the method called
    public void Wingame() {
    	System.out.println("Wingame called");
    	running=false;
        
    }

    // If the settlers are dead this is the method called
    public void Losegame() {
    	running = false;
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
    	//MovableList.add(map);
    	//GenerateMap(); //0 Egyelõre külön osztályban generáljuk a mapot 
    	while(running)
    	{
			/*MovableList.forEach(step -> step.Step());*/
			/*if(getIsTherAnySettler()==false)
				Losegame();
    		/*for(Steppable step : MovableList)
    			step.Step();*/
    		for(int i= 0; i<steppableList.size(); i++)
    		{
    			if(getIsTherAnySettler()==false)
    			{
    				Losegame();
    				return;
    			}
    			steppableList.get(i).Step();
    		}  		
    	}
    }

    // Adds a new movable object to the game
    public void AddMovable(Movable Playable, Asteroid a) {
    	System.out.println("AddMovable called");
    	/*Ice i = new Ice();
        Asteroid a1 = new Asteroid("a1", i, false, 0);
    	Asteroid a2 = new Asteroid("a2", i);*/
    	Playable.SetCurrentField(a);
    	/*a2.SetNeighbor(a1);
    	a1.SetNeighbor(a2);*/
    	a.AcceptPlayer(Playable);
    	AddSteppable(Playable);
    	
    }
    
    public static void AddSteppable(Steppable s)
    {
    	steppableList.add(s);
    }
    
    public static void RemoveSteppable(Steppable s)
    {
    	//MovableList.remove(s);
    	steppableList.remove(s);
    	System.out.println("Törtöltem egy Steppablet");
    }

   //Step funkcio overrideja
    /*public void Step() {
        
    }*/
    
    public boolean getIsTherAnySettler()
	{
    	Asteroid a = new Asteroid("bela");
    	Settler s = new Settler(a);
		int number = 0;
		for(int i = 0; i<steppableList.size(); i++)
		{
			if(steppableList.get(i).getClass().equals( s.getClass()))
				number++;
		}
		if(number==0)
			return false;
		return true;
	}

}