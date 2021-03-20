package game_logic;


//import java.util.Vector;
import java.util.Vector;


public class Goal_Asteroid extends Asteroid  {
	private boolean gamewin = false;
	private Vector<Material> GoalMaterials = new Vector<Material>();
	private Vector<Material> CurrentMaterials = new Vector<Material>();
	//Constructor itt will need a list of materials, these materials if collected will win the game
	Goal_Asteroid(Material M, Vector<Material> list) {
		super(M);
		System.out.println("Goal_Asteroid.Constructor Called");
		GoalMaterials = list;
	}
	
	//Kap egy "felhalmozando" anyagot es elhelyezi a tarban, ellenorzi, hogy ettol komplett-e a lista
	public void CompleteMaterial(Material M) {
		System.out.println("CompleteMaterial Called");
		CurrentMaterials.add(M);
		gamewin = true;
	}
	
	//visszaadja a gamewin valtozot
	public boolean GetGamewin() {System.out.println("GetGamewin called"); return gamewin;}
}
