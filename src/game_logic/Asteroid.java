package game_logic;
import java.util.Random;


public class Asteroid extends Field{
	//Attributes: Is the Asteroid sunside, and the value of the crust thickness
	private boolean isSunside;
	private int Thickness;
	//This is a bit magic number-ish, all asteroids store 3 units of material
	private Material[] CoreMaterial = new Material[3];
	//This is the default constructor, this may however change. Right now it creates the random value for the thickness
	
	public Asteroid(String name, Material M) {
		super(name);
		System.out.println("Asteroid.Constructor Called");
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
		int n=0;
		while(n<3) {
			CoreMaterial[n] = M;
			n++;
		}
	}
	//Operations
	
	//The drilling function. 
	public boolean GetDrilled(){
		System.out.println("Asteroid.GetDrilled Called");
		if(Thickness>=0) 
			Thickness--;
		else
			return false;
		if(Thickness ==0) {
			//if the asteroid has been drilled the material inside will get exposed.
			CoreMaterial[0].GetExposed(isSunside);
			CoreMaterial[1].GetExposed(isSunside);
			CoreMaterial[2].GetExposed(isSunside);
			}
		return true;
		
	}
	
	//incredibly simple thing to check if the asteroid is empty
	private int emptycnt() {
		for (int i=0; i<3; i++) {
			if(CoreMaterial[i]!= null)
				return i;
		}
		return -1;
	}
	
	//The mining function
	public Material GetMined(){
		System.out.println("Asteroid.GetMined() Called");
	//ha az aszteroida nem ures		
		if(emptycnt()!=-1) {
			Material temp =CoreMaterial[emptycnt()];
			CoreMaterial[emptycnt()]=null;
			return temp;	
			}
		return null;
	}
	
	
	
	//The sunstorm handler. It is similar to all asteroid types

	public boolean StoreMaterial(Material M) {
		System.out.println("Asteroid.StoreMaterial Called");
		if(emptycnt()!=-1)
			//The material cannot be stored as the asteroid is not empty
			return false;
			
		CoreMaterial[0]= M;
		return true;
	}
	
	//Hide in the asteroid
	public void GetHidden(Movable M) {
		System.out.println("Asteroid.Gethidden Called");
		if(emptycnt()!=-1) {
			M.Sethidden();
		}
		return;
	}
	
	@Override
	public void Step() {
		// TODO Auto-generated method stub
		
	}
}
