package game_logic;

import java.util.*;

/**
 * A játék színterét, vagyis az aszteroidaövet valósítja meg.
 * Számon tartja az ott elõforduló objektumokat és õ felel az aszteroida övben létre jövõ napviharért is
 */
public class Map implements Steppable {

	/**
	 * Az aszteroida ovben uralkodo sugarzas nagysaga
	 * 7 és 10 kozott napvihar kovetkezik be
	 */
	private int sugarzas = 1;
	/**
	 * Az aszteroidaöv mezõi
	 */
	protected Vector<Field> FieldList;
	// Default constructor
    public Map() { AnyoneAlive = true; }
    
    /**
     * Paraméterben átadható a map-ot alkotó fieldek
     */
    public Map(boolean alive, Vector<Field> newdb)
    { 
    	AnyoneAlive = alive;
    	FieldList = newdb;
    }

    /**
     * Egy boolean arra, hogy élnek-emég telepesek a játékban. Haigen,akkor az értéke igaz, ha nem akkor hamis
     */
    private boolean AnyoneAlive;


    /**
     * Növeli a sugárzás nagyságát és ha elért egykorlátot akkor véletlenszerûengenerál egy napvihart
     */
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

    /**
     * A napvihar lefutásáért felelõs metódus.
     * Egy bizonyos területen keletkezik
     */
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
    
    /**
     * Egy random aszteroidával tér vissza azaszteroida övbõl
     */
    public Field getRandomAsteroid()
    {
    	Random rand = new Random();
    	int idx = rand.nextInt(FieldList.size()-1);
    	Field asteroid = FieldList.get(idx);
    	return asteroid;
    }
    
    public Vector<Field> getFieldList()
    {
    	return FieldList;
    }

}