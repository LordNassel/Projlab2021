package game_logic;

// Uranium, simple material
public class Uranium extends Material {
	
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			System.out.println("Radioactive asteroid exploded");
			who.Explode();
		}
	}

}
