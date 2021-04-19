package game_logic;

import java.util.*;

/**
 * Egy interfész, ami minden olyan dolgot reprezentál, amely „lépni” tud.
 */
public interface Steppable {

    /**
     * Az adott lépésben végrehajtandó lépés.
     */
	public void Step();
}