package game_logic;

/**
 *  A vízjeget reprezentáló osztály. Napfénnyel érintkezve elszublimál.
 */
public class Ice extends Material {

	/**
	 *  Nevet kap a konstruktorban.
	 */
	public Ice(){
		name = "Ice";
	}

	/**
	 *  Vízjégként napfénnyel találkozik, aminek hatására elszublimál.
	 */
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			who.GetMined();		
		}
	}
}
