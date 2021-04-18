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
	
	public Asteroid(String name, String M) {
		super(name);
		//System.out.println("Asteroid.Constructor Called");
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		
		switch(M)
		{
		case "Uranium":
			/*for(int i =0; i<CoreMaterial.size(); i++)
			{
				Uranium u = new Uranium();
				CoreMaterial.add(u);
				i++;
			}*/
			Uranium u1 = new Uranium();
			Uranium u2 = new Uranium();
			Uranium u3 = new Uranium();
			CoreMaterial.add(u1);
			CoreMaterial.add(u2);
			CoreMaterial.add(u3);

			break;
		case "Iron":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Iron ir = new Iron();
				CoreMaterial.add(ir);
				i++;
			}
			break;
		case "Coal":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Coal c = new Coal();
				CoreMaterial.add(c);
				i++;
			}
			break;
		case "Ice":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Ice ic = new Ice();
				CoreMaterial.add(ic);
				i++;
			}
			break;
		default:
			break;			
		}
		
		/*int n = 0;
		while (n < 3) {
			Material u = M;
			CoreMaterial.add(M);
			n++;
		}*/
		this.isSunside = false;
	}
	//Default empty constructor
	public Asteroid (String name){
			super(name);
			//System.out.println("Empty Asteroid.Constructor Called");
			Random thicknessgen = new Random();
			Thickness = thicknessgen.nextInt(11);
			CoreMaterial.clear();
		}

	//sunside constructor
	public Asteroid(String name, Material M, boolean isSunside) {
		super(name);
		//System.out.println("Asteroid.Constructor Called");
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		int n=0;
		while(n<3) {
			CoreMaterial.add(M);
			n++;
		}

		this.isSunside = isSunside;
	}
	//EZ EGY DEBUG KONSTRUKTOR, NE HIVD NORMALIS UZEMBEN
	public Asteroid(String name, String M, boolean sunside, int thiccboi) {
		super(name);
	//	System.out.println("Asteroid.Constructor Called");
		int n=0;
		/*while(n<3) {
			CoreMaterial.add(M);
			n++;
		}*/
		switch(M)
		{
		case "Uranium":
			/*for(int i =0; i<CoreMaterial.size(); i++)
			{
				Uranium u = new Uranium();
				CoreMaterial.add(u);
				i++;
			}*/
			Uranium u1 = new Uranium();
			Uranium u2 = new Uranium();
			Uranium u3 = new Uranium();
			CoreMaterial.add(u1);
			CoreMaterial.add(u2);
			CoreMaterial.add(u3);

			break;
		case "Iron":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Iron ir = new Iron();
				CoreMaterial.add(ir);
				i++;
			}
			break;
		case "Coal":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Coal c = new Coal();
				CoreMaterial.add(c);
				i++;
			}
			break;
		case "Ice":
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				Ice ic = new Ice();
				CoreMaterial.add(ic);
				i++;
			}
			break;
		default:
			break;			
		}
		Thickness = thiccboi;
		this.isSunside =sunside;
	}
	//Operations
	
	@Override//The drilling function. 
	public boolean GetDrilled(){
		System.out.println("Asteroid.GetDrilled Called");
		if(Thickness>0) 
			Thickness--;
		else
			return false;
		if(Thickness ==0) {
			//if the asteroid has been drilled the material inside will get exposed. 
			for(int i =0; i<CoreMaterial.size(); i++)
			{
				this.isMinable = true; //?????????? Field attrib.

			}
		}
		return true;
		
	}

	
	//The mining function
	public Material GetMined(){
		System.out.println("Asteroid.GetMined() Called");
		
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
		System.out.println("Asteroid.StoreMaterial Called");
		
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
		System.out.println("Asteroid.Gethidden Called");
		
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
	
	/*public boolean isEmpty() :DD
	{
		if(this.CoreMaterial.isEmpty() == true)
			return true;
		else
			return false;
	}*/

	/*//Test fuggvenyek
	public void RemoveMaterialFromCore()
	{
		CoreMaterial.remove(0);
	}
	
	public void RemoveAllMaterialFromCore()
	{
		CoreMaterial.clear();
	}
	*/
}