package game_logic;

/**
 *  A v�zjeget reprezent�l� oszt�ly. Napf�nnyel �rintkezve elszublim�l.
 */
public class Ice extends Material {

	/**
	 *  Nevet kap a konstruktorban.
	 */
	public Ice(){
		name = "Ice";
	}

	/**
	 *  V�zj�gk�nt napf�nnyel tal�lkozik, aminek hat�s�ra elszublim�l.
	 */
	@Override
	public void GetExposed(boolean Sunside, Asteroid who) {
		if(Sunside) {
			who.GetMined();		
		}
	}
}
