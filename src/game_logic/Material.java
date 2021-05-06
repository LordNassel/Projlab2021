package game_logic;

/**
 *  Az aszteroid�kban megtal�lhat� nyersanyagot reprezent�lja. Kib�ny�sz�s hat�s�ra valamilyen m�don reag�l.
 */
public abstract class Material {
	/**
	 *   A nyersanyag napf�nnyel tal�lkozik.
	 */
	//t�pusellen?rz�st elker�lend?
	String name;
	public void GetExposed(boolean Sunside, Asteroid whoexposed) {

	}
	public String getname() {return name;}
}
