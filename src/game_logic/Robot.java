package game_logic;

import java.util.Vector;

public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
	public void HitByExplosion() {
		System.out.println("Robot HitByExplosion()");
		Vector<Field> neighbors = currentField.FindNeighbor();
		currentField.RemovePlayer(this);
		neighbors.get(0).AcceptPlayer(this);
    }

}
