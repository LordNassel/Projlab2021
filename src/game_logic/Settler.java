package game_logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
	import java.util.List;
import java.util.Scanner;
import java.util.Vector;
/**
 *   A játékosok által irányítható telepeseket reprezentálja.
 *   Nyersanyagot gyûjtenek és robotot vagy teleport kaput képesek craftolni.
 */
public class Settler extends Movable {

	/**
	 *   A telepes által gyûjtött nyersanyagokat tartalmazza. Egyszerre maximum 10 nyersanyag lehet nála.
	 */
	private List<Material> inventoryMain;

	/**
	 *   A telepes által birtokolt teleportokat tartalmazza. Maximum 3 lehet nála egyszerre.
	 */
	private List<Teleport> inventoryTeleport;

	/**
	 * Konstruktor nev nelkul.
	 */
	public Settler(Asteroid position)
	{
		super(position);
		inventoryMain = new ArrayList<Material>();
		inventoryTeleport = new ArrayList<Teleport>();
	}

	/**
	 * Konstruktor nevvel.
	 */
	public Settler(String name, Asteroid position)
	{
		super(position);
		movablesName = name;
		inventoryMain = new ArrayList<Material>();
		inventoryMain.clear();
		inventoryTeleport = new ArrayList<Teleport>();
	}

	/**
	 * A telepes bányászik aminek hatására meghívja az aszteroida GetMined() függvényét ami egy egység nyersanyaggal tér vissza ha sikerrel járt.
	 * Ekkor a kibányászott nyersanyagot eltárolja a material inventoryba a Store() fgv. hívással.
	 */
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

	/**
	 * A kibányászott nyersanyagot eltárolja a material inventoryban ha van hely
	 */
	public void Store(Material material)
	{
		if(inventoryMain.size()<10)
			inventoryMain.add(material);
		else
		{
			System.out.println("Hiba: A tarhely tele van");
			return;
		}
	}

	/**
	 *  Egy-egy egység vas, szén és urán felhasználásával Robot-ot hoz létre ha van elég nyersanyag nála.
	 *  Ha elkészült, akkor a telepes rárakja az aktuális aszteroidára az Accept() meghívásával.
	 */
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

	/**
	 * Egy új teleport párt hoz létre 2 egység vízjég, 2 egység vas és 1 egység urán felhasználásával, ha van elég nyersanyag nála.
	 * Ha elkészült a telepes beállítja a teleportokat egymás párjukként a setPair() fgv. hívással
	 * és eltárolja õket a teleport inventoryba (lista beépített add() használatával)
	 */
	public void CraftTeleports(String name1, String name2)
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
			Teleport t1 = new Teleport(name1);
			Teleport t2 = new Teleport(name2);
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

	/**
	 *   Egy segéd metódus ami visszaadja, hogy a paraméterül kapott nyersanyag típusból mennyi van tárolva telepesnél.
	 */
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

	/**
	 *   Egy új teleportot aktivál az aszteroida övben, vagyis a teleport isActive setterét meghívja.
	 */
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

	/**
	 *   Egy  teleportot lehet vele kiválasztani a teleport inventory-ból.
	 */
	public Teleport selectTeleportFromInventory()
	{
		if(inventoryTeleport.isEmpty())
		{
			System.out.println("Nincs nalad teleport");
			return null;
		}
		else
		{
			System.out.println("Valaszd ki melyik teleportot akarod akitválni: ");
			for(int i=0; i<inventoryTeleport.size();i++)
				System.out.println(i + ". " + inventoryTeleport.get(i).Getname());
			Scanner myinput = new Scanner(System.in);
			int input = myinput.nextInt();
			return inventoryTeleport.get(input);
		}
	}
	
	/**
	 *   Elhelyez egy egységnyi nyersanyagot az aszteroidába.
	 *   Csak akkor siekres ha teljesen üres vagy pedig olyan nyersanyag van már benne amilyen tipusút szeretnék elraktározni.
	 */
	public void PutMaterial(Material material)
	{
		if(!isHidden && ((Asteroid)currentField).StoreMaterial(material))
		{
		//((Asteroid)currentField).StoreMaterial(material);
		inventoryMain.remove(material);
		}
		else
			System.out.println("Sikertelen: Elobb buj elo a muvelet elvegzesehez");

	}

