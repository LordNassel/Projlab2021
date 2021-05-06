package game_logic;

/**
 *  Az aszteroidákban megtalálható nyersanyagot reprezentálja. Kibányászás hatására valamilyen módon reagál.
 */
public abstract class Material {
	/**
	 *   A nyersanyag napfénnyel találkozik.
	 */
	//típusellen?rzést elkerülend?
	String name;
	public void GetExposed(boolean Sunside, Asteroid whoexposed) {

	}
	public String getname() {return name;}
}
