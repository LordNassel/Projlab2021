package game_logic;

/**
 * Egy interfész, ami minden olyan dolgot reprezentál, amely „lépni” tud.
 */
public interface Steppable {

    /**
     * Az adott lépésben végrehajtandó lépés.
     */
	void Step();
}