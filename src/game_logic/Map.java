package game_logic;

import java.util.*;

/**
 * A j�t�k sz�nter�t, vagyis az aszteroida�vet val�s�tja meg.
 * Sz�mon tartja az ott el�fordul� objektumokat �s � felel az aszteroida�vben l�tre j�v� napvihar�rt is
 */
public class Map implements Steppable {

	/**
	 * Az aszteroida�vben uralkodo sugarzas nagysaga
	 * 7 �s 10 kozott napvihar kovetkezik be
	 */
	private int sugarzas = 1;

	/**
	 * Az aszteroida�v mez�i.
	 */
	protected Vector<Field> FieldList;

	/**
	 * Egy boolean arra, hogy �lnek-e m�g telepesek a j�t�kban. Ha igen, akkor az �rt�ke igaz, ha nem, akkor hamis.
	 */
	private boolean AnyoneAlive;

	/**
	 * A Default constructor, kezdetben vannak �l� telepesek.
	 */
    public Map() { AnyoneAlive = true; }
    
    /**
     * Konstruktor, param�terben �tadhat� a map-ot alkot� fieldek.
     */
    public Map(boolean alive, Vector<Field> newdb)
    { 
    	AnyoneAlive = alive;
    	FieldList = newdb;
    }

    /**
     * N�veli a sug�rz�s nagys�g�t �s ha el�rt egykorl�tot akkor v�letlenszer�engener�l egy napvihart
     */
    public void Step() {
    	sugarzas++; // N�velj�k a sugarzas szintjet
    	System.out.println("\nA sugarzas nagysaga: " + sugarzas + "\n"); //Kihirdetj�k
    	if(sugarzas >=7 && sugarzas < 9) // Ha 7 �s 9 k�z�tt van az �rt�ke random bek�vetkezhet egy napvihar
    	{
    		Random rand = new Random();
    		int cnt = rand.nextInt(5);
    		if(cnt<3)
    			StartSunstorm();
    	}
    	else if(sugarzas == 9) // Ha elerte a 9-et fixen napvihar gener�l�sa
    		StartSunstorm();
    	
    }

    /**
     * A napvihar lefut�s��rt felel�s met�dus.
     * Egy bizonyos ter�leten keletkezik
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
    		secondneighbors.addAll(neighbors.get(y).FindNeighbor()); 

    	}
    	for(int z=0; z < secondneighbors.size(); z++)
			secondneighbors.get(z).ReachedBySunStorm();
    	sugarzas = 1;
    }
    
    /**
     * Egy random aszteroid�val t�r vissza az aszteroida�vb�l.
     */
    public Field getRandomAsteroid()
    {
    	Random rand = new Random();
    	int idx = rand.nextInt(FieldList.size()-1);
    	Field asteroid = FieldList.get(idx);
    	return asteroid;
    }

	/**
	 *  Visszaadja az aszteroida�v mez�it.
	 */
    public Vector<Field> getFieldList()
    {
    	return FieldList;
    }

	/**
	 *  Visszaadja az aszteroida�vben uralkodo sugarzas nagysagat.
	 */
	public int getSugarzas(){return sugarzas;}
}