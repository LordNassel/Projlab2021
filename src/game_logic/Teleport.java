package game_logic;

import java.io.IOException;
import java.util.*;

import view.AsteroidView;
import view.Goal_AsteroidView;
import view.TeleportView;
import view.View;
/**
 * A p�lya egy m�sik lehets�ges mez�je, � reprezent�ljaa teleportot az aszteroida�vben.
 * T�roljaa p�rj�ul szolg�l� teleportot. F� feladata,
 * hogy a�haszn�lata� sor�n k�pesnek kell lennie �tir�ny�tani a p�rj�nak szomsz�dos
 * aszteroid�j�ra, valamintr� specifikus v�laszt kell tudniaadni a k�l�nb�z� egy�b esem�nyekre.
 */
public class  Teleport extends Field {
	/**
	 * Egy boolean v�ltoz� annak a nyilv�ntart�s�ra,hogy az aszteroidahaszn�latban van-e, vagyis akt�v vagy sem. Ha igenaz �rt�ke true, ha nem false
	 */
    private boolean IsActive;
    /**
     * Egy boolean v�ltoz� annak anyilv�ntart�s�ra, hogy a teleportot �rte-e napsz�l.
     */
    private boolean IsSunStroke;
    /**
     * Az aktu�lis teleport p�rjak�nt szolg�l� teleport
     */
    private Teleport Pair;
    protected Asteroid onAsteroid;
    /**
     *Default Constructor
     */
    public Teleport(String name, Asteroid onField){
    	super(name);
    	IsActive = false;
    	IsSunStroke = false;
        onAsteroid = onField;
    }

    public Teleport(String name){
        super(name);
        IsActive = false;
        IsSunStroke = false;
    }


    /**
     * Two-member teleport vector
     * @param WhichField
     */

    @Override
    public void SetNeighbor(Field WhichField){
        Neighbors.add(WhichField);
    }

    /**
     * Teleportk�nt SunStorm-mal utkozik
     * Szomszedos aszteroidara kerul a teleport
     */
    @Override
    public void ReachedBySunStorm(){
    	Vector<Field> neighbors = onAsteroid.FindNeighbor();
    	Random rand = new Random(); 
    	int idx = rand.nextInt(neighbors.size()-1);
    	while(neighbors.get(idx).getClass() == Teleport.class)
    		idx = rand.nextInt(neighbors.size()-1);
    	onAsteroid.removeTeleportOnAsteroi(this);
    	onAsteroid = ((Asteroid)neighbors.get(idx));
    	onAsteroid.addTeleportOnAsteroid(this);
    		
        if(IsSunStroke == false){
         setIsSunStroke();
        }
    }

    /**
    * Getters and Setters
    */
    public boolean getIsActive(){
        return IsActive;
    }

    /**
     * Getter
     */
    public void setIsActive() { 
        IsActive = !IsActive;
    }

    /**
     * Getter
     */
    public boolean getIsSunStroke() {return IsSunStroke;}

    /**
     * Setter
     */
    public void setIsSunStroke() {IsSunStroke = !IsSunStroke;}

    /**
     * We need this, so two teleport pairs cannot be crafted and set each other's mixed neighbors
     * @param givenTeleport
     */
    public void setPair(Teleport givenTeleport) {
        Pair = givenTeleport;
    }

    /**
     * Teleportra lepest kezel
     * A parjanak az aszteroidara leptet ha az aktiv
     */
    @Override
    public void AcceptPlayer(Movable M) {
    	Teleport pair = this.getPair();
    	if(pair.getIsActive() == true)
    	{
    		Asteroid target = pair.onAsteroid;
    		target.AcceptPlayer(M);
    	}
    	else
    	{
    		System.out.println("Hiba: A teleport parja nincsa aktivalva");
    		return;
    	}
    	
    }
    
    /**
     * Setter
     * @param a
     */
    public void setOnAsteroid(Asteroid a)
    {
    	onAsteroid = a;
        createFieldView();
    }
    /**
     * Getter
     * @return
     */
    public Teleport getPair(){
        return Pair;
    }

    /**
     * Getter
     */
	@Override 
	public boolean GetDrilled() {
		return false;
	}

	@Override
	public void createFieldView() {
		try {
			this.fieldView = new TeleportView(this.onAsteroid.getFieldView().getPosx()+110, this.onAsteroid.getFieldView().getPosy()+60);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}