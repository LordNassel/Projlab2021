package game_logic;

import java.io.IOException;
import java.util.*;

import view.AsteroidView;
import view.Goal_AsteroidView;
import view.TeleportView;
import view.View;
/**
 * A pálya egy másik lehetséges mezõje, õ reprezentáljaa teleportot az aszteroidaövben.
 * Tároljaa párjául szolgáló teleportot. Fõ feladata,
 * hogy a„használata” során képesnek kell lennie átirányítani a párjának szomszédos
 * aszteroidájára, valamintrá specifikus választ kell tudniaadni a különbözõ egyéb eseményekre.
 */
public class  Teleport extends Field {
	/**
	 * Egy boolean változó annak a nyilvántartására,hogy az aszteroidahasználatban van-e, vagyis aktív vagy sem. Ha igenaz értéke true, ha nem false
	 */
    private boolean IsActive;
    /**
     * Egy boolean változó annak anyilvántartására, hogy a teleportot érte-e napszél.
     */
    private boolean IsSunStroke;
    /**
     * Az aktuális teleport párjaként szolgáló teleport
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
     * Teleportként SunStorm-mal utkozik
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