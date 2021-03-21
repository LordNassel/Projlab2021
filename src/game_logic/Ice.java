package game_logic;


public class Ice extends Material {

	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			System.out.println("Ice disappears");
			who.GetMined();		// Legyen kilon fgv?
		}
	}
}
