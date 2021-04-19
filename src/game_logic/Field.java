package game_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public abstract class Field{
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected List<Movable> MovableList;
//Kell egy nev most ahhoz, hogy a paranccsoros navigacio letezhessen
private String name;


	public Field(String Name) {
	this.name = Name;
	MovableList = new ArrayList<Movable>();
	}
	
	//Explode. Kills all things on map then removes itself from the map.
	protected void Explode() {
	for(int i=MovableList.size()-1; i>=0; i--) // Iteráció közben törölünk elemeket ezért inkább így
		MovableList.get(i).HitByExplosion();
	for(int i = Neighbors.size()-1 ; i>=0; i--)
	{
		Neighbors.get(i).RemoveNeighbor(this);
	}
	Neighbors = null;

	// Kell ide olyan ami külön beállítja a szomszédainál hogy õ már nincs nem?
	}

	//Sunstorm on a generic asteroid type, the asteroid remains undamaged, all movables die.
	public void ReachedBySunStorm() {		
		if(this.getClass() == Asteroid.class)
		{
			List<Teleport> teleports = ((Asteroid)this).getTeleportsOnAsteroid();
			for(int x = 0; x< teleports.size(); x++)
				teleports.get(x).ReachedBySunStorm();
		}
		
		for(int i= MovableList.size()-1; i>=0; i--)
		{
			MovableList.get(i).HitBySunStorm(); //IndexOutOfBoundsExceptiont kapunk ha nem igy csinaljuk
		}

	}

	//Simple getter function for all neighbors, used mostly for player and AI navigation
	public Vector<Field> FindNeighbor(){/*System.out.println("Field.FindNeighbor Called")*/ return this.Neighbors;}


	//Simple neighbor setter functon
	public void SetNeighbor(Field WhichField) {
		Neighbors.add(WhichField);
	}
	
	public void RemoveNeighbor(Field field)
	{
		Neighbors.remove(field);
	}

	//To be implemented
	//protected void BroadCastRadiation() {System.out.println("Field.BroadCastRadiation Called");};

	public void AcceptPlayer (Movable M) {
	M.SetCurrentField(this);
	MovableList.add(M);
	}

	public void RemovePlayer(Movable M) {
		MovableList.remove(M);
	}

	public String Getname() {return this.name;}
	//Ez sajnos kell az aliennek
	public abstract boolean GetDrilled();

	//Ez ket tipusellenorzest elkerulendo
	//public Material GetMined(){return null;}


}

