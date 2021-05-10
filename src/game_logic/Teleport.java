package game_logic;

import view.TeleportView;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * A p�lya egy m�sik lehets�ges mez�je, � reprezent�ljaa teleportot az aszteroida�vben.
 * T�roljaa p�rj�ul szolg�l� teleportot. F� feladata,
 * hogy a�haszn�lata� sor�n k�pesnek kell lennie �tir�ny�tani a p�rj�nak szomsz�dos
 * aszteroid�j�ra, valamintr� specifikus v�laszt kell tudniaadni a k�l�nb�z� egy�b esem�nyekre.
 */
public class  Teleport extends Field {
    /**
     * Egy boolean v�ltoz� annak a nyilv�ntart�s�ra, hogy az aszteroida haszn�latban van-e, vagyis akt�v vagy sem.
     * Ha akt�v, akkor az �rt�ke true, ha inakt�v, akkor false.
     */
    private boolean IsActive;

    /**
     * Egy boolean v�ltoz� annak a nyilv�ntart�s�ra, hogy a teleportot �rte-e napsz�l.
     */
    private boolean IsSunStroke;

    /**
     * Az aktu�lis teleport p�rjak�nt szolg�l� teleport.
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
     * A param�ter�l kapott mez�t hozz�adja a neighbors list�hoz.
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
     * Visszaadja, hogy a teleport akt�v-e.
     */
    public boolean getIsActive(){
        return IsActive;
    }

    /**
     * Be�ll�tja, hogy a teleport akt�v-e.
     */
    public void setIsActive() { 
        IsActive = !IsActive;
    }

    /**
     * Visszaadja, hogy a teleportot �rte-e napsz�l.
     */
    public boolean getIsSunStroke() {return IsSunStroke;}

    /**
     * Be�ll�tja, hogy a teleportot �rte-e napsz�l.
     */
    public void setIsSunStroke() {IsSunStroke = !IsSunStroke;}

    /**
     * Be�ll�tja a teleport p�rj�t.
     * @param givenTeleport
     */
    public void setPair(Teleport givenTeleport) {
        Pair = givenTeleport;
    }

    /**
     * Teleportra lepest kezel
     * A parjanak az aszteroid�j�ra leptet, ha az aktiv.
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
     * Be�ll�tja a teleport aszteroid�j�t.
     * @param a
     */
    public void setOnAsteroid(Asteroid a)
    {
    	onAsteroid = a;
        createFieldView();
    }
    /**
     * Visszaadja a teleport p�rj�t.
     * @return
     */
    public Teleport getPair(){
        return Pair;
    }

    /**
     * A teleportot nem lehet megf�rni.
     */
	@Override 
	public boolean GetDrilled() {
		return false;
	}

    /**
     *  L�trehozza a Teleport n�zet�t.
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