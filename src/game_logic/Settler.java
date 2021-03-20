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
		//TO-DO opt check
		Robot craftedRobot = new Robot((Asteroid)currentField);
		currentField.AcceptPlayer(craftedRobot);
	}
	
	public void CraftTeleports()
	{
		System.out.println("CraftTeleports");
		//TO-DO opt check
		Teleport t1 = new Teleport();
		Teleport t2 = new Teleport();
		t1.setPair(t2);
		t2.setPair(t1);
		inventoryTeleport.add(t1);
		inventoryTeleport.add(t2);
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
