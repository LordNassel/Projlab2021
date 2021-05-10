package game_logic;

import view.TeleportView;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * A pálya egy másik lehetséges mezõje, õ reprezentáljaa teleportot az aszteroidaövben.
 * Tároljaa párjául szolgáló teleportot. Fõ feladata,
 * hogy a„használata” során képesnek kell lennie átirányítani a párjának szomszédos
 * aszteroidájára, valamintrá specifikus választ kell tudniaadni a különbözõ egyéb eseményekre.
 */
public class  Teleport extends Field {
    /**
     * Egy boolean változó annak a nyilvántartására, hogy az aszteroida használatban van-e, vagyis aktív vagy sem.
     * Ha aktív, akkor az értéke true, ha inaktív, akkor false.
     */
    private boolean IsActive;

    /**
     * Egy boolean változó annak a nyilvántartására, hogy a teleportot érte-e napszél.
     */
    private boolean IsSunStroke;

    /**
     * Az aktuális teleport párjaként szolgáló teleport.
     */
    private Teleport Pair;

    /**
     * Az aszteroida, amin a teleport van.
     */
    protected Asteroid onAsteroid;

    /**
     * Constructor
     */
    public Teleport(String name, Asteroid onField){
    	super(name);
    	IsActive = false;
    	IsSunStroke = false;
        onAsteroid = onField;
    }

    /**
     * Constructor
     */
    public Teleport(String name){
        super(name);
        IsActive = false;
        IsSunStroke = false;
    }

    /**
     * A paraméterül kapott mezõt hozzáadja a neighbors listához.
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
     * Visszaadja, hogy a teleport aktív-e.
     */
    public boolean getIsActive(){
        return IsActive;
    }

    /**
     * Beállítja, hogy a teleport aktív-e.
     */
    public void setIsActive() { 
        IsActive = !IsActive;
    }

    /**
     * Visszaadja, hogy a teleportot érte-e napszél.
     */
    public boolean getIsSunStroke() {return IsSunStroke;}

    /**
     * Beállítja, hogy a teleportot érte-e napszél.
     */
    public void setIsSunStroke() {IsSunStroke = !IsSunStroke;}

    /**
     * Beállítja a teleport párját.
     * @param givenTeleport
     */
    public void setPair(Teleport givenTeleport) {
        Pair = givenTeleport;
    }

    /**
     * Teleportra lepest kezel
     * A parjanak az aszteroidájára leptet, ha az aktiv.
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
     * Beállítja a teleport aszteroidáját.
     * @param a
     */
    public void setOnAsteroid(Asteroid a)
    {
    	onAsteroid = a;
        createFieldView();
    }
    /**
     * Visszaadja a teleport párját.
     * @return
     */
    public Teleport getPair(){
        return Pair;
    }

    /**
     * A teleportot nem lehet megfúrni.
     */
	@Override 
	public boolean GetDrilled() {
		return false;
	}

    /**
     *  Létrehozza a Teleport nézetét.
     */
	@Override
	public void createFieldView() {
		try {
			this.fieldView = new TeleportView(this.onAsteroid.getFieldView().getPosx()+110, this.onAsteroid.getFieldView().getPosy()+60);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}