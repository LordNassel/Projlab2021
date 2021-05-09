package game_logic;

/**
 *  Az uránt reprezentáló osztály. A 3. expozíció hatására felrobban.
 */
public class Uranium extends Material {

	/**
	 *  Urán expozíciónak száma.
	 */
	private int counter;

	/**
	 *  Konstruktor nullázza a számlálót, valamint nevet kap.
	 */
	public Uranium() {
		super();
		counter = 0;
		name = "Uranium";
	}
	/**
	 *  Növeli az expozíciók számát.
	 */
	public void IncreaseCounter() {
		counter++;
	}

	/**
	 *  Ha az urán napközelben van, és napfénnyel találkozik,
	 *  akkor a számláló eggyel növekszik, és ha eléri a hármat, akkor robban: meghívja az aszteroida Explode() függvényét.
	 */
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			IncreaseCounter();
		}
		
		if(counter == 3) {
			System.out.println("Radioactive uranium exploded");
			who.Explode();
		}
	}

}
