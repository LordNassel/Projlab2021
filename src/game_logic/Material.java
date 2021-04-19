package game_logic;

/**
 *  Az aszteroidákban megtalálható nyersanyagot reprezentálja. Kibányászás hatására valamilyen módon reagál.
 */
public abstract class Material {
	protected Asteroid asteroid;

	/**
	 *   A nyersanyag napfénnyel találkozik.
	 */
	public void GetExposed(boolean Sunside, Asteroid whoexposed) {
		
	}
}
