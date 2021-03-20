package game_logic;

import java.util.*

/**
 * This interface specifies the methods to be implemented for the step strategies
 */

public interface Steppable {
    /**
     * Performs tasks required to run the strategy
     *
     * @param m        - The Map (only one)
     * @param g        - The Game (only one)
     * @param movables - The list of Movables
     */

 public void Step() {
        //void execute (map.Map m){};
        //void execute (game.Game g){};
        //void execute (ArrayList < movable.Movable > movables){};
    }
}