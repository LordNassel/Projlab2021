package game_logic;

import java.util.*;
/**
 * This class specifies the variables and methods to be implemented for the Teleports
 */
public class  Teleport extends Field {
    private boolean IsActive;
    private boolean IsSunStroke;
    private Teleport Pair;
    protected Vector<Teleport> TeleportPair = new Vector<Teleport>();
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

    //Erre ideiglenesen mindenkepp szukseg van, de szerintem permanensen is
    public Teleport(String name){
        super(name);
        IsActive = false;
        IsSunStroke = false;
    }


    /**
     * Two-member teleport vector
     *     TODO:: How to set teleport as neighbors, cause Field neighbors are set already by func main OR!
     * @param WhichField
     */

    @Override
    public void SetNeighbor(Field WhichField){
        Neighbors.add(WhichField);
    }

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

    
    public void setIsActive() { 
        IsActive = !IsActive;
        
    }

    public boolean getIsSunStroke() {return IsSunStroke;}

    public void setIsSunStroke() {IsSunStroke = !IsSunStroke;}

    /**
     * We need this, so two teleport pairs cannot be crafted and set each other's mixed neighbors
     * @param givenTeleport
     */
    public void setPair(Teleport givenTeleport) {
        Pair = givenTeleport;
    }

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
    
    public void setOnAsteroid(Asteroid a)
    {
    	onAsteroid = a;
    }
    public Teleport getPair(){
        return Pair;
    }

	@Override 
	public boolean GetDrilled() {
		return false;
	}
	
	@Override
	public Material GetMined() {
		return null;
	}
}