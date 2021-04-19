package game_logic;

/**
 *  A vízjeget reprezentáló osztály. Napfénnyel érintkezve elolvad.
 */
public class Ice extends Material {

	/**
	 *  Vízjégként napfénnyel találkozik aminek hatására elszublimál.
	 */
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			who.GetMined();		
		}
	}
}
