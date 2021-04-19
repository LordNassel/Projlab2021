package game_logic;

import java.util.Vector;


public class Goal_Asteroid extends Asteroid  {
	private boolean gamewin = false;
	private Vector<Material> GoalMaterials = new Vector<Material>();
	private Vector<Material> CurrentMaterials = new Vector<Material>();
	//Constructor it will need a list of materials, these materials if collected will win the game
	public Goal_Asteroid(String name, String M, Vector<Material> list) {
		super(name, M);
		System.out.println("Goal_Asteroid.Constructor Called");
		CurrentMaterials = list;
	}
	
	//Kap egy "felhalmozando" anyagot es elhelyezi a tarban, ellenorzi, hogy ettol komplett-e a lista
	public void CompleteMaterial(Material M) {
		System.out.println("CompleteMaterial Called");
		CurrentMaterials.add(M);
		gamewin = true;
	}
	
	public void storeInBaseAsteroid(Material m)
	{
		CurrentMaterials.add(m);
	}
	// ezt szerintem mergelni kene a completematerial fv-nyel
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
			Game.Wingame();
		else
			System.out.println("Nincs eleg nyersanyag meg a bazis felepitesehez");
	}
	
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
	
	//visszaadja a gamewin valtozot
	public boolean GetGamewin() {System.out.println("GetGamewin called"); return gamewin;}
}
