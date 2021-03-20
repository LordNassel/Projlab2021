package game_logic;
import game_logic.Field;
import java.util.Random;
import game_logic.Material;


public class Asteroid extends Field{
	//Attributes: Is the Asteroid sunside, and the value of the crust thickness
	private boolean isSunside;
	private int Thickness;
	//This is a bit magic number-ish, all asterouds store 3 units of material
	private Material[] CoreMaterial = new Material[3]();
	//This is the default constructor, this may however change. Right now it creates the random value for the thickness
	
	public Asteroid(Material M) {
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
		if(Thickness<=0) 
			Thickness--;
		else
			return false;
		return true;
	}
	
	//incredibly simple thing to check if the asteroid is empty
	private boolean isempty() {
		System.out.println("Asteroid.isempty Called");
		if(CoreMaterial[0]==null && CoreMateria[1]==null && CoreMaterial[2]==null)
			return false;
		return true;
	}
	
	//The mining function
	public Material GetMined(){
		System.out.println("Asteroid.GetMined() Called");
			if(isempty()) {
				return CoreMaterial[n];
				CoreMaterial[n]=null;
			}
		return null;
	}
	
	
	
	//The sunstorm handler. It is similar to all asteroid types

	public boolean StoreMaterial(Material M) {
		System.out.println("Asteroid.StoreMaterial Called");
		if(isempty())
			//The material cannot be stored as the asteroid is not empty
			return false;
			
		CoreMaterial[0]= M;
		return true;
	}
	
	//Hide in the asteroid
	public void GetHidden(Movable M) {
		System.out.println("Asteroid.Gethidden Called");
		if(isempty())
			M.SetHidden = true;
		return;
	}
	
}
