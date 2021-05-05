package game_logic;

import view.Specialframe;

import java.util.Vector;

/**
 *  Azt az aszteroidát reprezentálja, ahol a telepeseknek meg kell építeniük a bázist.
 *  Az osztály felelõssége ellenõrizni, hogy ehhez megvan-e az elegendõ mennyiségû nyersanyag.
 */
public class Goal_Asteroid extends Asteroid  {
	/**
	 *  Véget ért-e a játék.
	 */
	private boolean gamewin = false;
	/**
	 *  Az aszteroidán összegyûjtött nyersanyagok tárolója.
	 */
	private Vector<Material> CurrentMaterials = new Vector<Material>();
	/**
	 *  A konstruktor megkapja az összegyûjtendõ anyagok listáját.
	 */
	public Goal_Asteroid(String name, String M, Vector<Material> list) {
		super(name, M);
		CurrentMaterials = list;
	}

	/**
	 *  Kap egy "felhalmozando" anyagot es elhelyezi a tarban.
	 */
	public void CompleteMaterial(Material M) {
		CurrentMaterials.add(M);
		//gamewin = true;
	}

	/**
	 *  Bázis építése.
	 */
	public void BuildBase()
	{
		Uranium u = new Uranium();
		Coal coal = new Coal();
		Iron iron = new Iron();
		Ice ice = new Ice();
		int uran_db = getMaterialTypeNumber(u);
		int coal_db = getMaterialTypeNumber(coal);
		int iron_db = getMaterialTypeNumber(iron);
		int ice_db = getMaterialTypeNumber(ice);
		
		if(uran_db >= 3 && coal_db >= 3&& iron_db >= 3 && ice_db >=3)
		{
			gamewin = true;
			Game.Wingame();
			return;
		}
		else
			System.out.println("Nincs eleg nyersanyag meg a bazis felepitesehez");
	}

	/**
	 * Visszaadja, hogy hány material van a tárolóban az kapott tipusbol.
	 */
	public int getMaterialTypeNumber(Material m)
	{
		int number = 0;
		for(int i = 0; i<CurrentMaterials.size(); i++)
		{
			if(CurrentMaterials.get(i).getClass().equals( m.getClass()))
				number++;
		}
		return number;
	}

	/**
	 *  Visszaadja a gamewin valtozot.
	 */
	public boolean GetGamewin() { return gamewin;}
}
