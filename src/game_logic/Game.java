package game_logic;

import java.util.*;


public class Game {

	private static boolean running;
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
    public static void Wingame() {
    	System.out.println("Wingame called");
    	running=false;
    }

    // If the settlers are dead this is the method called
    public void Losegame() {
    	running = false;
    	System.out.println("Losegame called");

        
    }

   // Starts the game, by generating the map
    public void StartGame() {
    	System.out.println("A jatek elkezdodott, jo szorakozast!");
    	int number = 1;
    	while(running)
    	{
    		System.out.println("\nA " + number + ". kor elkezdodott");
    		for(int i= 0; i<steppableList.size(); i++)
    		{
    			if(getIsTherAnySettler()==false)
    			{
    				Losegame();
    				return;
    			}
    			steppableList.get(i).Step();
    		}
    		number++;
    	}
    }

    // Adds a new movable object to the game
    public void AddMovable(Movable Playable, Asteroid a) {
    	Playable.SetCurrentField(a);
    	a.AcceptPlayer(Playable);
    	AddSteppable(Playable);
    	
    }
    
    public static void AddSteppable(Steppable s)
    {
    	steppableList.add(s);
    }
    
    public static void RemoveSteppable(Steppable s)
    {
    	steppableList.remove(s);
    }
    
    public boolean getIsTherAnySettler()
	{
    	Asteroid a = new Asteroid("bela");
    	Settler s = new Settler("Palyer", a);
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