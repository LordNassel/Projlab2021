package game_logic;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Asteroid extends Field{
	//Attributes: Is the Asteroid sunside, and the value of the crust thickness
	private boolean isSunside;
	private int Thickness;
	//This is a bit magic number-ish, all asteroids store 3 units of material
	//private Material[] CoreMaterial = new Material[3];
	private List<Material> CoreMaterial = new ArrayList<Material>(3);
	//This is the default constructor, this may however change. Right now it creates the random value for the thickness
	
	public Asteroid(String name, Material M) {
		super(name);
		System.out.println("Asteroid.Constructor Called");
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		int n=0;
		while(n<3) {
			//CoreMaterial[n] = M;
			CoreMaterial.add(M);
			n++;
		}
		this.isSunside =false;
	}
	//sunside constructor
	public Asteroid(String name, Material M, boolean isSunside) {
		super(name);
		System.out.println("Asteroid.Constructor Called");
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		int n=0;
		while(n<3) {
			//CoreMaterial[n] = M;
			CoreMaterial.add(M);
			n++;
		}

		this.isSunside = isSunside;
	}
	//EZ EGY DEBUG KONSTRUKTOR, NE HIVD NORMALIS UZEMBEN
	public Asteroid(String name, Material M, boolean sunside, int thiccboi) {
		super(name);
		System.out.println("Asteroid.Constructor Called");
		int n=0;
		while(n<3) {
			//CoreMaterial[n] = M;
			CoreMaterial.add(M);
			n++;
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
				CoreMaterial.get(i).GetExposed(isSunside, this);
			/*CoreMaterial[0].GetExposed(isSunside, this);
			CoreMaterial[1].GetExposed(isSunside, this);
			CoreMaterial[2].GetExposed(isSunside, this);*/
			}
		}
		return true;
		
	}
	
	//incredibly simple thing to check if the asteroid is empty
	/*private int emptycnt() {
		for (int i=0; i<3; i++) {
			if(CoreMaterial[i]!= null)
				return i;
		}
		return -1;
	}*/
	
	//The mining function
	public Material GetMined(){
		System.out.println("Asteroid.GetMined() Called");
	//ha az aszteroida nem ures	
		if(!CoreMaterial.isEmpty())
		{
			Material mined = CoreMaterial.get(0);
			CoreMaterial.remove(0);
			return mined;
		}
		else
		{
			System.out.println("Error: The asteroid is empty\n");
			return null;
			
		}
		/*
		if(emptycnt()!=-1) {
			Material temp =CoreMaterial[emptycnt()];
			CoreMaterial[emptycnt()]=null;
			return temp;	
			}
		return null;*/
	}
	
	
	
	//The sunstorm handler. It is similar to all asteroid types

	public boolean StoreMaterial(Material M) {
		System.out.println("Asteroid.StoreMaterial Called");
		/*if(emptycnt()!=-1)
		{
			System.out.println("Material cannot be stored");
			//The material cannot be stored as the asteroid is not empty
			return false;
		}*/
		
		if(CoreMaterial.size()<3)
		{
			CoreMaterial.add(M);
			return true;
		}
		else
		{
			System.out.println("The materail cannot be stored\n");
			return false;
		}
			
		/*CoreMaterial[0]= M;
		return true;*/
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
			System.out.println("ASteroid is not empty to hide\n");

		/*if(emptycnt()==-1) {	// emptycnt !=-1 volt
			M.SetIsHidden();
		}
		return;*/
	}
	
	//Teszthez kell egyelore
	public void RemoveMaterialFromCore()
	{
		CoreMaterial.remove(0);
	}
	
	public void RemoveAllMaterialFromCore()
	{
		CoreMaterial.clear();
	}
	
	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}
}