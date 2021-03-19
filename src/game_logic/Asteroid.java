package game_logic;
import game_logic.Field;
import java.util.Random;


public class Asteroid extends Field{
	//Attributes: Is the Asteroid sunside, and the value of the crust thickness
	private boolean isSunside;
	private int Thickness;
	//This is the default constructor, this may however change. Right now it creates the random value for the thickness
	
	public Asteroid() {
		Random thicknessgen = new Random();
		Thickness = thicknessgen.nextInt(11);
	}
	//Operations
	
	//to be implemented
	public boolean GetDrilled(){return true;}
	//to be implemented
	public Material GetMined(){}
	//this is what it does right now, but it will be expanded
	public void Explode(){System.out.println("CJ you a busta!!");}
	
	
}
