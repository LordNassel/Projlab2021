package game_logic;

import java.util.ArrayList;
	import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Settler extends Movable {
	//Az iranyithato jatekos

	
	private List<Material> inventoryMain;
	
	private List<Teleport> inventoryTeleport;
	
	public Settler(Asteroid position)
	{
		super(position);
		System.out.println("Settler constructor called");
		inventoryMain = new ArrayList<Material>();
		inventoryTeleport = new ArrayList<Teleport>();
	}
	//Banyaszik a jatekos
	public void Mine()
	{
		System.out.println("Mine");
		Material minedMaterial=currentField.GetMined();
		this.Store(minedMaterial);
	}
	//Egy anyagot eltarol a bolygoban
	public void Store(Material material)
	{
		System.out.println("Store");
		inventoryMain.add(material);
	}
	

//robot letrehozasa
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
	//Teleport letrehozasa
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
			
			System.out.println("Teleport pair  created");
		}
		else
			System.out.println("Failed: Not enough materials");
	}
	
	public int getMaterialTypeNumber(Material m)
	{
		int number = 0;
		for(int i = 0; i<inventoryMain.size(); i++)
		{
			if(inventoryMain.get(i).getClass().equals( m.getClass()))
				number++;
		}
		return number;
	}
	//Aktival egy teleportot
	public void ActivateTeleport(Teleport teleport)
	{
		System.out.println("ActivateTeleport");
		teleport.setIsActive();			
		inventoryTeleport.remove(teleport);
	}
	// elhelyez anyagot a bolygoban
	public void PutMaterial(Material material)
	{
		System.out.println("PutMaterial");
		((Asteroid)currentField).StoreMaterial(material);
		inventoryMain.remove(material);
	}

	//Ez a gui-n keresztul ker utvonalvalasztast
	public void FindDirections() {
		Vector<Field> currentlist = new Vector<Field>();
		currentlist = this.currentField.FindNeighbor();
		System.out.println("Válaszd ki melyik szomszédos bolygóra akarsz utazni:");
		for(int i = 0; i<currentlist.size(); i++) {
			System.out.println(i + ". " + currentlist.get(i).Getname());
		}

		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		this.Move(currentlist.get(n));
	}

}
