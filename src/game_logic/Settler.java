package game_logic;

import java.util.ArrayList;
	import java.util.List;

public class Settler extends Movable {
	
	private int radiation;
	
	private List<Material> inventoryMain;
	
	private List<Teleport> inventoryTeleport;
	
	public Settler(Asteroid position)
	{
		super(position);
		System.out.println("Settler constructor called");
		inventoryMain = new ArrayList<Material>();
		inventoryTeleport = new ArrayList<Teleport>();
	}
	
	public void Mine()
	{
		System.out.println("Mine");
		//TO-DO opt check
		Material minedMaterial=((Asteroid)currentField).GetMined();
		this.Store(minedMaterial);
	}
	
	public void Store(Material material)
	{
		System.out.println("Store");
		inventoryMain.add(material);
	}
	
	/*public void GetMaterail(Material material)
	{
		
	}*/
	
	public void CraftRobot()
	{
		System.out.println("CraftRobot");

		Coal coal = new Coal();
		Iron iron = new Iron();
		Uranium uran = new Uranium();
		
		int ncoal = getMaterialTypeNumber(coal);
		int niron = getMaterialTypeNumber(iron);
		int nuran = getMaterialTypeNumber(uran);
		if(ncoal>=1 && niron >= 1 && nuran >=1)
		{
			Robot craftedRobot = new Robot((Asteroid)currentField);
			currentField.AcceptPlayer(craftedRobot);
			inventoryMain.remove(coal);
			inventoryMain.remove(iron);
			inventoryMain.remove(uran);
			System.out.println("Robot created");
		}
		else
			System.out.println("Failed: not enoguh materials");
	}
	
	public void CraftTeleports()
	{
		System.out.println("CraftTeleports");

		int niron, nuran, nice;
		Iron iron = new Iron();
		Ice ice = new Ice();
		Uranium uranium = new Uranium();
		niron = getMaterialTypeNumber(iron);
		nuran = getMaterialTypeNumber(uranium);
		nice = getMaterialTypeNumber(ice);
		
		if(niron >=2 && nice >= 2 && nuran >=1)
		{
			Teleport t1 = new Teleport("name");
			Teleport t2 = new Teleport("name2");
			t1.setPair(t2);
			t2.setPair(t1);
			inventoryTeleport.add(t1);
			inventoryTeleport.add(t2);
			
			inventoryMain.remove(iron);
			inventoryMain.remove(iron);
			inventoryMain.remove(ice);
			inventoryMain.remove(ice);
			inventoryMain.remove(uranium);			
		}
	}
	
	public int getMaterialTypeNumber(Material m)
	{
		int number = 0;
		for(int i = 0; i<inventoryMain.size(); i++)
		{
			if(inventoryMain.get(i).getClass().equals( m.getClass()))
				//System.out.println( m.getClass().toString());
				number++;
			/*else
				//System.out.println("Nincs ilyen nyeranyag tarolva");
				get = false;*/
		}
		return number;
	}
	
	public void ActivateTeleport(Teleport teleport)
	{
		System.out.println("ActivateTeleport");
		teleport.setIsActive();			//Sry, blejavítottam egy true-val - Lévai Gábor 03.20 15.30
		inventoryTeleport.remove(teleport);
	}
	
	public void PutMaterial(Material material)
	{
		System.out.println("PutMaterial");
		//TO-DO opt check
		((Asteroid)currentField).StoreMaterial(material);
		inventoryMain.remove(material);
	}
	
	

}
