package game_logic;

import java.util.Random;
import java.util.Vector;

// Represents the robot in the game
public class Robot extends Movable {
	Random rand = new Random();
	private boolean isDrilled = false;
	
	//konstruktor nev nelkul
	public Robot(Asteroid position) {
		super(position);
	}
	//konstruktor nevvel
	public Robot(String name, Asteroid position) {
		super(position);
		movablesName = name;
	}
	
	// A robot atmegy egy random szomszedos aszteroidara, ha tud
	// egyebkent meghal.
	private void MoveOrDie() {
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			Die();
		} else {
		    Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
		    Move(randomNeighbor);
		}
	}
  
	@Override // A robot felrobbanas overrideja
	public void HitByExplosion() {
		System.out.println("Robot HitByExplosion() - landing on neighbor");
		MoveOrDie();
    }

	@Override // Step funkcio overrideja
    public void Step() {
		if(((Asteroid)currentField).getThickness() == 0) {
	    	System.out.println("Robot vagyok és léptem");

			Vector<Field> neighbors = currentField.FindNeighbor();
			Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
		    Move(randomNeighbor);
		}
		else
		{
			Drill();
	    	System.out.println("Robot vagyok és fúrtam");
		}
    }
	//estere most nincs jobb otlet majd reggel
	public void Mine() {}
	
}
