package game_logic;


import java.util.Set;
//import java.util.Vector;



import game_logic.Asteroid;
import game_logic.Material;
import game_logic.Game;

public class Goal_Asteroid extends Asteroid  {
	private Set<Material> GoalMaterials = new Set<Material>();
	private Set<Material> CurrentMaterials = new Set<Material>();
	//Constructor itt will need a list of materials, these materials if collected will win the game
	Goal_Asteroid(Set<Material> list){
		CurrentMaterials = list;
	}
	
	public void CompleteMaterial(Material M) {
		CurrentMaterials.add(M);
		if(GoalMaterials.equals(CurrentMaterials))
			Game.Wingame();
	}
}
