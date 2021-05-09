package game_logic;

import view.RobotView;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Egy mesters�ges intelligencia �ltal ir�ny�tott Robot-ot reprezent�l az aszteroida�vben.
 */
public class Robot extends Movable {
	/**
	 * Random sz�m gener�l�shoz.
	 */
	Random rand = new Random();
	
	/**
	 * Konstruktor nev nelkul.
	 */
	public Robot(Asteroid position) {
		super(position);
		createMovableView();
	}

	/**
	 * Konstruktor nevvel.
	 */
	public Robot(String name, Asteroid position) {
		super(position);
		movablesName = name;
		createMovableView();
	}
	
	/**
	 * Robot-k�nt el�ri a robban�s, aminek hat�s�ra kap egy szomsz�dos aszteroid�t a FindNeighbor() f�ggv�ny seg�ts�g�vel
	 * �s oda �l�p� �t (AcceptPlayer()) majd lel�pteti mag�t a RemovePlayer() h�v�ssal.
	 */
	@Override
	public void HitByExplosion() {
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			Die();
		} else {
			Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
			Move(randomNeighbor);
		}
    }


	/**
	 * A robot egy egys�gnyivel m�ly�ti az aszteroida k�peny�be f�rt lyukat, ha az aszteroida
	 * m�r teljesen �t volt f�rva, akkor egy random szomsz�dos aszteroid�ra megy �t.
	 */
	@Override
    public void Step() {
		System.out.println("Robot step");
		if(((Asteroid)currentField).getThickness() == 0 && (currentField.FindNeighbor().size()>0)) {
			Vector<Field> neighbors = currentField.FindNeighbor();
			Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
		    Move(randomNeighbor);
		}
		else
		{
			Drill();
		}
    }

	/**
	 *  L�trehozza a Robot n�zet�t.
	 */
	@Override
	public void createMovableView()
	{
		int x = this.currentField.getFieldView().getPosx();
		int y = this.currentField.getFieldView().getPosy();
		try {
			this.movableView = new RobotView(x+60,y-60);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
