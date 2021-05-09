package game_logic;

/**
 *  Az aszteroidákban megtalálható nyersanyagot reprezentálja. Kibányászás hatására valamilyen módon reagál.
 */
public abstract class Material {

	/**
	 *  A nyersanyag neve.
	 */
	String name;

	/**
	 *  A nyersanyag napfénnyel találkozik.
	 */
	public void GetExposed(boolean Sunside, Asteroid whoexposed) {}

	/**
	 *  Visszaadja a nyersanyag nevét.
	 */
	public String getname() {return name;}
}