package game_logic;

import java.util.Vector;

// Represents the robot in te game
public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
  
	@Override // A robot felrobbanas overrideja
	public void HitByExplosion() {
		System.out.println("Robot HitByExplosion() - landing on neighbor");
		Vector<Field> neighbors = currentField.FindNeighbor();
		if(neighbors.isEmpty()) {
			this.Die();
		} else {
			this.Move(neighbors.get(0));
		}
		
    }

}
