package game_logic;

public class Uranium extends Material {
	
	public void GetExposed(boolean Sunside) {
		if(Sunside && asteroid.getThickness() == 0) {
			asteroid.Explode();
		}
	}

}