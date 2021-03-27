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
    protected Field currentField;
    /**
     *Default Constructor
     */
    public Teleport(String name, Field onField){
    	super(name);
    	IsActive = false;
    	IsSunStroke = false;
        currentField = onField;
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

    public void HitBySunStorm(){
        if(IsSunStroke == false){
         setIsSunStroke();
        }
    }
    /*
    @Override
    public void Move(Field a)
    {
        System.out.println("Move()");
        this.currentField.RemovePlayer(this);
        a.AcceptPlayer(this);

    }

    @Override
    public void MoveToRandomNeighbor() {
        Random rand = new Random();
        Vector<Field> neighbors = currentField.FindNeighbor();
        Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
        Move(randomNeighbor);
    }*/

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

    public Teleport getPair(){
        return Pair;
    }

	@Override
	public void Step() {
		
	}

	@Override 
	public boolean GetDrilled() {
		return false;
	}
}