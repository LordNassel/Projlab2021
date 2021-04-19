package game_logic;

import java.util.Random;
import java.util.Vector;

// Represents the robot in the game
public class Robot extends Movable {
	Random rand = new Random();
	
	//private boolean isDrilled = false;

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
		Vector<Field> neighbors = currentField.FindNeighbor(); //Kéne szûrni, hogy csak aszteroida legyen?
		if(neighbors.isEmpty()) {
			Die();
		} else {
		    Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
		    Move(randomNeighbor);
		}
	}
  
	@Override // A robot felrobbanas overrideja
	public void HitByExplosion() {
		MoveOrDie();
    }
	
	
	
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
