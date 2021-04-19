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

   // true, if at least 1 player is alive
    private boolean AnyoneAlive;


   //
    public void Step() {
    	sugarzas++; // Növeljük a sugarzas szintjet
    	System.out.println("\nA sugarzas nagysaga: " + sugarzas + "\n"); //Kihirdetjük
    	if(sugarzas >=7 && sugarzas < 9) // Ha 7 és 9 között van az értéke random bekövetkezhet egy napvihar
    	{
    		Random rand = new Random();
    		int cnt = rand.nextInt(5);
    		if(cnt<3)
    			StartSunstorm();
    	}
    	else if(sugarzas == 9) // Ha elerte a 9-et fixen napvihar generálása
    		StartSunstorm();
    	
    }

    // Starts the Sun storm for all asteroids
    public void StartSunstorm() {
    	System.out.println("\nNapvihar kezdodott ebben a korben");
    	Field field = getRandomAsteroid();
    	Vector<Field> neighbors = field.FindNeighbor();
    	Vector<Field> secondneighbors = new Vector<Field>();
    	field.ReachedBySunStorm();
    	for(int y = neighbors.size()-1; y>=0; y--)
    	{
    		neighbors.get(y).ReachedBySunStorm(); //minden szomszedra meghivjuk
    		secondneighbors.addAll(neighbors.get(y).FindNeighbor()); //Nem effektív de nekünk megteszi

    	}
    	for(int z=0; z< secondneighbors.size(); z++)
			secondneighbors.get(z).ReachedBySunStorm();
    	sugarzas = 1;
    }

    //place a teleport on a field
   /* public void Place_teleport() {
        
        Coal c = new Coal();
        Asteroid a = new Asteroid("a", "Coal");
        Teleport t1 = new Teleport("tp1");
        
        t1.SetNeighbor(a);
        
    }*/
    
    public Field getRandomAsteroid()
    {
    	Random rand = new Random();
    	int idx = rand.nextInt(FieldList.size()-1);
    	Field asteroid = FieldList.get(idx);
    	return asteroid;
    }

}