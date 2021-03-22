package game_logic;

import java.util.*;
/**
 * This class specifies the variables and methods to be implemented for the Teleports
 */
public class  Teleport extends Field{
    private boolean IsActive;
    private Teleport Pair;
    protected Vector<Teleport> TeleportPair = new Vector<Teleport>();
    /**
     *Default Constructor
     */
    public Teleport(String name){
    	super(name);
        //setIsActive(false);
    	IsActive = false;
    }

    /**
     * Two-member teleport vector
     *     TODO:: How to set teleport as neighbors, cause Field neighbors are set already by func main OR!
     * @param WhichField
     */

    @Override
    public void SetNeighbor(Field WhichField){
        System.out.println("Field.SetnNeighbor Called To Set Teleport");
        Neighbors.add(WhichField);
    }

    /**
    * Getters and Setters
    */
    public boolean getIsActive(){
        return IsActive;
    }

    
    public void setIsActive(/*boolean isActive*/) { /* Miért adunk paraméterben át bármit is itt? -> a kerdes jogos nem kell, azonban amikor 
    elpusztul egy aszteroida, akkor false-ra kell majd tenni. emiatt a torzset valtoztattam */
        IsActive = !IsActive;
    }

    /**
     * We need this, so two teleport pairs cannot be crafted and set each other's mixed neighbors
     * @param givenTeleport
     */
    public void setPair(Teleport givenTeleport) {
        Pair = givenTeleport;
    }

    public Teleport getPair(){
        return Pair;
    }

	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}

	@Override //Ez itt nem TODO ez a teljes fgv elvart viselkedese
	public boolean GetDrilled() {
		return false;
	}
}