package game_logic;

import java.util.Vector;

// A robotot reprezent�lja a j�t�kban.
public class Robot extends Movable {

	public Robot(Asteroid position) {
		super(position);
	}
	
	/* 
	A robot robban�s hat�s�ra szomsz�dos aszteroid�n landol,
 	ha nem volt szomsz�d, a robot meghal.
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
