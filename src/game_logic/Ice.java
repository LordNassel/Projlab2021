package game_logic;

/**
 *  A v�zjeget reprezent�l� oszt�ly. Napf�nnyel �rintkezve elolvad.
 */
public class Ice extends Material {

	/**
	 *  V�zj�gk�nt napf�nnyel tal�lkozik aminek hat�s�ra elszublim�l.
	 */
	public Ice(){
		name = "Ice";
	}


	@Override

	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			who.GetMined();		
		}
	}
}
