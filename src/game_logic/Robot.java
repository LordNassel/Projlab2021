package game_logic;

import view.RobotView;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;

/**
 * Egy mesterséges intelligencia által irányított Robot-ot reprezentál az aszteroidaövben.
 */
public class Robot extends Movable {
	/**
	 * Random szám generáláshoz.
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
	 * Robot-ként eléri a robbanás, aminek hatására kap egy szomszédos aszteroidát a FindNeighbor() függvény segítségével
	 * és oda “lép” át (AcceptPlayer()) majd lelépteti magát a RemovePlayer() hívással.
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
	 * A robot egy egységnyivel mélyíti az aszteroida köpenyébe fúrt lyukat, ha az aszteroida
	 * már teljesen át volt fúrva, akkor egy random szomszédos aszteroidára megy át.
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
	 *  Létrehozza a Robot nézetét.
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