	/**
	 *   Kivalasztható vele, hogy melyik nyersanyagot kell eltárolni.
	 */
	public Material selectMaterialToPut()
	{
		if(inventoryMain.isEmpty())
		{
			System.out.println("A nyersanyag tarolo ures");
			return null;
		}
		else
		{
			System.out.println("Valaszd ki melyik nyersanyagot szeretned eltarolni:");
			for(int i=0; i<inventoryMain.size(); i++)
				System.out.println(i + ". " + inventoryMain.get(i));
			Scanner myinput = new Scanner(System.in);
			int input = myinput.nextInt();
			return inventoryMain.get(input);
		}
	}

	/**
	 *   Ez a gui-n keresztul ker utvonalvalasztast.
	 */
	public Field FindDirections() {
		Vector<Field> currentlist = new Vector<Field>();
		currentlist = this.currentField.FindNeighbor();
		if(currentlist.isEmpty())
		{
			System.out.println("Nincs szomszedos aszteroida");
			return null;
		}
		else {
		System.out.println("Válasz egy teleportot vagy szomszédos bolgyót az utazáshoz:");
		for(int i = 0; i<currentlist.size(); i++) {
			System.out.println(i + ". " + currentlist.get(i).Getname());
		}

		Scanner myinput =new Scanner(System.in);
		int n=0;
		n= myinput.nextInt();
		return currentlist.get(n);
		}
	}
	
	/**
	 * Base aszteroidan eltarol egy egyseg nyersanyagot
	 * @param m
	 */
	public void storeOnBaseMaterial(Material m)
	{
		if(this.currentField.getClass() == Goal_Asteroid.class)
		{
			((Goal_Asteroid)currentField).CompleteMaterial(m);
			inventoryMain.remove(m);
		}
		else
			System.out.println("Hiba: Nem a cel aszteroidan vagy");
	}

	/**
	 *   Visszaadja a telepes nevét.
	 */
	public String getSettlerName()
	{
		return movablesName;
	}

	/**
	 *   A telepes megépíti a bázist.
	 */
	public void Build()
	{
		((Goal_Asteroid)currentField).BuildBase();
	}

	/**
	 *   Lép a telepes.
	 */
	public void Step()
	{
		System.out.println("\nAz akutalis jatekos: " + movablesName);
		System.out.println("A jelenlegi poziciod: " + this.GetCurrentField().Getname() + " aszteroida");
		System.out.println("Mit szeretnél csinálni?");
		System.out.println("1. Mozgas");
		System.out.println("2. Furas");
		System.out.println("3. Bujas");
		System.out.println("4. Banyaszas");
		System.out.println("5. Nyersanyag tarolas aszteroidaban");
		System.out.println("6. Teleport lerakas");
		System.out.println("7. Robot craftolas");
		System.out.println("8. Teleport par craftolas");
		System.out.println("9. Nyersanyag tarolasa cel aszteroidan");
		System.out.println("10. Bazis felepites");

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
			CraftTeleports("Teleport1", "Teleport2");  //TO-DO név beolvasása
			break;
		case 9:
			Material mat = selectMaterialToPut();
			if(mat!=null)
				storeOnBaseMaterial(mat);
		case 10:
			if(currentField.getClass() == Goal_Asteroid.class)
				Build();
			else 
				System.out.println("Hiba, nem a cel aszteroidan vagy");
			break;
		default:
			break;
		}
	}

	/**
	 *   Eltárol egy teleportot az inventoryban.
	 */
	public void addTelportToInventory(Teleport t)
	{
		inventoryTeleport.add(t);
	}
	
	public List<Material> GetInventory_DEBUG(){return this.inventoryMain ;}
	
	public List<Teleport> getTeleportInventory()
	{
		return inventoryTeleport;
	}

}
