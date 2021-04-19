package game_logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Asteroid extends Field{
	//Attributes: Is the Asteroid sunside, and the value of the crust thickness
	private boolean isSunside;
	private int Thickness;
	//This is a bit magic number-ish, all asteroids store 3 units of material
	private List<Material> CoreMaterial = new ArrayList<Material>(3);
	//This is the default constructor, this may however change. Right now it creates the random value for the thickness
	private List<Teleport> teleportOnAsteroid = new ArrayList<Teleport>();
	
	public Asteroid(String name, String M) {
		super(name);
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		setAsteroidCore(M);

	}
	//Default empty constructor
	public Asteroid (String name){
			super(name);
			Random thicknessgen = new Random();
			Thickness = thicknessgen.nextInt(11);
			CoreMaterial.clear();
		}

	//sunside constructor
	public Asteroid(String name, String M, boolean isSunside) {
		super(name);
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		setAsteroidCore(M);


		this.isSunside = isSunside;
	}
	//EZ EGY DEBUG KONSTRUKTOR, NE HIVD NORMALIS UZEMBEN
	public Asteroid(String name, String M, boolean sunside, int thiccboi) {
		super(name);
		int n=0;
		
		setAsteroidCore(M);
		
		Thickness = thiccboi;
		this.isSunside =sunside;
	}
	//Operations
	
	@Override//The drilling function. 
	public boolean GetDrilled(){
		if(Thickness>0) 
			Thickness--;
		else
			return false;
		return true;
		
	}

	
	//The mining function
	public Material GetMined(){		
	//ha az aszteroida nem ures	
		if(!CoreMaterial.isEmpty())
		{
			CoreMaterial.get(0).GetExposed(isSunside, this);
			Material mined = CoreMaterial.get(0);
			CoreMaterial.remove(0);
			return mined;
		}
		else
		{
			System.out.println("Error: The asteroid is empty\n");
			return null;
			
		}
	}
	
	
	
	//The sunstorm handler. It is similar to all asteroid types
	public boolean StoreMaterial(Material M) {
		
		if(CoreMaterial.size()<3 && CoreMaterial.size() > 0 && CoreMaterial.get(0).equals(M)) // Ha van benne de nincs tele maradjon homogen
		{
			CoreMaterial.add(M);
			return true;
		}
		else if(CoreMaterial.isEmpty()) //Ha ures akkor nem szamit milyet teszunk be
		{
			CoreMaterial.add(M);
			return true;
		}
		else
		{
			System.out.println("The materail cannot be stored\n");
			return false;
		}
	}
	
	//Hide in the asteroid
	public void GetHidden(Movable M) {
		
		if(CoreMaterial.isEmpty())
		{
			M.SetIsHidden();
			System.out.println("Success\n");
		}
		else
			System.out.println("Asteroid is not empty to hide\n");
	}

	public int getThickness()
	{
		return Thickness;
	}
	
	public void addTeleportOnAsteroid(Teleport t)
	{
		teleportOnAsteroid.add(t);
		this.SetNeighbor(t);
	}
	
	public void removeTeleportOnAsteroi(Teleport t)
	{
		teleportOnAsteroid.remove(t);
		this.Neighbors.remove(t);
	}
	
	public List<Teleport> getTeleportsOnAsteroid()
	{
		return teleportOnAsteroid;
	}
	
	public void setAsteroidCore(String whichmaterial)
	{
		switch(whichmaterial)
		{
		case "Uranium":
			Uranium u1 = new Uranium();
			Uranium u2 = new Uranium();
			Uranium u3 = new Uranium();
			CoreMaterial.add(u1);
			CoreMaterial.add(u2);
			CoreMaterial.add(u3);
			break;
		case "Iron":
			Iron i1 = new Iron();
			Iron i2 = new Iron();
			Iron i3 = new Iron();
			CoreMaterial.add(i1);
			CoreMaterial.add(i2);
			CoreMaterial.add(i3);
			break;
		case "Coal":
			Coal c1 = new Coal();
			Coal c2 = new Coal();
			Coal c3 = new Coal();
			CoreMaterial.add(c1);
			CoreMaterial.add(c2);
			CoreMaterial.add(c3);
			break;
		case "Ice":
			Ice ic1 = new Ice();
			Ice ic2 = new Ice();
			Ice ic3 = new Ice();
			CoreMaterial.add(ic1);
			CoreMaterial.add(ic2);
			CoreMaterial.add(ic3);
			break;
		default:
			break;			
		}
	}
	
	//Ez egy olyan függvény
		public void IncUntCnt_DEBUG (){
			if(!CoreMaterial.isEmpty())
			{
				CoreMaterial.get(0).GetExposed(isSunside, this);
				CoreMaterial.get(1).GetExposed(isSunside, this);
				CoreMaterial.get(2).GetExposed(isSunside, this);
			}
		}
		public boolean getSunSide() {
			return isSunside;
		}
}