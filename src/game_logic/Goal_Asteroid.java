package game_logic;

import view.Specialframe;

import java.util.Vector;

/**
 *  Azt az aszteroid�t reprezent�lja, ahol a telepeseknek meg kell �p�teni�k a b�zist.
 *  Az oszt�ly felel�ss�ge ellen�rizni, hogy ehhez megvan-e az elegend� mennyis�g� nyersanyag.
 */
public class Goal_Asteroid extends Asteroid  {
	/**
	 *  V�get �rt-e a j�t�k.
	 */
	private boolean gamewin = false;
	/**
	 *  Az aszteroid�n �sszegy�jt�tt nyersanyagok t�rol�ja.
	 */
	private Vector<Material> CurrentMaterials = new Vector<Material>();
	/**
	 *  A konstruktor megkapja az �sszegy�jtend� anyagok list�j�t.
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
	 *  B�zis �p�t�se.
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
	 * Visszaadja, hogy h�ny material van a t�rol�ban az kapott tipusbol.
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
