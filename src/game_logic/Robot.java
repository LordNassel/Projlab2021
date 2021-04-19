package game_logic;

import java.util.Random;
import java.util.Vector;

/**
 * Egy mesters�ges intelligencia �ltal ir�ny�tott Robot-ot reprezent�l az aszteroida�vben.
 *
 */
public class Robot extends Movable {
	Random rand = new Random();
	
	//private boolean isDrilled = false;

	/**
	 * Konstruktor nev nelkul.
	 *
	 */
	public Robot(Asteroid position) {
		super(position);
	}
	
	// A robot atmegy egy random szomszedos aszteroidara, ha tud
	// egyebkent meghal.
	private void MoveOrDie() {
		Vector<Field> neighbors = currentField.FindNeighbor(); //K�ne sz�rni, hogy csak aszteroida legyen?
	}

	/**
	 * Konstruktor nevvel.
	 *
	 */
	public Robot(String name, Asteroid position) {
		super(position);
		movablesName = name;
	}

	/**
	 * Robot-k�nt el�ri a robban�s, aminek hat�s�ra kap egy szomsz�dos aszteroid�t a FindNeighbor() f�ggv�ny seg�ts�g�vel
	 * �s oda �l�p� �t (AcceptPlayer()) majd lel�pteti mag�t a RemovePlayer() h�v�ssal.
	 *
	 */
	@Override
	public void HitByExplosion() {
		System.out.println("Robot HitByExplosion() - landing on neighbor");
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			Die();
		} else {
			Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
			Move(randomNeighbor);
		}
    }

	/**
	 *  A robot egy egys�gnyivel m�ly�ti az aszteroida k�peny�be f�rt lyukat, ha az aszteroida
	 * m�r teljesen �t volt f�rva, akkor egy random szomsz�dos aszteroid�ra megy �t.
	 *
	 */
	@Override
    public void Step() {
		if(((Asteroid)currentField).getThickness() == 0) {
			Vector<Field> neighbors = currentField.FindNeighbor();
			Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
		    Move(randomNeighbor);
		}
		else
		{
			Drill();
		}
    }


	
}
