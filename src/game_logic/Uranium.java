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
	 *  Konstruktor null�zza a sz�ml�l�t.
	 */
	public Uranium() {
		super();
		counter = 0;
	}
	/**
	 *  IncreaseUraniumCnt-hoz kell.
	 */
	public void IncreaseCounter() {
		counter++;
	}

	/**
	 *  Ur�niumk�nt napf�nnyel tal�lkozik ha napk�zelben van.
	 *  Ekkor a sz�ml�l� eggyel n�vekszik �s ha el�ri a h�rmat, akkor robban: megh�vja az aszteroida Explode() f�ggv�ny�t.
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
