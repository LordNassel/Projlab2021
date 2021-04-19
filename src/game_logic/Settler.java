package game_logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
		inventoryMain = new ArrayList<Material>();
		inventoryTeleport = new ArrayList<Teleport>();
	}
	
	public Settler(String name, Asteroid position)
	{
		super(position);
		movablesName = name;
		inventoryMain = new ArrayList<Material>();
		inventoryMain.clear();
		inventoryTeleport = new ArrayList<Teleport>();
	}
	//Banyaszik a jatekos
	public void Mine()
	{
		if(!isHidden)
		{
		Material minedMaterial = ((Asteroid)currentField).GetMined();
		if(minedMaterial != null)
			this.Store(minedMaterial);
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");
	}
	//Egy anyagot eltarol az inventoryban
	public void Store(Material material)
	{	
		inventoryMain.add(material);
	}
	

//robot letrehozasa
	public void CraftRobot()
	{
		if(!isHidden)
		{
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
			Game.AddSteppable(craftedRobot);
			inventoryMain.remove(coal);
			inventoryMain.remove(iron);
			inventoryMain.remove(uran);
			System.out.println("Robot created");
		}
		else
			System.out.println("Failed: not enoguh materials");
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");

	}
	//Teleport letrehozasa
	public void CraftTeleports()
	{
		if(!isHidden)
		{
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
			
			System.out.println("Teleport pair created");
		}
		else
			System.out.println("Failed: Not enough materials");
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");

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
		if(!isHidden)
		{
			teleport.setIsActive();
			((Asteroid)currentField).addTeleportOnAsteroid(teleport);
			teleport.SetNeighbor(currentField);
			teleport.setOnAsteroid(((Asteroid)currentField));
			inventoryTeleport.remove(teleport);
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");

	}
	
	public Teleport selectTeleportFromInventory()
	{
		if(inventoryTeleport.isEmpty())
		{
			System.out.println("Nincs nalad teleport");
			return null;
		}
		else
		{
			System.out.println("Valaszd ki melyik teleportot akarod akitv�lni: ");
			for(int i=0; i<inventoryTeleport.size();i++)
				System.out.println(i + ". " + inventoryTeleport.get(i).Getname());
			Scanner myinput = new Scanner(System.in);
			int input = myinput.nextInt();
			return inventoryTeleport.get(input);
		}
	}
	
	// elhelyez anyagot a bolygoban
	public void PutMaterial(Material material)
	{
		if(!isHidden)
		{
		((Asteroid)currentField).StoreMaterial(material);
		inventoryMain.remove(material);
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");

	}
	
	public Material selectMaterialToPut()
	{
		if(inventoryMain.isEmpty())
		{
			System.out.println("A nyersanyag tarolo ures");
			return null;
		}
		else
		{
			System.out.println("Valasz ki melyik nyersanyagot szeretned eltarolni:");
			for(int i=0; i<inventoryMain.size(); i++)
				System.out.println(i + ". " + inventoryMain.get(i));
			Scanner myinput = new Scanner(System.in);
			int input = myinput.nextInt();
			return inventoryMain.get(input);
		}
	}

	//Ez a gui-n keresztul ker utvonalvalasztast
	public Field FindDirections() {
		Vector<Field> currentlist = new Vector<Field>();
		currentlist = this.currentField.FindNeighbor();
		if(currentlist.isEmpty())
		{
			System.out.println("Nincs szomszedos aszteroida");
			return null;
		}
		else {
		System.out.println("V�lasz egy teleportot vagy szomsz�dos bolgy�t az utaz�shoz:");
		for(int i = 0; i<currentlist.size(); i++) {
			System.out.println(i + ". " + currentlist.get(i).Getname());
		}

		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return currentlist.get(n);
		}
	}
	
	public String getSettlerName()
	{
		return movablesName;
	}
	
	public void Build()
	{
		((Goal_Asteroid)currentField).BuildBase();
	}
	
	public void Step()
	{
		System.out.println("\nAz akutalis jatekos: " + movablesName);
		System.out.println("A jelenlegi poziciod: " + this.GetCurrentField().Getname() + " aszteroida");
		System.out.println("Mit szeretn�l csin�lni?");
		System.out.println("1. Mozgas");
		System.out.println("2. Furas");
		System.out.println("3. Bujas");
		System.out.println("4. Banyaszas");
		System.out.println("5. Nyersanyag tarolas aszteroidaban");
		System.out.println("6. Teleport lerakas");
		System.out.println("7. Robot craftolas");
		System.out.println("8. Teleport par craftolas");
		System.out.println("9. Bazis felepites");

		Scanner myinput = new Scanner(System.in);
		int valasz = myinput.nextInt(); // TO-DO: Ha bezarod akkor a System.in-is amit nem tudunk ujra megnyitni
		
		switch(valasz)
		{
		case 1:
			Field targetField = FindDirections();
			Move(targetField);
			break;
		case 2:
			Drill();
			break;
		case 3:
			Hide();
			break;
		case 4:
			Mine();
			break;
		case 5:
			Material selectedMaterial = selectMaterialToPut();
			if(selectedMaterial != null)
				PutMaterial(selectedMaterial);
			break;
		case 6:
			Teleport selected = selectTeleportFromInventory();
			if(selected != null)
				ActivateTeleport(selected);
			break;
		case 7:
			CraftRobot();
			break;
		case 8:
			CraftTeleports();
			break;
		case 9:
			if(currentField.getClass() == Goal_Asteroid.class)
				Build();
			else 
				System.out.println("Hiba, nem a cel aszteroidan vagy");
			break;
		default:
			break;
		}
	}
	
	public void addTelportToInventory(Teleport t)
	{
		inventoryTeleport.add(t);
	}

}
