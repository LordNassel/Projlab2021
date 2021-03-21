package game_logic;

import java.util.Vector;

// A robotot reprezentálja a játékban.
public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
	/* 
	A robot robbanás hatására szomszédos aszteroidán landol,
 	ha nem volt szomszéd, a robot meghal.
	*/  
	@Override
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
