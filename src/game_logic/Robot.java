package game_logic;

import java.util.Random;
import java.util.Vector;

// Represents the robot in the game
public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
	// A robot atmegy egy random szomszedos aszteroidara
	private void MoveToRandomNeighbor() {
		Random rand = new Random();
		Vector<Field> neighbors = currentField.FindNeighbor();
	    Field randomNeighbor = neighbors.get(rand.nextInt(neighbors.size()));
	    Move(randomNeighbor);
	}
  
	@Override // A robot felrobbanas overrideja
	public void HitByExplosion() {
		System.out.println("Robot HitByExplosion() - landing on neighbor");
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			this.Die();
		} else {
			MoveToRandomNeighbor();
		}
    }

	@Override // Step funkcio overrideja
    public void Step() {
		if(currentField.GetDrilled() == false) {
			MoveToRandomNeighbor();
		}
    }
	
}
