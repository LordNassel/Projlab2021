package game_logic;

import java.util.Random;
import java.util.Vector;

// Represents the robot in the game
public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
	// A robot atmegy egy random szomszedos aszteroidara, ha tud
	// egyebkent meghal.
	private void MoveOrDie() {
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			Die();
		} else {
			Random rand = new Random();
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
		if(currentField.GetDrilled() == false) {
			MoveOrDie();
		}
    }
	
}
