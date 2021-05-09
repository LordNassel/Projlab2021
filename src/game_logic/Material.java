package game_logic;

/**
 *  Az aszteroid�kban megtal�lhat� nyersanyagot reprezent�lja. Kib�ny�sz�s hat�s�ra valamilyen m�don reag�l.
 */
public abstract class Material {

	/**
	 *  A nyersanyag neve.
	 */
	String name;

	/**
	 *  A nyersanyag napf�nnyel tal�lkozik.
	 */
	public void GetExposed(boolean Sunside, Asteroid whoexposed) {}

	/**
	 *  Visszaadja a nyersanyag nev�t.
	 */
	public String getname() {return name;}
}