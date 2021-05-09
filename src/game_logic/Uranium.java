package game_logic;

/**
 *  Az ur�nt reprezent�l� oszt�ly. A 3. expoz�ci� hat�s�ra felrobban.
 */
public class Uranium extends Material {

	/**
	 *  Ur�n expoz�ci�nak sz�ma.
	 */
	private int counter;

	/**
	 *  Konstruktor null�zza a sz�ml�l�t, valamint nevet kap.
	 */
	public Uranium() {
		super();
		counter = 0;
		name = "Uranium";
	}
	/**
	 *  N�veli az expoz�ci�k sz�m�t.
	 */
	public void IncreaseCounter() {
		counter++;
	}

	/**
	 *  Ha az ur�n napk�zelben van, �s napf�nnyel tal�lkozik,
	 *  akkor a sz�ml�l� eggyel n�vekszik, �s ha el�ri a h�rmat, akkor robban: megh�vja az aszteroida Explode() f�ggv�ny�t.
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
