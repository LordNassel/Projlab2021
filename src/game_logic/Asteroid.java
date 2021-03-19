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
		if(Thickness<=0) 
			Thickness--;
		else
			return false;
		return true;
	}
	
	//incredibly simple thing to check if the asteroid is empty
	private boolean isempty() {
		if(CoreMaterial[0]==null && CoreMaterial1]==null && CoreMaterial[2]==null)
			return false;
		return true;
	}
	
	//The mining function
	public Material GetMined(){
			if(isempty()) {
				return CoreMaterial[n];
				CoreMaterial[n]=null;
			}
		return null;
	}
	
	
	
	//The sunstorm handler. It is similar to all asteroid types

	public boolean StoreMaterial(Material M) {
		if(isempty())
			//The material cannot be stored as the asteroid is not empty
			return false;
			
		CoreMaterial[0]= M;
		return true;
	}
	
	//Hide in the asteroid
	public void GetHidden(Movable M) {
		if(isempty())
			M.SetHidden = true;
		return;
	}
	
}
