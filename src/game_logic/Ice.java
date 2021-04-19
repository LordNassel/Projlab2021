package game_logic;

// Ice, simple material
public class Ice extends Material {

	@Override
	// a felolvadas logikaja
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			//System.out.println("Ice disappears");
			who.GetMined();		
		}
	}
}
