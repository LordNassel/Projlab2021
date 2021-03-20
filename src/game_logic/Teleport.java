package game_logic;


/**
 * This class specifies the variables and methods to be implemented for the Teleports
 */
public class Teleport extends Field{
    private boolean IsActive;
    private Teleport TeleportPair;
    protected Vector<Teleport> TeleportPair = new Vector<Teleport>();
    /**
     *Default Constructor
     */
    public Teleport(){
        setIsActive(false);
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
    @Override
    public bool getActive(){
        return IsActive;
    }

    
    public void setActive(boolean isActive) {
        IsActive = isActive;
    }

    /**
     * We need this, so two teleport pairs cannot be crafted and set each other's mixed neighbors
     * @param givenTeleport
     */
    @Override
    public void setPair(Teleport givenTeleport) {
        TeleportPair = givenTeleport;
    }

    @Override
    public Teleport getPair(){
        return TeleportPair;
    }
}