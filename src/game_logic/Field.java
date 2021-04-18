package game_logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public abstract class Field{
//Vector storage for convenience, an array would work too since <=300
protected Vector<Field> Neighbors = new Vector<Field>();
protected List<Movable> MovableList;
//protected boolean isMinable = false; //?
//Kell egy nev most ahhoz, hogy a paranccsoros navigacio letezhessen
private String name;


	public Field(String Name) {
	this.name = Name;
	MovableList = new ArrayList<Movable>();
	}
	
	//Explode. Kills all things on map then removes itself from the map.
	protected void Explode() {
	System.out.println("Field.Explode Called");
	for(int i=MovableList.size()-1; i>=0; i--) // Iteráció közben törölünk elemeket ezért inkább így
		MovableList.get(i).HitByExplosion();
	Neighbors = null;
	// Kell ide olyan ami külön beállítja a szomszédainál hogy õ már nincs?
	}

	//Sunstorm on a generic asteroid type, the asteroid remains undamaged, all movables die.
	public void ReachedBySunStorm() {
		System.out.println("Field.SunStorm Called");
		/*for( Movable movi : MovableList)
			movi.HitBySunStorm(); //ConcurentModificationt kapunk*/
		/*for(int i=0; i<MovableList.size(); i++)
			MovableList.get(i).HitBySunStorm();*/
		for(int i= MovableList.size()-1; i>=0; i--)
		{
			MovableList.get(i).HitBySunStorm(); //IndexOutOfBoundsExceptiont kapunk
		}

	}

	//Simple getter function for all neighbors, used mostly for player and AI navigation
	public Vector<Field> FindNeighbor(){/*System.out.println("Field.FindNeighbor Called")*/ return this.Neighbors;}


	//Simple neighbor setter functon
	public void SetNeighbor(Field WhichField) {/*System.out.println("Field.SetnNeighbor Called"*/ Neighbors.add(WhichField);}

	//To be implemented
	protected void BroadCastRadiation() {System.out.println("Field.BroadCastRadiation Called");};

	public void AcceptPlayer (Movable M) {
	//System.out.println("Field.AcceptPlayer Called");
	M.SetCurrentField(this);
	MovableList.add(M);
	}

	public void RemovePlayer(Movable M) {
		System.out.println("RemovePlayer Called");
		M.SetCurrentField(null);
		MovableList.remove(M);
	}

	public String Getname() {return this.name;}
	//Ez sajnos kell az aliennek
	//public boolean getMinable(){ return this.isMinable;}
	public abstract boolean GetDrilled();

	//Ez ket tipusellenorzest elkerulendo
	//public Material GetMined(){return null;}


}

