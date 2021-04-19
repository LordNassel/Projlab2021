package game_logic;

import java.util.*;

/**
 * Egy interf�sz, ami minden olyan dolgot reprezent�l, amely �l�pni� tud.
 */
public interface Steppable {

    /**
     * Az adott l�p�sben v�grehajtand� l�p�s.
     */
	public void Step();
}